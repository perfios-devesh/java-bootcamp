package com.example.service.elearning.repository;

import com.example.service.elearning.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
}
