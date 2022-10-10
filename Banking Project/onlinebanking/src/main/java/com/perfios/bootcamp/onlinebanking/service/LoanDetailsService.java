package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.LoanDetails;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.repository.AccountRepository;
import com.perfios.bootcamp.onlinebanking.repository.LoanDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoanDetailsService {

    private final LoanDetailsRepository loanDetailsRepository;
    private final AccountRepository accountRepository;


    public AddLoanDetailsResponse addLoanDetails(LoanAccountDTO loanAccountDTO)
    {
        AddLoanDetailsResponse addLoanDetailsResponse = new AddLoanDetailsResponse();
        try{
            LoanDetails loanDetails = new LoanDetails();
            loanDetails.setLoanAmount(loanAccountDTO.getLoanAmount());
            loanDetails.setLoanPeriod(loanAccountDTO.getLoanPeriod());

            LoanDetails loanDetails1 = loanDetailsRepository.saveAndFlush(loanDetails);

            addLoanDetailsResponse.setLoanId(loanDetails1.getLoanId());
            addLoanDetailsResponse.setMessage("Loan Details created successfully.");
            addLoanDetailsResponse.setApiStatusSuccessful(true);


        }
        catch(Exception e){
            addLoanDetailsResponse.setErrorMessage("Exception in creating new Loan Details.");
            addLoanDetailsResponse.setApiStatusSuccessful(false);
        }

        return addLoanDetailsResponse;
    }

    public LoanDetailsResponseDTO getLoanDetails(Long accountNumber)
    {
        LoanDetailsResponseDTO loanDetailsResponseDTO = new LoanDetailsResponseDTO();
        try{
            LoanDetails loanDetails = accountRepository.getReferenceById(accountNumber).getLoanDetails();

            loanDetailsResponseDTO.setLoanId(loanDetails.getLoanId());
            loanDetailsResponseDTO.setLoanAmount(loanDetails.getLoanAmount());
            loanDetailsResponseDTO.setLoanPeriod(loanDetails.getLoanPeriod());
            loanDetailsResponseDTO.setRoi(loanDetails.getRoi());
            loanDetailsResponseDTO.setEmi(loanDetails.getEmi());
            loanDetailsResponseDTO.setAccountNumber(loanDetails.getAccount().getAccountNumber());
            loanDetailsResponseDTO.setMessage("Loan Details fetched successfully.");
            loanDetailsResponseDTO.setApiStatusSuccessful(true);


        }
        catch(Exception e){
            loanDetailsResponseDTO.setErrorMessage("Exception in fetching Loan Details.");
            loanDetailsResponseDTO.setApiStatusSuccessful(false);
        }

        return loanDetailsResponseDTO;
    }


}
