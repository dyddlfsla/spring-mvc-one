 ## HTTP 요청 데이터 - 개요

 HTTP 요청 메시지를 통해 클라이언트에서 서버로 요청 보내는 방법에 대해 알아보자.

 HTTP 요청은 클라이언트가 데이터를 서버로 전송하는 방식에 따라 대표적으로 3가지 경우로 구분할 수 있다.

 ### 1) URL 을 통한 요청
  - 데이터를 URL 문자열에 쿼리 파라미터 형태(query string)로 담아 전달한다.
    - ex) `/search?username=hello&age=20`
  - 보통 검색, 필터, 페이징 등에서 많이 사용되는 방식이다.
  - 캐싱이 가능하고, 전송 데이터가 URL 에 노출된다는 특징이 있다.
  - HTTP 메서드 중 주로 GET 메서드를 사용한다.


 ### 2) HTML Form 요청
  - HTML 의 `<form>` 태그를 통해 요청을 보내는 방식이다.
 ```html
    <form action="/submit" method="post">
      <label>이름:</label>
      <input type="text" name="username" />
    
      <label>나이:</label>
      <input type="number" name="age" />
  
      <button type="submit">전송</button>
    </form>
 ```
  - form 요청은 기본적으로 GET, POST 두 가지 방식만 직접 지원한다. 
    - GET 메서드인 경우, form 태그의 데이터가 URL 에 붙어 전송된다.  
       `/submit?username=hello&age=20`
    - POST 메서드인 경우, form 태그의 데이터는 `application/x-www-form-urlencoded` 형식으로 인코딩되어  
      `key=value&key2=value2` 형태로 요청 본문(body)에 담겨 전송된다.
      ```html
      POST /submit HTTP/1.1
      Host: localhost:8080
      Content-Type: application/x-www-form-urlencoded
      Content-Length: 23
      
      username=hello&age=20
      ```
  - 회원가입, 로그인, 상품 주문 등에서 활용된다.
  - `multipart/form-data` 는 파일 업로드시에 활용된다.  
  🚨 그렇다고 form 요청에서 꼭 GET, POST 메서드만 사용해야 하는 것은 아니다.  
     추가 태그를 사용해 다른 HTTP 메서드를 지정하는 일종의 편법을 사용할 수 있다.
     ```html
    <form method="post">
       <input type="hidden" name="_method" value="put">
    </form>
    ```
    또한 해당 form 요청을 받는 서버에서도 별도의 필터작업이 필요하다.
    * 스프링의 경우, 내장된 HiddenHttpMethodFilter 를 사용한다.  

 ### 3) JavaScript 기반 요청 (HTTP API) 
  - HTTP API 에서 주로 사용되는 방식으로 fetch 또는 axios 를 사용해 서버로 요청을 보낸다.
  - 필요에 따라 요청 메시지 본문(body)에 데이터를 담아 전송할 수 있으며, 이때 주로 JSON 형식을 사용한다.
    ```json
    { 
      "username": "hello",
      "age": 20
    }
    ```
  - 주로 POST, PUT, PATCH 메서드와 함께 사용되며, 요청 본문(body)을 활용하는 경우가 많다.  

  🚨 GET 메서드는 보통 URL 에 데이터를 담는 쿼리 스트링 형식이 대부분인데,  
  이론적으로 GET 방식도 메세지 본문(body)에 데이터를 담아 보낼 수 있다.  
  그러나, 그것은 HTTP 명세에서 GET 요청의 본문(body)에 대한 의미가 명확히 정의되어 있지 않으며, 대부분의 서버와 프레임워크가 이를 지원하지 않기 때문에 실제로는 사용되지 않는다.
