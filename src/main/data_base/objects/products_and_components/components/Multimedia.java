package main.data_base.objects.products_and_components.components;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class Multimedia {
    private Multimedia(){}

    private int code;
    private String connectorHeadPhone;
    private String interfaceUSB;
    private String suppVideoFormat;

    public int getCode() {
        return code;
    }

    public String getConnectorHeadPhone() {
        return connectorHeadPhone;
    }

    public String getInterfaceUSB() {
        return interfaceUSB;
    }

    public String getSuppVideoFormat() {
        return suppVideoFormat;
    }


    public static Builder newBuilder(){
        return new Multimedia().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setCode(int code) {
            Multimedia.this.code = code;
            return this;
        }

        public Builder setConnectorHeadPhone(String connectorHeadPhone) {
            Multimedia.this.connectorHeadPhone = connectorHeadPhone;
            return this;
        }

        public Builder setInterfaceUSB(String interfaceUSB) {
            Multimedia.this.interfaceUSB = interfaceUSB;
            return this;
        }

        public Builder setSuppVideoFormat(String suppVideoFormat) {
            Multimedia.this.suppVideoFormat = suppVideoFormat;
            return this;
        }

        public Multimedia build(){
            return Multimedia.this;
        }
    }
}

