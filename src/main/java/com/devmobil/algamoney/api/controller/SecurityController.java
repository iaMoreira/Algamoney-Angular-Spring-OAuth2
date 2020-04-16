package com.devmobil.algamoney.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.algamoney.api.model.User;

@RestController
public class SecurityController {

	// devolve o usuário autenticado;
    @RequestMapping(value = "/user-auth", method = RequestMethod.GET)
    public User user() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
    
    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
    	Cookie cookie = new Cookie("refreshToken", null);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(false); // TODO Em produção será true
    	cookie.setPath(req.getContextPath() + "/oauth/token");
    	cookie.setMaxAge(0);
    	
    	resp.addCookie(cookie);
    	resp.setStatus(HttpStatus.NO_CONTENT.value());
    	
    }
    
    
}
