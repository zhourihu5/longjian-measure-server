package com.longfor.longjian.measure.app.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.FeignClient.IPermissionService;
import com.longfor.longjian.measure.app.req.feignClientReq.ProjectPermissionReq;
import com.longfor.longjian.measure.constant.ErrorConstant;
import com.longfor.longjian.measure.consts.Enum.LoginEnum;
import com.longfor.longjian.measure.consts.Enum.YesNoEnum;
import com.longfor.longjian.measure.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.bc.ObjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
public class CtrlTool {

    @Autowired
    private IPermissionService ipermissionService;

    private static IPermissionService permissionService;

    @PostConstruct
    public void init(){
        permissionService = ipermissionService;
    }

    /**
     *
     * 項目鑒權
     * @param request
     * @param perm
     */
    public static void ProjPerm(HttpServletRequest request,String perm) throws Exception {
//        proj = c.MustGet("cur_proj").(*models.Project)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer proj = 6;
        Integer user = 7556;

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(proj);
        projectPermissionReq.setUser_id(user);
        projectPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)){
            Enum hasPer;
            try {
                LjBaseResponse<Object> res = permissionService.projectPermission(projectPermissionReq);
                log.warn("project permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf (((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.Yes.equals(hasPer)){
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new Exception(err);
                }
            }catch (Exception e){
                log.warn(e + "");
                throw e;
            }
        }
    }

}
