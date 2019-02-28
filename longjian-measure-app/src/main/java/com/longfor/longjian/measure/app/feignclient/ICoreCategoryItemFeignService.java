package com.longfor.longjian.measure.app.feignclient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.req.feignreq.CategoriesInfoReq;
import com.longfor.longjian.measure.app.vo.feignvo.CategoryJsonProtoItemVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "checkItem",configuration = LFFeignConfiguration.class)
public interface ICoreCategoryItemFeignService {

    @PostMapping(value = "category/get_categories_info" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<CategoryJsonProtoItemVo> searchByUserIdList(@RequestBody CategoriesInfoReq categoriesInfoReq);
}
