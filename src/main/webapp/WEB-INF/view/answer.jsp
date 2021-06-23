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
String[] hints ={"バスケ","東京","4年","東京",""};

String playerNamesList = String.join(",", playerNames);
String hintsList = String.join(",", hints);
%>

<%--
response.setIntHeader("Refresh", 63); 
String roomId = (String) request.getAttribute("roomId");
String[] playerNames = (String[]) request.getAttribute("playerNames");
int playerIndex = (int) request.getAttribute("playerIndex");
int answerPlayerIndex = (int) request.getAttribute("answerPlayerIndex");
String subject = (String) request.getAttribute("subject");
String[] hints = (String[]) request.getAttribute("hints");

String playerNamesList = String.join(",", playerNames);
String hintsList = String.join(",", hints);
--%>
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


	<%-- 時間表示  --%>
	<jsp:include page="./include/includeTime.jsp" />
	<p>---------------------------------------------------</p>

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


	<%-- 解答者に対する表示 --%>
	<form method="POST" action="./sendAnswer">
		<%
		if (playerIndex == answerPlayerIndex) {
		%>
		<p>ヒントから考えられるお題を記入し、決定してください！</p>
		<p>（制限時間は1分です）</p>
		<p>
			お題：<input type="text" name="answer">
		</p>
		<p>
			<input type="submit" value="答え決定">
		</p>
		<%
		}
		%>
	</form>

</body>
</html>