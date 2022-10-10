package com.perfios.bootcamp.onlinebanking.repository;

import com.perfios.bootcamp.onlinebanking.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUsername(String username);

    Client findByUsernameAndPassword(String username,String password);
}
