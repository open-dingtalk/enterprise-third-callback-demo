package com.dingtalk.callbackdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.callbackdemo.service.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class CallbackController {
    @Autowired
    private CallbackService callbackService;

    @PostMapping("/callback")
    public Map<String, String> callback(@RequestParam(value = "msg_signature") String msgSignature,
                                        @RequestParam(value = "timestamp") String timeStamp,
                                        @RequestParam(value = "nonce") String nonce,
                                        @RequestBody JSONObject json) {

        log.info("CallbackController#callback params: msg_signature: {}, timeStamp: {}, nonce: {}, json: {}", msgSignature, timeStamp, nonce, json);
        return callbackService.callback(msgSignature, timeStamp, nonce, json);
    }
}
