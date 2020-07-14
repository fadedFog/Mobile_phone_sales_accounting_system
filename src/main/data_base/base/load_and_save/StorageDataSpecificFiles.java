package main.data_base.base.load_and_save;

public class StorageDataSpecificFiles {
    public static final String dataCamera = "1&12 Mp&14 Mp&HD&LED!\n" +
            "2&12 Mp&14 Mp&FULL_HD&LED!\n" +
            "3&12 Mp&14 Mp&HD&XENON!\n" +
            "4&16 Mp&22 Mp&HD&LED!\n" +
            "5&16 Mp&22 Mp&HD&XENON!\n" +
            "6&20 Mp&22 Mp&FULL_HD&LED!\n" +
            "7&20 Mp&22 Mp&FULL_HD&XENON!\n" +
            "8&20 Mp&22 Mp&FOUR_K&XENON!\n" +
            "9&22 Mp&26 Mp&FULL_HD&LED!\n" +
            "10&22 Mp&26 Mp&FOUR_K&XENON!\n" +
            "11&22 Mp&26 Mp&FOUR_K&LED!\n" +
            "12&26 Mp&32 Mp&FOUR_K&XENON!";

    public static final String dataConfiguration = "1&HISILICON_KIRIN_710F&R4&IM64&SM64!\n" +
            "2&HISILICON_KIRIN_710F&R6&IM128&SM128!\n" +
            "3&HISILICON_KIRIN_710F&R8&IM256&SM256!\n" +
            "4&HISILICON_KIRIN_710F&R12&IM256&SM512!\n" +
            "5&HISILICON_KIRIN_710F&R16&IM256&SM1024!\n" +
            "6&MEDIATEK_MT6750S&R4&IM64&SM64!\n" +
            "7&MEDIATEK_MT6750S&R6&IM128&SM128!\n" +
            "8&MEDIATEK_MT6750S&R8&IM256&SM256!\n" +
            "9&MEDIATEK_MT6750S&R12&IM256&SM512!\n" +
            "10&MEDIATEK_MT6750S&R16&IM256&SM1024!\n" +
            "11&MEDIATEK_MT6750T&R4&IM64&SM64!\n" +
            "12&MEDIATEK_MT6750T&R6&IM128&SM128!\n" +
            "13&MEDIATEK_MT6750T&R8&IM256&SM256!\n" +
            "14&MEDIATEK_MT6750T&R12&IM256&SM512!\n" +
            "15&MEDIATEK_MT6750T&R16&IM256&SM1024!\n" +
            "16&SNAPDRAGON_710&R4&IM64&SM64!\n" +
            "17&SNAPDRAGON_710&R6&IM128&SM128!\n" +
            "18&SNAPDRAGON_710&R8&IM256&SM256!\n" +
            "19&SNAPDRAGON_710&R12&IM256&SM512!\n" +
            "20&SNAPDRAGON_710&R16&IM256&SM1024!";

    public static final String dataCountryOfManufacture = "1&CHINA!\n" +
            "2&JAPANESE!\n" +
            "3&VIETNAM!\n" +
            "5&USA!";

    public static final String dataDisplay = "1&D5_5&R1560x720&420&DUST!\n" +
            "2&D5_5&R1560x720&420&SCRATCH!\n" +
            "3&D5_5&R1560x720&420&MOISTURE!\n" +
            "4&D5_5&R1560x720&420&DUST;SCRATCH!\n" +
            "5&D5_5&R1560x720&420&DUST;MOISTURE!\n" +
            "6&D5_5&R1600x720&470&DUST!\n" +
            "7&D5_5&R1600x720&470&SCRATCH!\n" +
            "8&D5_5&R1600x720&470&MOISTURE!\n" +
            "9&D5_5&R1600x720&470&DUST;SCRATCH!\n" +
            "10&D5_5&R1600x720&470&DUST;MOISTURE!\n" +
            "11&D6_3&R1600x720&475&DUST!\n" +
            "12&D6_3&R1600x720&475&SCRATCH!\n" +
            "13&D6_3&R1600x720&475&MOISTURE!\n" +
            "14&D6_3&R1600x720&475&DUST;SCRATCH!\n" +
            "15&D6_3&R1600x720&475&DUST;MOISTURE!\n" +
            "16&D6_7&R2160x1080&520&DUST!\n" +
            "17&D6_7&R2160x1080&520&SCRATCH!\n" +
            "18&D6_7&R2160x1080&520&MOISTURE!\n" +
            "19&D6_7&R2160x1080&520&DUST;SCRATCH!\n" +
            "20&D6_7&R2160x1080&520&DUST;MOISTURE!";

    public static final String dataMultimedia = "1&MINI_JACK3d5&USB_TYPEC&HD!\n" +
            "2&MINI_JACK3d5&USB_TYPEC&FULL_HD!\n" +
            "3&MINI_JACK3d5&USB_TYPEC&FOUR_K!\n" +
            "4&MINI_JACK3d5&USB_TYPEC&ULTRA_HD_4K!\n" +
            "5&MINI_JACK4d5&USB_TYPEC&HD!\n" +
            "6&MINI_JACK4d5&USB_TYPEC&FULL_HD!\n" +
            "7&MINI_JACK4d5&USB_TYPEC&FOUR_K!\n" +
            "8&MINI_JACK4d5&USB_TYPEC&ULTRA_HD_4K!";

    private final String password = "password:!\n";

    public String dataAllProperties = password +
            "phoneShop:8912500596!\n" +
                    "address:ул. Чапаева, д.56, Рязань!\n" +
                    "codeShop:5309!";

    public String dataProperties =
            "phoneShop:8912500596!\n" +
            "address:ул. Чапаева, д.56, Рязань!\n" +
            "codeShop:5309!";

    private static StorageDataSpecificFiles storageDataSpecificFiles;
    private StorageDataSpecificFiles(){}
    public static StorageDataSpecificFiles getInstance(){
        if(storageDataSpecificFiles == null){
            storageDataSpecificFiles = new StorageDataSpecificFiles();
        }
        return storageDataSpecificFiles;
    }
}
