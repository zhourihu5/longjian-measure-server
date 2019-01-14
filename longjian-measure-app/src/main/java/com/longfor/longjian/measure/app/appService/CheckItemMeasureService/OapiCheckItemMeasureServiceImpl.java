package com.longfor.longjian.measure.app.appService.CheckItemMeasureService;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.consts.ErrorNumEnum;
import com.longfor.longjian.common.exception.CommonRuntimeException;
import com.longfor.longjian.common.req.feignClientReq.TeamPermissionReq;
import com.longfor.longjian.measure.app.feignClient.IMeasureFeignService;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.GetCategoryReq;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.GetCheckItemReq;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.ListTreeJsonReq;
import com.longfor.longjian.measure.app.req.CheckItemMeasureReq.UpdateReqMeasureReq;
import com.longfor.longjian.measure.app.req.fileReq.FileReq;
import com.longfor.longjian.measure.app.vo.checkItemsVo.CheckItemListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.*;
import com.longfor.longjian.measure.consts.Enum.LoginEnum;
import com.longfor.longjian.measure.consts.Enum.YesNoEnum;
import com.longfor.longjian.measure.domain.externalService.ICheckItemV3Service;
import com.longfor.longjian.measure.domain.externalService.IFileResourceService;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.CheckItemV3;
import com.longfor.longjian.measure.po.zhijian2.FileResource;
import com.longfor.longjian.measure.po.zhijian2_apisvr.Team;
import com.longfor.longjian.measure.util.FileUtil;
import com.longfor.longjian.measure.vo.FormVo;
import com.longfor.longjian.measure.vo.ReadFileVo;
import com.longfor.longjian.measure.vo.StoreUrlVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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

    @Override
    public LjBaseResponse<GetCheckItemVo> getCheckItemJson(GetCheckItemReq getCheckItemReq, HttpServletRequest request) {
        LjBaseResponse<GetCheckItemVo> ljBaseResponse = new LjBaseResponse<>();
        GetCheckItemVo getCheckItemVo = new GetCheckItemVo();
        HttpSession session = request.getSession();
        session.setAttribute("uid", 2);
        session.setAttribute("team_id", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        Integer teamId = (Integer) session.getAttribute("team_id");
        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setUser_id(uid);
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setPer_title("集团.实测实量.检查项管理.查看");
        LjBaseResponse<Object> response = permissionService.teamPermission(teamPermissionReq);
        Map data = JSON.parseObject(JSON.toJSONString(response.getData()), Map.class);
        Object has_per = data.get("has_per");
        String Yes = YesNoEnum.Yes.toString();
        if (!Yes.equals(has_per)) {
            throw new CommonRuntimeException(ErrorNumEnum.PermissionDenied);
        }
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
            log.error("error:" + e);
            throw new RuntimeException(e);
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<GetCategoryVo> getCategoryJson(GetCategoryReq getCategoryReq, HttpServletRequest request) {
        LjBaseResponse<GetCategoryVo> ljBaseResponse = new LjBaseResponse<>();
        GetCategoryVo getCategoryVo = new GetCategoryVo();
        HttpSession session = request.getSession();
        session.setAttribute("uid", 2);
        session.setAttribute("team_id", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        Integer teamId = (Integer) session.getAttribute("team_id");
        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setUser_id(uid);
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setPer_title("集团.实测实量.检查项管理.查看");
        LjBaseResponse<Object> response = permissionService.teamPermission(teamPermissionReq);
        Map data = JSON.parseObject(JSON.toJSONString(response.getData()), Map.class);
        Object has_per = data.get("has_per");
        String Yes = YesNoEnum.Yes.toString();
        if (!Yes.equals(has_per)) {
            throw new CommonRuntimeException(ErrorNumEnum.PermissionDenied);
        }
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
            log.error("error:" + e);
            throw new RuntimeException(e);
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
        LjBaseResponse<CheckItemListVo> ljBaseResponse = measureFeignService.listTreeJson(listTreeJsonReq.getId());
        CheckItemListVo data = ljBaseResponse.getData();
        LjBaseResponse<CheckItemListVo> ljBaseResponse2 = new LjBaseResponse<>();
        ljBaseResponse2.setData(data);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<Object> file(FileReq fileReq, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LjBaseResponse<Object> ljBaseResponse = new LjBaseResponse<>();
        //鉴权
        HttpSession session = request.getSession();
        session.setAttribute("uid", 2);
        session.setAttribute("team_id", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        Integer teamId = (Integer) session.getAttribute("team_id");
        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setUser_id(uid);
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setPer_title("集团.实测实量.检查项管理.查看");
        LjBaseResponse<Object> ljBaseResponse1 = permissionService.teamPermission(teamPermissionReq);
        Map data = JSON.parseObject(JSON.toJSONString(ljBaseResponse1.getData()), Map.class);
        Object has_per = data.get("has_per");
        String Yes = YesNoEnum.Yes.toString();
        if (!Yes.equals(has_per)) {
            throw new CommonRuntimeException(ErrorNumEnum.PermissionDenied);
        }
        FormVo formVo = new FormVo();
        formVo.setRootCategoryId(fileReq.getId());
        //readStructForm(request, formVo); todo 这行代码看不明白
        //不知道作用域为什么能直接取出对象？
        Team curTeam = (Team) request.getAttribute("cur_group");
        ReadFileVo readFileVo = new ReadFileVo();
        String name = null;
        String mimeType = null;
        byte[] content = null;
        CategoryV3 rc = null;
        FileResource fileResource = null;
        if (formVo.getRootCategoryId() > 0) {
            try {
                rc = checkItemV3Service.getRootCategoryNoFoundErr(formVo.getRootCategoryId());
                if (rc.getTeamId().equals(curTeam.getTeamId())) {
                    ljBaseResponse.setMessage(LoginEnum.NO_PERMISSION.getName());
                    return ljBaseResponse;
                }
                fileResource = fileResourceService.getByMd5NoFoundErr(rc.getFileMd5());
                if (fileResource != null) {
                    name = fileResource.getFileName();
                    mimeType = fileResource.getMimeType();
                    content = fileResourceService.readFileAll(fileResource.getId());
                }
            } catch (Exception e) {
                ljBaseResponse.setMessage("error:" + e);
                return ljBaseResponse;
            }

        }
        if (name.equals("")) {
            try {
                String filename = "./templates/file_templates/category_import_measure.xlsx";
                name = "实测实量检查项导入模板.xlsx";
                content = FileUtil.urlTobyte(filename);
            } catch (IOException e) {
                ljBaseResponse.setMessage("error:" + e);
                return ljBaseResponse;
            }
        }
        if (mimeType.equals("")) {
            mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (!name.equals("")) {
            response.addHeader("Content-Disposition", String.format("attachment; filename=/%s/", name));
        }
        StoreUrlVo storeUrlVo = FileUtil.fileResourceGetStoreUrl(fileResource.getStoreKey());
        Map<String, Object> map = Maps.newHashMap();
        map.put("schema", storeUrlVo.getSchema());
        map.put("uri", storeUrlVo.getUri());
        map.put("fileName", fileResource.getFileName());
        download(200, mimeType, content, map);
        return ljBaseResponse;
    }

    //未完成
    private void readStructForm(HttpServletRequest request, FormVo formVo) {
        readForm(request, getBindings(formVo));
    }

    //未完成
    private List<FormVo> getBindings(FormVo formVo) {
        return null;
    }

    //未完成
    private void readForm(HttpServletRequest request, List<FormVo> formVos) {

    }

    private void download(int code, String contentType, byte[] data, Map<String, Object> map) throws IOException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(map.get("schema").toString());
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(map.get("schema").toString() + "\\" + map.get("uri").toString());
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    throw new IOException(e1);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    throw new IOException(e1);
                }
            }
        }

    }
}
