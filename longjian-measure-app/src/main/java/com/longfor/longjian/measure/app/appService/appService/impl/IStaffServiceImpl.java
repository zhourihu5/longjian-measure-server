package com.longfor.longjian.measure.app.appService.appService.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.measure.app.appService.appService.IStaffService;
import com.longfor.longjian.measure.app.feignClient.ICoreUserFeignService;
import com.longfor.longjian.measure.app.feignClient.ICoreUserProjectRoleFeignService;
import com.longfor.longjian.measure.app.req.feignReq.MultiGetReq;
import com.longfor.longjian.measure.app.req.feignReq.UserRolesByProjectIdReq;
import com.longfor.longjian.measure.app.req.staff.*;
import com.longfor.longjian.measure.app.vo.feignVo.UserInfoProtoListVRspoVo;
import com.longfor.longjian.measure.app.vo.feignVo.UserInfoProtoVo;
import com.longfor.longjian.measure.app.vo.feignVo.UserProjectRoleProtoVo;
import com.longfor.longjian.measure.app.vo.feignVo.UserRolesByProjectIdListRspVo;
import com.longfor.longjian.measure.app.vo.staffVo.AllowUserSearchVo;
import com.longfor.longjian.measure.app.vo.staffVo.RepairerListSearchVo;
import com.longfor.longjian.measure.domain.externalService.IMeasureRepairerUserService;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadService;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.util.ArrayUtil;
import com.longfor.longjian.measure.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by Wang on 2019/1/7.
 */
@Service
@Slf4j
public class IStaffServiceImpl implements IStaffService {

    @Autowired
    private IMeasureSquadService iMeasureSquadService;

    @Autowired
    private IMeasureSquadUserService iMeasureSquadUserService;


    @Autowired
    private IMeasureRepairerUserService iMeasureRepairerUserService;

    @Autowired
    private ICoreUserFeignService coreUserFeignService;

    @Autowired
    private ICoreUserProjectRoleFeignService coreUserProjectRoleFeignService;

    private static final String LISTID = "listId";
    private static final String PROJECTID = "projectId";

    @Override
    public JSONObject squadSearch(SquadSearchReq squadSearchReq) throws CommonException {

        JSONObject totalObject = new JSONObject();

        List<MeasureSquad> measureSquadList = new ArrayList<>();
        Example example = new Example(MeasureSquad.class);
        Example.Criteria criteria = example.createCriteria().
                andEqualTo(LISTID, squadSearchReq.getList_id());
        ExampleUtil.addDeleteAtJudge(example);
        Integer count = 0;
        if (squadSearchReq.getPage() != null && squadSearchReq.getPage_size() != null) {
            Page result = PageHelper.startPage(squadSearchReq.getPage(), squadSearchReq.getPage_size());
            iMeasureSquadService.selectByExample(example);
            measureSquadList = result.getResult();
        } else if (squadSearchReq.getPage() == null && squadSearchReq.getPage_size() == null) {

            measureSquadList = iMeasureSquadService.selectByExample(example);
        } else {
            throw new CommonException("参数错误");
        }
        count = iMeasureSquadService.selectByCount(example);

        List<MeasureSquadUser> measureSquadUserList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < measureSquadList.size(); i++) {

            JSONObject jb = new JSONObject();

            Example uexample = new Example(MeasureSquadUser.class);
            Example.Criteria ucriteria = uexample.createCriteria();
            ucriteria.andEqualTo("squadId", measureSquadList.get(i).getId());
            ucriteria.andIsNull("deleteAt");
            measureSquadUserList = iMeasureSquadUserService.selectByExample(uexample);

            jb.put("id", measureSquadList.get(i).getId());
            jb.put("name", measureSquadList.get(i).getName());
            jb.put("plan_rate", measureSquadList.get(i).getPlanRate());
            jb.put("rate", measureSquadList.get(i).getRate());

            List<Integer> userIdList = new ArrayList<>();

            for (int j = 0; j < measureSquadUserList.size(); j++) {
                userIdList.add(measureSquadUserList.get(j).getUserId());
            }

            jb.put("user_id_list", userIdList);

            MultiGetReq multiGetReq = new MultiGetReq();
            multiGetReq.setUser_ids(userIdList);
            multiGetReq.setGroup_id(squadSearchReq.getGroup_id());

            LjBaseResponse<UserInfoProtoListVRspoVo> ljBaseResponse = coreUserFeignService.searchByUserIdList(multiGetReq);

            List<UserInfoProtoVo> userInfoProtoVos = ljBaseResponse.getData().getUser_infos();

            List<String> userRealName = new ArrayList<>();

            for (int k = 0; k < userInfoProtoVos.size(); k++) {
                userRealName.add(userInfoProtoVos.get(k).getReal_name());
            }
            jb.put("user_name_list", userRealName);

            jsonArray.add(jb);
        }

