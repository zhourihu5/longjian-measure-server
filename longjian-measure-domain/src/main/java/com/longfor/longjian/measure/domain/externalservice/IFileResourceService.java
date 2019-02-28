package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.FileResource;

import java.io.IOException;

public interface IFileResourceService {
    /**
     *
     * @param md5
     * @return
     */
    FileResource getByMd5NoFoundErr(String md5);

    /**
     *
     * @param id
     * @return
     */
    byte[] readFileAll(Integer id) throws IOException;
}
