package com.pamph.spring.note;

public class $15_HttpJsonResponse {

  /*
  *
  * HTTP 응답 데이터 - JSON
  *
  * 이번에는 응답 데이터에 JSON 데이터를 담아 클라이언트로 전송해보자.
  *
  * HTTP 응답으로 JSON 을 반환할 때에는 content-type 을 application/json 으로 지정해주어야 한다.
  *
  * response.setContentType("application/json");
  *
  * 앞서 우리는 클라이언트에서 전송된 JSON 데이터를 서버에서 전송 받을때 해당 json 데이터를 자바 객체로 변환한다고 하였고
  * 이를 위해 jackson 이나 gson 같은 라이브러리를 사용한다고 하였다.
  *
  * 마찬가지로 클라이언트로 json 응답을 보낼때에는 그 반대 과정으로, 서버에서 먼저 자바 객체를 생성한 후
  * 이 객체를 json 으로 변환하여 응답 메세지에 담아 클라이언트에 반환한다.
  *
  * HelloData helloData = new HelloData();
  * helloData.setUsername("hello");
  * helloData.setAge(20);
  *
  * String result = objectMapper.writeValueAsString(helloData);
  * response.getWriter().write(result);
  *
  * ※ 참고
  * application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다. 그래서 스펙에서 charset=utf-8 과 같은
  * 추가 파라미터를 지원하지 않는다. 따라서 application/json;charset=utf-8 이라고 전달하는 것은 의미없는 파라미터를 추가한 것이다.
  * 그러나 response.getWriter() 메서드를 사용하면 추가 파라미터를 자동으로 추가해버린다.
  * 이런 경우 response.getOutputStream() 으로 출력하면 해당 문제를 해결할 수 있다.
  *
  *
  * */

}
