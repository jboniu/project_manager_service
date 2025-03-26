package hut.zj.project.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.common.utils.CurrentUserUtils;
import hut.zj.project.constant.ApplicationStatus;
import hut.zj.project.dao.ConcludeApplicationRepository;
import hut.zj.project.entity.ConcludeApplication;
import hut.zj.project.service.IConcludeApplicationService;
import hut.zj.project.service.IProjectService;
import hut.zj.project.constant.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ConcludeApplicationServiceImpl extends BaseServiceImpl<ConcludeApplication> implements IConcludeApplicationService {
    public ConcludeApplicationServiceImpl(BaseRepository<ConcludeApplication> repository) {
        super(repository);
    }

    @Autowired
    private ConcludeApplicationRepository concludeApplicationRepository;

    @Autowired
    private IProjectService projectService;

    @Override
    @Transactional
    public ConcludeApplication toApplication(ConcludeApplication concludeApplication) {
        //申请结题中 项目状态变为 申请结题中 不可修改 不可申报 不可申请变更 不可申请结题
        projectService.changeStatus(concludeApplication.getProjectId(), ProjectStatus.CONCLUDE_AUDIT);
        concludeApplication.setStatus(ApplicationStatus.ON_APPLIACTION.getName());
        concludeApplication.setApplicant(CurrentUserUtils.getCurrentUser().getSecurityUserDto().getUserName());
        concludeApplication.setApplicateTime(new Date());
        return concludeApplicationRepository.save(concludeApplication);
    }

    @Override
    @Transactional
    public ConcludeApplication applicationPass(ConcludeApplication concludeApplication) {
        //结题申请通过 结题成功 不可修改 不可申请 不可申请变更 不可申请结题
        projectService.changeStatus(concludeApplication.getProjectId(),ProjectStatus.CONCLUDED);
        concludeApplication.setStatus(ApplicationStatus.SUCCESS_APPLICATION.getName());
        return concludeApplicationRepository.save(concludeApplication);
    }

    @Override
    @Transactional
    public ConcludeApplication applicationNotPass(ConcludeApplication concludeApplication) {
        //结题未通过 结题未通过 不可修改 不可申请 可以申请变更 可以申请结题
        projectService.changeStatus(concludeApplication.getProjectId(),ProjectStatus.FAIL_CONCLUDE_AUDIT);
        concludeApplication.setStatus(ApplicationStatus.FAIL_APPLICATION.getName());
        return concludeApplicationRepository.save(concludeApplication);
    }
}
