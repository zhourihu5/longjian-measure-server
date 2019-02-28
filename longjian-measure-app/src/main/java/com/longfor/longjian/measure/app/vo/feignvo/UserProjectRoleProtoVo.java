package com.longfor.longjian.measure.app.vo.feignvo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wang
 * @date 2018/12/29 0029 17:13
 */
@Data
@NoArgsConstructor
public class UserProjectRoleProtoVo extends UserRoleProtoVo implements Serializable {

    //项目id
    private Integer project_id;

}
