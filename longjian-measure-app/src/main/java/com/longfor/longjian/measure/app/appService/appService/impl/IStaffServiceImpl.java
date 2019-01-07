package com.longfor.longjian.measure.app.appService.appService.impl;

import com.longfor.longjian.measure.app.appService.appService.IStaffService;
import com.longfor.longjian.measure.app.req.staff.RepairerListUpdateReq;
import com.longfor.longjian.measure.app.req.staff.SquadAddReq;
import com.longfor.longjian.measure.app.req.staff.SquadDeleteReq;
import com.longfor.longjian.measure.app.req.staff.SquadUpdateReq;
import com.longfor.longjian.measure.domain.externalService.IMeasureRepairerUserService;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadService;
import com.longfor.longjian.measure.domain.externalService.IMeasureSquadUserService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import com.longfor.longjian.measure.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    @Transactional
    public void squadAdd(SquadAddReq squadAddReq) {

        MeasureSquad measureSquad=new MeasureSquad();

        measureSquad.setName(squadAddReq.getName());
        measureSquad.setListId(squadAddReq.getList_id());
        measureSquad.setRate(0);
        measureSquad.setProjectId(squadAddReq.getProject_id());
        measureSquad.setPlanRate(squadAddReq.getPlan_rate());
        measureSquad.setCreateAt(new Date());
        measureSquad.setUpdateAt(new Date());

        int id=iMeasureSquadService.create(measureSquad);

        String[] ids=squadAddReq.getUser_id_list().split(",");

        for(int i=0;i<ids.length;i++){
            MeasureSquadUser measureSquadUser=new MeasureSquadUser();
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
    public void squadUpdate(SquadUpdateReq squadUpdateReq) {
        String[] newUserIds=squadUpdateReq.getUser_id_list().split(",");
        MeasureSquad measureSquad=new MeasureSquad();

        measureSquad.setName(squadUpdateReq.getName());
        measureSquad.setId(squadUpdateReq.getSquad_id());
        measureSquad.setProjectId(squadUpdateReq.getProject_id());
        measureSquad.setListId(squadUpdateReq.getList_id());
        measureSquad.setProjectId(squadUpdateReq.getProject_id());
        measureSquad.setPlanRate(squadUpdateReq.getPlan_rate());
        measureSquad.setUpdateAt(new Date());

        iMeasureSquadService.update(measureSquad);


        MeasureSquadUser measureSquadUser=new MeasureSquadUser();
        measureSquadUser.setProjectId(squadUpdateReq.getProject_id());
        measureSquadUser.setListId(squadUpdateReq.getList_id());
        measureSquadUser.setSquadId(squadUpdateReq.getSquad_id());

        List<MeasureSquadUser> measureSquadUserList=iMeasureSquadUserService.select(measureSquadUser);//找出原有的组员

        Integer [] oldUserIds={};

        for(int i=0;i<measureSquadUserList.size();i++){
            oldUserIds[i]=measureSquadUserList.get(i).getUserId();
        }

        Integer[] newUseridsArr = (Integer[]) ConvertUtils.convert(newUserIds, Integer.class);

        List<Integer> deleteUserIds= ArrayUtil.getDiff(oldUserIds,newUseridsArr,"A-B");//找出删除组员并删除

        Map<String,Object> deleteMap=new HashMap<>();

        deleteMap.put("project_id",squadUpdateReq.getProject_id());
        deleteMap.put("list_id",squadUpdateReq.getList_id());
        deleteMap.put("squad_id",squadUpdateReq.getSquad_id());
        deleteMap.put("deleteUserIds",deleteUserIds);
        deleteMap.put("delete_at",new Date());

        iMeasureSquadUserService.deleteOld(deleteMap);

        List<Integer> addUserIds= ArrayUtil.getDiff(oldUserIds,newUseridsArr,"B-A");//找出新组员并添加

        List<MeasureSquadUser>measureSquadUsers=new ArrayList<>();

        for(int j=0;j<addUserIds.size();j++){

            MeasureSquadUser newMUser=new MeasureSquadUser();

            newMUser.setSquadId(squadUpdateReq.getSquad_id());
            newMUser.setProjectId(squadUpdateReq.getProject_id());
            newMUser.setListId(squadUpdateReq.getList_id());
            newMUser.setUserId(addUserIds.get(j));
            measureSquadUsers.add(newMUser);
        }
        iMeasureSquadUserService.insertList(measureSquadUserList);

    }

    @Override
    public void squadDelete(SquadDeleteReq squadDeleteReq) {

        MeasureSquadUser measureSquadUser=new MeasureSquadUser();
        measureSquadUser.setProjectId(squadDeleteReq.getProject_id());
        measureSquadUser.setListId(squadDeleteReq.getList_id());
        measureSquadUser.setSquadId(squadDeleteReq.getSquad_id());
        measureSquadUser.setDeleteAt(new Date());

        iMeasureSquadUserService.deleteMeasureSquadUser(measureSquadUser);

        MeasureSquad measureSquad=new MeasureSquad();
        measureSquad.setProjectId(squadDeleteReq.getProject_id());
        measureSquad.setListId(squadDeleteReq.getList_id());
        measureSquad.setId(squadDeleteReq.getSquad_id());
        measureSquad.setDeleteAt(new Date());

        iMeasureSquadService.update(measureSquad);
    }

    @Override
    public void repairerListUpdate(RepairerListUpdateReq repairerListUpdateReq) {

        String[] userIds=repairerListUpdateReq.getUser_id_list().split(",");

        MeasureRepairerUser measureRepairerUser=new MeasureRepairerUser();

        measureRepairerUser.setProjectId(repairerListUpdateReq.getProject_id());
        measureRepairerUser.setListId(repairerListUpdateReq.getList_id());
        measureRepairerUser.setRoleType(repairerListUpdateReq.getRole_type());

       List<MeasureRepairerUser>measureRepairerUserList= iMeasureRepairerUserService.select(measureRepairerUser);

       Integer[] newUseridsArr = (Integer[]) ConvertUtils.convert(userIds, Integer.class);

       Integer [] prevUserId={};

       for(int i=0;i<measureRepairerUserList.size();i++){
           prevUserId[i]=measureRepairerUserList.get(i).getUserId();
       }

       List<Integer>  delUserId=ArrayUtil.getDiff(prevUserId,newUseridsArr,"A-B");

        Map<String,Object>map=new HashMap<>();
        map.put("list_id",repairerListUpdateReq.getList_id());
        map.put("role_type",repairerListUpdateReq.getRole_type());
        map.put("delUserId",delUserId);

        iMeasureRepairerUserService.delOld(map);//删除旧数据

        List<Integer>  addUserId=ArrayUtil.getDiff(prevUserId,newUseridsArr,"B-A");

        List<MeasureRepairerUser>newMeaRep=new ArrayList<>();

        for(int j=0;j<addUserId.size();j++){

            MeasureRepairerUser newMUser=new MeasureRepairerUser();
            newMUser.setRoleType(repairerListUpdateReq.getRole_type());
            newMUser.setListId(repairerListUpdateReq.getList_id());
            newMUser.setProjectId(repairerListUpdateReq.getProject_id());
            newMUser.setUserId(addUserId.get(j));
            newMeaRep.add(newMUser);
        }

        iMeasureRepairerUserService.insertList(newMeaRep);//新增人员
    }
}
