package fr.formation.musicstore.entity;

public class Amplifier extends Instrument {
    private int powerWatts;

    public Amplifier(String brand, String model, int price, int stock, int powerWatts) {
        super(brand, model, TypeInstrument.AMPLIFICATEUR, (double) price, stock, "Amplificateur pour instrument de musique");
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