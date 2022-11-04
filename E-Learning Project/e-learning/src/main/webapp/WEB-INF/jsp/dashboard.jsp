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
      .card {
        color: black;
        margin-bottom: 20px;
      }
    </style>
  </head>

  <body style="background-color: black; color: white">
    <div style="width: 80%; margin: auto; margin-top: 10px">
      <div id="nav-placeholder"></div>
      <div style="text-align: center; width: 100%" id="main">
        <div class="container">
          <br />
          <h2>Your Courses</h2>
          <div class="row mt-5">
            <div class="col-md-12">
              <div id="inject" style="text-align: left"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script>
      var name;
      var userId;
      var courseId;
      var trainerId;
      var blobId;
      var iter = 0;
      $(function () {
        $("#nav-placeholder").load("resources/nav.html");

        name = sessionStorage.getItem("name");
        userId = sessionStorage.getItem("userId");
        trainerId = sessionStorage.getItem("trainerId");
        courseId = sessionStorage.getItem("courseId");
        blobId = sessionStorage.getItem("blobId");

        getYourCoursesApiCall();
      });

      async function getYourCoursesApiCall() {
        var requestUrl;
        if (userId != null) {
          requestUrl =
            "http://localhost:5050/elearning/user/course?userId=" + userId;
        } else if (trainerId != null) {
          requestUrl =
            "http://localhost:5050/elearning/trainer/course?trainerId=" +
            trainerId;
        }

        let response = await fetch(requestUrl, {
          method: "GET",
        });

        let resp = await response.json();
        console.log(resp);
        courseDTOS = resp.courseDTOS;
        console.log(courseDTOS);
        courseDTOS.forEach(createTable);
      }

      function setNavBarValues() {
        document.getElementById("navbarDropdownMenuLink").innerHTML = name;
        document.getElementById("clientNavBar").style.display = "flex";
        document.getElementById("commonNavBar").style.display = "flex";

        if (userId != null) {
          document
            .getElementById("dashboardLink")
            .setAttribute("href", "adminDashboard");
        } else {
          document
            .getElementById("dashboardLink")
            .setAttribute("href", "dashboard");
        }

        document
          .getElementById("imageLink")
          .setAttribute("href", "dashboard");
      }

      function createTable(item) {
        var courseId = item.courseId;
        var courseName = item.courseName;
        var trainerName = item.trainerName;
        var courseDescription = item.courseDescription;
        var courseDuration = item.courseDuration;
        var courseLevel = item.level;
        addMore(
          courseName,
          courseId,
          trainerName,
          courseDescription,
          courseDuration,
          courseLevel
        );
      }

      function addMore(
        courseName,
        courseId,
        trainerName,
        courseDescription,
        courseDuration,
        courseLevel
      ) {
        var injectDiv = document.getElementById("inject");

        var card = document.createElement("div");
        card.setAttribute("id", "course[" + courseId + "]");
        card.classList.add("card");

        var cardHeading = document.createElement("h5");
        cardHeading.setAttribute("id", "courseName[" + courseId + "]");
        cardHeading.innerHTML = courseName + " (" + courseLevel + ")";
        cardHeading.classList.add("card-header");

        var cardBody = document.createElement("div");
        cardBody.setAttribute("id", "courseBody[" + courseId + "]");
        cardBody.classList.add("card-body");

        var cardTitle = document.createElement("h5");
        cardTitle.setAttribute("id", "courseTitle[" + courseId + "]");
        cardTitle.innerHTML = trainerName;
        cardTitle.classList.add("card-text");

        var cardText = document.createElement("p");
        cardText.setAttribute("id", "courseDescription[" + courseId + "]");
        cardText.innerHTML = courseDescription;
        cardText.classList.add("card-text");

        var goToCourseBtn = document.createElement("button");
        goToCourseBtn.innerText = "Go To Course";
        goToCourseBtn.setAttribute("id", "goToCourseBtn[" + courseId + "]");
        goToCourseBtn.classList.add("btn-success");
        goToCourseBtn.classList.add("btn");
        goToCourseBtn.setAttribute("onclick", "goToCourse(" + courseId + ")");

        cardBody.appendChild(cardTitle);
        cardBody.appendChild(cardText);
        cardBody.appendChild(goToCourseBtn);

        card.appendChild(cardHeading);
        card.appendChild(cardBody);

        injectDiv.appendChild(card);
        iter++;
      }

      function goToCourse(courseId) {
        sessionStorage.setItem("courseId", courseId);
        window.open("viewCourse", "_self");
      }
    </script>
  </body>
</html>
