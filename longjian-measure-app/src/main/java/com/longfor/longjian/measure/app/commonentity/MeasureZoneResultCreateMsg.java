package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.app.vo.MsgAppendVo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@SuppressWarnings("squid:S1068")
public class MeasureZoneResultCreateMsg implements Serializable {

    @Data
    class MeasureZoneResult  implements Serializable{
        private String uuid;
        private String zone_uuid;
        private Integer proj_id;
        private Integer list_id;
        private Integer sender_id;
        private Integer area_id;
        private String area_path_and_id;
        private String category_key;
        private String category_path_and_key;
        private Integer ok_total;
        private Integer total;
        private Long timestamp;
    }

    private List<MeasureZoneResult> created_results;

    public MeasureZoneResultCreateMsg(){
        created_results = new ArrayList<>();
    }

    public void append(MsgAppendVo vo){
        MeasureZoneResult measureZoneResult = new MeasureZoneResult();
        measureZoneResult.setUuid(vo.getUuid());
        measureZoneResult.setZone_uuid(vo.getZoneUuid());
        measureZoneResult.setProj_id(vo.getProjId());
        measureZoneResult.setList_id(vo.getListId());
        measureZoneResult.setSender_id(vo.getSenderId());
        measureZoneResult.setArea_id(vo.getAreaId());
        measureZoneResult.setArea_path_and_id(vo.getAreaPathAndId());
        measureZoneResult.setCategory_key(vo.getCategoryKey());
        measureZoneResult.setCategory_path_and_key(vo.getCategoryPathAndKey());
        measureZoneResult.setOk_total(vo.getOkTotal());
        measureZoneResult.setTimestamp(vo.getTimeAt());
        measureZoneResult.setTotal(vo.getTotal());
        created_results.add(measureZoneResult);
    }

}
