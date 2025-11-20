package supermarked.util;

import supermarked.data.Vare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FileIo {

    // Loads products from a CSV file and returns them as Vare objects
    public Collection<Vare> loadProducts(String filePath) {

        // This will hold all products that are read from the file
        Collection<Vare> varer = new ArrayList<>();

        // Try to open and read the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean firstLine = true; // used to skip the header in the CSV file

            // Read the file line by line
            while ((line = br.readLine()) != null) {

                // Skip the first header row
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // Split the line into columns
                String[] data = line.split(";");

                // This makes sure that the line contains all of the expected values
                if (data.length < 6) {
                    continue;
                }

                // Basic product info
                String varenummer = data[0];
                String navn       = data[1];
                String enhed      = data[5]; 

                // This reads the price and then replaces the comma with a dot
                double pris;
                try {
                    pris = Double.parseDouble(data[3].replace(",", "."));
                } catch (NumberFormatException e) {
                    // If the price cannot be read then it skips this product
                    continue;
                }

                // Read the amount also replace comma with dot
                double antal;
                try {
                    antal = Double.parseDouble(data[4].replace(",", "."));
                } catch (NumberFormatException e) {
                    // If amount is missing or invalid, default to 1
                    antal = 1;
                }

                // Create the product object
                Vare vare = new Vare(varenummer, navn, pris, antal, enhed);

                // Add it to the list
                varer.add(vare);
            }

        } catch (IOException e) {
            // If something goes wrong while reading the file
            System.out.println("Error reading file: " + filePath);
        }

        // Return all loaded products
        return varer;
    }
}
