package com.longfor.longjian.measure.app.req.fileReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/11 11:42
 */
@Data
@NoArgsConstructor
public class FileReq {
    @NotNull
    private Integer team_id;
    @NotNull
    private Integer id;

    private String _download;
}
