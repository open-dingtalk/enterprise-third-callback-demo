package com.dingtalk.callbackdemo.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.callbackdemo.config.AppConfig;
import com.dingtalk.callbackdemo.model.OpenSyncBizData;
import com.dingtalk.callbackdemo.model.OpenSyncBizDataMedium;
import com.dingtalk.callbackdemo.repository.OpenSyncBizDataMediumRepository;
import com.dingtalk.callbackdemo.repository.OpenSyncBizDataRepository;
import com.dingtalk.callbackdemo.service.HandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RDSHandler {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private OpenSyncBizDataRepository openSyncBizDataRepository;

    @Autowired
    private OpenSyncBizDataMediumRepository openSyncBizDataMediumRepository;

    @Autowired
    private HandlerService handlerService;

    private static final String SUBSCRIBE_ID_SUFFIX = "_0";

    /**
     * 定时查询数据库未处理的高优先级事件，处理成功后更改status为1
     *
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void handlerOpenSyncBizData() {

        String subscribeId = appConfig.getSuiteId() + SUBSCRIBE_ID_SUFFIX;

        List<OpenSyncBizData> openSyncBizDataList = openSyncBizDataRepository.findAll(
                Example.of(OpenSyncBizData.builder()
                        .subscribeId(subscribeId).status(0)
                        .build()));
        openSyncBizDataList.stream().forEach(openSyncBizData -> {
            try {
                JSONObject eventJson = JSONObject.parseObject(openSyncBizData.getBizData());
                handlerService.handlerBizData(eventJson);
                openSyncBizData.setStatus(1);
                openSyncBizDataRepository.save(openSyncBizData);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("高优先级事件处理失败 openSyncBizData : {}", openSyncBizData);
            }
        });
    }

    /**
     * 定时查询数据库未处理的低优先级事件，处理成功后更改status为1
     *
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void handlerOpenSyncBizDataMedium() {

        String subscribeId = appConfig.getSuiteId() + SUBSCRIBE_ID_SUFFIX;

        List<OpenSyncBizDataMedium> openSyncBizDataMediumList = openSyncBizDataMediumRepository.findAll(
                Example.of(OpenSyncBizDataMedium.builder()
                        .subscribeId(subscribeId).status(0)
                        .build()));
        openSyncBizDataMediumList.stream().forEach(openSyncBizDataMedium -> {
            try {
                JSONObject eventJson = JSONObject.parseObject(openSyncBizDataMedium.getBizData());
                handlerService.handlerBizData(eventJson);
                openSyncBizDataMedium.setStatus(1);
                openSyncBizDataMediumRepository.save(openSyncBizDataMedium);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("低优先级事件处理失败 openSyncBizDataMedium : {}", openSyncBizDataMedium);
            }
        });
    }

}
