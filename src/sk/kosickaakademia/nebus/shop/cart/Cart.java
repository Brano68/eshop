package sk.kosickaakademia.nebus.shop.cart;

import sk.kosickaakademia.nebus.shop.Item;
import sk.kosickaakademia.nebus.shop.counable.CountItem;
import sk.kosickaakademia.nebus.shop.service.ServiceInterface;
import sk.kosickaakademia.nebus.shop.uncountable.WeightItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Item> list;

    public Cart(){
        list = new ArrayList<>();
    }

    public List<Item> getList() {
        return list;
    }

    /*
        public void addItemToTheCartm(Item item){
            if(item instanceof CountItem && ((CountItem) item).getCount()>0 && item.getPrice()>=0){
                list.add(item);
            }
            if(item instanceof WeightItem && ((WeightItem) item).getWeight()>0 && item.getPrice()>=0){
                list.add(item);
            }
            if(item instanceof ServiceInterface){
                list.add(item);
            }
            //list.add(item);
        }


     */
    public void addItemToTheCart(Item item){
        ///doplnene ze pripocita
        boolean zapis = false;
            for(Item i:list){
                if(i instanceof WeightItem && item.getName().equals(i.getName()) && item.getPrice() == i.getPrice()){
                    double weight = ((WeightItem) i).getWeight() + ((WeightItem)item).getWeight();
                    ((WeightItem) i).setWeight(weight);
                    zapis = true;
                    break;
                }
            }
            if(zapis){
                return;
            }

        for(Item i:list){
            if(i instanceof CountItem && item.getName().equals(i.getName()) && item.getPrice() == i.getPrice()){
                int count = ((CountItem) i).getCount() + ((CountItem)item).getCount();
                ((CountItem) i).setCount(count);
                zapis = true;
                break;
            }
        }
        if(zapis){
            return;
        }


        ////////povodne
        if(item instanceof CountItem && ((CountItem) item).getCount()>0 && item.getPrice()>=0){
            list.add(item);
        }

        if(item instanceof WeightItem && ((WeightItem) item).getWeight()>0 && item.getPrice()>=0){
            list.add(item);
        }
        if(item instanceof ServiceInterface){
            list.add(item);
        }
    }
/*
    public void printCartm(){
        for(Item i:list){
            if(i instanceof ServiceInterface){
                System.out.println("-- Polozka Delivery -- " + i.getItemPrice());
            }else{
                System.out.println("-- Polozka -- " + i.getName() +" "+ i.getPrice() +" "+ i.getItemPrice());
            }
            //System.out.println("-- Polozka -- " + i.getName() +" "+ i.getPrice() +" "+ i.getItemPrice());
        }
    }


 */
    public void printCart(){
        for(Item i:list){
            if(i instanceof ServiceInterface){
                System.out.println("-- Polozka Delivery -- " + i.getItemPrice());
            }
            if(i instanceof CountItem){
                System.out.println("-- Polozka -- " + "Nazov" + i.getName() +" "+"pocet: "+((CountItem) i).getCount() +" "+ "cena: " + i.getPrice() +" "+"vysledna cena: " + i.getItemPrice());
            }
            if(i instanceof WeightItem){
                System.out.println("-- Polozka -- " + "Nazov" + i.getName() +" "+"mnozstvo: "+((WeightItem) i).getWeight() +" "+"cena: " + i.getPrice() +" "+"vysledna cena: "+ i.getItemPrice());
            }
            //System.out.println("-- Polozka -- " + i.getName() +" "+ i.getPrice() +" "+ i.getItemPrice());
        }
    }

    public double getTotalPrice(){
        double vysledok = 0;
        for (Item i:list){
            vysledok += i.getItemPrice();
        }
        //System.out.println("Total price: " + vysledok);
        return vysledok;
    }

    public void napisMiTo(){
        for(Item i:list){
            if(i instanceof CountItem){
                ((CountItem) i).napisAhoj();
                ((CountItem) i).napisAhoj();

            }
            else {
                System.out.println("Neahoj");
            }
        }
    }

}
