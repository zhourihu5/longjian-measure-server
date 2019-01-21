package com.longfor.longjian.measure.domain.externalService;

import java.util.Date;
import java.util.Map;

public interface IExportFileRecordDomainService {
    /**
     *
     * @param userId
     * @param teamId
     * @param projectId
     * @param exportType
     * @param params
     * @param resultFilePath
     * @param resultName
     * @param status
     * @param errorMsg
     * @param executeAt
     * @return
     */
    Map<String, Object> insertFull(Integer userId, Integer teamId, Integer projectId, Integer exportType, String params, String resultFilePath, String resultName, int status, String errorMsg, Date executeAt);
}
