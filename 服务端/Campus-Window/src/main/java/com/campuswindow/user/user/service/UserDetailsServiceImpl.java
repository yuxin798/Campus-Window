//package com.campuswindow.user.service;
//
//import com.campuswindow.user.dto.LoginDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService service;
//    @Override
//    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
//        LoginDto loginDto = service.findOneByEmail(email);
//        if (loginDto == null)
//            throw new RuntimeException("用户不存在");
//        return new org.springframework.security.core.userdetails.User(loginDto.getEmail(),loginDto.getPassword(), Collections.emptyList());
//    }
//}
