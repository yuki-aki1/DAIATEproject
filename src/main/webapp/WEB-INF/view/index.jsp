<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String[] errorTexts = (String[]) request.getAttribute("errorTexts");
%>
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
		<%-- エラーメッセージ表示 --%>
		<%
		if (errorTexts != null) {
			for (String errorText : errorTexts) {
		%>
		<font color="red"><%=errorText%></font><br>
		<%
		}
		}
		%>

		<p>
			<input type="submit" value="ルームを作る">
		</p>
	</form>

</body>
</html>