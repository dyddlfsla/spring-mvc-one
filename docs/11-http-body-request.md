
 ## HTTP 요청 데이터 - API 메시지 바디 

 쿼리 파라미터도 아니고 Form 태그를 통한 방식도 아닌 직접 요청 메시지 본문(body)에 데이터를 담아 전송하는 법을 알아보자.

 ☑️ HTTP 요청 메세지 본문(body) 에 데이터를 직접 담아서 요청한다.  
 ☑️ HTTP API 통신에서 주로 사용되는 방식인데 메시지 본문에 JSON, XML, TEXT 등의 데이터를 담아 보낸다.  
 ☑️ 실무에서는 데이터를 JSON 형식으로 보내는 것이 거의 표준이 되었다.
 ☑️ POST, PUT, PATCH 등에 주로 사용된다.

 Postman 을 활용해 서버로 단순 텍스트 데이터를 보내고 서버에서 확인해보자.

 JSON 형식의 요청 본문(body)은 request.getParameter()로는 조회할 수 없고,  
 반드시 `InputStream`을 통해 직접 읽어야 한다.

```java
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    // inputStream 은 요청 본문 데이터르 byte 단위로 제공한다. 
    // byte 코드를 우리가 읽을 수 있는 문자(String)으로 보려면 문자인코딩을 지정해야 한다.

    System.out.println("messageBody = " + messageBody);
    
    // 자바 객체로 맵핑
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

    System.out.println("helloData.getUsername() = " + helloData.getUsername());
    System.out.println("helloData.getAge() = " + helloData.getAge());

    response.getWriter().write("ok");

  }
}
```
