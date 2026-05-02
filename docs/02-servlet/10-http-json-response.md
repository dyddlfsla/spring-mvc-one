
 ## HTTP 응답 데이터 - JSON

 이번에는 응답 메세지 본문(body)에 JSON 데이터를 담아 클라이언트로 전송해본다.

 HTTP 응답으로 JSON 을 반환할 때에는 content-type 을 application/json 으로 지정해주는 것이 표준이다.

 `response.setContentType("application/json");`

 앞서 서버에서 JSON 데이터를 전송 받을때 해당 JSON 데이터를 자바 객체로 재구성하여 사용한다고 했고
 이를 위해 jackson 이나 gson 같은 라이브러리를 사용한다고 하였다.

 마찬가지로 JSON 데이터를 응답 메세지에 담아 클라이언트로 보낼 때는 서버에서 먼저 자바 객체를 생성한 후
 이 객체를 JSON 으로 변환하여 전송한다.

```java

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Content-type: application/json
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    
    // 자바 객체 생성
    HelloData helloData = new HelloData();
    helloData.setUsername("hello");
    helloData.setAge(20);

    // 자바 객체를 JSON 문자열로 변환
    String result = objectMapper.writeValueAsString(helloData);
    
    response.getWriter().write(result);
    
  }
```

 🚨 참고  
 application/json은 RFC 8259 기준으로 UTF-8 인코딩을 사용한다.  
 또한 application/json 미디어 타입에는 charset 파라미터가 정의되어 있지 않다.  
 따라서 application/json;charset=utf-8처럼 charset을 붙여도 표준을 따르는 클라이언트 입장에서는 실질적인 의미가 없다.
 
 다만 많은 서버와 클라이언트는 application/json;charset=utf-8도 관용적으로 처리한다.
 그래서 대부분의 상황에서는 문제가 되지 않는다.
 
 Servlet에서 response.setCharacterEncoding("utf-8")과 getWriter()를 함께 사용하면  
 Content-Type에 charset=utf-8이 붙을 수 있다.
 
 스펙에 더 맞게 application/json만 보내고 싶다면 getOutputStream()으로
 UTF-8 바이트를 직접 출력할 수 있다. 하지만 일반적인 학습/실무 코드에서는 getWriter()를 사용해도 큰 문제는 거의 없다.
