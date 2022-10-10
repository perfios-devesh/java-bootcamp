package com.perfios.bootcamp.onlinebanking.web.rest;

import com.perfios.bootcamp.onlinebanking.domain.LoanDocuments;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.service.*;
import com.perfios.bootcamp.onlinebanking.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/banking")
@AllArgsConstructor
public class RestApiController {
    private final ClientService clientService;
    private final BranchService branchService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final LoanDetailsService loanDetailsService;
    private final LoanDocumentsService loanDocumentsService;
    private final ClientLoanDetailsService clientLoanDetailsService;

    @CrossOrigin
    @PostMapping(value = "/client")
    public ResponseEntity addClient(@RequestBody ClientDTO clientDTO)
    {
        AddClientResponse addClientResponse = null;
        try {
            addClientResponse = clientService.addClient(clientDTO);
        } catch (Exception e) {
            addClientResponse = new AddClientResponse();
            addClientResponse.setApiStatusSuccessful(false);
        }
        if (addClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addClientResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody ClientPasswordDTO clientPasswordDTO)
    {
        LoginResponseDTO loginResponseDTO = null;
        try {
            loginResponseDTO = userService.login(clientPasswordDTO);
        } catch (Exception e) {
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setApiStatusSuccessful(false);
        }
        if (loginResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(loginResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(loginResponseDTO);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/client")
    public ResponseEntity updateClientDetails(@RequestBody ClientDTO clientDTO)
    {
        UpdateClientResponse updateClientResponse = null;
        try {
            updateClientResponse = clientService.updateClientDetails(clientDTO);
        } catch (Exception e) {
            updateClientResponse = new UpdateClientResponse();
            updateClientResponse.setApiStatusSuccessful(false);
        }
        if (updateClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateClientResponse);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/client/passwords")
    public ResponseEntity setPasswordsFirstTime(@RequestBody ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = null;
        try {
            updateClientResponse = clientService.setPasswordsFirstTime(clientAllPasswordsDTO);
        } catch (Exception e) {
            updateClientResponse = new UpdateClientResponse();
            updateClientResponse.setApiStatusSuccessful(false);
        }
        if (updateClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateClientResponse);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/client/profilePassword")
    public ResponseEntity setProfilePassword(@RequestBody ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = null;
        try {
            updateClientResponse = clientService.setProfilePassword(clientAllPasswordsDTO);
        } catch (Exception e) {
            updateClientResponse = new UpdateClientResponse();
            updateClientResponse.setApiStatusSuccessful(false);
        }
        if (updateClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateClientResponse);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/client/transactionPassword")
    public ResponseEntity setTransactionPassword(@RequestBody ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = null;
        try {
            updateClientResponse = clientService.setTransactionPassword(clientAllPasswordsDTO);
        } catch (Exception e) {
            updateClientResponse = new UpdateClientResponse();
            updateClientResponse.setApiStatusSuccessful(false);
        }
        if (updateClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateClientResponse);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/client/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody ClientPasswordDTO clientPasswordDTO)
    {
        UpdateClientResponse updateClientResponse = null;
        try {
            updateClientResponse = clientService.forgotPassword(clientPasswordDTO);
        } catch (Exception e) {
            updateClientResponse = new UpdateClientResponse();
            updateClientResponse.setApiStatusSuccessful(false);
        }
        if (updateClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateClientResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/client")
    public ResponseEntity getClientDetails(@RequestParam(name = "clientId", required = false) Long clientId)
    {
        ClientsResponseDTO clientsResponseDTO = null;
        try {
            if(clientId == null)
                clientsResponseDTO = clientService.getAllClientDetails();
            else
                clientsResponseDTO = clientService.getClientDetails(clientId);
        } catch (Exception e) {
            clientsResponseDTO = new ClientsResponseDTO();
            clientsResponseDTO.setApiStatusSuccessful(false);
        }
        if (clientsResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(clientsResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(clientsResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/branch")
    public ResponseEntity addBranch(@RequestBody BranchDTO branchDTO)
    {
        AddBranchResponse addBranchResponse = null;
        try {
            addBranchResponse = branchService.addBranch(branchDTO);
        } catch (Exception e) {
            addBranchResponse = new AddBranchResponse();
            addBranchResponse.setApiStatusSuccessful(false);
        }
        if (addBranchResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addBranchResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addBranchResponse);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/branch")
    public ResponseEntity updateBranch(@RequestBody BranchDTO branchDTO)
    {
        UpdateBranchResponse updateBranchResponse = null;
        try {
            updateBranchResponse = branchService.updateBranch(branchDTO);
        } catch (Exception e) {
            updateBranchResponse = new UpdateBranchResponse();
            updateBranchResponse.setApiStatusSuccessful(false);
        }
        if (updateBranchResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(updateBranchResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(updateBranchResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/branch")
    public ResponseEntity getBranches()
    {
        BranchesResponseDTO branchesResponseDTO = null;
        try {
            branchesResponseDTO = branchService.getBranches();
        } catch (Exception e) {
            branchesResponseDTO = new BranchesResponseDTO();
            branchesResponseDTO.setApiStatusSuccessful(false);
        }
        if (branchesResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(branchesResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(branchesResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/account")
    public ResponseEntity addAccount(@RequestBody AccountDTO accountDTO)
    {
        AddAccountResponse addAccountResponse = null;
        try {
            addAccountResponse = accountService.addAccount(accountDTO);
        } catch (Exception e) {
            addAccountResponse = new AddAccountResponse();
            addAccountResponse.setApiStatusSuccessful(false);
        }
        if (addAccountResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addAccountResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addAccountResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/account/all")
    public ResponseEntity getAccounts(@RequestParam Long clientId)
    {
        AccountsResponseDTO accountsResponseDTO = null;
        try {
            accountsResponseDTO = accountService.getAccounts(clientId);
        } catch (Exception e) {
            accountsResponseDTO = new AccountsResponseDTO();
            accountsResponseDTO.setApiStatusSuccessful(false);
        }
        if (accountsResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(accountsResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(accountsResponseDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/account")
    public ResponseEntity getUsableAccounts(@RequestParam Long clientId)
    {
        AccountsResponseDTO accountsResponseDTO = null;
        try {
            accountsResponseDTO = accountService.getUsableAccounts(clientId);
        } catch (Exception e) {
            accountsResponseDTO = new AccountsResponseDTO();
            accountsResponseDTO.setApiStatusSuccessful(false);
        }
        if (accountsResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(accountsResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(accountsResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/transaction")
    public ResponseEntity addTransaction(@RequestBody NewTransactionDTO newTransactionDTO)
    {
        TransactionResponseDTO transactionResponseDTO = null;
        try {
            transactionResponseDTO = transactionService.newTransaction(newTransactionDTO);
        } catch (Exception e) {
            transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setApiStatusSuccessful(false);
        }
        if (transactionResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(transactionResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(transactionResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/transaction/withdraw")
    public ResponseEntity withdraw(@RequestBody NewTransactionDTO newTransactionDTO)
    {
        newTransactionDTO.setTransactionType(UtilConstants.TRANSACTION_TYPE_WITHDRAWAL);
        newTransactionDTO.setCreditAccountNumber(UtilConstants.BANK_ATM_ACCOUNT_NUMBER);
        TransactionResponseDTO transactionResponseDTO = null;
        try {
            transactionResponseDTO = transactionService.newTransaction(newTransactionDTO);
        } catch (Exception e) {
            transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setApiStatusSuccessful(false);
        }
        if (transactionResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(transactionResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(transactionResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/transaction/deposit")
    public ResponseEntity deposit(@RequestBody NewTransactionDTO newTransactionDTO)
    {
        newTransactionDTO.setTransactionType(UtilConstants.TRANSACTION_TYPE_DEPOSIT);
        newTransactionDTO.setDebitAccountNumber(UtilConstants.BANK_ATM_ACCOUNT_NUMBER);
        TransactionResponseDTO transactionResponseDTO = null;
        try {
            transactionResponseDTO = transactionService.newTransaction(newTransactionDTO);
        } catch (Exception e) {
            transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setApiStatusSuccessful(false);
        }
        if (transactionResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(transactionResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(transactionResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/transaction/all")
    public ResponseEntity getAllTransactions(@RequestBody AccountNumberDTO accountNumberDTO)
    {
        AllTransactionsDTO allTransactionsDTO = null;
        try {
            allTransactionsDTO = transactionService.getAllTransactions(accountNumberDTO.getAccountNumber());
        } catch (Exception e) {
            allTransactionsDTO = new AllTransactionsDTO();
            allTransactionsDTO.setApiStatusSuccessful(false);
        }
        if (allTransactionsDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(allTransactionsDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(allTransactionsDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/client/approve")
    public ResponseEntity approveClient(@RequestParam Long clientId)
    {
        AddClientResponse addClientResponse = null;
        try {
            addClientResponse = clientService.approveClient(clientId);
        } catch (Exception e) {
            addClientResponse = new AddClientResponse();
            addClientResponse.setApiStatusSuccessful(false);
        }
        if (addClientResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addClientResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addClientResponse);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/account/approve")
    public ResponseEntity approveAccount(@RequestParam Long accountNumber)
    {
        AddAccountResponse addAccountResponse = null;
        try {
            addAccountResponse = accountService.approveAccount(accountNumber);
        } catch (Exception e) {
            addAccountResponse = new AddAccountResponse();
            addAccountResponse.setApiStatusSuccessful(false);
        }
        if (addAccountResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addAccountResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addAccountResponse);
        }
    }


    @CrossOrigin
    @PostMapping(value = "/loan")
    public ResponseEntity createLoanAccount(@RequestBody LoanAccountDTO loanAccountDTO)
    {
        AddLoanDetailsResponse addLoanDetailsResponse = null;
        AddAccountResponse addAccountResponse = null;
        AddClientLoanDetailsResponse addClientLoanDetailsResponse =null;
        try {
            addLoanDetailsResponse = loanDetailsService.addLoanDetails(loanAccountDTO);
            if(addLoanDetailsResponse.getApiStatusSuccessful()){
                loanAccountDTO.setLoanId(addLoanDetailsResponse.getLoanId());
                addAccountResponse = accountService.addLoanAccount(loanAccountDTO);
                if(addAccountResponse.getApiStatusSuccessful()){
                    loanAccountDTO.setAccountNumber(addAccountResponse.getAccountNumber());
                    addClientLoanDetailsResponse = clientLoanDetailsService.addClientLoanDetails(loanAccountDTO);
                }
                else{
                    throw new RuntimeException();
                }
            }
            else{
                throw new RuntimeException();
            }
        } catch (Exception e) {
            addLoanDetailsResponse = new AddLoanDetailsResponse();
            addLoanDetailsResponse.setApiStatusSuccessful(false);
            addAccountResponse = new AddAccountResponse();
            addAccountResponse.setApiStatusSuccessful(false);
            addClientLoanDetailsResponse = new AddClientLoanDetailsResponse();
            addClientLoanDetailsResponse.setApiStatusSuccessful(false);
        }
        if (addLoanDetailsResponse.getApiStatusSuccessful() && addAccountResponse.getApiStatusSuccessful() && addClientLoanDetailsResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addClientLoanDetailsResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addClientLoanDetailsResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/loan")
    public ResponseEntity getLoanAccount(@RequestParam Long accountNumber)
    {
        LoanAccountResponseDTO loanAccountResponseDTO = null;
        try {
            loanAccountResponseDTO = accountService.getLoanDetails(accountNumber);
        } catch (Exception e) {
            loanAccountResponseDTO = new LoanAccountResponseDTO();
            loanAccountResponseDTO.setApiStatusSuccessful(false);
        }
        if (loanAccountResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(loanAccountResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(loanAccountResponseDTO);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/loan/uploadDocuments")
    public ResponseEntity uploadDocuments(@RequestParam("idProof") MultipartFile idProofFile, @RequestParam("addressProof") MultipartFile addressProofFile, @RequestParam("bankStatement") MultipartFile bankStatementFile , @RequestParam("clientLoanDetailsId") Long clientLoanDetailsId)
    {
        AddLoanDocumentsResponse addLoanDocumentsResponse = null;
        try {
            AddAllLoanDocumentsDTO addAllLoanDocumentsDTO = new AddAllLoanDocumentsDTO();
            List<AddLoanDocumentDTO> addLoanDocumentDTOList = new ArrayList<>();

            AddLoanDocumentDTO idProofAddLoanDocumentDTO = new AddLoanDocumentDTO();
            idProofAddLoanDocumentDTO.setDocumentFor("ID Proof");
            idProofAddLoanDocumentDTO.setMultipartFile(idProofFile);
            addLoanDocumentDTOList.add(idProofAddLoanDocumentDTO);

            AddLoanDocumentDTO addressProofAddLoanDocumentDTO = new AddLoanDocumentDTO();
            addressProofAddLoanDocumentDTO.setDocumentFor("Address Proof");
            addressProofAddLoanDocumentDTO.setMultipartFile(addressProofFile);
            addLoanDocumentDTOList.add(addressProofAddLoanDocumentDTO);

            AddLoanDocumentDTO bankStatementAddLoanDocumentDTO = new AddLoanDocumentDTO();
            bankStatementAddLoanDocumentDTO.setDocumentFor("Bank Statement");
            bankStatementAddLoanDocumentDTO.setMultipartFile(bankStatementFile);
            addLoanDocumentDTOList.add(bankStatementAddLoanDocumentDTO);

            addAllLoanDocumentsDTO.setAddLoanDocumentDTOList(addLoanDocumentDTOList);
            addAllLoanDocumentsDTO.setClientLoanDetailsId(clientLoanDetailsId);

            addLoanDocumentsResponse = loanDocumentsService.addLoanDocuments(addAllLoanDocumentsDTO);

        } catch (Exception e) {
            addLoanDocumentsResponse = new AddLoanDocumentsResponse();
            addLoanDocumentsResponse.setApiStatusSuccessful(false);
        }
        if (addLoanDocumentsResponse.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(addLoanDocumentsResponse);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(addLoanDocumentsResponse);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/loan/documents")
    public ResponseEntity getDocument(@RequestParam Long loanDocumentId)
    {
        LoanDocuments fileDB = loanDocumentsService.getFile(loanDocumentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getDocumentName() + "\"")
                .body(fileDB.getData());
    }

    @CrossOrigin
    @PostMapping(value = "/loan/approve")
    public ResponseEntity approveLoanAccount(@RequestParam Long accountNumber)
    {
        LoanAccountResponseDTO loanAccountResponseDTO = null;
        try {
            loanAccountResponseDTO = accountService.approveLoan(accountNumber);
        } catch (Exception e) {
            loanAccountResponseDTO = new LoanAccountResponseDTO();
            loanAccountResponseDTO.setApiStatusSuccessful(false);
        }
        if (loanAccountResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(loanAccountResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(loanAccountResponseDTO);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/loan/details")
    public ResponseEntity getLoanDetails(@RequestParam Long accountNumber)
    {
        LoanDetailsResponseDTO loanDetailsResponseDTO = null;
        try {
            loanDetailsResponseDTO = loanDetailsService.getLoanDetails(accountNumber);
        } catch (Exception e) {
            loanDetailsResponseDTO = new LoanDetailsResponseDTO();
            loanDetailsResponseDTO.setApiStatusSuccessful(false);
        }
        if (loanDetailsResponseDTO.getApiStatusSuccessful()) {
            return ResponseEntity.ok().header("Status","Successful").body(loanDetailsResponseDTO);
        } else {
            return ResponseEntity.status(400).header("Status","Failed").body(loanDetailsResponseDTO);
        }
    }
}
