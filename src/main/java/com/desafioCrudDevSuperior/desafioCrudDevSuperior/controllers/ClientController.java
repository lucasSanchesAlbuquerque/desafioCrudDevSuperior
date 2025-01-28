package com.desafioCrudDevSuperior.desafioCrudDevSuperior.controllers;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.ClientService;
import jakarta.validation.Valid;
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
    private ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto){
        ClientDto clientDto =  service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientDto.getIncome())
                .toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<ClientDto> update(@PathVariable Long id,@Valid @RequestBody ClientDto dto){
        ClientDto clientDto = service.update(id,dto);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Void>  delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
