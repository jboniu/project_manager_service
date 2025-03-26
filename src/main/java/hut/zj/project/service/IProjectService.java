package hut.zj.project.service;

import hut.zj.project.entity.Project;
import hut.zj.common.base.service.IBaseService;
import hut.zj.project.constant.ProjectStatus;

public interface IProjectService extends IBaseService<Project>{

    /**
     * 项目更新
     * @param newProject
     * @param oldProject
     * @return
     */
    Project update(Project newProject, Project oldProject);

    /**
     * 修改项目状态
     * @param projectId
     * @param status
     * @return
     */
    Project changeStatus(String projectId, ProjectStatus status);

    /**
     * 根据项目名称查询
     * @param name
     * @return
     */
    Project findByName(String name);
}
