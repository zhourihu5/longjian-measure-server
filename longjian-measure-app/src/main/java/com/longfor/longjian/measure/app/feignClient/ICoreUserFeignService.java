package com.longfor.longjian.measure.app.feignClient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.feignReq.MultiGetReq;
import com.longfor.longjian.measure.app.req.feignReq.SearchByIdListReq;
import com.longfor.longjian.measure.app.vo.feignVo.ProjAreaSearchByIdListVo;
import com.longfor.longjian.measure.app.vo.feignVo.UserInfoProtoListVRspoVo;
import com.longfor.longjian.measure.app.vo.feignVo.UserInfoProtoVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "user",configuration = LFFeignConfiguration.class)
public interface ICoreUserFeignService {

    @PostMapping(value = "user/multi_get/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<UserInfoProtoListVRspoVo> searchByUserIdList(@RequestBody MultiGetReq multiGetReq);
}
