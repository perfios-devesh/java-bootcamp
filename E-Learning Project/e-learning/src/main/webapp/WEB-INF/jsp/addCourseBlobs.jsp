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
     <script
          src="https://code.jquery.com/jquery-3.6.0.min.js"
          integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
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

      .form-label {
        margin-top: 7px;
      }
    </style>
  </head>

  <body style="background-color: black; color: white">
    <div style="width: 80%; margin: auto; margin-top: 10px">
      <div id="nav-placeholder"></div>
      <div style="text-align: center; width: 100%" id="main">
        <div class="container">
          <br />
          <h2>Create Your Course Here</h2>
          <div class="row mt-5">
            <div class="col-md-12">
              <div
                class="col-2"
                style="margin-bottom: 20px; margin-left: 87%; margin-top: -50px"
              >
                <br />
                <button
                  class="btn btn-primary"
                  onclick="addSectionDiv(); return false;"
                >
                  Add Section
                </button>
              </div>
              <div id="inject" style="text-align: left"></div>
              <div class="row justify-content-center">
                <div class="col-2">
                  <button
                    id="uploadAllBtn"
                    class="btn btn-success"
                    onclick="uploadAllBlobs(); return false;"
                  >
                    Upload All
                  </button>
                </div>
              </div>
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
      var sectionIter = 0;
      var blobsInSectionIter = new Array();
      blobsInSectionIter.push(0); //so that section iter matches the push in array
      var iter = 0;
      $(function () {
        $("#nav-placeholder").load("resources/nav.html");

        name = sessionStorage.getItem("name");
        userId = sessionStorage.getItem("userId");
        trainerId = sessionStorage.getItem("trainerId");
        courseId = sessionStorage.getItem("courseId");
        blobId = sessionStorage.getItem("blobId");

        addSectionDiv();
      });

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

      function addSectionDiv() {
        sectionIter++;

        var injectDiv = document.getElementById("inject");

        var sectionDiv = document.createElement("div");
        sectionDiv.setAttribute("id", "section[" + sectionIter + "]");
        sectionDiv.classList.add("card");

        var sectionNameDiv = document.createElement("div");
        sectionNameDiv.classList.add("card-header");
        sectionNameDiv.style.height = "55px";

        var sectionNameLabel = document.createElement("label");
        sectionNameLabel.setAttribute("for", "sectionName[" + sectionIter+ "]");
        sectionNameLabel.innerHTML = "Section Name:  ";
        sectionNameLabel.style.color = "black";
        sectionNameLabel.style.float = "left";
        sectionNameLabel.classList.add("form-label");

        var sectionName = document.createElement("input");
        sectionName.setAttribute("id", "sectionName[" + sectionIter +"]");
        sectionName.style.width = "40%";
        sectionName.style.display = "inline-block";
        sectionName.style.marginLeft = "1%";
        sectionName.style.float = "left";
        sectionName.classList.add("form-control");

        var addBlobBtn = document.createElement("button");
        addBlobBtn.innerHTML = "Add Video";
        addBlobBtn.classList.add("btn");
        addBlobBtn.classList.add("btn-primary");
        addBlobBtn.setAttribute("style", "display: inline-block; float:right;");
        addBlobBtn.setAttribute(
          "onclick",
          "addBlobInSectionIntermediate( " + sectionIter + ")"
        );

        sectionNameDiv.appendChild(sectionNameLabel);
        sectionNameDiv.appendChild(sectionName);
        sectionNameDiv.appendChild(addBlobBtn);

        var sectionBody = document.createElement("div");
        sectionBody.classList.add("card-body");
        sectionBody.setAttribute("id", "sectionBody[" + sectionIter+ "]");

        blobsInSectionIter.push(1);
        blobDivId = blobsInSectionIter[sectionIter];

        sectionDiv.appendChild(sectionNameDiv);
        sectionDiv.appendChild(sectionBody);

        injectDiv.appendChild(sectionDiv);

        addBlobInSection(sectionIter, blobDivId);

        console.log(blobsInSectionIter);
      }

      function addBlobInSectionIntermediate(sectionDivId) {
        blobDivId = blobsInSectionIter[sectionDivId] + 1;
        blobsInSectionIter[sectionDivId] = blobDivId;

        console.log(blobsInSectionIter);

        addBlobInSection(sectionDivId, blobDivId);
      }

      function addBlobInSection(sectionDivId, blobDivId) {
        var sectionBody = document.getElementById(
          "sectionBody[" + sectionDivId+ "]"
        );

        var blobDiv = document.createElement("div");
        blobDiv.setAttribute("id", "blob[" + sectionDivId + "][" + blobDivId + "]");
        blobDiv.classList.add("card");

        var blobHeadingDiv = document.createElement("div");
        blobHeadingDiv.classList.add("card-header");
        blobHeadingDiv.style.height = "55px";

        var blobHeadingLabel = document.createElement("label");
        blobHeadingLabel.setAttribute(
          "for",
          "blobHeading[" + sectionDivId + "][" + blobDivId + "]"
        );
        blobHeadingLabel.innerHTML = "Video Name:  ";
        blobHeadingLabel.style.color = "black";
        blobHeadingLabel.classList.add("form-label");

        var blobHeading = document.createElement("input");
        blobHeading.setAttribute(
          "id",
          "blobHeading[" + sectionDivId + "][" + blobDivId + "]"
        );
        blobHeading.style.width = "40%";
        blobHeading.style.display = "inline-block";
        blobHeading.style.marginLeft = "1%";
        blobHeading.classList.add("form-control");

        blobHeadingDiv.appendChild(blobHeadingLabel);
        blobHeadingDiv.appendChild(blobHeading);

        var blobBody = document.createElement("div");
        blobBody.classList.add("card-body");
        blobBody.setAttribute("id", "blobBody[" + sectionDivId + "][" + blobDivId + "]");

        var blobFileLabel = document.createElement("label");
        blobFileLabel.setAttribute(
          "for",
          "blobFile[" + sectionDivId + "][" + blobDivId + "]"
        );
        blobFileLabel.innerHTML = "Video File:  ";
        blobFileLabel.style.color = "black";
        blobFileLabel.classList.add("form-label");

        var videoCreate = document.createElement("video");
        var obUrl;
        var durationValue = 0;

        var blobFile = document.createElement("input");
        blobFile.setAttribute("type", "file");
        blobFile.setAttribute("accept", "video/mp4,video/x-m4v,video/*");
        blobFile.setAttribute("id", "blobFile[" + sectionDivId + "][" + blobDivId + "]");
        blobFile.style.width = "40%";
        blobFile.style.display = "inline-block";
        blobFile.style.marginLeft = "1%";
        blobFile.classList.add("form-control");
        blobFile.addEventListener("change", function (e) {
          var file = e.currentTarget.files[0];
          //check file extension for audio/video type
          if (file.name.match(/\.(avi|mp3|mp4|mpeg|ogg)$/i)) {
            obUrl = URL.createObjectURL(file);
            videoCreate.setAttribute("src", obUrl);
          }
        });

        videoCreate.addEventListener("canplaythrough", function (e) {
          durationValue = Math.round(e.currentTarget.duration);
          console.log(durationValue);
          document.getElementById(
            "blobDuration[" + sectionDivId + "][" + blobDivId + "]"
          ).value = durationValue;
          URL.revokeObjectURL(obUrl);
        });

        blobBody.appendChild(blobFileLabel);
        blobBody.appendChild(blobFile);

        var lineBreak = document.createElement("br");
        var lineBreak1 = document.createElement("br");

        blobBody.appendChild(lineBreak);
        blobBody.appendChild(lineBreak1);

        var blobDurationLabel = document.createElement("label");
        blobDurationLabel.setAttribute(
          "for",
          "blobDuration[" + sectionDivId + "][" + blobDivId + "]"
        );
        blobDurationLabel.innerHTML = "Video Length in Seconds:  ";
        blobDurationLabel.style.color = "black";
        blobDurationLabel.classList.add("form-label");

        var blobDuration = document.createElement("input");
        blobDuration.setAttribute("type", "number");
        blobDuration.setAttribute(
          "id",
          "blobDuration[" + sectionDivId + "][" + blobDivId + "]"
        );
        blobDuration.style.width = "31.5%";
        blobDuration.style.display = "inline-block";
        blobDuration.style.marginLeft = "1%";
        blobDuration.classList.add("form-control");
        blobDuration.disabled = "true";

        blobBody.appendChild(blobDurationLabel);
        blobBody.appendChild(blobDuration);

        // var lineBreak2 = document.createElement("br");
        // var lineBreak3 = document.createElement("br");

        // blobBody.appendChild(lineBreak2);
        // blobBody.appendChild(lineBreak3);

        // var uploadBlobBtn = document.createElement("button");
        // uploadBlobBtn.innerHTML = "Upload Video";
        // uploadBlobBtn.classList.add("btn");
        // uploadBlobBtn.classList.add("btn-success");
        // uploadBlobBtn.setAttribute(
        //   "id",
        //   "uploadBlobBtn[" + sectionDivId + "][" + blobDivId + "]"
        // );
        // uploadBlobBtn.setAttribute(
        //   "onclick",
        //   "uploadBlob(${sectionDivId} , ${blobDivId})"
        // );

        // blobBody.appendChild(uploadBlobBtn);

        blobDiv.appendChild(blobHeadingDiv);
        blobDiv.appendChild(blobBody);

        sectionBody.appendChild(blobDiv);
      }

      var totalCalls = 0;
      var calledCalls = 0;

      function uploadAllBlobs() {
        console.log(blobsInSectionIter);
        totalCalls = 0;
        calledCalls = 0;
        var i;
        var j;
        for (i = 1; i <= sectionIter; i++) {
          var sectionDivId = i;
          for (j = 1; j <= blobsInSectionIter[sectionDivId]; j++) {
            var blobDivId = j;

            uploadBlob(sectionDivId, blobDivId);
          }
        }
      }

      function checkIfAllDone() {
        if (calledCalls == totalCalls) {
          sessionStorage.setItem("courseId", courseId);
          window.open("viewCourse", "_self");
        }
      }

      function uploadBlob(sectionDivId, blobDivId) {
        if (
          document.getElementById("blobFile[" + sectionDivId + "][" + blobDivId + "]") !=
          null
        ) {
          totalCalls++;
          uploadBlobApiCall(sectionDivId, blobDivId);
        }
      }

      async function uploadBlobApiCall(sectionDivId, blobDivId) {
        var formData = new FormData();
        formData.append(
          "file",
          document.getElementById("blobFile[" + sectionDivId + "][" + blobDivId + "]")
            .files[0]
        );
        formData.append("blobType", "video/mp4");
        formData.append("courseId", courseId);
        formData.append(
          "section",
          document.getElementById("sectionName[" + sectionDivId + "]").value
        );
        formData.append(
          "itemDuration",
          document.getElementById("blobDuration[" + sectionDivId + "][" + blobDivId + "]")
            .value
        );
        formData.append("itemType", "video/mp4");
        formData.append(
          "itemHeading",
          document.getElementById("blobHeading[" + sectionDivId + "][" + blobDivId + "]")
            .value
        );

        //logging formData
        for (var pair of formData.entries()) {
          console.log(pair[0] + ", " + pair[1]);
        }

        let response = await fetch("http://localhost:5050/elearning/blob", {
          method: "POST",
          body: formData,
        });

        let resp = await response.json();
        console.log(resp);

        if (resp.apiStatusSuccessful == true) {
          document.getElementById(
            "sectionName[" + sectionDivId + "]"
          ).readOnly = true;
          document.getElementById(
            "blobHeading[" + sectionDivId + "][" + blobDivId + "]"
          ).readOnly = true;
          document.getElementById(
            "blobFile[" + sectionDivId + "][" + blobDivId + "]"
          ).readOnly = true;
          document.getElementById(
            "blobDuration[" + sectionDivId + "][" + blobDivId + "]"
          ).readOnly = true;
          document
            .getElementById("uploadAllBtn")
            .setAttribute("disabled", "disabled");

          calledCalls++;
          checkIfAllDone();
        }
      }
    </script>
  </body>
</html>
