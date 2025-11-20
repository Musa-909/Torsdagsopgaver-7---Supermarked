package supermarked.main;

import supermarked.data.Vare;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Kvittering {

    // VAT (moms) rate for all products
    private double momsSats = 0.25;

    // Prints a simple receipt for the basket
    public void udskrivBon(Collection<Vare> kurv, Collection<Vare> tilbudsVarer) {

        // If the basket is empty, stop here
        if (kurv == null || kurv.isEmpty()) {
            System.out.println("Kurven er tom.");
            return;
        }

        double total = 0.0;

        // This map makes it easy to check if a product is on sale
        // Key = varenummer, Value = the sale item
        Map<String, Vare> tilbudsMap = new HashMap<>();
        for (Vare t : tilbudsVarer) {
            tilbudsMap.put(t.getVarenummer(), t);
        }

        // These maps count how many of each item we have in the basket
        // and also store one Vare object for the given varenummer
        Map<String, Integer> antalPrVare = new HashMap<>();
        Map<String, Vare> varePrVarenummer = new HashMap<>();

        // This counts the items
        for (Vare v : kurv) {
            String nr = v.getVarenummer();

            // This increases amount by 1
            int count = antalPrVare.getOrDefault(nr, 0);
            antalPrVare.put(nr, count + 1);

            // This remembers the last Vare object for that varenummer
            varePrVarenummer.put(nr, v);
        }

        System.out.println("============== KASSEBON ==============");

        // Prints each different item in the basket
        for (String varenummer : antalPrVare.keySet()) {

            Vare vare = varePrVarenummer.get(varenummer);
            int antal = antalPrVare.get(varenummer);

            double normalPris = vare.getPris();

            // This checks if the item has a special offer
            Vare tilbud = tilbudsMap.get(varenummer);
            boolean erTilbud = (tilbud != null);

            // This uses sale price if available
            double pris = erTilbud ? tilbud.getPris() : normalPris;

            double linjeTotal = pris * antal;
            total += linjeTotal;

            // A basic line: "2 x Banan á 5.00 kr = 10.00 kr"
            System.out.printf("%d x %s a %.2f kr = %.2f kr%n",
                    antal, vare.getName(), pris, linjeTotal);

            // If the price was lower than normal, show the discount
            if (erTilbud && pris < normalPris) {
                double rabat = (normalPris - pris) * antal;
                System.out.printf("   SPAR: %.2f kr%n", rabat);
            }
        }

        // Calculates the moms
        double moms = total * momsSats;

        System.out.println("--------------------------------------");
        System.out.printf("Moms:   %.2f kr%n", moms);
        System.out.printf("Total:  %.2f kr%n", total);
        System.out.println("======================================");
    }
}