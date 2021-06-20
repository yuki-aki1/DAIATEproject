<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String playerNamesList = request.getParameter("playerNamesList");
String[] playerNames = playerNamesList.split(",");
String playerIndex = request.getParameter("playerIndex");
String answerPlayerIndex = request.getParameter("answerPlayerIndex");
int numOfExecution;
%>

<p>「プレイヤー一覧」</p>

<%
for (int i = 0; i < playerNames.length; i++) {
	numOfExecution = 0;
%>

<p>
	プレイヤー名：<%=playerNames[i]%>

	<%
	if (i == 0) {
	%>
	<%=dislayParentheses(numOfExecution)%>
	ホスト
	<%
	numOfExecution++;
	}

	if (i == Integer.parseInt(playerIndex)) {
	%>
	<%=dislayParentheses(numOfExecution)%>
	<%=displayComma(numOfExecution)%>
	あなた
	<%
	numOfExecution++;
	}

	if (answerPlayerIndex != null) {
	if (i == Integer.parseInt(answerPlayerIndex)) {
	%>
	<%=dislayParentheses(numOfExecution)%>
	<%=displayComma(numOfExecution)%>
	解答者
	<%
	numOfExecution++;
	}
	}
	%>

	<%=dislayCloseParentheses(numOfExecution)%>
</p>
<%
}
%>



<%!String displayComma(int numOfExecution) {
		if (numOfExecution > 0) {
			return ",";
		} else {
			return "";
		}
	}%>

<%!String dislayParentheses(int numOfExecution) {
		if (numOfExecution == 0) {
			return "(";
		} else {
			return "";
		}
	}%>

<%!String dislayCloseParentheses(int numOfExecution) {
		if (numOfExecution > 0) {
			return ")";
		} else {
			return "";
		}
	}%>