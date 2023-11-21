package com.practicaltraining.user.controller;

import com.practicaltraining.user.entity.User;
import com.practicaltraining.user.service.UserService;
import com.practicaltraining.utils.ResultVOUtil;
import com.practicaltraining.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/sendEmail")
    public Result sendEmail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("校园之窗");
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        message.setText("验证码为：" + code);
        mailSender.send(message);
        return ResultVOUtil.success(code);
    }

    @PostMapping("/register")
    public Result register(@Valid User user, String code, BindingResult bindingResult){
        String errorMessage = null;
        if (bindingResult.hasErrors()){
            errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResultVOUtil.error(errorMessage);
        }
        User registerUser = service.register(user);
        if (registerUser != null){
            return ResultVOUtil.success(registerUser);
        }else {
            return ResultVOUtil.error("网络异常,稍后重试");
        }
    }

    @PostMapping("/login")
    public Result login(@Valid User user, BindingResult bindingResult){
        String errorMessage = null;
        if (bindingResult.hasErrors()){
            errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResultVOUtil.error(errorMessage);
        }
        User loginUser = service.login(user);
        if (loginUser == null){
            return ResultVOUtil.error("用户名或密码错误");
        }
        return ResultVOUtil.success(loginUser);
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(String userId, String password){
        if(password == null){
            return ResultVOUtil.error("新密码不能为空");
        }
        int count = service.updatePassword(userId, password);
        if (count == 1){
            return ResultVOUtil.success("修改成功");
        }else {
            return ResultVOUtil.error("修改失败");
        }
    }

    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(String userId, MultipartFile avatar){
        String fileName = avatar.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."));
        String  filePath = "D:\\" + userId + suffix;
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
