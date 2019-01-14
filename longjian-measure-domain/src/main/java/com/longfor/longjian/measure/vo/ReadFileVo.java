package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/11 15:21
 */
@Data
@NoArgsConstructor
public class ReadFileVo {
    private byte[] content;
    private String name;
    private String mimeType;
}
