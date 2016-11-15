<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Каталог товаров на складе</title>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>
	Данные склада:
	<br /> Идентификатор: ${warehouse.id}
	<br /> Название: ${warehouse.name}
	<br /> Вместимость: ${warehouse.capacity}
	<br />

	<table border="1">
		<tr>
			<td>Наименование</td>
			<td>Цена</td>
			<td>Количество</td>
			<td>Скидка</td>
			<td>Описание</td>
			<td>Категория</td>
		</tr>
		<c:forEach items="${products}" var="product" varStatus="status">
			<tr align="center">
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.quantity}</td>
				<td>${product.discount}</td>
				<td>${product.description}</td>
				<td>${product.category.name}</td>
			</tr>
		</c:forEach>
	</table>
	<br />

	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<ul data-bind="foreach: warehouses">
					<li><a href="#"	data-bind="text: name, click: $root.viewWarehouse"></a></li>
				</ul>
			</div>
				
			<div class="col-lg-9">
				<select data-bind="options: $root.warehouses, optionsText: 'id'"></select>
			
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Наименование</th>
							<th>Цена</th>
							<th>Количество</th>
							<th>Скидка</th>
							<th>Описание</th>
							<th>Категория</th>
						</tr>
					</thead>
					<tbody data-bind="foreach: products">
						<tr>
							<td data-bind="text: ($index() + 1)"></td>
							<td data-bind="text: name"></td>
							<td data-bind="text: price"></td>
							<td data-bind="text: quantity"></td>
							<td data-bind="text: discount"></td>
							<td data-bind="text: description"></td>
							
							<td data-bind="text: category"></td>
						</tr>
					</tbody>
				</table>

				<form data-bind="submit: addProduct">
					<input data-bind="value: newName" placeholder="name" />
					<input data-bind="value: newPrice" placeholder="price" />
					<input data-bind="value: newQuantity" placeholder="quantity" />
					<input data-bind="value: newDiscount" placeholder="discount" />  
					<input data-bind="value: newDescription" placeholder="description" /> <br/> <br/>
	Выбор категории <select data-bind="options: $root.categories, optionsText: 'name', value: CategoryId"></select>
					
					<button type="submit">Add</button> <br /> <br />
					<button data-bind="click: save">Save</button> <br/>
				</form>
				<h3 data-bind="visible: total() > 0">
    				Total quantity: <span data-bind="text: total().toFixed(2)"></span>
				</h3>
			</div>
		</div>
	</div>
	<!-- /.container -->
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script	src="${pageContext.request.contextPath}/resources/js/knockout-3.4.0.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

	<div style="display: none;">
		<div id="baseUrl">${pageContext.request.contextPath}</div>
	</div>
</body>
</html>