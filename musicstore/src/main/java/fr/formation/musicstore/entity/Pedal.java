package fr.formation.musicstore.entity;

public class Pedal extends Instrument {
    private String effectType;

    public Pedal(String brand, String model, int price, int stock, String effectType) {
        super(model, brand, TypeInstrument.PEDALE, (double) price, stock, "Equipement permettant de moduler le son d'une guitare ou d'une basse pour obtenir un son différent");
        this.effectType = effectType;
    }
    
    public Pedal() {
        super();
        this.effectType = "";
    }

    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }
}
