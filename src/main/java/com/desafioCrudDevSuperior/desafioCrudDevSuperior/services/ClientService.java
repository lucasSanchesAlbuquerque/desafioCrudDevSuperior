package com.desafioCrudDevSuperior.desafioCrudDevSuperior.services;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.repositories.ClientRepository;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        Client c = new Client();
        c.setName(dto.getName());
        c.setCpf(dto.getCpf());
        c.setIncome(dto.getIncome());
        c.setBirthDate(dto.getBirthDate());
        c.setChildren(dto.getChildren());
        c = repository.save(c);

        return new ClientDto(c);
    }


}
