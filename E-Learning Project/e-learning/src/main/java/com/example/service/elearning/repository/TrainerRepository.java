package com.example.service.elearning.repository;

import com.example.service.elearning.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
}
