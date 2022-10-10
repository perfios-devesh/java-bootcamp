package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

import java.util.List;

@Data
public class AllTransactionsDTO extends CommonServiceResponse {
    private Double balance;
    List<TransactionResponseDTO> transactionResponseDTOS;
}
