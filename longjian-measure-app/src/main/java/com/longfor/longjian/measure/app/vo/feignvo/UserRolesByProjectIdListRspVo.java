package com.longfor.longjian.measure.app.vo.feignvo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class UserRolesByProjectIdListRspVo implements Serializable {

    private List<UserProjectRoleProtoVo> user_roles;//用户角色列表
}
