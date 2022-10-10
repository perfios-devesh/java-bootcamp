package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

import java.util.Date;

@Data
public class LoanAccountResponseDTO extends CommonServiceResponse {
    private Long clientLoanDetailsId;
    private Long accountNumber;
    private String type;
    private Double balance;
    private Date openingDate;
    private Date lastTransactionDate;
    private Long branchId;
    private Long clientId;
    private String branchName;
    private String firstName;
    private String lastName;
    private Long registeredMobileNumber;
    private String email;
    private Long aadhaarNumber;
    private String upiId;
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
    private String status;
    private Long idProofDocumentId;
    private Long addressProofDocumentId;
    private Long bankStatementDocumentId;
    private String idProofDocumentType;
    private String addressProofDocumentType;
    private String bankStatementDocumentType;

}
