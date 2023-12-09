package com.campuswindow.user.controller;


import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.user.dto.ChatUserDto;
import com.campuswindow.user.dto.LoginDto;
import com.campuswindow.user.dto.PasswordDto;
import com.campuswindow.user.dto.RegisterDto;
import com.campuswindow.user.entity.User;
import com.campuswindow.user.service.UserService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.RedisConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/user")
@Tag(name = "用户接口")
@Slf4j
public class UserController {

    private UserService userService;
    private JavaMailSender mailSender;
    private StringRedisTemplate redisTemplate;
    private FileUploadService fileUploadService;
    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("/sendEmailCode")
    @Operation(summary = "邮箱注册发送验证码")
    public Result<String> sendEmailCode(String to){
        return sendEmail(to);
    }

    @GetMapping("/sendEmailForUpdatePassword")
    @Operation(summary = "忘记密码发送验证码")
    public Result<?> sendEmailForUpdatePassword(String to){
        User user = userService.existEmail(to);
        if (user == null){
            log.info("用户模块：用户不存在");
            throw new RuntimeException("用户不存在");
        }
        return sendEmail(to);
    }

    private Result<String> sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("校园之窗");
        Random random = new Random();
        String code = UUID.randomUUID().toString().substring(0, 6);
        message.setText("验证码为：" + code);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(RedisConstant.EMAIL_VALIDATE_CODE + uuid, code, 1, TimeUnit.MINUTES);
        System.out.println(code);
        mailSender.send(message);
        return ResultVOUtil.success(uuid);
    }
    @PostMapping(path = "/register")
    @Operation(summary = "用户注册")
    public Result<User> register(@RequestBody @Valid RegisterDto registerDto, Errors errors){
        //数据校验
        String errorMessage = null;
        if (errors.hasErrors()){
            errorMessage = errors.getFieldError().getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        //注册校验
        User registerUser = userService.register(registerDto);
        return ResultVOUtil.success(registerUser);
    }

    @PostMapping(path = "/login")
    @Operation(summary = "用户登录")
    public Result<String> login(@RequestBody @Valid LoginDto loginDto, Errors errors){
        //数据校验
        String errorMessage = null;
        if (errors.hasErrors()){
            errorMessage = errors.getFieldError().getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        //登陆校验
        User loginUser = userService.login(loginDto);
        return ResultVOUtil.success(loginUser.getUserId());
    }

    @PostMapping("/updatePassword")
    @Operation(summary = "修改密码")
    public Result<?> updatePassword(@RequestBody @Valid PasswordDto passwordDto, Errors errors){
        //数据校验
        String errorMessage = null;
        if (errors.hasErrors()){
            errorMessage = errors.getFieldError().getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        userService.updatePassword(passwordDto);
        return ResultVOUtil.success();
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传头像")
    public Result<String> uploadAvatar(String userId, MultipartFile avatar) throws IOException {
        if (avatar.isEmpty()){
            throw new RuntimeException("头像不能为空");
        }
        String filePath = fileUploadService.save(avatar, MinioConstant.USERS_ROOT_PATH);
        userService.uploadAvatar(userId, filePath);
        return ResultVOUtil.success();
    }

    @GetMapping("/findOne")
    @Operation(summary = "根据用户Id查询用户名字和头像")
    public Result<ChatUserDto> findOne(String userId){
        ChatUserDto chatUserDto = userService.findChatUserByUserId(userId);
        return ResultVOUtil.success(chatUserDto);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
