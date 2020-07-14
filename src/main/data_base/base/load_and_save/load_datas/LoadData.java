package main.data_base.base.load_and_save.load_datas;

import main.application.Properties;
import main.data_base.base.AccountingSystem;
import main.data_base.base.UtilMethods;
import main.data_base.base.load_and_save.StorageFileNames;
import main.data_base.objects.products_and_components.warehouse.Warehouse;
import main.data_base.objects.sales.Sale;
import main.data_base.objects.sales.client.Client;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.delivery.Provider;
import main.data_base.objects.products_and_components.components.*;
import main.data_base.objects.products_and_components.components.types.camera.TypeRecordVideo;
import main.data_base.objects.products_and_components.components.types.camera.TypeSpark;
import main.data_base.objects.products_and_components.components.types.configuration.TypeInMemory;
import main.data_base.objects.products_and_components.components.types.configuration.TypeMaxSizeSM;
import main.data_base.objects.products_and_components.components.types.configuration.TypeProcessor;
import main.data_base.objects.products_and_components.components.types.configuration.TypeRAM;
import main.data_base.objects.products_and_components.components.types.country.Country;
import main.data_base.objects.products_and_components.components.types.display.TypeDiagonal;
import main.data_base.objects.products_and_components.components.types.display.TypeDisplayResolution;
import main.data_base.objects.products_and_components.components.types.display.TypeTouchScreen;
import main.data_base.objects.products_and_components.components.types.multimedia.TypeConnectorHeadPh;
import main.data_base.objects.products_and_components.components.types.multimedia.TypeInterfaceUSB;
import main.data_base.objects.products_and_components.components.types.multimedia.TypeSuppVideoFormat;
import main.data_base.objects.products_and_components.products.*;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeClassE;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeNameCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeOS;
import main.data_base.objects.workers_and_data.data.Position;
import main.data_base.objects.workers_and_data.workers.Worker;

import java.io.*;
import java.util.*;

public class LoadData {

