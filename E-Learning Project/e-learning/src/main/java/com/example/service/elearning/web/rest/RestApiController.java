package com.example.service.elearning.web.rest;

import com.example.service.elearning.dto.*;
import com.example.service.elearning.service.*;
import com.example.service.elearning.util.CommonServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/elearning")
public class RestApiController {
    private final BlobStoreService blobStoreService;
    private final CourseService courseService;
    private final TrainerService trainerService;
    private final UsersService usersService;
    private final UserNameDetailsService userNameDetailsService;

    @CrossOrigin
    @PostMapping(value = "/trainer")
    public ResponseEntity addTrainer(@RequestBody TrainerDTO trainerDTO)
    {
        AddTrainerResponse addTrainerResponse = null;
        try {
            addTrainerResponse = trainerService.addTrainer(trainerDTO);
        } catch (Exception e) {
            addTrainerResponse = new AddTrainerResponse();
            addTrainerResponse.setApiStatusSuccessful(false);
        }
        if (addTrainerResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addTrainerResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addTrainerResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/user")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO)
    {
        AddUserResponse addUserResponse = null;
        try {
            addUserResponse = usersService.addUser(userDTO);
        } catch (Exception e) {
            addUserResponse = new AddUserResponse();
            addUserResponse.setApiStatusSuccessful(false);
        }
        if (addUserResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addUserResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addUserResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/course")
    public ResponseEntity addCourse(@RequestBody CourseDTO courseDTO)
    {
        AddCourseResponse addCourseResponse = null;
        try {
            addCourseResponse = courseService.addCourse(courseDTO);
        } catch (Exception e) {
            addCourseResponse = new AddCourseResponse();
            addCourseResponse.setApiStatusSuccessful(false);
        }
        if (addCourseResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addCourseResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addCourseResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/trainer")
    public ResponseEntity getAllTrainers()
    {
        AllTrainersDTO allTrainersDTO = null;
        try {
            allTrainersDTO = trainerService.getTrainer();
        } catch (Exception e) {
            allTrainersDTO = new AllTrainersDTO();
            allTrainersDTO.setApiStatusSuccessful(false);
        }
        if (allTrainersDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(allTrainersDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(allTrainersDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/trainer/course")
    public ResponseEntity getCoursesByTrainerId(@RequestParam Long trainerId)
    {
        AllCoursesDTO allCoursesDTO = null;
        try {
            allCoursesDTO = courseService.getCoursesByTrainer(trainerId);
        } catch (Exception e) {
            allCoursesDTO = new AllCoursesDTO();
            allCoursesDTO.setApiStatusSuccessful(false);
        }
        if (allCoursesDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(allCoursesDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(allCoursesDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/course")
    public ResponseEntity getAllCourses()
    {
        AllCoursesDTO allCoursesDTO = null;
        try {
            allCoursesDTO = courseService.getAllCourses();
        } catch (Exception e) {
            allCoursesDTO = new AllCoursesDTO();
            allCoursesDTO.setApiStatusSuccessful(false);
        }
        if (allCoursesDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(allCoursesDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(allCoursesDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/user/course")
    public ResponseEntity getCoursesByUserId(@RequestParam Long userId)
    {
        AllCoursesDTO allCoursesDTO = null;
        try {
            allCoursesDTO = usersService.getCoursesByUser(userId);
        } catch (Exception e) {
            allCoursesDTO = new AllCoursesDTO();
            allCoursesDTO.setApiStatusSuccessful(false);
        }
        if (allCoursesDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(allCoursesDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(allCoursesDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/course/details")
    public ResponseEntity getCourseDetails(@RequestParam Long courseId , @RequestParam(required = false) Long userId)
    {
        CourseDetailsDTO courseDetailsDTO = null;
        try {
            courseDetailsDTO = blobStoreService.getCourseDetails(courseId , userId);
        } catch (Exception e) {
            courseDetailsDTO = new CourseDetailsDTO();
            courseDetailsDTO.setApiStatusSuccessful(false);
        }
        if (courseDetailsDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(courseDetailsDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(courseDetailsDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/course/register")
    public ResponseEntity registerForCourse(@RequestBody RegisterCourseDTO registerCourseDTO)
    {
        CommonServiceResponse commonServiceResponse = null;
        try {
            commonServiceResponse = usersService.registerForCourse(registerCourseDTO);
        } catch (Exception e) {
            commonServiceResponse = new CommonServiceResponse();
            commonServiceResponse.setApiStatusSuccessful(false);
        }
        if (commonServiceResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(commonServiceResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(commonServiceResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/blob/remove")
    public ResponseEntity removeUnwantedBlobs(@RequestBody RemoveBlobsDTO removeBlobsDTO)
    {
        CommonServiceResponse commonServiceResponse = null;
        try {
            commonServiceResponse = blobStoreService.removeUnwantedBlobs(removeBlobsDTO);
        } catch (Exception e) {
            commonServiceResponse = new CommonServiceResponse();
            commonServiceResponse.setApiStatusSuccessful(false);
        }
        if (commonServiceResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(commonServiceResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(commonServiceResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/blob")
    public ResponseEntity saveBlob(@RequestParam("file") MultipartFile file, @RequestParam("blobType") String blobType , @RequestParam("courseId") Long courseId , @RequestParam("section") String section ,@RequestParam("itemDuration") Long itemDuration ,@RequestParam("itemType") String itemType ,@RequestParam("itemHeading") String itemHeading) throws IOException {
        BlobDTO blobDTO = new BlobDTO();
        blobDTO.setBlobType(blobType);
        blobDTO.setCourseId(courseId);
        blobDTO.setFile(file);
        blobDTO.setSection(section);
        blobDTO.setItemDuration(itemDuration);
        blobDTO.setItemType(itemType);
        blobDTO.setItemHeading(itemHeading);

        SavedBlobResponseDTO savedBlobResponseDTO = new SavedBlobResponseDTO();
        savedBlobResponseDTO.setBlobId(blobStoreService.saveBlob(blobDTO).getBlobId());
        savedBlobResponseDTO.setApiStatusSuccessful(true);
        savedBlobResponseDTO.setMessage("Blob Saved Successfully");
        return ResponseEntity.ok().header("Status","Successful").body(savedBlobResponseDTO);
    }

    @CrossOrigin
    @GetMapping(value = "/blob/{id}",produces = "video/mp4")
    public ResponseEntity<Resource> getVideoByName(@PathVariable("id") Long id){
        return ResponseEntity
                .ok(new ByteArrayResource(blobStoreService.getBlob(id).getData()));
    }



    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDetailsDTO loginDetailsDTO)
    {
        LoginResponseDTO loginResponseDTO = null;
        try {
            loginResponseDTO = userNameDetailsService.login(loginDetailsDTO);
        } catch (Exception e) {
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setApiStatusSuccessful(false);
        }
        if (loginResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(loginResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(loginResponseDTO);
        }
    }
}