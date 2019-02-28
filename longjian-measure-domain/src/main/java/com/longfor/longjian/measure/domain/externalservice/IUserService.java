package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2_apisvr.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    /**
     *
     * @param userIds
     * @return
     */
    List<Map<String,Object>> getUserByUserIds(List<Integer> userIds);

    User getUserByUserId(Integer id);

    Map<Integer, User> getUsersByIds(List<Integer> collect);

    List<User> getUserEntitiesByUserIds(List<Integer> userIds);

    /**
     *
     * @param userIds
     * @return
     */
    Map<Integer, String> getUserRealNameMap(List<Integer> userIds);
}
