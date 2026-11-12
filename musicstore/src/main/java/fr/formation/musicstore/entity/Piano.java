package fr.formation.musicstore.entity;

import java.math.BigDecimal;

public class Piano extends Instrument {
    private String pianoType;

    public Piano(String brand, String model, BigDecimal price, int stock, String pianoType) {
        super(model, brand, TypeInstrument.PIANO, price, stock, "Instrument utilisé aussi bien dans la musique classique que dans des styles plus modernes");
        this.pianoType = pianoType;
    }

    public Piano() {
        super();
        this.pianoType = "";
    }

    public String getPianoType() {
        return pianoType;
    }

    public void setPianoType(String pianoType) {
        this.pianoType = pianoType;
    }
}