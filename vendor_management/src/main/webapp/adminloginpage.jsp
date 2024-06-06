<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>

<!-- <style>
body {
	padding-top: 70px;
}

.input-container {
	display: inline-block;
	margin-right: 20px; /* Adjust margin as needed */
}

.input-style {
	border: 1px solid #ccc;
	padding: 5px;
	border-radius: 5px; /* Optional: adds rounded corners */
	box-sizing: border-box;
	/* Optional: includes padding and border in element's total width and height */
}

.label-style {
	font-weight: bold;
}
</style> -->
<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: Raleway, sans-serif;
}

.login-container {
	max-width: 400px;
	margin: auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 20px;
}

.login-btn {
	width: 100%;
}

body {
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

footer {
	position: fixed;
	bottom: 0;
	width: 100%;
}

.footer-btn {
	color: white;
}

.footer-btn:hover {
	text-decoration: none;
}

.btn-floating {
	border-radius: 50%;
}

.btn-floating i {
	vertical-align: middle;
}

.btn-floating.m-1 {
	margin: 0 0.5rem;
}
</style>
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg "
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img class="rounded-circle"
				src="data:image/jpeg;base64,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"
				width="70" height="40" class="d-inline-block align-top" alt="Logo">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

		</div>
	</nav>

	<div class="container mt-5">
		<button class="btn btn-primary mb-3" id="viewProductBtn">View
			Product</button>
		<button class="btn btn-primary mb-3" id="viewVendorBtn">View
			Vendor</button>
			<button class="btn btn-primary mb-3" id="viewOrderBtn">View
			Order</button>
			
	</div>
	
	<div class="modal fade custom-width-modal" id="viewOrderModal"
		tabindex="-1" role="dialog" aria-labelledby="viewOrderModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-center" id="viewOrderModalLabel">Order
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeModals()" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Order Details -->
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong>ID:</strong> <span id="id"></span>
							</p>
							<p>
								<strong>Product Name:</strong> <span id="productName"></span>
							</p>
							<p>
								<strong>Product Price:</strong> <span id="productPrice"></span>
							</p>
							<p>
								<strong>Delivery Charge:</strong> <span id="deliveryCharge"></span>
							</p>
							<p>
								<strong>Description About Product:</strong> <span
									id="descriptionAboutProduct"></span>
							</p>
							<p>
								<strong>Order Quantity:</strong> <span id="orderQuantity"></span>
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong>Order Date:</strong> <span id="orderDate"></span>
							</p>
							<p>
								<strong>Delivery Date:</strong> <span id="delivaryDate"></span>
							</p>
							<p>
								<strong>Delivery Address:</strong> <span id="deliveryAddress"></span>
							</p>
							<p>
								<strong>Message:</strong> <span id="message"></span>
							</p>
						</div>
					</div>
					<strong>Order status:</strong>
					<form>
						<input type="hidden" id="orderId" name="id"> <select
							class="form-control" id="orderStatusSelect" name="status"
							required>
							<option value="">Select Availability</option>
							<option value="Received">Received</option>
							<option value="Can't_Received">Can't_Received</option>
						</select>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						onclick="closeModals()" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="updateOrderStatus()">Edit</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- view vendor detailes table--------------------------------------  -->
	<!------------------------------- User details table-------------------------------- -->
		<div id="tableContainer">
	
	<div class="table-responsive" id="userDetailsTableContainer"
		style="display: none;">
		<div class="row mb-3">
			<div class="col">
				<h3 id="vendorTable">Vendor Details</h3>
			</div>
			<div class="col-3">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search..."
						aria-label="Search" id="searchInput">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button"
							id="searchButton">Search</button>
					</div>
				</div>
			</div>
		</div>
		<table id="userDetailsTable"
			class="table table-bordered table-hover table-fixed">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact Number</th>
					<th>AltContact Number</th>
					<th>vendorName</th>
					<th>GSTNumber</th>
					<th>StartDate</th>
					<th>WebSite</th>
					<th>Address</th>
					<th>PinCode</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<!-- Table body content goes here -->
			</tbody>
		</table>
	</div>
	
	<!------------------------------view product table  ------------------------------------------->
	<div class="table-responsive" id="productDetailsTableContainer"
		style="display: none;">
		<div class="row mb-3">
			<div class="col">
				<h3 id="productTable">All Product Details</h3>
			</div>
			<div class="col-3">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search..."
						aria-label="Search" id="searchInput">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button"
							id="searchButton">Search</button>
					</div>
				</div>
			</div>
		</div>
		<table id="productDetailsTable"
			class="table table-bordered table-hover table-fixed">
			<!-- Table headers -->
			<thead class="thead-dark top-fixed">
				<tr>
					<th>SlNo</th>
					<th>ProductID</th>
					<th>VendorName</th>
					<th><select id="categoryFilter">
							<option value="">All Categories</option>
							<option value="grocery">Grocery</option>
							<option value="electronics">Electronics</option>
							<option value="cloths">Cloth's</option>
							<option value="home_furnitures">Home and Furniture's</option>
							<option value="cosmetics">Cosmetics</option>
							<option value="appliances">Appliances</option>
					</select></th>
					<th>ProductName</th>
					<th>ProductPrice</th>
					<th>DeliveryCharge</th>
					<th>DescriptionAboutProduct</th>
					<th>Available</th>
					<!-- <th>orderStatus</th> -->
					<th>Action</th>
					<th>vendor</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	
	<div class="table-responsive" id="orderDetailsTableContainer"
			style="display: none;">
			<table class="table" id="orderDetailsTable">
				<thead>
					<tr>
						<th>id</th>
						<th>productName</th>
						<th>productPrice</th>
						<th>deliveryCharge</th>
						<th>descriptionAboutProduct</th>
						<th>orderQuantity</th>
						<th>orderDate</th>
						<th>deliveryDate</th>
						<th>deliveryAddress</th>
						<th>message</th>
						<th>orderStatus</th>
						<th>Edit</th>

					</tr>
				</thead>
				<tbody>
					<!-- Table body content goes here -->
				</tbody>
			</table>
		</div>
	
	
	</div>
	
	
	
	
	<script>
    $(document).ready(function() {
        $("#viewOrderBtn").click(function() {
            var email = $('#email').val();
            // Open the modal
            $("#productDetailsTableContainer").hide();
            $("#userDetailsTableContainer").hide();
            $("#orderDetailsTableContainer").toggle();

            // Fetch order details using AJAX
            if ($("#orderDetailsTableContainer").is(":visible")) {
                $.ajax({
                    url: "orderHistory", // URL to your server-side endpoint
                    type: "GET",
                    data: { email: email },
                    success: function(orderDetails) {
                        // Clear existing table rows
                        $("#orderDetailsTable tbody").empty();

                        // Populate table with fetched data
                        $.each(orderDetails, function(index, order) {
                            var row = "<tr>" +
                                "<td>" + order.id + "</td>" +
                                "<td>" + order.productName + "</td>" +
                                "<td>" + order.productPrice + "</td>" +
                                "<td>" + order.deliveryCharge + "</td>" +
                                "<td>" + order.descriptionAboutProduct + "</td>" +
                                "<td>" + order.orderQuantity + "</td>" +
                                "<td>" + order.orderDate + "</td>" +
                                "<td>" + order.delivaryDate + "</td>" +
                                "<td>" + order.deliveryAddress + "</td>" +
                                "<td>" + order.message + "</td>" +
                                "<td>" + order.orderStatus + "</td>" +
                                "<td><button class='btn btn-primary btn-sm' onclick='editOrder(" + JSON.stringify(order).replace(/"/g, "&quot;") + ")'><i class='fas fa-eye'></i> Edit</button></td>" +
                                "</tr>";
                            $("#orderDetailsTable tbody").append(row);
                        });
                    },
                    error: function(xhr, status, error) {
                        console.error("Error fetching order details:", error);
                        // You can handle errors here, e.g., displaying an error message
                    }
                });
            }
        });
    });

    function editOrder(order) {
        $('#viewOrderModal').modal('show');
        $('#viewOrderModal #id').text(order.id);
        $('#viewOrderModal #orderId').val(order.id);
        $('#viewOrderModal #productName').text(order.productName);
        $('#viewOrderModal #productPrice').text(order.productPrice);
        $('#viewOrderModal #delivaryDate').text(order.delivaryDate);
        $('#viewOrderModal #deliveryCharge').text(order.deliveryCharge);
        $('#viewOrderModal #descriptionAboutProduct').text(order.descriptionAboutProduct);
        $('#viewOrderModal #orderQuantity').text(order.orderQuantity);
        $('#viewOrderModal #orderDate').text(order.orderDate);
        $('#viewOrderModal #message').text(order.message);
        $('#viewOrderModal #deliveryAddress').text(order.deliveryAddress);
    }

    function updateOrderStatus() {
        var orderId = $("#orderId").val();
        var status = $("#orderStatusSelect").val();

        // Check if orderId and status are not empty
        if (orderId && status) {
            // Send the AJAX request
            $.ajax({
                url: "updateOrderStatusinAdmin",
                type: "POST",
                data: { id: orderId, status: status },
                success: function(response) {
                    if (response === 'Order status updated successfully') {
                        $('#viewOrderModal').modal('hide');
                        $('#orderSuccessModal').modal('show');
                        setTimeout(function() {
                            $('#orderSuccessModal').modal('hide');
                        }, 3000);
                    } else {
                        $('#viewOrderModal').modal('hide');
                        $('#orderErrorModal').modal('show');
                        setTimeout(function() {
                            $('#orderErrorModal').modal('hide');
                        }, 3000);
                    }
                },
                error: function(xhr, status, error) {
                    $('#viewOrderModal').modal('hide');
                    $('#orderErrorModal').modal('show');
                    setTimeout(function() {
                        $('#orderErrorModal').modal('hide');
                    }, 3000);
                }
            });
        } else {
            console.error("Order ID and status are required");
        }
    }
</script>


	<!-- --------------------------------view vendor Modal----------------------- -->
	<div class="modal fade custom-width-modal " id="editUserModal"
		tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-center" id="editUserModalLabel">
						vendor Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h3 class="text-center">Personal Details</h3>
					<div class="row">
						<!-- Personal Details -->
						<div class="col-md-8">
							<p>
								<strong>Vendor ID:</strong> <span id="vendorId"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-user"></i>Owner Name:</strong> <span
									id="ownerName"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-envelope"></i>Email:</strong> <span
									id="email"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-phone"></i>Contact
									Number:</strong> <span id="contactNumber"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-phone"></i>Alternate
									Contact Number:</strong> <span id="altContactNumber"></span>
							</p>
						</div>

						<!-- User Photo -->
						<div class="col-md-4 text-center">
							<div class="round-image mb-3"
								style="height: 100px; width: 100px; overflow: hidden; border-radius: 50%;">
								<img id="userPhoto"
									src="http://localhost:8081/VendorManagement/admin/display?email="
									alt="User Photo" style="width: 100%; height: auto;">
							</div>
						</div>
					</div>
					<h3 class="text-center">Business Details</h3>
					<div class="row justify-content-center">
						<div class="col-md-6">
							<p>
								<strong><i class="fa-solid fa-user"></i>Vendor Name:</strong> <span
									id="vendorName"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-pencil"></i>GST Number:</strong> <span
									id="GSTNumber"></span>
							</p>
							<p>
								<strong><i class="fa-solid fa-date"></i>Start Date:</strong> <span
									id="startDate"></span>
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong><i class="fa-solid fa-globe"></i>WebSite:</strong> <span
									id="website"></span>
							</p>
							<p>
								<strong><i class="fa-regular fa-building"></i>Address:</strong>
								<span id="address"></span>
							</p>
							<p>
								<strong><i class="fa-regular fa-building"></i>Pin Code:</strong>
								<span id="pincode"></span>
							</p>
						</div>
					</div>

					<p>
						<strong>vendor status:</strong> <input type="hidden" id="vendorId">
						<input type="hidden" id="vendorEmail"> <select
							class="form-control" id="VendorStatus" name="status" required>
							<option value="">Select Availability</option>
							<option value="Pending">Pending</option>
							<option value="Approved">Approved</option>
						</select>
					</p>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button  onclick="saveVendorStatus()" type="button" class="btn btn-primary">Save
						Changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-----------------------------------------  -->
	<!-- Vendor Success Modal -->
	<div class="modal fade" id="vendorSuccessModal" tabindex="-1"
		role="dialog" aria-labelledby="vendorSuccessModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-success text-white">
				<div class="modal-body">Vendor status updated successfully.</div>
			</div>
		</div>
	</div>

	<!-- Vendor Error Modal -->
	<div class="modal fade" id="vendorErrorModal" tabindex="-1"
		role="dialog" aria-labelledby="vendorErrorModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-danger text-white">
				<div class="modal-body">An error occurred while updating
					vendor status.</div>
			</div>
		</div>
	</div>
	<!---------------------------------------Order Product Modal------------------------------------------------>
	<div class="modal fade" id="OrderProductModal" tabindex="-1"
		role="dialog" aria-labelledby="OrderProductModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="OrderProductModalLabel">
						<i class="fas fa-info-circle"></i> Product Details
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<strong><i class="fas fa-user"></i> Vendor Name:</strong><span
								id="PvendorName"></span>
						</div>
						<div class="col-md-6">
							<strong><i class="fas fa-cube"></i> Product ID:</strong><span
								id="ProductId"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><i class="fas fa-list"></i> Category:</strong> <span
								id="category"></span>
						</div>
						<div class="col-md-6">
							<strong><i class="fas fa-shopping-bag"></i> Product
								Name:</strong> <span id="productName"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><i class="fas fa-dollar-sign"></i> Product
								Price:</strong> <span id="productPrice"></span>
						</div>
						<div class="col-md-6">
							<strong><i class="fas fa-truck"></i> Delivery Charge:</strong> <span
								id="deliveryCharge"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><i class="fas fa-check-circle"></i> Available:</strong> <span
								id="available"></span>
						</div>
						<div class="col-md-6">
							<strong><i class="fas fa-info-circle"></i> Description
								About Product:</strong> <span id="descriptionAboutProduct"></span>
						</div>
					</div>
					<fieldset>
						<legend>
							<i class="fas fa-tasks"></i> Fill Order Details
						</legend>
						<form id="OrderProductForm">
							<input type="hidden" id="orderProductId" name="productId">
							<input type="hidden" id="ordervendorId" name="vendorId">
							<div id="orderFields">
								<label for="orderStatus"><i class="fas fa-check-circle"></i>
									Order Status:</label> <input type="text" id="orderStatus"
									class="form-control" name="orderStatus" value="Order"
									readonly="readonly"> <label for="quantity"><i
									class="fas fa-sort-numeric-up"></i> Quantity:</label> <input
									type="text" id="quantity" name="orderQuantity"
									class="form-control"> <label for="deliveryDate"><i
									class="far fa-calendar-alt"></i> Delivery Date:</label> <input
									type="date" id="deliveryDate" name="delivaryDate"
									class="form-control"> <label for="deliveryAddress"><i
									class="fas fa-map-marker-alt"></i> Delivery Address:</label> <input
									type="text" id="deliveryAddress" name="deliveryAddress"
									class="form-control"> <label for="message"><i
									class="far fa-comment"></i> Message:</label> <input type="text"
									id="message" class="form-control" name="message">
							</div>
						</form>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="submitOrderBtn" type="button" class="btn btn-primary">
						<i class="fas fa-save"></i> Submit
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- ------------------------------------------------------------------- -->
	<!-- Order Success Modal -->
	<div class="modal fade" id="orderSuccessModal" tabindex="-1"
		role="dialog" aria-labelledby="orderSuccessModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-success text-white">
				<div class="modal-body">Order placed successfully.</div>
			</div>
		</div>
	</div>

	<!-- Order Error Modal -->
	<div class="modal fade" id="orderErrorModal" tabindex="-1"
		role="dialog" aria-labelledby="orderErrorModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-danger text-white">
				<div class="modal-body">An error occurred while placing the
					order.</div>
			</div>
		</div>
	</div>
	<!-------------------------------- this API is getting all vendor List and save vendor status------------------>
	<script>
		// Define a function to fetch vendors data
		function getVendors() {
			$("#productDetailsTableContainer").hide();
			$("#orderDetailsTableContainer").hide();
			$("#userDetailsTableContainer").toggle();
			
			if ($("#userDetailsTableContainer").is(":visible")) {
				$
						.ajax({
							url : "viewVendorDetails",
							type : "GET",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$("#userDetailsTable tbody").empty();
								$
										.each(
												data,
												function(index, item) {
													var row = "<tr>" + "<td>"
															+ item.id
															+ "</td>"
															+ "<td><img src='display?imagePath="
															+ item.imagePath
															+ "' alt='Profile Picture' width='50' height='50'></td>"
															+ "<td>"
															+ item.ownerName
															+ "</td>"
															+ "<td class='edit-email'>"
															+ item.email
															+ "</td>"
															+ "<td>"
															+ item.contactNumber
															+ "</td>"
															+ "<td>"
															+ item.alternativeNumber
															+ "</td>"
															+ "<td>"
															+ item.vendorName
															+ "</td>"
															+ "<td>"
															+ item.gstNumber
															+ "</td>"
															+ "<td>"
															+ item.startDate
															+ "</td>"
															+ "<td>"
															+ item.website
															+ "</td>"
															+ "<td>"
															+ item.adress
															+ "</td>"
															+ "<td>"
															+ item.pincode
															+ "</td>"
															+ "<td>"
															+ item.status
															+ "</td>"
															+ "<td><button class='btn btn-primary btn-sm' onclick='editVendor("
															+ JSON
																	.stringify(item)
															+ ")'><i class='fas fa-eye'></i>view</button></td>"
															+ "</tr>";
													$("#userDetailsTable tbody")
															.append(row);
												});
							},
							error : function(xhr, status, error) {
								alert("Failed to fetch user data from server.");
								console.error(error);
							}
						});
			}
		}

		// Click event handler for #viewButton
		$("#viewVendorBtn").click(function() {
			getVendors();
		});

		function editVendor(vendor) {
			console.log(vendor);
			$('#editUserModal').modal('show');
			$('#vendorId').val(vendor.id);
			$('#vendorId').text(vendor.id);
			$('#ownerName').text(vendor.ownerName);
			$('#vendorEmail').text(vendor.email);
			$('#editUserModal #email').text(vendor.email);
			var userEmail = vendor.email;
			$('#userPhoto').attr(
					'src',
					'http://localhost:8081/VendorManagement/admin/display?imagePath='
							+ vendor.imagePath);
			$('#contactNumber').text(vendor.contactNumber);
			$('#altContactNumber').text(vendor.alternativeNumber);
			$('#vendorName').text(vendor.vendorName);
			$('#GSTNumber').text(vendor.gstNumber);
			$('#startDate').text(vendor.startDate);
			$('#website').text(vendor.website);
			$('#address').text(vendor.adress);
			$('#pincode').text(vendor.pincode);
			$('#VendorStatus').val(vendor.status);
		}
	</script>
	
	<script>

	function saveVendorStatus() {
	    var vendorId = $('#vendorId').val();
	    var vendorEmail = $('#vendorEmail').val();
	    var status = $('#VendorStatus').val();
	    console.log('saveVendorStatus');

	    $.ajax({
	        type: 'POST',
	        url: 'updateVendorStatus', // Add comma here
	        data: {
	            id: vendorId,
	            vendorEmail: vendorEmail,
	            status: status
	        },
	        success: function(response) {
	            if (response.includes("updateVendorStatusSuccessfully")) {
	                console.log('Vendor status updated successfully');
	                $('#editUserModal').modal('hide');
	                getVendors();
	                // Show success modal
	                $('#vendorSuccessModal').modal('show');
	                // Hide success modal after 3 seconds
	                setTimeout(function() {
	                    $('#vendorSuccessModal').modal('hide');
	                }, 3000);
	            } else {
	                console.log('Vendor status update failed');
	                $('#editUserModal').modal('hide');
	                $('#vendorErrorModal').modal('show');
	                setTimeout(function() {
	                    $('#vendorErrorModal').modal('hide');
	                }, 3000);
	            }
	        },
	        error: function(xhr, status, error) {
	            const errorMessage = "An error occurred while updating vendor status: " + error;
	            $('#vendorErrorMessage').text(errorMessage);
	            $('#editUserModal').modal('hide');
	            // Show error modal
	            $('#vendorErrorModal').modal('show');
	            // Hide error modal after 3 seconds
	            setTimeout(function() {
	                $('#vendorErrorModal').modal('hide');
	            }, 3000);
	        }
	    });
	}


 
 	</script>


	<script>
	$(document).ready(function() {
	    $("#viewProductBtn").click(function() {
	        $("#userDetailsTableContainer").hide();
	        
	        $("#orderDetailsTableContainer").hide();
	        $("#productDetailsTableContainer").toggle();
	        
	        if ($("#productDetailsTableContainer").is(":visible")) {
	            $.ajax({
	                url: "viewproduct",
	                type: "GET",
	                dataType: "json",
	                success: function(data) {
	                    $("#productDetailsTable tbody").empty();
	                    $.each(data, function(index, item) {
	                        var slno = index + 1;
	                        var row = "<tr>" +
	                            "<td>" + slno + "</td>" +
	                            "<td>" + item.id + "</td>" +
	                            "<td>" + (item.vendor ? item.vendor.vendorName : "") + "</td>" +
	                            "<td>" + item.category + "</td>" +
	                            "<td>" + item.productName + "</td>" +
	                            "<td>" + item.productPrice + "</td>" +
	                            "<td>" + item.deliveryCharge + "</td>" +
	                            "<td>" + item.description + "</td>" +
	                            "<td>" + item.available + "</td>" +
	                            "<td><button class='btn btn-warning btn-sm' onclick='orderProduct(" + JSON.stringify(item) + ")'><i class='fas fa-shopping-cart'></i> Order</button></td>" +
	                            "<td><button class='btn btn-primary btn-sm' onclick='editVendor(" + JSON.stringify(item.vendor) + ")'><i class='fas fa-eye'></i>Vendor</button></td>" +
	                            "</tr>";
	                        $("#productDetailsTable tbody").append(row);
	                    });
	                },
	                error: function(xhr, status, error) {
	                    console.error("Failed to fetch product data from server.", error);
	                    alert("Failed to fetch product data from server.");
	                }
	            });
	        }
	    });
	});

		function orderProduct(product) {
			console.log(product);
			$('#OrderProductModal').modal('show');
			$('#orderProductId').val(product.id);
			$('#ordervendorId').val(product.vendor.id);
			$('#ProductId').text(product.id);
			$('#PvendorName').text(product.vendor.vendorName);
			$('#category').text(product.category);
			$('#productName').text(product.productName);
			$('#productPrice').text(product.productPrice);
			$('#available').text(product.available);
			$('#deliveryCharge').text(product.deliveryCharge);
			$('#descriptionAboutProduct').text(product.description);
			$('#editProductModal #available').text(product.available);
		}
		
		$(document).ready(function() {
		    // Function to handle form submission
		    $('#submitOrderBtn').click(function() {
		        // Serialize form data
		        var formData = $('#OrderProductForm').serialize();
		        console.log("this====================")
		        
		        // AJAX request
		        $.ajax({
		            type: 'POST',
		            url: 'saveorderDetails', // Replace this with the actual URL of your backend endpoint
		            data: formData,
		            success: function(response) {
		                // Handle success response
		                console.log('Order submitted successfully:', response);
		                
		                // Close the modal
		                $('#OrderProductModal').modal('hide');
		                
		                // Optionally, do something after successful submission
		            },
		            error: function(xhr, status, error) {
		                // Handle error response
		                console.error('Error submitting order:', error);
		                
		                // Optionally, show an error message to the user
		            }
		        });
		    });
		});

		
	</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>















