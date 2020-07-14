package main.data_base.base;

import main.data_base.objects.sales.Sale;
import main.data_base.objects.sales.client.Client;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.delivery.Provider;
import main.data_base.objects.products_and_components.components.*;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.workers_and_data.workers.Worker;


public class AccountingSystem {
    private static AccountingSystem accountingSystem;
    private AccountingSystem(){}
    public static AccountingSystem getInstance(){
        if(accountingSystem == null){
            accountingSystem = new AccountingSystem();
        }
        return accountingSystem;
    }

    /** adding new objects in cash-arrays */
    public void addingNewWorker(Worker worker){
        workers = increaseSizeWorker(workers);
        int lastId = workers.length-1;
        workers[lastId] = worker;
    }

    public void addingNewCellPhone(CellPhone cellPhone){
        cellPhones = increaseSizeCellPhone(cellPhones);
        int lastId = cellPhones.length-1;
        cellPhones[lastId] = cellPhone;
    }

    public void addingNewDelivery(Delivery delivery){
        deliveries = UtilMethods.increaseSizeDelivery(deliveries);
        int lastId = deliveries.length-1;
        deliveries[lastId] = delivery;
    }

    public void addingNewProvider(Provider provider){
        providers = increaseSizeProvider(providers);
        int lastId = providers.length-1;
        providers[lastId] = provider;
    }

    public void addingNewClient(Client client){
        clients = increaseSizeClient(clients);
        int lastId = clients.length-1;
        clients[lastId] = client;
    }

    public void addingNewSales(Sale sale){
        sales = UtilMethods.increaseSizeSales(sales);
        int lastId = sales.length-1;
        sales[lastId] = sale;
    }

    /*at the best of times*/
//    public void addingNewCamera(Camera camera){
//        cameras = increaseSizeCamera(cameras);
//        int lastId = cameras.length-1;
//        cameras[lastId] = camera;
//    }
//
//    public void addingNewConfiguration(Configuration configuration){
//        configurations = increaseSizeConfiguration(configurations);
//        int lastId = configurations.length-1;
//        configurations[lastId] = configuration;
//    }
//
//    public void addingNewCountryOfManufacture(CountryOfManufacture country){
//        countryOfManufactures = increaseSizeCountryOfManufacture(countryOfManufactures);
//        int lastId = countryOfManufactures.length-1;
//        countryOfManufactures[lastId] = country;
//    }
//
//    public void addingNewDisplay(Display display){
//        displays = increaseSizeDisplay(displays);
//        int lastId = displays.length-1;
//        displays[lastId] = display;
//    }
//
//    public void addingNewMultimedia(Multimedia mult){
//        multimedia = increaseSizeMultimedia(multimedia);
//        int lastId = multimedia.length-1;
//        multimedia[lastId] = mult;
//    }
    /** removing chose row in table and delete in cash-arrays */

    public void removingDelivery(Delivery remover){
        Delivery[] newDelivery = decreaseSizeDelivery();

        int decrease = 0;

        for(int i = 0; i < deliveries.length; i++){
            if(remover.getNumber() != deliveries[i].getNumber()){
                newDelivery[i - decrease] = deliveries[i];
            }else{
                decrease+=1;
            }
        }

        deliveries = newDelivery;
    }

    public void removingCellPhone(CellPhone remover){
        CellPhone[] newCellPhones = decreaseSizeCellPhone();

        int decrease = 0;

        for(int i = 0; i < cellPhones.length; i++){
            if(remover.getArticleNumber() != cellPhones[i].getArticleNumber()){
                newCellPhones[i - decrease] = cellPhones[i];
            }else{
                decrease+=1;
            }
        }

        cellPhones = newCellPhones;
    }

    public void removingWorker(Worker remover){
        Worker[] newWorkers = decreaseSizeWorker();

        int decrease = 0;

        for(int i = 0; i < workers.length; i++){
            if(remover.getServiceNumber() != workers[i].getServiceNumber()){
                newWorkers[i - decrease] = workers[i];
            }else{
                decrease+=1;
            }
        }

        workers = newWorkers;
    }

