package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Jiazm
 * 2018/12/14 15:28
 */
@Data
public class MeasureIssueVo {
    private Integer id;
    private Integer area_id;
    private String area_path_and_id;
    private String attachment_md5_list;
    private String category_key;
    private String category_path_and_key;
    private Integer repairer_id;
    private Integer sender_id;
    private Integer squad_id;
    private Integer close_status;
    private Integer close_time;
    private Integer close_user;
    private Integer condition;
    private String desc;
    private String drawing_md5;
    private Integer destroy_at;
    private Integer destroy_user;
    private Integer plan_end_on;
    private Integer end_on;
    private Integer last_assigner;
    private Integer last_assigner_at;
    private Integer list_id;
    private Integer pos_x;
    private Integer pos_y;
    private Integer project_id;
    private Integer status;
    private Integer typ;
    private String uuid;
    private String region_uuid;
    private String zone_uuid;
    private Date client_create_at;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
}
