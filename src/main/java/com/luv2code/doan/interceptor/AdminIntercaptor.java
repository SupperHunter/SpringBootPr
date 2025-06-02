package com.luv2code.doan.interceptor;

import com.luv2code.doan.principal.UserPrincipal;
import com.luv2code.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminIntercaptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Loggggggggggggg " + principal);

        if (principal instanceof UserPrincipal) {
              Integer userId = ((UserPrincipal) principal).getId();

              System.out.println("userid " + userId);
        }
        else {
            System.out.println("redirect to login");

            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

}
