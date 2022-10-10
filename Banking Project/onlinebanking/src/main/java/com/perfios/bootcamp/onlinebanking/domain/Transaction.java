package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Double amount;
    private Date date;
    private String status;
    private String message;
    private String transactionType;             //UPI, NEFT , RTGS , Withdrawal , Deposit
    @ManyToOne
    @JoinColumn(name = "debitAccount")
    private Account debitAccount;
    @ManyToOne
    @JoinColumn(name = "creditAccount")
    private Account creditAccount;

}
