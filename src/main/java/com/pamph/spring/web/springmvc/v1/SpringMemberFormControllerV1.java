package com.pamph.spring.web.springmvc.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

  @RequestMapping("/springmvc/v1/members/new-form")
  public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("new-form");
  }
}
