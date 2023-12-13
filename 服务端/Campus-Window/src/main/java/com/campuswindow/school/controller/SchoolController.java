package com.campuswindow.school.controller;

import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.school.entity.School;
import com.campuswindow.school.service.SchoolService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/school")
@Tag(name = "学校接口")
public class SchoolController {

    private SchoolService service;
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result<List<School>> findAll(){
        List<School> schools = service.findAll();
        return ResultVOUtil.success(schools);
    }

    @GetMapping("/findOne")
    @Operation(summary = "根据学校名字查询学校")
    public Result<School> findOne(String userId){
        School school = service.findOne(userId);
        return ResultVOUtil.success(school);
    }

    @PostMapping("/addSchool")
    @Operation(summary = "添加学校")
    public Result<?> addSchool(@RequestBody School school){
        service.addSchool(school);
        return ResultVOUtil.success();
    }

    @PostMapping("/uploadLogo")
    @Operation(summary = "上传校徽")
    public Result<String> uploadLogo(MultipartFile file) {
        String url = fileUploadService.save(file, MinioConstant.SCHOOLS_ROOT_PATH);
        return ResultVOUtil.success(url);
    }



    @Autowired
    public void setService(SchoolService service) {
        this.service = service;
    }

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
