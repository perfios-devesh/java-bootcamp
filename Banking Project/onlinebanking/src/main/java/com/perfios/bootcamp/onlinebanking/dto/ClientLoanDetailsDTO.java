package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

@Data
public class ClientLoanDetailsDTO {
    private Long clientLoanDetailsId;
    private Integer ageInYears;
    private Integer lengthOfServiceInYears;
    private String employmentDetails;
    private String houseOwned;
    private Integer numberOfDependants;
    private Integer cibilScore;
    private Boolean guarantorAvailable;
    private String guarantorName;
    private String guarantorMobileNumber;
    private String guarantorPanNumber;
    private Long loanId;
}
