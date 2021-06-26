<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String roomId = (String) request.getAttribute("roomId");
String[] playerNames = (String[]) request.getAttribute("playerNames");
int playerIndex = (int) request.getAttribute("playerIndex");
int answerPlayerIndex = (int) request.getAttribute("answerPlayerIndex");
String subject = (String) request.getAttribute("subject");
String[] hints = (String[]) request.getAttribute("hints");
String answer = (String) request.getAttribute("answer");

String playerNamesList = String.join(",", playerNames);
String hintsList = String.join(",", hints);
%>
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
	<form method="POST" action="./leaveRoom">
		<input type="submit" value="退室">
	</form>
	<p>---------------------------------------------------</p>


	<%-- ヒント表示  --%>
	<jsp:include page="./include/includeHint.jsp">
		<jsp:param name="playerIndex" value="<%=playerIndex%>" />
		<jsp:param name="answerPlayerIndex" value="<%=answerPlayerIndex%>" />
		<jsp:param name="playerNamesList" value="<%=playerNamesList%>" />
		<jsp:param name="hintsList" value="<%=hintsList%>" />
	</jsp:include>
	<p>---------------------------------------------------</p>


	<%-- 正解、不正解の表示 --%>
		<%
		if (answer.equals(subject)) {
		%>
		<p>おめでとう！正解です！</p>
		<%
		}else {
		%>
		<p>残念！不正解です！</p>
		<%
		}
		%>
		
		
	<%-- 解答者の解答表示 --%>
	<p><%=playerNames[answerPlayerIndex]%>さんの解答：<%=answer%></p>
	
	<%-- お題（答え）の表示 --%>
	<p>お題は「<font color="red"><%=subject%></font>」でした！</p>
	
	
	<%-- NextGameボタン表示 --%>
	<form method="POST" action="./waitRoom">
		<input type="submit" value="次のゲームへ">
	</form>
	<p>---------------------------------------------------</p>

</body>
</html>