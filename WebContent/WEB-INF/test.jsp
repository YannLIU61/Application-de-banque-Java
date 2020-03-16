
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id ="user" class="beans.User"/>
<%-- user.setNom("Test Hello"); --%>
<jsp:setProperty name="user" property="nom" value="LIU"/>
<%--out.print(user.getNom()); --%>
<c:out value="${user.getNom()}"></c:out>
</body>
</html>