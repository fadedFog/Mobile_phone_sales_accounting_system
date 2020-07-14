package main.data_base.objects.products_and_components.components.types.configuration;

public enum TypeProcessor{
    HISILICON_KIRIN_710F("HiSilicon Kirin 710F"),
    MEDIATEK_MT6750S("MediaTek MT6750S"),
    MEDIATEK_MT6750T("MediaTek MT6750T"),
    SNAPDRAGON_710("Snapdragon 710");

    String name;
    TypeProcessor(String name) {
        this.name = name;
    }
    public String getName(){return name;}
    public static String getOrigProcessor(String processor){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeProcessor.values().length; i++){
            if(processor.equals(TypeProcessor.values()[i].getName())){
                result.append(TypeProcessor.values()[i].toString());
                i = TypeProcessor.values().length;
            }
        }

        return result.toString();
    }
}
