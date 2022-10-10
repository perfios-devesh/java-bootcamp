package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Branch;
import com.perfios.bootcamp.onlinebanking.dto.AddBranchResponse;
import com.perfios.bootcamp.onlinebanking.dto.BranchDTO;
import com.perfios.bootcamp.onlinebanking.dto.UpdateBranchResponse;
import com.perfios.bootcamp.onlinebanking.dto.BranchesResponseDTO;
import com.perfios.bootcamp.onlinebanking.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.perfios.bootcamp.onlinebanking.util.UtilConstants.BANK_NAME;

@Service
@AllArgsConstructor
public class BranchService{
    private final BranchRepository branchRepository;

    public AddBranchResponse addBranch(BranchDTO branchDTO)
    {
        AddBranchResponse addBranchResponse = new AddBranchResponse();
        try{
            Branch branch = new Branch();
            branch.setBranchName(branchDTO.getBranchName());
            branch.setIfsc("PERFS000"+branchDTO.getPinCode());
            branch.setAddress(branchDTO.getAddress());
            branch.setMicr("PERFS"+branchDTO.getPinCode());
            branch.setDistrict(branchDTO.getDistrict());
            branch.setState(branchDTO.getState());
            branch.setPinCode(branchDTO.getPinCode());
            branch.setBankName(BANK_NAME);
            branch.setAccountList(new ArrayList<>());
            Branch branch1 = branchRepository.saveAndFlush(branch);

            addBranchResponse.setBranchId(branch1.getBranchId());
            addBranchResponse.setMessage("Branch created Successfully.");
            addBranchResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addBranchResponse.setErrorMessage("Exception in creating new Branch.");
            addBranchResponse.setApiStatusSuccessful(false);
        }

        return addBranchResponse;
    }


    public UpdateBranchResponse updateBranch(BranchDTO branchDTO)
    {
        UpdateBranchResponse updateBranchResponse = new UpdateBranchResponse();
        try{
            Branch branch = branchRepository.getReferenceById(branchDTO.getBranchId());
            branch.setBranchName(branchDTO.getBranchName());
            branch.setIfsc("PERFS000"+branchDTO.getPinCode());
            branch.setAddress(branchDTO.getAddress());
            branch.setMicr("PERFS"+branchDTO.getPinCode());
            branch.setDistrict(branchDTO.getDistrict());
            branch.setState(branchDTO.getState());
            branch.setPinCode(branchDTO.getPinCode());
            //accounts are still maintained as same
            Branch branch1 = branchRepository.saveAndFlush(branch);

            updateBranchResponse.setBranchId(branch1.getBranchId());
            updateBranchResponse.setBranchName(branch1.getBranchName());
            updateBranchResponse.setIfsc(branch1.getIfsc());
            updateBranchResponse.setDistrict(branch1.getDistrict());
            updateBranchResponse.setMicr(branch1.getMicr());
            updateBranchResponse.setAddress(branch1.getAddress());
            updateBranchResponse.setState(branch1.getState());
            updateBranchResponse.setBankName(branch1.getBankName());
            updateBranchResponse.setPinCode(branch1.getPinCode());
            updateBranchResponse.setMessage("Branch updated Successfully.");
            updateBranchResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            updateBranchResponse.setErrorMessage("Exception in updating branch.");
            updateBranchResponse.setApiStatusSuccessful(false);
        }

        return updateBranchResponse;
    }

    public BranchesResponseDTO getBranches()
    {
        BranchesResponseDTO branchesResponseDTO = new BranchesResponseDTO();
        try{
            List<Branch> branches = branchRepository.findAll();
            List<BranchDTO> branchDTOS = new ArrayList<>();
            for(Branch branch : branches){
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setBranchId(branch.getBranchId());
                branchDTO.setBranchName(branch.getBranchName());
                branchDTO.setIfsc(branch.getIfsc());
                branchDTO.setAddress(branch.getAddress());
                branchDTO.setMicr(branch.getMicr());
                branchDTO.setDistrict(branch.getDistrict());
                branchDTO.setState(branch.getState());
                branchDTO.setPinCode(branch.getPinCode());
                branchDTO.setBankName(BANK_NAME);

                branchDTOS.add(branchDTO);
            }

            branchesResponseDTO.setBranchDTOS(branchDTOS);
            branchesResponseDTO.setMessage("Branch fetched Successfully.");
            branchesResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            branchesResponseDTO.setErrorMessage("Exception in fetching branch.");
            branchesResponseDTO.setApiStatusSuccessful(false);
        }

        return branchesResponseDTO;
    }

}
