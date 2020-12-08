package sk.kosickaakademia.nebus.shop.counable;

public interface CountItem {
    public int getCount();

    public void setCount(int count);

    default void napisAhoj(){
        System.out.println("Ahoj");
    }
}
