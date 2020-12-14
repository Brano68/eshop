package sk.kosickaakademia.nebus.shop;

import sk.kosickaakademia.nebus.shop.cart.Cart;
import sk.kosickaakademia.nebus.shop.counable.Water;
import sk.kosickaakademia.nebus.shop.kupon.Coupon;
import sk.kosickaakademia.nebus.shop.kupon.Reader;
import sk.kosickaakademia.nebus.shop.service.Delivery;
import sk.kosickaakademia.nebus.shop.uncountable.Apple;
import sk.kosickaakademia.nebus.shop.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Item i1 = new Apple("Golden", 0.59, 5.3);
        Apple i2 = new Apple("Jonatan", 1.3, 4.3);
        Item i3 = new Water("Budis", 0.59, 2);
        Item i6 = new Water("Budis", 0.59, 4);
        Item i5 = new Apple("Golden",0.59 , 5.3);
        Item i4 = new Delivery(2.99);

        //kosik
        Cart cart = new Cart();
        //naplnenie kosika
        cart.addItemToTheCart(i1);
        cart.addItemToTheCart(i2);
        cart.addItemToTheCart(i3);
        cart.addItemToTheCart(i5);
        cart.addItemToTheCart(i6);

        cart.addItemToTheCart(i4);



        //vypisanie tovaru

        cart.printCart();
        //cart.getTotalPrice();

        System.out.println("Mas zlavovy kupon? Zadaj y alebo n");
        Scanner scanner = new Scanner(System.in);
        String odpoved = scanner.nextLine().toLowerCase();
        if(odpoved.charAt(0) == 'y'){
            System.out.println("Zadaj zlavovy kupon: ");
            String cupon = scanner.nextLine();
            //teraz nacitame coupon.txt do array
            List<Coupon> list = Reader.getListOfCouponsFromFile();
            //
            boolean over = false;
            for(Coupon c:list){
                if(c.getCode().equals(cupon)){
                    int zlava = c.getValue();
                    double suma = cart.getTotalPrice()-(cart.getTotalPrice()*zlava/100);
                    System.out.println("Vysledna suma je: " + suma);
                    list.remove(c);
                    Reader.updateCoupons(list);
                    over = true;
                    break;
                }
            }
            if(!(over)){
                System.out.println("Zly kupon");
                System.out.println("Vysledna suma je: " + cart.getTotalPrice());
            }
        }else{
            System.out.println("Vysledna suma je: " + Util.zaokruhli(cart.getTotalPrice()));
            //System.out.println("Vysledna suma je: " + cart.getTotalPrice());
        }


        //vytvaram xml
        GenerateXml.generateFileXML(cart);
    }

}
