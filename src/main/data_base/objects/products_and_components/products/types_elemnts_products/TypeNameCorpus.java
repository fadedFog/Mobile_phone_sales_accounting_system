package main.data_base.objects.products_and_components.products.types_elemnts_products;

public enum TypeNameCorpus{
    CORNING_GORILLA_GLASS_5("Corning Gorilla Glass 5"),
    SUPER_AMOLED("Super AMOLED");

    String name;
    TypeNameCorpus(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static String getOrigNameCorpus(String nCorpus){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeNameCorpus.values().length; i++){
            if(nCorpus.equals(TypeNameCorpus.values()[i].getName())){
                result.append(TypeNameCorpus.values()[i].toString());
                i = TypeNameCorpus.values().length;
            }
        }

        return result.toString();
    }

}
