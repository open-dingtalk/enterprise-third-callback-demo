package com.dingtalk.callbackdemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.callbackdemo.constant.Constant;
import com.dingtalk.callbackdemo.factory.EventHandlerFactoryProducer;
import com.dingtalk.callbackdemo.service.HandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Iterator;


@Service
@Slf4j
public class HandlerServiceImpl implements HandlerService {

    @Autowired
    private EventHandlerFactoryProducer eventHandlerFactoryProducer;

    /**
     * 调用处理接口处理业务逻辑，重试3次
     *
     * @param eventJson
     */
    @Retryable
    @Override
    public void handler(JSONObject eventJson) {

        log.info("开始处理业务逻辑");

        String eventType = eventJson.getString("EventType");

        if (eventType.equals(Constant.CheckUrl.CHECK_CREATE_URL) || eventType.equals(Constant.CheckUrl.CHECK_UPDATE_URL)) {
            // 根据EventType分类处理
            eventHandlerFactoryProducer.getEventHandlerFactory(eventType).getEventHandler(eventType).handler(eventJson);
        } else {

            JSONArray bizData = eventJson.getJSONArray("bizData");
            Iterator<Object> iterator = bizData.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                JSONObject biz_data = jsonObject.getJSONObject("biz_data");
                eventType = biz_data.getString("syncAction");
                eventHandlerFactoryProducer.getEventHandlerFactory(eventType).getEventHandler(eventType).handler(jsonObject);
            }
        }


        log.info("业务逻辑处理完毕");

    }
}
