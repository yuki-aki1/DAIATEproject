<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%response.setIntHeader("Refresh", 1);%>
<% String name = (String)request.getAttribute("name"); %>
<% String roomId  = (String)request.getAttribute("roomId"); %>
<% int numOfUser = ((Integer)(request.getAttribute("numOfUser"))).intValue(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム待機画面</title>
</head>
<body>

	<% if(roomId != null){ %>
		<p>ルームID:<%= roomId %></p>
	<%} %>

	<% for (int i = 0; i < numOfUser; i++) { %>
		<% if(name != null){ %>
			<p>プレイヤー名:<%= name %></p>
		<% } %>
	<% } %>

</body>
</html>