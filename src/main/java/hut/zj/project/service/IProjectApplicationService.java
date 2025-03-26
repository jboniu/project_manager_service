package hut.zj.project.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.project.entity.ProjectApplication;

public interface IProjectApplicationService extends IBaseService<ProjectApplication> {
    /**
     * 项目申请
     * @param projectApplication
     * @return
     */
    ProjectApplication toApplication(ProjectApplication projectApplication);

    /**
     * 项目申请通过
     * @param projectApplication
     * @return
     */
    ProjectApplication applicationPass(ProjectApplication projectApplication);

    /**
     * 项目申请未通过
     * @param projectApplication
     * @return
     */
    ProjectApplication applicationNotPass(ProjectApplication projectApplication);
}
