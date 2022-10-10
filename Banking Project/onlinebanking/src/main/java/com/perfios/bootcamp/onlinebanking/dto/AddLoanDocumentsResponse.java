package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

import java.util.List;

@Data
public class AddLoanDocumentsResponse extends CommonServiceResponse {
    private List<Long> loanDocumentsId;
}
