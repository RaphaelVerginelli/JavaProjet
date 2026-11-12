package fr.formation.musicstore.mapper;

import fr.formation.musicstore.dto.InstrumentDTO;
import fr.formation.musicstore.entity.Instrument;
import org.springframework.stereotype.Component;

@Component
public class InstrumentMapper {

    public InstrumentDTO toDTO(Instrument instrument) {
        if (instrument == null) {
            return null;
        }
        return InstrumentDTO.builder()
                .id(instrument.getId())
                .nom(instrument.getNom())
                .marque(instrument.getMarque())
                .type(instrument.getType())
                .prix(instrument.getPrix())
                .quantiteStock(instrument.getQuantiteStock())
                .description(instrument.getDescription())
                .build();
    }

    public Instrument toEntity(InstrumentDTO dto) {
        if (dto == null) {
            return null;
        }
        return Instrument.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .marque(dto.getMarque())
                .type(dto.getType())
                .prix(dto.getPrix())
                .quantiteStock(dto.getQuantiteStock())
                .description(dto.getDescription())
                .build();
    }
}