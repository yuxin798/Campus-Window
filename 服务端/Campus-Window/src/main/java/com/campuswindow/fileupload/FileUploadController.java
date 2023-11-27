package com.campuswindow.fileupload;

import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件上传接口")
@RestController
@RequestMapping("/user")
public class FileUploadController {
    private FileUploadService fileUploadService;

    @Operation(summary = "用户头像上传接口")
    @PostMapping("/avatar")
    public Result<String> avatar(MultipartFile file) {
        String url =  fileUploadService.save(file);
        return ResultVOUtil.success();
    }

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
