package fr.formation.musicstore.mapper;

import fr.formation.musicstore.dto.CommandeDTO;

import fr.formation.musicstore.entity.Commande;
import fr.formation.musicstore.entity.Instrument;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandeMapper {
    public CommandeDTO toDTO(Commande commande) {

        if (commande == null) {
            return null;
        }
        CommandeDTO dto = CommandeDTO.builder()
            .id(commande.getId())
            .clientId(commande.getClient() != null ? commande.getClient().getId() : null)
            .articleIds(commande.getArticles() != null
                ? commande.getArticles().stream().map(Instrument::getId).toList()
                : List.of())
            .dateCommande(commande.getDateCommande())
            .montant(commande.getMontant())
            .statut(commande.getStatut())
            .build();
        
        return dto;
    }
}