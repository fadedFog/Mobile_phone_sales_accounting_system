package main.data_base.objects.products_and_components.products;

import java.io.Serializable;
import java.util.Objects;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class CellPhone implements Serializable {
    private CellPhone(){}

    private int articleNumber;
    private String name;
    private double price;
    private String classE;
    private String typeCorpus;
    private int numberSIM;
    private String OS;
    private int display;
    private int configuration;
    private int camera;
    private int multimedia;
    private String corpus;
    private int country;

    private String shortName;

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getClassE() {
        return classE;
    }

    public String getTypeCorpus() {
        return typeCorpus;
    }

    public int getNumberSIM() {
        return numberSIM;
    }

    public String getOS() {
        return OS;
    }

    public int getDisplay() {
        return display;
    }

    public int getConfiguration() {
        return configuration;
    }

    public int getCamera() {
        return camera;
    }

    public int getMultimedia() {
        return multimedia;
    }

    public String getCorpus() {
        return corpus;
    }

    public int getCountry() {
        return country;
    }

    public String getShortName(){
        return shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return articleNumber == cellPhone.articleNumber &&
                Double.compare(cellPhone.price, price) == 0 &&
                numberSIM == cellPhone.numberSIM &&
                display == cellPhone.display &&
                configuration == cellPhone.configuration &&
                camera == cellPhone.camera &&
                multimedia == cellPhone.multimedia &&
                country == cellPhone.country &&
                name.equals(cellPhone.name) &&
                classE.equals(cellPhone.classE) &&
                typeCorpus.equals(cellPhone.typeCorpus) &&
                OS.equals(cellPhone.OS) &&
                corpus.equals(cellPhone.corpus) &&
                shortName.equals(cellPhone.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleNumber, name, price, classE, typeCorpus, numberSIM, OS, display, configuration, camera, multimedia, corpus, country, shortName);
    }

    public static Builder newBuilder(){
        return new CellPhone().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setArticleNumber(int articleNumber) {
            CellPhone.this.articleNumber = articleNumber;
            return this;
        }

        public Builder setName(String name) {
            CellPhone.this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            CellPhone.this.price = price;
            return this;
        }

        public Builder setClassE(String classE) {
            CellPhone.this.classE = classE;
            return this;
        }

        public Builder setTypeCorpus(String typeCorpus) {
            CellPhone.this.typeCorpus = typeCorpus;
            return this;
        }

        public Builder setNumberSIM(int numberSIM) {
            CellPhone.this.numberSIM = numberSIM;
            return this;
        }

        public Builder setOS(String OS) {
            CellPhone.this.OS = OS;
            return this;
        }

        public Builder setDisplay(int display) {
            CellPhone.this.display = display;
            return this;
        }

        public Builder setConfiguration(int configuration) {
            CellPhone.this.configuration = configuration;
            return this;
        }

        public Builder setCamera(int camera) {
            CellPhone.this.camera = camera;
            return this;
        }

        public Builder setMultimedia(int multimedia) {
            CellPhone.this.multimedia = multimedia;
            return this;
        }

        public Builder setCorpus(String corpus) {
            CellPhone.this.corpus = corpus;
            return this;
        }

        public Builder setCountry(int country) {
            CellPhone.this.country = country;
            return this;
        }


        public Builder setShortName(String articleWithName){
            CellPhone.this.shortName = articleWithName;
            return this;
        }

        public CellPhone build(){
            return CellPhone.this;
        }
    }
}

