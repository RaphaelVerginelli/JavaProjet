package fr.formation.musicstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import fr.formation.musicstore.entity.Client;
import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.entity.Panier;
import fr.formation.musicstore.entity.TypeInstrument;
import fr.formation.musicstore.repository.PanierRepository;

class PanierServiceTest {

    @Mock
    private PanierRepository panierRepository;

    @InjectMocks
    private PanierService panierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjoutAuPanierEtCalculTotal() {

        Client client = new Client(1L, "Dupont", "Jean", "jean@mail.com", "0612345678");
        Panier panier = new Panier(1L, client, new ArrayList<>(), BigDecimal.ZERO);
        
        Instrument instrument = new Instrument(10L, "Guitare", "Fender", TypeInstrument.GUITARE, new BigDecimal("250.00"), 5, "Super guitare");

        when(panierRepository.findById(1L)).thenReturn(Optional.of(panier));
        when(panierRepository.save(any(Panier.class))).thenAnswer(invocation -> invocation.getArgument(0));


        Panier panierMisAJour = panierService.ajouterInstrument(1L, instrument);


        assertNotNull(panierMisAJour);
        assertEquals(1, panierMisAJour.getArticles().size());
        // Vérifie que le montant total est calculé correctement (250.00)
        assertEquals(0, new BigDecimal("250.00").compareTo(panierMisAJour.getMontant()));
        verify(panierRepository, times(1)).save(panier);
    }

    @Test
    void testSuppressionDuPanier() {

        Client client = new Client(1L, "Dupont", "Jean", "jean@mail.com", "0612345678");
        Instrument instrument = new Instrument(10L, "Guitare", "Fender", TypeInstrument.GUITARE, new BigDecimal("250.00"), 5, "Super guitare");
        
        ArrayList<Instrument> articles = new ArrayList<>();
        articles.add(instrument);
        
        Panier panier = new Panier(1L, client, articles, new BigDecimal("250.00"));

        when(panierRepository.findById(1L)).thenReturn(Optional.of(panier));
        when(panierRepository.save(any(Panier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Panier panierMisAJour = panierService.retirerInstrument(1L, instrument);

        assertNotNull(panierMisAJour);
        assertTrue(panierMisAJour.getArticles().isEmpty());
        // Vérifie que le montant total est recalculé à zéro
        assertEquals(0, BigDecimal.ZERO.compareTo(panierMisAJour.getMontant()));
    }
}