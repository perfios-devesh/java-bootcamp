<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
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
        margin-bottom: 10px;
      }
    </style>
  </head>

  <body style="background-color: black; color: white">
    <div style="width: 80%; margin: auto; margin-top: 10px">
      <div id="nav-placeholder"></div>
      <div style="text-align: center; width: 100%" id="main">
        <div class="container">
          <div class="row mt-5">
            <div class="col-md-12">
              <div id="courseDetails"></div>
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
      const details = new Map();
      var currentBlobId = 0;
      $(function () {
        $("#nav-placeholder").load("resources/nav.html");

        name = sessionStorage.getItem("name");
        userId = sessionStorage.getItem("userId");
        trainerId = sessionStorage.getItem("trainerId");
        courseId = sessionStorage.getItem("courseId");
        blobId = sessionStorage.getItem("blobId");

        getCourseDetailsApiCall();
      });

      async function getCourseDetailsApiCall() {
        var requestUrl = "";
        if (userId != null)
          requestUrl =
            "http://localhost:5050/elearning/course/details?courseId=" +
            courseId +
            "&userId=" +
            userId;
        else
          requestUrl =
            "http://localhost:5050/elearning/course/details?courseId=" +
            courseId;
        let response = await fetch(requestUrl, {
          method: "GET",
        });

        let resp = await response.json();
        console.log(resp);

        if (resp.currentBlobId != null) {
          currentBlobId = resp.currentBlobId;
        }

        var courseName = resp.courseName;
        var trainerName = resp.trainerName;
        var courseTrainerId = resp.trainerId;
        var courseDescription = resp.courseDescription;
        var courseDuration = resp.courseDuration;
        var courseLevel = resp.level;

        var injectDiv = document.getElementById("courseDetails");

        var card = document.createElement("div");
        card.classList.add("card");

        var cardHeader = document.createElement("div");
        cardHeader.setAttribute("id", "courseHeader[" + courseId + "]");
        cardHeader.classList.add("card-header");
        cardHeader.style.height = "40px";
        cardHeader.style.textAlign = "left";

        var cardHeading = document.createElement("h5");
        cardHeading.setAttribute("id", "courseName[" + courseId + "]");
        cardHeading.innerHTML = courseName + " (" + courseLevel + ")";
        cardHeading.style.float = "left";
        cardHeading.style.width = "80%";
        cardHeading.style.marginTop = "5px";

        cardHeader.appendChild(cardHeading);

        if (resp.currentBlobId == null && trainerId == null) {
          var registerForCourseBtn = document.createElement("button");
          registerForCourseBtn.innerHTML = "Register";
          registerForCourseBtn.classList.add("btn");
          registerForCourseBtn.classList.add("btn-success");
          registerForCourseBtn.style.display = "inline-block";
          registerForCourseBtn.setAttribute("onclick", "registerForCourse()");
          registerForCourseBtn.style.width = "10%";
          registerForCourseBtn.style.float = "right";
          cardHeader.style.height = "55px";

          cardHeader.appendChild(registerForCourseBtn);
        }

        if (trainerId == courseTrainerId) {
          var editCourseBtn = document.createElement("button");
          editCourseBtn.innerHTML = "Edit Course";
          editCourseBtn.classList.add("btn");
          editCourseBtn.classList.add("btn-primary");
          editCourseBtn.style.display = "inline-block";
          editCourseBtn.setAttribute("onclick", "editCourse()");
          editCourseBtn.style.width = "10%";
          editCourseBtn.style.float = "right";
          cardHeader.style.height = "55px";

          cardHeader.appendChild(editCourseBtn);
        }
        card.appendChild(cardHeader);

        var cardBody = document.createElement("div");
        cardBody.classList.add("card-body");

        var cardTitle = document.createElement("h5");
        cardTitle.innerHTML = trainerName;
        cardTitle.classList.add("card-text");

        var cardText = document.createElement("p");
        cardText.innerHTML = courseDescription;
        cardText.classList.add("card-text");

        cardBody.appendChild(cardTitle);
        cardBody.appendChild(cardText);
        card.appendChild(cardBody);

        injectDiv.appendChild(card);

        blobDTOS = resp.blobDTOS;
        console.log(blobDTOS);
        blobDTOS.forEach(createSectionDetailsArray);

        console.log(details);

        //creating ui elements
        details.forEach(function (value, key) {
          var injectDiv = document.getElementById("inject");

          var sectionDiv = document.createElement("div");
          sectionDiv.classList.add("card");

          var sectionName = document.createElement("h5");
          sectionName.innerHTML = key;
          sectionName.classList.add("card-header");

          var sectionBody = document.createElement("div");
          sectionBody.classList.add("card-body");

          value.forEach(function (blobDetails) {
            var card = document.createElement("div");
            card.setAttribute("id", "blob[" + blobDetails[0] + "]");
            card.classList.add("card");

            var cardHeader = document.createElement("div");
            cardHeader.setAttribute("id", "blobHeader[" + blobDetails[0] + "]");
            cardHeader.classList.add("card-header");
            cardHeader.style.height = "40px";

            var cardHeading = document.createElement("p");
            cardHeading.style.width = "80%";
            cardHeading.style.float = "left";
            cardHeading.innerHTML = blobDetails[1];

            if (trainerId == courseTrainerId) {
              cardHeader.setAttribute(
                "onclick",
                "goToVideoPage(" + blobDetails[0] + ")"
              );
              cardHeader.style.cursor = "pointer";
            }

            cardHeader.appendChild(cardHeading);

            if (blobDetails[0] == currentBlobId && currentBlobId != 0) {
              var resumeButton = document.createElement("button");
              resumeButton.innerHTML = "Resume";
              resumeButton.classList.add("btn");
              resumeButton.classList.add("btn-success");
              resumeButton.setAttribute(
                "onclick",
                "goToVideoPage(" + blobDetails[0] + ")"
              );
              resumeButton.style.width = "10%";
              resumeButton.style.float = "right";

              cardHeader.style.height = "55px";

              cardHeader.appendChild(resumeButton);
            }

            card.appendChild(cardHeader);

            sectionBody.appendChild(card);
          });

          sectionDiv.appendChild(sectionName);
          sectionDiv.appendChild(sectionBody);

          injectDiv.appendChild(sectionDiv);
        });

        setNavBarValues();
      }

      function createSectionDetailsArray(item) {
        var blobId = item.blobId;
        var itemType = item.itemType;
        var section = item.section;
        var itemHeading = item.itemHeading;
        var itemDuration = item.itemDuration;
        addMore(itemType, blobId, section, itemHeading, itemDuration);
      }

      function addMore(itemType, blobId, section, itemHeading, itemDuration) {
        var minutes = Math.floor(itemDuration / 60);
        var seconds = itemDuration - minutes * 60;
        var previousArray = details.get(section);
        if (previousArray == null) {
          previousArray = new Array();
        }

        blobNameIdArray = new Array();
        blobNameIdArray.push(blobId);
        blobNameIdArray.push(
          itemHeading + " (" + minutes + " min " + seconds + " sec)"
        );

        previousArray.push(blobNameIdArray);

        details.set(section, previousArray);

        iter++;
      }

      function goToVideoPage(blobId) {
        sessionStorage.setItem("blobId", blobId);
        window.open("viewVideo", "_self");
      }

      async function registerForCourse() {
        let data = {
          userId: userId,
          courseId: courseId,
        };
        let response = await fetch(
          "http://localhost:5050/elearning/course/register",
          {
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify(data),
          }
        );

        let resp = await response.json();
        console.log(resp);

        window.open("viewCourse", "_self");
      }

      function editCourse() {
        sessionStorage.setItem("courseId", courseId);
        window.open("editCourse", "_self");
      }
    </script>
  </body>
</html>
