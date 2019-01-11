package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.FileResource;

public interface IFileResourceService {
    /**
     *
     * @param md5
     * @return
     */
    FileResource getByMd5NoFoundErr(String md5);
}
