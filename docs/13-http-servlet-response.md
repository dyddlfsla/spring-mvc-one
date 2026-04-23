
 ## HttpServletResponse - 기본 사용법

 지금까지 클라이언트가 서버로 보내는 HttpServletRequest 에 대해 알아봤다.
 이번에는 반대로 서버가 클라이언트에게 보내주는 HTTP 응답을 알아보자.

 자바 서블릿에서는 클라이언트에게 응답을 보내기 위해 HttpServletResponse 객체를 제공한다.

 HttpServletResponse 객체를 통해 HTTP 응답 메세지를 생성할 수 있다.

```java
@WebServlet(name = "...", urlPattern = "/...")
 public class HttpResponse extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) {
   // HttpServletResponse 객체를 사용해 응답 메세지 생성
  }

 }
```

 HTTP 응답 메세지를 생성하기 위해 보통 다음과 같은 내용을 지정한다.

 #### ☑️ HTTP 응답코드 지정  
 #### ☑️ HTTP header 생성  
 #### ☑️ HTTP body 생성  


 또한, HttpServletResponse 는 응답 메세지를 작성하기 위해 여러 편의 기능을 제공한다.

 ### 1. content-type 편의 메서드
 response.setHeader("content-type", "text/plain;charset=utf-8");
 위와 같이 직접 헤더 값을 추가하는 것보다
 setContentType() 과 같은 메서드를 사용하면 한결 편리하게 응답 메세지의 content-type 을 지정할 수 있다.

 response.setContentType("text/plain");
 response.setCharacterEncoding("utf-8");

 ### 2. Cookie 편의 메서드
 response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
 위와 같이 직접 헤더에 쿠키 값을 추가하기 보다는 쿠키 객체를 만들고 addCookie() 메서드를 사용할 수 있다.

 Cookie cookie = new Cookie("myCookie", "good");
 cookie.setMaxAge(600);
 response.addCookie(cookie);

 ### 3. redirect 편의 메서드
 response.setHeader("Location", "/basic/hello-form.html");
 리다이렉트 역시 sendRedirect() 메서드를 통해 편리하게 지정할 수 있다.
 response.sendRedirect("/basic/hello-form.html");
