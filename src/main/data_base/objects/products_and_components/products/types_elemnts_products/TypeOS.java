package main.data_base.objects.products_and_components.products.types_elemnts_products;

public enum TypeOS{
    AND9d0_PIE("Android 9.0 Pie"),
    AND9d6_PRO("Android 9.6 Pro"),
    AND10d0_PIE("Android 10.0 Pie"),
    AND10d3_PRO("Android 10.3 Pro"),
    AND11d1_PIE("Android 11.1 Pie"),
    AND11d8_PRO("Android 11.8 Pro");

    String name;
    TypeOS(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static String getOrigOS(String os){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeOS.values().length; i++){
            if(os.equals(TypeOS.values()[i].getName())){
                result.append(TypeOS.values()[i].toString());
                i = TypeOS.values().length;
            }
        }

        return result.toString();
    }
}
