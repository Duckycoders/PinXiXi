package com.example.mini_amazon.config;

import com.example.mini_amazon.model.User;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = UserStorage.getCurrentUser();
        if (currentUser == null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}