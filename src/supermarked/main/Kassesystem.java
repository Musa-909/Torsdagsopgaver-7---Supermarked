package supermarked.main;

import supermarked.data.Vare;
import supermarked.util.FileIo;

import java.util.Collection;

public class Kassesystem {

    // These are all products in the store
    private Collection<Vare> alleVarer;

    // These are products that are on sale
    private Collection<Vare> tilbudsVarer;

    // This is the robot that builds a random basket
    private Robot robot;

    // Constructor that loads data from CSV and creates the robot
    public Kassesystem() {
        FileIo fileIo = new FileIo();

        // path
        this.alleVarer     = fileIo.loadProducts("data/varer.csv");
        this.tilbudsVarer  = fileIo.loadProducts("data/tilbud.csv");

        this.robot = new Robot();
    }

    // This starts the system and creates a basket and prints some info
    public void start() {

        // This is a robot that builds a random basket from all products
        Collection<Vare> kurv = robot.fyldIKurv(alleVarer);

        // Simple output
        System.out.println("Varer i butikken:      " + alleVarer.size());
        System.out.println("Varer på tilbud:       " + tilbudsVarer.size());
        System.out.println("Varer i kundens kurv:  " + kurv.size());
        System.out.println();

        Kvittering kvittering = new Kvittering();
        kvittering.udskrivBon(kurv, tilbudsVarer);
    }
}