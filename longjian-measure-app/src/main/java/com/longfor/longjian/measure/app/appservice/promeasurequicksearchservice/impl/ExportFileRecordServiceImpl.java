package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.impl;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.DateUtil;
import com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice.IExportFileRecordService;
import com.longfor.longjian.measure.domain.externalservice.IExportFileRecordDomainService;
import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.vo.ExportVo;
import com.longfor.longjian.measure.vo.InputVo;
import com.longfor.longjian.measure.vo.InsertFullVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private Random rand;

    public ExportFileRecordServiceImpl() throws NoSuchAlgorithmException {
        rand = SecureRandom.getInstanceStrong();
    }

    @Override
    public ExportFileRecord create(Integer userId, Integer teamId, Integer projectId, Integer exportType, InputVo input, String exportName, Date executeAt) throws LjBaseRuntimeException {
        String inputFilename = null;
        String outputFilename = null;
        try {
            String data = JSON.toJSONString(input);
            //随机一个长度不超过long的最大长度的整数
            long randCount = (long) (rand.nextDouble() * Long.MAX_VALUE);
            String baseDir = exportVo.getMeasure_base_dir();
            Integer ts = DateUtil.dateToTimestamp(new Date());
            inputFilename = String.format("%d%d.%s", randCount, ts, "input");
            outputFilename = String.format("/export/%d%d.%s", randCount, ts, "output");
            String filepath = String.format("%s/%s", baseDir, inputFilename);
            log.info("{}",filepath);
            this.writeInput(data, exportName, filepath);
        } catch (Exception e) {
            log.error("error:" + e.getMessage());
            throw new LjBaseRuntimeException(-9999, e + "");
        }
        String filepath = exportVo.getMeasure_base_uri() + outputFilename;
        InsertFullVo insertFullVo = new InsertFullVo();
        insertFullVo.setUserId(userId);
        insertFullVo.setTeamId(teamId);
        insertFullVo.setProjectId(projectId);
        insertFullVo.setExportType(exportType);
        insertFullVo.setParams(String.format("%s %s", inputFilename, outputFilename));
        insertFullVo.setResultFilePath(filepath);
        insertFullVo.setResultName(exportName);
        insertFullVo.setStatus(0);
        insertFullVo.setErrorMsg("");
        insertFullVo.setExecuteAt(executeAt);
        Map<String, Object> map = exportFileRecordDomainService.insertFull(insertFullVo);
        return (ExportFileRecord) map.get("exportFileRecord");

    }

    private void writeInput(String data, String exportName, String filepath) throws IOException {
        FileOutputStream out = null;
        OutputStreamWriter op = null;
        try {
            log.info("erxportName :{}", exportName);
            out = new FileOutputStream(String.format("%s", filepath));
            op = new OutputStreamWriter(out, "utf-8");
            File file = new File(String.format("%s", filepath));

            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
                log.info("Mkdirs is {}",mkdirs);
            }

            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                log.info("createNewFile flag{}", newFile);
            }
            op.append(data);
            op.flush();
        } catch (IOException e) {
            log.error("error:", e);
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
