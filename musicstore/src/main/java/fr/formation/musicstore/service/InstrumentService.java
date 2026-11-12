package fr.formation.musicstore.service;

import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.exception.BusinessException;
import fr.formation.musicstore.exception.ResourceNotFoundException;
import fr.formation.musicstore.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public Instrument ajouterInstrument(Instrument instrument) {
        if (instrument.getQuantiteStock() < 0) {
            throw new BusinessException("La quantité en stock ne peut pas être négative");
        }
        return instrumentRepository.save(instrument);
    }

    public Instrument modifierInstrument(Long id, Instrument instrumentModifie) {
        Instrument existant = consulterInstrument(id);

        existant.setNom(instrumentModifie.getNom());
        existant.setMarque(instrumentModifie.getMarque());
        existant.setType(instrumentModifie.getType());
        existant.setPrix(instrumentModifie.getPrix());
        existant.setQuantiteStock(instrumentModifie.getQuantiteStock());
        existant.setDescription(instrumentModifie.getDescription());

        return instrumentRepository.save(existant);
    }

    public void supprimerInstrument(Long id) {
        if (!instrumentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instrument introuvable : " + id);
        }
        instrumentRepository.deleteById(id);
    }

    public Instrument consulterInstrument(Long id) {
        return instrumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instrument introuvable : " + id));
    }

    public List<Instrument> consulterTousLesInstruments() {
        return instrumentRepository.findAll();
    }

    public int consulterStock(Long id) {
        return consulterInstrument(id).getQuantiteStock();
    }

    // Méthode appelée par CommandeService lors de la validation d'un panier
    public void decrementerStock(Long id) {
        Instrument instrument = consulterInstrument(id);
        if (instrument.getQuantiteStock() <= 0) {
            throw new BusinessException("Stock insuffisant pour l'instrument : " + instrument.getNom());
        }
        instrument.setQuantiteStock(instrument.getQuantiteStock() - 1);
        instrumentRepository.save(instrument);
    }
}