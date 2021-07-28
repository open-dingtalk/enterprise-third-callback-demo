package com.dingtalk.callbackdemo.factory;

import com.dingtalk.callbackdemo.config.ApplicationContextHolder;
import com.dingtalk.callbackdemo.handler.EventHandler;
import com.dingtalk.callbackdemo.handler.impl.SuiteAuthEventHandler;
import com.dingtalk.callbackdemo.handler.impl.SuiteTicketEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 企业授权相关回调
 */
@Component
public class SuiteAuthEventHandlerFactory extends AbstractEventHandlerFactory {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public EventHandler getEventHandler(String eventType) {
        if ("org_suite_auth".equalsIgnoreCase(eventType)) {
            // syncHttp推送方式授权应用
            return applicationContextHolder.getApplicationContext().getBean(SuiteAuthEventHandler.class);
        }
//        else if ("org_suite_change".equals(eventType)) {
//
//        } else if ("org_suite_relieve".equals(eventType)) {
//
//        }
        else {
            throw new RuntimeException("tmp eventType not match！");
        }
    }
}
