package com.leminhtien.controller;

import com.leminhtien.service.ICartService;
import com.leminhtien.service.IOrderService;
import com.leminhtien.service.IUserService;
import com.leminhtien.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptorHandle implements HandlerInterceptor {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderService orderService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (SecurityUtils.isAuthenticated()){
            Long userId = SecurityUtils.getPrincipal().getId();
            httpServletRequest.setAttribute("userCurrent",userService.findCurrentUser());
            httpServletRequest.setAttribute("count",cartService.countByUser());
            httpServletRequest.setAttribute("countOrder",orderService.countAllByUserIdAndStatusBetween(userId,(byte) 1,(byte) 4));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
