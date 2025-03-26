package hut.zj.project.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.project.dao.ProjectRepository;
import hut.zj.project.entity.Project;
import hut.zj.project.service.IProjectService;
import hut.zj.project.util.ProjectUtils;
import hut.zj.project.constant.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements IProjectService {
    public ProjectServiceImpl(BaseRepository<Project> repository) {
        super(repository);
    }

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BaseRepository<Project> baseRepository;

    @Override
    public Project update(Project newProject, Project oldProject) {
        newProject = ProjectUtils.someThingNotChange(newProject,oldProject);
        Project updated = baseRepository.save(newProject);
        return updated;
    }

    @Override
    public Project changeStatus(String projectId, ProjectStatus status) {
        Project project = projectRepository.findOne(projectId);
        Assert.notNull(project,"项目"+project.getName()+"不存在!");
        project.setStatus(status.getCode());
        return baseRepository.save(project);
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
}
