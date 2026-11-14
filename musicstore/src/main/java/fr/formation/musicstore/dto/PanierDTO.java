package fr.formation.musicstore.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanierDTO {
    private Long id;
    private Long clientIds;
    private List<Long> articlesIds;
    private BigDecimal montant;
}