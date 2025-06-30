package com.nccformation.gestiondestock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nccformation.gestiondestock.dto.MouvementStockDto;
import com.nccformation.gestiondestock.model.MouvementStock;
import com.nccformation.gestiondestock.repository.MouvementStockRepository;
import com.nccformation.gestiondestock.service.MouvementStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MouvementStockServiceImpl implements MouvementStockService {
    @Autowired
    private final MouvementStockRepository mouvementStockRepository;

    @Override
    public MouvementStockDto save(MouvementStockDto dto) {
        MouvementStock entity = MouvementStockDto.toEntity(dto);
        if (entity.getId() != null) {
            MouvementStock existing = mouvementStockRepository.findById(entity.getId()).orElse(null);
            if (existing != null) {
                entity.setCreationDate(existing.getCreationDate());
            } else {
                throw new RuntimeException("Mouvement de stock non trouvé pour mise à jour");
            }
        }
        MouvementStock saved = mouvementStockRepository.save(entity);
        return MouvementStockDto.fromEntity(saved);
    }

    @Override
    public MouvementStockDto findById(Integer id) {
        MouvementStock entity = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement de stock non trouvé avec id: " + id));
        return MouvementStockDto.fromEntity(entity);
    }

    @Override
    public List<MouvementStockDto> findAll() {
        return mouvementStockRepository.findAll().stream().map(MouvementStockDto::fromEntity).toList();
    }

    @Override
    public void delete(Integer id) {
        if (!mouvementStockRepository.existsById(id)) {
            throw new RuntimeException("Mouvement de stock non trouvé avec id: " + id);
        }
        mouvementStockRepository.deleteById(id);
    }
} 