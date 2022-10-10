package com.perfios.bootcamp.onlinebanking.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class AccountDTO {
    private Long accountNumber;
    private String type;
    private Double minimumBalance;
    private Double balance;
    private Date openingDate;
    private Date lastTransactionDate;
    private Long branchId;
    private Long clientId;
    private Long loanId;
}
