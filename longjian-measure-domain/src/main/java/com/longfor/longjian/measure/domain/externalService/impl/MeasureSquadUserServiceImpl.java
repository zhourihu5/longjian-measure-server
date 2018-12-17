package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureSquadUserMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureSquadUserServiceImpl implements IMeasureSquadUserService{

    @Autowired
    MeasureSquadUserMapper measureSquadUserMapper;

    @Override
    public List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt) {
        return measureSquadUserMapper.searchMeasureSquadUserByListIdTimestampGt(projectId, list_id, updateAtGt);
    }
}
