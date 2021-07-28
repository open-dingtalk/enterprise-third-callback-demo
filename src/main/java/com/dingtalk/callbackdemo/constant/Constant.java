package com.dingtalk.callbackdemo.constant;

/**
 * 项目中的常量定义类
 */
public class Constant {

    public static final String SUITE_TICKET = "suite_ticket";

    public static class BpmConstant{

        /**
         * 用于获取相对应工厂
         */
        public static final String BPMS = "bpms";

        /**
         * 事件类型 审批任务开始，结束，转交。
         */
        public static final String BPMS_TASK_CHANGE = "bpms_task_change";

        /**
         * 事件类型 审批实例开始，结束。
         */
        public static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";
    }

    public static class CheckUrl{
        /**
         * 用于获取相对应工厂
         */
        public static final String CHECK_URL = "check_url";

        /**
         * syncHttp推送 验证回调url事件
         * 参考文档：https://developers.dingtalk.com/document/app/validating-and-updating-callback-url-events-1
         *
         */
        public static final String CHECK_CREATE_URL = "check_create_suite_url";

        /**
         * syncHttp推送 更新回调url事件
         */
        public static final String CHECK_UPDATE_URL = "check_update_suite_url";

    }

    public static class OrgSuite{

        /**
         * 企业相关
         */
        public static final String ORG_SUITE = "org_suite";

        /**
         * 授权
         */
        public static final String ORG_SUITE_AUTH = "org_suite_org";

        /**
         * 授权范围变更
         */
        public static final String ORG_SUITE_CHANGE = "org_suite_change";

        /**
         * 解除授权
         */
        public static final String ORG_SUITE_RELIEVE = "org_suite_relieve";
    }
}
