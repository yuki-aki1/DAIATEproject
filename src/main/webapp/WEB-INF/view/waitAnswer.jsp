<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
// テストデータ
response.setIntHeader("Refresh", 63);
String roomId = "0002";
String[] playerNames = { "A", "B", "C", "D", "E" };
int playerIndex = 2;
int answerPlayerIndex = 4;
String subject = "オリンピック";
String[] hints ={"バスケ","東京","4年","東京",""};

String playerNamesList = String.join(",", playerNames);
String hintsList = String.join(",", hints);
--%>

<%
String roomId = (String) request.getAttribute("roomId");
String[] playerNames = (String[]) request.getAttribute("playerNames");
int playerIndex = (int) request.getAttribute("playerIndex");
int answerPlayerIndex = (int) request.getAttribute("answerPlayerIndex");
String subject = (String) request.getAttribute("subject");
String[] hints = (String[]) request.getAttribute("hints");

String playerNamesList = String.join(",", playerNames);
String hintsList = String.join(",", hints);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="5;URL=./waitAnswer">
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


	<%-- ヒント表示  --%>
	<jsp:include page="./include/includeHint.jsp">
		<jsp:param name="playerIndex" value="<%=playerIndex%>" />
		<jsp:param name="answerPlayerIndex" value="<%=answerPlayerIndex%>" />
		<jsp:param name="playerNamesList" value="<%=playerNamesList%>" />
		<jsp:param name="hintsList" value="<%=hintsList%>" />
	</jsp:include>
	<p>---------------------------------------------------</p>


	<%-- ヒント出す人に対する表示 --%>
	<%
	if (playerIndex != answerPlayerIndex) {
	%>
	<p>解答者が答えを入力するまで1分間お待ちください！</p>

	<%
	}
	%>


</body>
</html>