package main.data_base.base.load_and_save;

import java.io.*;

public class StorageFileNames {

    public static void initNameFiles(){
        File file = new File("accounting_system");
        System.out.println("accounting_system.iml PATH:  "+ file.getAbsolutePath());

        String absolutePathProgram = getAbsPathName(file);
        System.out.println("projectPackage PATH:  " + absolutePathProgram);

        mainDirectory = absolutePathProgram;

        fillingMainDirectory(absolutePathProgram);
    }

    private static void fillingMainDirectory(String absPathStartProgram){
        String nameDirectory = "\\data";
        File main = new File(absPathStartProgram + nameDirectory);

        if(!main.exists()){
            createDirectories(absPathStartProgram, nameDirectory);
            wasExistProperties = false;
            System.out.println("not exits dirProperties 1");
        }else {
            System.out.println("Directory \"data\" is able!");
            makeCastPathNameChildDirectories(absPathStartProgram + nameDirectory);
            createPathNameTxtFiles();

            checkingExistChildDirectory(absPathStartProgram + nameDirectory);
        }
        fillingSpecificFiles();
    }

    //checking all child directories and their txt files
    private static void checkingExistChildDirectory(String absInDirectory){
        File client = new File(absInDirectory + pClient);
        File delivery = new File(absInDirectory + pDelivery);
        File productsAndDelivery = new File(absInDirectory + pProductsAndComponents);
        File PAC_components = new File(absInDirectory + pPAC_components);
        File PAC_products = new File(absInDirectory + pPAC_products);
        File PAC_warehouse = new File(absInDirectory + pPAC_warehouse);
        File properties = new File(absInDirectory + pProperties);
        File workers = new File(absInDirectory + pWorkers);

        File[] directories = {client, delivery, productsAndDelivery, PAC_components, PAC_products,
                            PAC_warehouse, properties, workers};

        for(File directory: directories){
            if(!isExistFile(directory)){
                createChileDirectory(directory);
                if(directory.getName().equals("properties")){
                    System.out.println("not exits dirProperties 2");
                    wasExistProperties = false;
                }
            }
        }

        String[] namesTxt = {nameClient, nameSale, nameDelivery, nameProvider,
                            nameCamera, nameConfiguration, nameCountryOfManufacture,
                            nameDisplay, nameMultimedia, nameCellPhone, nameWarehouse,
                            StorageFileNames.properties, nameWorker};

        for(String name: namesTxt){
            if(!isExistFile(new File(nameClient))){
                createTxtFile(name);
            }
        }


    }
    
    private static boolean isExistFile(File file){
        return file.exists();
    }
    

    //create all directory
    private static void createDirectories(String absPathStartProgram, String addingName){
        File dData = new File(absPathStartProgram, addingName);

        try{
            dData.mkdir();
            System.out.println("Directory data was create!");
            mainDirectory = absPathStartProgram + addingName;

            createChildDirectories(absPathStartProgram + addingName);
            System.out.println("Child directories was create!");
        }catch (SecurityException e){
            System.out.println("Cant create directory \"data\"");
        }

    }

    //create child directories and their txt files
    private static void makeCastPathNameChildDirectories(String pathNameIn){
        dirClient = pathNameIn + pClient;
        dirDelivery = pathNameIn + pDelivery;
//        dirPAC = pathNameIn + pProductsAndComponents;
        dirPAC_Components = pathNameIn + pPAC_components;
        dirPAC_Products = pathNameIn + pPAC_products;
        dirPAC_Warehouse = pathNameIn + pPAC_warehouse;
        dirProperties = pathNameIn + pProperties;
        dirWorkers = pathNameIn + pWorkers;
    }

    private static void createChildDirectories(String absPathStartProgram){
        File client = new File(absPathStartProgram + pClient);
        File delivery = new File(absPathStartProgram + pDelivery);
        File productsAndDelivery = new File(absPathStartProgram + pProductsAndComponents);
        File PAC_components = new File(absPathStartProgram + pPAC_components);
        File PAC_products = new File(absPathStartProgram + pPAC_products);
        File PAC_warehouse = new File(absPathStartProgram + pPAC_warehouse);
        File properties = new File(absPathStartProgram + pProperties);
        File workers = new File(absPathStartProgram + pWorkers);

        createChildDirectory(client, delivery, productsAndDelivery,
                PAC_components, PAC_products, PAC_warehouse, properties, workers);

        makeCastPathNameChildDirectories(absPathStartProgram);

        createPathNameTxtFiles();
        createTxtFile();
    }

