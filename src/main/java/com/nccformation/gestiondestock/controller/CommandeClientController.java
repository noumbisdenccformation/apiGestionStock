package com.nccformation.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nccformation.gestiondestock.dto.CommandeClientDto;
import com.nccformation.gestiondestock.service.CommandeClientService;

@RestController
@RequestMapping("/api/commandes-clients")
public class CommandeClientController {

    @Autowired
    private CommandeClientService commandeClientService;

    @PostMapping
    public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeClientService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeClientDto> update(@PathVariable Integer id, @RequestBody CommandeClientDto dto) {
        try {
            dto.setId(id);
            return ResponseEntity.ok(commandeClientService.save(dto));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(commandeClientService.findById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 