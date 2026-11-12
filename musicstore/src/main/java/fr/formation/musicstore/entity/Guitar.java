package fr.formation.musicstore.entity;

import java.math.BigDecimal;

public class Guitar extends Instrument {
    private int numberOfStrings;

    public Guitar(String brand, String model, BigDecimal price, int stock, int numberOfStrings) {
        super(brand, model, TypeInstrument.GUITARE, price, stock, "Instrument à cordes très mélodieux");
        this.numberOfStrings = numberOfStrings;
    }

    public Guitar() {
        super();
        this.numberOfStrings = 6;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }
}