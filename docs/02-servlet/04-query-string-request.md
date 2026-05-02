
## HTTP 요청 데이터 - 쿼리 스트링(query string)

 다음 데이터를 클라이언트에서 서버로 전송해보자.

 ☑️ 전달 데이터
 - username=hello
 - age=20
 
 쿼리 스트링(query string)이란 url 문자열에 ? 기호와 같이 연결되는 데이터를 말하는데,    
 이러한 데이터 전달 방식은 주로 GET 메서드를 사용하며 URL 문자열에 데이터를 담아 서버에 전송된다.  
 검색, 필터, 페이징 등에서 많이 사용된다.

 쿼리 파라미터는 URL 문자열 뒤에 다음과 같이 `?` 문자를 붙임으로써 보낼 수 있다.  
 추가되는 파라미터는 `&` 로 구분한다.

 http://localhost:8080/request-param?username=hello&age=20

 그리고 이렇게 전송된 데이터는 서버 측에서 HttpServletRequest 가 제공하는 메서드를 통해 편리하게 조회할 수 있다.

```java
  String username = request.getParameter("username"); // 단일 파라미터 조회

  Enumeration<String> parameterNames = request.getParameterNames(); // 전송된 파라미터의 이름들을 전체 조회

  Map<String, String[]> parameterMap = request.getParameterMap(); // 파라미터들을 Map 에 담아 조회

  String[] usernames = request.getParameterValues("username"); // 같은 이름을 가진 파라미터가 여럿 있다면 복수 조회
```
 🚨 복수 파라미터에서 단일 파라미터 조회  
 `username=hello&age=20&username=kim` 과 같이 동일한 이름의 파라미터가 여러 개 전달될 수 있다.  
 이때 `request.getParameter()` 는 단일 파라미터 이름에 대해 하나의 값만 반환하며, 동일한 이름의 파라미터가 여러 개 존재할 경우 첫 번째 값을 반환한다.  
 여러 값을 모두 조회해야 하는 경우에는`request.getParameterValues()` 를 사용하여 배열 형태로 값을 조회해야 한다.


