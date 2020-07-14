package main.data_base.objects.products_and_components.components.types.configuration;

public enum TypeMaxSizeSM{
    SM64(64), SM128(128), SM256(256), SM512(512), SM1024(1024);

    int size;
    TypeMaxSizeSM(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public static String getOrigMaxSizeSM(int size){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeMaxSizeSM.values().length; i++){
            if(size == TypeMaxSizeSM.values()[i].getSize()){
                result.append(TypeMaxSizeSM.values()[i].toString());
                i = TypeMaxSizeSM.values().length;
            }
        }

        return result.toString();
    }
}
