package main.data_base.objects.delivery;

import java.io.Serializable;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class StructureOfDelivery implements Serializable {
    private StructureOfDelivery(){}

    private static final long serialVersionUID = 10324283323L;

    private int articleProduct;
    private int quantity;
    private int numberDelivery;

    private String shortNameProduct;

    public int getArticleProduct() {
        return articleProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumberDelivery() {
        return numberDelivery;
    }
    
    public String getShortNameProduct(){
        return shortNameProduct;
    }

    public static Builder newBuilder(){
        return new StructureOfDelivery().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setArticleProduct(int articleProduct) {
            StructureOfDelivery.this.articleProduct = articleProduct;
            return this;
        }

        public Builder setQuantity(int quantity) {
            StructureOfDelivery.this.quantity = quantity;
            return this;
        }

        public Builder setNumberDelivery(int numberDelivery) {
            StructureOfDelivery.this.numberDelivery = numberDelivery;
            return this;
        }

        public Builder setShortNameProduct(String articleWithName){
            StructureOfDelivery.this.shortNameProduct = articleWithName;
            return this;
        }

        public StructureOfDelivery build(){
            return StructureOfDelivery.this;
        }
    }
}
