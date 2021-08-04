package com.dingtalk.callbackdemo.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiServiceActivateSuiteRequest;
import com.dingtalk.api.response.OapiServiceActivateSuiteResponse;
import com.dingtalk.callbackdemo.config.AppConfig;
import com.dingtalk.callbackdemo.constant.UrlConstant;
import com.dingtalk.callbackdemo.exception.InvokeDingTalkException;
import com.dingtalk.callbackdemo.handler.EventHandler;
import com.dingtalk.callbackdemo.util.AccessTokenUtil;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业授权
 */
@Service
public class SuiteAuthEventHandler implements EventHandler {

    @Autowired
    private AppConfig appConfig;

    /**
     * 授权回调
     *
     * @param bizData
     */
    @Override
    public void handler(JSONObject bizData) {

        String suiteAccessToken = AccessTokenUtil.getSuiteAccessToken(appConfig.getSuiteKey(), appConfig.getSuiteSecret());

        String permanentCode = bizData.getString("permanent_code");
        JSONObject authCorpInfo = bizData.getJSONObject("auth_corp_info");
        String corpId = authCorpInfo.getString("corpid");
        activateSuite(permanentCode, corpId, suiteAccessToken);

    }

    /**
     * 激活应用
     *
     * @param permanentCode
     * @param suiteAccessToken
     */
    public void activateSuite(String permanentCode, String corpId, String suiteAccessToken) {

        try {
            DingTalkClient client = new DefaultDingTalkClient(UrlConstant.ACTIVATE_SUITE_URL.replace("SUITE_ACCESS_TOKEN", suiteAccessToken));
            OapiServiceActivateSuiteRequest req = new OapiServiceActivateSuiteRequest();
            req.setSuiteKey(appConfig.getSuiteKey());
            req.setAuthCorpid(corpId);
            req.setPermanentCode(permanentCode);
            OapiServiceActivateSuiteResponse rsp = client.execute(req);
            if (!rsp.isSuccess()) {
                throw new InvokeDingTalkException(rsp.getErrorCode(), rsp.getErrmsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }

}
