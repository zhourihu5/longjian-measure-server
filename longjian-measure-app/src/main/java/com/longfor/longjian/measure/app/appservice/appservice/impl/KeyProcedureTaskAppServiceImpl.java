package com.longfor.longjian.measure.app.appservice.appservice.impl;

import com.longfor.longjian.measure.app.appservice.appservice.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.consts.enums.ReportStatusEnum;
import com.longfor.longjian.measure.domain.externalservice.IReportResultService;
import com.longfor.longjian.measure.po.zhijian2.ReportResult;
import com.longfor.longjian.measure.util.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
@Service
public class KeyProcedureTaskAppServiceImpl implements IKeyProcedureTaskAppService {

    @Autowired
    private IReportResultService reportResultService;

    @Override
    public void startReport(String report_uuid, Integer uid, HttpServletRequest request) throws Exception {
        // 保存请求内容
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(() -> {
            try {
                writeRequestToFile(report_uuid, uid, request);
            } catch (Exception e) {
                log.error("错误", e);
            }
        }).start();
        //不传uuid的时候也允许上报，但不记录状态
        if (report_uuid.length() == 0) {
            return;
        }
        ReportResult item;
        try {
            item = reportResultService.getByReportUuid(report_uuid);
        } catch (Exception e) {
            throw new CommonException("查询记录出错");
        }
        if (item != null) {
            Integer status = item.getStatus();
            if (ReportStatusEnum.PADDING.getValue().equals(status)) {
                throw new CommonException("正在处理，请不要重复上报");
            }
            if (ReportStatusEnum.SUCCEED.getValue().equals(status)) {
                throw new CommonException("已上报成功，请不要重复上报");
            }
            item.setStatus(ReportStatusEnum.PADDING.getValue());
            item.setUpdateAt(new Date());
            reportResultService.updateByPrimaryKey(item);
        } else {
            ReportResult reportResult = new ReportResult();
            reportResult.setReportUuid(report_uuid);
            reportResult.setUserId(uid);
            reportResult.setStatus(ReportStatusEnum.PADDING.getValue());
            reportResult.setCreateAt(new Date());
            reportResult.setUpdateAt(new Date());
            reportResultService.insertSelective(reportResult);
        }
    }

    @Override
    public void updateReportStatus(String report_uuid, String reportUuidStatus) {
        ReportResult item = reportResultService.getByReportUuid(report_uuid);
        item.setStatus(Integer.parseInt(reportUuidStatus));
        item.setUpdateAt(new Date());
        reportResultService.updateByPrimaryKey(item);
    }

    private void writeRequestToFile(String uuid, Integer userId, HttpServletRequest request) throws Exception {
        // 记录上报请求的目录
        String requestRecordFileDir = "/data/zhijian/api_request_record";
        String recordFile = String.format("%s/%s-%d-%s.txt", requestRecordFileDir, DateTime.now().toString("yyyyMMddHHmmss"), userId, uuid);
        String userDir = System.getProperty("user.dir");
        String recordPath = recordFile;
        if (userDir.contains(":")) {
            requestRecordFileDir = userDir.substring(0, 2) + requestRecordFileDir;
            recordPath = userDir.substring(0, 2) + recordFile;
        }
        log.debug("writeRequestToFile " + recordPath);
        Path pathDir = Paths.get(requestRecordFileDir);
        Path pathFile = Paths.get(recordPath);
        List<String> context = new ArrayList<>();
        context.add(request.getMethod() + "\t");
        context.add(request.getRequestURI() + "\n");
        context.add(request.getCharacterEncoding() + "\t");
        if (!Files.exists(pathDir)) {
            Files.createDirectories(pathDir);
        }
        if (!Files.exists(pathFile)) {
            Files.createFile(pathFile);
        }
        Files.write(pathFile, context, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
