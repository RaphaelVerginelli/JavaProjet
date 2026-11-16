package fr.formation.musicstore.service;

import fr.formation.musicstore.dto.CommandeDTO;
import fr.formation.musicstore.entity.*;
import fr.formation.musicstore.exception.BusinessException;
import fr.formation.musicstore.mapper.CommandeMapper;
import fr.formation.musicstore.repository.CommandeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private PanierService panierService;

    @Mock
    private InstrumentService instrumentService;

    @Mock
    private CommandeMapper commandeMapper;

    @InjectMocks
    private CommandeService commandeService;

    @Test
    void transformerPanierEnCommande_Succes() {
        Client client = new Client();
        client.setId(1L);

        Instrument guitare = new Instrument();
        guitare.setId(10L);
        guitare.setPrix(BigDecimal.valueOf(500.0));
        guitare.setQuantiteStock(5);

        Panier panier = new Panier();
        panier.setId(1L);
        panier.setClient(client);
        panier.setArticles(List.of(guitare));
        panier.setMontant(BigDecimal.valueOf(500.0));

        when(panierService.findById(1L)).thenReturn(panier);
        when(commandeRepository.save(any(Commande.class))).thenAnswer(inv -> inv.getArgument(0));

        Commande resultat = commandeService.transformerPanierEnCommande(1L);

        assertNotNull(resultat);
        assertEquals(StatutCommande.VALIDEE, resultat.getStatut());
        assertEquals(BigDecimal.valueOf(500.0), resultat.getMontant());
        verify(instrumentService, times(1)).decrementerStock(10L);
        verify(panierService, times(1)).viderPanier(1L);
    }

    @Test
    void transformerPanierEnCommande_PanierVide_ThrowsBusinessException() {
        Panier panierVide = new Panier();
        panierVide.setId(1L);
        panierVide.setArticles(List.of());

        when(panierService.findById(1L)).thenReturn(panierVide);

        assertThrows(BusinessException.class, () -> {
            commandeService.transformerPanierEnCommande(1L);
        });

        verify(commandeRepository, never()).save(any());
    }

    @Test
    void transformerPanierEnCommande_StockInsuffisant_ThrowsBusinessException() {
        Instrument piano = new Instrument();
        piano.setId(20L);
        piano.setQuantiteStock(0);

        Panier panier = new Panier();
        panier.setId(1L);
        panier.setArticles(List.of(piano));

        when(panierService.findById(1L)).thenReturn(panier);
        doThrow(new BusinessException("Stock insuffisant"))
                .when(instrumentService).decrementerStock(20L);

        assertThrows(BusinessException.class, () -> {
            commandeService.transformerPanierEnCommande(1L);
        });

        verify(commandeRepository, never()).save(any());
    }
}