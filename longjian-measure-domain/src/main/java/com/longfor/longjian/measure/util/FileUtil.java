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
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     /*public static void main(String[] args) {
        StoreUrlVo urlVo = fileResourceGetStoreUrl("pictures/452699f53a5d42c3ad81878ab4bd5a0b.png");
        System.out.println(urlVo.getUri());
    }*/
}
