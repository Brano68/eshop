package sk.kosickaakademia.nebus.shop.kupon;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private static String fileName = "resource/coupon.txt";

    public static List<Coupon> getListOfCouponsFromFile(){
        List<Coupon> list = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] array = data.split(" ");
                Coupon newCoupon = new Coupon(array[0], Integer.parseInt(array[1]));
                list.add(newCoupon);
            }
            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void updateCoupons(List<Coupon> list){
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            for(Coupon temp:list){
                fw.write(temp.getCode() + " " + temp.getValue() + '\n');
            }
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}

















