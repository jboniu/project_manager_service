package hut.zj.project.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.project.entity.UpdateApplication;

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
