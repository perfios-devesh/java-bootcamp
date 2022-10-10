package com.perfios.bootcamp.onlinebanking.service;

import com.perfios.bootcamp.onlinebanking.domain.Client;
import com.perfios.bootcamp.onlinebanking.dto.*;
import com.perfios.bootcamp.onlinebanking.repository.ClientRepository;
import com.perfios.bootcamp.onlinebanking.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.perfios.bootcamp.onlinebanking.util.UtilConstants.CLIENT_STATUS_APPLIED;

@Service
@AllArgsConstructor
public class ClientService{
    private final ClientRepository clientRepository;

    public AddClientResponse addClient(ClientDTO clientDTO)
    {
        AddClientResponse addClientResponse = new AddClientResponse();
        try{
            Client client = new Client();
            client.setFirstName(clientDTO.getFirstName());
            client.setLastName(clientDTO.getLastName());
            client.setFatherName(clientDTO.getFatherName());
            client.setMotherName(clientDTO.getMotherName());
            client.setRegisteredMobileNumber(clientDTO.getRegisteredMobileNumber());
            client.setAlternateMobileNumber(clientDTO.getAlternateMobileNumber());
            client.setEmail(clientDTO.getEmail());
            client.setPanNumber(clientDTO.getPanNumber());
            client.setAadhaarNumber(clientDTO.getAadhaarNumber());
            client.setStatus(CLIENT_STATUS_APPLIED);                                    //the client first created is always in applied state. It will be later checked and updated by the bank employee and assigned a bank account
            client.setAccountList(new ArrayList<>());
            client.setUsername(clientDTO.getUsername());
            client.setPassword(clientDTO.getPassword());
            client.setTransactionPassword(clientDTO.getTransactionPassword());
            client.setProfilePassword(clientDTO.getProfilePassword());
            Client client1 =  clientRepository.saveAndFlush(client);

            addClientResponse.setClientId(client1.getClientId());
            addClientResponse.setFirstName(client1.getFirstName());
            addClientResponse.setLastName(client1.getLastName());
            addClientResponse.setMessage("User created successfully.");
            addClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addClientResponse.setErrorMessage("Exception in creating new User.");
            addClientResponse.setApiStatusSuccessful(false);
        }

        return addClientResponse;
    }

    public UpdateClientResponse updateClientDetails(ClientDTO clientDTO)
    {
        UpdateClientResponse updateClientResponse = new UpdateClientResponse();
        try{
            Client client = clientRepository.getReferenceById(clientDTO.getClientId());
            client.setRegisteredMobileNumber(clientDTO.getRegisteredMobileNumber());
            client.setAlternateMobileNumber(clientDTO.getAlternateMobileNumber());
            client.setEmail(clientDTO.getEmail());
            Client client1 =  clientRepository.saveAndFlush(client);

            updateClientResponse.setClientId(client1.getClientId());
            updateClientResponse.setMessage("User updated successfully.");
            updateClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            updateClientResponse.setErrorMessage("Exception in creating new User.");
            updateClientResponse.setApiStatusSuccessful(false);
        }

        return updateClientResponse;
    }

    public UpdateClientResponse setPasswordsFirstTime(ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = new UpdateClientResponse();
        try{
            Client client = clientRepository.getReferenceById(clientAllPasswordsDTO.getClientId());
            if(client.getTransactionPassword() == null && client.getProfilePassword()==null){
                client.setProfilePassword(clientAllPasswordsDTO.getProfilePassword());
                client.setTransactionPassword(clientAllPasswordsDTO.getTransactionPassword());
                Client client1 =  clientRepository.saveAndFlush(client);
                updateClientResponse.setClientId(client1.getClientId());
                updateClientResponse.setMessage("User updated successfully.");
                updateClientResponse.setApiStatusSuccessful(true);
            }
            else {
                throw new RuntimeException("Passwords are already set.");
            }
        }
        catch (RuntimeException e){
            updateClientResponse.setErrorMessage(e.getMessage());
            updateClientResponse.setApiStatusSuccessful(false);
        }
        catch(Exception e){
            updateClientResponse.setErrorMessage("Exception in creating new User.");
            updateClientResponse.setApiStatusSuccessful(false);
        }

        return updateClientResponse;
    }

    public UpdateClientResponse setProfilePassword(ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = new UpdateClientResponse();
        try{
            Client client = clientRepository.getReferenceById(clientAllPasswordsDTO.getClientId());
                client.setProfilePassword(clientAllPasswordsDTO.getProfilePassword());

                Client client1 =  clientRepository.saveAndFlush(client);
                updateClientResponse.setClientId(client1.getClientId());
                updateClientResponse.setMessage("User updated successfully.");
                updateClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            updateClientResponse.setErrorMessage("Exception in updating profile password of User.");
            updateClientResponse.setApiStatusSuccessful(false);
        }

        return updateClientResponse;
    }

