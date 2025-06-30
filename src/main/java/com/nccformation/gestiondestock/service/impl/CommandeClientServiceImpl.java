package com.nccformation.gestiondestock.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.nccformation.gestiondestock.dto.CommandeClientDto;
import com.nccformation.gestiondestock.service.CommandeClientService;
import com.nccformation.gestiondestock.model.CommandeClient;
import com.nccformation.gestiondestock.repository.CommandeClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandeClientServiceImpl implements CommandeClientService {
    @Autowired
    private final CommandeClientRepository commandeClientRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        CommandeClient entity = CommandeClientDto.toEntity(dto);
        if (entity.getId() != null) {
            CommandeClient existing = commandeClientRepository.findById(entity.getId()).orElse(null);
            if (existing != null) {
                entity.setCreationDate(existing.getCreationDate());
            } else {
                throw new RuntimeException("Commande client non trouvée pour mise à jour");
            }
        }
        CommandeClient saved = commandeClientRepository.save(entity);
        return CommandeClientDto.fromEntity(saved);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        CommandeClient entity = commandeClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande client non trouvée avec id: " + id));
        return CommandeClientDto.fromEntity(entity);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity).toList();
    }

    @Override
    public void delete(Integer id) {
        if (!commandeClientRepository.existsById(id)) {
            throw new RuntimeException("Commande client non trouvée avec id: " + id);
        }
        commandeClientRepository.deleteById(id);
    }
} 