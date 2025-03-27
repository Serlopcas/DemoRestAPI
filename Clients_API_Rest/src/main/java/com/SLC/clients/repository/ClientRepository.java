package com.SLC.clients.repository;

import com.SLC.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByActive(boolean active);

    List<Client> findByNameContainingIgnoreCase(String name);

    List<Client> findByEmailContainingIgnoreCase(String email);
}
