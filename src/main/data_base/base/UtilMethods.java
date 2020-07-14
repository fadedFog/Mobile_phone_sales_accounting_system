package main.data_base.base;

import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.sales.Sale;

public class UtilMethods {

    public static String[] getElementsFromArray(String text, String regex){
        return text.split(regex);
    }

    public static Delivery[] increaseSizeDelivery(Delivery[] array){
        Delivery[] newArray = new Delivery[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    public static Sale[] increaseSizeSales(Sale[] array){
        Sale[] newArray = new Sale[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

}
