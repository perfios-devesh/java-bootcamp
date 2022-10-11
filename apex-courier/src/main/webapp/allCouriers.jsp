<%@page import="com.trial.CourierDetailsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trial.CourierDetails"%>
<%@page import="com.trial.Costing"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="com.trial.Utils"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Apex Courier</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<style>
.col-4, .col-11, .col-8 {
	margin-bottom: 15px;
	text-align: left;
}

input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}


th, td {
  padding: 15px;
  }
  
  a{
  	color: grey;
  }
</style>
</head>

<body style="background-color: black; color: white">
	<div style="width: 80%; margin: auto; margin-top: 10px">
		<div id="nav-placeholder"></div>

		<div style="text-align: center; width: 100%" id="main">
			

			<table style="width: 100%;margin-top: 25px;">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Mobile Number</th>
					<th>From City</th>
					<th>To City</th>
					<th>Weight in Grams</th>
					<th>Cost</th>
					<th>Shipment Date</th>
					<th>Delivery Date</th>
					<th>Status</th>
				</tr>
				
				<%
			List<CourierDetails> list = CourierDetailsDao.getAllCourierDetails();
				for (CourierDetails courierDetails : list) {
					%>
					<tr>
					<td><%=courierDetails.getId() %></td>
					<td><%=courierDetails.getName() %></td>
					<td><%=courierDetails.getMobile() %></td>
					<td><%=courierDetails.getFromCity() %></td>
					<td><%=courierDetails.getToCity() %></td>
					<td><%=courierDetails.getWeight() %></td>
					<td><%=courierDetails.getCost() %></td>
					<td><%=courierDetails.getSendDate() %></td>
					<td><%=courierDetails.getDeliveryDate() %></td>
					<%
						if(courierDetails.getStatus().equals(Utils.SENT_STATUS)){
							%>
							<td><a href='DeliveredServelet?id=<%=courierDetails.getId()%>'>Set as Delivered</a></td>
							<%
						}
						else{
							%>
							<td><%=courierDetails.getStatus() %></td>
							<%
						}
					%>
					</tr>
					
					<%
				}
			%>

			</table>
		</div>
	</div>

	<script>
		$(function() {
			$("#nav-placeholder").load("nav.jsp");
		});
	</script>
</body>
</html>
