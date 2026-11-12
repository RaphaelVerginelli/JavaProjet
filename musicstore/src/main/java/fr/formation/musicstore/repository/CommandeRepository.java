package fr.formation.musicstore.repository;

import fr.formation.musicstore.entity.Commande;
import fr.formation.musicstore.entity.Client;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommandeRepository {
    private final Map<Long, Commande> commandesMap = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Commande save(Commande c) {
        if (c.getId() == null) {
            c.setId(idSequence.getAndIncrement());
        }
        commandesMap.put(c.getId(), c);
        return c;
    }

    public Optional<Commande> findById(Long id) {
        return Optional.ofNullable(commandesMap.get(id));
    }

    public List<Commande> findByClientId(Long clientId) {
        return commandesMap.values().stream().filter(
            commande -> commande.getClient() != null &&
            clientId.equals(commande.getClient().getId())).toList();
    }
}