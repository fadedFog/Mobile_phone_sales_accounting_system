package main.data_base.objects.products_and_components.components.types.display;

public enum TypeDisplayResolution{
    R2160x1080("2160x1080"), R2340x1080("2340x1080"),
    R1560x720("1560x720"), R1600x720("1600x720"),
    R2520x1080("2520x1080");

    String resolution;
    TypeDisplayResolution(String resolution) {
        this.resolution = resolution;
    }
    public String getResolution() {
        return resolution;
    }
    public static String getOrigResolution(String res){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeDisplayResolution.values().length; i++){
            if(res.equals(TypeDisplayResolution.values()[i].getResolution())){
                result.append(TypeDisplayResolution.values()[i].toString());
                i = TypeDisplayResolution.values().length;
            }
        }

        return result.toString();
    }
}
