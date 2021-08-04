package com.dingtalk.callbackdemo.repository;

import com.dingtalk.callbackdemo.model.OpenSyncBizData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OpenSyncBizDataRepository extends JpaRepository<OpenSyncBizData, Long>, JpaSpecificationExecutor<OpenSyncBizData> {

}
