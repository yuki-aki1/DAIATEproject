<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// テストデータ
response.setIntHeader("Refresh", 63);
String roomId = "0002";
String[] playerNames = { "A", "B", "C", "D", "E" };
int playerIndex = 2;
int answerPlayerIndex = 4;
String subject = "オリンピック";

String playerNamesList = String.join(",", playerNames);
%>

<%--
response.setIntHeader("Refresh", 63); 
String roomId = (String) request.getAttribute("roomId");
String[] playerNames = (String[]) request.getAttribute("playerNames");
int playerIndex = (int) request.getAttribute("playerIndex");
int answerPlayerIndex = (int) request.getAttribute("answerPlayerIndex");
String subject = (String) request.getAttribute("subject");
String startTime = (String) request.getAttribute("startTime");
String playerNamesList = String.join(",", playerNames);
-- --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- ルームID表示 --%>
	<jsp:include page="./include/includeRoomId.jsp">
		<jsp:param name="roomId" value="<%=roomId%>" />
	</jsp:include>
<p>---------------------------------------------------</p>


	<%--  プレイヤー一覧表示 --%>
	<jsp:include page="./include/includePlayerName.jsp">
		<jsp:param name="playerIndex" value="<%=playerIndex%>" />
		<jsp:param name="answerPlayerIndex" value="<%=answerPlayerIndex%>" />
		<jsp:param name="playerNamesList" value="<%=playerNamesList%>" />
	</jsp:include>


	<%-- 退室ボタン表示 --%>
	<form method="POST" action="./home">
		<input type="submit" value="退室">
	</form>
<p>---------------------------------------------------</p>

	<%-- 時間表示  --%>
	<jsp:include page="./include/includeTime.jsp" />
<p>---------------------------------------------------</p>

	<%-- ヒント出す人に対する表示 --%>
	<form method="POST" action="./sendHint">
		<%
		if (playerIndex != answerPlayerIndex) {
		%>
		<p>あなたはヒントを出すプレイヤーです！</p>
		<p>
			お題：「<font color="red"><%=subject%></font>」
		</p>
		<p>1分以内にヒントを入力してください！</p>
		<p>
			ヒント：<input type="text" name="hint">
		</p>
		<p>
			<input type="submit" value="ヒント決定">
		</p>
		<%
		}
		%>
	</form>

	<%-- 解答者に対する表示 --%>
	<%
	if (playerIndex == answerPlayerIndex) {
	%>
	<p>あなたは解答者です！</p>
	<p>ヒントが入力されるまで1分間お待ちください！</p>

	<%
	}
	%>


</body>
</html>