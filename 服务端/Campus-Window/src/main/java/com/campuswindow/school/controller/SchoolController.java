package com.campuswindow.school.controller;

import com.campuswindow.school.entity.School;
import com.campuswindow.school.service.SchoolService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school")
@Tag(name = "学校接口")
public class SchoolController {

    private SchoolService service;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<School> schools = service.findAll();
        return ResultVOUtil.success(schools);
    }

    @GetMapping("/findOne")
    @Operation(summary = "根据学校名字查询学校")
    public Result findOne(String schoolName){
        School school = service.findOne(schoolName);
        return ResultVOUtil.success(school);
    }

    @Autowired
    public void setService(SchoolService service) {
        this.service = service;
    }
}
