package com.example.service.elearning.service;

import com.example.service.elearning.domain.BlobStore;
import com.example.service.elearning.domain.Course;
import com.example.service.elearning.domain.UserCourseDetails;
import com.example.service.elearning.dto.BlobDTO;
import com.example.service.elearning.dto.CourseDetailsDTO;
import com.example.service.elearning.dto.RemoveBlobsDTO;
import com.example.service.elearning.repository.BlobStoreRepository;
import com.example.service.elearning.repository.CourseRepository;
import com.example.service.elearning.repository.UserCourseDetailsRepository;
import com.example.service.elearning.repository.UsersRepository;
import com.example.service.elearning.util.CommonServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BlobStoreService {

    private final BlobStoreRepository blobStoreRepository;
    private final CourseRepository courseRepository;
    private final UserCourseDetailsRepository userCourseDetailsRepository;
    private final UsersRepository usersRepository;

    public BlobStore getBlob(Long id) {
        return blobStoreRepository.getById(id);
    }

    public BlobStore saveBlob(BlobDTO blobDTO) throws IOException {

        Course course = courseRepository.getById(blobDTO.getCourseId());
        course.setCourseDuration(course.getCourseDuration() + blobDTO.getItemDuration());

        Course course1 = courseRepository.saveAndFlush(course);

        BlobStore blobStore = new BlobStore();
        blobStore.setBlobType(blobDTO.getBlobType());
        blobStore.setData(blobDTO.getFile().getBytes());
        blobStore.setCourse(course1);
        blobStore.setSection(blobDTO.getSection());
        blobStore.setItemDuration(blobDTO.getItemDuration());
        blobStore.setItemHeading(blobDTO.getItemHeading());
        blobStore.setItemType(blobDTO.getItemType());

        return blobStoreRepository.save(blobStore);
    }

    public CourseDetailsDTO getCourseDetails(Long courseId , Long userId) {
        CourseDetailsDTO courseDetailsDTO = new CourseDetailsDTO();
        try {
            Course course = courseRepository.getById(courseId);

            courseDetailsDTO.setCourseName(course.getCourseName());
            courseDetailsDTO.setCourseDescription(course.getCourseDescription());
            courseDetailsDTO.setTrainerId(course.getTrainer().getTrainerId());
            courseDetailsDTO.setTrainerName(course.getTrainer().getFirstName() + " " + course.getTrainer().getLastName());
            courseDetailsDTO.setLevel(course.getLevel());
            courseDetailsDTO.setCourseDuration(course.getCourseDuration());

            List<BlobStore> blobStoreList = blobStoreRepository.findByCourse(course);
            List<BlobDTO> blobDTOS = new ArrayList<>();
            for (BlobStore blobStore : blobStoreList) {
                BlobDTO blobDTO = new BlobDTO();
                blobDTO.setCourseId(courseId);
                blobDTO.setBlobId(blobStore.getBlobId());
                blobDTO.setBlobType(blobStore.getBlobType());
                blobDTO.setSection(blobStore.getSection());
                blobDTO.setItemType(blobStore.getItemType());
                blobDTO.setItemDuration(blobStore.getItemDuration());
                blobDTO.setItemHeading(blobStore.getItemHeading());

                blobDTOS.add(blobDTO);
            }
            courseDetailsDTO.setBlobDTOS(blobDTOS);

            if(userId != null){
                UserCourseDetails userCourseDetails = userCourseDetailsRepository.findByCourseAndUsers(course, usersRepository.getById(userId));
                if (userCourseDetails != null) {
                    courseDetailsDTO.setCurrentBlobId(userCourseDetails.getCurrentBlobId());
                    courseDetailsDTO.setRegisteredOn(userCourseDetails.getRegisteredOn());
                    courseDetailsDTO.setPercentageCompleted(userCourseDetails.getPercentageCompleted());
                }
            }

            courseDetailsDTO.setMessage("Fetched Course Details Successfully");
            courseDetailsDTO.setApiStatusSuccessful(true);
        } catch (Exception e) {
            courseDetailsDTO.setErrorMessage("Exception in fetching Course Details.");
            courseDetailsDTO.setApiStatusSuccessful(false);
        }
        return courseDetailsDTO;
    }

    public CommonServiceResponse removeUnwantedBlobs(RemoveBlobsDTO removeBlobsDTO){
        CommonServiceResponse commonServiceResponse = new CommonServiceResponse();
        try{
            List<BlobStore> blobStoreList = blobStoreRepository.findByCourse(courseRepository.getById(removeBlobsDTO.getCourseId()));
            for(BlobStore blobStore : blobStoreList){
                if( !removeBlobsDTO.getToKeepIds().contains(blobStore.getBlobId())){
                    blobStoreRepository.delete(blobStore);
                }
            }
            commonServiceResponse.setMessage("Updated Course Details Successfully");
            commonServiceResponse.setApiStatusSuccessful(true);
        }catch (Exception e){
            commonServiceResponse.setErrorMessage("Exception in updating Course Details.");
            commonServiceResponse.setApiStatusSuccessful(false);
        }

        return commonServiceResponse;
    }

}
