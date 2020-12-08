package sk.kosickaakademia.nebus.shop.uncountable;

import sk.kosickaakademia.nebus.shop.Item;

public class Peanuts extends Item implements WeightItem{

    private double weight;

    public Peanuts(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getItemPrice() {
        return weight*getPrice();
    }
}
