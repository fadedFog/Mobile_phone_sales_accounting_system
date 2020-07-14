package main.data_base.objects.delivery;

import java.io.Serializable;

public class Delivery implements Serializable {
    private Delivery(){}

    private static final long serialVersionUID = 4345234234L;

    private int number;
    private String date;
    private int providerCode;
    private int serviceNumberWorker;
    private double amount;
    private StructureOfDelivery[] ofDeliveries;

    // at the best of time
//    public void removeOfDelivery(StructureOfDelivery remover){
//        StructureOfDelivery[] newArray = decreaseSizeOfDelivery(ofDeliveries);
//
//        int decrease = 0;
//
//        for(int i = 0; i < ofDeliveries.length; i++){
//            boolean article = ofDeliveries[i].getArticleProduct() == remover.getArticleProduct();
//            boolean quantity = ofDeliveries[i].getQuantity() == remover.getQuantity();
//            boolean number = ofDeliveries[i].getNumberDelivery() == remover.getNumberDelivery();
//
//            if(!(article && quantity && number)){
//                newArray[i - decrease] = ofDeliveries[i];
//            }else{
//                decrease+=1;
//            }
//        }
//
//        ofDeliveries = newArray;
//    }
//
//    private StructureOfDelivery[] decreaseSizeOfDelivery(StructureOfDelivery[] origin){
//        return new StructureOfDelivery[origin.length-1];
//    }



    public int getNumber() {
        return number;
    }

    public String getDate(){
        return date;
    }

    public int getProviderCode() {
        return providerCode;
    }

    public int getServiceNumberWorker() {
        return serviceNumberWorker;
    }

    public double getAmount() {
        return amount;
    }

    public StructureOfDelivery[] getOfDeliveries() {
        return ofDeliveries;
    }

    public static Builder newBuilder(){
        return new Delivery().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setNumber(int number) {
            Delivery.this.number = number;
            return this;
        }

        public Builder setDate(String date) {
            Delivery.this.date = date;
            return this;
        }

        public Builder setProviderCode(int providerCode) {
            Delivery.this.providerCode = providerCode;
            return this;
        }

        public Builder setServiceNumberWorker(int serviceNumberWorker) {
            Delivery.this.serviceNumberWorker = serviceNumberWorker;
            return this;
        }

        public Builder setAmount(double amount) {
            Delivery.this.amount = amount;
            return this;
        }

        public Builder setOfDeliveries(StructureOfDelivery[] ofDeliveries){
            Delivery.this.ofDeliveries = ofDeliveries;
            return this;
        }

        public Delivery build(){
            return Delivery.this;
        }
    }
}