    private static void createChildDirectory(File... files){
        for(File file: files){
            try{
                file.mkdir();
                System.out.println("Dir " + file.getName() + " was create");
            }catch (SecurityException ex){
                System.out.println("Cant create directory " + file.getName());
            }
        }
    }

    private static void createPathNameTxtFiles(){
       nameClient = dirClient + "\\clients.txt";
       nameSale = dirClient + "\\sales.txt";
       nameDelivery = dirDelivery + "\\deliveries.txt";
       nameProvider = dirDelivery + "\\providers.txt";
       nameCamera = dirPAC_Components + "\\dataCamera.txt";
       nameConfiguration = dirPAC_Components + "\\dataConfiguration.txt";
       nameCountryOfManufacture = dirPAC_Components + "\\daraCountryOfManufacture.txt";
       nameDisplay = dirPAC_Components + "\\dataDisplay.txt";
       nameMultimedia = dirPAC_Components + "\\dataMultimedia.txt";
       nameCellPhone = dirPAC_Products + "\\dataCellPhone.txt";
       nameWarehouse = dirPAC_Warehouse + "\\dataWarehouse.txt";
       properties = dirProperties + "\\properties.txt";
       nameWorker = dirWorkers + "\\dataWorker.txt";

    }

    private static void createTxtFile() {
        String[] names = {nameCellPhone, nameCamera, nameConfiguration, nameCountryOfManufacture,
                nameDisplay, nameMultimedia, nameDelivery, nameProvider, nameWorker, nameClient,
                nameSale, nameWarehouse, properties};

        for(String name: names){
            try{
                FileOutputStream fos = new FileOutputStream(name);
                fos.close();
            }catch (IOException e){
                System.out.println("Cant create txt:  " + name);
            }
        }
    }

    private static void createChileDirectory(File file){
        try{
            file.mkdir();
            System.out.println("Dir " + file.getName() + " was create");
        }catch (SecurityException ex){
            System.out.println("Cant create directory " + file.getName());
        }
    }

    private static void createTxtFile(String pathNameTxt){
        try{
            FileOutputStream fos = new FileOutputStream(pathNameTxt);
            fos.close();
        }catch (IOException e){
            System.out.println("Cant create txt:  " + pathNameTxt);
        }
    }

    //filling specific txt file data
    private static void fillingSpecificFiles(){
        writeInFile(nameCamera, StorageDataSpecificFiles.dataCamera);
        writeInFile(nameConfiguration, StorageDataSpecificFiles.dataConfiguration);
        writeInFile(nameCountryOfManufacture, StorageDataSpecificFiles.dataCountryOfManufacture);
        writeInFile(nameDisplay, StorageDataSpecificFiles.dataDisplay);
        writeInFile(nameMultimedia, StorageDataSpecificFiles.dataMultimedia);
        if(!wasExistProperties) {
            writeInFile(properties, StorageDataSpecificFiles.getInstance().dataAllProperties);
        }
    }

    private static void writeInFile(String nameFile, String data){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile))){
            writer.write(data);
        }catch (IOException e){
            System.out.println("Cant write data in file: " + nameFile);
        }
    }


    private static String getAbsPathName(File file){
        int toCut = 0;
        String absOld = file.getAbsolutePath();

        for(int i = absOld.length()-1; i >= 0; i--){
            if(absOld.charAt(i) == '\\'){
                toCut = i;
                i = 0;
            }
        }

        return absOld.substring(0, toCut);
    }


    /**Names directory only*/
    private static final String pClient = "\\client";
    private static final String pDelivery = "\\delivery";
    private static final String pProductsAndComponents = "\\products_and_components";
    private static final String pPAC_components = pProductsAndComponents + "\\components";
    private static final String pPAC_products = pProductsAndComponents + "\\products";
    private static final String pPAC_warehouse = pProductsAndComponents + "\\dataWarehouse";
    private static final String pProperties = "\\properties";
    private static final String pWorkers = "\\workers";

    /**Names directories*/
    public  static String mainDirectory;
    private static String dirClient;
    private static String dirDelivery;
//    private static String dirPAC;
    private static String dirPAC_Components;
    private static String dirPAC_Products;
    private static String dirPAC_Warehouse;
    private static String dirProperties;
    private static String dirWorkers;

    /**Names txt-files*/
    public static String nameCellPhone;
    public static String nameCamera;
    public static String nameConfiguration;
    public static String nameCountryOfManufacture;
    public static String nameDisplay;
    public static String nameMultimedia;
    public static String nameDelivery;
    public static String nameProvider;
    public static String nameWorker;
    public static String nameClient;
    public static String nameSale;
    public static String nameWarehouse;
    public static String properties;

    private static boolean wasExistProperties = true;
}
