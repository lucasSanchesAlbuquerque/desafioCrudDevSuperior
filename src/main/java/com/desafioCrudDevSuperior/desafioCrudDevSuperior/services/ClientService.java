package com.desafioCrudDevSuperior.desafioCrudDevSuperior.services;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.repositories.ClientRepository;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.exceptions.DatabaseException;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Page<ClientDto> findAll(Pageable pageable){
        Page<Client> clients = repository.findAll(pageable);
        return  clients.map(x -> new ClientDto(x));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));;
        return new ClientDto(client);
    }


    @Transactional
    public ClientDto insert(ClientDto dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client = repository.save(client);

        return new ClientDto(client);
    }

    public ClientDto update(Long id, ClientDto dto){
        try {
            Client client = repository.getReferenceById(id);
            client.setName(dto.getName());
            client.setCpf(dto.getCpf());
            client.setIncome(dto.getIncome());
            client.setBirthDate(dto.getBirthDate());
            client.setChildren(dto.getChildren());
            client = repository.save(client);

            return new ClientDto(client);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recuso não encontrado");
        }

    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
