package hut.zj.project.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.project.entity.Fund;
import hut.zj.project.entity.Project;
import hut.zj.project.query.ProjectQuery;
import hut.zj.project.service.IFundService;
import hut.zj.project.service.IProjectService;
import hut.zj.project.util.ProjectUtils;
import hut.zj.project.view.ProjectView;
import hut.zj.project.constant.ProjectStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Api("项目")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IFundService fundService;

    @ApiOperation(value="申报项目")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class) ProjectView projectView, BindingResult bindingResult) throws Exception {
        Project project = ProjectUtils.viewToProject(projectView);
        Project existence = projectService.findByName(project.getName());
        Assert.isNull(existence,"项目"+project.getName()+"已经存在");
        project.setFunds(null);
        //初始化一些东西
        project = ProjectUtils.init(project);
        List<Fund> funds = projectView.getFunds();
        Project savedProject = projectService.save(project);
        projectView.getFunds().stream().forEach(fund -> fund.setProjectId(savedProject.getId()));
        savedProject.setFunds((List<Fund>) fundService.save(projectView.getFunds()));
        return Result.getSuccess(ProjectUtils.projectToView(savedProject));
    }

    @ApiOperation(value="修改项目")
    @PutMapping("")
    @AutoErrorhandler
    public Result update(@RequestBody @Validated(V.Save.class)ProjectView projectView, BindingResult bindingResult) throws Exception {
        Project oldProject = projectService.findById(projectView.getId());
        Assert.notNull(oldProject,"修改的项目不存在!");
        Project updated = projectService.update(ProjectUtils.viewToProject(projectView),oldProject);
        return Result.getSuccess(ProjectUtils.projectToView(updated));
    }

    @ApiOperation(value="查询项目")
    @GetMapping("/query")
    @AutoErrorhandler
    public Result query(ProjectQuery query) throws Exception {
        PageResult pageResult = projectService.queryPage(query);
        List<Project> rows = pageResult.getRows();
        List<ProjectView> projectViewList = ProjectUtils.projectsToView(rows);
        pageResult.setRows(projectViewList);
        return Result.getSuccess(pageResult);
    }

    @ApiOperation(value = "根据id获取项目")
    @GetMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result findById(String id){
        Project project = projectService.findById(id);
        ProjectView projectView = ProjectUtils.projectToView(project);
        return Result.getSuccess(projectView);
    }

    @ApiOperation(value = "根据id删除项目(废弃)")
    @DeleteMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result delete(String id){
        return Result.getSuccess(projectService.changeStatus(id, ProjectStatus.ABANDONED));
    }

    @ApiOperation(value="项目申报")
    @PutMapping(value = "project_audit",params = {"id"})
    @AutoErrorhandler
    public Result audit(String id){
        Project project = projectService.findById(id);
        ProjectView projectView = ProjectUtils.projectToView(projectService.changeStatus(id, ProjectStatus.PROJECT_AUDIT));
        return Result.getSuccess(projectView);
    }


    @ApiOperation(value = "测试下载")
    @GetMapping("down_load")
    public void testDownLoad(HttpServletResponse response){
        String fileName = "hut/zj/college_project.sql";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("C://Users//86716//Desktop//"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

}
