package com.campuswindow.activity.commentimage.controller;

import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/comment")
@Tag(name = "评论文件处理接口")
public class CommentImageController {
    private FileUploadService fileUploadService;
    /*
     * 评论文件处理接口
     */
    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(MultipartFile file) {
        String url =  fileUploadService.save(file, MinioConstant.ACTIVITY_ROOT_PATH);
        return ResultVOUtil.success(url);
    }

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
