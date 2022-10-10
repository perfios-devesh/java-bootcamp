package com.perfios.bootcamp.onlinebanking.repository;

import com.perfios.bootcamp.onlinebanking.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUsernameAndPassword(String username, String password);
}
