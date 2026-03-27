package com.pamph.spring.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*
* 1. 파라미터 전송 기능
* http://localhost:8080/request-param?username=hello&age=20
*
*
* */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("[전체 파라미터 조회] - start");

    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

    System.out.println("[전체 파라미터 조회] - end");
    System.out.println();

    System.out.println("[단일 파라미터 조회");
    String userName = request.getParameter("username");
    System.out.println("username = " + userName);
    
    String age = request.getParameter("age");
    System.out.println("age = " + age);

    System.out.println("[이름이 같은 복수 파라미터 조회]");
    // 이름이 같은 파라미터가 2개 이상 존재할때 단일 파라미터를 조회하면 가장 먼저 전송된 데이터가 조회된다.
    String[] usernames = request.getParameterValues("username");

    for (String username : usernames) {
      System.out.println("username = " + username);
    }

  }
}
