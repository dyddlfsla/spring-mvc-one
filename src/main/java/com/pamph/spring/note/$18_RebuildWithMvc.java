package com.pamph.spring.note;

public class $18_RebuildWithMvc {

  /*
  *
  * MVC 패턴 적용하기
  *
  * 서블릿에게 컨트롤러 역할을 부여하고 JSP 에는 VIEW 역할을 부여한다.
  * 이때 데이터 Model 은 HttpServletRequest 객체를 이용해 다룬다. request 내부에는 데이터 저장소를 가지고 있는데,
  * request.setAttribute(), request.getAttribute() 메서드를 사용해 데이터를 보관하고 조회할 수 있다.
  *
  * @WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
  * public class MvcMemberFormServlet extends HttpServlet {
  *
  * @Override
  * protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  *   String viewPath = "/WEB-INF/views/new-form.jsp";
  *   RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
  *   dispatcher.forward(request, response);
  *  }
  * }
  *
  *
  *
  * ▶ dispatcher.forward(): url 이 맵핑된 서버내의 다른 리소스로 이동하는 기능이다. 서버 내부에서 다시 해당 자원을 호출하는것이다.
  * ▶ WEB-INF 패키지: WEB-INF 디렉토리는 외부로부터 보호되는 영역으로, 해당 경로에 존재하는 리소스는 브라우저의 URL 요청을 통해 직접 접근할 수 없다.
  *   따라서 WEB-INF 하위 리소스는 반드시 서버 내부에서 forward 또는 include 등의 방식으로만 접근 가능하다.
  *
  * ▶
  *
  *
  *
  *
  * */

}
