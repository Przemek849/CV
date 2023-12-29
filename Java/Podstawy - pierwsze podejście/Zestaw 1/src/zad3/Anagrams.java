/**
 *
 *  @author Koszewski Przemysław PD4177
 *
 */

package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    ArrayList<String> listaIn = new ArrayList<>();

    HashMap<String, TreeSet<String>> mapy = new HashMap<>();

    Map<String, ArrayList<String>> nowaMapa;

    String path;

    public Anagrams(String path) {
        this.path = path;
        przezuj(path);
        wypluj(mapy);
        nowaMapa = wypluj(mapy);
    }

    public void przezuj(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String a = scanner.next();
                listaIn.add(a);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < listaIn.size() - 1; i++) {
            String jeden = listaIn.get(i);
            String dwa = listaIn.get(i + 1);
            char[] pierwszy = jeden.toCharArray();
            char[] drugi = dwa.toCharArray();
            Arrays.sort(pierwszy);
            Arrays.sort(drugi);
            mapy.computeIfAbsent(String.valueOf(pierwszy), key -> new TreeSet<>()).add(jeden);
            mapy.computeIfAbsent(String.valueOf(drugi), key -> new TreeSet<>()).add(dwa);
        }

    }

    public Map<String, ArrayList<String>> wypluj(HashMap<String, TreeSet<String>> stara) {
        HashMap<String, ArrayList<String>> nowa = new HashMap<>();
        for (String key : stara.keySet()) {
            TreeSet<String> treeSet = stara.get(key);
            ArrayList<String> arrayList = new ArrayList<>(treeSet);
            nowa.put(key, arrayList);
        }
        return nowa;
    }


    public String getAnagramsFor(String word) {
        char[] slowo = word.toCharArray();
        Arrays.sort(slowo);
        String strSlowo = String.valueOf(slowo);
        if (mapy.containsKey(strSlowo)) {
            String dane = nowaMapa.get(strSlowo).toString();
            return word + ": " + dane;
        }else {
            return word + ": ";
        }

    }

    public List<ArrayList<String>> getSortedByAnQty() {
        List<ArrayList<String>> listy = new ArrayList<>(nowaMapa.values());
        listy.sort((o1, o2) -> {
            int porownanie = Integer.compare(o2.size(), o1.size());
            if (porownanie == 0) {
                porownanie = o2.toString().compareTo(o1.toString());
            }
            return porownanie;
        });
        return listy;
    }
}