    /** READ data from files */
    private void readFormFile(List<String> list, String nameFile){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))){
            while((line = reader.readLine()) != null){
                list.add(line);
            }

        }catch (IOException e){
            System.out.println("FileNotFoundException");
        }
    }

    public void loadDelivery(){
        Delivery[] deliveries;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(StorageFileNames.nameDelivery))){
            deliveries = (Delivery[]) ois.readObject();
            accountingSystem.setDeliveries(deliveries);

        }catch (IOException | ClassNotFoundException e){
            System.err.println("Cant load data for array deliveries");
            accountingSystem.setDeliveries(new Delivery[0]);
        }


    }

    public void loadCellPhones(){
        readFormFile(linesCellPhones, StorageFileNames.nameCellPhone);
        dataTransmissionCellPhone();
    }

    public void loadCamera(){
        readFormFile(linesCamera, StorageFileNames.nameCamera);
        dataTransmissionCamera();
    }

    public void loadConfiguration(){
        readFormFile(linesConfiguration, StorageFileNames.nameConfiguration);
        dataTransmissionConfiguration();
    }

    public void loadCountryOfManufacture(){
        readFormFile(linesCountryOfManufacture, StorageFileNames.nameCountryOfManufacture);
        dataTransmissionCountryOfManufacture();
    }

    public void loadDisplay(){
        readFormFile(linesDisplay, StorageFileNames.nameDisplay);
        dataTransmissionDisplay();
    }

    public void loadMultimedia(){
        readFormFile(linesMultimedia, StorageFileNames.nameMultimedia);
        dataTransmissionMultimedia();
    }

    public void loadWorker(){
        readFormFile(linesWorker, StorageFileNames.nameWorker);
        dataTransmissionWorker();
    }

    public void loadProvider(){
        readFormFile(linesProvider, StorageFileNames.nameProvider);
        dataTransmissionProvider();
    }

    public void loadClient(){
        readFormFile(linesClient, StorageFileNames.nameClient);
        dataTransmissionClient();
    }

    public void loadSales(){
        Sale[] sales;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(StorageFileNames.nameSale))){
            sales = (Sale[]) ois.readObject();
            accountingSystem.setSale(sales);
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Cant load data for array sales");
            accountingSystem.setSale(new Sale[0]);
        }
    }

    public void loadWarehouse(){
        Warehouse[] warehouses;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(StorageFileNames.nameWarehouse))){

            warehouses = (Warehouse[]) ois.readObject();
            Warehouse.getInstance().setWarehouse(warehouses[0]);

//            Warehouse.getInstance().iterationMap();
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Cant load data for Warehouse");

            Warehouse warehouse = Warehouse.getInstance();

            if(accountingSystem.getDeliveries().length != 0 && accountingSystem.getDeliveries() != null){
                Delivery[] deliveries = accountingSystem.getDeliveries();

                for (Delivery delivery : deliveries) {
                    warehouse.addingCellPhones(delivery.getOfDeliveries());
                }
            }

            if(accountingSystem.getSale().length != 0 && accountingSystem.getSale() != null){
                Sale[] sales = accountingSystem.getSale();

                for (Sale sale : sales) {
                    warehouse.sellingCellPhones(sale.getOfSales());
                }
            }

        }
    }

    public void loadAllTables(){
        loadCellPhones();
        loadCamera();
        loadConfiguration();
        loadCountryOfManufacture();
        loadDisplay();
        loadMultimedia();
        loadWorker();
        loadDelivery();
        loadProvider();
        loadClient();
        loadSales();
        loadWarehouse();
    }

    public void loadProperties(){
        readFormFile(properties, StorageFileNames.properties);
        dataTransmissionProperties();
    }


    /** Filling cash for AccountingSystem.class and Properties.class*/

    private String[] getReadableData(String strangeData){
        String[] elements = strangeData.split("&");
        int lastId = elements.length-1;
        int lengthLastElement = elements[lastId].length()-1;

        elements[lastId] = elements[lastId].substring(0, lengthLastElement);
        return elements;
    }

    private String[] getReadableDataProperties(String line){
        return line.split(":");
    }

    private void dataTransmissionCellPhone(){
        CellPhone[] cellPhones = new CellPhone[linesCellPhones.size()];

        for(int i = 0; i < linesCellPhones.size(); i++){
            String[] readableData = getReadableData(linesCellPhones.get(i));

            int articleNumber = Integer.parseInt(readableData[0]);
            String name = readableData[1];
            double price = Double.parseDouble(readableData[2]);
            String classE = TypeClassE.valueOf(readableData[3]).getName();

            String[] typesCorpus = UtilMethods.getElementsFromArray(readableData[4], ";");
            String typeCorpus = getLinePropertiesTypeCorpus(typesCorpus);

            int numberSIM = Integer.parseInt(readableData[5]);
            String OS = TypeOS.valueOf(readableData[6]).getName();
            int display = Integer.parseInt(readableData[7]);
            int configuration = Integer.parseInt(readableData[8]);
            int camera = Integer.parseInt(readableData[9]);
            int multimedia = Integer.parseInt(readableData[10]);
            String corpus = TypeNameCorpus.valueOf(readableData[11]).getName();
            int country = Integer.parseInt(readableData[12]);

            String shortName = readableData[13];

            CellPhone cellPhone = CellPhone.newBuilder().setArticleNumber(articleNumber)
                                                        .setName(name)
                                                        .setPrice(price)
                                                        .setClassE(classE)
                                                        .setTypeCorpus(typeCorpus)
                                                        .setNumberSIM(numberSIM)
                                                        .setOS(OS)
                                                        .setDisplay(display)
                                                        .setConfiguration(configuration)
                                                        .setCamera(camera)
                                                        .setMultimedia(multimedia)
                                                        .setCorpus(corpus)
                                                        .setCountry(country)
                                                        .setShortName(shortName).build();

            cellPhones[i] = cellPhone;
        }

        accountingSystem.setCellPhones(cellPhones);
    }

    public static String getLinePropertiesTypeCorpus(String[] typesCorpus){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < typesCorpus.length; i++){
            result.append(TypeCorpus.valueOf(typesCorpus[i]).getName());

            if(i != typesCorpus.length-1){
                result.append(", ");
            }
        }

        return result.toString();
    }

    private void dataTransmissionCamera(){
        Camera[] cameras = new Camera[linesCamera.size()];

        for(int i = 0; i < linesCamera.size(); i++){
            String[] readableData = getReadableData(linesCamera.get(i));

            int code = Integer.parseInt(readableData[0]);
            String mainCamera = readableData[1];
            String frontCamera = readableData[2];
            String recordVideo = TypeRecordVideo.valueOf(readableData[3]).getQuality();
            String typeSpark = TypeSpark.valueOf(readableData[4]).getName();

            Camera camera = Camera.newBuilder().setCode(code)
                                                .setMainCamera(mainCamera)
                                                .setFrontCamera(frontCamera)
                                                .setRecordVideo(recordVideo)
                                                .setTypeSpark(typeSpark).build();

            cameras[i] = camera;

        }

        accountingSystem.setCameras(cameras);
    }

    private void dataTransmissionConfiguration(){
        Configuration[] configs = new Configuration[linesConfiguration.size()];

        for(int i = 0; i < linesConfiguration.size(); i++){
            String[] readableData = getReadableData(linesConfiguration.get(i));

            int code = Integer.parseInt(readableData[0]);
            String processor = TypeProcessor.valueOf(readableData[1]).getName();
            int sizeRam = TypeRAM.valueOf(readableData[2]).getSize();
            int sizeInMemory = TypeInMemory.valueOf(readableData[3]).getSize();
            int maxSizeStorageMemory = TypeMaxSizeSM.valueOf(readableData[4]).getSize();

            Configuration config = Configuration.newBuilder().setCode(code)
                                                                .setProcessor(processor)
                                                                .setSizeRAM(sizeRam)
                                                                .setSizeInMemory(sizeInMemory)
                                                                .setMaxSizeStorageMemory(maxSizeStorageMemory).build();
            configs[i] = config;
        }

        accountingSystem.setConfigurations(configs);
    }

    private void dataTransmissionCountryOfManufacture(){
        CountryOfManufacture[] countries = new CountryOfManufacture[linesCountryOfManufacture.size()];

        for(int i = 0; i < linesCountryOfManufacture.size(); i++){
            String[] readableData = getReadableData(linesCountryOfManufacture.get(i));

            int code = Integer.parseInt(readableData[0]);
            String countryName = Country.valueOf(readableData[1]).getCountry();

            CountryOfManufacture country = CountryOfManufacture.newBuilder().setCode(code)
                                                                            .setCountry(countryName).build();

            countries[i] = country;
        }

        accountingSystem.setCountryOfManufactures(countries);
    }

    private void dataTransmissionDisplay(){
        Display[] displays = new Display[linesDisplay.size()];

        for(int i = 0; i < linesDisplay.size(); i++){
            String[] readableData = getReadableData(linesDisplay.get(i));

            int code = Integer.parseInt(readableData[0]);
            double displayDiagonal = TypeDiagonal.valueOf(readableData[1]).getDiagonal();
            String displayResolution = TypeDisplayResolution.valueOf(readableData[2]).getResolution();
            int numberPPI = Integer.parseInt(readableData[3]);

            String[] typesTouchScreen = UtilMethods.getElementsFromArray(readableData[4], ";");
            String touchScreen = getLinePropertiesTypeTouchScreen(typesTouchScreen);

            Display display = Display.newBuilder().setCode(code)
                                                    .setDisplayDiagonal(displayDiagonal)
                                                    .setDisplayResolution(displayResolution)
                                                    .setNumberPPI(numberPPI)
                                                    .setTouchScreen(touchScreen).build();
            displays[i] = display;
        }

        accountingSystem.setDisplays(displays);
    }

    public static String getLinePropertiesTypeTouchScreen(String[] typesTouchScreens){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < typesTouchScreens.length; i++){
            result.append(TypeTouchScreen.valueOf(typesTouchScreens[i]).getProtect());

            if(i != typesTouchScreens.length-1){
                result.append(", ");
            }
        }

        return result.toString();
    }

    private void dataTransmissionMultimedia(){
        Multimedia[] multimedia = new Multimedia[linesMultimedia.size()];

        for(int i = 0; i < linesMultimedia.size(); i++){
            String[] readableData = getReadableData(linesMultimedia.get(i));

            int code = Integer.parseInt(readableData[0]);
            String connectionHeadPhone = TypeConnectorHeadPh.valueOf(readableData[1]).getModel();
            String interfaceUSB = TypeInterfaceUSB.valueOf(readableData[2]).getName();
            String suppVideoFormat = TypeSuppVideoFormat.valueOf(readableData[3]).getFormat();

            Multimedia mult = Multimedia.newBuilder().setCode(code)
                                                        .setConnectorHeadPhone(connectionHeadPhone)
                                                        .setInterfaceUSB(interfaceUSB)
                                                        .setSuppVideoFormat(suppVideoFormat).build();

            multimedia[i] = mult;
        }

        accountingSystem.setMultimedia(multimedia);
    }

    private void dataTransmissionWorker(){
        Worker[] workers = new Worker[linesWorker.size()];

        for(int i = 0; i < linesWorker.size(); i++){
            String[] readableData = getReadableData(linesWorker.get(i));

            int serviceNumber = Integer.parseInt(readableData[0]);
            String secondName = readableData[1];
            String name = readableData[2];
            String middleName = readableData[3];
            String position = Position.valueOf(readableData[4]).getPost();
            String numberPhone = readableData[5];

            Worker worker = Worker.newBuilder().setServiceNumber(serviceNumber)
                                                .setSecondName(secondName)
                                                .setName(name)
                                                .setMiddleName(middleName)
                                                .setPosition(position)
                                                .setNumberPhone(numberPhone).build();

            workers[i] = worker;
        }

        accountingSystem.setWorkers(workers);
    }

