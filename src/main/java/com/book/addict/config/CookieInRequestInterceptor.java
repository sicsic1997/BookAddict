package com.book.addict.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CookieInRequestInterceptor extends HandlerInterceptorAdapter {

    private static final String COOKIES_NOT_NEEDED_ON = "/user/";
    private static final String COOKIE_NAME_TO_BE_PRESENT = "isLogged";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*if(request.getRequestURI().contains(COOKIES_NOT_NEEDED_ON)) {
            return super.preHandle(request, response, handler);
        } else {
            if(Arrays
                    .stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(COOKIE_NAME_TO_BE_PRESENT))
                    .collect(Collectors.toList()).size() == 0) {
                return false;
            }
        }*/
        return super.preHandle(request, response, handler);
    }
}
