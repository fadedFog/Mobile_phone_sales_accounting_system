package main.data_base.objects.products_and_components.components.types.country;

public enum Country{
    CHINA("Китай"),
    VIETNAM("Въетнам"),
    JAPANESE("Япония"),
    USA("США");

    String country;
    Country(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    public static String getOrigCountry(String country){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < Country.values().length; i++){
            if(country.equals(Country.values()[i].getCountry())){
                result.append(Country.values()[i].toString());
                i = Country.values().length;
            }
        }

        return result.toString();
    }
}
