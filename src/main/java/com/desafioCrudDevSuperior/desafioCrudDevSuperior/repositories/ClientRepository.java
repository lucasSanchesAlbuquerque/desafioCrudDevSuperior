package com.desafioCrudDevSuperior.desafioCrudDevSuperior.repositories;

import com.desafioCrudDevSuperior.desafioCrudDevSuperior.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
