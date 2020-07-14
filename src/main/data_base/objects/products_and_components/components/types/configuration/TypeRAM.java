package main.data_base.objects.products_and_components.components.types.configuration;

public enum TypeRAM{
    R4(4), R6(6), R8(8), R12(12), R16(16);

    int size;
    TypeRAM(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public static String getOrigRAM(int post){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeRAM.values().length; i++){
            if(post == TypeRAM.values()[i].getSize()){
                result.append(TypeRAM.values()[i].toString());
                i = TypeRAM.values().length;
            }
        }

        return result.toString();
    }
}
