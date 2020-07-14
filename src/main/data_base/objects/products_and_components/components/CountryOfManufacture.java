package main.data_base.objects.products_and_components.components;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class CountryOfManufacture {
    private CountryOfManufacture(){}

    private int code;
    private String country;

    public int getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public static Builder newBuilder(){
        return new CountryOfManufacture().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setCode(int code) {
            CountryOfManufacture.this.code = code;
            return this;
        }

        public Builder setCountry(String country) {
            CountryOfManufacture.this.country = country;
            return this;
        }

        public CountryOfManufacture build(){
            return CountryOfManufacture.this;
        }
    }
}

