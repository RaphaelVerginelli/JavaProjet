package fr.formation.musicstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {

    private static Long counter = 0L;

    private Long id;
    private String nom;
    private String marque;
    private TypeInstrument type;
    private double prix;
    private int quantiteStock;
    private String description;

    // Constructeur
    public Instrument(String nom, String marque, TypeInstrument type, double prix, int quantiteStock, String description) {
        this.id = ++counter;
        this.nom = nom;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
    }

    // Diminue le stock disponible de l'instrument.
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
        }
        if (this.quantiteStock < quantity) {
            throw new IllegalStateException("Stock insuffisant pour l'instrument : " + this.nom);
        }
        this.quantiteStock -= quantity;
    }

    // Calcule le prix TTC à partir du prix HT et du taux de TVA.
    public double calculateTtcPrice(double tvaRate) {
        return this.prix * (1 + tvaRate);
    }
}