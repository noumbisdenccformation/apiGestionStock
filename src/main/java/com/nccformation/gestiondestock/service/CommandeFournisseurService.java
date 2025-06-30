package com.nccformation.gestiondestock.service;

import java.util.List;

import com.nccformation.gestiondestock.dto.CommandeFournisseurDto;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto dto);
    CommandeFournisseurDto findById(Integer id);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
} 