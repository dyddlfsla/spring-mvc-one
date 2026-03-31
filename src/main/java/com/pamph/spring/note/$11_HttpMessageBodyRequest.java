package com.pamph.spring.note;

public class $11_HttpMessageBodyRequest {

  /*
  *
  * HTTP 요청 데이터 - API 메시지 바디(단순 텍스트)
  *
  * 쿼리 파라미터도 아니고 Form 태그를 통한 방식도 아닌
  * 직접 요청 메시지에 데이터를 담아 보내는 방법을 알아보자.
  *
  * ▶ HTTP message body 에 데이터를 직접 담아서 요청
  * ▶ HTTP API 에서 주로 사용하는 방식인데 메시지 바디에 JSON, XML, TEXT 등의 데이터를 담아 보낸다.
  * ▶ 데이터 형식은 사실상 JSON 형식으로 보내는 것이 표준이 되었다.
  * ▶ POST, PUT, PATCH
  *
  * Postman 을 활용해 서버로 단순 텍스트 데이터를 보내고 서버에서 확인해보자.
  *
  * 서버에서는 이렇게 전송된 데이터는 InputStream 을 사용해서 직접 읽을 수 있다.
  *
  * ServletInputStream inputStream = req.getInputStream();
  * String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
  *
  * ※ 참고
  * inputStream 은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 문자(String)으로 보려면 문자표(Charset)을 지정해주어야 한다.
  * 여기서는 UTF_8 Charset 을 지정해주었다.
  *
  * System.out.println("messageBody = " + messageBody);
  * resp.getWriter().write("great!");
  *
  * Postman 을 통해 테스트 해보자.
  *
  * 메시지 바디에 문자를 담아 전송.
  *
  * ● POST http://localhost:8080/request-body-string
  * ● content-type:text/plain
  * ● messageBody:hello
  * ● 결과-> messageBody = hello
  *
  *
  *
  * */

}
