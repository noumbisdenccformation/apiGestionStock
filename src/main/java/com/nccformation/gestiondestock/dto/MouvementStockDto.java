package com.nccformation.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.nccformation.gestiondestock.model.MouvementStock;
import com.nccformation.gestiondestock.model.TypeMouvementStock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MouvementStockDto {
    private Integer id;
    private ArticleDto article;
    private Instant dateMouvement;
    private BigDecimal quantite;
    private TypeMouvementStock typeMouvement;
    private String sourceMouvement;

    public static MouvementStockDto fromEntity(MouvementStock entity) {
        if (entity == null) return null;
        return MouvementStockDto.builder()
            .id(entity.getId())
            .article(ArticleDto.fromEntity(entity.getArticle()))
            .dateMouvement(entity.getDateMouvement())
            .quantite(entity.getQuantite())
            .typeMouvement(entity.getTypeMouvement())
            .sourceMouvement(entity.getSourceMouvement())
            .build();
    }

    public static MouvementStock toEntity(MouvementStockDto dto) {
        if (dto == null) return null;
        MouvementStock entity = new MouvementStock();
        entity.setId(dto.getId());
        entity.setArticle(ArticleDto.toEntity(dto.getArticle()));
        entity.setDateMouvement(dto.getDateMouvement());
        entity.setQuantite(dto.getQuantite());
        entity.setTypeMouvement(dto.getTypeMouvement());
        entity.setSourceMouvement(dto.getSourceMouvement());
        return entity;
    }
} 