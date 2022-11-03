package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

import java.util.List;

@Data
public class AllTrainersDTO extends CommonServiceResponse {
    private List<TrainerDTO> trainerDTOS;
}
