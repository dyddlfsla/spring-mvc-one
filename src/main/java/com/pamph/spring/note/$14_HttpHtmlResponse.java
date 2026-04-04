package com.pamph.spring.note;

public class $14_HttpHtmlResponse {

  /*
  *
  * HTTP 응답 데이터 - 단순 텍스트와 HTML
  *
  * HTTP 응답 메세지는 보통 다음과 같은 내용들을 담아 전달한다.
  *
  * 1. 단순 텍스트로 응답하는 경우
  *   앞서 살펴본 writer 를 이용한다. writer.println("ok");
  *
  * 2. HTML 페이지로 응답하는 경우
  *
  * 3. HTTP API - MessageBody 에 json 데이터를 담아 응답하는 경우
  *
  *
  * HTML 정보를 담아 응답하는 경우, 응답 메세지의 content-type 을 text/html 로 지정하고
  * 태그 문자열을 직접 지정해주어야 한다.
  *
  * response.setContentType("text/html");
  * response.setCharacterEncoding("utf-8");
  *
  * PrintWriter writer = response.getWriter();
  * writer.println("<html>");
  * writer.println("<body>");
  * writer.println("<div>hello</div>");
  * writer.println("<body>");
  * writer.println("</html");
  *
  *
  *
  * */

}
