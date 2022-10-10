package com.perfios.bootcamp.onlinebanking.repository;

import com.perfios.bootcamp.onlinebanking.domain.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails,Long> {
}
