package fr.formation.musicstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.musicstore.dto.InstrumentDTO;
import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.mapper.InstrumentMapper;
import fr.formation.musicstore.service.InstrumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;
    private final InstrumentMapper instrumentMapper;

    @GetMapping
    public List<InstrumentDTO> getAllInstruments() {
        return instrumentService.consulterTousLesInstruments().stream()
                .map(instrumentMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public InstrumentDTO getInstrumentById(@PathVariable Long id) {
        return instrumentMapper.toDTO(instrumentService.consulterInstrument(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument(@Valid @RequestBody InstrumentDTO dto) {
        Instrument instrument = instrumentMapper.toEntity(dto);
        return instrumentMapper.toDTO(instrumentService.ajouterInstrument(instrument));
    }

    @PutMapping("/{id}")
    public InstrumentDTO updateInstrument(@PathVariable Long id, @Valid @RequestBody InstrumentDTO dto) {
        Instrument instrument = instrumentMapper.toEntity(dto);
        return instrumentMapper.toDTO(instrumentService.modifierInstrument(id, instrument));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrument(@PathVariable Long id) {
        instrumentService.supprimerInstrument(id);
    }
}