package hut.zj.project.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.project.entity.ConcludeApplication;

public interface IConcludeApplicationService extends IBaseService<ConcludeApplication>{
    /**
     * 结题申请
     * @param concludeApplication
     * @return
     */
    ConcludeApplication toApplication(ConcludeApplication concludeApplication);

    /**
     * 结题通过
     * @param concludeApplication
     * @return
     */
    ConcludeApplication applicationPass(ConcludeApplication concludeApplication);

    /**
     * 结题未通过
     * @param concludeApplication
     * @return
     */
    ConcludeApplication applicationNotPass(ConcludeApplication concludeApplication);
}