        totalObject.put("squad_info", jsonArray);
        totalObject.put("total", count);

        return totalObject;
    }

    @Override
    public List<AllowUserSearchVo> allowUserSearch(AllowUserSearchReq allowUserSearchReq) {

        Example example = new Example(MeasureSquadUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, allowUserSearchReq.getProject_id());
        criteria.andEqualTo(LISTID, allowUserSearchReq.getList_id());
        ExampleUtil.addDeleteAtJudge(example);
        List<MeasureSquadUser> measureSquadUserList = iMeasureSquadUserService.selectByExample(example);

        Integer[] allUserIds = new Integer[measureSquadUserList.size()];

        for (int i = 0; i < measureSquadUserList.size(); i++) {
            allUserIds[i] = measureSquadUserList.get(i).getUserId();
        }

        UserRolesByProjectIdReq userRolesByProjectIdReq = new UserRolesByProjectIdReq();

        userRolesByProjectIdReq.setGroup_id(allowUserSearchReq.getGroup_id());
        userRolesByProjectIdReq.setProject_id(allowUserSearchReq.getProject_id());

        LjBaseResponse<UserRolesByProjectIdListRspVo> ljBaseResponse = coreUserProjectRoleFeignService.userRolesByProjectId(userRolesByProjectIdReq);//项目角色表中该项目下所有的用户id

        List<UserProjectRoleProtoVo> userProjectRoleProtoVoList = ljBaseResponse.getData().getUser_roles();

        Integer[] staffUserIds = new Integer[userProjectRoleProtoVoList.size()];

        for (int j = 0; j < userProjectRoleProtoVoList.size(); j++) {
            staffUserIds[j] = userProjectRoleProtoVoList.get(j).getUser_id();
        }

        List<Integer> allowUserIdList = ArrayUtil.getDiff(staffUserIds, allUserIds, "A-B");


        MultiGetReq multiGetReq = new MultiGetReq();
        multiGetReq.setUser_ids(allowUserIdList);
        multiGetReq.setGroup_id(allowUserSearchReq.getGroup_id());

        LjBaseResponse<UserInfoProtoListVRspoVo> userInfo = coreUserFeignService.searchByUserIdList(multiGetReq);//根据集团id  用户ids查询用户信息

        List<UserInfoProtoVo> userInfoProtoVos = userInfo.getData().getUser_infos();


        List<AllowUserSearchVo> allow_user_info = new ArrayList<>();

        for (int z = 0; z < userInfoProtoVos.size(); z++) {
            AllowUserSearchVo allowUserSearchVo = new AllowUserSearchVo();
            allowUserSearchVo.setUser_id(userInfoProtoVos.get(z).getUser_id());
            allowUserSearchVo.setReal_name(userInfoProtoVos.get(z).getReal_name());
            allow_user_info.add(allowUserSearchVo);
        }

        return allow_user_info;
    }

    @Override
    public List<RepairerListSearchVo> repairerListSearch(RepairerListSearchReq repairerListSearchReq) {

        Example example = new Example(MeasureRepairerUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, repairerListSearchReq.getProject_id());
        criteria.andEqualTo(LISTID, repairerListSearchReq.getList_id());
        criteria.andEqualTo("roleType", repairerListSearchReq.getRole_type());
        ExampleUtil.addDeleteAtJudge(example);
        List<MeasureRepairerUser> measureRepairerUserList = iMeasureRepairerUserService.selectByExample(example);

        List<Integer> repairerIdList = new ArrayList<>();

        for (int i = 0; i < measureRepairerUserList.size(); i++) {
            repairerIdList.add(measureRepairerUserList.get(i).getUserId());
        }

        MultiGetReq multiGetReq = new MultiGetReq();
        multiGetReq.setUser_ids(repairerIdList);
        multiGetReq.setGroup_id(repairerListSearchReq.getGroup_id());

        LjBaseResponse<UserInfoProtoListVRspoVo> userInfo = coreUserFeignService.searchByUserIdList(multiGetReq);//根据集团id  用户ids查询用户信息

        List<UserInfoProtoVo> userInfoProtoVos = userInfo.getData().getUser_infos();

        Map<Integer, String> map = new HashMap<>();

        for (int k = 0; k < userInfoProtoVos.size(); k++) {
            map.put(userInfoProtoVos.get(k).getUser_id(), userInfoProtoVos.get(k).getReal_name());
        }

        List<RepairerListSearchVo> repairer_info = new ArrayList<>();

        for (MeasureRepairerUser measureRepairerUser : measureRepairerUserList) {
            RepairerListSearchVo repairerListSearchVo = new RepairerListSearchVo();
            repairerListSearchVo.setId(measureRepairerUser.getId());
            repairerListSearchVo.setUser_id(measureRepairerUser.getUserId());
            repairerListSearchVo.setReal_name(map.get(measureRepairerUser.getUserId()));
            repairerListSearchVo.setRole_type(measureRepairerUser.getRoleType());

            repairer_info.add(repairerListSearchVo);
        }
        return repairer_info;
    }

    @Override
    @Transactional
    public void squadAdd(SquadAddReq squadAddReq) {

        MeasureSquad measureSquad = new MeasureSquad();

        measureSquad.setName(squadAddReq.getName());
        measureSquad.setListId(squadAddReq.getList_id());
        measureSquad.setRate(0);
        measureSquad.setProjectId(squadAddReq.getProject_id());
        measureSquad.setPlanRate(squadAddReq.getPlan_rate());
        measureSquad.setCreateAt(new Date());
        measureSquad.setUpdateAt(new Date());

        int id = iMeasureSquadService.create(measureSquad);

        String[] ids = squadAddReq.getUser_id_list().split(",");

        for (int i = 0; i < ids.length; i++) {
            MeasureSquadUser measureSquadUser = new MeasureSquadUser();
            measureSquadUser.setProjectId(squadAddReq.getProject_id());
            measureSquadUser.setUserId(Integer.parseInt(ids[i]));
            measureSquadUser.setSquadId(id);
            measureSquadUser.setListId(squadAddReq.getList_id());
            measureSquadUser.setCreateAt(new Date());
            measureSquadUser.setUpdateAt(new Date());
            iMeasureSquadUserService.create(measureSquadUser);
        }
    }

    @Override
    @Transactional
    public void squadUpdate(SquadUpdateReq squadUpdateReq) {

        String[] newUserIds = squadUpdateReq.getUser_id_list().split(",");
        MeasureSquad measureSquad = new MeasureSquad();

        measureSquad.setName(squadUpdateReq.getName());
        measureSquad.setId(squadUpdateReq.getSquad_id());
        measureSquad.setProjectId(squadUpdateReq.getProject_id());
        measureSquad.setListId(squadUpdateReq.getList_id());
        measureSquad.setProjectId(squadUpdateReq.getProject_id());
        measureSquad.setPlanRate(squadUpdateReq.getPlan_rate());
        measureSquad.setUpdateAt(new Date());

        iMeasureSquadService.update(measureSquad);


//        MeasureSquadUser measureSquadUser=new MeasureSquadUser();
//        measureSquadUser.setProjectId(squadUpdateReq.getProject_id());
//        measureSquadUser.setListId(squadUpdateReq.getList_id());
//        measureSquadUser.setSquadId(squadUpdateReq.getSquad_id());
        Example example = new Example(MeasureSquadUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, squadUpdateReq.getProject_id());
        criteria.andEqualTo(LISTID, squadUpdateReq.getList_id());
        criteria.andEqualTo("squadId", squadUpdateReq.getSquad_id());
        ExampleUtil.addDeleteAtJudge(example);

        List<MeasureSquadUser> measureSquadUserList = iMeasureSquadUserService.selectByExample(example);//找出原有的组员

        Integer[] oldUserIds = new Integer[measureSquadUserList.size()];

        for (int i = 0; i < measureSquadUserList.size(); i++) {
            oldUserIds[i] = measureSquadUserList.get(i).getUserId();
        }

        Integer[] newUseridsArr = (Integer[]) ConvertUtils.convert(newUserIds, Integer.class);

        List<Integer> deleteUserIds = ArrayUtil.getDiff(oldUserIds, newUseridsArr, "A-B");//找出删除组员并删除

        if (deleteUserIds.size() > 0) {
            Map<String, Object> deleteMap = new HashMap<>();

            deleteMap.put("project_id", squadUpdateReq.getProject_id());
            deleteMap.put("list_id", squadUpdateReq.getList_id());
            deleteMap.put("squad_id", squadUpdateReq.getSquad_id());
            deleteMap.put("deleteUserIds", deleteUserIds);
            deleteMap.put("delete_at", new Date());
            deleteMap.put("update_at", new Date());
            iMeasureSquadUserService.deleteOld(deleteMap);
        }


        List<Integer> addUserIds = ArrayUtil.getDiff(oldUserIds, newUseridsArr, "B-A");//找出新组员并添加

        List<MeasureSquadUser> measureSquadUsers = new ArrayList<>();

        for (int j = 0; j < addUserIds.size(); j++) {

            MeasureSquadUser newMUser = new MeasureSquadUser();

            newMUser.setSquadId(squadUpdateReq.getSquad_id());
            newMUser.setProjectId(squadUpdateReq.getProject_id());
            newMUser.setListId(squadUpdateReq.getList_id());
            newMUser.setUserId(addUserIds.get(j));
            newMUser.setCreateAt(new Date());
            newMUser.setUpdateAt(new Date());
            measureSquadUsers.add(newMUser);
        }
        if (measureSquadUsers.size() > 0) {
            iMeasureSquadUserService.insertList(measureSquadUsers);
        }

    }

    @Override
    @Transactional
    public void squadDelete(SquadDeleteReq squadDeleteReq) {

        MeasureSquadUser measureSquadUser = new MeasureSquadUser();
        measureSquadUser.setProjectId(squadDeleteReq.getProject_id());
        measureSquadUser.setListId(squadDeleteReq.getList_id());
        measureSquadUser.setSquadId(squadDeleteReq.getSquad_id());
        measureSquadUser.setDeleteAt(new Date());
        measureSquadUser.setUpdateAt(new Date());
        iMeasureSquadUserService.deleteMeasureSquadUser(measureSquadUser);

        MeasureSquad measureSquad = new MeasureSquad();
        measureSquad.setProjectId(squadDeleteReq.getProject_id());
        measureSquad.setListId(squadDeleteReq.getList_id());
        measureSquad.setId(squadDeleteReq.getSquad_id());
        measureSquad.setDeleteAt(new Date());

        iMeasureSquadService.update(measureSquad);
    }

    @Override
    @Transactional
    public void repairerListUpdate(RepairerListUpdateReq repairerListUpdateReq) {

        String[] userIds = repairerListUpdateReq.getUser_id_list().split(",");

//        MeasureRepairerUser measureRepairerUser=new MeasureRepairerUser();
//        measureRepairerUser.setProjectId(repairerListUpdateReq.getProject_id());
//        measureRepairerUser.setListId(repairerListUpdateReq.getList_id());
//        measureRepairerUser.setRoleType(repairerListUpdateReq.getRole_type());
        Example example = new Example(MeasureRepairerUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, repairerListUpdateReq.getProject_id());
        criteria.andEqualTo(LISTID, repairerListUpdateReq.getList_id());
        criteria.andEqualTo("roleType", repairerListUpdateReq.getRole_type());
        ExampleUtil.addDeleteAtJudge(example);
        List<MeasureRepairerUser> measureRepairerUserList = iMeasureRepairerUserService.selectByExample(example);

        Integer[] newUseridsArr = (Integer[]) ConvertUtils.convert(userIds, Integer.class);

        Integer[] prevUserId = new Integer[measureRepairerUserList.size()];

        for (int i = 0; i < measureRepairerUserList.size(); i++) {
            prevUserId[i] = measureRepairerUserList.get(i).getUserId();
        }

        List<Integer> delUserId = ArrayUtil.getDiff(prevUserId, newUseridsArr, "A-B");

        if (delUserId.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("list_id", repairerListUpdateReq.getList_id());
            map.put("role_type", repairerListUpdateReq.getRole_type());
            map.put("deleteUserIds", delUserId);
            map.put("delete_at", new Date());
            map.put("project_id", repairerListUpdateReq.getProject_id());
            map.put("update_at", new Date());
            iMeasureRepairerUserService.delOld(map);//删除旧数据
        }

        List<Integer> addUserId = ArrayUtil.getDiff(prevUserId, newUseridsArr, "B-A");

        List<MeasureRepairerUser> newMeaRep = new ArrayList<>();

        for (int j = 0; j < addUserId.size(); j++) {

            MeasureRepairerUser newMUser = new MeasureRepairerUser();
            newMUser.setRoleType(repairerListUpdateReq.getRole_type());
            newMUser.setListId(repairerListUpdateReq.getList_id());
            newMUser.setProjectId(repairerListUpdateReq.getProject_id());
            newMUser.setUserId(addUserId.get(j));
            newMUser.setCreateAt(new Date());
            newMUser.setUpdateAt(new Date());
            newMeaRep.add(newMUser);
        }
        if (newMeaRep.size() > 0) {
            iMeasureRepairerUserService.insertList(newMeaRep);//新增人员
        }
    }
}
