package com.SLC.clients.controller;

import com.SLC.clients.model.Client;
import com.SLC.clients.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Client> getClients() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Client getClientById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public List<Client> getClientsByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/email/{email}")
    @ResponseBody
    public List<Client> getClientsByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/active/{isActive}")
    @ResponseBody
    public List<Client> getClientsByActive(@PathVariable boolean isActive) {
        return service.findByActive(isActive);
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
        return ResponseEntity.status(201).body(service.create(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modify(@PathVariable Long id, @Valid @RequestBody Client client) {
        return ResponseEntity.status(201).body(service.modify(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
