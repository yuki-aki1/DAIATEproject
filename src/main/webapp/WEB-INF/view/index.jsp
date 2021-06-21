<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%response.setIntHeader("Refresh", 1);%>
<% String nameError = (String)request.getAttribute("nameError"); %>
<% String roomIdError = (String)request.getAttribute("roomIdError"); %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>

	<form method="POST" action="./makeRoom">
		<p>
			プレイヤー名：<input type="text" name="name">
		</p>
		<p>
			<input type="submit" value="ルームを作る">
		</p>
	</form>
	<br>
	<form method="POST" action="./enterRoom">
		<p>
			プレイヤー名：<input type="text" name="name">
		</p>
		<p>
			ルームID：<input type="text" name="roomId">
		</p>
		<%--ルームIDが存在しないエラーメッセージ表示 --%>
		<% if(nameError != null){ %>
		<p>
			<font color="red">ルームIDが存在しません</font>
		</p>
		<%} %>
		<%--プレイヤー名が既に存在しているエラーメッセージ表示 --%>
		<% if(roomIdError != null){ %>
		<p>
			<font color="red">このプレイヤー名は既にルームで使用されています。</font>
		</p>
		<%} %>

		<p>
			<input type="submit" value="ルームを作る">
		</p>
	</form>

</body>
</html>