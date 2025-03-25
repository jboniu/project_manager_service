package com.javanoteany.project.service;

import com.javanoteany.common.base.service.IBaseService;
import com.javanoteany.project.entity.UpdateApplication;

public interface IUpdateApplicationService extends IBaseService<UpdateApplication> {

    /**
     * 变更申请通过
     * @param updateApplication
     * @return
     */
    UpdateApplication applicationPass(UpdateApplication updateApplication);

    /**
     * 申请变更
     * @param updateApplication
     * @return
     */
    UpdateApplication toApplication(UpdateApplication updateApplication);

    /**
     * 变更申请未通过
     * @param updateApplication
     * @return
     */
    UpdateApplication applicationNotPass(UpdateApplication updateApplication);
}
