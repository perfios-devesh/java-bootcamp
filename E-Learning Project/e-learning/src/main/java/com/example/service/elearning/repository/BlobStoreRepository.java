package com.example.service.elearning.repository;

import com.example.service.elearning.domain.BlobStore;
import com.example.service.elearning.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlobStoreRepository extends JpaRepository<BlobStore,Long> {

    List<BlobStore> findByCourse(Course course) ;
}
