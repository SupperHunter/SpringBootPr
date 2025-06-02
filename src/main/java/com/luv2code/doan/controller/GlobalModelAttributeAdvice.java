package com.luv2code.doan.controller;

import com.luv2code.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class GlobalModelAttributeAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String email = auth.getName();

            model.addAttribute("canManageProducts", userService.hasRole(email, "manage_products"));
            model.addAttribute("canManageCategories", userService.hasRole(email, "manage_categories"));
            model.addAttribute("canManageUsers", userService.hasRole(email, "manage_users"));
            model.addAttribute("canManageOrders", userService.hasRole(email, "manage_orders"));
            model.addAttribute("canManageBrands", userService.hasRole(email, "manage_brands"));
            model.addAttribute("canManagePosters", userService.hasRole(email, "manage_banners"));
        }
    }
}
