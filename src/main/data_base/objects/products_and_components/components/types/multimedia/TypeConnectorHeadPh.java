package main.data_base.objects.products_and_components.components.types.multimedia;

public enum TypeConnectorHeadPh{
    MINI_JACK3d5("Mini Jack 3.5 мм"),
    MINI_JACK4d5("Mini Jack 4.5 мм");

    String model;
    TypeConnectorHeadPh(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }
    public static String getOrigModel(String model){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeConnectorHeadPh.values().length; i++){
            if(model.equals(TypeConnectorHeadPh.values()[i].getModel())){
                result.append(TypeConnectorHeadPh.values()[i].toString());
                i = TypeConnectorHeadPh.values().length;
            }
        }

        return result.toString();
    }
}
