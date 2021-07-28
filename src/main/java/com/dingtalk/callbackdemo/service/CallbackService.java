package com.dingtalk.callbackdemo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface CallbackService {

    Map<String, String> callback(String msgSignature, String timeStamp, String nonce, JSONObject json);
}
