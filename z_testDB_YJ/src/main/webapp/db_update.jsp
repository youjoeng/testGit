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
	
	String user_id = request.getParameter("user_id") == null ? "hkd" :  request.getParameter("user_id");
 	String user_name = request.getParameter("user_name")  == null ? "홍길자1111" :  request.getParameter("user_name");
 	String user_phone = request.getParameter("user_phone")  == null ? "017" :  request.getParameter("user_phone");
 	String user_grade = request.getParameter("user_grade")  == null ? "0" :  request.getParameter("user_grade");
	
	int result = QueryBean.updateUserInfo(user_id, user_name, user_phone, user_grade);
	
	out.println("{");
	out.println(" 		\"SUCCESS\":\""+ result +"\" ");
	out.println("}");

%>

