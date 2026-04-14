<%@ page import="com.pamph.spring.domain.member.MemberRepository" %>
<%@ page import="com.pamph.spring.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  // jsp 도 결국 서블릿으로 변환되므로 request, response 객체는 변수 선언 없이도 사용할 수 있다.
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
