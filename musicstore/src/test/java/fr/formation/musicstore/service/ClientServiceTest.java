package fr.formation.musicstore.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import fr.formation.musicstore.repository.ClientRepository;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerClient() {
        // Given
        Client client = new Client(null, "Dupont", "Jean", "jean@mail.com", "0612345678");
        Client clientSauvegarde = new Client(1L, "Dupont", "Jean", "jean@mail.com", "0612345678");
        
        when(clientRepository.save(any(Client.class))).thenReturn(clientSauvegarde);

        // When
        Client resultat = clientService.creerClient(client);

        // Then
        assertNotNull(resultat);
        assertEquals(1L, resultat.getId());
        assertEquals("Dupont", resultat.getNom());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testRechercheClientExistant() {
        // Given
        Client client = new Client(1L, "Dupont", "Jean", "jean@mail.com", "0612345678");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // When
        Client resultat = clientService.consulterClient(1L);

        // Then
        assertNotNull(resultat);
        assertEquals(1L, resultat.getId());
        assertEquals("Jean", resultat.getPrenom());
    }

    @Test
    void testClientInexistant() {
        // Given
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.consulterClient(99L);
        });

        assertTrue(exception.getMessage().contains("Client non trouvé"));
    }
}