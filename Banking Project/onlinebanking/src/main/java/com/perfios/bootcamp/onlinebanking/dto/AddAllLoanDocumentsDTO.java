package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddAllLoanDocumentsDTO {
    List<AddLoanDocumentDTO> addLoanDocumentDTOList;
    private Long clientLoanDetailsId;
}
