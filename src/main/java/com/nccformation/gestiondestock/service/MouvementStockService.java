package com.nccformation.gestiondestock.service;

import java.util.List;

import com.nccformation.gestiondestock.dto.MouvementStockDto;

public interface MouvementStockService {
    MouvementStockDto save(MouvementStockDto dto);
    MouvementStockDto findById(Integer id);
    List<MouvementStockDto> findAll();
    void delete(Integer id);
} 