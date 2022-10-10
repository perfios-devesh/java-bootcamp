package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.domain.Branch;
import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddAccountResponse extends CommonServiceResponse {

    private Long accountNumber;
    private Long branchId;
    private String branchName;
    private Long clientId;
    private String firstName;
    private String lastName;
    private Long registeredMobileNumber;
    private String email;
    private Long aadhaarNumber;
    private String upiId;
    private Long loanId;

}
