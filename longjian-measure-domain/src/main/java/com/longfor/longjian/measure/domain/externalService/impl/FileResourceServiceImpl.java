package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.FileResourceMapper;
import com.longfor.longjian.measure.domain.externalService.IFileResourceService;
import com.longfor.longjian.measure.po.zhijian2.FileResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * Jiazm 2019/01/10 13:44
 */
@Service
@Slf4j
public class FileResourceServiceImpl implements IFileResourceService {
    @Resource
    private FileResourceMapper fileResourceMapper;
    @Override
    public FileResource getByMd5NoFoundErr(String md5) {
        Example example =new Example(FileResource.class);
        example.createCriteria().andEqualTo("fileMd5",md5);
        return fileResourceMapper.selectOneByExample(example);
    }
}
