package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoanAccountDTO {
    private Long clientLoanDetailsId;

    private Long accountNumber;
    private String type;
    private Double balance;
    private Date openingDate;
    private Date lastTransactionDate;
    private Long branchId;
    private Long clientId;
    private Long loanId;

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

    private Double loanAmount;
    private Double loanPeriod;
    private Double emi;
    private Double roi;
}
