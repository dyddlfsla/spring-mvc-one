package com.pamph.spring.web.frontcontroller.v5;

import com.pamph.spring.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

  boolean supports(Object handler);

  ModelView handle(HttpServletRequest request , HttpServletResponse response, Object handler);

}