//    private void dataTransmissionDelivery(){
//        Delivery[] deliveries = new Delivery[linesDelivery.size()];
//
//        for(int i = 0; i < linesDelivery.size(); i++){
//            String[] readableData = getReadableData(linesDelivery.get(i));
//
//            int number = Integer.parseInt(readableData[0]);
//            String date = readableData[1];
//            int providerCode = Integer.parseInt(readableData[2]);
//            int serviceNumberWorker = Integer.parseInt(readableData[3]);
//
//            int lengthAmount = readableData[4].length();
//
//            String charsAmount = readableData[4].substring(0, lengthAmount-1);
//            double amount = Double.parseDouble(charsAmount);
//
//            Delivery delivery = Delivery.newBuilder().setNumber(number)
//                                                        .setDate(date)
//                                                        .setProviderCode(providerCode)
//                                                        .setServiceNumberWorker(serviceNumberWorker)
//                                                        .setAmount(amount).build();
//            deliveries[i] = delivery;
//        }
//
//        accountingSystem.setDeliveries(deliveries);
//    }

    private void dataTransmissionProvider(){
        Provider[] providers = new Provider[linesProvider.size()];

        for(int i = 0; i < linesProvider.size(); i++){
            String[] readableData = getReadableData(linesProvider.get(i));

            int code = Integer.parseInt(readableData[0]);
            String name = readableData[1];
            String INN = readableData[2];
            String CPP = readableData[3];
            String address = readableData[4];
            String numberPhone = readableData[5];
            String paymentAccount = readableData[6];
            String bank = readableData[7];
            String BIK = readableData[8];
            String correspondentAccount = readableData[9];

            Provider provider = Provider.newBuilder().setCode(code)
                                                        .setName(name)
                                                        .setINN(INN)
                                                        .setCPP(CPP)
                                                        .setAddress(address)
                                                        .setNumberPhone(numberPhone)
                                                        .setPaymentAccount(paymentAccount)
                                                        .setBank(bank)
                                                        .setBIK(BIK)
                                                        .setCorrespondentAccount(correspondentAccount)
                                                        .build();

            providers[i] = provider;
        }

        accountingSystem.setProviders(providers);
    }

    private void dataTransmissionClient(){
        Client[] clients = new Client[linesClient.size()];

        for(int i = 0; i < linesClient.size(); i++){
            String[] readable = getReadableData(linesClient.get(i));

            int codeClient = Integer.parseInt(readable[0]);
            String secondName = readable[1];
            String name = readable[2];
            String middleName = readable[3];
            String numberPhone = readable[4];
            String email = readable[5];
            String address = readable[6];

            Client client = Client.newBuilder().setCodeClient(codeClient)
                                                .setSecondName(secondName)
                                                .setName(name)
                                                .setMiddleName(middleName)
                                                .setNumberPhone(numberPhone)
                                                .setEmail(email)
                                                .setAddress(address).build();

            clients[i] = client;
        }

        accountingSystem.setClients(clients);
    }

