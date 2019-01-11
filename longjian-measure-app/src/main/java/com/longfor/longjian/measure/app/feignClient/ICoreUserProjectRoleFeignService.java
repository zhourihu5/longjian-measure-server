package com.longfor.longjian.measure.app.feignClient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.feignReq.MultiGetReq;
import com.longfor.longjian.measure.app.req.feignReq.UserRolesByProjectIdReq;
import com.longfor.longjian.measure.app.vo.feignVo.UserRolesByProjectIdListRspVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "userProjectRole",configuration = LFFeignConfiguration.class)
public interface ICoreUserProjectRoleFeignService {

    @PostMapping(value = "rs/user_roles_by_project_id" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<UserRolesByProjectIdListRspVo> userRolesByProjectId(@RequestBody UserRolesByProjectIdReq userRolesByProjectIdReq);
}
