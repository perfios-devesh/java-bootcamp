package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Client;
import com.perfios.bootcamp.onlinebanking.dto.AddClientResponse;
import com.perfios.bootcamp.onlinebanking.dto.ClientDTO;
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
public class ClientServiceTest {
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

    @Test
    void addClientSuccessfully() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Abhinav");
        clientDTO.setLastName("Bakshi");
        clientDTO.setFatherName("Anil Bakshi");
        clientDTO.setMotherName("Aunty");
        clientDTO.setRegisteredMobileNumber(7006028654l);
        clientDTO.setAlternateMobileNumber(7006028655l);
        clientDTO.setEmail("abhinavprathamreckless@gmail.com");
        clientDTO.setPanNumber("BLMPO2566F");
        clientDTO.setAadhaarNumber(569988562388L);
        clientDTO.setUsername("bakshiAbhinav");
        clientDTO.setPassword("Organic@99");

        Client client1 = new Client();
        client1.setFirstName("Abhinav");
        client1.setLastName("Bakshi");
        client1.setRegisteredMobileNumber(7006028654l);
        client1.setEmail("abhinavprathamreckless@gmail.com");
        client1.setAadhaarNumber(569988562388L);
        when(clientRepository.saveAndFlush(any())).thenReturn(client1);

        AddClientResponse addClientResponse = clientService.addClient(clientDTO);
        assertTrue(addClientResponse.getApiStatusSuccessful());
    }
}
