package com.example.service.elearning.service;

import com.example.service.elearning.domain.Trainer;
import com.example.service.elearning.domain.UserNameDetails;
import com.example.service.elearning.dto.AddTrainerResponse;
import com.example.service.elearning.dto.AllTrainersDTO;
import com.example.service.elearning.dto.TrainerDTO;
import com.example.service.elearning.repository.TrainerRepository;
import com.example.service.elearning.repository.UserNameDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserNameDetailsRepository userNameDetailsRepository;

    public AddTrainerResponse addTrainer(TrainerDTO trainerDTO) {
        AddTrainerResponse addTrainerResponse = new AddTrainerResponse();
        try {
            UserNameDetails userNameDetails = new UserNameDetails();
            userNameDetails.setUsername(trainerDTO.getUsername());
            userNameDetails.setPassword(trainerDTO.getPassword());
            userNameDetails.setRole("TRAINER");

            UserNameDetails userNameDetails1 = userNameDetailsRepository.saveAndFlush(userNameDetails);

            Trainer trainer = new Trainer();
            trainer.setCourse(new ArrayList<>());
            trainer.setFirstName(trainerDTO.getFirstName());
            trainer.setLastName(trainerDTO.getLastName());
            trainer.setEmail(trainerDTO.getEmail());
            trainer.setMobileNumber(trainerDTO.getMobileNumber());
            trainer.setUserNameDetails(userNameDetails1);

            Trainer trainer1 = trainerRepository.saveAndFlush(trainer);



            addTrainerResponse.setTrainerId(trainer1.getTrainerId());
            addTrainerResponse.setUsername(userNameDetails1.getUsername());
            addTrainerResponse.setFirstName(trainer1.getFirstName());
            addTrainerResponse.setLastName(trainer1.getLastName());
            addTrainerResponse.setMobileNumber(trainer1.getMobileNumber());
            addTrainerResponse.setEmail(trainer1.getEmail());
            addTrainerResponse.setMessage("Added Trainer Successfully");
            addTrainerResponse.setApiStatusSuccessful(true);
        } catch (Exception e) {
            addTrainerResponse.setErrorMessage("Exception in adding trainer.");
            addTrainerResponse.setApiStatusSuccessful(false);
        }
        return addTrainerResponse;
    }

    public AllTrainersDTO getTrainer() {
        AllTrainersDTO allTrainersDTO = new AllTrainersDTO();
        try {
            List<Trainer> trainerList = trainerRepository.findAll();
            List<TrainerDTO> trainerDTOS = new ArrayList<>();
            for (Trainer trainer : trainerList) {
                TrainerDTO trainerDTO = new TrainerDTO();
                trainerDTO.setTrainerId(trainer.getTrainerId());
                trainerDTO.setFirstName(trainer.getFirstName());
                trainerDTO.setLastName(trainer.getLastName());
                trainerDTO.setEmail(trainer.getEmail());
                trainerDTO.setMobileNumber(trainer.getMobileNumber());
                trainerDTO.setCourses(trainer.getCourse());
                trainerDTOS.add(trainerDTO);
            }

            allTrainersDTO.setTrainerDTOS(trainerDTOS);
            allTrainersDTO.setMessage("Fetched Trainers Successfully");
            allTrainersDTO.setApiStatusSuccessful(true);
        } catch (Exception e) {
            allTrainersDTO.setErrorMessage("Exception in fetching trainers.");
            allTrainersDTO.setApiStatusSuccessful(false);
        }
        return allTrainersDTO;
    }
}
