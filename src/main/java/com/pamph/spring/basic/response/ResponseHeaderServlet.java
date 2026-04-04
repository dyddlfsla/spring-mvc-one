package com.pamph.spring.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // [status-line]
    response.setStatus(HttpServletResponse.SC_OK);

    //[response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");

    // Header 에 값을 직접 지정할 수도 있지만, 더 쉽게 Header 다룰 수 있는 여러 편의 메서드가 존재한다.

    // header 편의 메서드
    content(response);
    cookie(response);
    redirect(response);

    // 메시지 편의 메서드
    PrintWriter writer = response.getWriter();
    writer.write("ok");

  }

  private void content(HttpServletResponse response) {
    // content 편의 메서드
    // 직접 헤더에 값을 설정하지 않아도 setContentType(), setCharacterEncoding() 등을 사용해서 헤더 값을 설정할 수도 있다.
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
  }

  private void cookie(HttpServletResponse response) {
    // Set-Cookie: myCookie=good; Max-Age=600;
    //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600);
    response.addCookie(cookie);
  }

  private void redirect(HttpServletResponse response) throws IOException {
    // Status Code 302
    // Location: /basic/hello-form.html

//    response.setStatus(HttpServletResponse.SC_FOUND);
//    response.setHeader("Location", "/basic/hello-form.html");

    response.sendRedirect("/basic/hello-form.html");
  }
}
