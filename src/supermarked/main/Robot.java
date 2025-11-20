package supermarked.main;

import supermarked.data.Vare;

import java.util.*;

public class Robot {

    private int min = 5; // minimum antal (slags) varer i kurven
    private int max = 25; // maximum antal (slags) varer i kurven
    private int sandsynlighedForKun1Vare = 70; // procents sandsynlighed for at der kun er et styk af hver slags vare
    private int maxStyk = 5; // maximalt antal af samme slags vare

    public Robot() {

    }

    /**
     * Denne robot fylder nogle tilfældige varer i kurven.
     * <p>
     * Den tager mellem 'min' og 'max' varer - som regel kun en af hver
     * men nogle gange op til 'maxStyk'.
     *
     * Og den smider varerne i kurven fuldstændig tilfældig rækkefølge.
     *
     * @param alleVarer
     * @return en kurv med varer i vilkårlig rækkefølge
     */
    public Collection<Vare> fyldIKurv(Collection<Vare> alleVarer) {
        // hav en god random klar!
        Random random = new Random();

        // lav en kurv til varerne
        List<Vare> kurv = new ArrayList<>();

        // find på et tilfældigt antal varer, mellem 5 og 25
        int antalVarer = random.nextInt(min, max);
        for (int i = 0; i < antalVarer; i++) {
            // find et tilfældigt styktal - med % sandsynlighed for at der kun er 1
            int styk = (random.nextInt(100) > sandsynlighedForKun1Vare) ? random.nextInt(2, maxStyk) : 1;

            // find en tilfældig vare i samlingen af alleVarer
            Vare vare = alleVarer.stream().skip(random.nextInt(alleVarer.size())).findFirst().get();
            // og put det antal styk i kurven
            while (styk > 0) {
                kurv.add(vare);
                styk--;
            }
        }

        // ryst kurven godt, så ens varer ikke ligger lige ved siden af hinanden
        Collections.shuffle(kurv);

        // og returner den fyldte kurv
        return kurv;
    }

}
