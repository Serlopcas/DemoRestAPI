package com.SLC.clients.service;

import com.SLC.clients.exception.NotFoundException;
import com.SLC.clients.model.Client;
import com.SLC.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    //CREATE
    public Client create(Client client) {
        repo.save(client);
        return client;
    }

    //READ ALL
    public List<Client> getAll() {
        return repo.findAll();
    }

    //READ ONE
    public Client getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with this ID: " + id));
    }

    //UPDATE
    public Client modify(Long id, Client newClientData) {
        Client prevClient = getById(id);
        prevClient.setName(newClientData.getName());
        prevClient.setEmail(newClientData.getEmail());
        prevClient.setActive(newClientData.isActive());
        repo.save(prevClient);
        return prevClient;
    }

    //DELETE
    public void delete(Long id) {
        Client client = getById(id);
        repo.delete(client);
    }

    //CUSTOM SEARCHES
    public List<Client> findByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Client> findByEmail(String email){
        return repo.findByEmailContainingIgnoreCase(email);
    }

    public List<Client> findByActive(boolean isActive){
        return repo.findByActive(isActive);
    }
}