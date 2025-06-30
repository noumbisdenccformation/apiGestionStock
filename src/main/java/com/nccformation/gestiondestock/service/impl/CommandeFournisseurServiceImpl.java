package com.nccformation.gestiondestock.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.nccformation.gestiondestock.dto.CommandeFournisseurDto;
import com.nccformation.gestiondestock.service.CommandeFournisseurService;
import com.nccformation.gestiondestock.model.CommandeFournisseur;
import com.nccformation.gestiondestock.repository.CommandeFournisseurRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    @Autowired
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        CommandeFournisseur entity = CommandeFournisseurDto.toEntity(dto);
        if (entity.getId() != null) {
            CommandeFournisseur existing = commandeFournisseurRepository.findById(entity.getId()).orElse(null);
            if (existing != null) {
                entity.setCreationDate(existing.getCreationDate());
            } else {
                throw new RuntimeException("Commande fournisseur non trouvée pour mise à jour");
            }
        }
        CommandeFournisseur saved = commandeFournisseurRepository.save(entity);
        return CommandeFournisseurDto.fromEntity(saved);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        CommandeFournisseur entity = commandeFournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande fournisseur non trouvée avec id: " + id));
        return CommandeFournisseurDto.fromEntity(entity);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).toList();
    }

    @Override
    public void delete(Integer id) {
        if (!commandeFournisseurRepository.existsById(id)) {
            throw new RuntimeException("Commande fournisseur non trouvée avec id: " + id);
        }
        commandeFournisseurRepository.deleteById(id);
    }
} 