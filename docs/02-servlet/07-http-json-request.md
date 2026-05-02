
 ## HTTP 요청 데이터 - API 메시지 바디(JSON 포맷)

 이번에는 HTTP API 에서 주로 사용하는 JSON 데이터 전송에 대해 알아보자.

 ☑️ 다음과 같이 요청 메세지 본문(body) 에 json 데이터가 담겨있다.

```html
 ┌───────────────────────────────────────┐
 │ POST /request-body-json HTTP/1.1      │
 │ Host: localhost:8080                  │
 │ Content-Type: application/json        │
 │                                       │
 │ {                                     │
 │   "username":"hello",                 │
 │   "age": 20                           │
 │ }                                     │
 └───────────────────────────────────────┘
```

 지금은 설명을 위해 요청 메세지 본문에 단순 텍스트가 담기는 경우와 JSON 데이터가 담기는 경우를 구분하고 있지만,    
 실제로는 요청 본문(body)은 원래 byte 데이터로 전달되며, JSON 또한 텍스트 기반 포맷이기 때문에 `getInputStream()` 또는 `getReader()`를 통해 읽을 수 있다.  

```java
 @WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
 public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    System.out.println("messageBody = " + messageBody);
    
    /*
     * 이때 JSON 데이터를 자바 객체로 변환하기 위해서 jackson 이나 Gson 같은 라이브러리가 자주 사용된다.
     * 스프링부트에서는 jackson 라이브러리를 기본 제공한다.
     * 
     * 데이터를 담을 HelloData 클래스를 만들고
     * 서블릿 클래스에서 ObjectMapper 필드를 추가하자. jack 라이브러리에서 제공하는 객체이다.
     * 
     * */
     
    // ObjectMapper는 JSON 문자열을 파싱하여 지정한 클래스(HelloData) 형태의 객체로 변환한다.
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    
    System.out.println("helloData.getUsername() = " + helloData.getUsername());
    System.out.println("helloData.getAge() = " + helloData.getAge());

    response.getWriter().write("ok");

    }
 }
```
 
