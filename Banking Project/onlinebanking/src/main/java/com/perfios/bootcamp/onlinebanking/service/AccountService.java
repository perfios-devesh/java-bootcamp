package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.*;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.repository.AccountRepository;
import com.perfios.bootcamp.onlinebanking.repository.BranchRepository;
import com.perfios.bootcamp.onlinebanking.repository.ClientRepository;
import com.perfios.bootcamp.onlinebanking.repository.LoanDetailsRepository;
import com.perfios.bootcamp.onlinebanking.util.UtilConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final ClientRepository clientRepository;
    private final LoanDetailsRepository loanDetailsRepository;

    private final ClientLoanDetailsService clientLoanDetailsService;

    public AddAccountResponse addAccount(AccountDTO accountDTO)
    {
        AddAccountResponse addAccountResponse = new AddAccountResponse();
        try{
            Branch branch = branchRepository.getReferenceById(accountDTO.getBranchId());
            Client client = clientRepository.getReferenceById(accountDTO.getClientId());
            Account account = new Account();
            account.setBranch(branch);
            account.setClient(client);
            if(accountDTO.getType().equals(UtilConstants.ACCOUNT_TYPE_SAVINGS_ACCOUNT))
                account.setMinimumBalance(2000.0);
            else
                account.setMinimumBalance(0.0);
            account.setType(accountDTO.getType());
            account.setBalance(0.0);
            account.setCreditTransactionList(new ArrayList<>());
            account.setDebitTransactionList(new ArrayList<>());
            account.setOpeningDate(new Date());
            account.setLastTransactionDate(new Date());
            account.setStatus(UtilConstants.ACCOUNT_STATUS_APPLIED);

            Account account1 = accountRepository.saveAndFlush(account);

            addAccountResponse.setAccountNumber(account1.getAccountNumber());
            addAccountResponse.setBranchId(account1.getBranch().getBranchId());
            addAccountResponse.setBranchName(account1.getBranch().getBranchName());
            addAccountResponse.setClientId(account1.getClient().getClientId());
            addAccountResponse.setFirstName(account1.getClient().getFirstName());
            addAccountResponse.setLastName(account1.getClient().getLastName());
            addAccountResponse.setRegisteredMobileNumber(account1.getClient().getRegisteredMobileNumber());
            addAccountResponse.setEmail(account1.getClient().getEmail());
            addAccountResponse.setAadhaarNumber(account1.getClient().getAadhaarNumber());
            addAccountResponse.setMessage("Account created successfully.");
            addAccountResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addAccountResponse.setErrorMessage("Exception in creating new Account.");
            addAccountResponse.setApiStatusSuccessful(false);
        }

        return addAccountResponse;
    }

    public AccountsResponseDTO getAccounts(Long clientId)
    {
        AccountsResponseDTO accountsResponseDTO = new AccountsResponseDTO();
        try{
            Client client = clientRepository.getReferenceById(clientId);
            List<Account> accountList = client.getAccountList();
            List<AccountDetailsDTO> accountDetailsDTOS = new ArrayList<>();
            for(Account account: accountList){
                AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
                accountDetailsDTO.setAccountNumber(account.getAccountNumber());
                accountDetailsDTO.setType(account.getType());
                accountDetailsDTO.setBalance(account.getBalance());
                accountDetailsDTO.setStatus(account.getStatus());
                accountDetailsDTO.setBranchId(account.getBranch().getBranchId());
                accountDetailsDTO.setClientId(account.getClient().getClientId());
                accountDetailsDTO.setBranchName(account.getBranch().getBranchName());
                accountDetailsDTO.setClientName(account.getClient().getFirstName() + " " +account.getClient().getLastName());

                accountDetailsDTOS.add(accountDetailsDTO);
            }
            accountsResponseDTO.setAccountDetailsDTOS(accountDetailsDTOS);
            accountsResponseDTO.setMessage("Accounts fetched successfully.");
            accountsResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            accountsResponseDTO.setErrorMessage("Exception in fetching Accounts.");
            accountsResponseDTO.setApiStatusSuccessful(false);
        }

        return accountsResponseDTO;
    }

    public AccountsResponseDTO getUsableAccounts(Long clientId)
    {
        AccountsResponseDTO accountsResponseDTO = new AccountsResponseDTO();
        try{
            Client client = clientRepository.getReferenceById(clientId);
            List<Account> accountList = client.getAccountList();
            List<AccountDetailsDTO> accountDetailsDTOS = new ArrayList<>();
            for(Account account: accountList){
                if(account.getStatus().equals(UtilConstants.CLIENT_STATUS_APPROVED)){
                    AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
                    accountDetailsDTO.setAccountNumber(account.getAccountNumber());
                    accountDetailsDTO.setType(account.getType());
                    accountDetailsDTO.setBalance(account.getBalance());
                    accountDetailsDTO.setStatus(account.getStatus());
                    accountDetailsDTO.setBranchId(account.getBranch().getBranchId());
                    accountDetailsDTO.setClientId(account.getClient().getClientId());
                    accountDetailsDTO.setBranchName(account.getBranch().getBranchName());
                    accountDetailsDTO.setClientName(account.getClient().getFirstName() + " " +account.getClient().getLastName());
                    accountDetailsDTOS.add(accountDetailsDTO);
                }
            }
            accountsResponseDTO.setAccountDetailsDTOS(accountDetailsDTOS);
            accountsResponseDTO.setMessage("Accounts fetched successfully.");
            accountsResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            accountsResponseDTO.setErrorMessage("Exception in fetching Accounts.");
            accountsResponseDTO.setApiStatusSuccessful(false);
        }

        return accountsResponseDTO;
    }


    public AddAccountResponse approveAccount(Long accountNumber)
    {
        AddAccountResponse addAccountResponse = new AddAccountResponse();
        try{
            Account account = accountRepository.getReferenceById(accountNumber);

            account.setStatus(UtilConstants.ACCOUNT_STATUS_APPROVED);

            Account account1 = accountRepository.saveAndFlush(account);

            addAccountResponse.setAccountNumber(account1.getAccountNumber());
            addAccountResponse.setMessage("Account is Approved");
            addAccountResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addAccountResponse.setErrorMessage("Exception in approving account.");
            addAccountResponse.setApiStatusSuccessful(false);
        }
        return addAccountResponse;
    }

    public AddAccountResponse addLoanAccount(LoanAccountDTO loanAccountDTO)
    {
        AddAccountResponse addAccountResponse = new AddAccountResponse();
        try{
            Branch branch = branchRepository.getReferenceById(loanAccountDTO.getBranchId());
            Client client = clientRepository.getReferenceById(loanAccountDTO.getClientId());
            LoanDetails loanDetails = loanDetailsRepository.getReferenceById(loanAccountDTO.getLoanId());
            Account account = new Account();
            account.setBranch(branch);
            account.setClient(client);
            account.setType(UtilConstants.ACCOUNT_TYPE_LOAN_ACCOUNT);
            account.setCreditTransactionList(new ArrayList<>());
            account.setDebitTransactionList(new ArrayList<>());
            account.setOpeningDate(new Date());
            account.setLastTransactionDate(new Date());
            account.setStatus(UtilConstants.ACCOUNT_STATUS_APPLIED);
            account.setLoanDetails(loanDetails);
            Account account1 = accountRepository.saveAndFlush(account);

            loanDetails.setAccount(account1);
            loanDetailsRepository.saveAndFlush(loanDetails);

            addAccountResponse.setAccountNumber(account1.getAccountNumber());
            addAccountResponse.setBranchId(account1.getBranch().getBranchId());
            addAccountResponse.setBranchName(account1.getBranch().getBranchName());
            addAccountResponse.setClientId(account1.getClient().getClientId());
            addAccountResponse.setFirstName(account1.getClient().getFirstName());
            addAccountResponse.setLastName(account1.getClient().getLastName());
            addAccountResponse.setRegisteredMobileNumber(account1.getClient().getRegisteredMobileNumber());
            addAccountResponse.setEmail(account1.getClient().getEmail());
            addAccountResponse.setAadhaarNumber(account1.getClient().getAadhaarNumber());
            addAccountResponse.setLoanId(account1.getLoanDetails().getLoanId());
            addAccountResponse.setMessage("Loan Account created successfully.");
            addAccountResponse.setApiStatusSuccessful(true);


        }
        catch(Exception e){
            addAccountResponse.setErrorMessage("Exception in creating new Loan Account.");
            addAccountResponse.setApiStatusSuccessful(false);
        }

        return addAccountResponse;
    }


    public LoanAccountResponseDTO getLoanDetails(Long accountNumber)
    {
        LoanAccountResponseDTO loanAccountResponseDTO = new LoanAccountResponseDTO();
        try{
            Account account1 = accountRepository.getReferenceById(accountNumber);

            loanAccountResponseDTO.setAccountNumber(accountNumber);
            loanAccountResponseDTO.setBranchId(account1.getBranch().getBranchId());
            loanAccountResponseDTO.setBranchName(account1.getBranch().getBranchName());
            loanAccountResponseDTO.setClientId(account1.getClient().getClientId());
            loanAccountResponseDTO.setFirstName(account1.getClient().getFirstName());
            loanAccountResponseDTO.setLastName(account1.getClient().getLastName());
            loanAccountResponseDTO.setRegisteredMobileNumber(account1.getClient().getRegisteredMobileNumber());
            loanAccountResponseDTO.setEmail(account1.getClient().getEmail());
            loanAccountResponseDTO.setAadhaarNumber(account1.getClient().getAadhaarNumber());
            loanAccountResponseDTO.setType(account1.getType());
            loanAccountResponseDTO.setBalance(account1.getBalance());
            loanAccountResponseDTO.setStatus(account1.getStatus());
            loanAccountResponseDTO.setBranchId(account1.getBranch().getBranchId());
            loanAccountResponseDTO.setClientId(account1.getClient().getClientId());

            LoanDetails loanDetails = account1.getLoanDetails();
            loanAccountResponseDTO.setLoanId(loanDetails.getLoanId());
            loanAccountResponseDTO.setLoanPeriod(loanDetails.getLoanPeriod());
            loanAccountResponseDTO.setLoanAmount(loanDetails.getLoanAmount());
            loanAccountResponseDTO.setEmi(loanDetails.getEmi());
            loanAccountResponseDTO.setRoi(loanDetails.getRoi());

            ClientLoanDetails clientLoanDetails = loanDetails.getClientLoanDetails();
            loanAccountResponseDTO.setClientLoanDetailsId(clientLoanDetails.getClientLoanDetailsId());
            loanAccountResponseDTO.setEmploymentDetails(clientLoanDetails.getEmploymentDetails());
            loanAccountResponseDTO.setCibilScore(clientLoanDetails.getCibilScore());
            loanAccountResponseDTO.setAgeInYears(clientLoanDetails.getAgeInYears());
            loanAccountResponseDTO.setGuarantorAvailable(clientLoanDetails.getGuarantorAvailable());
            loanAccountResponseDTO.setGuarantorMobileNumber(clientLoanDetails.getGuarantorMobileNumber());
            loanAccountResponseDTO.setGuarantorName(clientLoanDetails.getGuarantorName());
            loanAccountResponseDTO.setGuarantorPanNumber(clientLoanDetails.getGuarantorPanNumber());
            loanAccountResponseDTO.setHouseOwned(clientLoanDetails.getHouseOwned());
            loanAccountResponseDTO.setLengthOfServiceInYears(clientLoanDetails.getLengthOfServiceInYears());
            loanAccountResponseDTO.setNumberOfDependants(clientLoanDetails.getNumberOfDependants());

            List<LoanDocuments> loanDocumentsList = clientLoanDetails.getLoanDocumentsList();
            for(LoanDocuments loanDocuments : loanDocumentsList){
                if(loanDocuments.getDocumentFor().equals("ID Proof")){
                    loanAccountResponseDTO.setIdProofDocumentId(loanDocuments.getDocumentId());
                    loanAccountResponseDTO.setIdProofDocumentType(loanDocuments.getDocumentType());
                }
                if(loanDocuments.getDocumentFor().equals("Address Proof")){
                    loanAccountResponseDTO.setAddressProofDocumentId(loanDocuments.getDocumentId());
                    loanAccountResponseDTO.setAddressProofDocumentType(loanDocuments.getDocumentType());
                }
                if(loanDocuments.getDocumentFor().equals("Bank Statement")){
                    loanAccountResponseDTO.setBankStatementDocumentId(loanDocuments.getDocumentId());
                    loanAccountResponseDTO.setBankStatementDocumentType(loanDocuments.getDocumentType());
                }
            }

            loanAccountResponseDTO.setMessage("Loan Account fetched successfully.");
            loanAccountResponseDTO.setApiStatusSuccessful(true);

        }
        catch(Exception e){
            loanAccountResponseDTO.setErrorMessage("Exception in fetching Loan Account.");
            loanAccountResponseDTO.setApiStatusSuccessful(false);
        }

        return loanAccountResponseDTO;
    }

    public LoanAccountResponseDTO approveLoan(Long accountNumber)
    {
        LoanAccountResponseDTO loanAccountResponseDTO = new LoanAccountResponseDTO();
        try{
            Account account = accountRepository.getReferenceById(accountNumber);
            LoanDetails loanDetails = account.getLoanDetails();

            account.setBalance(loanDetails.getLoanAmount());
            account.setMinimumBalance(0.0);
            account.setStatus(UtilConstants.ACCOUNT_STATUS_APPROVED);
            Account account1 = accountRepository.saveAndFlush(account);

            loanDetails.setRoi(10.0);
            Double emiCalculate = (loanDetails.getLoanAmount() + ((loanDetails.getRoi()/100)*loanDetails.getLoanAmount()*loanDetails.getLoanPeriod()) )/ (loanDetails.getLoanPeriod()*12);
            loanDetails.setEmi(emiCalculate);
            LoanDetails loanDetails1 = loanDetailsRepository.saveAndFlush(loanDetails);

            loanAccountResponseDTO.setRoi(loanDetails1.getRoi());
            loanAccountResponseDTO.setEmi(loanDetails1.getEmi());
            loanAccountResponseDTO.setBalance(account1.getBalance());
            loanAccountResponseDTO.setStatus(account1.getStatus());
            loanAccountResponseDTO.setLoanId(loanDetails1.getLoanId());
            loanAccountResponseDTO.setLoanPeriod(loanDetails1.getLoanPeriod());
            loanAccountResponseDTO.setLoanAmount(loanDetails1.getLoanAmount());
            loanAccountResponseDTO.setMessage("Loan Account approved successfully.");
            loanAccountResponseDTO.setApiStatusSuccessful(true);

        }
        catch(Exception e){
            loanAccountResponseDTO.setErrorMessage("Exception in approving Loan Account.");
            loanAccountResponseDTO.setApiStatusSuccessful(false);
        }

        return loanAccountResponseDTO;
    }
}
