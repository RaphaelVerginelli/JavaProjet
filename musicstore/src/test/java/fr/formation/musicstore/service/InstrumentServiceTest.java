package fr.formation.musicstore.service;

import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.entity.TypeInstrument;
import fr.formation.musicstore.exception.BusinessException;
import fr.formation.musicstore.exception.ResourceNotFoundException;
import fr.formation.musicstore.repository.InstrumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstrumentServiceTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    @InjectMocks
    private InstrumentService instrumentService;

    private Instrument guitare;

    @BeforeEach
    void setUp() {
        guitare = Instrument.builder()
                .id(1L)
                .nom("Stratocaster")
                .marque("Fender")
                .type(TypeInstrument.GUITARE)
                .prix(new BigDecimal("1299.99"))
                .quantiteStock(5)
                .description("Guitare électrique")
                .build();
    }

    // 1. Ajout d'un instrument
    @Test
    @DisplayName("Doit ajouter un instrument valide avec succès")
    void ajouterInstrument_Succes() {
        when(instrumentRepository.save(any(Instrument.class))).thenReturn(guitare);

        Instrument resultat = instrumentService.ajouterInstrument(guitare);

        assertNotNull(resultat);
        assertEquals("Stratocaster", resultat.getNom());
        assertEquals("Fender", resultat.getMarque());
        verify(instrumentRepository, times(1)).save(guitare);
    }

    @Test
    @DisplayName("Doit lever une BusinessException si le stock initial est négatif")
    void ajouterInstrument_StockNegatif_LanceException() {
        guitare.setQuantiteStock(-1);

        assertThrows(BusinessException.class, () -> instrumentService.ajouterInstrument(guitare));
        verify(instrumentRepository, never()).save(any());
    }

    // 2. Suppression d'un instrument
    @Test
    @DisplayName("Doit supprimer un instrument existant")
    void supprimerInstrument_Succes() {
        when(instrumentRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> instrumentService.supprimerInstrument(1L));
        verify(instrumentRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Doit lever une ResourceNotFoundException lors de la suppression d'un instrument inexistant")
    void supprimerInstrument_Inexistant_LanceException() {
        when(instrumentRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> instrumentService.supprimerInstrument(99L));
        verify(instrumentRepository, never()).deleteById(anyLong());
    }

    // 3. Consultation du stock
    @Test
    @DisplayName("Doit retourner la quantité en stock d'un instrument existant")
    void consulterStock_Succes() {
        when(instrumentRepository.findById(1L)).thenReturn(Optional.of(guitare));

        int stock = instrumentService.consulterStock(1L);

        assertEquals(5, stock);
        verify(instrumentRepository, times(1)).findById(1L);
    }

    // 4. Instrument inexistant
    @Test
    @DisplayName("Doit lever une ResourceNotFoundException si l'instrument consulté n'existe pas")
    void consulterInstrument_Inexistant_LanceException() {
        when(instrumentRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> instrumentService.consulterInstrument(99L)
        );

        assertTrue(exception.getMessage().contains("99"));
    }

    @Test
    @DisplayName("Doit lever une ResourceNotFoundException lors de la consultation du stock d'un instrument inexistant")
    void consulterStock_Inexistant_LanceException() {
        when(instrumentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> instrumentService.consulterStock(99L));
    }

    // 5. Bonus : Décrémentation du stock (utilisée par CommandeService)
    @Test
    @DisplayName("Doit décrémenter le stock de 1 lorsque l'instrument est disponible")
    void decrementerStock_Succes() {
        when(instrumentRepository.findById(1L)).thenReturn(Optional.of(guitare));
        when(instrumentRepository.save(any(Instrument.class))).thenReturn(guitare);

        instrumentService.decrementerStock(1L);

        assertEquals(4, guitare.getQuantiteStock());
        verify(instrumentRepository, times(1)).save(guitare);
    }

    @Test
    @DisplayName("Doit lever une BusinessException lors de la décrémentation si le stock est à 0")
    void decrementerStock_StockEpuise_LanceException() {
        guitare.setQuantiteStock(0);
        when(instrumentRepository.findById(1L)).thenReturn(Optional.of(guitare));

        assertThrows(BusinessException.class, () -> instrumentService.decrementerStock(1L));
        verify(instrumentRepository, never()).save(any());
    }
}