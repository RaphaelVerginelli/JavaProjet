package fr.formation.musicstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.musicstore.entity.Client;
import fr.formation.musicstore.entity.Instrument;
import fr.formation.musicstore.entity.Panier;
import fr.formation.musicstore.service.ClientService;
import fr.formation.musicstore.service.PanierService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
public class PanierController {

    private final PanierService panierService;
    private final ClientService clientService; 

    
    @GetMapping("/{id}")
    public Panier getPanierById(@PathVariable Long id) {
        return panierService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Panier createPanier(@RequestParam Long clientId) {
        Client client = clientService.consulterClient(clientId);
        return panierService.creerPanier(client);
    }


    @PostMapping("/{id}/instruments")
    public Panier addInstrumentToPanier(@PathVariable Long id, @RequestBody Instrument instrument) {
        return panierService.ajouterInstrument(id, instrument);
    }


    @DeleteMapping("/{id}/instruments")
    public Panier removeInstrumentFromPanier(@PathVariable Long id, @RequestBody Instrument instrument) {
        return panierService.retirerInstrument(id, instrument);
    }


    @DeleteMapping("/{id}/vider")
    public Panier viderPanier(@PathVariable Long id) {
        return panierService.viderPanier(id);
    }
}