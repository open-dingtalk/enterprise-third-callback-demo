package com.dingtalk.callbackdemo.factory;


import com.dingtalk.callbackdemo.handler.EventHandler;

/**
 * 事件处理抽象工厂
 */
public abstract class AbstractEventHandlerFactory {

    public abstract EventHandler getEventHandler(String eventType);

}
