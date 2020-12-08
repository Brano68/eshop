package sk.kosickaakademia.nebus.shop.counable;

import sk.kosickaakademia.nebus.shop.Item;

public class Choco extends Item implements CountItem{

    private int count;

    public Choco(String name, double price, int count){
        super(name, price);
        this.count = count;
    }


    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public double getItemPrice() {
        return count*getPrice();
    }
}
