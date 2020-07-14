package main.data_base.objects.products_and_components.components;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class Display {
    private Display(){}

    private int code;
    private double displayDiagonal;
    private String displayResolution;
    private int numberPPI;
    private String touchScreen;

    public int getCode() {
        return code;
    }

    public double getDisplayDiagonal() {
        return displayDiagonal;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public int getNumberPPI() {
        return numberPPI;
    }

    public String getTouchScreen() {
        return touchScreen;
    }


    public static Builder newBuilder(){
        return new Display().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setCode(int code) {
            Display.this.code = code;
            return this;
        }

        public Builder setDisplayDiagonal(double diagonal) {
            Display.this.displayDiagonal = diagonal;
            return this;
        }

        public Builder setDisplayResolution(String displayResolution) {
            Display.this.displayResolution = displayResolution;
            return this;
        }

        public Builder setNumberPPI(int numberPPI) {
            Display.this.numberPPI = numberPPI;
            return this;
        }

        public Builder setTouchScreen(String touchScreen) {
            Display.this.touchScreen = touchScreen;
            return this;
        }

        public Display build(){
            return Display.this;
        }

    }
}

