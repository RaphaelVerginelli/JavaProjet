package fr.formation.musicstore.service;

import fr.formation.musicstore.entity.StatutCommande;
import fr.formation.musicstore.entity.Commande;
import fr.formation.musicstore.entity.Panier;
import fr.formation.musicstore.entity.Instrument;

import fr.formation.musicstore.exception.ResourceNotFoundException;
import fr.formation.musicstore.exception.BusinessException;

import fr.formation.musicstore.repository.CommandeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final PanierService panierService;
    private final InstrumentService instrumentService;

    public Commande consulterCommande(Long commandeId) {
        return commandeRepository.findById(commandeId)
            .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable : " + commandeId));
    }

    public List<Commande> consulterHistoriqueClient(Long clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    public Commande transformerPanierEnCommande(Long panierId) {
        Panier panier = panierService.findById(panierId);

        if (panier.getArticles() == null || panier.getArticles().isEmpty()) {
            throw new BusinessException("Panier vide : " + panierId);
        }

        for (Instrument instrument : panier.getArticles()) {
            instrumentService.decrementerStock(instrument.getId());
        }

        Commande commande = commandeRepository.save(Commande.builder()
                .client(panier.getClient())
                .articles(panier.getArticles())
                .dateCommande(LocalDateTime.now())
                .montant(panier.getMontant())
                .statut(StatutCommande.VALIDEE)
                .build());

        panierService.viderPanier(panierId);

        return commande;
    }
}