package sk.kosickaakademia.nebus.shop.service;

import sk.kosickaakademia.nebus.shop.Item;

public class Delivery extends Item implements ServiceInterface{

    public Delivery(double price) {
        super("Delivery", price);
    }

    //@Override
    //public double getPrice() {
        //return 2.99;
        //return getPrice();
    //}

    @Override
    public double getItemPrice() {
        return getPrice();
    }
}
