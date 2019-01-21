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
            //todo writeInput() 有 bug 不知道列明及长度
            //this.writeInput(data,exportName,filepath,response);
        } catch (Exception e) {
            log.error("error:" + e.getMessage());
            throw new Exception(e);
        }
        String filepath = exportVo.getMeasure_base_uri() + outputFilename;
        Map<String, Object> map = exportFileRecordDomainService.insertFull(userId, teamId, projectId, exportType, String.format("%s %s", inputFilename, outputFilename), filepath, exportName, 0, "", executeAt);
        ExportFileRecord exportFileRecord = (ExportFileRecord) map.get("exportFileRecord");
        return exportFileRecord;
    }

    private void writeInput(byte[] data,String exportName,String[] rowName, HttpServletResponse response) throws Exception {
        List<byte[]> list = Lists.newArrayList();
        list.add(data);
        Object[] array = list.toArray();
        List<Object[]> listObj =Lists.newArrayList();
        listObj.add(array);
        ExportExcelUtil exportExcelUtil =new ExportExcelUtil(exportName,rowName,listObj);
        exportExcelUtil.export(response);
    }

    /*public static void main(String[] args) {
        System.out.println("http://www.baidu.com?id=123&test=1");
        System.out.println(HtmlUtils.htmlEscape("http://www.baidu.com?id=123&test=1   <"));
    }*/
}
