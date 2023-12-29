package zad1;

import java.util.*;

public class XList<T> extends ArrayList<T> {

    ArrayList<T> mainList = new ArrayList<>();

    public XList() {
    }

    public XList(List<T> elements) {
        super(elements);
    }

    public XList(T... arguments) {
        this(Arrays.asList(arguments));
    }

    public static <T> XList<T> of(T... arguments) {
        return new XList<>(arguments);
    }
    
    
    public XList(Set set) {
        super(set);
    }

    public static <T> XList<T> of(List<T> args) {
        return new XList<>(args);
    }

    public static XList of(Set set) {
        return new XList<>(set);
    }

    public static XList<String> charsOf(String napis) {
        XList<String> lista = new XList();
        for (char a : napis.toCharArray()) {
            lista.add(String.valueOf(a));
        }
        return lista;
    }

    public static XList<String> tokensOf(String... napisy) {
        XList<String> lista = new XList<>();

        for (String napis : napisy) {
            String[] znaki = napis.split("\\p{Punct}");
            lista.addAll(Arrays.asList(znaki));
        }

        return lista;
    }

    public static XList tokensaf(String... napis) {
        return new XList(napis);
    }

    public XList union(List<T> lista) {
        XList xList = new XList();
        xList.addAll(this);
        xList.addAll(lista);
        return xList;
    }

    public XList union(Set set) {
        XList xList = new XList();
        xList.addAll(this);
        xList.addAll(set);
        return xList;
    }

    public XList union(Integer[] t) {
        XList xList = new XList();
        xList.addAll(this);
        xList.addAll(Arrays.asList(t));
        return xList;
    }

    public XList diff(Set set) {
        XList xList = new XList();
        xList.addAll(this);
        xList.removeAll(set);
        return xList;
    }

    public XList diff(XList list) {
        XList xList = new XList();
        xList.addAll(this);
        xList.removeAll(list);
        return xList;
    }

    public XList unique() {
        Set set = new LinkedHashSet(this);
        return new XList(set);
    }


    public void forEachWithIndex(IntListProcessor<T> interf) {
        for (int i = 0; i < size(); i++) {
            interf.process(get(i), i);
        }
    }

    interface IntListProcessor<T> {
        void process(T element, int index);
    }
}