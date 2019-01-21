package com.longfor.longjian.measure.app.appService.proMeasureQuickSearchService;

import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.vo.InputVo;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface IExportFileRecordService {
    /**
     *
     * @param curUserId
     * @param teamId
     * @param projId
     * @param value
     * @param input
     * @param format
     * @param date
     */
    ExportFileRecord create(Integer curUserId, Integer teamId, Integer projId, Integer value, InputVo input, String format, Date date, HttpServletResponse response) throws Exception;
}
