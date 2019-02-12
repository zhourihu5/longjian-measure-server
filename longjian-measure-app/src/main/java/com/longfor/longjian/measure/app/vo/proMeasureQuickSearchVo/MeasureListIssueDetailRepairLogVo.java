package com.longfor.longjian.measure.app.vo.proMeasureQuickSearchVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Date 2019/2/12 11:27
 * Created by Jiazhongmin
 */
@Data
@NoArgsConstructor
public class MeasureListIssueDetailRepairLogVo {
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 提交时间
     */
    private Integer create_at;
    /**
     * 描述
     */
    private String content;
    /**
     * 附件MD5列表
     */
    private List<String> attachment_md5_list;
}
