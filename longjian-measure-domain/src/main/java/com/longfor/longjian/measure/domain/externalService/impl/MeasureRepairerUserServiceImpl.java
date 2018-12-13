package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureRepairerUserMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureRepairerUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureRepairerUserServiceImpl implements IMeasureRepairerUserService {

    @Autowired
    MeasureRepairerUserMapper measureRepairerUserMapper;

    @Override
    public List<MeasureRepairerUser> searchMeasureReparierUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt) {
        return measureRepairerUserMapper.searchMeasureReparierUserByListIdTimestampGt(projectId,list_id,updateAtGt);
    }
}
