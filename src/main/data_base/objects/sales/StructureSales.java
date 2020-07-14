package main.data_base.objects.sales;

import java.io.Serializable;

public class StructureSales implements Serializable {
    private StructureSales(){}

    private static final long serialVersionUID = 4353234L;

    private int codeSale;
    private int codeProduct;
    private int quantity;
    private double amount;

    private String shortNameProduct;

    //at the best time
//    public int getCodeSale() {
//        return codeSale;
//    }

    public int getCodeProduct() {
        return codeProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public String getShortNameProduct(){
        return shortNameProduct;
    }

    public static Builder newBuilder(){
        return new StructureSales().new Builder();
    }

    public class Builder{

        public Builder setCodeSale(int codeSale) {
            StructureSales.this.codeSale = codeSale;
            return this;
        }

        public Builder setCodeProduct(int codeProduct) {
            StructureSales.this.codeProduct = codeProduct;
            return this;
        }

        public Builder setQuantity(int quantity) {
            StructureSales.this.quantity = quantity;
            return this;
        }

        public Builder setAmount(double amount) {
            StructureSales.this.amount = amount;
            return this;
        }

        public Builder setShortNameProduct(String shortNameProduct){
            StructureSales.this.shortNameProduct = shortNameProduct;
            return this;
        }

        public StructureSales build(){
            return StructureSales.this;
        }
    }

}
