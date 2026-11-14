package fr.formation.musicstore.entity;

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
public class Panier {
    private Long id;
    private Client client;
    private List<Instrument> articles;
    private BigDecimal montant;
    
    // Getters et Setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Instrument> getArticles() {
        return articles;
    }

    public void setArticles(List<Instrument> articles) {
        this.articles = articles;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montantTotal) {
        this.montant = montantTotal;
    }
}
