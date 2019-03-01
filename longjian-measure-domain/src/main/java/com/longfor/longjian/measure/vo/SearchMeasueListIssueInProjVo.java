package com.longfor.longjian.measure.vo;

import lombok.Data;

import java.util.List;

/**
 * @Date 2019/3/1 16:02
 * Created by Jiazhongmin
 */
@Data
public class SearchMeasueListIssueInProjVo {
    private Integer projectId;
    private Integer limit;
    private Integer page;
    private String category_key;
    private List<Integer> areaIdList;
    private List<String> measureListIdList;
    private List<String> createAtRangeList;
    private Integer status;
    private Integer repairer_id;
    private Boolean is_overdue;
}
