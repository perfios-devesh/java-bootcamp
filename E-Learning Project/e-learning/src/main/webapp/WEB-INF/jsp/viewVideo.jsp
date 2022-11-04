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
          <div
            id="courseDetails"
            style="text-align: left; margin-top: 20px"
          ></div>
          <div
            class="row mt-10 justify-content-between"
            style="margin-top: 20px"
          >
            <div class="col-md-6">
              <video
                id="video-screen"
                width="800px"
                height="500px"
                controls
              ></video>

              <div style="text-align: left">
                <h5 id="videoName" style="color: white"></h5>
              </div>
            </div>
            <div class="col-md-4">
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
      var blobId;
      const details = new Map();
      const videoScreen = document.getElementById("video-screen");
      var currentBlobId = 0;
      $(function () {
        $("#nav-placeholder").load("resources/nav.html");

        name = sessionStorage.getItem("name");
        userId = sessionStorage.getItem("userId");
        trainerId = sessionStorage.getItem("trainerId");
        courseId = sessionStorage.getItem("courseId");
        blobId = sessionStorage.getItem("blobId");

        videoScreen.src = "http://localhost:5050/elearning/blob/" + blobId;
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
        var courseDescription = resp.courseDescription;
        var courseDuration = resp.courseDuration;
        var courseLevel = resp.level;
        var percentageCompleted = resp.percentageCompleted;

        var injectDiv = document.getElementById("courseDetails");

        var card = document.createElement("div");
        card.classList.add("card");

        var cardHeader = document.createElement("div");
        cardHeader.setAttribute("id", "course[" + courseId + "]");
        cardHeader.classList.add("card-header");

        var cardHeading = document.createElement("h5");
        cardHeading.setAttribute("id", "courseName[" + courseId + "]");
        cardHeading.innerHTML = courseName + " (" + courseLevel + ")";
        cardHeading.style.width = "80%";
        cardHeading.style.float = "left";
        cardHeader.appendChild(cardHeading);

        if (trainerId == null) {
          var cardPercentage = document.createElement("h5");
          cardPercentage.setAttribute("id", "coursePercentage[" + courseId + "]");
          cardPercentage.innerHTML = "Completed: " + percentageCompleted + "%";
          cardPercentage.style.width = "12%";
          cardPercentage.style.float = "right";
          cardHeader.appendChild(cardPercentage);
        }

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

        card.appendChild(cardHeader);
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
            cardHeader.setAttribute("onclick", "showVideo(" + blobDetails[0] + ")");
            cardHeader.style.cursor = "pointer";

            var cardHeading = document.createElement("p");
            cardHeader.setAttribute("id", "blobHeading[" + blobDetails[0] + "]");
            cardHeading.style.width = "80%";
            cardHeading.style.float = "left";
            cardHeading.innerHTML = blobDetails[1];

            cardHeader.appendChild(cardHeading);
            card.appendChild(cardHeader);

            sectionBody.appendChild(card);
          });

          sectionDiv.appendChild(sectionName);
          sectionDiv.appendChild(sectionBody);

          injectDiv.appendChild(sectionDiv);
        });

        document.getElementById("videoName").innerHTML =
          document.getElementById("blobHeading[" + blobId + "]").innerHTML;
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

      function showVideo(blobId) {
        document.getElementById("videoName").innerHTML =
          document.getElementById("blobHeading[" + blobId + "]").innerHTML;
        videoScreen.src = "http://localhost:5050/elearning/blob/" + blobId;
      }
    </script>
  </body>
</html>
