<!DOCTYPE html>
<%@page import="com.trial.CostingDao"%>
<%@page import="com.trial.Costing"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="com.trial.Utils" %>
<%@ page import="java.util.*" %>
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
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <style>
      .col-4,
      .col-11,
      .col-8 {
        margin-bottom: 15px;
        text-align: left;
      }
      input[type="number"]::-webkit-inner-spin-button,
      input[type="number"]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }
    </style>
  </head>

  <body style="background-color: black; color: white">
    <div style="width: 80%; margin: auto; margin-top: 10px">
      <div id="nav-placeholder"></div>

      <div style="text-align: center; width: 100%" id="main">
        <div
          style="
            width: 44%;
            float:left;
            text-align: left;
            padding: 10px;
            margin-top: 10px;
          "
        >
          <div style="margin-top: 10px;">
            <h2 style="text-align: center;">Courier Booking</h2>
            <form action="BookCourier" method="post">
              <div class="row ">
                <div class="col-11">
                  <label for="name" class="form-label">Name</label>
                  <input
                    required="required"
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    placeholder="Name"
                  />
                </div>
                </div>
                <div class="row ">
                <div class="col-11">
                  <label for="mobile" class="form-label">Mobile Number</label>
                  <input
                    required="required"
                    type="number"
                    class="form-control"
                    id="mobile"
                    name = "mobile"
                    placeholder="Mobile"
                  />
                </div>
              </div>    
              <div class="row ">
                  <div class="col-11">
                    <label for="fromCity" class="form-label">From City</label>
                    <select
                      required="required"
                      class="form-control"
                      id="fromCity"
                      name = "fromCity"
                      placeholder="From City"
                      onchange="showCalculateBtn();"
                    >
                      <option selected disabled value="">From City</option>
                      <option value="bengaluru">Bengaluru</option>
                      <option value="lucknow">Lucknow</option>
                      <option value="mumbai">Mumbai</option>
                      <option value="hyderabad">Hyderabad</option>
                    </select>
                  </div>
                </div>
                <div class="row ">
                  <div class="col-11">
                    <label for="toCity" class="form-label">To City</label>
                    <select
                      required="required"
                      class="form-control"
                      id="toCity"
                      name = "toCity"
                      placeholder="To City"
                      onchange="showCalculateBtn();"
                    >
                      <option selected disabled value="">To City</option>
                      <option value="bengaluru">Bengaluru</option>
                      <option value="lucknow">Lucknow</option>
                      <option value="mumbai">Mumbai</option>
                      <option value="hyderabad">Hyderabad</option>
                    </select>
                  </div>
                </div>   
                <div class="row ">
                <div class="col-11">
                  <label for="weight" class="form-label">Weight of Package in Grams</label>
                  <input
                    required="required"
                    type="number"
                    class="form-control"
                    id="weight"
                    name= "weight"
                    placeholder="Weight of Package in Grams"
                    onchange="showCalculateBtn();"
                  />
                </div>
              </div>  
              <div class="row" id="costInput" style="display: none;">
                <div class="col-11">
                  <label for="cost" class="form-label">Cost of Booking</label>
                  <input
                    readonly="readonly"
                    type="number"
                    class="form-control"
                    id="cost"
                    name = "cost"
                    placeholder="Cost of Booking"
                  />
                </div>
              </div>        
              <div style="text-align: center" id="calculateCostBtn">
              <br>
                <button
                type="button"
                  onclick="calculateCost();return false;"
                  class="btn btn-primary"
                >
                  Calculate Cost
                </button>
              </div>
              <div style="text-align: center;display: none;" id="submitFormBtn">
              <br>
                <button
                  type="submit"
                  class="btn btn-primary"
                >
                  Book Courier
                </button>
              </div>
            </form>
          </div>
          <br>
        </div>
	
        <div
          id="carouselExampleInterval"
          class="carousel carousel-dark slide"
          data-bs-ride="carousel"
          style="height: 500px; width: 55%;float: right;margin-top: 60px;"
        >
          <div class="carousel-inner" style="height: 500px; margin-top: 10px">
            <div class="carousel-item active" data-bs-interval="2000">
              <img
                src="resources/carausel_first.jpg"
                style="height: 500px"
                class="d-block w-100"
                alt="First"
              />
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <img
                src="resources/carausel_second.jpg"
                style="height: 500px"
                class="d-block w-100"
                alt="Second"
              />
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <img
                src="resources/carausel_third.jpg"
                style="height: 500px"
                class="d-block w-100"
                alt="Third"
              />
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleInterval"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleInterval"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>

    <script>
      $(function () {
        $("#nav-placeholder").load("nav.jsp");
      });
      
      var fromCityList =[];
      var toCityList =[];
      var distanceList =[];
      var costPerGramList =[];
      
      <%
	  List<Costing> costingList = CostingDao.getAllCostings();
	  for(Costing costing : costingList){
		  %>
		  fromCityList.push("<%= costing.getFromCity()%>");
		  toCityList.push("<%= costing.getToCity()%>");
		  distanceList.push(<%= costing.getDistance()%>);
		  costPerGramList.push(<%= costing.getCostPerGram()%>);
		  <%
	  }
	  %>
      
      function calculateCost() {
    	  console.log(fromCityList);
    	  var fromCity = document.getElementById("fromCity").value;
    	  var toCity = document.getElementById("toCity").value;
    	  var weight = document.getElementById("weight").value;
    	  
    	  var cost = 0;
    	  
    	  for(var i =0; i<= fromCityList.length; i++){
    		  if((fromCityList[i] == fromCity && toCityList[i]== toCity) ||  (fromCityList[i] == toCity && toCityList[i]== fromCity))
    			  {
    			  	cost = costPerGramList[i] * weight;
    			  	break;
    			  }
    	  }
    	  
    	  document.getElementById("cost").value = cost.toFixed(2);
    	document.getElementById("costInput").style.display = ""; 
		document.getElementById("calculateCostBtn").style.display = "none";
		document.getElementById("submitFormBtn").style.display = "";
	}
      
      function showCalculateBtn(){
    	document.getElementById("costInput").style.display = "none"; 
  		document.getElementById("calculateCostBtn").style.display = "";
  		document.getElementById("submitFormBtn").style.display = "none";
      }

         </script>
  </body>
</html>
