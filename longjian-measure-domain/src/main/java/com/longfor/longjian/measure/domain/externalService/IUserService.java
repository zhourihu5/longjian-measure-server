package com.longfor.longjian.measure.domain.externalService;

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

    List<User> getUserEntitiesByUserIds(List<Integer> userIds);
}
