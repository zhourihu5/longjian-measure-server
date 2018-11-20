package com.longfor.longjian.measure.app.vo.appAnalysisVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20
 */
@Data
public class LogVo {
    private Integer id;
    private String uuid;
    private String issueUuid;
    private String desc;
    private Integer list;
    private Integer sender;
    private String senderName;
    private Integer typ;
    private Integer status;
    private String attachmentMd5List;
    private String attachmentMd5ListUrl;
    private String categoryKey;
    private String detail;
    private String clientCreateAt;
}
