package com.campuswindow.user.service;

import com.campuswindow.user.dto.*;
import com.campuswindow.user.entity.User;
import com.campuswindow.user.repository.UserRepository;
import com.campuswindow.user.vo.ModifyInformationVo;
import com.campuswindow.utils.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private StringRedisTemplate redisTemplate;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    public User register(RegisterDto registerDto) {
        String code = redisTemplate.opsForValue().get(RedisConstant.EMAIL_VALIDATE_CODE + registerDto.getEmailCodeKey());
        if (code == null || !code.equalsIgnoreCase(registerDto.getEmailCode())){
            throw new RuntimeException("验证码错误");
        }
        User userByEmail = userRepository.findUserByEmail(registerDto.getEmail());
        if (userByEmail != null){
            throw new RuntimeException("邮箱已存在");
        }
        redisTemplate.delete(RedisConstant.EMAIL_VALIDATE_CODE + registerDto.getEmailCodeKey());
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        User user = new User();
        user.setUserId(id);
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setSchool(registerDto.getSchool());
        user.setUserName(registerDto.getUserName());
        //设置默认头像 TODO
        user.setAvatar("D:\\images\\users\\default.jpg");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    public User login(LoginDto loginDto) {
        User loginUser = userRepository.findUserByEmail(loginDto.getEmail());
        if (loginUser == null || !loginDto.getPassword().equals(loginUser.getPassword())){
            throw new RuntimeException("邮箱或密码错误");
        }
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
//        // authenticate方法会调用loadUserByUsername
//        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("用户名或密码错误");
//        }
//        // 校验成功，强转对象
//        LoginDto user = (LoginDto) authenticate.getPrincipal();
//        // 校验通过返回token
//        String token = JwtUtil.generateKey()
//        Map<String, Object> map = new HashMap<>();
//        map.put("token",token);
//        return Result.ok(map);

        return loginUser;
    }

    public int uploadAvatar(String userId, String avatar) {
        return userRepository.updateAvatarByUserId(userId, avatar);
    }

    public void updatePassword(PasswordDto passwordDto) {
        userRepository.updatePasswordByUserId(passwordDto.getEmail(), passwordDto.getPassword());
    }

    public User existEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public ChatUserDto findChatUserByUserId(String userId) {
        User user = userRepository.findChatUserByUserId(userId);
        return new ChatUserDto(user.getUserId(), user.getUserName(), user.getAvatar());
    }

    public User findLoginDtoByEmail(String email) {
        return userRepository.findLoginDtoByEmail(email);
    }

    public void modifyInformation(modifyInformationDto modifyInformationDto) {
        userRepository.updateInformationByUserId(modifyInformationDto.getUserId(), modifyInformationDto.getUserName(), modifyInformationDto.getGender(), modifyInformationDto.getSignature());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ModifyInformationVo findInformation(String userId) {
        return userRepository.findInformation(userId);
    }
}
