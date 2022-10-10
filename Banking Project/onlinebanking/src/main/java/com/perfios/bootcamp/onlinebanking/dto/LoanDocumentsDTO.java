package com.perfios.bootcamp.onlinebanking.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LoanDocumentsDTO {
    private Long documentId;
    MultipartFile multipartFile;
    private String documentFor;
}
