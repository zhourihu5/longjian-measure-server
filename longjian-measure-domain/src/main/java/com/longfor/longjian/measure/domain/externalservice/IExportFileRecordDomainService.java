package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.vo.InsertFullVo;

import java.util.Map;

public interface IExportFileRecordDomainService {
    /**
     *
     * @param insertFullVo
     * @return
     */
    Map<String, Object> insertFull(InsertFullVo insertFullVo);
}
