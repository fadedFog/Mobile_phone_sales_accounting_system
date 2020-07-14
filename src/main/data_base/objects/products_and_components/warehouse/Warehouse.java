package main.data_base.objects.products_and_components.warehouse;

import main.data_base.base.AccountingSystem;
import main.data_base.objects.delivery.StructureOfDelivery;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.sales.StructureSales;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Warehouse implements Serializable {
    private static Warehouse warehouse;
    private Warehouse(){}
    public static Warehouse getInstance(){
        if(warehouse == null){
            warehouse = new Warehouse();
            warehouse.cellPhones = new HashMap<>();
        }
        return warehouse;
    }


    public void addingCellPhones(StructureOfDelivery[] ofDeliveries){
        boolean isIt = false;

        for (StructureOfDelivery ofDelivery : ofDeliveries) {
            int article = ofDelivery.getArticleProduct();
            int quantity = ofDelivery.getQuantity();
            CellPhone phone = AccountingSystem.getInstance().getCellPhone(article);


            for (Map.Entry<CellPhone, Integer> tmp : cellPhones.entrySet()) {
                if (containsProduct(phone, tmp.getKey())) {
                    int allQuantity = quantity + tmp.getValue();
                    tmp.setValue(allQuantity);
                    isIt = true;
                }
            }

            if (!isIt) {
                cellPhones.put(phone, quantity);
            }

            isIt = false;
        }
//        System.out.println();
//        iterationMap();

    }

    public void sellingCellPhones(StructureSales[] ofSales){
        for (StructureSales ofSale : ofSales) {
            int article = ofSale.getCodeProduct();
            int quantity = ofSale.getQuantity();
            CellPhone phone = AccountingSystem.getInstance().getCellPhone(article);

            for (Map.Entry<CellPhone, Integer> tmp : cellPhones.entrySet()) {
                if (containsProduct(phone, tmp.getKey())) {
                    int allQuantity = tmp.getValue() - quantity;
                    tmp.setValue(allQuantity);
                }
            }
        }
//        System.out.println();
//        iterationMap();
    }

    private boolean containsProduct(CellPhone phoneArray, CellPhone phoneMap){
        return phoneArray.equals(phoneMap);
    }


    // getters and setters
    public void setWarehouse(Warehouse warehouse){
       Warehouse.warehouse = warehouse;
    }

    public Map<CellPhone, Integer> getCellPhones() {
        return cellPhones;
    }


//    public void iterationMap(){
//        for(Map.Entry<CellPhone, Integer> tmp: cellPhones.entrySet()){
//            System.out.println(tmp.getKey().getShortName() + " - " + tmp.getValue());
//        }
//    }

    // variables
    private Map<CellPhone, Integer> cellPhones;

}
