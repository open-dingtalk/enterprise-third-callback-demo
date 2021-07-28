package com.dingtalk.callbackdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.util.DingCallbackCrypto;
import com.dingtalk.callbackdemo.config.AppConfig;
import com.dingtalk.callbackdemo.service.CallbackService;
import com.dingtalk.callbackdemo.service.HandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private HandlerService handlerService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    /**
     * 第三方企业 使用syncHttp推送，推送数据格式参考：https://developers.dingtalk.com/document/app/data-format
     * @param msgSignature
     * @param timeStamp
     * @param nonce
     * @param json
     * @return
     */
    @Override
    public Map<String, String> callback(String msgSignature, String timeStamp, String nonce, JSONObject json) {
        try {
            // 1.构建加解密方法，进行解密
            DingCallbackCrypto callbackCrypto = new DingCallbackCrypto(appConfig.getToken(), appConfig.getAesKey(), appConfig.getSuiteKey());
            String encryptMsg = json.getString("encrypt");
            String decryptMsg = callbackCrypto.getDecryptMsg(msgSignature, timeStamp, nonce, encryptMsg);

            // 2. 反序列化回调事件json数据
            JSONObject eventJson = JSON.parseObject(decryptMsg);
            log.info("eventJson: {}", eventJson);

            // 3. 异步处理业务逻辑
            executor.execute(() -> handlerService.handler(eventJson));


            // 4. 返回success的加密数据
            Map<String, String> successMap = callbackCrypto.getEncryptedMap("success");
            return successMap;

        } catch (DingCallbackCrypto.DingTalkEncryptException e) {
            e.printStackTrace();
            log.error("process callback failed！msg: {}", e);
            throw new RuntimeException("process callback failed！");
        }
    }

}
