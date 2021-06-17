<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String name = (String)request.getAttribute("name"); %>
<% String roomId  = (String)request.getAttribute("roomId"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム待機画面</title>
</head>
<body>

	<% if(name != null){ %>
	<p>
		プレイヤー名:<%= name %></p>
	<%} %>

	<% if(roomId != null){ %>
	<p>
		ルームID:<%= roomId %></p>
	<%} %>

</body>
</html>