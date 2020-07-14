package main.data_base.objects.products_and_components.components.types.display;

public enum TypeTouchScreen{
    DUST("пиль"), SCRATCH("царапин"), MOISTURE("влаги");

    String protect;
    TypeTouchScreen(String protect) {
        this.protect = protect;
    }
    public String getProtect() {
        return protect;
    }
    public static String getOrigProtect(String protect){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeTouchScreen.values().length; i++){
            if(protect.equals(TypeTouchScreen.values()[i].getProtect())){
                result.append(TypeTouchScreen.values()[i].toString());
                i = TypeTouchScreen.values().length;
            }
        }

        return result.toString();
    }
}
