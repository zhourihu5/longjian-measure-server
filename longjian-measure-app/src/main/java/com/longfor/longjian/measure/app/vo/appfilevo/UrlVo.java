package com.longfor.longjian.measure.app.vo.appfilevo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 10:50
 */
@Data
public class UrlVo {
    /**
     * MD5值
     */
    private String md5;
    /**
     * 下载地址
     */
    private List<String> path;
}
