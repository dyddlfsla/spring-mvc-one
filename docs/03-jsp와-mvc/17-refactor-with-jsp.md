
 ## 서블릿을 JSP 로 리팩토링해보기.
 
 ### 1) 프로젝트에 JSP 의존성 추가

 먼저 프로젝트의 빌드 스크립트를 수정하고 JSP 라이브러리를 다운로드 한다. 

 ```groovy
dependencies {
    // 스프링 내장 톰캣에서 JSP 를 컴파일/실행하기 위한 jasper 의존성 추가
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
}
 ```
 🚨 일반적으로 JSP 는 자체 문법만으로는 사용하기 불편하여 JSTL(JSP Standard Tag Library)같은 추가 라이브러리를 같이 사용한다.

 이제부터는 HTML 대신 JSP 페이지를 만들어 회원 관리 앱을 만들어 보자.

 모든 JSP 페이지의 첫 줄은 다음의 코드를 넣어주는 편인데,

```jsp
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
```
 톰캣 기본설정이 text/html 이므로 반드시 넣어야 하는 것은 아니지만 만약을 위해 실무에서는 거의 작성한다.

 JSP 에서는 HTML 태그와 자바 코드를 사용하여 태그와 결합하여 동적 데이터를 넣을 수 있다.  
 다음과 같은 기본 문법이 사용된다.

 ### 2) JSP 기본 문법

 #### <% %> → 스크립트 릿(자바코드 실행)
 #### <%= %> → 표현식(값 출력)
 #### <%! %> → 선언문(변수/메서드 선언)

 JSP 코드는 서버 내부에서 서블릿으로 변환되어 처리된다.

 ### 3) 회원 등록 JSP

 ```html
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Title</title>
  </head>
  <body>
  <form action="/jsp/members/save.jsp" method="post">
      username: <input type="text" name="username"/>
      age: <input type="text" name="age"/>
      <button type="submit">전송</button>
  </form>
  </body>
  </html>
```

 ### 4) 회원 저장 JSP

```html
 <%@ page import="com.pamph.spring.domain.member.MemberRepository" %>
 <%@ page import="com.pamph.spring.domain.member.Member" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%
 
   // jsp 도 결국 서블릿으로 변환되므로 request, response 객체를 변수 선언 없이 사용할 수 있다.
   MemberRepository memberRepository = MemberRepository.getInstance();
 
   System.out.println("MemberSaveServlet.service");
 
   String username = request.getParameter("username");
   int age = Integer.parseInt(request.getParameter("age"));
 
   Member member = new Member(username, age);
   memberRepository.save(member);
 
 %>
 <html>
 <head>
     <title>Title</title>
 </head>
 <body>
 
 성공
 <ul>
   <li>id=<%=member.getId()%></li>
   <li>username=<%=member.getUsername()%></li>
   <li>age=<%=member.getAge()%></li>
 </ul>
 
 </body>
 </html>
```

 ### 5) 전체 회원 조회 JSP

```html
  <%@ page import="com.pamph.spring.domain.member.MemberRepository" %>
  <%@ page import="com.pamph.spring.domain.member.Member" %>
  <%@ page import="java.util.List" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  
  <%
      MemberRepository memberRepository = MemberRepository.getInstance();
      List<Member> members = memberRepository.findAll();
  %>
  
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
      <%
          for (Member member : members) {
              out.write("    <tr>");
              out.write("        <td>"+member.getId()+"</td>");
              out.write("        <td>"+member.getUsername()+"</td>");
              out.write("        <td>"+member.getAge()+"</td>");
              out.write("    </tr>");
          }
      %>
      </tbody>
  </table>
  </body>
  </html>
```


 🚨 **서블릿과 JSP 의 한계**  
 서블릿만으로 개발할 때에는 뷰(View)화면을 위해 HTML 태그를 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했지만,  
 JSP를 사용하면서 서블릿처럼 자바 문자열로 HTML을 직접 조립하지 않아도 되고, HTML 문서 안에 필요한 부분만 자바 코드를 섞어 동적으로 화면을 만들 수 있다. 
 하지만 여전히 JSP 안에 비즈니스 로직과 뷰 코드가 함께 존재한다는 한계가 있다.

 회원 저장 JSP 를 보자.
 코드의 상위 절반은 회원을 저장하기 위한 비즈니스 로직이고, 나머지 하위 절반은 결과를 HTML 로 보여주기 위한 뷰 영역이다.  
 코드를 살펴보면, 데이터의 대상이 되는 회원, 이를 조회하는 레포지토리 등등 다양한 자바 객체들이 JSP 에 노출되어 있다.  
 즉 JSP 에 너무 많은 작업이 몰려 있는 것이다. 단순하게 회원 이름과 나이만 받아 저장하는 이 간단한 프로젝트에서도 이렇게 복잡한 코드가 나오게 되는데  
 실제 실무에서 만들어지는 프로젝트들은 수백, 수천개의 클래스로 이루어져 있으며 다른 서버와도 끊임없이 통신하는 로직들을 가지고 있다.
 그런데 이러한 구조로 감당할 수 있을까?  

 결국 이러한 문제를 해결하기 위해 여러 방법이 시도되었고 MVC 패턴이 등장한다.

 요청 처리와 비즈니스 로직은 컨트롤러/서비스 계층에서 처리하고, JSP는 HTML 화면을 그리는 View 역할에 집중하도록 하자.  
 과거 개발자들도 모두 비슷한 고민이 있었고, 여러 해결방안 중 하나가 MVC 패턴인 것이다.

