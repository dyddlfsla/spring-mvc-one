 ## HTTP 요청 데이터 - 개요

 HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.

 서버로 데이터를 전달하는 방식은, **데이터가 어디에 담겨 전송되는지에** 따라 크게 3가지 경우로 구분된다.

 ### 1) GET - 쿼리 파라미터
  - /url?username=hello&age=20
  - 메시지 바디 없이, 데이터가 URL 의 쿼리 파라미터에 담겨 전달된다.
  - 보통 검색, 필터, 페이징 등에서 많이 사용되는 방식이다.
  - 캐싱이 가능하고, 데이터가 url 에 노출된다는 특징이 있다.


 ### 2) POST - HTML form
  - html 의 `<form>` 태그를 통해 데이터를 전달하는 방식이다.
  - content-type:application/x-www-form-urlencoded
  - HTTP 요청 메시지 본문에 쿼리 파라미터 형식으로 전달한다. 
    - username=hello&age=20
  - 회원가입, 로그인, 상품 주문 등에서 활용된다.
  - `multipart/form-data` 는 파일 업로드시에 활용된다.


 ### 3) HTTP message body 
  - HTTP API 에서 주로 사용되는 방식이다.
  - 요청 메시지 본문(body)에 직접 데이터를 담아 전송한다.
  - 본문에 담기는 데이터 형식은 주로 JSON 형식을 사용한다.
    ```json
    { 
    "username": "hello",
     "age": 20
    }
    ```
  - 주로 POST, PUT, PATCH 메서드에서 사용된다.
  🚨이론적으로 GET 메서드도 메세지 본문(body)에 데이터를 담아 보낼 수 있지만, 현실적으로 사용되지 않는다.
