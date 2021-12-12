package com.linfeng.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linfeng.serviceedu.entity.EduTeacher;
import com.linfeng.serviceedu.entity.vo.TeacherQueryReq;
import com.linfeng.serviceedu.mapper.EduTeacherMapper;
import com.linfeng.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author linfeng
 * @since 2021-12-11
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public List<EduTeacher> selectTeacherPage(TeacherQueryReq teacherQueryReq, Page<EduTeacher> pageParam) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQueryReq.getName())) {
            String name = teacherQueryReq.getName();
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(teacherQueryReq.getLevel())) {
            Integer level = teacherQueryReq.getLevel();
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(teacherQueryReq.getBegin())) {
            String begin = teacherQueryReq.getBegin();
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(teacherQueryReq.getEnd())) {
            queryWrapper.le("gmt_create", teacherQueryReq.getEnd());
        }
        IPage<EduTeacher> eduTeacherIPage = this.baseMapper.selectPage(pageParam, queryWrapper);
        return eduTeacherIPage.getRecords();

    }
}
