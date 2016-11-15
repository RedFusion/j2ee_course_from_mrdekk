<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Информация по заказу</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>id</td>
			<td>Клиент</td>
		</tr>
		<tr align="center">
			<td>${order.id}</td>
			<td>${order.customer.name}</td>
		</tr>
	</table>
</body>
</html>