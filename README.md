# Read Me First

项目主要是钉钉第三方企业应用回调，使用syncHttp和RDS两种推送方式，实际项目中使用其中一种即可。

syncHttp推送是钉钉调用回调接口，用户在回调接口根据不同的事件类型实现相应的业务逻辑，demo使用异步方法处理业务逻辑，处理失败时重试三次。

rds推送是钉钉把事件数据写入数据库，用户在项目中连接数据库，定时查询事件数据，根据不同的事件类型实现相应的业务逻辑。


# Getting Started

https://github.com/open-dingtalk/enterprises_third_callback-demo.git

### Reference Documentation

* [配置开发管理](https://developers.dingtalk.com/document/app/isvapp-development-overview)
* [SyncHTTP推送概述](https://developers.dingtalk.com/document/app/configure-synchttp-push)
* [RDS推送概述](https://developers.dingtalk.com/document/app/rds-push-overview-3)
* [推送数据格式](https://developers.dingtalk.com/document/app/data-format)
* [内网穿透](https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app)

### Guides

* 创建第三方企业应用
* 配置开发管理
* 修改服务端配置文件
* 根据业务逻辑实现相应的处理类
