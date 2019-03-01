package com.longfor.longjian.measure.app.appservice.promeasurequicksearchservice;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.vo.InputVo;

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
    ExportFileRecord create(Integer curUserId, Integer teamId, Integer projId, Integer value, InputVo input, String format, Date date) throws LjBaseRuntimeException;
}
