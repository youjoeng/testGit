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
	
	request.setCharacterEncoding("UTF-8");
	
	QueryBean.getConnection();
	
	String user_id = request.getParameter("user_id") == null ? "ccccc" :  request.getParameter("user_id");
 	String user_name = request.getParameter("user_name")  == null ? "" :  request.getParameter("user_name");
 	String user_phone = request.getParameter("user_phone")  == null ? "" :  request.getParameter("user_phone");
 	String user_grade = request.getParameter("user_grade")  == null ? "" :  request.getParameter("user_grade");
	
	//int result = QueryBean.insertUserInfo("ggc1", "강감찬", "010-1234-5678", 3);
	int result = QueryBean.insertUserInfo(user_id, user_name, user_phone, user_grade);
	
	out.println("{");
	out.println(" 		\"SUCCESS\":\""+ result +"\" ");
	out.println("}");

%>
	