package com.pamph.spring.note;

public class $9_QueryParameterRequest {

  /*
  *
  * HTTP 요청 데이터 - GET 쿼리 파라미터
  *
  * 다음 데이터를 클라이언트에서 서버로 전송해보자.
  *
  * 전달 데이터
  * ◆ username=hello
  * ◆ age=20
  *
  * HTTP 요청 메시지 바디 없이, URL 의 쿼리 파라미터를 사용해서 데이터를 전달한다.
  * 이러한 방식은 검색, 필터, 페이징 등에서 많이 사용된다.
  *
  * 쿼리 파라미터는 URL 에 다음과 같이 ? 문자를 붙임으로써 보낼 수 있다. 추가되는 파라미터는 & 로 구분한다.
  *
  * http://localhost:8080/request-param?username=hello&age=20
  *
  * 그리고 이렇게 전송된 데이터는 서버에서 HttpServletRequest 가 제공하는 메서드를 통해 편리하게 조회할 수 있다.
  *
  * ▶ String username = request.getParameter("username"); // 단일 파라미터 조회
  *
  * ▶ Enumeration<String> parameterNames = request.getParameterNames(); // 전송된 파라미터의 이름들을 전체 조회
  *
  * ▶ Map<String, String[]> parameterMap = request.getParameterMap(); // 파라미터들을 Map 에 담아 조회
  *
  * ▶ String[] usernames = request.getParameterValues("username"); // 같은 이름을 가진 파라미터가 여럿 있다면 복수 조회
  *
  * ※ 복수 파라미터에서 단일 파라미터 조회
  * username=hello&age=20&username=kim 과 같이 파라미터 이름은 username 으로 동일한데 값이 2개 이상이면 어떻게 될까?
  * request.getParameter() 는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을 때 사용해야 한다.
  * 지금처럼 같은 이름의 파라미터가 2개 이상인 경우에는 request.getParameterValues() 를 사용해야 한다.
  * 또한 이런 상황에서 getParameter() 를 사용하면 request.getParameterValues() 로 생성되는 배열의 첫번째 값을 반환한다.
  *
  *
  *
  *
  *
  *
  * */

}
