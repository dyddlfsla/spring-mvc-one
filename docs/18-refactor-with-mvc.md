
## MVC 패턴 적용하기

서블릿에게 컨트롤러 역할을 부여하고 JSP 에는 VIEW 역할을 부여한다.  
이때 데이터 Model 은 HttpServletRequest 객체를 이용해 다룬다.  
request 내부에는 데이터 저장소를 가지고 있는데, request.setAttribute(), request.getAttribute() 메서드를 사용해 데이터를 보관하고 조회할 수 있다.  

### 1) 회원 등록 화면 보여주기

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

 ☑️ `dispatcher.forward()`: 현재 요청과 응답 객체를 유지한 채 서버 내부의 다른 자원으로 요청 처리를 위임하는 기능이다.  

 ☑️ `WEB-INF 패키지`: WEB-INF 디렉토리는 외부로부터 보호되는 영역으로, 해당 경로에 존재하는 리소스는 브라우저의 URL 요청을 통해 직접 접근할 수 없다.    
     따라서 WEB-INF 하위 리소스는 반드시 서버 내부에서 forward 또는 include 등의 방식으로만 접근 가능하다.

 ☑️ `redirect vs. forward`
  리다이렉트는 클라이언트(웹 브라우저)에 응답이 나간 뒤, 클라이언트가 지정된 URL로 다시 요청하는 방식이다.  
  이 과정에서 클라이언트는 재요청을 인지할 수 있으며, 브라우저의 URL도 변경된다. 또한 새로운 요청이 생성되므로 request 객체는 유지되지 않는다.  
  반면, forward는 서버 내부에서 요청을 다른 자원으로 전달하는 방식으로, 클라이언트는 이를 인지할 수 없고 URL도 변경되지 않는다. 이때 기존 request 객체가 그대로 유지되어 데이터 전달이 가능하다.  


```html
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <title>Title</title>
 </head>
 <body>
 <!-- 
 여기서 form action 값을 보면, 절대경로(/로 시작)가 아니라 상대경로(/ 없이 시작)인 것을 확인하자.
 상대경로를 사용하면, [현재 URL 이 속한 계층 경로 + /save] 가 요청 URL 이 된다. -->
 <form action="save" method="post"> 
     username: <input type="text" name="username"/>
     age: <input type="text" name="age"/>
     <button type="submit">전송</button>
 </form>
 </body>
 </html>
```

---

 ### 2) 회원 저장 요청 처리하기

 ☑️ 서버 코드

```java

 @WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
 public class MvcMemberSaveServlet extends HttpServlet {
 
   private MemberRepository memberRepository = MemberRepository.getInstance();
 
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
     String username = request.getParameter("username");
     int age = Integer.parseInt(request.getParameter("age"));
 
     Member member = new Member(username, age);
     memberRepository.save(member);
 
     // Model 에 데이터를 보관한다.
     request.setAttribute("member", member);
 
     String viewPath = "/WEB-INF/views/save-result.jsp";
     RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
     dispatcher.forward(request, response);
   }
 }
```

 ☑️ JSP 코드

```html
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <title>Title</title>
 </head>
 <body>
 
 성공
 <!--
   getAttribute() 로 모델이 저장된 member 객체를 꺼낼 수 있지만
   비효율적이므로 잘 사용되지 않는다.
   <li>id=<%=((Member)request.getAttribute("member")).getId()%>
 -->
 <ul>
     <!-- 대신 JSP 가 기본 제공하는 ${} 문법을 사용해 값을 꺼낸다.-->
     <li>id=${member.id}</li> 
     <li>username=${member.username}</li>
     <li>age=${member.age}</li>
 </ul>
 </body>
 </html>
```

---

 ### 3) 전체 회원 조회하기
 
 ☑️ 서버 코드

```java

 @WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
 public class MvcMemberListServlet extends HttpServlet {
 
   private MemberRepository memberRepository = MemberRepository.getInstance();
 
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
     List<Member> members = memberRepository.findAll();
 
     request.setAttribute("members", members);
 
     String viewPath = "/WEB-INF/views/members.jsp";
     RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
     dispatcher.forward(request, response);
 
   }
 }
 
```

 ☑️ JSP 코드
 
```html
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <!-- jstl 라이브러리를 추가하여 더 쉽게 JSP 문법을 사용한다. -->
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
 <html>
 <head>
     <title>Title</title>
 </head>
 <body>
 <a href="/index.html">메인</a>
 <table>
   <thead>
   <th>id</th>
   <th>username</th>
   <th>age</th>
   </thead>
   <tbody>
   <!-- 기존의 for 문 방식보다 더 가독성 있고 쉽게 코드를 작성할 수 있다.-->
   <c:forEach var="item" items="${members}">
     <tr>
       <td>${item.id}</td>
       <td>${item.username}</td>
       <td>${item.age}</td>
     </tr>
   </c:forEach>
   </tbody>
 </table>
 </body>
 </html>
```
 **정리**

 MVC 패턴을 적용한 덕분에 컨트롤러의 역할과 뷰를 그리는 역할을 더 명확하게 구분할 수 있다.  
 특히 뷰는 화면을 그리는 역할에 충실한 덕분에 코드가 깔끔하고 직관적이다. 단순하게 모델에서 필요한 데이터를 꺼내고, 화면을 만들기만 하면 된다.  
 그렇다면 이것만으로 충분하다고 할 수 있을까?


 🚨 `MVC 패턴의 한계`  
 순수 JSP 를 사용하던 것보다는 확실히 각 역할 간의 구분이 뚜렷해졌고 코드 재사용성도 높아졌다.  
 하지만 그럼에도 자세히 살펴보면 컨트롤러와 뷰 코드에는 중복이 많고 불필요한 코드도 보인다.

 `MVC 컨트롤러의 단점`
 - forward 중복
   - View로 이동하는 코드가 항상 중복 호출되어야 한다.
   ```java
     RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
     dispatcher.forward(request, response);
   ```
 - viewPath 의 중복
   - 포워드를 하기 위해 viewPath 를 지정하는 코드 역시 컨트롤러마다 중복되고 있다.
     ```java
     String viewPath = "/WEB-INF/views/save-result.jsp";
     ```
 - 불필요한 코드
   - response 객체가 필요하지 않은 상황에서도 재정의로 인해 계속 선언되어야 한다.
 - 공통 처리의 어려움
   - 앱 기능이 복잡해질수록 컨트롤러에서 공통으로 처리해야 하는 부분이 점점 더 많이 증가할 것이다.  
   - 공통된 기능을 메서드로 추출하면 될 것 같지만, 그렇게 하더라도 해당 메서드는 중복 호출되고고 개발자의 실수 확률을 높인다.
