package com.example.service.elearning.service;

import com.example.service.elearning.domain.Course;
import com.example.service.elearning.domain.Trainer;
import com.example.service.elearning.dto.AddCourseResponse;
import com.example.service.elearning.dto.AllCoursesDTO;
import com.example.service.elearning.dto.CourseDTO;
import com.example.service.elearning.repository.CourseRepository;
import com.example.service.elearning.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    public AddCourseResponse addCourse(CourseDTO courseDTO) {
        AddCourseResponse addCourseResponse = new AddCourseResponse();
        try {
            Trainer trainer = trainerRepository.getById(courseDTO.getTrainerId());
            Course course = new Course();
            course.setCourseDuration(0l);
            course.setCourseName(courseDTO.getCourseName());
            course.setCourseDescription(courseDTO.getCourseDescription());
            course.setLevel(courseDTO.getLevel());
            course.setTrainer(trainer);
            course.setBlobStores(new ArrayList<>());

            Course course1 = courseRepository.saveAndFlush(course);

            addCourseResponse.setTrainerId(trainer.getTrainerId());
            addCourseResponse.setTrainerName(trainer.getFirstName());
            addCourseResponse.setCourseId(course1.getCourseId());
            addCourseResponse.setCourseName(course1.getCourseName());
            addCourseResponse.setLevel(course1.getLevel());
            addCourseResponse.setCourseDuration(course1.getCourseDuration());
            addCourseResponse.setCourseDescription(course1.getCourseDescription());
            addCourseResponse.setMessage("Added Course Successfully");
            addCourseResponse.setApiStatusSuccessful(true);
        } catch (Exception e) {
            addCourseResponse.setErrorMessage("Exception in adding Course.");
            addCourseResponse.setApiStatusSuccessful(false);
        }
        return addCourseResponse;
    }


    public AllCoursesDTO getCoursesByTrainer(Long trainerId) {
        AllCoursesDTO allCoursesDTO = new AllCoursesDTO();
        try {
            List<Course> courseList;
            if (trainerId == null) {
                courseList = courseRepository.findAll();
            } else {
                courseList = courseRepository.findByTrainer(trainerRepository.getById(trainerId));
            }

            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course course : courseList) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setTrainerId(course.getTrainer().getTrainerId());
                courseDTO.setTrainerName(course.getTrainer().getFirstName());
                courseDTO.setCourseId(course.getCourseId());
                courseDTO.setCourseName(course.getCourseName());
                courseDTO.setCourseDescription(course.getCourseDescription());
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

    public AllCoursesDTO getAllCourses() {
        AllCoursesDTO allCoursesDTO = new AllCoursesDTO();
        try {
            List<Course>
                courseList = courseRepository.findAll();

            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course course : courseList) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setTrainerId(course.getTrainer().getTrainerId());
                courseDTO.setTrainerName(course.getTrainer().getFirstName() + " " + course.getTrainer().getLastName() );
                courseDTO.setCourseId(course.getCourseId());
                courseDTO.setCourseName(course.getCourseName());
                courseDTO.setCourseDescription(course.getCourseDescription());
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
}
