package com.perfios.bootcamp.onlinebanking.repository;

import com.perfios.bootcamp.onlinebanking.domain.ClientLoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLoanDetailsRepository extends JpaRepository<ClientLoanDetails,Long> {
}
