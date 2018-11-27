package com.longfor.longjian.measure.domain.externalService;

import java.util.List;
import java.util.Map;

public interface IUserService {
    /**
     *
     * @param userIds
     * @return
     */
    List<Map<String,Object>> getUserByUserIds(List<Integer> userIds);
}
