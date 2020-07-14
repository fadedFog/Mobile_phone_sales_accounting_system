package main.data_base.base.load_and_save.save_data;

import main.application.Properties;
import main.data_base.base.AccountingSystem;
import main.data_base.base.load_and_save.StorageDataSpecificFiles;
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
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeClassE;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeNameCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeOS;
import main.data_base.objects.workers_and_data.data.Position;
import main.data_base.objects.workers_and_data.workers.Worker;

import java.io.*;

public class SaveData {
    public SaveData(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    private void writeInFile(String[] readyData, String nameFile) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile))){
            for(int i = 0; i < readyData.length; i++){
                writer.write(readyData[i]);

                if(i != readyData.length-1) writer.write("\r\n");
            }

        }catch (IOException e){
            System.out.println("FileNotFounded!");
        }
    }

    private String makeReadyLine(String ... oldData){
        StringBuilder readyData = new StringBuilder();

        for(int i = 0; i < oldData.length; i++) {
            readyData.append(oldData[i]);
            if(i != oldData.length-1) {
                readyData.append("&");
            }else{
                readyData.append("!");
            }
        }

        return readyData.toString();
    }

    /*at the best of times*/
//    private String getNormalDate(Calendar calendar){
//        String day = makeZeroDigit(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
//        String month = makeZeroDigit(String.valueOf(calendar.get(Calendar.MONTH)+1));
//        String year = String.valueOf(calendar.get(Calendar.YEAR));
//        return day + "." + month + "." + year;
//    }

