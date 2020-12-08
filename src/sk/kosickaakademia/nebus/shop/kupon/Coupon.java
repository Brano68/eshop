package sk.kosickaakademia.nebus.shop.kupon;

public class Coupon {
    private String code;
    private int value;

    public Coupon(String code, int value){
        this.code = code;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
