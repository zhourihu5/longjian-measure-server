package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 17:38
 */
@Data
public class MeasureSquadAndRepairerVo {
    /**
     *测量小组列表
     */
    private List<SquadListVo> squad_list;
    /**
     * 测量小组列表
     */
    private List<SquadUserListVo> squad_user_list;
    /**
     *爆点整改人员列表
     */
    private List<RepairerListVo> repairer_list;

}
