package fr.formation.musicstore.entity;

import java.math.BigDecimal;

public class Amplifier extends Instrument {
    private int powerWatts;

    public Amplifier(String brand, String model, BigDecimal price, int stock, int powerWatts) {
        super(brand, model, TypeInstrument.AMPLIFICATEUR, price, stock, "Amplificateur pour instrument de musique");
        if(powerWatts <= 0) {
            throw new IllegalArgumentException("La puissance en watts doit être supérieure à zéro");
        }
        this.powerWatts = powerWatts;
    }

    public Amplifier() {
        super();
        this.powerWatts = 0;
    }

    public int getPowerWatts() {
        return powerWatts;
    }

    public void setPowerWatts(int powerWatts) {
        this.powerWatts = powerWatts;
    }
}