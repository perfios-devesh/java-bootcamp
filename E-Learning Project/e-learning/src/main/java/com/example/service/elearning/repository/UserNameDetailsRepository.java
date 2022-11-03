package com.example.service.elearning.repository;

import com.example.service.elearning.domain.UserNameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserNameDetailsRepository extends JpaRepository<UserNameDetails, Long> {
    UserNameDetails findByUsernameAndPassword(String username , String password);
}
