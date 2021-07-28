package com.dingtalk.callbackdemo.factory;

import com.dingtalk.callbackdemo.config.ApplicationContextHolder;
import com.dingtalk.callbackdemo.handler.EventHandler;
import com.dingtalk.callbackdemo.handler.impl.SuiteTicketEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * suiteTicket 回调
 */
@Component
public class SuiteTicketEventHandlerFactory extends AbstractEventHandlerFactory {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public EventHandler getEventHandler(String eventType) {
        return applicationContextHolder.getApplicationContext().getBean(SuiteTicketEventHandler.class);
    }
}
