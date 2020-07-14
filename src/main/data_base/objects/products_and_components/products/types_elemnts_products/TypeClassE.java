package main.data_base.objects.products_and_components.products.types_elemnts_products;

public enum TypeClassE{
    SMARTPHONE("Смартфон");

    String name;
    TypeClassE(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static String getOrigClassE(String classE){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeClassE.values().length; i++){
            if(classE.equals(TypeClassE.values()[i].getName())){
                result.append(TypeClassE.values()[i].toString());
                i = TypeClassE.values().length;
            }
        }

        return result.toString();
    }
}
