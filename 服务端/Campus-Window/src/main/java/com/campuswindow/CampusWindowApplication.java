package com.campuswindow;

import com.campuswindow.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MinioProperties.class)
public class  CampusWindowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusWindowApplication.class, args);
    }
//    /**
//     * 将加密工具类加入IOC容器中,便于加密
//     * */
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
}
