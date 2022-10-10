package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Client;
import com.perfios.bootcamp.onlinebanking.domain.Users;
import com.perfios.bootcamp.onlinebanking.dto.ClientPasswordDTO;
import com.perfios.bootcamp.onlinebanking.dto.LoginResponseDTO;
import com.perfios.bootcamp.onlinebanking.repository.ClientRepository;
import com.perfios.bootcamp.onlinebanking.repository.UserRepository;
import com.perfios.bootcamp.onlinebanking.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;


    public LoginResponseDTO login(ClientPasswordDTO clientPasswordDTO)
    {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        try{
            Client client = clientRepository.findByUsernameAndPassword(clientPasswordDTO.getUsername(),clientPasswordDTO.getPassword());
            if(client!=null && client.getStatus().equals(UtilConstants.CLIENT_STATUS_APPROVED)){
                loginResponseDTO.setApiStatusSuccessful(true);
                loginResponseDTO.setMessage("User login success.");
                loginResponseDTO.setId(client.getClientId());
                loginResponseDTO.setRole(UtilConstants.USER_ROLE_CLIENT);
                loginResponseDTO.setLevel(UtilConstants.ROLE_LEVEL_CLIENT);
                loginResponseDTO.setFullName(client.getFirstName() + " " + client.getLastName());
                if(client.getProfilePassword() == null && client.getTransactionPassword() == null){
                    loginResponseDTO.setPasswordStatus(UtilConstants.PASSWORD_STATUS_NOT_SET);
                }
                else {
                    loginResponseDTO.setPasswordStatus(UtilConstants.PASSWORD_STATUS_SET);
                }
            } else if (client!=null && client.getStatus().equals(UtilConstants.CLIENT_STATUS_APPLIED)) {
                throw new RuntimeException("Please wait for the bank to approve your profile to login.");
            } else
            {
                Users users = userRepository.findByUsernameAndPassword(clientPasswordDTO.getUsername(), clientPasswordDTO.getPassword());
                if (users != null) {
                    loginResponseDTO.setApiStatusSuccessful(true);
                    loginResponseDTO.setMessage("User login success.");
                    loginResponseDTO.setId(users.getUserId());
                    loginResponseDTO.setRole(UtilConstants.USER_ROLE_BANK_USER);
                    loginResponseDTO.setLevel(users.getLevel());
                    loginResponseDTO.setFullName(users.getFirstName() + " " + users.getLastName());
                }
                else
                    throw new RuntimeException("Username Password incorrect.");
            }


        }
        catch(RuntimeException e){
            loginResponseDTO.setErrorMessage(e.getMessage());
            loginResponseDTO.setApiStatusSuccessful(false);
        }
        catch(Exception e){
            loginResponseDTO.setErrorMessage("Wrong Credentials.");
            loginResponseDTO.setApiStatusSuccessful(false);
        }

        return loginResponseDTO;
    }
}
