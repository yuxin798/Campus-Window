package com.campuswindow.school.controller;

import com.campuswindow.school.entity.School;
import com.campuswindow.school.service.SchoolService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school")
public class ShcoolController {

    @Autowired
    private SchoolService service;

    @GetMapping("/findAll")
    public Result findAll(){
        List<School> schools = service.findAll();
        if (schools == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(schools);
    }

    @GetMapping("/findOne")
    public Result findOne(String schoolName){
        School school = service.findOne(schoolName);
        return ResultVOUtil.success(school);
    }
}
