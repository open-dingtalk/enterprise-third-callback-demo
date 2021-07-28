package com.dingtalk.callbackdemo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface HandlerService {

    void handler(JSONObject eventJson);
}
