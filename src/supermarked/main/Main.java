package supermarked.main;

public class Main {
    public static void main(String[] args) {

        // Creates the system (this loads varer.csv + tilbud.csv + robot)
        Kassesystem system = new Kassesystem();

        // Start the checkout flow
        system.start();
    }
}