package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

@Data
public class AddClientLoanDetailsResponse extends CommonServiceResponse {
    private Long clientLoanDetailsId;
}
