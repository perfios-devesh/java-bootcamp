package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateBranchResponse extends CommonServiceResponse {
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
