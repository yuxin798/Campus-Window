//package com.campuswindow.utils;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.util.DigestUtils;
//
//import java.util.Arrays;
//
//public class CustomMd5PasswordEncoder implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return Arrays.toString(DigestUtils.md5Digest(rawPassword.toString().getBytes()));
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return encodedPassword.equals(Arrays.toString(DigestUtils.md5Digest(rawPassword.toString().getBytes())));
//    }
//}
