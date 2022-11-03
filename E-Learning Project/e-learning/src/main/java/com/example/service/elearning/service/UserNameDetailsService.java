package com.example.service.elearning.service;

import com.example.service.elearning.domain.Trainer;
import com.example.service.elearning.domain.UserNameDetails;
import com.example.service.elearning.domain.Users;
import com.example.service.elearning.dto.LoginDetailsDTO;
import com.example.service.elearning.dto.LoginResponseDTO;
import com.example.service.elearning.repository.UserNameDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class UserNameDetailsService {
    private final UserNameDetailsRepository userNameDetailsRepository;

    public LoginResponseDTO login(LoginDetailsDTO loginDetailsDTO){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        try {
            UserNameDetails userNameDetails = userNameDetailsRepository.findByUsernameAndPassword(loginDetailsDTO.getUsername() , loginDetailsDTO.getPassword());

            if(userNameDetails == null){
                throw new Exception("User Not Present");
            }
            else{
                if(userNameDetails.getRole().equals("TRAINER")){
                    Trainer trainer = userNameDetails.getTrainer();
                    loginResponseDTO.setName(trainer.getFirstName()+ " " + trainer.getLastName());
                    loginResponseDTO.setTrainerId(trainer.getTrainerId());
                }
                else{
                    Users users = userNameDetails.getUsers();
                    loginResponseDTO.setName(users.getFirstName() + " " + users.getLastName());
                    loginResponseDTO.setUserId(users.getUserId());
                }
                loginResponseDTO.setUsername(userNameDetails.getUsername());
                loginResponseDTO.setRole(userNameDetails.getRole());
                loginResponseDTO.setMessage("Added Course Successfully");
                loginResponseDTO.setApiStatusSuccessful(true);
            }
        } catch (Exception e) {
            loginResponseDTO.setErrorMessage(e.getMessage());
            loginResponseDTO.setApiStatusSuccessful(false);
        }
        return loginResponseDTO;
    }
}
