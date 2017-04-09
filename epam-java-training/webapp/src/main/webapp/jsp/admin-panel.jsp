<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="jspf/admin_header.jspf"%>
<%@include file="jspf/admin_left_menu.jspf"%>

<%
String p = "jspf/admin_header.jspf";
 if(request.getParameter("command")!=null)
 { 
   p = "jspf/" + request.getParameter("command")+".jsp";%> 
   <jsp:include page="<%=p %>" />
<% }
%>    


<%@include file="jspf/admin_footer.jspf"%>
