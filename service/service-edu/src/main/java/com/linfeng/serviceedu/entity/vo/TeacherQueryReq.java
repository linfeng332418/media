package com.linfeng.serviceedu.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Api(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class TeacherQueryReq implements Serializable {

    @ApiModelProperty(value = "查询当前页",required = true)
    private long page;

    @ApiModelProperty(value = "查询该页的记录数",required = true)
    private long limit;

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
