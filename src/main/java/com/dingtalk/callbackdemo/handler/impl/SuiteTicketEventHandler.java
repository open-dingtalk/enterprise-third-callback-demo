package com.dingtalk.callbackdemo.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.callbackdemo.handler.EventHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

/**
 * 推送suiteTicket
 */
@Service
public class SuiteTicketEventHandler implements EventHandler {

    /**
     * 接收到推送过来的suiteTicket时保存到application.properties(target里面)
     *  实际应用需要存到缓存里面并且设置过期事件，使用时优先从缓存中获取
     * @param eventJson
     */
    @Override
    public void handler(JSONObject eventJson) {

        JSONObject bizData = eventJson.getJSONObject("biz_data");
        String suiteTicket = bizData.getString("suiteTicket");
        Properties properties = new Properties();
        String fileName = "application.properties";
        Resource resource = new ClassPathResource(fileName);
        try {
            PathResource pathResource = new PathResource(resource.getURI());
            properties.load(resource.getInputStream());
            properties.setProperty("dingtalk.suite_ticket", suiteTicket);
            properties.store(pathResource.getOutputStream(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
