
 ## HTTP 요청 데이터 - HTML Form

 이번에는 HTML 의 Form 을 사용해서 클라이언트에서 서버로 데이터를 전송해보자.
 이러한 Form 을 통한 요청은 회원 가입이나 상품 주문 등에서 많이 사용된다.

 ### 1) form 요청의 특징
 form 태그는 method 어트리뷰트에 `get` 또는 `post` 를 지정할 수 있다.

```html
<form action="/search" method="">
  <input type="text" name="username" />
  <input type="text" name="age" />
  <button type="submit">전송</button>
</form>
```
 ☑️ GET 메서드를 사용하는 경우  
 form 안의 데이터들은 URL 에 쿼리 스트링으로 구성되어 전달된다.  
 `/search?username=hello&age=20`  

 ☑️ POST 메서드를 사용하는 경우  
 요청 메세지의 Content-Type 으로 `application/x-www-form-urlencoded` 가 지정되고  
 데이터들은 HTTP 요청 본문(body)에 `key=value&key2=value2` 형태로 담겨 전달된다.

 ```html
  HTTP Request Message
┌─────────────────────────────────────────────────┐
│ POST /search HTTP/1.1                           │
│ Host: localhost:8080                            │
│ Content-Type: application/x-www-form-urlencoded │
│                                                 │
│ username=aux&age=20                             │
└─────────────────────────────────────────────────┘
```
🚨 헷갈리지 말자. 쿼리 스트링이란 URL 뒤에 따라 붙은 문자열 데이터를 가리키는 것이다.  
    form 요청 본문에 담긴 데이터는 쿼리 스트링과 형태가 동일하지만 쿼리 스트링이 아니다.

 ### 2) GET, POST 메서드로 전달된 데이터 읽기

 위 내용에서 `쿼리 스트링`과 요청 본문(body)에 담긴 `key=value` 는 서로 형태만 같을 뿐 다른 개념이라고 설명했다.
 그러나 데이터가 쿼리 스트링이든 key=value 든 Servlet 의 `getParameter()` 메서드를 사용해 값을 읽을 수 있다.

 다시 말해, 클라이언트 입장에서는 URL 을 이용한 쿼리 스트링과 form 태그를 이용한 요청이 각각 다른 방식의 요청이지만
 서버에서는 이 둘을 동일한 파라미터 형태로 처리하므로 `request.getParameter()`로 구분 없이 조회할 수 있다.

 - 메세지 헤더의 content-type 은 HTTP 메시지 본문(body)의 데이터 형식이 무엇인지 지정한다.
 - URL 쿼리 스트링으로 데이터를 전달할 때는 보통 HTTP 메시지 본문(body)을 같이 사용하지 않으므로 content-type 이 의미가 없다.
 - 요청 메세지 본문(body)에 데이터를 넣어 전달할 때에는 반드시 해당 데이터가 어떤 형식인지 content-type 을 통해 지정해야 한다.


