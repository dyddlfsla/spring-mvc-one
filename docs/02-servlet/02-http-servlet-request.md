 ## HttpServletRequest

 ### 1) HttpServletRequest 개요

 클라이언트로부터 HTTP Request Message 가 왔을 때, 이 요청을 개발자가 직접 하나씩 파싱(parsing)하고 해석한 다음,  
 요청에 따른 처리를 수행할 수도 있을 것이다. 그러나 현실적으로 모든 요청을 하나씩 분석하는 일은 매우 번거롭고 불편한 작업이다.

 이때, Spring Web 에서 지원하는 Servlet 을 사용함으로서 우리는 HTTP 요청에 담긴 정보를 쉽게 꺼내서 사용할 수 있다.
 Servlet 은 HttpServletRequest 객체를 가지고 있는데, HttpServletRequest 객체에 HTTP 요청 정보가 담겨 있다.

```html
  HTTP Request Message
 ┌─────────────────────────────────────────────────┐
 │ POST /save HTTP/1.1                             │
 │ Host: localhost:8080                            │
 │ Content-Type: application/x-www-form-urlencoded │
 │                                                 │
 │ username=aux&age=20                             │
 └─────────────────────────────────────────────────┘
```

 HttpServletRequest 는 HTTP 요청 정보외에도 다양한 추가 기능을 제공한다.

 ※ HttpServletRequest/HttpServletResponse 를 사용할 때 가장 중요한 것은   
 이 객체들이 HTTP 요청 메세지와 HTTP 응답 메세지를 편리하게 사용할 수 있도록 도와주는 객체라는 점이다.  
 따라서 이 객체들이 가진 기능에 대해 깊이 이해하기 위해서는 HTTP 스펙이 제공하는 HTTP Request Message, HTTP Response Message 자체를 이해해야 한다.

 ### 2) HttpServletRequest 기본 사용법

 ☑️ HTTP 요청 메세지에 담긴 `startLine` 정보 얻기  
   HttpServletRequest 객체의 메서드를 통해 HTTP 메서드, 프로토콜, URI 등의 정보를 얻을 수 있다.
   - getMethod(): 요청 메서드 반환.
   - getProtocol(): HTTP 요청의 프로토콜 이름을 문자열로 반환. ex) HTTP 1.1
   - getScheme(): HTTP 요청에 사용된 프로토콜 이름을 반환. ex) http or https
   - getRequestURL(): HTTP 요청에 사용된 전체 요청 URL을 문자열로 반환.
   - getRequestURI(): 요청된 리소스의 URI (Uniform Resource Identifier)를 반환.
   - getQueryString(): HTTP 요청 URL 중 쿼리 문자열 부분을 반환. 쿼리 문자열이 없다면 null 반환.
   - isSecure(): HTTP 요청이 SSL을 통해 보안 연결로 전송되었는지 여부를 나타낸다. https 이면 true 아니면 false 반환

 ☑️ HTTP 요청 메세지에 담긴 `headerLine` 정보 얻기
  - getHeaderNames(): HTTP 요청에 포함된 모든 헤더 이름을 가지고 있는 Enumeration 을 반환
```java
    Enumeration<String> headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
          String headerName = headerNames.nextElement();
          String headerValue = request.getHeader(headerName);
          System.out.println(String.format("headerName = %s%n", headerValue));
```
 ☑️ HTTP 요청 메시지에 담긴 `header` 정보 얻기
   - getServerName(): HTTP 요청을 받은 서버의 호스트명을 반환. ex) example.com
   - getServerPort(): HTTP 요청을 받은 서버의 포트 번호를 반환.
   - getLocale(): 클라이언트의 언어 및 국가 설정 정보를 반환.
   - getCookies(): HTTP 요청에서 받은 모든 쿠키(Cookie)를 반환.
   - getContentType(): HTTP 요청의 컨텐츠 유형(Content-Type)을 나타내는 문자열을 반환. ex) "text/plain", "application/json"
   - getContentLength(): HTTP 요청의 컨텐츠 길이(Content-Length), 즉 메시지 본문의 byte 수를 반환.
   - getCharacterEncoding():  HTTP 요청의 문자 인코딩을 나타내는 문자열을 반환. ex) "UTF-8", "ISO-8859-1"

 ☑️ HTTP 요청 메시지에 담긴 `기타 정보` 얻기
   - getRemoteHost(): HTTP 요청을 보낸 원격 호스트의 이름을 반환.
   - getRemoteAddr(): 클라이언트의 IP 주소를 반환. ex) 192.168.1.1
   - getRemotePort(): 클라이언트와 연결된 원격 포트 번호를 반환.
   - getLocalName(): 현재 서버의 호스트 이름을 반환.
   - getLocalAddr(): 현재 서버의 IP 주소를 반환.
   - getLocalPort(): 현재 서버가 사용하는 로컬 포트 번호를 반환

