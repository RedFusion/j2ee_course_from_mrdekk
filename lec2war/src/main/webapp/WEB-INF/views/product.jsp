<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Информация о продукте</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Наименование</td>
			<td>Цена</td>
			<td>Количество</td>
			<td>Скидка</td>
			<td>Описание</td>
			<td>Категория</td>
		</tr>
		<tr align="center">
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td>${product.quantity}</td>
			<td>${product.discount}</td>
			<td>${product.description}</td>
			<td>${product.category.name}</td>
		</tr>
	</table>
</body>
</html>