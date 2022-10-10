package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddLoanDocumentDTO {
    MultipartFile multipartFile;
    private String documentFor;
}