    public void removingProvider(Provider remover){
        Provider[] newProviders = decreaseSizeProvider();

        int decrease = 0;

        for(int i = 0; i < providers.length; i++){
            if(remover.getCode() != providers[i].getCode()){
                newProviders[i - decrease] = providers[i];
            }else{
                decrease+=1;
            }
        }

        providers = newProviders;
    }

    public void removingClient(Client remover){
        Client[] newClients = decreaseSizeClient();

        int decrease = 0;

        for(int i = 0; i < clients.length; i++){
            if(remover.getCodeClient() != clients[i].getCodeClient()){
                newClients[i - decrease] = clients[i];
            }else{
                decrease += 1;
            }
        }

        clients = newClients;
    }

    public void removingSales(Sale remover){
        Sale[] newSales = decreaseSizeSale();

        int decrease = 0;

        for(int i = 0; i < sales.length; i++){
            if(remover.getCodeSale() != sales[i].getCodeSale()){
                newSales[i - decrease] = sales[i];
            }else {
                decrease += 1;
            }
        }

        sales = newSales;
    }

    /** increase size everyone array */
    //delivery
//    private Delivery[] increaseSizeDelivery(Delivery[] array){
//        Delivery[] newArray = new Delivery[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }

    private Provider[] increaseSizeProvider(Provider[] array){
        Provider[] newArray = new Provider[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    //workers
    private Worker[] increaseSizeWorker(Worker[] array){
        Worker[] newArray = new Worker[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    //products
    private CellPhone[] increaseSizeCellPhone(CellPhone[] array){
        CellPhone[] newArray = new CellPhone[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    //client
    private Client[] increaseSizeClient(Client[] array){
        Client[] newArray = new Client[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

//    //sale
//    private Sale[] increaseSizeSales(Sale[] array){
//        Sale[] newArray = new Sale[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }

    /*at the best of times*/
//    //components
//    private Camera[] increaseSizeCamera(Camera[] array){
//        Camera[] newArray = new Camera[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }
//
//    private Configuration[] increaseSizeConfiguration(Configuration[] array){
//        Configuration[] newArray = new Configuration[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }
//
//    private CountryOfManufacture[] increaseSizeCountryOfManufacture(CountryOfManufacture[] array){
//        CountryOfManufacture[] newArray = new CountryOfManufacture[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }
//
//    private Display[] increaseSizeDisplay(Display[] array){
//        Display[] newArray = new Display[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }
//
//    private Multimedia[] increaseSizeMultimedia(Multimedia[] array){
//        Multimedia[] newArray = new Multimedia[array.length + 1];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//
//        return newArray;
//    }

    /** decrease size everyone array */

    private Delivery[] decreaseSizeDelivery(){
        return new Delivery[deliveries.length-1];
    }

    private Provider[] decreaseSizeProvider(){
        return new Provider[providers.length-1];
    }

    private Worker[] decreaseSizeWorker(){
        return new Worker[workers.length-1];
    }

    private CellPhone[] decreaseSizeCellPhone(){
        return new CellPhone[cellPhones.length-1];
    }

    private Client[] decreaseSizeClient(){
        return new Client[clients.length-1];
    }

    private Sale[] decreaseSizeSale(){
        return new Sale[sales.length-1];
    }

    //at the best of time
//    private Camera[] decreaseSizeCamera(){
//        return new Camera[cameras.length-1];
//    }
//
//    private Configuration[] decreaseSizeConfiguration(){
//        return new Configuration[configurations.length-1];
//    }
//
//    private CountryOfManufacture[] decreaseSizeCountryOfManufacture(){
//        return new CountryOfManufacture[countryOfManufactures.length-1];
//    }
//
//    private Display[] decreaseSizeDisplay(){
//        return new Display[displays.length-1];
//    }
//
//    private Multimedia[] decreaseSizeMultimedia(){
//        return new Multimedia[multimedia.length-1];
//    }

    /** arrays cash */
    //delivery
    private Delivery[] deliveries;
    private Provider[] providers;
    //workers
    private Worker[] workers;
    //components
    private Camera[] cameras;
    private Configuration[] configurations;
    private CountryOfManufacture[] countryOfManufactures;
    private Display[] displays;
    private Multimedia[] multimedia;
    //products
    private CellPhone[] cellPhones;
    //clients
    private Client[] clients;
    //sales
    private Sale[] sales;

    /** PrimaryKeys tables */

    private int[] keysDeliveries;
    private int[] keysProviders;
    private int[] keysCellPhones;
    private int[] keysWorkers;
    private int[] keysClient;

    /* at the best of times */
//    private int[] keysOfDeliveries;
//    private int[] keysCameras;
//    private int[] keysConfigurations;
//    private int[] keysCountry;
//    private int[] keysDisplays;
//    private int[] keysMultimedia;
//    private int[] keyOfSales;

    /** specific methods */
    public int getIdDelivery(int code){
        int id = -1;
        for(int i = 0; i < deliveries.length; i++){
            if(code == deliveries[i].getNumber()){
                id = i;
                i = deliveries.length;
            }
        }
        return id;
    }
    public Delivery getDelivery(int code){
        int id = -1;
        for(int i = 0; i < deliveries.length; i++){
            if(code == deliveries[i].getNumber()){
                id = i;
                i = deliveries.length;
            }
        }
        return deliveries[id];
    }

    public Provider getProvider(int code){
        int id = -1;
        for(int i = 0; i < providers.length; i++){
            if(code == providers[i].getCode()){
                id = i;
                i = providers.length;
            }
        }
        return providers[id];
    }
    public int getIdProvider(int code){
        int id = -1;
        for(int i = 0; i < providers.length; i++){
            if(code == providers[i].getCode()){
                id = i;
                i = providers.length;
            }
        }
        return id;
    }

    public Worker getWorker(int code){
        int id = -1;

        for(int i = 0; i < workers.length; i++){
            if(code == workers[i].getServiceNumber()){
                id = i;
                i = workers.length;
            }
        }
        return workers[id];
    }
    public int getIdWorker(int code){
        int id = -1;
        for(int i = 0; i < workers.length; i++){
            if(code == workers[i].getServiceNumber()){
                id = i;
                i = workers.length;
            }
        }
        return id;
    }

    public CellPhone getCellPhone(int code){
        int id = -1;
        for(int i = 0; i < cellPhones.length; i++) {
            if (code == cellPhones[i].getArticleNumber()) {
                id = i;
                i = cellPhones.length;
            }
        }
        return cellPhones[id];
    }
    public int getIdCellPhone(int code){
        int id = -1;
        for(int i = 0; i < cellPhones.length; i++){
            if(code == cellPhones[i].getArticleNumber()){
                id = i;
                i = cellPhones.length;
            }
        }
        return id;
    }

    public CountryOfManufacture getCountry(int code){
        int id = -1;
        for(int i = 0; i < countryOfManufactures.length; i++){
            if(code == countryOfManufactures[i].getCode()){
                id = i;
                i = countryOfManufactures.length;
            }
        }
        return countryOfManufactures[id];
    }

    public Multimedia getMultimedia(int code){
        int id = -1;
        for(int i = 0; i < multimedia.length; i++){
            if(code == multimedia[i].getCode()){
                id = i;
                i = multimedia.length;
            }
        }
        return multimedia[id];
    }

    public Camera getCamera(int code){
        int id = -1;
        for(int i = 0; i < cameras.length; i++){
            if(code == cameras[i].getCode()){
                id = i;
                i = cameras.length;
            }
        }
        return cameras[id];
    }

    public Configuration getConfiguration(int code){
        int id = -1;
        for(int i = 0; i < configurations.length; i++){
            if(code == configurations[i].getCode()){
                id = i;
                i = configurations.length;
            }
        }
        return configurations[id];
    }

    public Display getDisplay(int code){
        int id = -1;
        for(int i = 0; i < displays.length; i++){
            if(code == displays[i].getCode()){
                id = i;
                i = displays.length;
            }
        }
        return displays[id];
    }

    public Sale getSale(int codeSale){
        int id = -1;
        for(int i = 0; i < sales.length; i++){
            if(codeSale == sales[i].getCodeSale()){
                id = i;
                i = sales.length;
            }
        }

        return sales[id];
    }

    public int getIdSale(int code){
        int id = -1;
        for(int i = 0; i < sales.length; i++){
            if(code == sales[i].getCodeSale()){
                id = i;
                i = sales.length;
            }
        }

        return id;
    }

    public int getIdClient(int code){
        int id = -1;
        for(int i = 0; i < clients.length; i++){
            if(code == clients[i].getCodeClient()){
                id = i;
                i = clients.length;
            }
        }
        return id;
    }
    public Client getClient(int code){
        int id = -1;
        for(int i = 0; i < clients.length; i++){
            if(code == clients[i].getCodeClient()){
                id = i;
                i = clients.length;
            }
        }
        return clients[id];
    }

    /** getters */
    public Delivery[] getDeliveries() {
        return deliveries;
    }

    public Provider[] getProviders() {
        return providers;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public Camera[] getCameras() {
        return cameras;
    }

    public Configuration[] getConfigurations() {
        return configurations;
    }

    public CountryOfManufacture[] getCountryOfManufactures() {
        return countryOfManufactures;
    }

    public Display[] getDisplays() {
        return displays;
    }

    public Multimedia[] getMultimedia() {
        return multimedia;
    }

    public CellPhone[] getCellPhones() {
        return cellPhones;
    }

    public Client[] getClients(){
        return clients;
    }

    public Sale[] getSale(){
        return sales;
    }

    /** setters */
    public void setDeliveries(Delivery[] deliveries) {
        this.deliveries = deliveries;
//        setUpPK_Deliveries();
    }

    public void setProviders(Provider[] providers) {
        this.providers = providers;
//        setUpPK_Providers();
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
//        setUpPK_Workers();
    }

    public void setCameras(Camera[] cameras) {
        this.cameras = cameras;
    }

    public void setConfigurations(Configuration[] configurations) {
        this.configurations = configurations;
    }

    public void setCountryOfManufactures(CountryOfManufacture[] countryOfManufactures) {
        this.countryOfManufactures = countryOfManufactures;
    }

    public void setDisplays(Display[] displays) {
        this.displays = displays;
    }

    public void setMultimedia(Multimedia[] multimedia) {
        this.multimedia = multimedia;
    }

    public void setCellPhones(CellPhone[] cellPhones) {
        this.cellPhones = cellPhones;
//        setUpPK_CellPhones();
    }

    public void setClients(Client[] clients){
        this.clients = clients;
//        setUpPK_Client();
    }

    public void setSale(Sale[] sales){
        this.sales = sales;
    }

    /** set up PrimaryKeys in arrays */
    private void setUpPK_Deliveries(){
        keysDeliveries = new int[deliveries.length];
        for(int i = 0; i < deliveries.length; i++){
            keysDeliveries[i] = deliveries[i].getNumber();
        }
    }

    private void setUpPK_Providers(){
        keysProviders = new int[providers.length];
        for(int i = 0; i < providers.length; i++){
            keysProviders[i] = providers[i].getCode();
        }
    }

    private void setUpPK_Workers(){
        keysWorkers = new int[workers.length];
        for(int i = 0; i < workers.length; i++){
            keysWorkers[i] = workers[i].getServiceNumber();
        }
    }

    private void setUpPK_CellPhones(){
        keysCellPhones = new int[cellPhones.length];
        for(int i = 0; i < cellPhones.length; i++){
            keysCellPhones[i] = cellPhones[i].getArticleNumber();
        }
    }

    private void setUpPK_Client(){
        keysClient = new int[clients.length];
        for(int i = 0; i < clients.length; i++){
            keysClient[i] = clients[i].getCodeClient();
        }
    }
}
