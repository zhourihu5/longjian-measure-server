package com.longfor.longjian.measure.app.FeignClient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.feignClientReq.ProjectPermissionReq;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "permission",configuration = LFFeignConfiguration.class)
public interface IPermissionService {

    @PostMapping(value = "project_permission" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<Object> projectPermission(ProjectPermissionReq projectPermissionReq);
}
