package com.example.service.elearning.repository;

import com.example.service.elearning.domain.Course;
import com.example.service.elearning.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByTrainer(Trainer trainer);
}
