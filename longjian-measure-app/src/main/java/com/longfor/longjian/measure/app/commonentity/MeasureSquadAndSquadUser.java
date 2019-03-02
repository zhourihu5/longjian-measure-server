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
@SuppressWarnings("squid:S1068")
public class MeasureSquadAndSquadUser {
    @SuppressWarnings("squid:S00116")
    private MeasureSquad Squad;
    @SuppressWarnings("squid:S00116")
    private List<MeasureSquadUser> Users;
}


