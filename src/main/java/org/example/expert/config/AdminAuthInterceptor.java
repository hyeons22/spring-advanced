package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.domain.auth.exception.AuthException;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserRole userRole = UserRole.of((String)request.getAttribute("userId"));
        log.info(String.valueOf(userRole));

        if(UserRole.ADMIN != userRole){
            log.warn("경고 : Admin이 아닙니다.");
            throw new AuthException("Admin이 아닙니다.");
        }

        LocalDateTime time = LocalDateTime.now();
        String nowTime = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("요청시간 = {}", nowTime);
        log.info("URL = {}" , request.getRequestURI());

        return true;
    }
}
