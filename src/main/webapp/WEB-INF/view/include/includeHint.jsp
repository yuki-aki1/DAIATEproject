<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashSet,java.util.Set"%>
<%
String playerNamesList = request.getParameter("playerNamesList");
String[] playerNames = playerNamesList.split(",");
String playerIndex = request.getParameter("playerIndex");
String answerPlayerIndex = request.getParameter("answerPlayerIndex");
String hintsList = request.getParameter("hintsList");
String answer = request.getParameter("answer");
String[] hints = hintsList.split(",",-1);
%>

<p>「ヒント一覧」</p>

<%
for (int i = 0; i < playerNames.length; i++) {
	if (i != Integer.parseInt(answerPlayerIndex)) {
		%>
<p>
	プレイヤー名：<%=playerNames[i]%>
	<%
		if (isDup(hints,i) && playerIndex.equals(answerPlayerIndex) && answer != null) {
		%>
	×(ヒント被り)
	<%	
		}else {
		%>
	<%=hints[i]%>
	<%	
		}
		%>
</p>
<%	
	}
}
%>

<%!boolean isDup(String[] hints,int i) {
	boolean dup = false;
	for(int j=0;j<hints.length;j++){
		if(j==i){
			continue;
		}else if(hints[i].equals(hints[j])){
			dup= true;
			break;
		}
	}
	return dup;
	}%>