package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public interface IMeasureSquadUserService {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);

    /**
     *
     * @param id
     */
    void delete(Integer id);

    void create(MeasureSquadUser measureSquadUser);

    List<MeasureSquadUser> select(MeasureSquadUser measureSquadUser);

    void deleteOld(Map<String,Object> map);

    void insertList(List<MeasureSquadUser>measureSquadUserList);

    void deleteMeasureSquadUser(MeasureSquadUser measureSquadUser);

    List<MeasureSquadUser>selectByExample(Example example);
}
