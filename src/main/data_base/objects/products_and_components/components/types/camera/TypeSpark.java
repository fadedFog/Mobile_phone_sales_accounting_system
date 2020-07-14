package main.data_base.objects.products_and_components.components.types.camera;

public enum TypeSpark{
    LED("светодиодная"),
    XENON("ксеоновая");

    String name;
    TypeSpark(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static String getOrigSpark(String name){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeSpark.values().length; i++){
            if(name.equals(TypeSpark.values()[i].getName())){
                result.append(TypeSpark.values()[i].toString());
                i = TypeSpark.values().length;
            }
        }

        return result.toString();
    }
}
