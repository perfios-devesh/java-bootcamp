<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>E-Learning</title>
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
          class="col-9 justify-content-center"
          style="
            margin: auto;
            text-align: center;
            padding: 10px;
            margin-top: 10px;
          "
        >
          <div class="container">
            <form
              onsubmit="registerFunction();return false;"
              id="formDiv"
              style="display: block"
            >
              <h2>Register</h2>
              <br />
              <div class="row justify-content-center">
                <div class="col-8">
                  <label for="typeOfUser" class="form-label">You Are </label>
                  <select
                    required="required"
                    class="form-control"
                    id="typeOfUser"
                  >
                    <option selected value="Student">Student</option>
                    <option value="Trainer">Trainer</option>
                  </select>
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-4">
                  <label for="firstName" class="form-label">First Name</label>
                  <input
                    required="required"
                    type="text"
                    class="form-control"
                    id="firstName"
                    placeholder="First Name"
                  />
                </div>
                <div class="col-4">
                  <label for="lastName" class="form-label">Last Name</label>
                  <input
                    required="required"
                    type="text"
                    class="form-control"
                    id="lastName"
                    placeholder="Last Name"
                  />
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-4">
                  <label for="email" class="form-label">Email-id</label>
                  <input
                    required="required"
                    type="email"
                    class="form-control"
                    id="email"
                    placeholder="Email-id"
                  />
                </div>
                <div class="col-4">
                  <label for="mobileNumber" class="form-label"
                    >Mobile Number</label
                  >
                  <input
                    required="required"
                    type="number"
                    class="form-control"
                    id="mobileNumber"
                    placeholder="Mobile Number"
                  />
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-8">
                  <label for="username" class="form-label">Username</label>
                  <input
                    required="required"
                    type="text"
                    class="form-control"
                    id="username"
                    placeholder="Username"
                  />
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-8">
                  <label for="password" class="form-label">Password</label>
                  <input
                    required="required"
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="Password"
                  />
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-8">
                  <label for="confirmPassword" class="form-label"
                    >Confirm Password</label
                  >
                  <input
                    onblur="matchPassword();"
                    required="required"
                    type="password"
                    class="form-control"
                    id="confirmPassword"
                    placeholder="Confirm Password"
                  />
                  <p
                    id="matchPasswordParagraph"
                    style="display: none; color: red"
                  >
                    Passwords don't match.
                  </p>
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-2">
                  <button class="btn btn-primary">Register</button>
                </div>
              </div>
              <div class="row justify-content-center">
                <div class="col-8" style="text-align: center; margin-top: 15px">
                  <a
                    href="index"
                    style="color: white; text-decoration: underline"
                    >Already Registered. Sign In here.</a
                  >
                </div>
              </div>
            </form>
            <div
              id="registeredDiv"
              style="align-items: center; display: none; margin-top: 25%"
            >
              <h3>
                You have been Registered. <br />You can now login and access
                different courses.
              </h3>
              <br />
              <button
                onclick="window.open('index', '_self');"
                class="btn btn-primary"
              >
                Sign in
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script>
      var name;
      var userId;
      var courseId;
      var blobId;
      var trainerId;
      $(function () {
        $("#nav-placeholder").load("resources/nav.html");
      });

      function matchPassword() {
        var pw1 = document.getElementById("password");
        var pw2 = document.getElementById("confirmPassword");
        if (pw1.value != pw2.value) {
          pw2.value = "";
          document.getElementById("matchPasswordParagraph").style.display =
            "inline-block";
        } else {
          document.getElementById("matchPasswordParagraph").style.display =
            "none";
        }
      }

      function registerFunction() {
        var pw1 = document.getElementById("password");
        var pw2 = document.getElementById("confirmPassword");
        if (pw1.value != pw2.value) {
          pw2.value = "";
          document.getElementById("matchPasswordParagraph").style.display =
            "inline-block";
        } else {
          document.getElementById("matchPasswordParagraph").style.display =
            "none";
        }
        registerApiCall();
      }

      async function registerApiCall() {
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var mobileNumber = document.getElementById("mobileNumber").value;
        var email = document.getElementById("email").value;
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        var typeOfUser = document.getElementById("typeOfUser").value;

        let data = {
          firstName: firstName,
          lastName: lastName,
          mobileNumber: mobileNumber,
          email: email,
          username: username,
          password: password,
        };
        console.log(data);

        var requestUrl = "";
        if (typeOfUser == "Student") {
          requestUrl = "http://localhost:5050/elearning/user";
        } else {
          requestUrl = "http://localhost:5050/elearning/trainer";
        }

        let response = await fetch(requestUrl, {
          headers: { "Content-Type": "application/json" },
          method: "POST",
          body: JSON.stringify(data),
        });

        let resp = await response.json();
        console.log(resp);

        document.getElementById("registeredDiv").style.display = "block";
        document.getElementById("formDiv").style.display = "none";
      }
    </script>
  </body>
</html>
