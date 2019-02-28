package com.longfor.longjian.measure.app.vo.companyvo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lipeishuai on 2018/11/11.
 */

@ApiModel("公司信息")
@Setter
@Getter
@NoArgsConstructor
public class CompanyVo implements Serializable {

    private int id;
    private String name;
    private int parent_id;
    private String path;
    private int level;
    private int display_index;
    private int is_group;

    private List<ProjVo> projs;

}
