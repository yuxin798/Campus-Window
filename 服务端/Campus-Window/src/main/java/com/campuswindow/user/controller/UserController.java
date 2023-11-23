package com.campuswindow.user.controller;


import com.campuswindow.user.entity.User;
import com.campuswindow.user.service.UserService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping(path = "/user")
@Tag(name = "用户接口")
public class UserController {

    @Autowired
    private UserService service;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/sendEmailCode")
    @Operation(summary = "邮箱注册发送验证码")
    public Result sendEmailCode(String to){
        return sendEmail(to);
    }

    @GetMapping("/sendEmailForUpdatePassword")
    @Operation(summary = "忘记密码发送验证码")
    public Result sendEmailForUpdatePassword(String to){
        User user = service.existEmail(to);
        if (user == null){
            return ResultVOUtil.error("用户不存在");
        }
        return sendEmail(to);
    }

    private Result sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("校园之窗");
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        message.setText("验证码为：" + code);
        System.out.println(code);
        mailSender.send(message);
        return ResultVOUtil.success(code);
    }

    //AJAX
//    @GetMapping("/existEmail")
//    public Result existEmail(String email){
//        User user = service.existEmail(email);
//        if (user != null){
//            return ResultVOUtil.success("邮箱重复");
//        }
//        return ResultVOUtil.error("邮箱正确");
//    }

    @PostMapping(path = "/register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody @Valid User user, BindingResult bindingResult){
        String errorMessage = null;
        if (bindingResult.hasErrors()){
            errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResultVOUtil.error(errorMessage);
        }
        User registerUser = service.register(user);
        if (registerUser != null){
            return ResultVOUtil.success(registerUser);
        }else {
            return ResultVOUtil.error("用户名重复");
        }
    }

    @PostMapping(path = "/login")
    @Operation(summary = "用户登录")
    public Result login(@RequestBody @Validated(User.Login.class) User user, BindingResult bindingResult){
        System.out.println("前端登陆数据" + user);
        String errorMessage = null;
        if (bindingResult.hasErrors()){
            errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResultVOUtil.error(errorMessage);
        }
        System.out.println(user);
        User loginUser = service.login(user);
        System.out.println("后端登陆数据" + loginUser);
        if (loginUser == null){
            return ResultVOUtil.error("用户名或密码错误");
        }
        return ResultVOUtil.success(loginUser);
    }

    @PostMapping("/updatePassword")
    @Operation(summary = "修改密码")
    public Result updatePassword(@RequestBody User user){
        if(user.getPassword() == null){
            return ResultVOUtil.error("新密码不能为空");
        }
        int count = service.updatePassword(user.getEmail(), user.getPassword());
        if (count == 1){
            return ResultVOUtil.success("修改成功");
        }else {
            return ResultVOUtil.error("email不存在");
        }
    }

    @PostMapping("/uploadAvatar")
    @Operation(summary = "上传头像")
    public Result uploadAvatar(String userId, MultipartFile avatar){
        String fileName = avatar.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."));
        String  filePath = "D:\\images\\users\\" + userId + suffix;
        try {
            avatar.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int count = service.uploadAvatar(userId, filePath);
        if (count == 1){
            return ResultVOUtil.success("上传成功");
        }else {
            return ResultVOUtil.error("上传失败");
        }
    }
}
