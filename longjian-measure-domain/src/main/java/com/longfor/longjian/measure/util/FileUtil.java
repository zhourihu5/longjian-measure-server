package com.longfor.longjian.measure.util;

import com.google.common.collect.Lists;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Jiazm 2019/01/11 16:28
 */
public class FileUtil {
    //将文件转换成byte数组
    public static byte[] urlTobyte(String filePath) throws IOException {
        InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] buffer = new byte[fis.available()];
        return buffer;
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
    /* public static void main(String[] args) throws IOException {
         byte[] bytes = urlTobyte("pictures/2019-01-17/1547721951652018806.xlsx");
         System.out.println(bytes);
    }*/
}
