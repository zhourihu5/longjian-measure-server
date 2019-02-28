package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.impl;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService.IExportFileRecordService;
import com.longfor.longjian.measure.domain.externalService.IExportFileRecordDomainService;
import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.vo.ExportVo;
import com.longfor.longjian.measure.vo.InputVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Random;

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
            String data = JSON.toJSONString(input);
            //随机一个长度不超过long的最大长度的整数
            Random random = new Random();
            long randCount = (long) (random.nextDouble() * Long.MAX_VALUE);
            String base_dir = exportVo.getMeasure_base_dir();
            Integer ts = DateUtil.dateToTimestamp(new Date());
            String base_uri = exportVo.getMeasure_base_uri();
            inputFilename = String.format("%d%d.%s", randCount, ts, "input");
            outputFilename = String.format("/export/%d%d.%s", randCount, ts, "output");
            String filepath = String.format("%s/%s", base_dir, inputFilename);
            this.writeInput(data, exportName, filepath);
        } catch (Exception e) {
            log.error("error:" + e.getMessage());
            throw new Exception(e);
        }
        String filepath = exportVo.getMeasure_base_uri() + outputFilename;
        Map<String, Object> map = exportFileRecordDomainService.insertFull(userId, teamId, projectId, exportType, String.format("%s %s", inputFilename, outputFilename), filepath, exportName, 0, "", executeAt);
        ExportFileRecord exportFileRecord = (ExportFileRecord) map.get("exportFileRecord");
        return exportFileRecord;
    }

    private void writeInput(String data, String exportName, String filepath) throws Exception {
        FileOutputStream out = null;
        OutputStreamWriter op = null;
        try {
            out = new FileOutputStream(String.format("%s", filepath));
            op = new OutputStreamWriter(out, "utf-8");
            File file = new File(String.format("%s", filepath));

            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            op.append(data);
            op.flush();
        } catch (IOException e) {
            log.error("error:",e);
        } finally {
            if (op != null) {
                op.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
