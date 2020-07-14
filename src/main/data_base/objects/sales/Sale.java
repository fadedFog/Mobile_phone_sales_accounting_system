package main.data_base.objects.sales;

import java.io.Serializable;

public class Sale implements Serializable {
    private Sale(){}

    private static final long serialVersionUID = 124569865L;

    private int codeSale;
    private int codeWorker;
    private int codeClient;
    private String date;
    private StructureSales[] ofSales;

    public double getTotalAmount(){
        double totalAmount = 0;

        for(StructureSales ofSale: ofSales){
            totalAmount += ofSale.getAmount();
        }

        return totalAmount;
    }

    public int getTotalQuantity(){
        int totalQuantity = 0;

        for(StructureSales ofSale: ofSales){
            totalQuantity += ofSale.getQuantity();
        }

        return totalQuantity;
    }


    public int getCodeSale() {
        return codeSale;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public int getCodeWorker() {
        return codeWorker;
    }

    public String getDate() {
        return date;
    }

    public StructureSales[] getOfSales() {
        return ofSales;
    }

    public static Builder newBuilder(){
        return new Sale().new Builder();
    }

    public class Builder{

        public Builder setCodeSale(int codeSale){
            Sale.this.codeSale = codeSale;
            return this;
        }

        public Builder setCodeClient(int codeClient) {
            Sale.this.codeClient = codeClient;
            return this;
        }

        public Builder setCodeWorker(int codeWorker) {
            Sale.this.codeWorker = codeWorker;
            return this;
        }

        public Builder setDate(String date) {
            Sale.this.date = date;
            return this;
        }

        public Builder setOfSales(StructureSales[] ofSales){
            Sale.this.ofSales = ofSales;
            return this;
        }

        public Sale build(){
            return Sale.this;
        }
    }
}
