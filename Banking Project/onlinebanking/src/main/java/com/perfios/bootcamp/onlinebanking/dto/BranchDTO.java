package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class BranchDTO {
    private Long branchId;
    private String bankName;
    private String branchName;
    private String micr;
    private String ifsc;
    private String address;
    private String district;
    private String state;
    private Integer pinCode;
}
