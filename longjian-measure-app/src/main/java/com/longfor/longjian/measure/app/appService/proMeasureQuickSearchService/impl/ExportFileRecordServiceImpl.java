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
import java.io.*;
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
            String data = JSON.toJSONString(input);
            //byte[] data = ObjectToByte(input);
            //随机一个长度不超过long的最大长度的整数
            Random random = new Random(Long.MAX_VALUE);
            long randCount = Math.abs(random.nextLong());
            String base_dir = exportVo.getMeasure_base_dir();
            Integer ts = DateUtil.dateToTimestamp(new Date());
            String base_uri = exportVo.getMeasure_base_uri();
            inputFilename = String.format("%d%d.%s", randCount, ts, "input");
            outputFilename = String.format("/export/%d%d.%s", randCount, ts, "output");
            //String filepath = base_dir +"/"+ inputFilename;
            String filepath = String.format("%s/%s", base_dir, inputFilename);
            //todo 源码数据处理 看未进行对数据的操作暂时 已Json格式写入excel中 方便以后处理数据
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
        try {
            File file = new File(String.format("%s", filepath));
            //File file = new File(String.format("D:/%s", exportName));

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out;
            out = new FileOutputStream(String.format("%s", filepath));
            //out = new FileOutputStream(String.format("D:/%s",exportName));
            //String data1 = new String(data,"utf-8");
            OutputStreamWriter op = new OutputStreamWriter(out, "utf-8");
            op.append(data);
            //out.write(data);
            op.flush();
            op.close();
            /*out.write(data);
            out.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
