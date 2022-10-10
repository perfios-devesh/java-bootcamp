package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AccountsResponseDTO extends CommonServiceResponse {

    List<AccountDetailsDTO> accountDetailsDTOS;

}
