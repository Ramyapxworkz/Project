<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" integrity="..." crossorigin="anonymous" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<style >
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

* {
	box-sizing: border-box;
	margin: 0;
	padding: 10;
	font-family: Raleway, sans-serif;
}
/* Custom CSS styles */
body {
	background-color: #f8f9fa;
/*       display: flex;
 */    justify-content: center;
    align-items: center;
    height: 100vh; 
}
.container {
    max-width: 600px;
    margin: 30% ; /* Center the container horizontally */
    padding: 30px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
/* .container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
	width: 100%;
}
 */
.form-column {
	max-width: 500px;
	padding: 20px;
	border-radius: 10px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-column h2 {
	text-align: center;
}

button[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
}

button[type="submit"]:hover {
	background-color: #0056b3;
}
</style>

</head>
<body>
<nav class="navbar navbar-expand-lg " style="background-color: #e3f2fd;">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"> 
		<img class="rounded-circle"
			src="data:image/jpeg;base64,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"
			width="70" height="40" class="d-inline-block align-top" alt="Logo">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="signIn.jsp">SignIn</a></li>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="register.jsp">Register</a></li>
			</ul>
		</div> -->
	</div>
</nav>
<div type="container">
<form action="editprofile" enctype="multipart/form-data" method="post">
			<div class="form-column">
				<h2>Register Here</h2>
				<div class="mb-3">
					<h4>Personal Details</h4>
					<div class="row">
						<div class="col">
							<label for="ownerName" class="form-label">Owner Name</label> <input
								type="text" class="form-control" id="ownerName" name="ownerName" value="${read.ownerName}"
								onchange="onOwnerName()" required> <span
								id="errorOwnerName" style="color: red;"></span>
						</div>
						<div class="col">
							<label for="email" class="form-label">Email</label> <input
								type="email" class="form-control" id="signupEmail" name="email" value="${read.email}"
								readonly="readonly">
						</div>
					</div>
					<div class="row">
						<div class="col">
							<label for="contactNumber" class="form-label">Contact
								Number</label> <input type="tel" class="form-control" id="contactNumber"
								name="contactNumber" onchange="validateNumber()" value="${read.contactNumber }"required>
							<span id="numberError" style="color: red;"></span>
						</div>

						<div class="col">
							<label for="alternativeNumber" class="form-label">Alternate
								Number</label> <input type="tel" class="form-control"
								id="alternativeNumber" name="alternativeNumber"
								onchange="validateNumber()" value="${read.alternativeNumber}"> <span
								id="alternativeNumberError" style="color: red;"></span>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<h4>Business Details</h4>
					<div class="row">
						<div class="col">
							<label for="vendorName" class="form-label">Vendor Name</label> <input
								type="text" class="form-control" id="vendorName"
								name="vendorName" onchange="onVendorName()"  value="${read.vendorName}"required> <span
								id="errorVendorName" style="color: red;"></span>
						</div>

						<div class="col">
							<label for="gstNumber" class="form-label">GST Number</label> <input
								type="text" class="form-control" id="gstNumber" name="gstNumber"
								value="${read.gstNumber}" required>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<label for="startDate" class="form-label">Start Date</label> <input
								type="date" class="form-control" id="startDate" name="startDate"
								value="${read.startDate}" required>
						</div>
						<div class="col">
							<label for="website" class="form-label"> Website</label> <input
								type="text" class="form-control" id="website" name="website" value="${read.website}">
						</div>
						<div class="row">
							<div class="col">
								<label for="address" class="form-label">Address</label> <input
									type="text" class="form-control" id="address" name="adress" value="${read.adress}"
									required>
							</div>
							<div class="col">
								<label for="pincode" class="form-label">Pincode</label> <input
									type="text" class="form-control" id="pincode" name="pincode" value="${read.pincode}">
							</div>
						<div class="design">
				<label for="imageFile">Upload image</label> <input type="file"
					class="form-control" id="imageFile" name="imageFile" >
				<span id="imageError" style="color: red;"></span>
			</div> 

						</div>
					</div>
					<br>
					<button type="submit" class="btn btn-primary d-block mx-auto"
						id="button" >Register</button>
				</div>
		</form>

</div>

<script>

		 function onOwnerName() {
			var ownerName = document.getElementById("ownerName").value.trim();

			var isValidLength = ownerName.replace(/\s+/g, '').length >= 3;

			var isValidCharacters = /^[A-Za-z\s]+$/.test(ownerName);

			if (isValidLength && isValidCharacters) {
				document.getElementById("errorOwnerName").innerHTML = "";
				document.getElementById("button").disabled = false;
			} else {
				document.getElementById("errorOwnerName").innerHTML = "OwnerName must be at least 3 alphabetic characters";
				document.getElementById("button").disabled = true;
			}
		} 
		
		 function onVendorName() {
		var vendorName = document.getElementById("vendorName").value.trim();

		var isValidLength = vendorName.replace(/\s+/g, '').length >= 3;

		var isValidCharacters = /^[A-Za-z\s]+$/.test(vendorName);

		if (isValidLength && isValidCharacters) {
			document.getElementById("errorVendorName").innerHTML = "";
			document.getElementById("button").disabled = false;
		} else {
			document.getElementById("errorVendorName").innerHTML = "VendorName must be at least 3 alphabetic characters";
			document.getElementById("button").disabled = true;
		}
	} 

		function validateNumber() {
			var number = document.getElementById("contactNumber").value;
			var alternativeNumber = document
					.getElementById("alternativeNumber").value;
			var numberRegex = /^[0-9]{10}$/;
			var button = document.getElementById("button");

			if (number.trim() === "" && alternativeNumber.trim() === "") {
				document.getElementById("numberError").innerHTML = "";
				document.getElementById("alternativeNumberError").innerHTML = "";
				button.disabled = true;
				return;
			}

			if (numberRegex.test(number)) {
				document.getElementById("numberError").innerHTML = "";
				if (number !== alternativeNumber) {
					if (alternativeNumber.trim() !== "") {
						if (numberRegex.test(alternativeNumber)) {
							document.getElementById("alternativeNumberError").innerHTML = "";
							button.disabled = false;
						} else {
							document.getElementById("alternativeNumberError").innerHTML = "Enter correct alternative number";
							button.disabled = true;
						}
					} else {
						document.getElementById("alternativeNumberError").innerHTML = "";
						button.disabled = false;
					}
				} else {
					document.getElementById("alternativeNumberError").innerHTML = "Contact number and alternative should not be the same";
					button.disabled = true;
				}
			} else {
				document.getElementById("numberError").innerHTML = "Contact Number Must be 10 digits";
				button.disabled = true;
			}
		}
	</script>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>