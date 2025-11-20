package supermarked.data;

public class Vare {

    // These are the basic product fields from the CSV file
    private String varenummer;
    private String name;
    private double pris;
    private double antal;
    private String enhed;

    // Constructor that creates a product with all needed values
    public Vare(String varenummer, String name, double pris, double antal, String enhed) {
        this.varenummer = varenummer;
        this.name = name;
        this.pris = pris;
        this.antal = antal;
        this.enhed = enhed;
    }

    // Getter for the product number
    public String getVarenummer() {
        return varenummer;
    }

    // Getter for the product name
    public String getName() {
        return name;
    }

    // Getter for the price
    public double getPris() {
        return pris;
    }

    // Getter for the amount/quantity of the product
    public double getAntal() {
        return antal;
    }

    // Getter for the unit (e.g. GR, ML, ST)
    public String getEnhed() {
        return enhed;
    }
}