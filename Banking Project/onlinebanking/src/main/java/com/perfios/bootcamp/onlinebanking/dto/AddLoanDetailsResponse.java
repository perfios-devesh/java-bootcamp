package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

@Data
public class AddLoanDetailsResponse extends CommonServiceResponse {
    private Long loanId;
}
