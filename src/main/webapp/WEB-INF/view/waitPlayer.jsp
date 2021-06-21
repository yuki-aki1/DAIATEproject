<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String[] playerNames = (String[])request.getAttribute("playerNames"); %>
<% String roomId  = (String)request.getAttribute("roomId"); %>
<% int playerIndex = ((Integer)(request.getAttribute("playerIndex"))).intValue(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム待機画面</title>
</head>
<body>

	<%
	
	if (roomId != null) { 
		out.print("<p>ルームID:" + roomId + "<p>");
	}
	
	if (playerNames != null) {
		for (int i = 0; i < playerNames.length; i++) {
			out.print("<p>プレイヤー名:" + playerNames[i]);
			if (i == 0) {
				out.print("(ホスト)");
			}
			if (i == playerIndex) {
				out.print("(あなた)");
			}
			out.print("<p>");
		}
	}
	
	%>

</body>
</html>