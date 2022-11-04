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
      .row {
        text-align: left;
      }
    </style>
  </head>

  <body style="background-color: black; color: white">
    <div style="width: 80%; margin: auto; margin-top: 10px">
      <div id="nav-placeholder"></div>
      <div style="text-align: center; width: 100%" id="main">
        <div class="container">
          <br />
          <h2>Add New Course</h2>
          <div class="row mt-5">
            <div class="col-md-12">
              <div>
                <form>
                  <div class="row justify-content-center">
                    <div class="col-6">
                      <label for="courseName" class="form-label"
                        >Course Name</label
                      >
                      <input
                        required="required"
                        type="text"
                        class="form-control"
                        id="courseName"
                        placeholder="Course Name"
                      />
                    </div>
                  </div>

                  <div class="row justify-content-center">
                    <div class="col-6">
                      <br />
                      <label for="courseLevel" class="form-label"
                        >Course Level</label
                      >
                      <select
                        required="required"
                        class="form-control"
                        id="courseLevel"
                        placeholder="Course Level"
                      >
                        <option selected disabled value="">Course Level</option>
                        <option value="Beginner">Beginner</option>
                        <option value="Beginner">Intermediate</option>
                        <option value="Beginner">Advanced</option>
                      </select>
                    </div>
                  </div>

                  <div class="row justify-content-center">
                    <div class="col-6">
                      <br />
                      <label for="courseDescription" class="form-label"
                        >Course Description</label
                      >
                      <textarea
                        required="required"
                        rows="3"
                        class="form-control"
                        id="courseDescription"
                        placeholder="Course Description"
                      ></textarea>
                    </div>
                  </div>

                  <div class="row justify-content-center">
                    <div class="col-2">
                      <br />
                      <button
                        class="btn btn-primary"
                        onclick="addCourseApiCall(); return false;"
                      >
                        Add Course
                      </button>
                    </div>
                  </div>
                </form>
              </div>
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
      });

      async function addCourseApiCall() {
        var courseName = document.getElementById("courseName").value;
        var courseLevel = document.getElementById("courseLevel").value;
        var courseDescription =
          document.getElementById("courseDescription").value;

        let data = {
          courseName: courseName,
          level: courseLevel,
          trainerId: trainerId,
          courseDescription: courseDescription,
        };
        console.log(data);

        let response = await fetch("http://localhost:5050/elearning/course", {
          headers: { "Content-Type": "application/json" },
          method: "POST",
          body: JSON.stringify(data),
        });

        let resp = await response.json();
        console.log(resp);

        if (resp.apiStatusSuccessful == true) {
          sessionStorage.setItem("courseId", resp.courseId);
          window.open("addCourseBlobs", "_self");
        }
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
    </script>
  </body>
</html>
