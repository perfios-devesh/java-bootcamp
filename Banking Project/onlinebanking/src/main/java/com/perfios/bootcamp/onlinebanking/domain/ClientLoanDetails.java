package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ClientLoanDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinColumn(name="loan_details")
    private LoanDetails loanDetails;

    @OneToMany(mappedBy = "clientLoanDetails")
    private List<LoanDocuments> loanDocumentsList;
}
