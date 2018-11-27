package com.longfor.longjian.measure.domain.externalService;

import java.util.List;

public interface IUserInProjectService {
    /**
     *
     * @param projectIds
     * @return
     */
    List<Integer> getUserIdByProjectIds(int[] projectIds);
}
