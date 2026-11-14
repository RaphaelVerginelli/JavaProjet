package fr.formation.musicstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.musicstore.entity.Client;
import fr.formation.musicstore.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    // Injection du repository via le constructeur
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // 1. Créer un client
    public Client creerClient(Client client) {
        return clientRepository.save(client);
    }

    // 2. Consulter un client par son ID
    public Client consulterClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + id));
    }

    // 3. Consulter tous les clients
    public List<Client> consulterTousLesClients() {
        return clientRepository.findAll();
    }

    // 4. Modifier les informations d'un client
    public Client modifierClient(Long id, Client clientDetails) {
        Client clientExistant = consulterClient(id);
        
        clientExistant.setNom(clientDetails.getNom());
        clientExistant.setPrenom(clientDetails.getPrenom());
        clientExistant.setEmail(clientDetails.getEmail());
        clientExistant.setTelephone(clientDetails.getTelephone());
        
        return clientRepository.save(clientExistant);
    }

    // 5. Supprimer un client (utile pour la gestion)
    public void supprimerClient(Long id) {
        consulterClient(id); // Vérifie s'il existe avant de le supprimer
        clientRepository.deleteById(id);
    }
}