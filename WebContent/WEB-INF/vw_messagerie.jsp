<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link href="assets/css/mystyle.css" rel="stylesheet">
</head>
</head>
<body>
	<main> <article>
	<div class="liste">
		<h3>
			<c:out value="${sessionScope.connected_user.prenom} "></c:out>
			<c:out value=" ${sessionScope.connected_user.nom}"></c:out>
			- Messages reçus
		</h3>
		<table>
			<c:forEach items="${sessionScope.messagesRecus}" var="message">
				<tr>
					<td><c:out value="${message.nom }."></c:out> <c:out
							value=".${message.prenom }">
						</c:out></td>
					<td><c:out value="${message.sujet }."></c:out></td>
					<td><c:out value="${message.corps }."></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	</article> </main>
</body>
</html>