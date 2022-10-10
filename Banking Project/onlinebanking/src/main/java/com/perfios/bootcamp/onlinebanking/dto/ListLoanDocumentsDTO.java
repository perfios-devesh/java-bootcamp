package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListLoanDocumentsDTO {
    List<LoanDocumentsDTO> loanDocumentsDTOList;
    private Long clientLoanDetailsId;
}
