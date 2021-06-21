<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--
// テストデータ
response.setIntHeader("Refresh", 5); 

String roomId = "0002";
String[] playerNames = { "A" };
int playerIndex = 0;

String playerNamesList = String.join(",", playerNames);
--%>

<%
// response.setIntHeader("Refresh", 5);
response.setHeader("URL", "");
String roomId = (String) request.getAttribute("roomId");
String[] playerNames = (String[]) request.getAttribute("playerNames");
int playerIndex = (int) request.getAttribute("playerIndex");
String playerNamesList = String.join(",", playerNames);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3;URL=./waitRoom">
<title>ルーム待機画面</title>
</head>
<body>


	<%-- ルームID表示 --%>
	<jsp:include page="./include/includeRoomId.jsp">
		<jsp:param name="roomId" value="<%=roomId%>" />
	</jsp:include>
	<br>

	<%--  プレイヤー一覧表示 --%>
	<jsp:include page="./include/includePlayerName.jsp">
		<jsp:param name="playerIndex" value="<%=playerIndex%>" />
		<jsp:param name="playerNamesList" value="<%=playerNamesList%>" />
	</jsp:include>
	<br>
	
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