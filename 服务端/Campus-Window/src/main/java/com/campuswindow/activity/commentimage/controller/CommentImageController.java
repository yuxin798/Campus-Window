package com.campuswindow.activity.commentimage.controller;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import com.campuswindow.activity.commentimage.service.CommentImageService;
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
@RequestMapping("/comment")
@Tag(name = "评论文件处理接口")
public class CommentImageController {
    private FileUploadService fileUploadService;
    private CommentImageService commentImageService;

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
    @Autowired
    public void setCommentImageService(CommentImageService commentImageService) {
        this.commentImageService = commentImageService;
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(String userId, MultipartFile file) {
        String url =  fileUploadService.save(file, MinioConstant.ACTIVITY_ROOT_PATH);
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        String imageId = UUID.randomUUID().toString().replace("-", "");
        CommentImage commentImage = new CommentImage(imageId, null, userId, url, -1);;
        if (suffix.equals("mp4")){
            commentImage.setType(1);
        }else {
            commentImage.setType(0);
        }
        commentImageService.saveCommentImage(commentImage);
        return ResultVOUtil.success(url);
    }

    @GetMapping("/deleteCommentImage")
    @Operation(summary = "删除文件")
    public Result deleteCommentImage(String userId) {
        commentImageService.deleteCommentImageByUserId(userId);
        return ResultVOUtil.success();
    }

}