    public UpdateClientResponse setTransactionPassword(ClientAllPasswordsDTO clientAllPasswordsDTO)
    {
        UpdateClientResponse updateClientResponse = new UpdateClientResponse();
        try{
            Client client = clientRepository.getReferenceById(clientAllPasswordsDTO.getClientId());
            client.setTransactionPassword(clientAllPasswordsDTO.getTransactionPassword());

            Client client1 =  clientRepository.saveAndFlush(client);
            updateClientResponse.setClientId(client1.getClientId());
            updateClientResponse.setMessage("User updated successfully.");
            updateClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            updateClientResponse.setErrorMessage("Exception in updating transaction password of User.");
            updateClientResponse.setApiStatusSuccessful(false);
        }

        return updateClientResponse;
    }

    public UpdateClientResponse forgotPassword(ClientPasswordDTO clientPasswordDTO)
    {
        UpdateClientResponse updateClientResponse = new UpdateClientResponse();
        try{
            Client client = clientRepository.findByUsername(clientPasswordDTO.getUsername());
            client.setPassword(clientPasswordDTO.getPassword());

            Client client1 =  clientRepository.saveAndFlush(client);

            updateClientResponse.setClientId(client1.getClientId());
            updateClientResponse.setMessage("User password updated successfully.");
            updateClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            updateClientResponse.setErrorMessage("Exception in changing password.");
            updateClientResponse.setApiStatusSuccessful(false);
        }

        return updateClientResponse;
    }
    public ClientsResponseDTO getClientDetails(Long clientId)
    {
        ClientsResponseDTO clientsResponseDTO = new ClientsResponseDTO();
        try{
            List<ClientDTO> clientDTOS = new ArrayList<>();
            Client client = clientRepository.getReferenceById(clientId);
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClientId(client.getClientId());
            clientDTO.setFirstName(client.getFirstName());
            clientDTO.setLastName(client.getLastName());
            clientDTO.setFatherName(client.getFatherName());
            clientDTO.setMotherName(client.getMotherName());
            clientDTO.setRegisteredMobileNumber(client.getRegisteredMobileNumber());
            clientDTO.setAlternateMobileNumber(client.getAlternateMobileNumber());
            clientDTO.setEmail(client.getEmail());
            clientDTO.setPanNumber(client.getPanNumber());
            clientDTO.setAadhaarNumber(client.getAadhaarNumber());
            clientDTO.setStatus(client.getStatus());
            clientDTO.setUsername(client.getUsername());

            clientDTOS.add(clientDTO);
            clientsResponseDTO.setClientDTOS(clientDTOS);
            clientsResponseDTO.setMessage("Client Details fetched successfully.");
            clientsResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            clientsResponseDTO.setErrorMessage("Exception in fetching Client Details.");
            clientsResponseDTO.setApiStatusSuccessful(false);
        }

        return clientsResponseDTO;
    }

    public ClientsResponseDTO getAllClientDetails()
    {
        ClientsResponseDTO clientsResponseDTO = new ClientsResponseDTO();
        try{
            List<ClientDTO> clientDTOS = new ArrayList<>();
            List<Client> clientList = clientRepository.findAll();

            for(Client client : clientList){
                if(! client.getStatus().equals(UtilConstants.CLIENT_STATUS_BANK)) {
                    ClientDTO clientDTO = new ClientDTO();
                    clientDTO.setClientId(client.getClientId());
                    clientDTO.setFirstName(client.getFirstName());
                    clientDTO.setLastName(client.getLastName());
                    clientDTO.setFatherName(client.getFatherName());
                    clientDTO.setMotherName(client.getMotherName());
                    clientDTO.setRegisteredMobileNumber(client.getRegisteredMobileNumber());
                    clientDTO.setAlternateMobileNumber(client.getAlternateMobileNumber());
                    clientDTO.setEmail(client.getEmail());
                    clientDTO.setPanNumber(client.getPanNumber());
                    clientDTO.setAadhaarNumber(client.getAadhaarNumber());
                    clientDTO.setStatus(client.getStatus());
                    clientDTO.setUsername(client.getUsername());
                    clientDTOS.add(clientDTO);
                }
            }

            clientsResponseDTO.setClientDTOS(clientDTOS);
            clientsResponseDTO.setMessage("All Clients fetched successfully.");
            clientsResponseDTO.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            clientsResponseDTO.setErrorMessage("Exception in fetching all clients.");
            clientsResponseDTO.setApiStatusSuccessful(false);
        }

        return clientsResponseDTO;
    }


    public AddClientResponse approveClient(Long clientId)
    {
        AddClientResponse addClientResponse = new AddClientResponse();
        try{
            Client client = clientRepository.getReferenceById(clientId);

            client.setStatus(UtilConstants.CLIENT_STATUS_APPROVED);

            Client client1 = clientRepository.saveAndFlush(client);

            addClientResponse.setClientId(client1.getClientId());
            addClientResponse.setMessage("Client is Approved");
            addClientResponse.setApiStatusSuccessful(true);
        }
        catch(Exception e){
            addClientResponse.setErrorMessage("Exception in approving client.");
            addClientResponse.setApiStatusSuccessful(false);
        }
        return addClientResponse;
    }
}
