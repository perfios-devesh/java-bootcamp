package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.ClientLoanDetails;
import com.perfios.bootcamp.onlinebanking.domain.LoanDetails;
import com.perfios.bootcamp.onlinebanking.dto.AddClientLoanDetailsResponse;
import com.perfios.bootcamp.onlinebanking.dto.ClientLoanDetailsDTO;
import com.perfios.bootcamp.onlinebanking.dto.LoanAccountDTO;
import com.perfios.bootcamp.onlinebanking.repository.ClientLoanDetailsRepository;
import com.perfios.bootcamp.onlinebanking.repository.LoanDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ClientLoanDetailsService {
    private final LoanDetailsRepository loanDetailsRepository;
    private final ClientLoanDetailsRepository clientLoanDetailsRepository;


    public AddClientLoanDetailsResponse addClientLoanDetails(LoanAccountDTO loanAccountDTO)
    {
        AddClientLoanDetailsResponse addClientLoanDetailsResponse = new AddClientLoanDetailsResponse();
        try{
            LoanDetails loanDetails = loanDetailsRepository.getReferenceById(loanAccountDTO.getLoanId());
            ClientLoanDetails clientLoanDetails = new ClientLoanDetails();
            clientLoanDetails.setLoanDetails(loanDetails);
            clientLoanDetails.setEmploymentDetails(loanAccountDTO.getEmploymentDetails());
            clientLoanDetails.setLoanDocumentsList(new ArrayList<>());
            clientLoanDetails.setCibilScore(loanAccountDTO.getCibilScore());
            clientLoanDetails.setAgeInYears(loanAccountDTO.getAgeInYears());
            clientLoanDetails.setGuarantorAvailable(loanAccountDTO.getGuarantorAvailable());
            clientLoanDetails.setGuarantorMobileNumber(loanAccountDTO.getGuarantorMobileNumber());
            clientLoanDetails.setGuarantorName(loanAccountDTO.getGuarantorName());
            clientLoanDetails.setGuarantorPanNumber(loanAccountDTO.getGuarantorPanNumber());
            clientLoanDetails.setHouseOwned(loanAccountDTO.getHouseOwned());
            clientLoanDetails.setLengthOfServiceInYears(loanAccountDTO.getLengthOfServiceInYears());
            clientLoanDetails.setNumberOfDependants(loanAccountDTO.getNumberOfDependants());
            ClientLoanDetails clientLoanDetails1 = clientLoanDetailsRepository.saveAndFlush(clientLoanDetails);

            loanDetails.setClientLoanDetails(clientLoanDetails1);
            loanDetailsRepository.saveAndFlush(loanDetails);

            addClientLoanDetailsResponse.setClientLoanDetailsId(clientLoanDetails1.getClientLoanDetailsId());
            addClientLoanDetailsResponse.setMessage("Client Loan Details created successfully.");
            addClientLoanDetailsResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addClientLoanDetailsResponse.setErrorMessage("Exception in creating new Client Loan Details.");
            addClientLoanDetailsResponse.setApiStatusSuccessful(false);
        }

        return addClientLoanDetailsResponse;
    }
}
