package com.dingtalk.callbackdemo.repository;

import com.dingtalk.callbackdemo.model.OpenSyncBizDataMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OpenSyncBizDataMediumRepository extends JpaRepository<OpenSyncBizDataMedium, Long>, JpaSpecificationExecutor<OpenSyncBizDataMedium> {

}
