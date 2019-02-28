package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-12 19:09
 **/
@Data
public class MeasureSquadAndSquadUser {
    private MeasureSquad Squad;
    private List<MeasureSquadUser> Users;
}


