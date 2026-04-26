<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

성공
<%--
  서버에서 request.setAttribute() 로 담았던 데이터를 꺼내 사용한다.
--%>
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
</body>
</html>
