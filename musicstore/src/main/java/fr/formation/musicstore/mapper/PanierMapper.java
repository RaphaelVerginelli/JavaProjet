package fr.formation.musicstore.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.formation.musicstore.dto.PanierDTO;
import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.entity.Panier;

@Component
public class PanierMapper {

    public PanierDTO toDTO(Panier panier) {
        if (panier == null) {
            return null;
        }
        return PanierDTO.builder()
                .id(panier.getId())
                .clientIds(panier.getClient() != null ? panier.getClient().getId() : null)
                .articlesIds(panier.getArticles() != null
                        ? panier.getArticles().stream().map(Instrument::getId).toList()
                        : List.of())
                .montant(panier.getMontant() != null ? panier.getMontant() : panier.getMontant())
                .build();
    }
}