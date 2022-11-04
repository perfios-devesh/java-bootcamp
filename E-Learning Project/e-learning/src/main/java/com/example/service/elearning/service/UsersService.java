package com.example.service.elearning.service;

import com.example.service.elearning.domain.Course;
import com.example.service.elearning.domain.UserCourseDetails;
import com.example.service.elearning.domain.UserNameDetails;
import com.example.service.elearning.domain.Users;
import com.example.service.elearning.dto.*;
import com.example.service.elearning.repository.CourseRepository;
import com.example.service.elearning.repository.UserCourseDetailsRepository;
import com.example.service.elearning.repository.UserNameDetailsRepository;
import com.example.service.elearning.repository.UsersRepository;
import com.example.service.elearning.util.CommonServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final CourseRepository courseRepository;
    private final UserCourseDetailsRepository userCourseDetailsRepository;
    private final UserNameDetailsRepository userNameDetailsRepository;

    public AddUserResponse addUser(UserDTO userDTO) {
        AddUserResponse addUserResponse = new AddUserResponse();
        try {
            UserNameDetails userNameDetails = new UserNameDetails();
            userNameDetails.setUsername(userDTO.getUsername());
            userNameDetails.setPassword(userDTO.getPassword());
            userNameDetails.setRole("USER");

            UserNameDetails userNameDetails1 = userNameDetailsRepository.saveAndFlush(userNameDetails);

            Users users = new Users();
            users.setUserCourseDetails(new ArrayList<>());
            users.setFirstName(userDTO.getFirstName());
            users.setLastName(userDTO.getLastName());
            users.setEmail(userDTO.getEmail());
            users.setMobileNumber(userDTO.getMobileNumber());
            users.setUserNameDetails(userNameDetails1);

            Users users1 = usersRepository.saveAndFlush(users);



            addUserResponse.setUserId(users1.getUserId());
            addUserResponse.setUsername(userNameDetails1.getUsername());
            addUserResponse.setMessage("Added User Successfully");
            addUserResponse.setApiStatusSuccessful(true);
        } catch (Exception e) {
            addUserResponse.setErrorMessage("Exception in adding User.");
            addUserResponse.setApiStatusSuccessful(false);
        }
        return addUserResponse;
    }

    public AllCoursesDTO getCoursesByUser(Long userId) {
        AllCoursesDTO allCoursesDTO = new AllCoursesDTO();
        try {
            List<UserCourseDetails> userCourseDetailsList = usersRepository.getById(userId).getUserCourseDetails();
            List<Course> courseList = new ArrayList<>();
            for(UserCourseDetails userCourseDetails : userCourseDetailsList){
                courseList.add(userCourseDetails.getCourse());
            }
            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course course : courseList) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setCourseDescription(course.getCourseDescription());
                courseDTO.setTrainerId(course.getTrainer().getTrainerId());
                courseDTO.setTrainerName(course.getTrainer().getFirstName());
                courseDTO.setCourseId(course.getCourseId());
                courseDTO.setCourseName(course.getCourseName());
                courseDTO.setCourseDuration(course.getCourseDuration());
                courseDTO.setLevel(course.getLevel());
                List<Long> blobStoreIds = new ArrayList<>();
                course.getBlobStores().stream().forEach(blobStore -> {
                    blobStoreIds.add(blobStore.getBlobId());
                });
                courseDTO.setBlobStoreIds(blobStoreIds);

                courseDTOS.add(courseDTO);
            }
            allCoursesDTO.setCourseDTOS(courseDTOS);
            allCoursesDTO.setMessage("Fetched Courses Successfully");
            allCoursesDTO.setApiStatusSuccessful(true);
        } catch (Exception e) {
            allCoursesDTO.setErrorMessage("Exception in fetching Courses.");
            allCoursesDTO.setApiStatusSuccessful(false);
        }
        return allCoursesDTO;
    }

    public CommonServiceResponse registerForCourse(RegisterCourseDTO registerCourseDTO){
        CommonServiceResponse commonServiceResponse = new CommonServiceResponse();

        try {
            UserCourseDetails userCourseDetails = new UserCourseDetails();
            Course course = courseRepository.getById(registerCourseDTO.getCourseId());
            userCourseDetails.setCourse(course);
            userCourseDetails.setUsers(usersRepository.getById(registerCourseDTO.getUserId()));
            userCourseDetails.setRegisteredOn(LocalDateTime.now());
            userCourseDetails.setCurrentBlobId(course.getBlobStores().get(0).getBlobId());
            userCourseDetails.setPercentageCompleted(0.0);

            userCourseDetailsRepository.saveAndFlush(userCourseDetails);

            commonServiceResponse.setMessage("Registered for Course Successfully");
            commonServiceResponse.setApiStatusSuccessful(true);
        } catch (Exception e) {
            commonServiceResponse.setErrorMessage("Exception in registering for Course.");
            commonServiceResponse.setApiStatusSuccessful(false);
        }

        return commonServiceResponse;
    }
}
