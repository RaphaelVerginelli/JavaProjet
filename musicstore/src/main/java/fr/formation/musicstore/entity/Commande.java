package fr.formation.musicstore.entity;

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
public class Commande {
    private Long id;
    private Client client;
    private List<Instrument> articles;
    private LocalDateTime dateCommande;
    private BigDecimal montant;
    private StatutCommande statut;
}