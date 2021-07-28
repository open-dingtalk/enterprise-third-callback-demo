package com.dingtalk.callbackdemo.factory;

import com.dingtalk.callbackdemo.config.ApplicationContextHolder;
import com.dingtalk.callbackdemo.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取事件工厂 事件参考文档：https://developers.dingtalk.com/document/app/event-list
 */
@Component
public class EventHandlerFactoryProducer {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    /**
     * 根据事件类型获取响应工厂
     * @param eventType
     * @return
     */
    public AbstractEventHandlerFactory getEventHandlerFactory(String eventType) {

        if (eventType.startsWith(Constant.CheckUrl.CHECK_CREATE_URL) || eventType.startsWith(Constant.CheckUrl.CHECK_UPDATE_URL)) {
            // 验证回调和更新回调事件，目前没做任何处理，可以根据自己的业务实现业务逻辑
            return applicationContextHolder.getApplicationContext().getBean(CheckUrlEventHandlerFactory.class);
        } else if (eventType.startsWith(Constant.OrgSuite.ORG_SUITE)) {
             // 企业授权相关
            return applicationContextHolder.getApplicationContext().getBean(SuiteAuthEventHandlerFactory.class);
        } else if (eventType.startsWith(Constant.SUITE_TICKET)) {
            // suiteTicket 回调
            return applicationContextHolder.getApplicationContext().getBean(SuiteTicketEventHandlerFactory.class);
        }
//        else if (eventType.startsWith("chat")) {
//            // 群会话事件
//        } else if (eventType.startsWith("check_in")) {
//            // 签到事件
//        } else if (eventType.startsWith("attendance")) {
//            // 考勤事件
//        } else if (eventType.startsWith("meetingroom")) {
//            // 会议室事件
//        } else if (eventType.startsWith("edu")) {
//            // 家校通讯录事件
//        } else if (eventType.startsWith("user")) {
//            // 通讯录员工相关事件
//        } else if (eventType.startsWith("org")) {
//            // 通讯录企业相关事件
//        } else if (eventType.startsWith("label")) {
//            // 通讯录角色相关事件
//        }
        else {
            // 传入事件不匹配，需要自行处理
            throw new RuntimeException("eventType not match");
        }
    }
}
