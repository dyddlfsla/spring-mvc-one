package com.pamph.spring.note;

public class $8_HttpRequestData {

  /*
  *
  * Ⅰ. HTTP 요청 데이터 - 개요
  *
  * HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.
  *
  *
  * 서버로 데이터를 전달할 때는 주로 다음과 같은 3가지 방법이 사용된다.
  *
  * 1) GET - 쿼리 파라미터
  *  ● /url?username=hello&age=20
  *  ● 메시지 바디 없이, URL 의 쿼리 파라미터에 데이터를 포함해서 전달한다.
  *  ● 보통 검색, 필터, 페이징 등에서 많이 사용한다.
  *
  *
  * 2) POST - HTML form
  *  ● content-type:application/x-www-form-urlencoded
  *  ● 메시지 바디에 쿼리 파라미터 형식으로 전달한다. username=hello&age=20
  *  ● 회원가입, 상품 주문 등에서 활용된다.
  *
  *
  * 3) HTTP message body 에 데이터를 직접 담아서 요청
  *  ● HTTP API 에서 주로 사용, JSON, XML, TEXT
  *  ● 데이터 형식은 주로 JSON 형식을 사용한다.
  *  ● POST, PUT, PATCH
  *
  *
  *
  *
  * */

}
