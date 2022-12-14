package com.perfios.bootcamp.onlinebanking.util;

public interface UtilConstants {

    String BANK_NAME = "PERFIOS BANK";
    Long BANK_ATM_ACCOUNT_NUMBER = Long.valueOf(3);
    Integer ROLE_LEVEL_USER = 2;
    Integer ROLE_LEVEL_ADMIN = 1;
    Integer ROLE_LEVEL_CLIENT = 0;
    String USER_ROLE_CLIENT = "Client";
    String USER_ROLE_BANK_USER = "Bank User";
    String UPI_ID_EXTENSION = "@per";
    String ACCOUNT_TYPE_SALARY_ACCOUNT = "SALARY ACCOUNT";
    String ACCOUNT_TYPE_LOAN_ACCOUNT = "LOAN ACCOUNT";
    String ACCOUNT_TYPE_SAVINGS_ACCOUNT = "SAVINGS ACCOUNT";
    String TRANSACTION_TYPE_UPI = "UPI";
    String TRANSACTION_TYPE_NEFT = "NEFT";
    String TRANSACTION_TYPE_RTGS = "RTGS";
    String TRANSACTION_TYPE_WITHDRAWAL = "WITHDRAWAL";
    String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    String CLIENT_STATUS_APPLIED = "APPLIED";
    String CLIENT_STATUS_APPROVED = "APPROVED";
    String CLIENT_STATUS_BANK = "BANK";

    String ACCOUNT_STATUS_APPLIED = "APPLIED";
    String ACCOUNT_STATUS_APPROVED = "APPROVED";
    String ACCOUNT_STATUS_CLOSURE_REQUESTED = "CLOSURE REQUESTED";
    String ACCOUNT_STATUS_CLOSED = "CLOSED";
    String TRANSACTION_STATUS_SUCCESSFUL = "SUCCESSFUL";
    String TRANSACTION_STATUS_PROCESSING = "PROCESSING";
    String TRANSACTION_STATUS_FAILED = "FAILED";

    Integer PASSWORD_STATUS_SET = 1;
    Integer PASSWORD_STATUS_NOT_SET = 0;



}
