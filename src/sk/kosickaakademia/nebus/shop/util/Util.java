package sk.kosickaakademia.nebus.shop.util;

public class Util {

    public static double zaokruhli(double cislo){
        double vysledok = Math.round(cislo * 100);
        vysledok = vysledok/100;
        return vysledok;
    }

}
