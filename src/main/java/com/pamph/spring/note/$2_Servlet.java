package com.pamph.spring.note;

public class $2_Servlet {
  /*
 self-taught
  *
  * Ⅰ. 서블릿 - Servlet
  *
  * 1. 서블릿 없이 서버 구현하기
  *
  * 다음과 같이 HTML Form 을 이용해 회원을 저장하는 HTTP 요청을 보낸다고 해보자.
  *
  *  member-new.html
  *  UserName: Kim  |  age: 20  submit
  *  ┌────────────────────────────────────────┐
  *  │<form action="/save" method="post">     │
  *  │  <input type="text" name="username"/>  │
  *  │  <input type="text" name="age"/>       │
  *  │  <button type="submit">submit</button> │
  *  │</form>                                 │
  *  └────────────────────────────────────────┘
  *                   ↓
  *   웹 브라우저가 생성한 HTTP Request Message
  *  ┌────────────────────────────────────────────────┐
  *  │ POST /save HTTP/1.1                            │
  *  │ Host: www.google.com                           │
  *  │ Content-Type:application/x-www.form-urlencoded │
  *  │                                                │
  *  │ username=Kim&age=20                            │
  *  └────────────────────────────────────────────────┘
  *                   ┌─────────┐
  *                   │  POST   │
  *  ┌────────────┐   └─────────┘                                      ┌────────────┐
  *  │  Client    │ ------------------------------------------------>  │  Server    │
  *  └────────────┘                                                    └────────────┘
  *
  * 그리고, 이 요청에 대해 응답하는 서버를 구현한다고 생각해보자.
  * 개발자는 이 요청에 응답하기 위해 다음과 같은 것들을 코드로 작성해야 한다.
  *
  *  1) 서버 TCP/IP 연결 대기, 소켓 연결
  *  2) HTTP 요청 메시지를 parsing 해서 내용 읽기
  *  3) POST 방식, /save URL 인지 확인
  *  4) Content-Type 확인
  *  5) HTTP 메세지의 본문 데이터 확인
  *     - username, age 데이터 parsing
  *  6) 저장 프로세스 실행
  *  7) 비즈니스 로직 실행  <----------------- 의미있는 비즈니스 로직
  *     - 데이터베이스에 저장 요청
  *  8) HTTP 응답 메시지 생성 시작
  *  9) Header 작성
  *  10) 응답 메시지 본문에 HTML 데이터 저장
  *  11) TCP/IP 응답 전달, 소켓 종료
  *          ↓
  *  HTTP Response Message
  * ┌────────────────────────────────────────┐
  * │ HTTP/1.1 200 OK                        │
  * │ Content-Type:text/html;charset=UTF-8   │
  * │ Content-Length: 3423                   │
  * │ <html>                                 │
  * │   <body>...</body>                     │
  * │ </html>                                │
  * │ ...                                    │
  * └────────────────────────────────────────┘
  *
  * 사실 위 과정 중에서 가장 중요한 단계는 회원 정보를 직접 가공하는 비즈니스 로직 실행 단계이다.
  * 그 전과 후의 과정은 HTTP 메세지를 읽고 생성하기 위해 해야 하는 반복 작업이라고 볼 수 있다.
  *
  * 그래서 7번을 제외한 나머지 과정의 반복 작업을 해결하기 위해서 Servlet 이 도입되었다.
  * 서블릿을 사용하면 7번을 제외한 1 ~ 10번까지의 단계를 모두 자동화시켜 처리할 수 있다.
  *
  * 2. Servlet 이란?
  *
  * @WebServlet(name = "helloServlet", urlPatterns = "/hello")
  * public class HelloServlet extends HttpServlet {
  *
  *   @Override
  *   protected void service(HttpServletRequest request, HttpServletResponse response) {
  *     //애플리케이션 로직
  *   }
  * }
  *
  * 서블릿은 HTTP 요청/응답 정보를 가진 객체인데, 서블릿의 특징을 살펴보면 다음과 같다.
  *
  * · urlPattern(/hello) 의 URL 이 호출되면 서블릿 코드가 실행된다.
  * · HTTP 요청 정보를 편리하게 읽을 수 있는 HttpServletRequest 객체를 제공한다.
  * · HTTP 응답 정보를 편리하게 제공할 수 있는 HttpServletResponse 객체를 제공한다.
  * · 개발자는 서블릿을 이용해 매우 편리하게 HTTP 스펙을 이용할 수 있다.
  *
  * 1) 서블릿의 동작 과정
  *                                           Web Application Server
  *                                         ┌──────────────────────────────────────────────────────────────┐
  *                                         │                                      Servlet Container       │
  *  ┌─────────────┐                        │     ┌───────────────┐         ┌────────────────────────────┐ │
  *  │ Web browser │                        │     │               │         │                            │ │
  *  │             │  localhost:8080/hello  │     │  [request]  → │ ------> │   run  (request, response) │ │
  *  │             │  --------------------> │     │  [response]   │         │       ↘                    │ │
  *  │             │                        │     │               │         │         helloServlet       │ │
  *  │             │                        │     │               │         │       ↙                    │ │
  *  │             │   "Hello, world!"      │     │               │         │   close                    │ │
  *  │             │  <-------------------- │     │  [response]   │ <------ │  ←                         │ │
  *  │             │                        │     │               │         │                            │ │
  *  └─────────────┘                        │     └───────────────┘         └────────────────────────────┘ │
  *                                         └──────────────────────────────────────────────────────────────┘
  *  · 브라우저로부터 HTTP 요청이 들어오면 WAS 는 Request, Response 객체를 새로 만들어서 서블릿 객체를 호출한다.
  *  · 개발자는 HttpServletRequest 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용.
  *  · 개발자는 HttpServletResponse 객체에 HTTP 응답 정보 입력.
  *  · WAS 는 HttpServletResponse 객체에 담긴 정보로 HTTP 응답 메세지 작성한 뒤 브라우저에게 전송.
  *
  * 2) 서블릿 컨테이너
  * 서블릿 컨테이너는 WAS 로부터 Request, Response 객체를 전달 받아 서블릿 객체를 생성한다.
  * 즉, 서블릿 컨테이너는 서블릿 객체의 생성, 초기화, 호출, 소멸 등의 생명주기를 담당하는 컨테이너인 것이다.
  *
  * · 톰캣처럼 Servlet 을 지원하는 WAS 를 서블릿 컨테이너라고 한다.
  * · 서블릿 컨테이너는 서블릿 객체의 생성, 초기화, 호출, 종료 등의 생명주기를 관리한다.
  * · 서블릿 객체는 `싱글톤`으로 관리된다.
  *   - 클라이언트의 요청이 올때 마다 계속 서블릿 객체를 생성하는 것은 비효율적이다.
  *   - 최초 로딩 시점에 서블릿 객체를 미리 만들어 두고 계속 재활용한다.
  *   - 모든 클라이언트 요청은 동일한 서블릿 객체 인스턴스에 접근한다.
  *   - 서블릿은 모든 요청이 공유하는 자원이므로, 서블릿을 최대한 무상태(Stateless)하게 만들어야 한다. 공유 변수 사용을 주의하자.
  *   - 서블릿 컨테이너가 종료되면, 서블릿 객체도 소멸한다.
  * · JSP 도 서블릿으로 변환되어서 사용된다.
  * · 클라이언트의 동시 요청을 위해 멀티 쓰레드를 지원한다.
  *   - 100대, 1000대의 클라이언트가 동시에 요청해도 WAS 는 지체없이 요청을 처리할 수 있다.
  *   - 개발자가 직접 멀티 스레드에 신경쓰지 않아도 WAS 가 자동으로 처리해준다.
  *
  * */

}
