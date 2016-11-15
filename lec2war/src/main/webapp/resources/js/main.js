$(document).ready(
		function() {
			var baseUrl = $("#baseUrl").text();

			function Category(data)
			{
				this.id = ko.observable(data.id);
				this.name = ko.observable(data.name);
			}
			
			function Warehouse(data) 
			{
				this.id = ko.observable(data.id);
				this.name = ko.observable(data.name);
				this.capacity = ko.observable(data.capacity);
			}

			function Product(data) 
			{
				this.id = ko.observable(data.id);
				this.name = ko.observable(data.name);
				this.price = ko.observable(data.price);
				this.quantity = ko.observable(data.quantity);
				this.discount = ko.observable(data.discount);
				this.description = ko.observable(data.description);
				
				this.category = ko.observable(data.category);
			}

			function Controller() 
			{
				var self = this;

				self.warehouses = ko.observableArray([]);
				self.products = ko.observableArray([]);
				self.categories = ko.observableArray([]);
				
				self.newName = ko.observable();
				self.newPrice = ko.observable();
				self.newQuantity = ko.observable();
				self.newDiscount = ko.observable();
				self.newDescription = ko.observable();
				
				self.CategoryId = ko.observable();
				
				// Computed data
				self.total = ko.computed(function() 
				{
					var total = 0;
					for (var i = 0; i < self.products().length; i++)
					     total += parseFloat(self.products()[i].quantity());
					return total;
				});  
			    
				// Operations
			    self.addProduct = function() 
			    {
			        self.products.push(new Product(
			        { 
			        	name: this.newName(),
			        	price: this.newPrice(),
			        	quantity: this.newQuantity(),
			        	discount: this.newDiscount(),
			        	description: this.newDescription()
			        }));
			    };
			    
			    self.save = function()
			    {
			    	$.ajax(baseUrl + "/rest/warehouse/" + warehouse.id() + "/products", 
			    	{
			    		data: ko.toJSON({ products: self.products }),
			    		type: "post", contentType: "application/json",
			    		success: function () { alert("saved"); },
			            error: function () { alert("no worky"); }
			    	});
			    };

			    $.getJSON(baseUrl + "/rest/categories", function(response)
			    {
			    	var mapped = $.map(response.categories, function(item)
			    	{
			    		return new Category(item)
			    	});
			    	self.categories(mapped);	
			    });		
			    
			    
				$.getJSON(baseUrl + "/rest/warehouse", function(response) 
				{
					var mapped = $.map(response.warehouses, function(item) 
					{
						return new Warehouse(item)
					});
					self.warehouses(mapped);
				});

				self.viewWarehouse = function(warehouse) 
				{
					$.getJSON(baseUrl + "/rest/warehouse/" + warehouse.id()	+ "/products", function(response) 
					{
						var mapped = $.map(response.products, function(item) 
						{
							return new Product(item)
						});
						self.products(mapped);
					});
				}
			}
			
			ko.applyBindings(new Controller());
		});