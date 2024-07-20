package com.pamph.spring.note;

public class $6_HelloServlet {

  /*
 self-taught
  *
  * Ⅰ. Servlet 등록하기
  *
  * 스프링부트 환경에서 서블릿을 등록하고 사용해보자.
  *
  * ※ 서블릿은 Tomcat 같은 웹 애플리케이션 서버를 직접 설치하고 그 위에 서블릿 코드를 클래스 파일로 빌드해서 올린 다음,
  * Tomcat 서버를 실행하면 된다. 하지만 이 과정은 매우 번거롭고 설정이 복잡하다.
  * 스프링부트 환경에서는 Tomcat 서버를 내장하고 있으므로 위의 과정이 없이도 손쉽게 서블릿 코드를 실행할 수 있다.
  *
  * 1) Servlet 환경 구성
  *
  * @ServletComponentScan
  * 스프링부트는 Servlet 을 직접 등록해서 사용할 수 있도록 @ServletComponentScan 이라는 어노테이션을 지원한다.
  *
  * 프로젝트 최상단 애플리케이션 시작 클래스에 @ServletComponentScan 을 붙여준다.
  *
  * @ServletComponentScan
  * @SpringBootApplication
  * public class SpringMvcOneApplication {
  *   ...
  * }
  *
  * 2) Servlet 등록하기
  * 실제 동작하는 Servlet 코드를 작성해본다.
  *
  * @WebServlet(name = "helloServlet", urlPatterns = "/hello")
  * public class HelloServlet extends HttpServlet {
  *
  *   @Override
  *   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  *
  *     System.out.println("HelloServlet.service");
  *     System.out.println("request = " + request);
  *     System.out.println("response = " + response);
  *
  *     String username = request.getParameter("username");
  *     System.out.println("username = " + username);
  *
  *     response.setContentType("text/plaint");
  *     response.setCharacterEncoding("UTF-8");
  *     response.getWriter().write(String.format("Hello %s!", username));
  *   }
  * }
  *
  * Servlet 클래스는 HttpServlet 클래스를 상속해야 한다. => extends HttpServlet
  * 클래스에 @WebServlet 애노테이션을 적용한다.
  *   - name: Servlet 의 이름
  *   - urlPatterns: Servlet 에 연결할 URL 경로
  *
  * 브라우저에서 매핑된 URL 로 HTTP 요청이 오면 서블릿 컨테이너는 protected void service(...) 메서드를 실행한다.
  *
  * ▶ HttpServletRequest request: HTTP 요청 정보를 담고 있는 객체이다.
  *   - getParameter(): 객체에서 요청 정보를 조회한다.
  *
  * ▶ HttpServletResponse response: HTTP 응답 정보를 생성하는 객체이다.
  *   - setContentType(): 응답 메세지 본문의 데이터 타입을 지정한다.
  *   - setCharacterEncoding(): 응답 메세지 본문의 문자셋을 지정한다.
  *   - getWriter().write(): 응답 메세지 본문에 데이터를 출력한다.
  *
  * 웹 애플리케이션 서버의 요청 응답 구조는 다음과 같다.
  *
  *                                         ┌───────────── Spring Boot ─────────────────────────────────────┐
  *                                         │   built-in Tomcat Server                                      │
  *                                         │  ┌──────────────────────────────────────────────────────────┐ │
  *                                         │  │                                      Servlet Container   │ │
  * ┌─────────────┐                         │  │ ┌─────────────┐           ┌────────────────────────────┐ │ │
  * │ Web browser │                         │  │ │             │           │                            │ │ │
  * │             │  localhost:8080/hello   │  │ │ [request] → │ --------> │   run  (request, response) │ │ │
  * │             │  -------------------->  │  │ │ [response]  │           │       ↘                    │ │ │
  * │             │                         │  │ │             │           │         helloServlet       │ │ │
  * │             │                         │  │ │             │           │       ↙                    │ │ │
  * │             │   "Hello, world!"       │  │ │             │           │   close                    │ │ │
  * │             │  <--------------------  │  │ │ [response]  │ <-------- │  ←                         │ │ │
  * │             │                         │  │ │             │           │                            │ │ │
  * └─────────────┘                         │  │ └─────────────┘           └────────────────────────────┘ │ │
  *                                         │  └──────────────────────────────────────────────────────────┘ │
  *                                         └───────────────────────────────────────────────────────────────┘
  *
  *
  * ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
  *
  * */

}
