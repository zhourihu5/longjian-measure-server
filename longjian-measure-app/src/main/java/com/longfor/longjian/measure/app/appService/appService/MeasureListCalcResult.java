package com.longfor.longjian.measure.app.appService.appService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.measure.po.zhijian2.MeasureZoneResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasureListCalcResult {

    @Data
    class MeasureZoneGroupData{
        private String texture;
        private Map<String,MeasureZonePointData> data;
        private Double score;
    }

    @Data
    class MeasureZonePointData{
        /**
         * MeasurePointRule.Key
         */
        private String key;
        /**
         * 数据类型：1，boolean；2，整数(int)；14，浮点数（float64）
         */
        private Integer dataType;
        /**
         * 数据列表
         * Double[]
         */
        private String data;
        /**
         * 是否需要录入设计值
         */
        private Boolean designValueReqd;
        /**
         * 设计值
         */
        private Double designValue;
        /**
         * 合格数
         */
        private Integer okTotal;
        /**
         * 总数
         */
        private Integer total;
        /**
         * 合格情况序列：0，不合格；1，合格。例："0110001"，合格数=3，总数=7
         */
        private String seq;
        /**
         * 偏差值
         */
        private String deviation;
    }

//    public HashMap<String,Object> toMap(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("key",);
//    }

    /**
     * 实测实量计算合格率
     * @param formula
     * @param result
     */
    public void calcResult(String formula, MeasureZoneResult result){
        //todo
        // defer func() {
        //		if errMsg := recover(); errMsg != nil {
        //			log.Warning(errMsg)
        //			err = core.ErrorEnum.CALC_RESULT_FAILED
        //		}
        //	}()不太懂这段
        // 计算逻辑较复杂

        // todo 匿名函数，数据类型转换
        List<MeasureZoneGroupData> resultData = new ArrayList<>();
        JSONArray data = JSONArray.parseArray(result.getData());
        for (Object textResult:data
             ) {
            JSONObject d = (JSONObject)textResult;
            MeasureZoneGroupData r = new MeasureZoneGroupData();
            r.setTexture(d.getString("Texture"));
            r.setData(new HashMap<>());
            r.setScore(0.0);
            JSONArray singlePoints = JSONArray.parseArray(d.getString("Data"));
            for (Object singlePoint : singlePoints){
                JSONObject pd = (JSONObject)singlePoint;
                MeasureZonePointData npd = new MeasureZonePointData();
                npd.setKey(pd.getString("Key"));
                npd.setDataType(pd.getInteger("DataType"));
                npd.setData(pd.getString("Data"));
                npd.setDesignValueReqd(pd.getBooleanValue("DesignValueReqd"));
                npd.setDesignValue(pd.getDoubleValue("DesignValue"));
                r.getData().put(pd.getString("Key"),npd);
            }
            resultData.add(r);
        }
    }
}
