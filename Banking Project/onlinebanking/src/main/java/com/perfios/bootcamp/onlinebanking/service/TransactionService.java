package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Account;
import com.perfios.bootcamp.onlinebanking.domain.Client;
import com.perfios.bootcamp.onlinebanking.domain.Transaction;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.repository.AccountRepository;
import com.perfios.bootcamp.onlinebanking.repository.TransactionRepository;
import com.perfios.bootcamp.onlinebanking.util.SortByDate;
import com.perfios.bootcamp.onlinebanking.util.UtilConstants;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionResponseDTO newTransaction(NewTransactionDTO newTransactionDTO)
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(newTransactionDTO.getTransactionType());
        transaction.setDate(new Date());
        transaction.setAmount(newTransactionDTO.getAmount());

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        try{
            Account debitAccount = accountRepository.getReferenceById(newTransactionDTO.getDebitAccountNumber());
            Account creditAccount = accountRepository.getReferenceById(newTransactionDTO.getCreditAccountNumber());

            debitAccount.getAccountNumber();
            creditAccount.getAccountNumber();
            if(debitAccount.getAccountNumber() != 0)
                transaction.setDebitAccount(debitAccount);

            if(creditAccount.getAccountNumber() != 0)
                transaction.setCreditAccount(creditAccount);

//            transaction.setCreditAccount(creditAccount);
//            transaction.setDebitAccount(debitAccount);

            if(debitAccount.getAccountNumber() != UtilConstants.BANK_ATM_ACCOUNT_NUMBER){
                if(! debitAccount.getClient().getTransactionPassword().equals(newTransactionDTO.getTransactionPassword()))
                    throw new SecurityException("Wrong Transaction Password.");
            }
            else{
                if(! creditAccount.getClient().getTransactionPassword().equals(newTransactionDTO.getTransactionPassword()))
                    throw new SecurityException("Wrong Transaction Password.");
            }



            if(debitAccount.getBalance()>= newTransactionDTO.getAmount()){
                debitAccount.setBalance(debitAccount.getBalance() - newTransactionDTO.getAmount());
                debitAccount.setLastTransactionDate(new Date());
                creditAccount.setBalance(creditAccount.getBalance() + newTransactionDTO.getAmount());
                creditAccount.setLastTransactionDate(new Date());
                accountRepository.saveAndFlush(debitAccount);
                accountRepository.saveAndFlush(creditAccount);
                transaction.setMessage("Transaction Successful");
                transaction.setStatus(UtilConstants.TRANSACTION_STATUS_SUCCESSFUL);
                transactionResponseDTO.setMessage("Transaction Successful.");
            }
            else{
                transaction.setMessage("Not enough balance.");
                transaction.setStatus(UtilConstants.TRANSACTION_STATUS_FAILED);
                transactionResponseDTO.setMessage("Not enough balance.");
            }
            transactionResponseDTO.setApiStatusSuccessful(true);
        }
        catch(EntityNotFoundException e){
            transaction.setMessage("Wrong Account Number");
            transaction.setStatus(UtilConstants.TRANSACTION_STATUS_FAILED);
            transactionResponseDTO.setMessage("Wrong Account Number.");
            transactionResponseDTO.setApiStatusSuccessful(true);
        }
        catch(SecurityException e){
            transaction.setMessage("Wrong Transaction Password.");
            transaction.setStatus(UtilConstants.TRANSACTION_STATUS_FAILED);
            transactionResponseDTO.setMessage("Wrong Transaction Password.");
            transactionResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            transaction.setStatus(UtilConstants.TRANSACTION_STATUS_FAILED);
            transaction.setMessage("Server Error");
            transactionResponseDTO.setErrorMessage("Exception in processing transaction.");
            transactionResponseDTO.setApiStatusSuccessful(false);
        }
        finally {
            Transaction transaction1 = transactionRepository.saveAndFlush(transaction);
            transactionResponseDTO.setTransactionId(transaction1.getTransactionId());
            transactionResponseDTO.setTransactionType(transaction1.getTransactionType());
            transactionResponseDTO.setDate(transaction1.getDate());
            transactionResponseDTO.setAmount(transaction1.getAmount());
            transactionResponseDTO.setStatus(transaction1.getStatus());
            transactionResponseDTO.setCreditAccountNumber(newTransactionDTO.getCreditAccountNumber());
            transactionResponseDTO.setDebitAccountNumber(newTransactionDTO.getDebitAccountNumber());
        }

        return transactionResponseDTO;
    }

    public AllTransactionsDTO getAllTransactions(Long accountNumber){
        AllTransactionsDTO allTransactionsDTO = new AllTransactionsDTO();
        try{
            List<TransactionResponseDTO> transactionResponseDTOS = new ArrayList<>();
            List<Transaction> transactionList = transactionRepository.findAll();

            for(Transaction transaction : transactionList){
                if(transaction.getStatus().equals(UtilConstants.TRANSACTION_STATUS_SUCCESSFUL) && (transaction.getDebitAccount().getAccountNumber()== accountNumber || transaction.getCreditAccount().getAccountNumber()==accountNumber)) {
                    TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
                    transactionResponseDTO.setTransactionId(transaction.getTransactionId());
                    transactionResponseDTO.setTransactionType(transaction.getTransactionType());
                    transactionResponseDTO.setDate(transaction.getDate());
                    transactionResponseDTO.setStatus(transaction.getStatus());
                    transactionResponseDTO.setAmount(transaction.getAmount());
                    transactionResponseDTO.setMessage(transaction.getMessage());
                    transactionResponseDTO.setDebitAccountNumber(transaction.getDebitAccount().getAccountNumber());
                    transactionResponseDTO.setCreditAccountNumber(transaction.getCreditAccount().getAccountNumber());
                    transactionResponseDTO.setDebitClientName(transaction.getDebitAccount().getClient().getFirstName() + " " + transaction.getDebitAccount().getClient().getLastName());
                    transactionResponseDTO.setCreditClientName(transaction.getCreditAccount().getClient().getFirstName() + " " + transaction.getCreditAccount().getClient().getLastName());
                    transactionResponseDTOS.add(transactionResponseDTO);
                }
            }

            Collections.sort(transactionResponseDTOS, new SortByDate());
            allTransactionsDTO.setBalance(accountRepository.getReferenceById(accountNumber).getBalance());
            allTransactionsDTO.setTransactionResponseDTOS(transactionResponseDTOS);
            allTransactionsDTO.setMessage("All transactions fetched successfully.");
            allTransactionsDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            allTransactionsDTO.setErrorMessage("Exception in fetching all transactions.");
            allTransactionsDTO.setApiStatusSuccessful(false);
        }

        return allTransactionsDTO;
    }
}
