package fr.formation.musicstore.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import fr.formation.musicstore.entity.Instrument;

@Repository
public class InstrumentRepository {

    private final Map<Long, Instrument> instrumentsMap = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Instrument save(Instrument instrument) {
        if (instrument.getId() == null) {
            instrument.setId(idSequence.getAndIncrement());
        }
        instrumentsMap.put(instrument.getId(), instrument);
        return instrument;
    }

    public Optional<Instrument> findById(Long id) {
        return Optional.ofNullable(instrumentsMap.get(id));
    }

    public List<Instrument> findAll() {
        return new ArrayList<>(instrumentsMap.values());
    }

    public void deleteById(Long id) {
        instrumentsMap.remove(id);
    }

    public boolean existsById(Long id) {
        return instrumentsMap.containsKey(id);
    }
}