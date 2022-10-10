package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LoanDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;
    private String documentName;
    private String documentType;
    @Lob
    private byte[] data;

    private String documentFor;                    //For which reason is this document uploaded, for eg. salary proof, house electricity bill, aadhaar etc.
    @ManyToOne
    @JoinColumn(name = "client_loan_details_id")
    private ClientLoanDetails clientLoanDetails;
}
