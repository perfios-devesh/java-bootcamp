package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Branch;
import com.perfios.bootcamp.onlinebanking.dto.AddBranchResponse;
import com.perfios.bootcamp.onlinebanking.dto.BranchDTO;
import com.perfios.bootcamp.onlinebanking.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {

    @Mock
    BranchRepository branchRepository;
    @InjectMocks
    BranchService branchService;

    @Test
    void addBranchSuccessfully() {

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchName("Kormangala");
        branchDTO.setDistrict("Bengaluru");
        branchDTO.setAddress("Kormangala, Bengaluru.");
        branchDTO.setState("Karnataka");
        branchDTO.setPinCode(560030);

        Branch branch1 = new Branch();
        branch1.setBranchId(1l);

        when(branchRepository.saveAndFlush(any())).thenReturn(branch1);

        AddBranchResponse addBranchResponse = branchService.addBranch(branchDTO);
        assertNotNull(addBranchResponse);
        assertTrue(addBranchResponse.getApiStatusSuccessful());
        assertEquals(1l, addBranchResponse.getBranchId());
    }
}
