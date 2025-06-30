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

import com.nccformation.gestiondestock.dto.CommandeFournisseurDto;
import com.nccformation.gestiondestock.service.CommandeFournisseurService;

@RestController
@RequestMapping("/api/commandes-fournisseurs")
public class CommandeFournisseurController {

    @Autowired
    private CommandeFournisseurService commandeFournisseurService;

    @PostMapping
    public ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeFournisseurService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeFournisseurDto> update(@PathVariable Integer id, @RequestBody CommandeFournisseurDto dto) {
        try {
            dto.setId(id);
            return ResponseEntity.ok(commandeFournisseurService.save(dto));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeFournisseurDto> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(commandeFournisseurService.findById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 