//    private String makeZeroDigit(String digit){
//        if(digit.length() == 1){
//            digit = "0" + digit;
//        }
//        return digit;
//    }

    public void saveDelivery(){
        clearOldDataFiles(StorageFileNames.nameDelivery);

        Delivery[] deliveries = accountingSystem.getDeliveries();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(StorageFileNames.nameDelivery, true))){

            if(deliveries.length != 0) {
                    oos.writeObject(deliveries);
            }else{
//                System.out.println("Input empty place");
                oos.writeBytes("");
            }

        }catch (IOException e){
            System.err.println("Cant save array deliveries");
        }

    } //+

    public void saveProvider(){
        int length = accountingSystem.getProviders().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Provider provider = accountingSystem.getProviders()[i];

            String number = String.valueOf(provider.getCode());
            String name = provider.getName();
            String INN = provider.getINN();
            String CPP = provider.getCPP();
            String address = provider.getAddress();
            String numberPhone = provider.getNumberPhone();
            String paymentAccount = provider.getPaymentAccount();
            String bank = provider.getBank();
            String BIK = provider.getBIK();
            String correspondentAccount = provider.getCorrespondentAccount();

            readyData[i] = makeReadyLine(number, name, INN, CPP, address, numberPhone,
                                        paymentAccount, bank, BIK, correspondentAccount);

        }

        writeInFile(readyData, StorageFileNames.nameProvider);
    } //+

    public void saveCamera(){
        int length = accountingSystem.getCameras().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Camera camera = accountingSystem.getCameras()[i];

            String code = String.valueOf(camera.getCode());
            String mainCamera = camera.getMainCamera();
            String frontCamera = camera.getFrontCamera();
            String recordVideo = TypeRecordVideo.getOrigRecordVideo(camera.getRecordVideo());
            String typeSpark = TypeSpark.getOrigSpark(camera.getTypeSpark());

            readyData[i] = makeReadyLine(code, mainCamera, frontCamera, recordVideo, typeSpark);
        }

        writeInFile(readyData, StorageFileNames.nameCamera);
    } //+

    public void saveConfiguration(){
        int length = accountingSystem.getConfigurations().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Configuration config = accountingSystem.getConfigurations()[i];

            String code = String.valueOf(config.getCode());
            String processor = TypeProcessor.getOrigProcessor(config.getProcessor());
            String sizeRAM = TypeRAM.getOrigRAM(config.getSizeRAM());
            String sizeInMemory = TypeInMemory.getOrigInMemory(config.getSizeInMemory());
            String maxSizeStorageMemory = TypeMaxSizeSM.getOrigMaxSizeSM(config.getMaxSizeStorageMemory());

            readyData[i] = makeReadyLine(code, processor, sizeRAM, sizeInMemory, maxSizeStorageMemory);
        }

        writeInFile(readyData, StorageFileNames.nameConfiguration);
    } //+

    public void saveCountryOfManufacture(){
        int length = accountingSystem.getCountryOfManufactures().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            CountryOfManufacture countryOfManufacture = accountingSystem.getCountryOfManufactures()[i];

            String code = String.valueOf(countryOfManufacture.getCode());
            String country = Country.getOrigCountry(countryOfManufacture.getCountry());

            readyData[i] = makeReadyLine(code, country);
        }

        writeInFile(readyData, StorageFileNames.nameCountryOfManufacture);
    } //+

    public void saveDisplay(){
        int length = accountingSystem.getDisplays().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Display display = accountingSystem.getDisplays()[i];

            String code = String.valueOf(display.getCode());
            String displayDiagonal = TypeDiagonal.getOrigDiagonal(display.getDisplayDiagonal());
            String displayResolution = TypeDisplayResolution.getOrigResolution(display.getDisplayResolution());
            String numberPPI = String.valueOf(display.getNumberPPI());
            String touchScreen = getTypeTouchScreen(display.getTouchScreen());

            readyData[i] = makeReadyLine(code, displayDiagonal, displayResolution, numberPPI, touchScreen);
        }

        writeInFile(readyData, StorageFileNames.nameDisplay);
    } //+

    private String getTypeTouchScreen(String typesScreen){
        StringBuilder result = new StringBuilder();
        String[] types = typesScreen.split(", ");

        for(int i = 0; i < types.length; i++){
            result.append(TypeTouchScreen.getOrigProtect(types[i]));
            if(i != types.length-1){
                result.append(";");
            }
        }

        return result.toString();
    }

    public void saveMultimedia(){
        int length = accountingSystem.getMultimedia().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Multimedia multimedia = accountingSystem.getMultimedia()[i];

            String code = String.valueOf(multimedia.getCode());
            String connectorHeadPhone = TypeConnectorHeadPh.getOrigModel(multimedia.getConnectorHeadPhone());
            String interfaceUSB = TypeInterfaceUSB.getOrigUSB(multimedia.getInterfaceUSB());
            String suppVideoFormat = TypeSuppVideoFormat.getOrigVFormat(multimedia.getSuppVideoFormat());

            readyData[i] = makeReadyLine(code, connectorHeadPhone, interfaceUSB, suppVideoFormat);
        }

        writeInFile(readyData, StorageFileNames.nameMultimedia);
    } //+

    public void saveCellPhone(){
        int length = accountingSystem.getCellPhones().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            CellPhone cellPhone = accountingSystem.getCellPhones()[i];

            String articleNumber = String.valueOf(cellPhone.getArticleNumber());
            String name = cellPhone.getName();
            String price = String.valueOf(cellPhone.getPrice());
            String classE = TypeClassE.getOrigClassE(cellPhone.getClassE());
            String typeCorpus = getTypeCorpus(cellPhone.getTypeCorpus());
            String numberSIM = String.valueOf(cellPhone.getNumberSIM());
            String OS = TypeOS.getOrigOS(cellPhone.getOS());
            String display = String.valueOf(cellPhone.getDisplay());
            String configuration = String.valueOf(cellPhone.getConfiguration());
            String camera = String.valueOf(cellPhone.getCamera());
            String multimedia = String.valueOf(cellPhone.getMultimedia());
            String corpus = TypeNameCorpus.getOrigNameCorpus(cellPhone.getCorpus());
            String country = String.valueOf(cellPhone.getCountry());

            String shortName = cellPhone.getShortName();

            readyData[i] = makeReadyLine(articleNumber, name, price, classE, typeCorpus, numberSIM, OS, display,
                                        configuration, camera, multimedia, corpus, country, shortName);
        }

        writeInFile(readyData, StorageFileNames.nameCellPhone);
    } //+

    private String getTypeCorpus(String typesCorpus){
        StringBuilder result = new StringBuilder();
        String[] types = typesCorpus.split(", ");

        for(int i = 0; i < types.length; i++){
            result.append(TypeCorpus.getOrigCorpus(types[i]));
            if(i != types.length-1){
                result.append(";");
            }
        }

        return result.toString();
    }

    public void saveWorker(){
        int length = accountingSystem.getWorkers().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Worker worker = accountingSystem.getWorkers()[i];

            String serviceNumber = String.valueOf(worker.getServiceNumber());
            String secondName = worker.getSecondName();
            String name = worker.getName();
            String middleName = worker.getMiddleName();
            String position = Position.getOrigPost(worker.getPosition());
            String numberPhone = worker.getNumberPhone();

            readyData[i] = makeReadyLine(serviceNumber, secondName, name, middleName, position, numberPhone);
        }

        writeInFile(readyData, StorageFileNames.nameWorker);
    } //+

    public void saveClient(){
        int length = accountingSystem.getClients().length;
        String[] readyData = new String[length];

        for(int i = 0; i < length; i++){
            Client client = accountingSystem.getClients()[i];

            String codeClient = String.valueOf(client.getCodeClient());
            String secondName = client.getSecondName();
            String name = client.getName();
            String middleName = client.getMiddleName();
            String numberPhone = client.getNumberPhone();
            String email = client.getEmail();
            String address = client.getAddress();

            readyData[i] = makeReadyLine(codeClient, secondName, name, middleName, numberPhone, email, address);
        }

        writeInFile(readyData, StorageFileNames.nameClient);
    } //+

    public void saveSales(){
        clearOldDataFiles(StorageFileNames.nameSale);

        Sale[] sales = accountingSystem.getSale();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(StorageFileNames.nameSale, true))){

            if(sales.length != 0){
                oos.writeObject(sales);
            }else{
                oos.writeBytes("");
            }

        }catch (IOException e){
            System.err.println("Cant save array sales");
        }
    } //+

    public void saveWarehouse(){
        clearOldDataFiles(StorageFileNames.nameWarehouse);

//        Warehouse.getInstance().iterationMap();

        Warehouse[] warehouses = {Warehouse.getInstance()};

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(StorageFileNames.nameWarehouse, true))){

            oos.writeObject(warehouses);

        }catch (IOException e){
            System.out.println("Cant save data warehouse");
//            e.printStackTrace();
        }
    }

    public void saveAllTables(){
        saveDelivery();
        saveProvider();
        saveCamera();
        saveConfiguration();
        saveCountryOfManufacture();
        saveDisplay();
        saveMultimedia();
        saveCellPhone();
        saveWorker();
        saveClient();
        saveSales();
        saveWarehouse();
    }

    public void saveProperties(){
        String password = Properties.getPassword();
        System.out.println("Password: " + password);
        if(password.isEmpty()){
            password = "";
        }


        String readPassword = "password:" + password +"!\n";
        System.out.println("readPassword: " + readPassword);


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(StorageFileNames.properties))){
            writer.write(readPassword);
            writer.write(StorageDataSpecificFiles.getInstance().dataProperties);
        }catch (IOException e){
            System.out.println("Cant save new password");
        }
    } //+

    private void clearOldDataFiles(String nameFile){
        try (FileWriter writer = new FileWriter(nameFile)) {
            writer.write("");

        }catch (IOException e){
            System.out.println("Cant clear file nameFile:{" + nameFile + "}");
        }
    }

    private final AccountingSystem accountingSystem;
}











