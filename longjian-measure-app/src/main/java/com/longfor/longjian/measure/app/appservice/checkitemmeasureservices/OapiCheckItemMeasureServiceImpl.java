package com.longfor.longjian.measure.app.appservice.checkitemmeasureservices;

import com.google.common.collect.Maps;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.consts.GroupModuleEnum;
import com.longfor.longjian.common.entity.TeamBase;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.SessionInfo;
import com.longfor.longjian.measure.app.feignclient.IMeasureFeignService;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCategoryReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.GetCheckItemReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.ListTreeJsonReq;
import com.longfor.longjian.measure.app.req.checkitemmeasurereq.UpdateReqMeasureReq;
import com.longfor.longjian.measure.app.req.filereq.FileReq;
import com.longfor.longjian.measure.app.vo.checkitemsvo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.promeasurevo.*;
import com.longfor.longjian.measure.consts.constant.CtrlToolConstant;
import com.longfor.longjian.measure.consts.enums.LoginEnum;
import com.longfor.longjian.measure.domain.externalservice.ICategoryV3Service;
import com.longfor.longjian.measure.domain.externalservice.ICheckItemV3Service;
import com.longfor.longjian.measure.domain.externalservice.IFileResourceService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.CheckItemV3;
import com.longfor.longjian.measure.po.zhijian2.FileResource;
import com.longfor.longjian.measure.util.FileUtil;
import com.longfor.longjian.measure.vo.FormVo;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Jiazm 2019/01/09 20:01
 */
@Service
@Slf4j
public class OapiCheckItemMeasureServiceImpl implements IOapiCheckItemMeasureService {
    @Resource
    private ICheckItemV3Service checkItemV3Service;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IMeasureFeignService measureFeignService;
    @Resource
    private IFileResourceService fileResourceService;
    @Resource
    private CtrlTool ctrlTool;
    @Resource
    private ICategoryV3Service categoryV3Service;
    @Resource
    private SessionInfo sessionInfo;
    public static final String ERROR = "error:";

    @Override
    public LjBaseResponse<GetCheckItemVo> getCheckItemJson(GetCheckItemReq getCheckItemReq, HttpServletRequest request) throws LjBaseRuntimeException {
        try {
            ctrlTool.teamPerm(request, CtrlToolConstant.GROUP_MEASURE_CHECKITEM_CHECK);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999,e+"");
        }
        LjBaseResponse<GetCheckItemVo> ljBaseResponse = new LjBaseResponse<>();
        GetCheckItemVo getCheckItemVo = new GetCheckItemVo();

