package hut.zj.project.util;

import hut.zj.project.entity.Project;
import hut.zj.project.view.ProjectView;
import hut.zj.common.exception.CopyFailedException;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.common.utils.CurrentUserUtils;
import hut.zj.project.constant.ProjectStatus;
import hut.zj.project.dto.Member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectUtils {

    /**
     * 视图转换为实体
     * @param projectView
     * @return
     */
    public static Project viewToProject(ProjectView projectView){
        Project project = new Project();
        try {
            project = BeanCopyUtils.copy(projectView, Project.class);
        } catch (Exception e) {
            throw new CopyFailedException("projectView->project对象拷贝失败! "+e.getMessage());
        }
        project.setMemberJsons(Member.toJsons(projectView.getMembers()));
        return project;
    }

    /**
     * 实体转换为视图
     * @param project
     * @return
     */
    public static ProjectView projectToView(Project project){
        ProjectView projectView = new ProjectView();
        try {
            projectView = BeanCopyUtils.copy(project, ProjectView.class);
        } catch (Exception e) {
            throw new CopyFailedException("project->projectView对象拷贝失败! "+e.getMessage());
        }
        projectView.setMembers(Member.toMembers(project.getMemberJsons()));
        projectView.setStatusString(ProjectStatus.getByCode(project.getStatus()).getName());
        return projectView;
    }

    /**
     * 实体list转为视图list
     * @param projects
     * @return
     */
    public static List<ProjectView> projectsToView(List<Project> projects){
        List<ProjectView> projectViewList = new ArrayList<>();
        for(Project project : projects){
            projectViewList.add(ProjectUtils.projectToView(project));
        }
        return projectViewList;
    }

    /**
     * 这里设置project哪些属性不可被修改
     * @param newProject
     * @param oldProject
     * @return
     */
    public static Project someThingNotChange(Project newProject, Project oldProject) {
        newProject.setCreatedUser(oldProject.getCreatedUser());
        newProject.setStatus(oldProject.getStatus());
        newProject.setBeginTime(oldProject.getBeginTime());
        newProject.setCanUpdate(oldProject.isCanUpdate());
        newProject.setCanConclude(oldProject.isCanConclude());
        newProject.setCanApplication(oldProject.isCanApplication());
        newProject.setId(oldProject.getId());
        return newProject;
    }

    /**
     * 初始化设置一些默认值
     * @param project
     * @return
     */
    public static Project init(Project project){
        project.setCreatedUser(CurrentUserUtils.getCurrentUser().getSecurityUserDto().getUserName());
        project.setStatus(ProjectStatus.NOT_DECLARED.getCode());
        project.setCanApplication(true);
        project.setCanConclude(true);
        project.setCanUpdate(true);
        project.setBeginTime(new Date());
        return project;
    }
}
