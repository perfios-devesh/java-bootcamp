package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

@Data
public class LoginResponseDTO extends CommonServiceResponse {

    private Long id;
    private String fullName;
    private String role;
    private Integer level;
    private Integer passwordStatus;
}
