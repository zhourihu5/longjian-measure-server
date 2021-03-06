package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.longjian.measure.dao.zhijian2.FileResourceMapper;
import com.longfor.longjian.measure.domain.externalservice.IFileResourceService;
import com.longfor.longjian.measure.po.zhijian2.FileResource;
import com.longfor.longjian.measure.util.FileUtil;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Jiazm 2019/01/10 13:44
 */
@Service
@Slf4j
public class FileResourceServiceImpl implements IFileResourceService {
    @Resource
    private FileResourceMapper fileResourceMapper;
    private static final String ERROR="error:";
    @Override
    public FileResource getByMd5NoFoundErr(String md5) {
        Example example = new Example(FileResource.class);
        example.createCriteria().andEqualTo("fileMd5", md5);
        return fileResourceMapper.selectOneByExample(example);
    }

    @Override
    public byte[] readFileAll(Integer id) throws IOException {
        Example example = new Example(FileResource.class);
        example.createCriteria().andEqualTo("id", id);
        FileResource fileResource = null;

        try {
            fileResource = fileResourceMapper.selectOneByExample(example);
        } catch (Exception e) {
            log.error(ERROR + e);
            return new byte[0];
        }
        return readFileAllByte(fileResource);
    }

    private byte[] readFileAllByte(FileResource fileResource) throws IOException {
        StoreUrlVo storeUrlVo = FileUtil.fileResourceGetStoreUrl(fileResource.getStoreKey());
        if (!storeUrlVo.getSchema().equals("pictures")) {//file
            throw new MalformedURLException("file is not a local file");
        }
        byte[] bytes = null;
        try {
            bytes = FileUtil.urlTobyte(storeUrlVo.getSchema()+"/"+ storeUrlVo.getUri());//文件路径跟名称
        } catch (MalformedURLException e) {
            log.error(ERROR + e);
            throw new MalformedURLException(ERROR+e);
        }
        return bytes;
    }
}
