package com.pamph.spring.note;

public class $12_HttpMessageJsonRequest {

  /*
  *
  * HTTP 요청 데이터 - API 메시지 바디(JSON 포맷)
  *
  * 이번에는 HTTP API 에서 주로 사용하는 JSON 형식으로 데이터를 전달해보자.
  *
  * JSON 데이터 전송
  * ● POST:http://localhost:8080/request-body-json
  * ● content-type: application/json
  * ● message body: {"username":"hello", "age": 20}
  * ● 결과 -> messageBody = {"username":"hello", "age": 20}
  *
  * 그러나 실무에서는 JSON 형식으로 전달된 데이터를 그대로 사용하지 않고 자바 객체로 변환하여 사용한다.
  * 이때 JSON 데이터를 자바 객체로 변환하는데 jackson 이나 Gson 같은 라이브러리가 자주 사용된다.
  * 스프링부트에서는 jackson 라이브러리를 기본 제공한다.
  *
  * 데이터를 담을 HelloData 클래스를 만들고
  * 서블릿 클래스에서 ObjectMapper 필드를 추가하자. jack 라이브러리에서 제공하는 객체이다.
  *
  * private ObjectMapper objectMapper = new ObjectMapper();
  * HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
  *
  * System.out.println("helloData.getUsername() = " + helloData.getUsername());
  * System.out.println("helloData.getAge() = " + helloData.getAge());
  *
  *
  * */

}
