
## MVC 패턴 적용하기

서블릿에게 컨트롤러 역할을 부여하고 JSP 에는 VIEW 역할을 부여한다.  
이때 데이터 Model 은 HttpServletRequest 객체를 이용해 다룬다.  
request 내부에는 데이터 저장소를 가지고 있는데, request.setAttribute(), request.getAttribute() 메서드를 사용해 데이터를 보관하고 조회할 수 있다.  

```java
 @WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
   public class MvcMemberFormServlet extends HttpServlet {
   
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   String viewPath = "/WEB-INF/views/new-form.jsp";
   RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
   dispatcher.forward(request, response);
   }
 }
```


▶ dispatcher.forward(): url 이 맵핑된 서버내의 다른 리소스로 이동하는 기능이다. 서버 내부에서 다시 해당 자원을 호출하는것이다.
▶ WEB-INF 패키지: WEB-INF 디렉토리는 외부로부터 보호되는 영역으로, 해당 경로에 존재하는 리소스는 브라우저의 URL 요청을 통해 직접 접근할 수 없다.
따라서 WEB-INF 하위 리소스는 반드시 서버 내부에서 forward 또는 include 등의 방식으로만 접근 가능하다.

▶ redirect vs. forward
리다이렉트는 클라이언트(웹 브라우저)에 응답이 나간 뒤, 클라이언트가 지정된 URL로 다시 요청하는 방식이다.
이 과정에서 클라이언트는 재요청을 인지할 수 있으며, 브라우저의 URL도 변경된다.
또한 새로운 요청이 생성되므로 request 객체는 유지되지 않는다.
반면 forward는 서버 내부에서 요청을 다른 자원으로 전달하는 방식으로, 클라이언트는 이를 인지할 수 없고 URL도 변경되지 않는다.
이때 기존 request 객체가 그대로 유지되어 데이터 전달이 가능하다.

