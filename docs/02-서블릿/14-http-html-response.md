
 ## HTTP 응답 데이터 - 단순 텍스트와 HTML

 HTTP 응답 메세지를 통해 전달하는 데이터는 보통 대표적으로 3가지의 형태이다.

 ### 1) 단순 텍스트로 응답하는 경우

 앞서 배운 것처럼, `getWriter()`를 활용한다.

```java

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    PrintWriter writer = response.getWriter();
    writer.write("ok");
    // 문자열 "ok" 전송
  }
```

 ### 2) HTML 페이지로 응답하는 경우

 HTML 태그 역시 브라우저를 통해 해석되는 문자열 데이터이므로 HTML 문서를 전송하기 위해,  
 `getWriter()`를 사용할 수 있다.

```java

 @Override
 protected void service(HttpServletRequest req, HttpServletResponse response) throws IOException {
   
   // HTML 정보를 담아 응답하는 경우, 응답 메세지의 content-type 을 text/html 로 지정하고
   // 태그 문자열을 직접 지정해주어야 한다.
   response.setContentType("text/html");
   response.setCharacterEncoding("utf-8");

    PrintWriter writer = response.getWriter();
    writer.println("<html>");
    writer.println("<body>");
    writer.println("<div>hello</div>");
    writer.println("</body>");
    writer.println("</html>");
    
 }

```

 ### 3) HTTP API - 응답 메세지 본문에 JSON 데이터를 담는 경우

 
