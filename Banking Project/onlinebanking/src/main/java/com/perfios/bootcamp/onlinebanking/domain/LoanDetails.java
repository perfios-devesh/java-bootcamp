package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LoanDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private Double loanAmount;
    private Double loanPeriod;
    private Double emi;
    private Double roi;

    @OneToOne(mappedBy = "loanDetails")
    private ClientLoanDetails clientLoanDetails;

    @OneToOne(mappedBy = "loanDetails")
    private Account account;
}
