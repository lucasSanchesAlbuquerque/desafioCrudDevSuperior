package com.desafioCrudDevSuperior.desafioCrudDevSuperior.services;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Page<ClientDto> findAll(Pageable pageable){
        Page<Client> clients = repository.findAll(pageable);
        return  clients.map(x -> new ClientDto(x));
    }


}
