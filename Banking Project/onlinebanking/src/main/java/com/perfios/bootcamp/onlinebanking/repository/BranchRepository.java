package com.perfios.bootcamp.onlinebanking.repository;

import com.perfios.bootcamp.onlinebanking.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

}
