package main.data_base.objects.sales.client;

public class Client {

    private int codeClient;
    private String secondName;
    private String name;
    private String middleName;
    private String numberPhone;
    private String email; // May be empty
    private String address; // May be empty

    public int getCodeClient() {
        return codeClient;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public static Builder newBuilder(){
        return new Client().new Builder();
    }

    public class Builder{

        public Builder setCodeClient(int codeClient) {
            Client.this.codeClient = codeClient;
            return this;
        }

        public Builder setSecondName(String secondName) {
            Client.this.secondName = secondName;
            return this;
        }

        public Builder setName(String name) {
            Client.this.name = name;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            Client.this.middleName = middleName;
            return this;
        }

        public Builder setNumberPhone(String numberPhone) {
            Client.this.numberPhone = numberPhone;
            return this;
        }

        public Builder setEmail(String email) {
            Client.this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            Client.this.address = address;
            return this;
        }

        public Client build(){
            return Client.this;
        }
    }
}















