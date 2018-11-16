package com.longfor.longjian.measure.app.vo.companyVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lipeishuai on 2018/11/11.
 */
@Setter
@Getter
@NoArgsConstructor
public class CompanyListVo implements Serializable{

    private List<CompanyVo> items;
    private List<LevelVo> levels;

}
