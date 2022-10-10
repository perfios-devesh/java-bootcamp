package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String type;
    private Double minimumBalance;
    private Double balance;
    private Date openingDate;
    private Date lastTransactionDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "debitAccount")
    private List<Transaction> debitTransactionList;
    @OneToMany(mappedBy = "creditAccount")
    private List<Transaction> creditTransactionList;
    @OneToOne
    @JoinColumn(name="loan_details")
    private LoanDetails loanDetails;

}
