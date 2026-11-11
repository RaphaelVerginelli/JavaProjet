package fr.formation.musicstore.dto;

import fr.formation.musicstore.entity.StatutCommande;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private Long id;
    private Long clientId;
    private List<Long> articleIds;
    private LocalDateTime dateCommande;
    private BigDecimal montant;
    private StatutCommande statut;
}