package main.data_base.objects.products_and_components.products.types_elemnts_products;

public enum TypeCorpus{
    PLASTIC("пластик"),
    METAL("металл"),
    GLASS("стекло");

    String name;
    TypeCorpus(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static String getOrigCorpus(String corpus){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeCorpus.values().length; i++){
            if(corpus.equals(TypeCorpus.values()[i].getName())){
                result.append(TypeCorpus.values()[i].toString());
                i = TypeCorpus.values().length;
            }
        }

        return result.toString();
    }
}
