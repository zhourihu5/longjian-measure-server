package com.longfor.longjian.measure.app.commonEntity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
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

    public void append(String uuid, String zoneUuid,Integer projId, Integer listId, Integer senderId, Integer areaId ,
                                             String areaPathAndId, String categoryKey, String categoryPathAndKey,Integer okTotal, Integer total,
                                             Long timeAt){
        MeasureZoneResult measureZoneResult = new MeasureZoneResult();
        measureZoneResult.setUuid(uuid);
        measureZoneResult.setZone_uuid(zoneUuid);
        measureZoneResult.setProj_id(projId);
        measureZoneResult.setList_id(listId);
        measureZoneResult.setSender_id(senderId);
        measureZoneResult.setArea_id(areaId);
        measureZoneResult.setArea_path_and_id(areaPathAndId);
        measureZoneResult.setCategory_key(categoryKey);
        measureZoneResult.setCategory_path_and_key(categoryPathAndKey);
        measureZoneResult.setOk_total(okTotal);
        measureZoneResult.setTimestamp(timeAt);
        measureZoneResult.setTotal(total);
        created_results.add(measureZoneResult);
    }

}
