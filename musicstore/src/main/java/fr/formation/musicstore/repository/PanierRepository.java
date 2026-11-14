package fr.formation.musicstore.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.formation.musicstore.entity.Panier;

@Repository
public class PanierRepository {
    private final Map<Long, Panier> paniers = new HashMap<>();
    private Long currentId = 1L;

    public Panier save(Panier panier) {
        if (panier.getId() == null) {
            panier.setId(currentId++);
        }
        paniers.put(panier.getId(), panier);
        return panier;
    }

    public Optional<Panier> findById(Long id) {
        return Optional.ofNullable(paniers.get(id));
    }

    public List<Panier> findAll() {
        return new ArrayList<>(paniers.values());
    }

    public void deleteById(Long id) {
        paniers.remove(id);
    }
}