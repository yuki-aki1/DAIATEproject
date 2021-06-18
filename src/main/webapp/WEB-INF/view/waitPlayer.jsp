<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String[] playerNames = { "A", "B", "C", "D", "E" };
String roomId = "0002";
int playerIndex = 0;
%>
<%--
response.setIntHeader("Refresh", 1); 
String[] playerNames = (String[]) request.getAttribute("playerNames");
String roomId = (String) request.getAttribute("roomId");
int playerIndex = (int) request.getAttribute("playerIndex");
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム待機画面</title>
</head>
<body>

	<%-- ルームID表示 --%>
	<p>
		ルームID：<%=roomId%></p>
	<br>

	<%-- プレイヤー一覧表示 --%>
	<p>「プレイヤー一覧」</p>
	<%
	for (int i = 0; i < playerNames.length; i++) {
	%>
	<p>
		プレイヤー名:<%=playerNames[i]%>
		<%
		if (i == 0) {
		%>
		(ホスト)
		<%
		} else if (i == playerIndex) {
		%>
		(あなた)
		<%
		}
		%>
	</p>
	<%
	}
	%>

	<%-- 退室ボタン表示 --%>
	<form method="POST" action="./home">
		<input type="submit" value="退室">
	</form>

	<br>
	<%-- ゲーム開始ボタン表示 --%>
	<%
	if (playerIndex == 0) {
	%>
	<form method="POST" action="./startGame">
		<p>
			<input type="submit" value="ゲーム開始">
		</p>
		<%
		}
		%>
	</form>

</body>
</html>