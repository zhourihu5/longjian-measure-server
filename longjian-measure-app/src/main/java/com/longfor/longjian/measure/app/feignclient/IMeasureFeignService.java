package com.longfor.longjian.measure.app.feignclient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.vo.checkitemsvo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.CheckItemUpdateJsonVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Jiazm 2019/01/10 16:03
 */
@Service
@LFFeignClient(group = "longjian-basic-server",value = "measureCheckItem",configuration = LFFeignConfiguration.class)
public interface IMeasureFeignService {
    @PostMapping(value = "update_json" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<CheckItemUpdateJsonVo> updateJson(@RequestParam(required = true,name = "category_id") Integer categoryId,
                                                     @RequestParam(required = true,name = "cls")Integer cls,
                                                     @RequestParam(required = true,name = "file_md5")String fileMd5,
                                                     @RequestParam(required = true,name = "force")Boolean force);
    @GetMapping(value = "list_tree_json" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<CheckItemListVo> listTreeJson(@RequestParam(required = true,name = "id") Integer id,
                                                 @RequestParam(required = true,name = "module") String module);
}