        String subKey = StringUtils.substring(getCheckItemReq.getKey(), 2, getCheckItemReq.getKey().length());
        try {
            CheckItemV3 checkItem = checkItemV3Service.getCheckItemByKeyNoFoundErr(subKey);
            CheckItemVo checkItemVo = new CheckItemVo();
            checkItemVo.setId(checkItem.getId());
            checkItemVo.setKey(checkItem.getKey());
            checkItemVo.setName(checkItem.getName());
            checkItemVo.setCustom_key(checkItem.getCustomKey());
            checkItemVo.setDesc(checkItem.getDesc());
            checkItemVo.setOrder(checkItem.getOrder());
            checkItemVo.setCommon_issues(checkItem.getCommonIssues());
            checkItemVo.setRequired_type(checkItem.getRequiredType());
            getCheckItemVo.setItem(checkItemVo);
            ljBaseResponse.setData(getCheckItemVo);
        } catch (Exception e) {
            log.error(ERROR + e);
            throw new LjBaseRuntimeException(-9999,e+"");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<GetCategoryVo> getCategoryJson(GetCategoryReq getCategoryReq, HttpServletRequest request) throws LjBaseRuntimeException {
        try {
            ctrlTool.teamPerm(request, "??????.????????????.???????????????.??????");
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999,e+"");
        }
        LjBaseResponse<GetCategoryVo> ljBaseResponse = new LjBaseResponse<>();
        GetCategoryVo getCategoryVo = new GetCategoryVo();

        String subKey = StringUtils.substring(getCategoryReq.getKey(), 2, getCategoryReq.getKey().length());
        try {
            CategoryV3 categoryV3 = checkItemV3Service.getCategoryByKeyNoFoundErr(subKey);
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(categoryV3.getId());
            categoryVo.setKey(categoryV3.getKey());
            categoryVo.setName(categoryV3.getName());
            categoryVo.setFather_key(categoryV3.getFatherKey());
            categoryVo.setCustom_key(categoryV3.getCustomKey());
            categoryVo.setDesc(categoryV3.getDesc());
            categoryVo.setOrder(categoryV3.getOrder());
            getCategoryVo.setItem(categoryVo);
            ljBaseResponse.setData(getCategoryVo);
        } catch (Exception e) {
            log.error(ERROR + e);
            throw new LjBaseRuntimeException(-9999,e+"");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<CheckItemUpdateJsonVo> updateJson(UpdateReqMeasureReq req, HttpServletRequest request) {
        LjBaseResponse<CheckItemUpdateJsonVo> ljBaseResponse = measureFeignService.updateJson(req.getCategory_id(), req.getCls(), req.getFile_md5(), req.getForce());
        LjBaseResponse<CheckItemUpdateJsonVo> ljBaseResponse2 = new LjBaseResponse<>();
        ljBaseResponse2.setData(ljBaseResponse.getData());
        ljBaseResponse2.setResult(ljBaseResponse.getResult());
        ljBaseResponse2.setMessage(ljBaseResponse.getMessage());
        ljBaseResponse2.setCode(ljBaseResponse.getCode());
        ljBaseResponse2.setTimestamp(ljBaseResponse.getTimestamp());
        return ljBaseResponse2;

    }

    @Override
    public LjBaseResponse<CheckItemListVo> listTreeJson(ListTreeJsonReq listTreeJsonReq, HttpServletRequest request) {
        LjBaseResponse<CheckItemListVo> ljBaseResponse = measureFeignService.listTreeJson(listTreeJsonReq.getId(), GroupModuleEnum.SCSL.getId());
        CheckItemListVo data = ljBaseResponse.getData();
        LjBaseResponse<CheckItemListVo> ljBaseResponse2 = new LjBaseResponse<>();
        ljBaseResponse2.setData(data);
        return ljBaseResponse;
    }

    @Override
    @SuppressWarnings("squid:S3776")
    public LjBaseResponse<Object> file(FileReq fileReq, HttpServletRequest request, HttpServletResponse response) throws LjBaseRuntimeException, IOException {
        TeamBase teamBase = null;
        try {
            ctrlTool.teamPerm(request, "??????.????????????.???????????????.??????");
            teamBase = (TeamBase) sessionInfo.getBaseInfo("cur_group");
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e.getMessage());
        }
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        FormVo formVo = new FormVo();
        formVo.setRootCategoryId(fileReq.getId());
        String name = "";
        String mimeType = null;
        CategoryV3 rc = new CategoryV3();
        FileResource fileResource = null;
        if (formVo.getRootCategoryId() > 0) {
            try {
                rc = checkItemV3Service.getRootCategoryNoFoundErr(formVo.getRootCategoryId());
                if (!rc.getTeamId().equals(teamBase.getTeamId())) {
                    ljBaseResponse.setMessage(LoginEnum.NO_PERMISSION.getName());
                    return ljBaseResponse;
                }
                fileResource = fileResourceService.getByMd5NoFoundErr(rc.getFileMd5());
                if (fileResource != null) {
                    name = fileResource.getFileName();
                    mimeType = fileResource.getMimeType();
                    fileResourceService.readFileAll(fileResource.getId());
                }
            } catch (Exception e) {
                ljBaseResponse.setMessage(e.getMessage());
                return ljBaseResponse;
            }

        }
        if (StringUtils.isBlank(name)) {
            try {
                String filename = "./templates/file_templates/category_import_measure.xlsx";
                name = "?????????????????????????????????.xlsx";
                FileUtil.urlTobyte(filename);
            } catch (IOException e) {
                ljBaseResponse.setMessage(ERROR + e);
                return ljBaseResponse;
            }
        }
        if (StringUtils.isBlank(mimeType)) {
            mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (StringUtils.isNotBlank(name)) {
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
        }
        response.addHeader("Content-Type", mimeType);
        StoreUrlVo storeUrlVo = null;
        if (fileResource != null) {
            storeUrlVo = FileUtil.fileResourceGetStoreUrl(fileResource.getStoreKey());
        }
        Map<String, Object> map = Maps.newHashMap();
        if (fileResource != null && storeUrlVo != null) {
            map.put("schema", storeUrlVo.getSchema());
            map.put("uri", storeUrlVo.getUri());
            map.put("fileName", fileResource.getFileName());
        }
        byte[] buff = new byte[1024];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(map.get("schema").toString() + "/" + map.get("uri").toString()));
                OutputStream os = response.getOutputStream()){
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);

                os.flush();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return ljBaseResponse;
    }
}
