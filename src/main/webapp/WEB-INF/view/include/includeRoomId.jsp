<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<p>
	ルームID:

	<%
String roomId = request.getParameter("roomId");
%>

	<%=roomId%>
</p>