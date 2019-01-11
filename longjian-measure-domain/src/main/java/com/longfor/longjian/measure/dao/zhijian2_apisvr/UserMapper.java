package com.longfor.longjian.measure.dao.zhijian2_apisvr;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends LFMySQLMapper<User> {
    /**
     *
     * @param userIds
     * @return
     */
    List<Map<String,Object>> getUserByUserIds(@Param("userIds") List<Integer> userIds);

    /**
     * @Description:
     * @param id
     * @return com.longfor.longjian.measure.po.zhijian2_apisvr.User
     * @author DDC
     * @date 2019/1/11 15:50
     **/
    User getUserByUserId(@Param("id") Integer id);
}