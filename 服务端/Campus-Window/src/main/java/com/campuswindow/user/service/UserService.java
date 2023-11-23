package com.campuswindow.user.service;

import com.campuswindow.user.entity.User;
import com.campuswindow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public User register(User user) {
        User userByEmail = repository.findUserByEmail(user.getEmail());
        if (userByEmail != null){
            return null;
        }
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        user.setAvatar("D:\\images\\users\\default.jpg");
        user.setCreate_time(new Timestamp(System.currentTimeMillis()));
        return repository.save(user);
    }

    public User login(User user) {
        User loginUser = repository.findUserByEmail(user.getEmail());
        if (!user.getPassword().equals(loginUser.getPassword())){
            return null;
        }
        return loginUser;
    }

    public int uploadAvatar(String userId, String avatar) {
        return repository.updateAvatarByUserId(userId, avatar);
    }

    public int updatePassword(String email, String password) {
        return repository.updatePasswordByEmail(email, password);
    }

    //配合前面的AJAX
    public User existEmail(String email) {
        return repository.findUserByEmail(email);
    }
}
