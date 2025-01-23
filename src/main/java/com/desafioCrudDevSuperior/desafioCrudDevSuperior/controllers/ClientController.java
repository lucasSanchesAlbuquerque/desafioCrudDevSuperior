package com.desafioCrudDevSuperior.desafioCrudDevSuperior.controllers;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    private ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<ClientDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping()
    private ResponseEntity<ClientDto> insert(@RequestBody ClientDto dto){
        ClientDto clientDto =  service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientDto.getIncome())
                .toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

}
