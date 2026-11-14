package fr.formation.musicstore.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.formation.musicstore.entity.Client;

@Repository
public class ClientRepository {
    
    // Stockage en mémoire (simule la base de données pour le moment)
    private final Map<Long, Client> clients = new HashMap<>();
    private Long currentId = 1L;

    // Enregistrer ou mettre à jour un client
    public Client save(Client client) {
        if (client.getId() == null) {
            client.setId(currentId++);
        }
        clients.put(client.getId(), client);
        return client;
    }

    // Trouver un client par son ID
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(clients.get(id));
    }

    // Récupérer la liste de tous les clients
    public List<Client> findAll() {
        return new ArrayList<>(clients.values());
    }

    // Supprimer un client par son ID
    public void deleteById(Long id) {
        clients.remove(id);
    }
}