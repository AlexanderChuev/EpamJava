<%@page import="com.chuyeu.training.myapp.services.IAttributeService"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="jspf/admin_left_menu.jspf"%>
	<%
		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");

		IAttributeService service = contex.getBean(IAttributeService.class);
		List<String> list = service.getNames();
	%>

	<div class="select_attribute_wrapper">
		<h2>Добавить значение атрибуту</h2>
		<div class="select_attribute">
			<label> Attribute Name </label> <select>
				<option>Select Attribute</option>
				<%
					for (String attriute : list) {
				%>
				<option><%=attriute%></option>
				<%
					}
				%>

			</select> <br> <label> Attribute Name </label>
			<form name="add_value" method="POST" action="attribute">
				<input type="text" name="value" value="" /> <br />
				<p>
					<input type="submit" value="Добавить">
				</p>
			</form>
		</div>
	</div>
</body>
</html>
