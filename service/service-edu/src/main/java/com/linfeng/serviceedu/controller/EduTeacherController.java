package com.linfeng.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linfeng.commonutils.Result;
import com.linfeng.serviceedu.entity.EduTeacher;
import com.linfeng.serviceedu.entity.vo.EduTeacherUpdateReq;
import com.linfeng.serviceedu.entity.vo.TeacherQueryReq;
import com.linfeng.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author linfeng
 * @since 2021-12-11
 */
@Api(description = "讲师管理增删改查")
@RestController
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation("得到所有讲师的信息")
    @PostMapping("/getTeachers")
    public Result getTeachers() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items", list);
    }


    @ApiOperation("根据ID删除教师")
    @DeleteMapping("{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation("分页，根据条件来查询教师")
    @PostMapping("/getTeacherPage")
    public Result selectTeacherPageByCondition(@ApiParam(value = "分页查询入参") @RequestBody TeacherQueryReq teacherQueryReq) {
        Page<EduTeacher> pageParam = new Page(teacherQueryReq.getPage(), teacherQueryReq.getLimit());
        //查询分页数据
        List<EduTeacher> pageTeachers = eduTeacherService.selectTeacherPage(teacherQueryReq, pageParam);
        int size = pageTeachers.size();
        return Result.ok().data("page", pageTeachers).data("total", size);

    }

    @ApiOperation("新增老师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@ApiParam(value = "新增老师入参") @RequestBody EduTeacher eduTeacherReq) {
        boolean save = eduTeacherService.save(eduTeacherReq);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation("根据ID查询老师")
    @PostMapping("/selectTeacherById")
    public Result selectTeacherById(@ApiParam(value = "老师ID") @RequestParam("id") long id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        if (teacher != null) {
            return Result.ok().data("teacher", teacher);
        } else {
            return Result.error().message("该老师不存在");
        }
    }

    @ApiOperation("根据ID修改老师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@ApiParam(value = "新增老师参数") @RequestBody EduTeacherUpdateReq eduTeacherUpdateReq) {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(eduTeacherUpdateReq, eduTeacher);
        eduTeacherService.updateById(eduTeacher);
        return Result.ok();

    }


}

