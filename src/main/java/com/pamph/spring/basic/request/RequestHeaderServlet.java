package com.pamph.spring.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    printStartLine(request);
    printHeaders(request);
    printHeaderUtils(request);
    printEtc(request);
  }

  private static void printEtc(HttpServletRequest request) {
    System.out.println("-----------기타정보-조회-START--------------");

    System.out.println("[Remote] 조회");
    System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
    System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
    System.out.println("request.getRemotePort() = " + request.getRemotePort());
    System.out.println();

    System.out.println("[local] 조회");
    System.out.println("request.getLocalName() = " + request.getLocalName());
    System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
    System.out.println("request.getLocalPort() = " + request.getLocalPort());
    System.out.println("-----------기타정보-조회-END--------------\n");
  }

  private static void printHeaderUtils(HttpServletRequest request) {
    System.out.println("-----------Header-편의 조회-START--------------");
    System.out.println("request.getServerName() = " + request.getServerName());
    System.out.println("request.getServerPort() = " + request.getServerPort());
    System.out.println();

    System.out.println("[Accept-Language 조회]");
    Iterable<Locale> iterable = () -> request.getLocales().asIterator();
    StreamSupport.stream(iterable.spliterator(), false)
            .forEach(locale -> System.out.println("locale:" + locale));
    System.out.println("request.getLocale() = " + request.getLocale());
    System.out.println();

    System.out.println("[Cookie 조회]");
    if (request.getCookies() != null) {
      Stream.of(request.getCookies())
          .forEach(cookie -> System.out.println(cookie.getName() + "=" + cookie.getValue()));
    }
    System.out.println();

    System.out.println("[Content 조회]");
    System.out.println("request.getContentType() = " + request.getContentType());
    System.out.println("request.getContentLength() = " + request.getContentLength());
    System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
    System.out.println();

    System.out.println("-----------Header-편의 조회-END--------------\n");
  }

  private static void printHeaders(HttpServletRequest request) {
    System.out.println("-----------Header-LINE-START--------------");
    Iterable<String> iterable = () -> request.getHeaderNames().asIterator();
    StreamSupport.stream(iterable.spliterator(), false)
        .forEach(header -> System.out.println(String.format("headerName = %s, headerValue = %s", header, request.getHeader(header))));
    System.out.println("-----------Header-LINE-END--------------\n");
  }

  private static void printStartLine(HttpServletRequest request) {

    System.out.println("-----------REQUEST-LINE-START--------------");
    System.out.println("request.getMethod() = " + request.getMethod());
    System.out.println("request.getProtocol() = " + request.getProtocol());
    System.out.println("request.getScheme() = " + request.getScheme());
    System.out.println("request.getRequestURL() = " + request.getRequestURL());
    System.out.println("request.getRequestURI() = " + request.getRequestURI());
    System.out.println("request.getQueryString() = " + request.getQueryString());
    System.out.println("request.isSecure() = " + request.isSecure());
    System.out.println("-----------REQUEST-LINE-END--------------\n");

  }

}
