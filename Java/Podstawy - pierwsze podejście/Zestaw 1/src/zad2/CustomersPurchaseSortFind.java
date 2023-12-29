/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomersPurchaseSortFind {

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<Purchase> arrayListPurchase = new ArrayList<>();
    String fname;
    HashMap<String, ArrayList<String>> mapa = new HashMap<>();

    public CustomersPurchaseSortFind() {
    }

    public void readFile(String fname) {
        this.fname = fname;
        try {
            Scanner scanner = new Scanner(new File(fname));
            String regex = "c([0-9]+)";
            Pattern pattern = Pattern.compile(regex);
            while (scanner.hasNextLine()) {
                String id;
                String zdanie = scanner.nextLine();
                arrayList.add(zdanie);
                Matcher matcher = pattern.matcher(zdanie);
                if (matcher.find()) {
                    id = matcher.group();
                    mapa.computeIfAbsent(id, key -> new ArrayList<>()).add(zdanie);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showSortedBy(String napis) {

        if (napis.equals("Nazwiska")) {
            tworzKlienta();
            sortNazwiska();
            System.out.println(napis);
            for (Purchase k : arrayListPurchase) {
                System.out.println(k);
            }
            arrayListPurchase.clear();
            System.out.println();
        } else {
            tworzKlienta();
            System.out.println(napis);
            sortKoszty();
            for (Purchase k : arrayListPurchase) {
                System.out.println(k + " (koszt: " + k.koszt() + ")");
            }
            arrayListPurchase.clear();
            System.out.println();
        }
    }

    public void tworzKlienta() {
        for (String linia : arrayList) {
            String[] opis = linia.split("[ ;]");
            arrayListPurchase.add(new Purchase(opis[0], opis[1], opis[2], opis[3], opis[4], opis[5]));

        }
    }

    public void sortNazwiska() {
        arrayListPurchase.sort((o1, o2) -> {
            int porownanie = o1.getNazwisko().compareTo(o2.getNazwisko());
            if (porownanie == 0) {
                String a1 = o1.getId().substring(1);
                String a2 = o2.getId().substring(1);
                porownanie = a1.compareTo(a2);
            }
            return porownanie;
        });
    }

    public void sortKoszty() {

        arrayListPurchase.sort((o1, o2) -> {
            int porownanie = o2.koszt().compareTo(o1.koszt());
            if (porownanie == 0) {
                String kl1 = o1.getId().substring(1);
                String kl2 = o2.getId().substring(1);
                porownanie = kl1.compareTo(kl2);
            }
            return porownanie;
        });
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (String values : mapa.get(id)) {
            System.out.println(values);
        }
        System.out.println();
    }
}