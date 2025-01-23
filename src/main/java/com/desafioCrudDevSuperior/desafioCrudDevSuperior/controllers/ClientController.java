package com.desafioCrudDevSuperior.desafioCrudDevSuperior.controllers;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.dto.ClientDto;
import com.desafioCrudDevSuperior.desafioCrudDevSuperior.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
