package com.pamph.spring.note;

public class $10_FormDataRequest {

  /*
  *
  * HTTP 요청 데이터 - POST HTML form
  *
  * 이번에는 HTML 의 Form 을 사용해서 클라이언트에서 서버로 데이터를 전송해보자.
  * 이러한 Form 을 통한 요청은 회원 가입이나 상품 주문 등에서 많이 사용된다.
  *
  * ◆ Form 요청의 특징
  * 1) content-type: application/x-www-form-urlencoded
  * 2) 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전달한다. ex) username=hello&age=20
  *
  * <form action="/request-param" method="post">
  *    <input type="text" name="username" />
  *    <input type="text" name="age" />
  *  <button type="submit">전송</button>
  * </form>
  *
  *
  * HTML Form 태그에서 메서드를 method="pose" 로 지정하고 요청을 보내면 웹 브라우저는 다음 형식으로
  * HTTP 메세지를 만든다. (개발자 모드로 확인해보자)
  *
  * ▶ 요청 URL: http://localhost:8080/request-param
  * ▶ content-type: application/x-www-form-urlencoded
  * ▶ message body: username=hello&age=20
  *
  * application/x-www-form-urlencoded 형식은 앞서 GET 쿼리 파라미터에서 살펴본 형식과 같다.
  * 따라서 Form 요청으로 전송된 데이터를 조회할때 마찬가지로 쿼리 파라미터 조회 메서드를 사용할 수 있다.
  *
  * 클라이언트 입장에서는 url 을 이용한 쿼리 파리미터와 Form 태그를 이용한 요청으로 각각 다른 방식의 요청이지만
  * 서버에서는 이 둘을 동일한 형식으로 취급하므로 request.getParameter() 메서드로 구분없이 조회할 수 있다.
  *
  * 정리하면 request.getParameter() 는 GET URL 쿼리 파라미터 형식도 지원하고, HTML Form 형식도 둘 다 지원한다.
  *
  *
  *
  *
  *
  * */

}
