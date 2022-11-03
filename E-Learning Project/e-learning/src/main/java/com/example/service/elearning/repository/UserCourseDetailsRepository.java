package com.example.service.elearning.repository;

import com.example.service.elearning.domain.Course;
import com.example.service.elearning.domain.UserCourseDetails;
import com.example.service.elearning.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseDetailsRepository extends JpaRepository<UserCourseDetails,Long> {
    UserCourseDetails findByCourseAndUsers(Course course , Users users);
}
