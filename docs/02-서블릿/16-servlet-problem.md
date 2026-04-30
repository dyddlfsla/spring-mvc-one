
 ## 순수한 Servlet 코드의 문제점.

 서블릿을 사용해 간단한 회원 저장 앱을 만들어보자.

 사용자가 회원 등록이나 전체 회원 멤버 조회시 response 객체에 html 태그를 하나하나씩 작성하여 클라이언트에게 전송한다.

 ### 1) 클라이언트에게 회원 등록하는 html 보여주기

```java

 @WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
 public class MemberFormServlet extends HttpServlet {

   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
     response.setContentType("text/html");
     response.setCharacterEncoding("utf-8");
 
     PrintWriter writer = response.getWriter();
 
     writer.write("<!DOCTYPE html>\n" +
         "<html>\n" +
         "<head>\n" +
         "    <meta charset=\"UTF-8\">\n" +
         "    <title>Title</title>\n" +
         "</head>\n" +
         "<body>\n" +
         "<form action=\"/servlet/members/save\" method=\"post\">\n" +
         "    username: <input type=\"text\" name=\"username\" />\n" +
         "    age:      <input type=\"text\" name=\"age\" />\n" +
         "    <button type=\"submit\">전송</button>\n" +
         "</form>\n" +
         "</body>\n" +
         "</html>\n");

  }
}

```

 ### 2) 클라이언트가 회원 등록을 요청하면 받아서 처리하기

```java

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    System.out.println("MemberSaveServlet.service");
    
    // 전달된 데이터에서 회원 정보 추출
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    
    // 회원 객체를 만들고 저장
    Member member = new Member(username, age);
    memberRepository.save(member);
    
    // 사용자에게 등록된 회원 정보와 성공 여부를 html 로 응답
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");

    PrintWriter writer = response.getWriter();

    writer.write("<html>\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "</head>\n" +
        "<body>\n" +
        "성공\n" +
        "<ul>\n" +
        "    <li>id="+member.getId()+"</li>\n" +
        "    <li>username="+member.getUsername()+"</li>\n" +
        "    <li>age="+member.getAge()+"</li>\n" +
        "</ul>\n" +
        "<a href=\"/index.html\">메인</a>\n" +
        "</body>\n" +
        "</html>");

  }
}
```

 ### 3) 사용자에게 전체 회원 목록 보여주기

```java

 @WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
 public class MemberListServlet extends HttpServlet {

   private MemberRepository memberRepository = MemberRepository.getInstance();
 
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
     // 저장된 모든 회원 조회
     List<Member> members = memberRepository.findAll();
 
     response.setContentType("text/html");
     response.setCharacterEncoding("utf-8");
 
     PrintWriter writer = response.getWriter();
 
     writer.write("<html>");
     writer.write("<head>");
     writer.write("    <meta charset=\"UTF-8\">");
     writer.write("    <title>Title</title>");
     writer.write("</head>");
     writer.write("<body>");
     writer.write("<a href=\"/index.html\">메인</a>");
     writer.write("<table>");
     writer.write("    <thead>");
     writer.write("    <th>id</th>");
     writer.write("    <th>username</th>");
     writer.write("    <th>age</th>");
     writer.write("    </thead>");
     writer.write("    <tbody>");
     
     // 반복문을 통해 각 회원 정보 추출
     for (Member member : members) {
       writer.write("    <tr>");
       writer.write("        <td>"+member.getId()+"</td>");
       writer.write("        <td>"+member.getUsername()+"</td>");
       writer.write("        <td>"+member.getAge()+"</td>");
       writer.write("    </tr>");
     }
 
     writer.write("    </tbody>");
     writer.write("</table>");
     writer.write("</body>");
     writer.write("</html>");

  }
}

```

 #### 🚨 순수 서블릿의 한계

 서블릿과 순수 자바 코드만으로도 클라이언트와 통신하는 서버를 구현할 수 있다.  
 하지만 클라이언트에게 HTML 문서를 응답하기 위해서는, 서버에서 HTML의 각 태그를 자바 문자열로 직접 작성해야 한다.  
 이 방식은 단순한 페이지에서는 잘 동작하지만, 코드가 길어지고 복잡해질수록 가독성이 떨어지고 유지보수가 어려워지는 문제가 있다.  
 자바 코드로 HTML 태그를 일일이 조합하기보다는, 이미 작성된 HTML 문서에 필요한 데이터만 동적으로 적용할 수 있다면 훨씬 효율적이다.  
 이러한 문제를 해결하기 위해 등장한 것이 `템플릿 엔진`이다.
 템플릿 엔진을 사용하면 HTML 문서의 특정 부분에만 값을 주입하거나 로직을 적용하여, 동적으로 변경된 결과를 생성할 수 있다.
 대표적인 템플릿 엔진으로는 JSP, Thymeleaf, Freemarker, Velocity 등이 있다.  
 이제 JSP를 사용하여, 클라이언트에게 전달할 HTML 문서를 보다 효율적으로 생성해보자.
