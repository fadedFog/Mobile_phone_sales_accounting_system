package main.data_base.objects.products_and_components.components.types.multimedia;

public enum TypeInterfaceUSB{
    USB_TYPEC("USB Type-C");

    private String name;
    TypeInterfaceUSB(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static String getOrigUSB(String usb){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeInterfaceUSB.values().length; i++){
            if(usb.equals(TypeInterfaceUSB.values()[i].getName())){
                result.append(TypeInterfaceUSB.values()[i].toString());
                i = TypeInterfaceUSB.values().length;
            }
        }

        return result.toString();
    }
}