//    private void dataTransmissionSale(){
//        Sale[] sales = new Sale[linesSale.size()];
//
//        for(int i = 0; i < linesSale.size(); i++){
//            String[] readable = getReadableData(linesSale.get(i));
//
//            int codeSale = Integer.parseInt(readable[0]);
//            int codeClient = Integer.parseInt(readable[1]);
//            int codeWorker = Integer.parseInt(readable[2]);
//            String date = readable[3];
//
//            Sale sale = Sale.newBuilder().setCodeSale(codeSale)
//                                                            .setCodeClient(codeClient)
//                                                            .setCodeWorker(codeWorker)
//                                                            .setDate(date).build();
//            sales[i] = sale;
//        }
//
//        accountingSystem.setSale(sales);
//    }

    private void dataTransmissionProperties(){
        for (String property : this.properties) {
            String[] element = getReadableDataProperties(property);

            String nameProperties = element[0];
            String value = element[1].substring(0, element[1].length() - 1);

            if ("password".equals(nameProperties)) {
                Properties.setPassword(value);
            }
            if ("phoneShop".equals(nameProperties)) {
                Properties.setPhoneShop(value);
            }
            if("address".equals(nameProperties)) {
                Properties.setAddress(value);
            }
            if("codeShop".equals(nameProperties)){
                Properties.setCodeShop(Integer.parseInt(value));
            }
        }
    }

    /**----------------------------------------------------------------------------------------------------------*/

    private  AccountingSystem accountingSystem;
    private static LoadData loadData;
    private LoadData() {}
    public static LoadData getInstance(){
        if(loadData == null){
            loadData = new LoadData();
            loadData.accountingSystem = AccountingSystem.getInstance();
        }

        return loadData;
    }

    private  final List<String> linesCellPhones = new ArrayList<>();
    private  final List<String> linesCamera = new ArrayList<>();
    private  final List<String> linesConfiguration = new ArrayList<>();
    private  final List<String> linesCountryOfManufacture = new ArrayList<>();
    private  final List<String> linesDisplay = new ArrayList<>();
    private  final List<String> linesMultimedia = new ArrayList<>();
    private  final List<String> linesWorker = new ArrayList<>();
    private  final List<String> linesProvider = new ArrayList<>();
    private  final List<String> linesClient = new ArrayList<>();
    private  final List<String> properties = new ArrayList<>();


    /* at the best of times */
//    public List<String> getLinesCellPhones() {
//        return linesCellPhones;
//    }
//
//    public List<String> getLinesCamera() {
//        return linesCamera;
//    }
//
//    public List<String> getLinesConfiguration() {
//        return linesConfiguration;
//    }
//
//    public List<String> getLinesCountryOfManufacture() {
//        return linesCountryOfManufacture;
//    }
//
//    public List<String> getLinesDisplay() {
//        return linesDisplay;
//    }
//
//    public List<String> getLinesMultimedia() {
//        return linesMultimedia;
//    }
//
//    public List<String> getLinesWorker() {
//        return linesWorker;
//    }
//
//    public List<String> getLinesDelivery() {
//        return linesDelivery;
//    }
//
//    public List<String> getLinesProvider() {
//        return linesProvider;
//    }
//
//    public List<String> getLinesStructureOfProvider() {
//        return linesStructureOfProvider;
//    }
//
//    public List<String> getProperties(){
//        return properties;
//    }
}
