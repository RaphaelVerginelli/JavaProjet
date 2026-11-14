package fr.formation.musicstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import fr.formation.musicstore.entity.Client;
import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.entity.Panier;
import fr.formation.musicstore.repository.PanierRepository;

@Service
public class PanierService {

    private final PanierRepository panierRepository;

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    // Retourne directement l'entité Panier (compatible avec panierService.findById(panierId))
    public Panier findById(Long id) {
        return panierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'id : " + id));
    }

    // Créer un panier pour un client
    public Panier creerPanier(Client client) {
        Panier panier = new Panier();
        panier.setClient(client);
        panier.setArticles(new ArrayList<>());
        panier.setMontant(BigDecimal.ZERO);
        return panierRepository.save(panier);
    }

    // Ajouter un instrument au panier
    public Panier ajouterInstrument(Long panierId, Instrument instrument) {
        Panier panier = findById(panierId);
        if (panier.getArticles() == null) {
            panier.setArticles(new ArrayList<>());
        }
        panier.getArticles().add(instrument);
        panier.setMontant(calculerTotal(panier));
        return panierRepository.save(panier);
    }

    // Retirer un instrument du panier
    public Panier retirerInstrument(Long panierId, Instrument instrument) {
        Panier panier = findById(panierId);
        if (panier.getArticles() != null) {
            panier.getArticles().remove(instrument);
            panier.setMontant(calculerTotal(panier));
        }
        return panierRepository.save(panier);
    }

    // Calculer le montant total du panier
    public BigDecimal calculerTotal(Panier panier) {
        if (panier.getArticles() == null || panier.getArticles().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return panier.getArticles().stream()
                .map(Instrument::getPrix)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Vider le panier (utilisé notamment après validation d'une commande)
    public Panier viderPanier(Long panierId) {
        Panier panier = findById(panierId);
        if (panier.getArticles() != null) {
            panier.getArticles().clear();
        }
        panier.setMontant(BigDecimal.ZERO);
        return panierRepository.save(panier);
    }
}