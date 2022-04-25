<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean" />
<jsp:setProperty property="*" name="QueryBean" />

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	request.setCharacterEncoding("UTF-8");
	
	QueryBean.getConnection();
	
	String user_id = request.getParameter("user_id") == null ? "hkd1" :  request.getParameter("user_id");
	
	int result = QueryBean.deleteUserInfo(user_id);
	
	out.println("{");
	out.println(" 		\"SUCCESS\":\""+ result +"\" ");
	out.println("}");

%>

