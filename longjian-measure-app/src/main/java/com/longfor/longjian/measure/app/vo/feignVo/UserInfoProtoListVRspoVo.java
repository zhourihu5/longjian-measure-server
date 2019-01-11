package com.longfor.longjian.measure.app.vo.feignVo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class UserInfoProtoListVRspoVo implements Serializable {

    private List<UserInfoProtoVo> user_infos;//账号信息列表
}

