package com.linfeng.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linfeng.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linfeng.serviceedu.entity.vo.TeacherQueryReq;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author linfeng
 * @since 2021-12-11
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> selectTeacherPage(TeacherQueryReq teacherQueryReq, Page<EduTeacher> teacherPage);
}
