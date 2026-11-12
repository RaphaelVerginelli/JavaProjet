package fr.formation.musicstore.dto;

import java.math.BigDecimal;

import fr.formation.musicstore.entity.TypeInstrument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstrumentDTO {
    private Long id;

    @NotBlank(message = "Le nom de l'instrument est obligatoire")
    private String nom;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotNull(message = "Le type d'instrument est obligatoire")
    private TypeInstrument type;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être supérieur à zéro")
    private BigDecimal prix;

    @PositiveOrZero(message = "La quantité en stock ne peut pas être négative")
    private int quantiteStock;

    private String description;
}