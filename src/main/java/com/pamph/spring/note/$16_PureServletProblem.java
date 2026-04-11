package com.pamph.spring.note;

public class $16_PureServletProblem {

  /*
  * 순수한 서블릿을 사용하여 간단한 회원 저장 앱을 만들어보자.
  *
  * 회원 등록이나 전체 회원 멤버 조회시 응답 response 객체에 html 태그를 하나하나씩 작성하여 클라이언트에게 전송한다.
  *
  * 1) 클라이언트에게 회원 등록하는 html form 보여주기
  * PrintWriter writer = response.getWriter();
  *
  * writer.write("<!DOCTYPE html>\n" +
  *     "<html>\n" +
  *     "<head>\n" +
  *     "    <meta charset=\"UTF-8\">\n" +
  *     "    <title>Title</title>\n" +
  *     "</head>\n" +
  *     "<body>\n" +
  *     "<form action=\"/servlet/members/save\" method=\"post\">\n" +
  *     "    username: <input type=\"text\" name=\"username\" />\n" +
  *     "    age:      <input type=\"text\" name=\"age\" />\n" +
  *     "    <button type=\"submit\">전송</button>\n" +
  *     "</form>\n" +
  *     "</body>\n" +
  *     "</html>\n");
  *
  *
  * 2) 회원 등록 후 등록된 회원 내용 보여주기
  *
  * writer.write("<html>\n" +
  *     "<head>\n" +
  *     "    <meta charset=\"UTF-8\">\n" +
  *     "</head>\n" +
  *     "<body>\n" +
  *     "성공\n" +
  *     "<ul>\n" +
  *     "    <li>id="+member.getId()+"</li>\n" +
  *     "    <li>username="+member.getUsername()+"</li>\n" +
  *     "    <li>age="+member.getAge()+"</li>\n" +
  *     "</ul>\n" +
  *     "<a href=\"/index.html\">메인</a>\n" +
  *     "</body>\n" +
  *     "</html>");
  *
  *
  * 3) 전체 회원 목록 보여주기
  *
  * writer.write("<html>");
  *  writer.write("<head>");
  *  writer.write("    <meta charset=\"UTF-8\">");
  *  writer.write("    <title>Title</title>");
  *  writer.write("</head>");
  *  writer.write("<body>");
  *  writer.write("<a href=\"/index.html\">메인</a>");
  *  writer.write("<table>");
  *  writer.write("    <thead>");
  *  writer.write("    <th>id</th>");
  *  writer.write("    <th>username</th>");
  *  writer.write("    <th>age</th>");
  *  writer.write("    </thead>");
  *  writer.write("    <tbody>");
  *
  *  for (Member member : members) {
  *    writer.write("    <tr>");
  *    writer.write("        <td>"+member.getId()+"</td>");
  *    writer.write("        <td>"+member.getUsername()+"</td>");
  *    writer.write("        <td>"+member.getAge()+"</td>");
  *    writer.write("    </tr>");
  *  }
  *
  *  writer.write("    </tbody>");
  *  writer.write("</table>");
  *  writer.write("</body>");
  *  writer.write("</html>");
  *
  *
  *
  * ※ 순수한 서블릿만의 한계
  * 지금까지 서블릿과 순수 자바코드를 활용해 클라이언트와 통신하는 서버를 만들었다.
  * 클라이언트에게 적절한 html 문서를 내려주기 위해 서버에서는 html 의 각 태그들을 자바 문자열 형식으로 다뤄야 했다.
  * 언뜻 보면 서블릿만으로도 원하는 html 태그를 조작하여 적절한 html 응답을 보내주는 것처럼 보이지만 이는 매우 불편하고 비효율적이다.
  *
  * 자바 코드로 일일이 HTML 태그를 조합하여 html 을 만들어내기 보다 이미 만들어진 html 문서에 필요한 데이터만 적용시킬 수 있다면 더 효율적일 것이다.
  * 이것이 바로 템플릿 엔진이 나온 이유이다. 템플릿 엔진을 사용하면 HTML 문서에서 필요한 곳만 코드를 적용하여 동적으로 변경할 수 있다.
  *
  * 현재 주로 사용되는 템플릿 엔진에는 JSP, Thymeleaf, Freemarker, Velocity 등이 있다.
  *
  * 일단 JSP 를 사용하여 클라이언트에게 보내는 html 문서를 좀더 효율적으로 생성해보자.
  *
  * */

}
