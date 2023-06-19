<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>

<%
	Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/shoppingmall"; //Database 이름은 campusdb 
		String id = "root";                     //MySQL에 접속을 위한 계정의 ID
		String pwd = "";            //MySQL에 접속을 위한 계정의 암호
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pwd);
%>