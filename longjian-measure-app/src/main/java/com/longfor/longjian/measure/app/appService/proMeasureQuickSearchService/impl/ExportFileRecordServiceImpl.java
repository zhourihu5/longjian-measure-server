package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IExportFileRecordService;
import com.longfor.longjian.measure.domain.externalService.IExportFileRecordDomainService;
import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.util.ExportExcelUtil;
import com.longfor.longjian.measure.vo.ExportVo;
import com.longfor.longjian.measure.vo.InputVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Jiazm 2019/01/19 16:04
 */
@Service
@Slf4j
public class ExportFileRecordServiceImpl implements IExportFileRecordService {
    @Resource
    private ExportVo exportVo;
    @Resource
    private IExportFileRecordDomainService exportFileRecordDomainService;

    @Override
    public ExportFileRecord create(Integer userId, Integer teamId, Integer projectId, Integer exportType, InputVo input, String exportName, Date executeAt, HttpServletResponse response) throws Exception {
        String inputFilename = null;
        String outputFilename = null;
        try {
           // String data =JSON.toJSONString(input);
            byte[] data = JSON.toJSONBytes(input);
            //随机一个长度不超过long的最大长度的整数
            Random random = new Random(Long.MAX_VALUE);
            long randCount = Math.abs(random.nextLong());
            String base_dir = exportVo.getMeasure_base_dir();
            Integer ts = DateUtil.dateToTimestamp(new Date());
            String base_uri = exportVo.getMeasure_base_uri();
            inputFilename = String.format("%d%d.%s", randCount, ts, "input");
            outputFilename = String.format("/export/%d%d.%s", randCount, ts, "output");
            String filepath = base_dir + inputFilename;
            //todo uat环境是异步导出 源码是同步导出。 暂时todo
            this.writeInput(data,exportName,filepath);
        } catch (Exception e) {
            log.error("error:" + e.getMessage());
            throw new Exception(e);
        }
        String filepath = exportVo.getMeasure_base_uri() + outputFilename;
        Map<String, Object> map = exportFileRecordDomainService.insertFull(userId, teamId, projectId, exportType, String.format("%s %s", inputFilename, outputFilename), filepath, exportName, 0, "", executeAt);
        ExportFileRecord exportFileRecord = (ExportFileRecord) map.get("exportFileRecord");
        return exportFileRecord;
    }

    private void writeInput(byte[] data,String exportName,String filepath) throws Exception {
        try {
            File file = new File(String.format("%s/%s",filepath,exportName));

            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            if(!file.exists()){
                file.createNewFile();
            }
            //todo 导出data数据未处理
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
