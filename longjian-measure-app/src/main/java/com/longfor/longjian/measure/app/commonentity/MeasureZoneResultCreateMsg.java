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
        @SuppressWarnings("squid:S00116")
        private String uuid;
        @SuppressWarnings("squid:S00116")
        private String zone_uuid;
        @SuppressWarnings("squid:S00116")
        private Integer proj_id;
        @SuppressWarnings("squid:S00116")
        private Integer list_id;
        @SuppressWarnings("squid:S00116")
        private Integer sender_id;
        @SuppressWarnings("squid:S00116")
        private Integer area_id;
        @SuppressWarnings("squid:S00116")
        private String area_path_and_id;
        @SuppressWarnings("squid:S00116")
        private String category_key;
        @SuppressWarnings("squid:S00116")
        private String category_path_and_key;
        @SuppressWarnings("squid:S00116")
        private Integer ok_total;
        @SuppressWarnings("squid:S00116")
        private Integer total;
        @SuppressWarnings("squid:S00116")
        private Long timestamp;
    }
    @SuppressWarnings("squid:S00116")
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
        measureZoneResult.setTimestamp(vo.getTimeAt() / 1000);
        measureZoneResult.setTotal(vo.getTotal());
        created_results.add(measureZoneResult);
    }

}
