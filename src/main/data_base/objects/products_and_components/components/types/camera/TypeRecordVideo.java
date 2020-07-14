package main.data_base.objects.products_and_components.components.types.camera;

public enum TypeRecordVideo{
    HD("HD"),
    FULL_HD("Full HD"),
    FOUR_K("4K");

    String quality;
    TypeRecordVideo(String quality) {
        this.quality = quality;
    }
    public String getQuality() {
        return quality;
    }
    public static String getOrigRecordVideo(String rVideo){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeRecordVideo.values().length; i++){
            if(rVideo.equals(TypeRecordVideo.values()[i].getQuality())){
                result.append(TypeRecordVideo.values()[i].toString());
                i = TypeRecordVideo.values().length;
            }
        }

        return result.toString();
    }
}
