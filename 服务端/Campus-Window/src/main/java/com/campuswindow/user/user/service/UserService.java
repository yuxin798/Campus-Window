package com.campuswindow.user.user.service;

import com.campuswindow.user.follow.service.FollowService;
import com.campuswindow.user.user.dto.*;
import com.campuswindow.user.user.entity.User;
import com.campuswindow.user.user.repository.UserRepository;
import com.campuswindow.user.user.vo.*;
import com.campuswindow.utils.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private StringRedisTemplate redisTemplate;
    private FollowService followService;

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
        user.setAvatar("http://192.168.144.132:9000/campus-bucket/default.jpg");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setBackground("http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg");
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

    public void updateAvatar(String userId, String avatar) {
        userRepository.updateAvatarByUserId(userId, avatar);
    }

    public void updateBackground(String userId, String filePath) {
        userRepository.updateBackground(userId, filePath);
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

    public void modifyInformation(ModifyInformationDto modifyInformationDto) {
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

    @Autowired
    public void setFollowService(FollowService followService) {
        this.followService = followService;
    }

    public ModifyInformationVo findInformation(String userId) {
        return userRepository.findInformation(userId);
    }

    public String findSchool(String userId) {
        return userRepository.findSchoolByUserId(userId);
    }

    public void updateFollowersByUserId(String userId, int i) {
        userRepository.updateFollowersByUserId(userId, i);
    }

    public void updateFansByUserId(String toUserId, int i) {
        userRepository.updateFansByUserId(toUserId, i);
    }

    public void updateLovesByUserId(String userId, int i) {
        userRepository.updateLovesByUserId(userId, i);
    }

    public void updateFriendsByUserIdAndToUserId(String userId, String toUserId, int i) {
        userRepository.updateFriendsByUserIdAndToUserId(userId, i);
        userRepository.updateFriendsByUserIdAndToUserId(toUserId, i);

    }

    public void followOtherUser(String userId, String toUserId) {
        followService.followOtherUser(userId, toUserId);
        userRepository.updateFollowersByUserId(userId, 1);
        userRepository.updateFansByUserId(toUserId, 1);
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            updateFriendsByUserIdAndToUserId(userId, toUserId, 1);
        }
    }

    public void cancelFollowOtherUser(String userId, String toUserId) {
        followService.cancelFollowOtherUser(userId, toUserId);
        userRepository.updateFollowersByUserId(userId, -1);
        userRepository.updateFansByUserId(toUserId, -1);
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count != 0){
            updateFriendsByUserIdAndToUserId(userId, toUserId, -1);
        }
    }

    public UserVo findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    public List<FriendsVo> findFriendsByUserId(String userId) {
        return userRepository.findFriendsByUserId(userId);
    }

    public List<FollowersVo> findFollowersByUserId(String userId) {
        return userRepository.findFollowersByUserId(userId).stream()
                .peek(e -> e.setMutualFollow(followService.mutualFollowIsOrNot(userId, e.getUserId())))
                .collect(Collectors.toList());

    }

    public List<FansVo> findFansByUserId(String userId) {
        return userRepository.findFansByUserId(userId).stream()
                .peek(e -> e.setMutualFollow(followService.mutualFollowIsOrNot(userId, e.getUserId())))
                .collect(Collectors.toList());
    }
}
