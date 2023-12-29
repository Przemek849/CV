/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad2;


public class Purchase implements Comparable<Purchase>{
    String id;
    String nazwisko;
    String imie;
    String towar;
    String cena;
    String ilosc;


    public Purchase(String id, String nazwisko, String imie, String towar, String ilosc, String cena) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.towar = towar;
        this.ilosc = ilosc;
        this.cena = cena;
    }

    public String getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Double getIlosc() {
        Double dIlosc = Double.parseDouble(ilosc);
        return dIlosc;
    }

    public Double getCena() {
        Double dCena = Double.parseDouble(cena);
        return dCena;
    }

    public Double koszt() {
        Double koszt = getIlosc() * getCena();
        return koszt;
    }


    @Override
    public int compareTo(Purchase p) {
        return this.getNazwisko().compareTo(p.getNazwisko());
    }

    public String toString() {
        return id + ";" + nazwisko + " " + imie + ";" + towar + ";" + ilosc + ";" + cena;
    }
}