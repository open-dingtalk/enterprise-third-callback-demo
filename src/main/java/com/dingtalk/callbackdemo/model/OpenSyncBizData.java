package com.dingtalk.callbackdemo.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 高优先级数据
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "open_sync_biz_data")
public class OpenSyncBizData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create", nullable = false)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified", nullable = false)
    private Date gmtModified;

    /**
     * 订阅方ID
     */
    @Column(name = "subscribe_id", nullable = false)
    private String subscribeId;

    /**
     * 企业ID
     */
    @Column(name = "corp_id", nullable = false)
    private String corpId;

    /**
     * 业务ID
     */
    @Column(name = "biz_id", nullable = false)
    private String bizId;

    /**
     * 业务类型
     */
    @Column(name = "biz_type", nullable = false)
    private Integer bizType;

    /**
     * 业务数据
     */
    @Column(name = "biz_data", nullable = false)
    private String bizData;

    /**
     * 对账游标
     */
    @Column(name = "open_cursor", nullable = false)
    private Long openCursor;

    /**
     * 处理状态0为未处理。其他状态开发者自行定义
     */
    @Column(name = "status", nullable = false)
    private Integer status;

}
