package main.data_base.objects.products_and_components.components.types.configuration;

public enum TypeInMemory{
    IM64(64), IM128(128), IM256(256);

    int size;
    TypeInMemory(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public static String getOrigInMemory(int size){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeInMemory.values().length; i++){
            if(size == TypeInMemory.values()[i].getSize()){
                result.append(TypeInMemory.values()[i].toString());
                i = TypeInMemory.values().length;
            }
        }

        return result.toString();
    }
}
