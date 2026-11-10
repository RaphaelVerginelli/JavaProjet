package fr.formation.musicstore.entity;

public class Bass extends Instrument {
    private int numberOfStrings;

    public Bass(String brand, String model, int price, int stock, int numberOfStrings) {
        super(model, brand, TypeInstrument.BASSE, (double) price, stock, "Instrument à cordes le plus souvent d'accompagnement aux sonorités plus grave que la guitare");
        this.numberOfStrings = numberOfStrings;
    }

    public Bass() {
        super();
        this.numberOfStrings = 4;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }
}