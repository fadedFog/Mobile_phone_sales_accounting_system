package main.data_base.objects.products_and_components.components.types.multimedia;

import main.data_base.objects.workers_and_data.data.Position;

public enum TypeSuppVideoFormat{
    HD("HD"),
    FULL_HD("Full HD"),
    FOUR_K("4K"),
    ULTRA_HD_4K("Ultra HD 4K");

    String format;
    TypeSuppVideoFormat(String format) {
        this.format = format;
    }
    public String getFormat() {
        return format;
    }
    public static String getOrigVFormat(String format){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeSuppVideoFormat.values().length; i++){
            if(format.equals(TypeSuppVideoFormat.values()[i].getFormat())){
                result.append(TypeSuppVideoFormat.values()[i].toString());
                i = TypeSuppVideoFormat.values().length;
            }
        }

        return result.toString();
    }
}
