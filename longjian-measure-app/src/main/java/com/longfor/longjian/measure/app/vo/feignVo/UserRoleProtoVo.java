package com.longfor.longjian.measure.app.vo.feignVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wang
 * @date 2019/1/2 0002 11:01
 */
@Data
@NoArgsConstructor
public class UserRoleProtoVo implements Serializable {

    private Integer user_id;
    private Integer role_id;
    private String role_name;
    private Integer group_id;
    private Integer role_type;
    private Integer system;
}
