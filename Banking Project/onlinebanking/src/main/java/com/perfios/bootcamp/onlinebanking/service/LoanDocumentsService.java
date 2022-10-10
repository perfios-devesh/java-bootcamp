package com.perfios.bootcamp.onlinebanking.service;


import com.perfios.bootcamp.onlinebanking.domain.ClientLoanDetails;
import com.perfios.bootcamp.onlinebanking.domain.LoanDocuments;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.repository.ClientLoanDetailsRepository;
import com.perfios.bootcamp.onlinebanking.repository.LoanDocumentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class LoanDocumentsService {
    private final ClientLoanDetailsRepository clientLoanDetailsRepository;
    private final LoanDocumentsRepository loanDocumentsRepository;

    public AddLoanDocumentsResponse addLoanDocuments(AddAllLoanDocumentsDTO addAllLoanDocumentsDTO)
    {
        AddLoanDocumentsResponse addLoanDocumentsResponse = new AddLoanDocumentsResponse();
        List<Long> loanDocumentsIdList = new ArrayList<>();
        List<LoanDocuments> loanDocumentsList = new ArrayList<>();
        try{
            ClientLoanDetails clientLoanDetails = clientLoanDetailsRepository.getReferenceById(addAllLoanDocumentsDTO.getClientLoanDetailsId());

            for(AddLoanDocumentDTO addLoanDocumentDTO : addAllLoanDocumentsDTO.getAddLoanDocumentDTOList()){
                LoanDocuments loanDocuments = new LoanDocuments();
                loanDocuments.setDocumentFor(addLoanDocumentDTO.getDocumentFor());

                MultipartFile multipartFile = addLoanDocumentDTO.getMultipartFile();
                loanDocuments.setDocumentName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                loanDocuments.setDocumentType(multipartFile.getContentType());
                loanDocuments.setData(multipartFile.getBytes());

                loanDocuments.setClientLoanDetails(clientLoanDetails);
                LoanDocuments loanDocuments1 = loanDocumentsRepository.saveAndFlush(loanDocuments);
                loanDocumentsIdList.add(loanDocuments1.getDocumentId());
                loanDocumentsList.add(loanDocuments1);
            }

//            clientLoanDetails.setLoanDocumentsList(loanDocumentsList);
//            clientLoanDetailsRepository.saveAndFlush(clientLoanDetails);

            addLoanDocumentsResponse.setLoanDocumentsId(loanDocumentsIdList);
            addLoanDocumentsResponse.setMessage("Loan Documents created successfully.");
            addLoanDocumentsResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addLoanDocumentsResponse.setErrorMessage("Exception in creating new Loan Documents.");
            addLoanDocumentsResponse.setApiStatusSuccessful(false);
        }

        return addLoanDocumentsResponse;
    }

    public AddLoanDocumentsResponse addIdProof(AddLoanDocumentDTO addLoanDocumentDTO , Long clientLoanDetailsId)
    {
        AddLoanDocumentsResponse addLoanDocumentsResponse = new AddLoanDocumentsResponse();
        List<Long> loanDocumentsIdList = new ArrayList<>();
        List<LoanDocuments> loanDocumentsList = new ArrayList<>();
        try{
            ClientLoanDetails clientLoanDetails = clientLoanDetailsRepository.getReferenceById(clientLoanDetailsId);
                LoanDocuments loanDocuments = new LoanDocuments();
                loanDocuments.setDocumentFor(addLoanDocumentDTO.getDocumentFor());

                MultipartFile multipartFile = addLoanDocumentDTO.getMultipartFile();
                loanDocuments.setDocumentName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                loanDocuments.setDocumentType(multipartFile.getContentType());
                loanDocuments.setData(multipartFile.getBytes());

                loanDocuments.setClientLoanDetails(clientLoanDetails);
                LoanDocuments loanDocuments1 = loanDocumentsRepository.saveAndFlush(loanDocuments);
                loanDocumentsIdList.add(loanDocuments1.getDocumentId());
                loanDocumentsList.add(loanDocuments1);

//            clientLoanDetails.setLoanDocumentsList(loanDocumentsList);
//            clientLoanDetailsRepository.saveAndFlush(clientLoanDetails);

            addLoanDocumentsResponse.setLoanDocumentsId(loanDocumentsIdList);
            addLoanDocumentsResponse.setMessage("ID Proof created successfully.");
            addLoanDocumentsResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addLoanDocumentsResponse.setErrorMessage("Exception in creating new ID Proof Document.");
            addLoanDocumentsResponse.setApiStatusSuccessful(false);
        }

        return addLoanDocumentsResponse;
    }

    public LoanDocuments getFile(Long id) {
        return loanDocumentsRepository.getReferenceById(id);
    }

    public Stream<LoanDocuments> getAllFiles() {
        return loanDocumentsRepository.findAll().stream();
    }

}
