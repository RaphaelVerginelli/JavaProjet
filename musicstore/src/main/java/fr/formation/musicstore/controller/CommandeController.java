package fr.formation.musicstore.controller;

import fr.formation.musicstore.dto.CommandeDTO;

import fr.formation.musicstore.mapper.CommandeMapper;

import fr.formation.musicstore.service.CommandeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;
    private final CommandeMapper commandeMapper;

    @GetMapping("/{id}")
    public CommandeDTO getCommande(@PathVariable Long id) {
        return commandeMapper.toDTO(commandeService.consulterCommande(id));
    }

    @GetMapping("/client/{clientId}")
    public List<CommandeDTO> getClientHistory(@PathVariable Long clientId) {
        return commandeService.consulterHistoriqueClient(clientId).stream()
                .map(commandeMapper::toDTO)
                .toList();
    }

    @PostMapping
    public CommandeDTO validerPanier(@RequestParam Long panierId) {
        return commandeMapper.toDTO(commandeService.transformerPanierEnCommande(panierId));
    }
}