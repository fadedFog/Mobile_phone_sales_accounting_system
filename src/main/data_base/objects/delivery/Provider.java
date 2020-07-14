package main.data_base.objects.delivery;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class Provider {
    private Provider(){}

    private int code;
    private String name;
    private String INN;
    private String CPP;
    private String address;
    private String numberPhone;
    private String paymentAccount;
    private String bank;
    private String BIK;
    private String correspondentAccount;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getINN() {
        return INN;
    }

    public String getCPP() {
        return CPP;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public String getBank() {
        return bank;
    }

    public String getBIK() {
        return BIK;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }
    

    public static Builder newBuilder(){
        return new Provider().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setCode(int code) {
            Provider.this.code = code;
            return this;
        }

        public Builder setName(String name) {
            Provider.this.name = name;
            return this;
        }

        public Builder setINN(String INN) {
            Provider.this.INN = INN;
            return this;
        }

        public Builder setCPP(String CPP) {
            Provider.this.CPP = CPP;
            return this;
        }

        public Builder setAddress(String address) {
            Provider.this.address = address;
            return this;
        }

        public Builder setNumberPhone(String numberPhone) {
            Provider.this.numberPhone = numberPhone;
            return this;
        }

        public Builder setPaymentAccount(String paymentAccount) {
            Provider.this.paymentAccount = paymentAccount;
            return this;
        }

        public Builder setBank(String bank) {
            Provider.this.bank = bank;
            return this;
        }

        public Builder setBIK(String BIK) {
            Provider.this.BIK = BIK;
            return this;
        }

        public Builder setCorrespondentAccount(String correspondentAccount) {
            Provider.this.correspondentAccount = correspondentAccount;
            return this;
        }

        public Provider build(){
            return Provider.this;
        }
    }
}
