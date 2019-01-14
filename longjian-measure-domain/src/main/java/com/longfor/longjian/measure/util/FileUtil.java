package com.longfor.longjian.measure.util;

import com.google.common.collect.Lists;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Jiazm 2019/01/11 16:28
 */
public class FileUtil {
    //将文件转换成byte数组
    public static byte[] urlTobyte(String url) throws IOException {
        URL ur = new URL(url);
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(ur.openStream());
            out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
        } catch (Exception e) {
            throw new MalformedURLException("error:" + e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new IOException("error:" + e);
            }
        }
        byte[] content = out.toByteArray();
        return content;
    }
    public static StoreUrlVo fileResourceGetStoreUrl(String storeKey) {
        StoreUrlVo storeUrlVo = new StoreUrlVo();
        if (storeKey.length() > 0) {
            String[] storeSchemaAndUrl = storeKey.split("/");
            String storeSchema = storeSchemaAndUrl[0];//图表
            List<String> stringUrl = Lists.newArrayList();
            String storeUri = storeSchemaAndUrl[storeSchemaAndUrl.length - 1];//url
            for (String schemaAndUrl : storeSchemaAndUrl) {
                if (schemaAndUrl.contains("-")) {
                    stringUrl.add(schemaAndUrl);
                    stringUrl.add(storeSchemaAndUrl[2]);
                }
            }
            if (stringUrl.size() > 1) {
                storeUri = StringUtils.join(stringUrl, "/");
            }
            storeUrlVo.setUri(storeUri);
            storeUrlVo.setSchema(storeSchema);
        }
        return storeUrlVo;
    }
     /*public static void main(String[] args) {
        StoreUrlVo urlVo = fileResourceGetStoreUrl("pictures/452699f53a5d42c3ad81878ab4bd5a0b.png");
        System.out.println(urlVo.getUri());
    }*/
}
