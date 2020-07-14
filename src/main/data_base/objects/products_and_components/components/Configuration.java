package main.data_base.objects.products_and_components.components;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class Configuration {
    private Configuration(){}

    private int code;
    private String processor;
    private int sizeRAM;
    private int sizeInMemory;
    private int maxSizeStorageMemory;

    public int getCode() {
        return code;
    }

    public String getProcessor() {
        return processor;
    }

    public int getSizeRAM() {
        return sizeRAM;
    }

    public int getSizeInMemory() {
        return sizeInMemory;
    }

    public int getMaxSizeStorageMemory() {
        return maxSizeStorageMemory;
    }


    public static Builder newBuilder(){
        return new Configuration().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setCode(int code) {
            Configuration.this.code = code;
            return this;
        }

        public Builder setProcessor(String processor) {
            Configuration.this.processor = processor;
            return this;
        }

        public Builder setSizeRAM(int sizeRAM) {
            Configuration.this.sizeRAM = sizeRAM;
            return this;
        }

        public Builder setSizeInMemory(int sizeInMemory) {
            Configuration.this.sizeInMemory = sizeInMemory;
            return this;
        }

        public Builder setMaxSizeStorageMemory(int maxSizeStorageMemory) {
            Configuration.this.maxSizeStorageMemory = maxSizeStorageMemory;
            return this;
        }


        public Configuration build(){
            return Configuration.this;
        }
    }
}

