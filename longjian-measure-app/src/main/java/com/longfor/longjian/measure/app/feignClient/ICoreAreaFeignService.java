package com.longfor.longjian.measure.app.feignClient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.feignReq.SearchByIdListReq;
import com.longfor.longjian.measure.app.vo.feignVo.ProjAreaSearchByIdListVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "coreArea",configuration = LFFeignConfiguration.class)
public interface ICoreAreaFeignService {

    @PostMapping(value = "proj_area/search_by_id_list" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<ProjAreaSearchByIdListVo> searchByIdList(@RequestBody SearchByIdListReq searchByIdListReq);
}
