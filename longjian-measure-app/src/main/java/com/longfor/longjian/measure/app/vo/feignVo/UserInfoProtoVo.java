package com.longfor.longjian.measure.app.vo.feignVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Houyan
 * @date 2019/1/2 0002 17:26
 */
@Data
@NoArgsConstructor
public class UserInfoProtoVo implements Serializable {
    private Integer user_id;
    private String user_name;
    private String real_name;
    private String email;
    private String mobile;
    private Integer status;
    private String group_code="";
    private Integer group_id;
    private Integer genre;//实际数据库中无该字段

}
