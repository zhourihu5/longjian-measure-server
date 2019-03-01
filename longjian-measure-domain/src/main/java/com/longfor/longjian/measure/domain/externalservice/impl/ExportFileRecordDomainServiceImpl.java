package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Maps;
import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.ExportFileRecordMapper;
import com.longfor.longjian.measure.domain.externalservice.IExportFileRecordDomainService;
import com.longfor.longjian.measure.po.zhijian2.ExportFileRecord;
import com.longfor.longjian.measure.vo.InsertFullVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Jiazm 2019/01/19 18:04
 */
@Service
@Slf4j
public class ExportFileRecordDomainServiceImpl implements IExportFileRecordDomainService {
    @Resource
    private ExportFileRecordMapper exportFileRecordMapper;

    @Override
    @LFAssignDataSource("zhijian2")
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> insertFull(InsertFullVo insertFullVo) {
        Date date = new Date();
        ExportFileRecord item = new ExportFileRecord();
        item.setUserId(insertFullVo.getUserId());
        item.setTeamId(insertFullVo.getTeamId());
        item.setProjectId(insertFullVo.getProjectId());
        item.setExportType(insertFullVo.getExportType());
        item.setParams(insertFullVo.getParams());
        item.setResultFilePath(insertFullVo.getResultFilePath());
        item.setResultName(insertFullVo.getResultName());
        item.setStatus(insertFullVo.getStatus());
        item.setErrorMsg(insertFullVo.getErrorMsg());
        item.setExecuteAt(insertFullVo.getExecuteAt());
        item.setCreateAt(date);
        item.setUpdateAt(date);
        int affected = exportFileRecordMapper.insertSelective(item);
        Map<String, Object> map = Maps.newHashMap();
        map.put("affected", affected);
        map.put("exportFileRecord", item);
        return map;
    }
}
