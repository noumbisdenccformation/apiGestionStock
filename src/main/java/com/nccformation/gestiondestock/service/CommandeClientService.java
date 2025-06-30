package com.nccformation.gestiondestock.service;

import java.util.List;

import com.nccformation.gestiondestock.dto.CommandeClientDto;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
} 