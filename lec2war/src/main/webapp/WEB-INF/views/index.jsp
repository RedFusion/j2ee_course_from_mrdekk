<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Интернет-витрина</title>
</head>
<body>
	     <a href="/mart-parent/warehouse?id=1">Информация о товарах на складе</a>
	<br> <a href="/mart-parent/product?id=1">Информация по продукту</a>
	<br> <a href="/mart-parent/order?id=2">Информация о заказе</a> <br />
	
	<span>Input id warehouse: </span><input id="id" name="id" value="2" /><br />
		<p id="rstr" >&lt;no data&gt;</p><br />
		<input id="subm" type="submit" name="Do" value="Get Warehouse info" />
		
		<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.min.js" ></script>
		<script type="text/javascript" >
			$( document ).ready( function( )
			{
				$( "#subm" ).click( function( )
				{
					$.ajax(
					{
						url: "index",
						type: "POST",
						data: JSON.stringify( { id: parseInt($('#id').val(), 10) }),
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(data) {
							if (data.status == 'ok') {
								$("#rstr").html(data.warehouse.id + " " + data.warehouse.name + " " 
										+ data.warehouse.capacity);
							} else {
								$("#rstr").html("&lt;Server Error&gt;");
							}
						}
					});
				});
			});
		</script>
		
		
</body>
</html>