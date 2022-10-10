package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Account;
import com.perfios.bootcamp.onlinebanking.domain.Branch;
import com.perfios.bootcamp.onlinebanking.domain.Client;
import com.perfios.bootcamp.onlinebanking.dto.AccountDTO;
import com.perfios.bootcamp.onlinebanking.dto.AddAccountResponse;
import com.perfios.bootcamp.onlinebanking.repository.AccountRepository;
import com.perfios.bootcamp.onlinebanking.repository.BranchRepository;
import com.perfios.bootcamp.onlinebanking.repository.ClientRepository;
import com.perfios.bootcamp.onlinebanking.repository.LoanDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    BranchRepository branchRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    LoanDetailsRepository loanDetailsRepository;
    @InjectMocks
    ClientLoanDetailsService clientLoanDetailsService;
    @InjectMocks
    AccountService accountService;
    @InjectMocks
    BranchService branchService;
    @InjectMocks
    ClientService clientService;

    Long branchId;
    Long clientId;


    @Test
    void addAccountSuccessfully() {

        Branch branch1 = new Branch();
        branch1.setBranchId(1L);
        branch1.setBranchName("Kormangala");
        when(branchRepository.getReferenceById(any())).thenReturn(branch1);

        Client client1 = new Client();
        client1.setFirstName("Abhinav");
        client1.setLastName("Bakshi");
        client1.setRegisteredMobileNumber(7006028654L);
        client1.setEmail("abhinavprathamreckless@gmail.com");
        client1.setAadhaarNumber(569988562388L);
        when(clientRepository.getReferenceById(any())).thenReturn(client1);

        Account account1 = new Account();
        account1.setAccountNumber(1L);
        account1.setBranch(branch1);
        account1.setClient(client1);
        when(accountRepository.saveAndFlush(any())).thenReturn(account1);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setType("SAVINGS BANK");
        accountDTO.setBranchId(branchId);
        accountDTO.setClientId(clientId);
        AddAccountResponse addAccountResponse = accountService.addAccount(accountDTO);
        assertTrue(addAccountResponse.getApiStatusSuccessful());
    }
}