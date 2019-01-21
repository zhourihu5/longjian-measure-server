package com.longfor.longjian.measure.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jiazm 2019/01/19 17:44
 */
@Component
@ConfigurationProperties(prefix = "export")
@Data
@NoArgsConstructor
public class ExportVo {
    private String measure_base_dir;
    private String measure_base_uri;
}
