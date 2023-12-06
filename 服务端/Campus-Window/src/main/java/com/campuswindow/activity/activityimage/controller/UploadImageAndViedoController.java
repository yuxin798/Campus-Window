package com.campuswindow.activity.activityimage.controller;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/activity")
@Tag(name = "帖子文件处理接口")
public class UploadImageAndViedoController {

    private FileUploadService fileUploadService;
    private ActivityImageService activityImageService;

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
    @Autowired
    public void setActivityService(ActivityImageService activityImageService) {
        this.activityImageService = activityImageService;
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(String userId, MultipartFile file) {
        String url =  fileUploadService.save(file, MinioConstant.ACTIVITY_ROOT_PATH);
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        String imageId = UUID.randomUUID().toString().replace("-", "");
        ActivityImage activityImage = new ActivityImage(imageId, null, userId, url, -1);;
        if (suffix.equals("mp4")){
            activityImage.setType(1);
        }else {
            activityImage.setType(0);
        }
        activityImageService.saveActivityImage(activityImage);
        return ResultVOUtil.success(url);
    }

    @GetMapping("/deleteActivityImage")
    @Operation(summary = "删除文件")
    public Result deleteAvatar(String userId) {
        activityImageService.deleteActivityImageByUserId(userId);
        return ResultVOUtil.success();
    }
}
