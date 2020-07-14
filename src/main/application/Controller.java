package main.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.application.work_documents.cheque.ChequeAndDocumentInfoMaker;
import main.application.work_documents.reports.ReportsMaker;
import main.data_base.base.AccountingSystem;
import main.data_base.base.load_and_save.StorageFileNames;
import main.data_base.base.load_and_save.load_datas.LoadData;
import main.data_base.base.load_and_save.save_data.SaveData;
import main.data_base.objects.products_and_components.warehouse.Warehouse;
import main.data_base.objects.sales.Sale;
import main.data_base.objects.sales.StructureSales;
import main.data_base.objects.sales.client.Client;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.delivery.Provider;
import main.data_base.objects.delivery.StructureOfDelivery;
import main.data_base.objects.products_and_components.components.*;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeClassE;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeNameCorpus;
import main.data_base.objects.products_and_components.products.types_elemnts_products.TypeOS;
import main.data_base.objects.workers_and_data.data.Position;
import main.data_base.objects.workers_and_data.workers.Worker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Properties.isInSystem && !Properties.systemWasLoad) {
            Properties.systemWasLoad = true;
            LoadData.getInstance().loadAllTables();

            initTableDelivery();
            Properties.deliveries = tableDelivery;
            initTableCellPhone();
            Properties.cellPhones = tableProducts;
            initTableWorker();
            Properties.workers = tableWorkers;
            initTableProvider();
            Properties.providers = tableProviders;
            initTableClient();
            Properties.clients = tableClients;
            initTableSale();
            Properties.sales = tableSales;
        }

        if(Properties.isAddingNewDelivery){
            initListChoiceBoxesDelivery();
            initChoiceBoxAddDelivery();

            initTableOfDelivery();
            Properties.ofDelivery = tableOfDeliveries;
        }

        if(Properties.isAddingNewOfDelivery){
            initListChoiceBoxesOfDelivery();
            initChoiceBoxAddOfDelivery();
        }

        if(Properties.isAddingNewCellPhone){
            initListChoiceBoxesCellPhone();
            initChoiceBoxAddCellPhone();
        }

        if(Properties.isAddingNewWorker){
            initListChoiceBoxesWorker();
            initChoiceBoxAddWorker();
        }

        if(Properties.isAddingNewSale){
            initListChoiceBoxesSales();
            initChoiceBoxAddSale();

            initTableOfSale();
            Properties.ofSales = tableOfSale;
        }

        if(Properties.isAddingNewOfSale){
            initListChoiceBoxesOfSale();
            initChoiceBoxAddOfSale();
        }

        if(Properties.isCheckWorker){
            initFieldCheckingWorker();
        }

        if(Properties.isCheckProvider){
            initFieldCheckingProvider();
        }

        if(Properties.isCheckDelivery){
            initFieldCheckingDelivery();
            initTableCheckOfDelivery();
        }

        if(Properties.isCheckOfDelivery){
            initTextFieldsCheckOfDelivery();
        }

        if(Properties.isCheckCellPhone){
            initFieldCheckingCellPhone();
        }

        if(Properties.isCheckClient){
            initFieldCheckingClient();
        }

        if(Properties.isCheckSale){
            initFieldCheckingSale();
            initTableCheckOfSale();
        }

        if(Properties.isCheckOfSale){
            initTextFieldsCheckOfSale();
        }

        if(Properties.isTablePartDelivery){
            initTablePartDelivery();
        }

        if(Properties.isTablePartSellProduct){
            initTablePartSale();
        }

        if(Properties.isNotFountElement){
            messageNotFoundElement.setText(Properties.messageNotFountElement);
        }

    }

    /** Initialize tables and their columns */
    private void initTableDelivery(){
        initColumnsTableDelivery();

        tableDelivery.setItems(getDeliveries());
    }
    private void initColumnsTableDelivery(){
        numberDelivery.setCellValueFactory(new PropertyValueFactory<>("number"));
        dateDelivery.setCellValueFactory(new PropertyValueFactory<>("date"));
        providerDelivery.setCellValueFactory(new PropertyValueFactory<>("providerCode"));
        workerDelivery.setCellValueFactory(new PropertyValueFactory<>("serviceNumberWorker"));
        amountDelivery.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableDelivery.getSortOrder().setAll(dateDelivery);
    }
    private ObservableList<Delivery> getDeliveries(){
        ObservableList<Delivery> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getDeliveries()));

        return list;
    }

    private void initTableOfDelivery(){
        initColumnsTableOfDelivery();

        tableOfDeliveries.setItems(getOfDeliveries());
    }
    private void initColumnsTableOfDelivery(){
        columnProductOfDelivery.setCellValueFactory(new PropertyValueFactory<>("shortNameProduct"));
        columnQuantityOfDelivery.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    private ObservableList<StructureOfDelivery> getOfDeliveries(){
        return FXCollections.observableArrayList();
    }

    private void initTableCellPhone(){
        initColumnsTableCellPhone();

        tableProducts.setItems(getCellPhones());
    }
    private void initColumnsTableCellPhone(){
        articleProduct.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        nameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
        classProduct.setCellValueFactory(new PropertyValueFactory<>("classE"));
        typeCorpusProduct.setCellValueFactory(new PropertyValueFactory<>("typeCorpus"));
        simProduct.setCellValueFactory(new PropertyValueFactory<>("numberSIM"));
        osProduct.setCellValueFactory(new PropertyValueFactory<>("OS"));
        displayProduct.setCellValueFactory(new PropertyValueFactory<>("display"));
        configProduct.setCellValueFactory(new PropertyValueFactory<>("configuration"));
        cameraProduct.setCellValueFactory(new PropertyValueFactory<>("camera"));
        multyProduct.setCellValueFactory(new PropertyValueFactory<>("multimedia"));
        corpusProduct.setCellValueFactory(new PropertyValueFactory<>("corpus"));
        countryProduct.setCellValueFactory(new PropertyValueFactory<>("country"));
    }
    private ObservableList<CellPhone> getCellPhones(){
        ObservableList<CellPhone> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getCellPhones()));

        return list;
    }

    private void initTableWorker(){
        initColumnsTableWorker();

        tableWorkers.setItems(getWorkers());
    }
    private void initColumnsTableWorker(){
        numberWorker.setCellValueFactory(new PropertyValueFactory<>("serviceNumber"));
        sNameWorker.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        nameWorker.setCellValueFactory(new PropertyValueFactory<>("name"));
        mNameWorker.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        positionWorker.setCellValueFactory(new PropertyValueFactory<>("position"));
        numberPhoneWorker.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
    }
    private ObservableList<Worker> getWorkers(){
        ObservableList<Worker> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getWorkers()));

        return list;
    }

    private void initTableProvider(){
        initColumnsTableProvider();

        tableProviders.setItems(getProviders());
    }
    private void initColumnsTableProvider(){
        codeProvider.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameProvider.setCellValueFactory(new PropertyValueFactory<>("name"));
        innProvider.setCellValueFactory(new PropertyValueFactory<>("INN"));
        cppProvider.setCellValueFactory(new PropertyValueFactory<>("CPP"));
        addressProvider.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberPhoneProvider.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        paymentAccountProvider.setCellValueFactory(new PropertyValueFactory<>("paymentAccount"));
        bankProvider.setCellValueFactory(new PropertyValueFactory<>("bank"));
        bikProvider.setCellValueFactory(new PropertyValueFactory<>("BIK"));
        correspondentAccountProvider.setCellValueFactory(new PropertyValueFactory<>("correspondentAccount"));
    }
    private ObservableList<Provider> getProviders(){
        ObservableList<Provider> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getProviders()));

        return list;
    }

    private void initTableClient(){
        initColumnsTableClient();

        tableClients.setItems(getClients());
    }
    private void initColumnsTableClient(){
        codeClient.setCellValueFactory(new PropertyValueFactory<>("codeClient"));
        secondNameClient.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        nameClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        middleNameClient.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        numberPhoneClient.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        emailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressClient.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    private ObservableList<Client> getClients(){
        ObservableList<Client> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getClients()));

        return list;
    }

    private void initTableSale(){
        initColumnsTableSale();

        tableSales.setItems(getSales());
    }
    private void initColumnsTableSale(){
        codeSale.setCellValueFactory(new PropertyValueFactory<>("codeSale"));
        codeWorkerSale.setCellValueFactory(new PropertyValueFactory<>("codeWorker"));
        codeClientSale.setCellValueFactory(new PropertyValueFactory<>("codeClient"));
        dateSale.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private ObservableList<Sale> getSales() {
        ObservableList<Sale> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(accountingSystem.getSale()));

        return list;
    }

    private void initTableOfSale(){
        initColumnsTableOfSale();

        tableOfSale.setItems(getOfSales());
    }
    private void initColumnsTableOfSale(){
        columnProductOfSale.setCellValueFactory(new PropertyValueFactory<>("shortNameProduct"));
        columnQuantityOfSale.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnAmountOfSale.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    private ObservableList<StructureSales> getOfSales(){
        return FXCollections.observableArrayList();
    }

    /** frames for start program and end him */
    //on the bets times
//    @FXML
//    private void openSettingDataBase(ActionEvent event) throws IOException{
//        Parent settingDBParent = FXMLLoader.load(getClass().getResource("view_core/settingDataBase.fxml"));
//        Scene settingDBScene = new Scene(settingDBParent);
//
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setTitle("Настройка базы данных");
//        window.setScene(settingDBScene);
//        window.show();
//    }
    //    @FXML
//    private void connectionWithDatabase(ActionEvent event) throws IOException{
//        openEnterSystem(event);
//    }
    @FXML
    private void openEnterSystem(ActionEvent event) throws IOException{
        Parent enterSystemParent = FXMLLoader.load(getClass().getResource("view_core/enterSystem.fxml"));
        Scene enterSystemScene = new Scene(enterSystemParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Вход в систему");
        window.setScene(enterSystemScene);
        window.show();
    }

    @FXML
    private void openPasswordScreen(ActionEvent event) throws IOException{
        Parent passwordFrameParent = FXMLLoader.load(getClass().getResource("view_core/passwordFrame.fxml"));
        Scene passwordFrameScene = new Scene(passwordFrameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Вход в систему");
        window.setScene(passwordFrameScene);
        window.show();
    }
    @FXML
    private void changePassword(/*ActionEvent event*/) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/changePassword.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open changePassword");
        }
    }
    @FXML
    private void changeOldPassword(ActionEvent event){
        String oldPassword = textOldPassword.getText();
        String newPassword = textNewPassword.getText();
        String retryNewPassword = textRetryNewPassword.getText();

        if(Properties.getPassword().equals(oldPassword)){
            if(newPassword.equals(retryNewPassword)){
                if(!retryNewPassword.equals(oldPassword)){
                    Properties.setPassword(retryNewPassword);
                    saveData.saveProperties();
                    openAccessChangePassword();
                    closeAddingWindow(event);
                }else{
                    openWindowSameOldAndNewPassword();
                }
            }else{
                openWindowNonSameNewAndRetryPassword();
            }
        }else{
            openWindowNonCorrectPassword();
        }
    }
    private void openWindowNonCorrectPassword(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/nonCorrectPassword.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant openWindowNonCorrectPassword");
        }
    }
    private void openWindowNonSameNewAndRetryPassword(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/nonSameNewAndRetryPassword.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant openWindowNonSameNewAndRetryPassword");
        }
    }
    private void openWindowSameOldAndNewPassword(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/sameOldAndNewPassword.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant openWindowSameOldAndNewPassword");
        }
    }
    private void openAccessChangePassword(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/accessChangePassword.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant openAccessChangePassword");
        }
    }

    @FXML
    private void openAccountingSystem(ActionEvent event) throws IOException{
        if(!(passwordField.getText().equals(Properties.getPassword()))){
            openWindowNonCorrectPassword();
        }else{
            Properties.isInSystem = true;
            Parent accountingSystemParent = FXMLLoader.load(getClass().getResource("view_core/accountingSystem.fxml"));
            Scene accountingSystemScene = new Scene(accountingSystemParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Система учета продаж");
            window.setScene(accountingSystemScene);
            window.show();
        }

    }
    @FXML
    private void closeAccountingSystem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/closeSystem.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant closeAccountingSystem");
        }
    }
    @FXML
    private void closeWithSave(ActionEvent event) {
        saveData.saveAllTables();
        closeWithoutSave(event);
    }
    @FXML
    private void closeWithoutSave(ActionEvent event) {
        System.exit(1);
    }

    /** open frame "Добавить нового(-ую) ..."
     *---------DELIVERY----------------------------------*/
    private ObservableList<String> codeAddWorker;
    private ObservableList<String> codeAddProviders;

    private void initListChoiceBoxesDelivery(){
        codeAddWorker = FXCollections.observableArrayList();
        for(Worker worker: accountingSystem.getWorkers()){
            codeAddWorker.add(worker.getServiceNumber() + ". " + worker.getSecondName() + " " + worker.getName() + ". " + worker.getPosition());
        }

        codeAddProviders = FXCollections.observableArrayList();
        for(Provider provider: accountingSystem.getProviders()){
            codeAddProviders.add(provider.getCode() + ". " + provider.getName());
        }
    }
    private void initChoiceBoxAddDelivery(){
        providerAddDelivery.setItems(codeAddProviders);
        workerAddDelivery.setItems(codeAddWorker);
    }
    @FXML //from
    private void addNewDelivery(/*ActionEvent event*/) {
        Properties.isAddingNewDelivery = true;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingDelivery.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение новой поставки");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open addNewDelivery");
        }
    }

    @FXML //in
    private void addObjectDelivery(ActionEvent event) {
        String codeDelivery = textNumberDelivery.getText().trim();
        String dateDelivery = getNormalDate(dateAddDelivery);

        String codeWorker = getCodeWorkerDelivery();
        String codeProvider = getCodeProviderDelivery();
        String amount = getCodeAmountDelivery();

        StructureOfDelivery[] ofDeliveries;


        if(isAllLineNotEmpty(codeDelivery, dateDelivery, codeWorker, codeProvider, amount)
                && (isDigit(codeDelivery, false))
                    && isUniqueKeyDelivery(Integer.parseInt(codeDelivery)) && tableOfDeliveries.getItems().size() != 0) {
            Properties.isAddingNewDelivery = false;

            ObservableList<StructureOfDelivery> list = tableOfDeliveries.getItems();
            int countData = list.size();
            ofDeliveries = new StructureOfDelivery[countData];

            for(int i = 0; i < list.size(); i++){
                StructureOfDelivery tmp = list.get(i);

                StructureOfDelivery newOfDelivery =
                        StructureOfDelivery.newBuilder().
                                setArticleProduct(tmp.getArticleProduct())
                                .setQuantity(tmp.getQuantity())
                                .setNumberDelivery(Integer.parseInt(codeDelivery))
                                .setShortNameProduct(tmp.getShortNameProduct()).build();

                ofDeliveries[i] = newOfDelivery;
            }

            // warehouse increase quantity phones
            Warehouse.getInstance().addingCellPhones(ofDeliveries);

            Delivery newDelivery = Delivery.newBuilder().setNumber(Integer.parseInt(codeDelivery))
                                                        .setDate(dateDelivery)
                                                        .setProviderCode(Integer.parseInt(codeProvider))
                                                        .setServiceNumberWorker(Integer.parseInt(codeWorker))
                                                        .setAmount(Double.parseDouble(amount))
                                                        .setOfDeliveries(ofDeliveries).build();

            accountingSystem.addingNewDelivery(newDelivery);
            Properties.deliveries.getItems().add(newDelivery);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewDelivery = false;
            if(tableOfDeliveries.getItems().size() == 0){
                openMessageNotFillDate(/*event*/);
            }
            if(!isDigit(codeDelivery, false)){
                mistakeCodeDelivery();
                openMessageNotFillDate(/*event*/);
            }
            if(isDigit(codeDelivery, false)) {
                if (!codeDelivery.equals("") && !isUniqueKeyDelivery(Integer.parseInt(codeDelivery))) {
                    mistakeCodeDelivery();
                    openNeedUniqueKeys();
                }
            }

        }

    }

    private String getNormalDate(DatePicker date){
        if(date.getValue() == null){
            return "";
        }

        String dayOFMonth = makeZeroDigit(String.valueOf(date.getValue().getDayOfMonth()));
        String month = makeZeroDigit(String.valueOf(date.getValue().getMonth().getValue()));
        String year = String.valueOf(date.getValue().getYear());

        return dayOFMonth + "." + month + "." + year;
    }
    // need append logic

    private String getCodeWorkerDelivery(){
        if(workerAddDelivery.getValue() == null){
            return "";
        }

        return workerAddDelivery.getValue().split("\\.")[0];
    }
    private String getCodeProviderDelivery(){
        if(providerAddDelivery.getValue() == null){
            return "";
        }

        return providerAddDelivery.getValue().split("\\.")[0];
    }
    private String getCodeAmountDelivery(){
        if(amountAddDelivery.getText() == null){
            return "";
        }

        return amountAddDelivery.getText();
    }
    private String makeZeroDigit(String digit){
        if(digit.length() == 1){
            digit = "0" + digit;
        }
        return digit;
    }
    // need append logic
    public void countingAmountDelivery(/*MouseEvent mouseEvent*/) {
        ObservableList<StructureOfDelivery> list = tableOfDeliveries.getItems();
        double allAmount = 0;

        if(list.size() != 0){
            for(StructureOfDelivery ofDelivery: list){
                int quantity = ofDelivery.getQuantity();
                double price = accountingSystem.getCellPhone(ofDelivery.getArticleProduct()).getPrice();

                allAmount += (quantity * price);
            }
        }

        amountAddDelivery.setText(String.valueOf(allAmount));
    }
    private boolean isUniqueKeyDelivery(int key){
        for (Delivery delivery : accountingSystem.getDeliveries()) {
            if (key == delivery.getNumber()) {
                return false;
            }
        }

        return true;
    }

    /**---------STRUCTURE_OF_DELIVERY-----------------------*/
    private ObservableList<String> codeAddProduct;

    private void initListChoiceBoxesOfDelivery(){
        codeAddProduct = FXCollections.observableArrayList();
        for(CellPhone phone: accountingSystem.getCellPhones()){
            codeAddProduct.add(phone.getShortName() + ", " + phone.getPrice());
        }
    }
    private void initChoiceBoxAddOfDelivery(){
        numberAddProductOfDelivery.setItems(codeAddProduct);
    }

    @FXML //from
    private void addNewStructureDelivery(ActionEvent event) {
        Properties.isAddingNewOfDelivery = true;
        Properties.isAddingNewDelivery = false;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingDeliveryStructure.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавление нового состава поставки");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open addingDeliveryStructure");
        }
    }

    @FXML // in
    private void addingObjectOfDelivery(ActionEvent event) {
        String codeProduct = getCodeProductDelivery();
        String quantity = quantityAddOfDelivery.getText().trim();
        String shortName = numberAddProductOfDelivery.getValue().split(",")[0];

        if(isDigit(quantity, false)){
            StructureOfDelivery structureOfDelivery = StructureOfDelivery.newBuilder()
                    .setArticleProduct(Integer.parseInt(codeProduct))
                    .setQuantity(Integer.parseInt(quantity))
                    .setNumberDelivery(0)
                    .setShortNameProduct(shortName).build();

            Properties.ofDelivery.getItems().add(structureOfDelivery);

            closeAddingOfDelivery(event);
        }else{
            if(!isDigit(quantity, false)){
                mistakeCodeQuantity();
                openMessageNotFillDate(/*event*/);
            }
        }
    }
    private String getCodeProductDelivery(){
        if(numberAddProductOfDelivery.getValue() == null){
            return "";
        }

        return numberAddProductOfDelivery.getValue().split(":")[0];
    }

    /**---------CELLPHONE----------------------------------*/
    private ObservableList<String> codeClass;
    private ObservableList<String> codeTypeCorpus;
    private ObservableList<String> codeOS;
    private ObservableList<String> codeDisplay;
    private ObservableList<String> codeConfiguration;
    private ObservableList<String> codeCamera;
    private ObservableList<String> codeCorpus;
    private ObservableList<String> codeMultimedia;
    private ObservableList<String> codeCountry;

    private void initListChoiceBoxesCellPhone(){
        codeClass = FXCollections.observableArrayList();
        codeClass.add(TypeClassE.values()[0].getName());

        codeTypeCorpus = FXCollections.observableArrayList();
        for(TypeCorpus corpus: TypeCorpus.values()){
            codeTypeCorpus.add(corpus.getName());
        }
        int lastIDArray = TypeCorpus.values().length-1;
        for(int i = 0; i < lastIDArray; i++){
            codeTypeCorpus.add(TypeCorpus.values()[i].getName() + ", " + TypeCorpus.values()[lastIDArray].getName());
        }
        StringBuilder allTypesCorpus = new StringBuilder();
        for(int i = 0; i < lastIDArray+1; i++){
            allTypesCorpus.append(TypeCorpus.values()[i].getName());
            if(i != lastIDArray){
                allTypesCorpus.append(", ");
            }
        }
        codeTypeCorpus.add(allTypesCorpus.toString());

        codeOS = FXCollections.observableArrayList();
        for(TypeOS os: TypeOS.values()){
            codeOS.add(os.getName());
        }

        codeDisplay = FXCollections.observableArrayList();
        for(Display display: accountingSystem.getDisplays()){
            codeDisplay.add(display.getCode() + ". " + display.getDisplayDiagonal() + ". "
                    + display.getDisplayResolution() + ". " + display.getNumberPPI() + ". " + display.getTouchScreen());
        }

        codeConfiguration = FXCollections.observableArrayList();
        for(Configuration con: accountingSystem.getConfigurations()){
            codeConfiguration.add(con.getCode() + ". " + con.getProcessor() + ". "
                    + con.getSizeRAM() + ". " + con.getSizeInMemory() + ". " + con.getMaxSizeStorageMemory());
        }

        codeCamera = FXCollections.observableArrayList();
        for(Camera camera: accountingSystem.getCameras()){
            codeCamera.add(camera.getCode() + ". " + camera.getMainCamera() + ". "
                    + camera.getFrontCamera() + ". " + camera.getRecordVideo() + ". " + camera.getTypeSpark());
        }

        codeCorpus = FXCollections.observableArrayList();
        for(TypeNameCorpus nameCorpus: TypeNameCorpus.values()){
            codeCorpus.add(nameCorpus.getName());
        }

        codeMultimedia = FXCollections.observableArrayList();
        for(Multimedia multimedia: accountingSystem.getMultimedia()){
            codeMultimedia.add(multimedia.getCode() + ". " + multimedia.getConnectorHeadPhone() + ". "
                    + multimedia.getInterfaceUSB() + ". " + multimedia.getSuppVideoFormat());
        }

        codeCountry = FXCollections.observableArrayList();
        for(CountryOfManufacture country: accountingSystem.getCountryOfManufactures()){
            codeCountry.add(country.getCode() + ". " + country.getCountry());
        }
    }
    private void initChoiceBoxAddCellPhone(){
        classCellPhone.setItems(codeClass);
        typeCorpusCellPhone.setItems(codeTypeCorpus);
        osCellPhone.setItems(codeOS);
        displayCellPhone.setItems(codeDisplay);
        configurationCellPhone.setItems(codeConfiguration);
        cameraCellPhone.setItems(codeCamera);
        corpusNameCellPhone.setItems(codeCorpus);
        multimediaCellPhone.setItems(codeMultimedia);
        countryCellPhone.setItems(codeCountry);
    }
    @FXML //from
    private void addNewProduct(/*ActionEvent event*/) {
        Properties.isAddingNewCellPhone = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingCellPhone.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение нового товара");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open addNewProduct");
        }
    }

    @FXML //in
    private void addObjectCellPhone(ActionEvent event) {
        String article = articleCellPhone.getText().trim();
        String namePhone = nameCellPhone.getText().trim();
        String price = getPrice();
        String classE = getClassE();
        String typeCorpus = getTypeCorpus();
        String SIM = simCellPhone.getText().trim();
        String os = getOS();
        String display = getDisplay();
        String configuration = getConfiguration();
        String camera = getCamera();
        String corpus = getCorpus();
        String multimedia = getMultimedia();
        String country = getCountry();

        String shortName = article + ": " + namePhone;

        if(isAllLineNotEmpty(article, namePhone, price, classE, typeCorpus, SIM, os, display, configuration, camera,
                                corpus, multimedia, country) && isLengthValueCorrect(SIM, 1)
                && (isDigit(article, false) && isDigit(price, true) && isDigit(SIM, false))
                && !isContainsForbiddenChars(namePhone) && isUniqueKeyCellPhone(Integer.parseInt(article))){
            Properties.isAddingNewCellPhone = false;

            CellPhone newCellPhone = CellPhone.newBuilder().setArticleNumber(Integer.parseInt(article))
                                                            .setName(namePhone)
                                                            .setPrice(Double.parseDouble(price))
                                                            .setClassE(classE)
                                                            .setTypeCorpus(typeCorpus)
                                                            .setNumberSIM(Integer.parseInt(SIM))
                                                            .setOS(os)
                                                            .setDisplay(Integer.parseInt(display))
                                                            .setConfiguration(Integer.parseInt(configuration))
                                                            .setCamera(Integer.parseInt(camera))
                                                            .setCorpus(corpus)
                                                            .setMultimedia(Integer.parseInt(multimedia))
                                                            .setCountry(Integer.parseInt(country))
                                                            .setShortName(shortName).build();

            accountingSystem.addingNewCellPhone(newCellPhone);
            Properties.cellPhones.getItems().add(newCellPhone);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewCellPhone = false;
            if(isContainsForbiddenChars(namePhone)){
                openMessageForbiddenChars();
                mistakeName();
            }else {
                if(isDigit(article, false)
                        && !article.equals("") && !isUniqueKeyCellPhone(Integer.parseInt(article))) {
                    openNeedUniqueKeys();
                    mistakeArticle();
                }else {
                    if(!isDigit(article, false)){
                        mistakeArticle();
                    }
                    if(!isDigit(SIM, false)){
                        mistakeSIM();
                    }
                    if(!isDigit(price, true)){
                        mistakePriceCellPhone();
                    }
                    openMessageNotFillDate(/*event*/);
                }
            }
        }

        Properties.isAddingNewCellPhone = false;
    }

    private String getPrice(){
        String result = priceCellPhone.getText().trim();
        return result.replaceAll(",", ".");
    }
    private String getClassE(){
        if(classCellPhone.getValue() == null){
            return "";
        }

        return classCellPhone.getValue();
    }
    private String getTypeCorpus(){
        if(typeCorpusCellPhone.getValue() == null){
            return "";
        }

        return typeCorpusCellPhone.getValue();
    }
    private String getOS(){
        if(osCellPhone.getValue() == null){
            return "";
        }

        return osCellPhone.getValue();
    }
    private String getDisplay(){
        if(displayCellPhone.getValue() == null){
            return "";
        }

        return displayCellPhone.getValue().split("\\.")[0];
    }
    private String getConfiguration(){
        if(configurationCellPhone.getValue() == null){
            return "";
        }

        return configurationCellPhone.getValue().split("\\.")[0];
    }
    private String getCamera(){
        if(cameraCellPhone.getValue() == null){
            return "";
        }

        return cameraCellPhone.getValue().split("\\.")[0];
    }
    private String getCorpus(){
        if(corpusNameCellPhone.getValue() == null){
            return "";
        }

        return corpusNameCellPhone.getValue();
    }
    private String getMultimedia(){
        if(multimediaCellPhone.getValue() == null){
            return "";
        }

        return multimediaCellPhone.getValue().split("\\.")[0];
    }
    private String getCountry(){
        if(countryCellPhone.getValue() == null){
            return "";
        }

        return countryCellPhone.getValue().split("\\.")[0];
    }
    private boolean isUniqueKeyCellPhone(int key){
        for(CellPhone cellPhone: accountingSystem.getCellPhones()){
            if(key == cellPhone.getArticleNumber()){
                return false;
            }
        }
        return true;
    }

    /**---------WORKER----------------------------------*/
    private ObservableList<String> positions;

    private void initListChoiceBoxesWorker(){
        positions = FXCollections.observableArrayList();
        for(Position position: Position.values()){
            positions.add(position.getPost());
        }
    }
    private void initChoiceBoxAddWorker(){
        positionAddWorker.setItems(positions);
    }
    @FXML //from
    private void addNewWorker(/*ActionEvent event*/) {
        Properties.isAddingNewWorker = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingWorker.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение нового сотрудника");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            //System.out.println("Cant download new window 9!");
        }
    }

    @FXML //in
    private void addObjectWorker(ActionEvent event) {
        String serviceNumber = serviceNumberAddWorker.getText().trim();
        String secondName = secondNameAddWorker.getText().trim();
        String name = nameAddWorker.getText().trim();
        String middleName =  middleNameAddWorker.getText().trim();
        String position = getPosition();
        String numberPhone = numberPhoneAddWorker.getText().trim();

        if(isAllLineNotEmpty(serviceNumber, secondName, name, middleName, position, numberPhone)
                && isLengthValueCorrect(numberPhone, 11)
                && (isDigit(serviceNumber, false) && isDigit(numberPhone, false))
                    && (!isContainsForbiddenChars(secondName) && !isContainsForbiddenChars(name)
                    && !isContainsForbiddenChars(middleName)) && isUniqueKeyWorker(Integer.parseInt(serviceNumber))){
            Properties.isAddingNewWorker = false;

            Worker newWorker = Worker.newBuilder().setServiceNumber(Integer.parseInt(serviceNumber))
                                                    .setSecondName(secondName)
                                                    .setName(name)
                                                    .setMiddleName(middleName)
                                                    .setPosition(position)
                                                    .setNumberPhone(numberPhone).build();

            accountingSystem.addingNewWorker(newWorker);
            Properties.workers.getItems().add(newWorker);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewWorker = false;
            if(isContainsForbiddenChars(secondName) || isContainsForbiddenChars(name)
                    || isContainsForbiddenChars(middleName)){
                if(isContainsForbiddenChars(secondName)){
                    mistakeSNameWorker();
                }
                if(isContainsForbiddenChars(name)){
                    mistakeNameWorker();
                }
                if(isContainsForbiddenChars(middleName)){
                    mistakeMNameWorker();
                }
                openMessageForbiddenChars();
            }else {
                if(isDigit(serviceNumber, false) && !serviceNumber.equals("")
                        && !isUniqueKeyWorker(Integer.parseInt(serviceNumber))){
                    openNeedUniqueKeys();
                    mistakeCodeWorker();
                }else{
                    if(!isDigit(serviceNumber, false)){
                        mistakeCodeWorker();
                    }
                    if(!isDigit(numberPhone, false) || !isLengthValueCorrect(numberPhone, 11)){
                        mistakePhoneWorker();
                    }
                    openMessageNotFillDate(/*event*/);
                }
            }
        }
    }

    private String getPosition(){
        if(positionAddWorker.getValue() == null){
            return "";
        }

        return positionAddWorker.getValue();
    }
    private boolean isUniqueKeyWorker(int key){
        for(Worker worker: accountingSystem.getWorkers()){
            if(key == worker.getServiceNumber()){
                return false;
            }
        }
        return true;
    }

    /**---------PROVIDER----------------------------------*/
    @FXML //from
    private void addNewProvider(/*ActionEvent event*/) {
        Properties.isAddingNewProvider = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addProvider.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение нового поставщика");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window (AddingProvider)");
        }
    }
    @FXML //in
    private void addObjectProvider(ActionEvent event) {
        String code = codeAddProvider.getText().trim();
        String name = nameAddProvider.getText().trim();
        String inn = innAddProvider.getText().trim();
        String cpp = cppAddProvider.getText().trim();
        String address = addressAddProvider.getText().trim();
        String phone = numberPhoneAddProvider.getText().trim();
        String paymentAccount = paymentAccountAddProvider.getText().trim();
        String bank = bankAddProvider.getText().trim();
        String bik = bikAddProvider.getText().trim();
        String correspondentAccount = correspondentAccountAddProvider.getText().trim();

        if(isAllLineNotEmpty(code, name, inn, cpp, address, phone, paymentAccount, bank, bik, correspondentAccount)
                    && (isLengthValueCorrect(inn, 9) && isLengthValueCorrect(cpp, 10)
                && isLengthValueCorrect(phone, 11)
                && isLengthValueCorrect(paymentAccount, 11)
                && isLengthValueCorrect(bik, 9)
                && isLengthValueCorrect(correspondentAccount, 20))
                    && (isDigit(code, false) && isDigit(inn, false) && isDigit(cpp, false)
                    && isDigit(phone, false) && isDigit(paymentAccount, false)
                    && isDigit(bik, false) && isDigit(correspondentAccount, false))
                && (!isContainsForbiddenChars(name) && !isContainsForbiddenChars(address)
                && !isContainsForbiddenChars(bank)) && isUniqueKeyProvider(Integer.parseInt(code))){

            Properties.isAddingNewProvider = false;
            Provider newProvider = Provider.newBuilder().setCode(Integer.parseInt(code))
                                                        .setName(name)
                                                        .setINN(inn)
                                                        .setCPP(cpp)
                                                        .setAddress(address)
                                                        .setNumberPhone(phone)
                                                        .setPaymentAccount(paymentAccount)
                                                        .setBank(bank)
                                                        .setBIK(bik)
                                                        .setCorrespondentAccount(correspondentAccount).build();

            accountingSystem.addingNewProvider(newProvider);
            Properties.providers.getItems().add(newProvider);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewProvider = false;
            if(!isLengthValueCorrect(inn, 9)){
                mistakeINNProvider();
            }
            if(!isLengthValueCorrect(cpp, 10)){
                mistakeCPPProvider();
            }
            if(!isLengthValueCorrect(phone, 11)){
                mistakePhoneProvider();
            }
            if(!isLengthValueCorrect(paymentAccount, 11)){
                mistakePAccProvider();
            }
            if(!isLengthValueCorrect(bik, 9)){
                mistakeBIKProvider();
            }
            if(!isLengthValueCorrect(correspondentAccount, 20)){
                mistakeCAccProvider();
            }

            if((isContainsForbiddenChars(name) || isContainsForbiddenChars(address)
                    || isContainsForbiddenChars(bank))){
                if(isContainsForbiddenChars(name)){
                    mistakeNameProvider();
                }
                if(isContainsForbiddenChars(address)){
                    mistakeAddressProvider();
                }
                if(isContainsForbiddenChars(bank)){
                    mistakeBankProvider();
                }
                openMessageForbiddenChars();
            }else{
                if (isDigit(code, false) && !isUniqueKeyProvider(Integer.parseInt(code))){
                    mistakeCodeProvider();
                    openNeedUniqueKeys();
                }else {
                    if(!isDigit(code, false)){
                        mistakeCodeProvider();
                    }
                    if(!isDigit(inn, false)){
                        mistakeINNProvider();
                    }
                    if(!isDigit(cpp, false)){
                        mistakeCPPProvider();
                    }
                    if(!isDigit(phone, false)){
                        mistakePhoneProvider();
                    }
                    if(!isDigit(paymentAccount, false)){
                        mistakePAccProvider();
                    }
                    if(!isDigit(bik, false)){
                        mistakeBIKProvider();
                    }
                    if(!isDigit(correspondentAccount, false)){
                        mistakeCAccProvider();
                    }
                    openMessageNotFillDate(/*event*/);
                }
            }
        }
    }

    private boolean isUniqueKeyProvider(int key){
        for(Provider provider: accountingSystem.getProviders()){
            if(key == provider.getCode()){
                return false;
            }
        }
        return true;
    }

    /**---------CLIENT----------------------------------*/
    @FXML //from
    private void addNewClient(ActionEvent event) {
        Properties.isAddingNewClient = true;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingClient.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение нового клиента");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window (AddingClient)");
        }
    }
    @FXML //in
    public void addObjectClient(ActionEvent event) {
        String codeClient = codeAddClient.getText().trim();
        String secondName = secondNameAddClient.getText().trim();
        String name = nameAddClient.getText().trim();
        String middleName = middleNameAddClient.getText().trim();
        String numberPhone = numberPhoneAddClient.getText().trim();
        String email = emailAddClient.getText().trim();
        String address = addressAddClient.getText().trim();

        if(isAllLineNotEmpty(codeClient, name, numberPhone) && isLengthValueCorrect(numberPhone, 11) &&
                (isDigit(codeClient, false) && isDigit(numberPhone, false))
                    && (!isContainsForbiddenChars(secondName)
                    && !isContainsForbiddenChars(name) && !isContainsForbiddenChars(middleName)
                    && !isContainsForbiddenChars(address))
                                && isUniqueKeyClient(Integer.parseInt(codeClient))){

            Properties.isAddingNewClient = false;
            int codeClientR = Integer.parseInt(codeClient);

            Client newClient = Client.newBuilder().setCodeClient(codeClientR)
                                                    .setSecondName(secondName)
                                                    .setName(name)
                                                    .setMiddleName(middleName)
                                                    .setNumberPhone(numberPhone)
                                                    .setEmail(email)
                                                    .setAddress(address).build();

            accountingSystem.addingNewClient(newClient);
            Properties.clients.getItems().add(newClient);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewClient = false;
            if(isContainsForbiddenChars(secondName)
                    || isContainsForbiddenChars(name) || isContainsForbiddenChars(middleName)
                    || isContainsForbiddenChars(address)){
                if(isContainsForbiddenChars(secondName)){
                    mistakeSNameClient();
                }
                if(isContainsForbiddenChars(name) || name.isEmpty()){
                    mistakeNameClient();
                }
                if(isContainsForbiddenChars(middleName)){
                    mistakeMNameClient();
                }
                if(isContainsForbiddenChars(address)){
                    mistakeAddressClient();
                }

                openMessageForbiddenChars();
            }else{
                if(isDigit(codeClient, false) && !isUniqueKeyClient(Integer.parseInt(codeClient))){
                    mistakeCodeClient();
                    openNeedUniqueKeys();
                }else{
                    if(!isDigit(codeClient, false)){
                        mistakeCodeClient();
                    }
                    if(!isDigit(numberPhone, false) || !isLengthValueCorrect(numberPhone, 11)){
                        mistakePhoneClient();
                    }
                    openMessageNotFillDate();
                }
            }
        }
    }

    private boolean isUniqueKeyClient(int key){
        for(Client client: accountingSystem.getClients()){
            if(key == client.getCodeClient()){
                return false;
            }
        }
        return true;
    }

    /**---------SALE----------------------------------*/
    private ObservableList<String> listCodeAddWorkerSale;
    private ObservableList<String> listCodeAddClientSale;

    private void initListChoiceBoxesSales(){
        listCodeAddWorkerSale = FXCollections.observableArrayList();

        for(Worker worker: accountingSystem.getWorkers()){
            listCodeAddWorkerSale.add(worker.getServiceNumber() + ": " + worker.getSecondName()
                    + " " + worker.getName() + ", " + worker.getPosition());
        }

        listCodeAddClientSale = FXCollections.observableArrayList();

        for(Client client: accountingSystem.getClients()){
            listCodeAddClientSale.add(client.getCodeClient() + ": " + client.getName() + ", " + client.getNumberPhone());
        }
    }
    private void initChoiceBoxAddSale(){
        codeAddWorkerSale.setItems(listCodeAddWorkerSale);
        codeAddClientSale.setItems(listCodeAddClientSale);
    }

    @FXML //from
    private void addNewSale(ActionEvent event) {
        Properties.isAddingNewSale = true;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingSale.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение новой продажи");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window (AddingSale)");
        }
    }
    @FXML //in
    private void addObjectSale(ActionEvent event) {
        String codeSale = codeAddSale.getText().trim();
        String codeWorker = getCodeAddWorkerSale();
        String codeClient = getCodeAddClientSale();
        String date = getNormalDate(addDateSale);

        StructureSales[] ofSales;


        if(!date.isEmpty() && !codeWorker.isEmpty() && !codeClient.isEmpty() &&
                isDigit(codeSale, false)
                && isUniqueKeySale(Integer.parseInt(codeSale)) && tableOfSale.getItems().size() != 0){
            Properties.isAddingNewSale = false;

            ObservableList<StructureSales> list = tableOfSale.getItems();
            int countData = list.size();
            ofSales = new StructureSales[countData];

            for(int i = 0; i < list.size(); i++){
                StructureSales tmp = list.get(i);

                StructureSales newOfSale = StructureSales.newBuilder()
                        .setCodeSale(Integer.parseInt(codeSale))
                        .setCodeProduct(tmp.getCodeProduct())
                        .setQuantity(tmp.getQuantity())
                        .setAmount(tmp.getAmount())
                        .setShortNameProduct(tmp.getShortNameProduct()).build();

                ofSales[i] = newOfSale;
            }

            //warehouse decrease quantity phones
            Warehouse.getInstance().sellingCellPhones(ofSales);

            Sale newSale = Sale.newBuilder().setCodeSale(Integer.parseInt(codeSale))
                                            .setCodeWorker(Integer.parseInt(codeWorker))
                                            .setCodeClient(Integer.parseInt(codeClient))
                                            .setDate(date)
                                            .setOfSales(ofSales).build();


            accountingSystem.addingNewSales(newSale);
            Properties.sales.getItems().add(newSale);

            closeAddingWindow(event);
        }else{
            Properties.isAddingNewSale = false;

            if(tableOfSale.getItems().size() == 0){
                openMessageNotFillDate();
            }

            if(!isDigit(codeSale, false)){
                if(!isDigit(codeSale, false)){
                    mistakeCodeSale();
                }

                openMessageNotFillDate();

            }else if(!isUniqueKeySale(Integer.parseInt(codeSale))){
                mistakeCodeSale();
                openNeedUniqueKeys();
            }else if(date.isEmpty() || codeWorker.isEmpty() || codeClient.isEmpty()){
                openMessageNotFillDate();
            }
        }

    }


    private String getCodeAddWorkerSale(){
        if(codeAddWorkerSale.getValue() == null){
            return "";
        }
        return codeAddWorkerSale.getValue().split(":")[0];
    }
    private String getCodeAddClientSale(){
        if(codeAddClientSale.getValue() == null){
            return "";
        }
        return codeAddClientSale.getValue().split(":")[0];
    }


    private boolean isUniqueKeySale(int key){
        for(Sale sale: accountingSystem.getSale()){
            if(key == sale.getCodeSale()){
                return false;
            }
        }
        return true;
    }

    /**---------STRUCTURE_SALE----------------------------------*/
    private ObservableList<String> listCodeAddProductSale;

    private void initListChoiceBoxesOfSale(){
        listCodeAddProductSale = FXCollections.observableArrayList();

        for(CellPhone cellPhone: accountingSystem.getCellPhones()){
            listCodeAddProductSale.add(cellPhone.getShortName()
                    + ", " + cellPhone.getPrice() + " руб.");
        }
    }
    private void initChoiceBoxAddOfSale(){
        codeAddProductOfSale.setItems(listCodeAddProductSale);
    }

    @FXML //from
    private void addNewStructureSale(ActionEvent event) {
        Properties.isAddingNewOfSale = true;
        Properties.isAddingNewSale = false;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/addingSaleStructure.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавление нового состава продажи");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open addingSaleStructure");
        }
    }

    @FXML // in
    private void addingObjectOfSale(ActionEvent event) {
        String codeProduct = getCodeAddProductSale();
        String quantity = addQuantityOfSale.getText();
        String amount = addAmountOfSale.getText();

        String shortName = codeAddProductOfSale.getValue().split(",")[0];

        if(isDigit(quantity, false)){
                StructureSales structureSales =
                        StructureSales.newBuilder().setCodeProduct(Integer.parseInt(codeProduct))
                                                    .setQuantity(Integer.parseInt(quantity))
                                                    .setAmount(Double.parseDouble(amount))
                                                    .setShortNameProduct(shortName).build();

            Properties.ofSales.getItems().add(structureSales);

            closeAddingOfSale(event);
        }else{
            if(!isDigit(quantity, false)){
                mistakeQuantitySale();
            }
        }
    }

    private String getCodeAddProductSale(){
        if(codeAddProductOfSale.getValue() == null){
            return "";
        }
        return codeAddProductOfSale.getValue().split(":")[0];
    }
    public void countingAmountSale(/*MouseEvent mouseEvent*/) {
        if(codeAddProductOfSale.getValue() != null && (!addQuantityOfSale.getText().equals("") &&
                isDigit(addQuantityOfSale.getText(), false))){
            int quantity = Integer.parseInt(addQuantityOfSale.getText());
            int codeProduct = Integer.parseInt(codeAddProductOfSale.getValue().split(":")[0]);
            double price = accountingSystem.getCellPhone(codeProduct).getPrice();
            double amount = price * quantity;

            addAmountOfSale.setText(String.valueOf(amount));
        }
    }
    /**-------------------------------MethodsForAdding-------------------------------------------------------*/
    @FXML
    private void closeMessageWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeAddingWindow(ActionEvent event) {
        Properties.isAddingNewDelivery = false;
        Properties.isAddingNewOfDelivery = false;
        Properties.isAddingNewCellPhone = false;
        Properties.isAddingNewWorker = false;
        Properties.isAddingNewProvider = false;
        Properties.isAddingNewClient = false;
        Properties.isAddingNewSale = false;
        Properties.isCheckWorker = false;
        Properties.isCheckCellPhone = false;
        Properties.isCheckDelivery = false;
        Properties.isCheckProvider = false;
        Properties.isCheckClient = false;
        Properties.isCheckSale = false;
        Properties.isTablePartDelivery = false;
        Properties.isTablePartSellProduct = false;
        Properties.isNotFountElement = false;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeAddingOfDelivery(ActionEvent event) {
        Properties.isAddingNewDelivery = true;
        Properties.isAddingNewOfDelivery = false;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeCheckOfDelivery(ActionEvent event) {
        Properties.isCheckDelivery = true;
        Properties.isCheckOfDelivery = false;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeAddingOfSale(ActionEvent event){
        Properties.isAddingNewSale = true;
        Properties.isAddingNewOfSale = false;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeCheckOfSale(ActionEvent event) {
        Properties.isCheckSale = true;
        Properties.isCheckOfSale = false;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void openNeedUniqueKeys(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/needUniqueKey.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Добавдение нового товара");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open NeedUniqueKey");
        }
    }

    private boolean isLengthValueCorrect(String value, int mustLength){
        return value.length() == mustLength;
    }

    private void openMessageNotFillDate(/*ActionEvent event*/){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/messageNotFillData.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open MessageNotFillDate");
        }
    }

    private void openMessageForbiddenChars(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/inputForbiddenCharacters.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Ошибка в заполении");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant open MessageForbiddenChars");
        }
    }

    private boolean isAllLineNotEmpty(String ... lines){
        for(String line: lines){
            if(line == null || line.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private boolean isDigit(String line, boolean hasPoint){
        boolean wasPoint = false;
        for(int i = 0; i < line.length(); i++){
            char chr = line.charAt(i);
            if(!Character.isDigit(chr)){
                if((chr == '.' || chr == ',') && hasPoint){
                    if(!wasPoint){
                        wasPoint = true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isContainsForbiddenChars(String line){
        return line.contains("!") || line.contains("&");
    }

    /**--------------------CHECKING_ONE_DATA_FROM_TABLE----------------------------------
     *---------WORKER----------------------------------*/
    private void initFieldCheckingWorker(){
        labelServiceNumber.setText(String.valueOf(Properties.checkingWorker.getServiceNumber()));
        serviceNumberCheckWorker.setText(String.valueOf(Properties.checkingWorker.getServiceNumber()));
        secondNameCheckWorker.setText(Properties.checkingWorker.getSecondName());
        nameCheckWorker.setText(Properties.checkingWorker.getName());
        middleNameCheckWorker.setText(Properties.checkingWorker.getMiddleName());
        numberPhoneCheckWorker.setText(Properties.checkingWorker.getNumberPhone());
        positionCheckWorker.setText(Properties.checkingWorker.getPosition());
    }
    @FXML
    private void getDataOneWorker(ActionEvent event) {
        ObservableList<Worker> productSelected;
        productSelected = tableWorkers.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchNumberWorker.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingWorker = productSelected.get(lastId);
                openCheckWorker();
            }
        }else{
            int codeWorker = Integer.parseInt(searchNumberWorker.getText());
            searchNumberWorker.setText("");
            Properties.checkingWorker = accountingSystem.getWorker(codeWorker);
            openCheckWorker();
        }

    }
    private void openCheckWorker(){
        Properties.isCheckWorker = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkWorker.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckWorker");
        }
    }

    /**---------DELIVERY----------------------------------*/

    private void initTableCheckOfDelivery(){
        initColumnsCheckTableOfDelivery();

        tableCheckOfDeliveries.setItems(getCheckOfDelivery());
    }
    private void initColumnsCheckTableOfDelivery(){
        columnProductCheckOfDelivery.setCellValueFactory(new PropertyValueFactory<>("shortNameProduct"));
        columnQuantityCheckOfDelivery.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    private ObservableList<StructureOfDelivery> getCheckOfDelivery(){
        ObservableList<StructureOfDelivery> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(Properties.checkingDelivery.getOfDeliveries()));

        return list;
    }

    private void initFieldCheckingDelivery(){
        labelCodeDelivery.setText(String.valueOf(Properties.checkingDelivery.getNumber()));
        codeCheckDelivery.setText(String.valueOf(Properties.checkingDelivery.getNumber()));
        dateCheckDelivery.setText(Properties.checkingDelivery.getDate());

        int idWorker = Properties.checkingDelivery.getServiceNumberWorker();
        Worker worker = accountingSystem.getWorker(idWorker);
        workerCheckDelivery.setText(worker.getServiceNumber() + ": " + worker.getSecondName() + " " + worker.getName()
                + ", " + worker.getPosition());

        int idProvider = Properties.checkingDelivery.getProviderCode();
        Provider provider = accountingSystem.getProvider(idProvider);
        providerCheckDelivery.setText(provider.getCode() + ": " + provider.getName() + ", "
                + provider.getNumberPhone() + ", " + provider.getAddress());

        amountCheckDelivery.setText(Properties.checkingDelivery.getAmount() + " руб.");
    }
    @FXML
    private void getDataOneDelivery(ActionEvent event) {
        ObservableList<Delivery> productSelected;
        productSelected = tableDelivery.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchKeyDelivery.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingDelivery = productSelected.get(lastId);
                StructureOfDelivery[] ofSales = Properties.checkingDelivery.getOfDeliveries();

                if(referencesDeliveryIsCorrect(Properties.checkingDelivery.getServiceNumberWorker(),
                        Properties.checkingDelivery.getProviderCode(), ofSales)){
                    openCheckDelivery();
                }else{
                    deleteRowDelivery();
                }
            }
        }else{
            int codeDelivery = Integer.parseInt(searchKeyDelivery.getText());
            searchKeyDelivery.setText("");
            Properties.checkingDelivery = accountingSystem.getDelivery(codeDelivery);
            openCheckDelivery();
        }
    }
    private void openCheckDelivery(){
        Properties.isCheckDelivery = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkDelivery.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckDelivery");
        }
    }

    /**---------STRUCTURE_OF_DELIVERY----------------------------------*/
    private void initTextFieldsCheckOfDelivery(){
        StructureOfDelivery check = Properties.checkingOfDelivery;
        CellPhone checkPhone = accountingSystem.getCellPhone(check.getArticleProduct());

        productCheckOfDelivery.setText(checkPhone.getArticleNumber() + ": " + checkPhone.getName()
                + ", " + checkPhone.getPrice());
        quantityCheckOfDelivery.setText(String.valueOf(check.getQuantity()));
    }

    @FXML
    private void getDataOneOfDelivery(ActionEvent event) {
        ObservableList<StructureOfDelivery> productSelected;
        productSelected = tableCheckOfDeliveries.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;

        if(lastId >= 0) {
            Properties.checkingOfDelivery = productSelected.get(lastId);

            openCheckOfDelivery();
        }
    }
    private void openCheckOfDelivery(){
        Properties.isCheckDelivery = false;
        Properties.isCheckOfDelivery = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkOfDelivery.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckDelivery");
        }
    }

    /**---------CELLPHONE----------------------------------*/
    private void initFieldCheckingCellPhone(){
        labelArticlePhone.setText(String.valueOf(Properties.checkingCellPhone.getArticleNumber()));
        articleCheckCellPhone.setText(String.valueOf(Properties.checkingCellPhone.getArticleNumber()));
        nameCheckCellPhone.setText(Properties.checkingCellPhone.getName());
        priceCheckCellPhone.setText(String.valueOf(Properties.checkingCellPhone.getPrice()));
        simCheckCellPhone.setText(String.valueOf(Properties.checkingCellPhone.getNumberSIM()));
        classCheckCellPhone.setText(Properties.checkingCellPhone.getClassE());
        typeCorpusCheckCellPhone.setText(Properties.checkingCellPhone.getTypeCorpus());

        int idCountry = Properties.checkingCellPhone.getCountry();
        countryCheckCellPhone.setText(idCountry + " "
                + accountingSystem.getCountry(idCountry).getCountry());

        int idMultimedia = Properties.checkingCellPhone.getMultimedia();
        Multimedia multimedia = accountingSystem.getMultimedia(idMultimedia);
        multimediaCheckCellPhone.setText(idMultimedia + ": "
                + multimedia.getInterfaceUSB() + ", " + multimedia.getSuppVideoFormat() + ", "
                + multimedia.getConnectorHeadPhone());

        corpusCheckCellPhone.setText(Properties.checkingCellPhone.getCorpus());

        int idCamera = Properties.checkingCellPhone.getCamera();
        Camera camera = accountingSystem.getCamera(idCamera);
        cameraCheckCellPhone.setText(camera.getCode() + ": " + camera.getMainCamera() + ", " + camera.getFrontCamera()
                + ", " + camera.getRecordVideo() + ", " + camera.getTypeSpark());

        int idConfiguration = Properties.checkingCellPhone.getConfiguration();
        Configuration configuration = accountingSystem.getConfiguration(idConfiguration);
        configurationCheckCellPhone.setText(configuration.getCode() + ": " + configuration.getProcessor() + ", "
                + configuration.getSizeRAM() + ", " + configuration.getSizeInMemory() + ", "
                + configuration.getMaxSizeStorageMemory());

        int idDisplay = Properties.checkingCellPhone.getDisplay();
        Display display = accountingSystem.getDisplay(idDisplay);
        displayCheckCellPhone.setText(display.getCode() + ": " + display.getDisplayDiagonal() + ", "
                + display.getDisplayResolution() + ", " + display.getNumberPPI() + ", " + display.getTouchScreen());

        osCheckCellPhone.setText(Properties.checkingCellPhone.getOS());
    }
    @FXML
    private void getDataOneCellPhone(ActionEvent event) {
        ObservableList<CellPhone> productSelected;
        productSelected = tableProducts.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchArticleProduct.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingCellPhone = productSelected.get(lastId);
                openCheckCellPhone();
            }
        }else{
            int codeCellPhone = Integer.parseInt(searchArticleProduct.getText());
            searchArticleProduct.setText("");
            Properties.checkingCellPhone = accountingSystem.getCellPhone(codeCellPhone);
            openCheckCellPhone();
        }
    }
    private void openCheckCellPhone(){
        Properties.isCheckCellPhone = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkCellPhone.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckCellPhone");
        }
    }

    /**---------PROVIDER----------------------------------*/
    private void initFieldCheckingProvider(){
        labelCodeProvider.setText(String.valueOf(Properties.checkingProvider.getCode()));
        codeCheckProvider.setText(String.valueOf(Properties.checkingProvider.getCode()));
        nameCheckProvider.setText(Properties.checkingProvider.getName());
        innCheckProvider.setText(Properties.checkingProvider.getINN());
        cppCheckProvider.setText(Properties.checkingProvider.getCPP());
        addressCheckProvider.setText(Properties.checkingProvider.getAddress());
        numberPhoneCheckProvider.setText(Properties.checkingProvider.getNumberPhone());
        correspondentAccountCheckProvider.setText(Properties.checkingProvider.getCorrespondentAccount());
        bikCheckProvider.setText(Properties.checkingProvider.getBIK());
        bankCheckProvider.setText(Properties.checkingProvider.getBank());
        paymentAccountCheckProvider.setText(Properties.checkingProvider.getPaymentAccount());
    }
    @FXML
    private void getDataOneProvider(ActionEvent event) {
        ObservableList<Provider> productSelected;
        productSelected = tableProviders.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchNumberProvider.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingProvider = productSelected.get(lastId);
                openCheckProvider();
            }
        }else {
            int codeProvider = Integer.parseInt(searchNumberProvider.getText());
            searchNumberProvider.setText("");
            Properties.checkingProvider = accountingSystem.getProvider(codeProvider);
            openCheckProvider();
        }
    }
    private void openCheckProvider(){
        Properties.isCheckProvider = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkProvider.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckProvider");
        }
    }

    /**---------CLIENT----------------------------------*/
    private void initFieldCheckingClient(){
        labelCodeClient.setText(String.valueOf(Properties.checkingClient.getCodeClient()));
        infCheckClient.setText(String.valueOf(Properties.checkingClient.getCodeClient()));
        secondNameCheckClient.setText(Properties.checkingClient.getSecondName());
        nameCheckClient.setText(Properties.checkingClient.getName());
        middleNameCheckClient.setText(Properties.checkingClient.getMiddleName());
        numberPhoneCheckClient.setText(Properties.checkingClient.getNumberPhone());
        emailCheckClient.setText(Properties.checkingClient.getEmail());
        addressCheckClient.setText(Properties.checkingClient.getAddress());
    }
    @FXML
    private void getDataOneClient(ActionEvent event){
        ObservableList<Client> productSelected;
        productSelected = tableClients.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchCodeClient.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingClient = productSelected.get(lastId);
                openCheckClient();
            }
        }else {
            int codeProvider = Integer.parseInt(searchCodeClient.getText());
            searchCodeClient.setText("");
            Properties.checkingClient = accountingSystem.getClient(codeProvider);
            openCheckClient();
        }
    }
    private void openCheckClient(){
        Properties.isCheckClient = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkClient.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckClient");
        }
    }

    /**---------SALE----------------------------------*/
    private void initTableCheckOfSale(){
        initColumnsCheckTableOfSale();

        tableCheckOfSale.setItems(getCheckOfSale());
    }
    private void initColumnsCheckTableOfSale(){
        columnProductCheckOfSale.setCellValueFactory(new PropertyValueFactory<>("shortNameProduct"));
        columnQuantityCheckOfSale.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnAmountCheckOfSale.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    private ObservableList<StructureSales> getCheckOfSale(){
        ObservableList<StructureSales> list = FXCollections.observableArrayList();

        list.addAll(Arrays.asList(Properties.checkingSale.getOfSales()));

        return list;
    }

    private void initFieldCheckingSale(){
        checkLabelSale.setText(String.valueOf(Properties.checkingSale.getCodeSale()));
        checkCodeSale.setText(String.valueOf(Properties.checkingSale.getCodeSale()));
        Worker worker = accountingSystem.getWorker(Properties.checkingSale.getCodeWorker());
        checkCodeWorkerSale.setText(worker.getServiceNumber() + ": " + worker.getSecondName() + " " + worker.getName()
                + ", " + worker.getPosition());

        Client client = accountingSystem.getClient(Properties.checkingSale.getCodeClient());
        checkCodeClientSale.setText(client.getCodeClient() + ": " + client.getSecondName() + " "
                + client.getName() + ", " + client.getNumberPhone());
        checkDateSale.setText(Properties.checkingSale.getDate());
    }

    @FXML
    private void getDataOneSale(ActionEvent event) {
        ObservableList<Sale> productSelected;
        productSelected = tableSales.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(searchCodeSale.getText().isEmpty()){
            if(lastId >= 0){
                Properties.checkingSale = productSelected.get(lastId);
                StructureSales[] ofSales = Properties.checkingSale.getOfSales();

                int idWorker = Properties.checkingSale.getCodeWorker();
                int idClient = Properties.checkingSale.getCodeClient();

                if(referencesSaleIsCorrect(idWorker, idClient, ofSales)) {
                    openCheckSale();
                }else{
                    deleteRowSale();
                }
            }
        }else {
            int codeSale = Integer.parseInt(searchCodeSale.getText());
            searchCodeSale.setText("");
            Properties.checkingSale = accountingSystem.getSale(codeSale);
            openCheckSale();
        }
    }
    private void openCheckSale(){
        Properties.isCheckSale = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkSale.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckSale");
        }
    }

    /**---------STRUCTURE_SALE----------------------------------*/
    private void initTextFieldsCheckOfSale(){
        StructureSales check = Properties.checkingOfSale;
        CellPhone checkPhone = accountingSystem.getCellPhone(check.getCodeProduct());

        productCheckOfSale.setText(checkPhone.getArticleNumber()
                + ": " + checkPhone.getName() + ", " + checkPhone.getPrice());
        quantityCheckOfSale.setText(String.valueOf(check.getQuantity()));
        amountCheckOfSale.setText(String.valueOf(check.getAmount()));
    }

    @FXML
    private void getDataOneOfSale(ActionEvent event) {
        ObservableList<StructureSales> productSelected;
        productSelected = tableCheckOfSale.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;

        if(lastId >= 0) {
            Properties.checkingOfSale = productSelected.get(lastId);

            openCheckOfSale();
        }
    }
    private void openCheckOfSale(){
        Properties.isCheckSale = false;
        Properties.isCheckOfSale = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/checkOfSale.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window CheckDelivery");
        }
    }

    /**-----------------------------SEARCH_ONE_DATA_BY_uKEY-------------------------------------------------*/

    @FXML
    private void searchDelivery(ActionEvent event) {
        String codeSearch = searchKeyDelivery.getText().trim();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdDelivery(Integer.parseInt(codeSearch)) != -1) {
                getDataOneDelivery(event);
            } else {
                searchKeyDelivery.setText("");
                Properties.messageNotFountElement = "Поставщик, с номер " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    @FXML
    private void searchProduct(ActionEvent event) {
        String codeSearch = searchArticleProduct.getText();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdCellPhone(Integer.parseInt(codeSearch)) != -1) {
                getDataOneCellPhone(event);
            } else {
                searchArticleProduct.setText("");
                Properties.messageNotFountElement = "Товар, с номером " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    @FXML
    private void searchWorker(ActionEvent event) {
        String codeSearch = searchNumberWorker.getText();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdWorker(Integer.parseInt(codeSearch)) != -1) {
                getDataOneWorker(event);
            } else {
                searchNumberWorker.setText("");
                Properties.messageNotFountElement = "Сотрудник, с номером  " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    @FXML
    private void searchProvider(ActionEvent event) {
        String codeSearch = searchNumberProvider.getText();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdProvider(Integer.parseInt(codeSearch)) != -1) {
                getDataOneProvider(event);
            } else {
                searchNumberProvider.setText("");
                Properties.messageNotFountElement = "Поставщик, с номером  " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    @FXML
    private void searchClient(ActionEvent event) {
        String codeSearch = searchCodeClient.getText();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdClient(Integer.parseInt(codeSearch)) != -1) {
                getDataOneClient(event);
            } else {
                searchCodeClient.setText("");
                Properties.messageNotFountElement = "Клиент, с номером  " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    @FXML
    private void searchSale(ActionEvent event){
        String codeSearch = searchCodeSale.getText();
        if(!codeSearch.isEmpty() && isDigit(codeSearch, false)) {
            if (accountingSystem.getIdSale(Integer.parseInt(codeSearch)) != -1) {
                getDataOneSale(event);
            } else {
                searchCodeSale.setText("");
                Properties.messageNotFountElement = "Продажа, с номером  " + codeSearch + ", не найден!";
                openWindowNoFoundElement();
            }
        }else{
            openMessageNotFillDate();
        }
    }

    /**------------------------------MethodsForOpenAndSearchOneData----------------------------------------*/
    private void openWindowNoFoundElement(){
        Properties.isNotFountElement = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/messageNotFoundElement.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window SearchElement");
        }
    }

    private boolean referencesDeliveryIsCorrect(int idWorker, int idProvider, StructureOfDelivery[] ofDeliveries){
        boolean firstWave = -1 != accountingSystem.getIdWorker(idWorker)
                && -1 != accountingSystem.getIdProvider(idProvider);

        if(!firstWave){
            return false;
        }

        for(StructureOfDelivery ofDelivery: ofDeliveries){
            if(-1 == accountingSystem.getIdCellPhone(ofDelivery.getArticleProduct())){
                return false;
            }
        }

        return true;
    }
    private boolean referencesSaleIsCorrect(int idWorker, int idClient, StructureSales[] ofSales){
        boolean firstWave= -1 != accountingSystem.getIdWorker(idWorker)
                && -1 != accountingSystem.getIdClient(idClient);

        if(!firstWave){
            return false;
        }

        for(StructureSales ofSale: ofSales){
            if(-1 == accountingSystem.getIdCellPhone(ofSale.getCodeProduct())){
                return false;
            }
        }

        return true;
    }

    /**------------------------------TABLE_PARTS------------------------------------------
     *---------DELIVERY----------------------------------*/
    private void initTablePartDelivery(){
        initColumnsTablePartDelivery();

        tablePartDelivery.setItems(getDeliveries());
    }
    private void initColumnsTablePartDelivery(){
        numberPartDelivery.setCellValueFactory(new PropertyValueFactory<>("number"));
        datePartDelivery.setCellValueFactory(new PropertyValueFactory<>("date"));
        providerPartDelivery.setCellValueFactory(new PropertyValueFactory<>("providerCode"));
        workerPartDelivery.setCellValueFactory(new PropertyValueFactory<>("serviceNumberWorker"));
        amountPartDelivery.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tablePartDelivery.getSortOrder().setAll(datePartDelivery);
    }
    @FXML
    private void openTablePartDelivery(ActionEvent event) {
        Properties.isTablePartDelivery = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/tablePartDeliveries.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window TablePartDelivery");
        }
    }

    /**---------SALE----------------------------------*/
    private void initTablePartSale(){
        initColumnsTablePartSale();

        tablePartSales.setItems(getSales());
    }
    private void initColumnsTablePartSale(){
        codePartSale.setCellValueFactory(new PropertyValueFactory<>("codeSale"));
        codePartWorkerSale.setCellValueFactory(new PropertyValueFactory<>("codeWorker"));
        codePartClientSale.setCellValueFactory(new PropertyValueFactory<>("codeClient"));
        datePartSale.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    @FXML
    private void openTablePartSellProducts(ActionEvent event){
        Properties.isTablePartSellProduct = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_core/tablePartSellProducts.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.show();
        }catch (Exception e){
            System.out.println("Cant download new window TablePartSellProduct");
        }
    }

    /**--------------------------onAction_for_button_SAVE-----------------------------*/
    @FXML
    private void saveDataDelivery(){
        saveData.saveDelivery();
        saveData.saveWarehouse();
//        Warehouse.getInstance().iterationMap();
    }

    @FXML
    private void saveDataProduct(){
        saveData.saveCellPhone();
    }

    @FXML
    private void saveDataWorker(){
        saveData.saveWorker();
    }

    @FXML
    private void saveDataProvider(){
        saveData.saveProvider();
    }

    @FXML
    private void saveDataClient(){
        saveData.saveClient();
    }

    @FXML
    private void saveDataSale(){
        saveData.saveSales();
        saveData.saveWarehouse();
//        Warehouse.getInstance().iterationMap();
    }


    /**---------------------onAction_for_button_DELETE---------------------------*/
    @FXML
    private void deleteRowDelivery(){
        ObservableList<Delivery> productSelected, allProduct;
        allProduct = tableDelivery.getItems();
        productSelected = tableDelivery.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;

        if(lastId >= 0) {
            Delivery remover = productSelected.get(lastId);

            accountingSystem.removingDelivery(remover);
        }

        try {
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Delivery");
        }

        saveDataDelivery();

    }

    @FXML
    private void deleteRowOfDelivery(ActionEvent event) {
        ObservableList<StructureOfDelivery> productSelected, allProduct;
        allProduct = tableOfDeliveries.getItems();
        productSelected = tableOfDeliveries.getSelectionModel().getSelectedItems();


        try{
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. OfDelivery");
        }

    }

    @FXML
    private void deleteRowProduct(){
        ObservableList<CellPhone> productSelected, allProduct;
        allProduct = tableProducts.getItems();
        productSelected = tableProducts.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(lastId >= 0){
            CellPhone remover = productSelected.get(lastId);

            accountingSystem.removingCellPhone(remover);
        }

        try {
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Product");
        }

        saveDataProduct();
    }

    @FXML
    private void deleteRowWorker(){
        ObservableList<Worker> productSelected, allProduct;
        allProduct = tableWorkers.getItems();
        productSelected = tableWorkers.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(lastId >= 0){
            Worker remover = productSelected.get(lastId);

            accountingSystem.removingWorker(remover);
        }

        try {
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Worker");
        }

        saveDataWorker();
    }

    @FXML
    private void deleteRowProvider(){
        ObservableList<Provider> productSelected, allProduct;
        allProduct = tableProviders.getItems();
        productSelected = tableProviders.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(lastId >= 0){
            Provider remover = productSelected.get(lastId);

            accountingSystem.removingProvider(remover);
        }

        try {
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Provide");
        }

        saveDataProvider();
    }

    @FXML
    private void deleteRowClient(){
        ObservableList<Client> productSelected, allProduct;
        allProduct = tableClients.getItems();
        productSelected = tableClients.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(lastId >= 0){
            Client remover = productSelected.get(lastId);

            accountingSystem.removingClient(remover);
        }

        try {
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Client");
        }

        saveDataClient();
    }

    @FXML
    private void deleteRowSale(){
        ObservableList<Sale> productSelected, allProduct;
        allProduct = tableSales.getItems();
        productSelected = tableSales.getSelectionModel().getSelectedItems();

        int lastId = productSelected.size()-1;
        if(lastId >= 0){
            Sale remover = productSelected.get(lastId);

            accountingSystem.removingSales(remover);
        }

        try{
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. Sale");
        }

        saveData.saveSales();
    }

    @FXML
    private void deleteRowOfSale(ActionEvent event) {
        ObservableList<StructureSales> productSelected, allProduct;
        allProduct = tableOfSale.getItems();
        productSelected = tableOfSale.getSelectionModel().getSelectedItems();

        try{
            productSelected.forEach(allProduct::remove);
        }catch (NoSuchElementException e){
            System.out.println("No such element. ofSale");
        }
    }

    /**-------------------generate_reports_tables------------------------------*/
    @FXML
    private void generatedReportDelivery(ActionEvent event) throws IOException {
        saveDataDelivery();
        ReportsMaker.getInstance().generateDeliveriesReports(accountingSystem.getDeliveries());
        if(isFirstMessage){
            mustCloseExcelFirst.setOpacity(1);
        }else{
            mustCloseExcelFirst.setOpacity(0);
            mustCloseExcelSecond.setOpacity(0);
            changeColorLabelCreate(labelCreateReportDelivery, opacityDelivery);
        }
    }

    @FXML
    private void generatedReportSales(ActionEvent event) throws IOException{
        saveData.saveSales();
        ReportsMaker.getInstance().generateSalesReports(accountingSystem.getSale());
        ReportsMaker.getInstance().generateRemainProductsReports();
        if(isSecondMessage){
            mustCloseExcelSecond.setOpacity(1);
        }else{
            mustCloseExcelSecond.setOpacity(0);
            mustCloseExcelFirst.setOpacity(0);
            changeColorLabelCreate(labelCreateReportSales, opacitySales);
        }
    }

    @FXML
    private void openReportsFile(MouseEvent mouseEvent) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(ReportsMaker.getInstance().getPathNameFile_Reports());
        try {
            desktop.open(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**-------------------generate_cheques------------------------------*/
    @FXML
    private void makeChequeOfSale(ActionEvent event) throws IOException{
        ChequeAndDocumentInfoMaker.getInstance().generateChequeOnSale(Properties.checkingSale);
        if(isSaleMessage){
            mustCloseExcelSale.setOpacity(1);
        }else{
            mustCloseExcelSale.setOpacity(0);
            openChequeFile();
        }
    }

    private void openChequeFile(){
        Desktop desktop = Desktop.getDesktop();
        File file = new File(ChequeAndDocumentInfoMaker.getInstance().getPathNameFile_Cheque());
        try {
            desktop.open(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**-------------------generate_docInfo_Specific_Delivery-------------*/
    @FXML
    private void makeSpecificDeliveryDocument(ActionEvent event) throws IOException{
        ChequeAndDocumentInfoMaker.getInstance().generateDocumentInfoSpecificDelivery(Properties.checkingDelivery);
        if(isDeliveryMessage){
            mustCloseExcelDelivery.setOpacity(1);
        }else{
            mustCloseExcelDelivery.setOpacity(0);
            openDocumentInfoSpecificDelivery();
        }

    }

    private void openDocumentInfoSpecificDelivery(){
        Desktop desktop = Desktop.getDesktop();
        File file = new File(ChequeAndDocumentInfoMaker.getInstance().getPathNameFile_Document());
        try {
            desktop.open(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**---------------another_methods_for_functioning_frames------------------*/
    @FXML
    private void closeSimpleWindow(ActionEvent event) {
        closeAddingWindow(event);
    }

    /**-----change_color_button_CLOSEWINDOW_and_same_with_it*/
    @FXML
    private void changeColorButtonToRed(/*MouseEvent event*/) {
        buttonClose.setStyle("-fx-background-color:  #CD2626");
    }
    @FXML
    private void changeColorButtonToGray(/*MouseEvent event*/) {
        buttonClose.setStyle("-fx-background-color:  #CDC9C9");
    }

    /**-----methods_for_popup_label_REPORTS_CREATED*/
    private void changeColorLabelCreate(Label label, double[] opacity){
        opacity[0] = 1;
        label.setOpacity(opacity[0]);
        Timer timer = new Timer(100, actionEvent -> {
            if(opacity[0] >= 0) {
                label.setOpacity(opacity[0] -= 0.01);
                label.setCursor(javafx.scene.Cursor.HAND);
            }else{
                label.setCursor(javafx.scene.Cursor.DEFAULT);
                opacity[0] = -1;
            }
        });
        timer.start();

    }
    @FXML
    private void reloadNumberOpacityDelivery(MouseEvent mouseEvent) {
        int beforePoint = (int) (opacityDelivery[0] * 10);
        if(beforePoint >= 0) {
            opacityDelivery[0] = 1;
        }
    }
    @FXML
    public void reloadNumberOpacitySales(MouseEvent mouseEvent) {
        int beforePoint = (int) (opacitySales[0] * 10);
        if(beforePoint >= 0) {
            opacitySales[0] = 1;
        }
    }

    /**--------------VARIABLES-----------------------------------------------*/
    private final AccountingSystem accountingSystem = AccountingSystem.getInstance();
    private final SaveData saveData = new SaveData(accountingSystem);

    /**--------DELIVERY------------------------------------------------
     * mainTable */
    @FXML
    private TableView<Delivery> tableDelivery;
    @FXML
    private TableColumn<Delivery, Integer> numberDelivery;
    @FXML
    private TableColumn<Delivery, String> dateDelivery;
    @FXML
    private TableColumn<Delivery, Integer> providerDelivery;
    @FXML
    private TableColumn<Delivery, Integer> workerDelivery;
    @FXML
    private TableColumn<Delivery, Double> amountDelivery;
    /** check one data */
    @FXML
    private Label labelCodeDelivery;
    @FXML
    private TextField codeCheckDelivery;
    @FXML
    private TextField amountCheckDelivery;
    @FXML
    private TextField dateCheckDelivery;
    @FXML
    private TextField workerCheckDelivery;
    @FXML
    private TextField providerCheckDelivery;

    @FXML
    private TableView<StructureOfDelivery> tableCheckOfDeliveries;
    @FXML
    private TableColumn<StructureOfDelivery, String> columnProductCheckOfDelivery;
    @FXML
    private TableColumn<StructureOfDelivery, Integer> columnQuantityCheckOfDelivery;

    /** add new data */
    @FXML
    private TextField textNumberDelivery;
    @FXML
    private DatePicker dateAddDelivery;
    @FXML
    private ChoiceBox<String> workerAddDelivery;
    @FXML
    private ChoiceBox<String> providerAddDelivery;
    @FXML
    private TextField amountAddDelivery;

    @FXML
    private Rectangle statusCodeDelivery;


    @FXML
    private void returnStatusCodeDelivery(KeyEvent event) {
        statusCodeDelivery.setFill(Color.valueOf("#c3ddf5"));
    }
    private void mistakeCodeDelivery(){
        statusCodeDelivery.setFill(Color.valueOf("#ff5b5b"));
    }

    /** table part delivery */
    @FXML
    private TableView<Delivery> tablePartDelivery;
    @FXML
    private TableColumn<Delivery, Integer> numberPartDelivery;
    @FXML
    private TableColumn<Delivery, String> datePartDelivery;
    @FXML
    private TableColumn<Delivery, Integer> providerPartDelivery;
    @FXML
    private TableColumn<Delivery, Integer> workerPartDelivery;
    @FXML
    private TableColumn<Delivery, Double> amountPartDelivery;
    /** search delivery */
    @FXML
    private TextField searchKeyDelivery;

    /**---------STRUCTURE_OF_DELIVERY-----------------------
    * mainTable in frame addingDelivery */
    @FXML
    private TableView<StructureOfDelivery> tableOfDeliveries;
    @FXML
    private TableColumn<StructureOfDelivery, String> columnProductOfDelivery;
    @FXML
    private TableColumn<StructureOfDelivery, Integer> columnQuantityOfDelivery;
    /** check one data */
    @FXML
    private TextField productCheckOfDelivery;
    @FXML
    private TextField quantityCheckOfDelivery;
    /** add new data */
    @FXML
    private ChoiceBox<String> numberAddProductOfDelivery;
    @FXML
    private TextField quantityAddOfDelivery;

    @FXML
    private Rectangle statusQuantityOfDelivery;

    @FXML
    private void returnStatusCodeQuantity(KeyEvent event) {
        statusQuantityOfDelivery.setFill(Color.valueOf("#c3ddf5"));
    }
    private void mistakeCodeQuantity(){
        statusQuantityOfDelivery.setFill(Color.valueOf("#ff5b5b"));
    }

    /**--------CELL_PHONE------------------------------------------------
     * mainTable */
    @FXML
    private TableView<CellPhone> tableProducts;
    @FXML
    private TableColumn<CellPhone, Integer> articleProduct;
    @FXML
    private TableColumn<CellPhone, String> nameProduct;
    @FXML
    private TableColumn<CellPhone, Double> priceProduct;
    @FXML
    private TableColumn<CellPhone, String> classProduct;
    @FXML
    private TableColumn<CellPhone, String> typeCorpusProduct;
    @FXML
    private TableColumn<CellPhone, Integer> simProduct;
    @FXML
    private TableColumn<CellPhone, String> osProduct;
    @FXML
    private TableColumn<CellPhone, Integer> displayProduct;
    @FXML
    private TableColumn<CellPhone, Integer> configProduct;
    @FXML
    private TableColumn<CellPhone, Integer> cameraProduct;
    @FXML
    private TableColumn<CellPhone, Integer> multyProduct;
    @FXML
    private TableColumn<CellPhone, String> corpusProduct;
    @FXML
    private TableColumn<CellPhone, Integer> countryProduct;
    /** check one data */
    @FXML
    private Label labelArticlePhone;
    @FXML
    private TextField articleCheckCellPhone;
    @FXML
    private TextField nameCheckCellPhone;
    @FXML
    private TextField priceCheckCellPhone;
    @FXML
    private TextField simCheckCellPhone;
    @FXML
    private TextField classCheckCellPhone;
    @FXML
    private TextField typeCorpusCheckCellPhone;
    @FXML
    private TextField countryCheckCellPhone;
    @FXML
    private TextField multimediaCheckCellPhone;
    @FXML
    private TextField corpusCheckCellPhone;
    @FXML
    private TextField cameraCheckCellPhone;
    @FXML
    private TextField configurationCheckCellPhone;
    @FXML
    private TextField displayCheckCellPhone;
    @FXML
    private TextField osCheckCellPhone;
    /** add new data */
    @FXML
    private Rectangle statusCodeCellPhone;
    @FXML
    private Rectangle statusNameCellPhone;
    @FXML
    private Rectangle statusPriceCellPhone;
    @FXML
    private Rectangle statusSIMCellPhone;
    @FXML
    private TextField articleCellPhone;
    @FXML
    private TextField nameCellPhone;
    @FXML
    private TextField priceCellPhone;
    @FXML
    private TextField simCellPhone;
    @FXML
    private ChoiceBox<String> classCellPhone;
    @FXML
    private ChoiceBox<String> typeCorpusCellPhone;
    @FXML
    private ChoiceBox<String> countryCellPhone;
    @FXML
    private ChoiceBox<String> multimediaCellPhone;
    @FXML
    private ChoiceBox<String> corpusNameCellPhone;
    @FXML
    private ChoiceBox<String> cameraCellPhone;
    @FXML
    private ChoiceBox<String> configurationCellPhone;
    @FXML
    private ChoiceBox<String> displayCellPhone;
    @FXML
    private ChoiceBox<String> osCellPhone;

    @FXML
    void returnStatusArticle(KeyEvent event) {
        statusCodeCellPhone.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    void returnStatusNameCellPhone(KeyEvent event) {
        statusNameCellPhone.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    void returnStatusPriceCellPhone(KeyEvent event) {
        statusPriceCellPhone.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    void returnStatusSIM(KeyEvent event) {
        statusSIMCellPhone.setFill(Color.valueOf("#c3ddf5"));
    }

    private void mistakeArticle(){
        statusCodeCellPhone.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeName(){
        statusNameCellPhone.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakePriceCellPhone(){
        statusPriceCellPhone.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeSIM(){
        statusSIMCellPhone.setFill(Color.valueOf("#ff5b5b"));
    }
    /** search cellphones */
    @FXML
    private TextField searchArticleProduct;

    /**--------WORKER------------------------------------------------
     * mainTable */
    @FXML
    private TableView<Worker> tableWorkers;
    @FXML
    private TableColumn<Worker, Integer> numberWorker;
    @FXML
    private TableColumn<Worker, String> sNameWorker;
    @FXML
    private TableColumn<Worker, String> nameWorker;
    @FXML
    private TableColumn<Worker, String> mNameWorker;
    @FXML
    private TableColumn<Worker, String> positionWorker;
    @FXML
    private TableColumn<Worker, String> numberPhoneWorker;
    /** check one data */
    @FXML
    private TextField serviceNumberCheckWorker;
    @FXML
    private TextField secondNameCheckWorker;
    @FXML
    private TextField nameCheckWorker;
    @FXML
    private TextField middleNameCheckWorker;
    @FXML
    private TextField numberPhoneCheckWorker;
    @FXML
    private TextField positionCheckWorker;
    @FXML
    private Label labelServiceNumber;
    /** add new data */
    @FXML
    private TextField serviceNumberAddWorker;
    @FXML
    private TextField secondNameAddWorker;
    @FXML
    private TextField nameAddWorker;
    @FXML
    private TextField middleNameAddWorker;
    @FXML
    private ChoiceBox<String> positionAddWorker;
    @FXML
    private TextField numberPhoneAddWorker;

    @FXML
    private Rectangle statusCodeWorker;
    @FXML
    private Rectangle statusSNameWorker;
    @FXML
    private Rectangle statusNameWorker;
    @FXML
    private Rectangle statusMNameWorker;
    @FXML
    private Rectangle statusPhoneWorker;

    @FXML
    private void returnStatusCodeWorker(KeyEvent keyEvent) {
        statusCodeWorker.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusMNameWorker(KeyEvent event) {
        statusMNameWorker.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusNameWorker(KeyEvent event) {
        statusNameWorker.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusPhoneWorker(KeyEvent event) {
        statusPhoneWorker.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusSNameWorker(KeyEvent event) {
        statusSNameWorker.setFill(Color.valueOf("#c3ddf5"));
    }

    private void mistakeCodeWorker(){
        statusCodeWorker.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeMNameWorker(){
        statusMNameWorker.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeNameWorker(){
        statusNameWorker.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakePhoneWorker(){
        statusPhoneWorker.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeSNameWorker(){
        statusSNameWorker.setFill(Color.valueOf("#ff5b5b"));
    }
    /** search worker */
    @FXML
    private TextField searchNumberWorker;

    /**--------PROVIDER------------------------------------------------
     * mainTable */
    @FXML
    private TableView<Provider> tableProviders;
    @FXML
    private TableColumn<Provider, Integer> codeProvider;
    @FXML
    private TableColumn<Provider, String> nameProvider;
    @FXML
    private TableColumn<Provider, String> innProvider;
    @FXML
    private TableColumn<Provider, String> cppProvider;
    @FXML
    private TableColumn<Provider, String> addressProvider;
    @FXML
    private TableColumn<Provider, String> numberPhoneProvider;
    @FXML
    private TableColumn<Provider, String> paymentAccountProvider;
    @FXML
    private TableColumn<Provider, String> bankProvider;
    @FXML
    private TableColumn<Provider, String> bikProvider;
    @FXML
    private TableColumn<Provider, String> correspondentAccountProvider;
    /** check one data */
    @FXML
    private Label labelCodeProvider;
    @FXML
    private TextField codeCheckProvider;
    @FXML
    private TextField nameCheckProvider;
    @FXML
    private TextField innCheckProvider;
    @FXML
    private TextField cppCheckProvider;
    @FXML
    private TextField addressCheckProvider;
    @FXML
    private TextField numberPhoneCheckProvider;
    @FXML
    private TextField correspondentAccountCheckProvider;
    @FXML
    private TextField bikCheckProvider;
    @FXML
    private TextField bankCheckProvider;
    @FXML
    private TextField paymentAccountCheckProvider;
    /** add new data */
    @FXML
    private TextField codeAddProvider;
    @FXML
    private TextField nameAddProvider;
    @FXML
    private TextField innAddProvider;
    @FXML
    private TextField cppAddProvider;
    @FXML
    private TextField addressAddProvider;
    @FXML
    private TextField numberPhoneAddProvider;
    @FXML
    private TextField correspondentAccountAddProvider;
    @FXML
    private TextField bikAddProvider;
    @FXML
    private TextField bankAddProvider;
    @FXML
    private TextField paymentAccountAddProvider;

    @FXML
    private Rectangle statusCodeProvider;
    @FXML
    private Rectangle statusNameProvider;
    @FXML
    private Rectangle statusINNProvider;
    @FXML
    private Rectangle statusCPPProvider;
    @FXML
    private Rectangle statusAddressProvider;
    @FXML
    private Rectangle statusNPhoneProvider;
    @FXML
    private Rectangle statusPAccProvider;
    @FXML
    private Rectangle statusBankProvider;
    @FXML
    private Rectangle statusBIKProvider;
    @FXML
    private Rectangle statusCAccProvider;

    @FXML
    private void returnStatusAddressProvider(KeyEvent event) {
        statusAddressProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusBIKProvider(KeyEvent event) {
        statusBIKProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusBankProvider(KeyEvent event) {
        statusBankProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusCAccProvider(KeyEvent event) {
        statusCAccProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusCPPProvider(KeyEvent event) {
        statusCPPProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusCodeProvider(KeyEvent event) {
        statusCodeProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusINNProvider(KeyEvent event) {
        statusINNProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusNameProvider(KeyEvent event) {
        statusNameProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusPAccProvider(KeyEvent event) {
        statusPAccProvider.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusPhoneProvider(KeyEvent event) {
        statusNPhoneProvider.setFill(Color.valueOf("#c3ddf5"));
    }

    private void mistakeAddressProvider(){
        statusAddressProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeBIKProvider(){
        statusBIKProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeBankProvider(){
        statusBankProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeCAccProvider(){
        statusCAccProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeCPPProvider(){
        statusCPPProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeCodeProvider(){
        statusCodeProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeINNProvider(){
        statusINNProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeNameProvider(){
        statusNameProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakePAccProvider(){
        statusPAccProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakePhoneProvider(){
        statusNPhoneProvider.setFill(Color.valueOf("#ff5b5b"));
    }
    /** search provider */
    @FXML
    private TextField searchNumberProvider;

    /**--------CLIENT------------------------------------------------
     * mainTable */
    @FXML
    private TableView<Client> tableClients;
    @FXML
    private TableColumn<Client, Integer> codeClient;
    @FXML
    private TableColumn<Client, String> secondNameClient;
    @FXML
    private TableColumn<Client, String> nameClient;
    @FXML
    private TableColumn<Client, String> middleNameClient;
    @FXML
    private TableColumn<Client, Integer> numberPhoneClient;
    @FXML
    private TableColumn<Client, String> emailClient;
    @FXML
    private TableColumn<Client, String> addressClient;
    /** check one data */
    @FXML
    private Label labelCodeClient;
    @FXML
    private TextField infCheckClient;
    @FXML
    private TextField secondNameCheckClient;
    @FXML
    private TextField nameCheckClient;
    @FXML
    private TextField middleNameCheckClient;
    @FXML
    private TextField numberPhoneCheckClient;
    @FXML
    private TextField emailCheckClient;
    @FXML
    private TextField addressCheckClient;
    /** add new data */
    @FXML
    private TextField codeAddClient;
    @FXML
    private TextField secondNameAddClient;
    @FXML
    private TextField nameAddClient;
    @FXML
    private TextField middleNameAddClient;
    @FXML
    private TextField numberPhoneAddClient;
    @FXML
    private TextField emailAddClient;
    @FXML
    private TextField addressAddClient;

    @FXML
    private Rectangle statusCodeClient;
    @FXML
    private Rectangle statusSNameClient;
    @FXML
    private Rectangle statusNameClient;
    @FXML
    private Rectangle statusMNameClient;
    @FXML
    private Rectangle statusPhoneClient;
    @FXML
    private Rectangle statusAddressClient;

    @FXML
    private void returnStatusAddressClient(KeyEvent event) {
        statusAddressClient.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusCodeClient(KeyEvent event) {
        statusCodeClient.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusMNameClient(KeyEvent event) {
        statusMNameClient.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusNameClient(KeyEvent event) {
        statusNameClient.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusPhoneClient(KeyEvent event) {
        statusPhoneClient.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusSNameClient(KeyEvent event) {
        statusSNameClient.setFill(Color.valueOf("#c3ddf5"));
    }

    private void mistakeAddressClient(){
        statusAddressClient.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeCodeClient(){
        statusCodeClient.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeMNameClient(){
        statusMNameClient.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeNameClient(){
        statusNameClient.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakePhoneClient(){
        statusPhoneClient.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeSNameClient(){
        statusSNameClient.setFill(Color.valueOf("#ff5b5b"));
    }
    /** search client */
    @FXML
    private TextField searchCodeClient;

    /**--------SALE------------------------------------------------
     * mainTable */
    @FXML
    private TableView<Sale> tableSales;
    @FXML
    private TableColumn<Sale, Integer> codeSale;
    @FXML
    private TableColumn<Sale, Integer> codeWorkerSale;
    @FXML
    private TableColumn<Sale, Integer> codeClientSale;
    @FXML
    private TableColumn<Sale, String> dateSale;
    /** check one data */
    @FXML
    private TextField checkCodeWorkerSale;
    @FXML
    private TextField checkCodeSale;
    @FXML
    private TextField checkCodeClientSale;
    @FXML
    private TextField checkDateSale;
    @FXML
    private Label checkLabelSale;

    @FXML
    private TableView<StructureSales> tableCheckOfSale;
    @FXML
    private TableColumn<StructureSales, Integer> columnProductCheckOfSale;
    @FXML
    private TableColumn<StructureSales, Integer> columnQuantityCheckOfSale;
    @FXML
    private TableColumn<StructureSales, Double> columnAmountCheckOfSale;
    /** add new data */
    @FXML
    private TextField codeAddSale;
    @FXML
    private ChoiceBox<String> codeAddWorkerSale;
    @FXML
    private ChoiceBox<String> codeAddClientSale;
    @FXML
    private DatePicker addDateSale;

    @FXML
    private Rectangle statusCodeSale;


    @FXML
    private void returnStatusCodeSale(KeyEvent keyEvent) {
        statusCodeSale.setFill(Color.valueOf("#c3ddf5"));
    }
    @FXML
    private void returnStatusQuantitySale(KeyEvent keyEvent) {
        statusQuantitySale.setFill(Color.valueOf("#c3ddf5"));
    }

    private void mistakeCodeSale(){
        statusCodeSale.setFill(Color.valueOf("#ff5b5b"));
    }
    private void mistakeQuantitySale(){
        statusQuantitySale.setFill(Color.valueOf("#ff5b5b"));
    }
    /** table part sale */
    @FXML
    private TableView<Sale> tablePartSales;
    @FXML
    private TableColumn<Sale, Integer> codePartSale;
    @FXML
    private TableColumn<Sale, Integer> codePartWorkerSale;
    @FXML
    private TableColumn<Sale, Integer> codePartClientSale;
    @FXML
    private TableColumn<Sale, String> datePartSale;
    /** search sale */
    @FXML
    private TextField searchCodeSale;

    /**--------STRUCTURE_SALE------------------------------------------------
     * mainTable */
    @FXML
    private TableView<StructureSales> tableOfSale;
    @FXML
    private TableColumn<StructureSales, String> columnProductOfSale;
    @FXML
    private TableColumn<StructureSales, Integer> columnQuantityOfSale;
    @FXML
    private TableColumn<StructureSales, Double> columnAmountOfSale;
    /** check one data*/
    @FXML
    private TextField quantityCheckOfSale;
    @FXML
    private TextField amountCheckOfSale;
    @FXML
    private TextField productCheckOfSale;
    /** add new data*/
    @FXML
    private TextField addQuantityOfSale;
    @FXML
    private TextField addAmountOfSale;
    @FXML
    private ChoiceBox<String> codeAddProductOfSale;

    @FXML
    private Rectangle statusQuantitySale;
    /**----------for frames Password and same this word -------------*/
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField textNewPassword;
    @FXML
    private TextField textRetryNewPassword;
    @FXML
    private PasswordField textOldPassword;


    /**----------for to information users about reports -------------*/
    @FXML
    private Label mustCloseExcelFirst;
    @FXML
    private Label mustCloseExcelSecond;
    @FXML
    private Label mustCloseExcelDelivery;
    @FXML
    private Label mustCloseExcelSale;
    @FXML
    private Label labelCreateReportDelivery;
    @FXML
    private Label labelCreateReportSales;

    private final double[] opacityDelivery = {1};
    private final double[] opacitySales = {1};
    private static boolean isFirstMessage = false;
    private static boolean isSecondMessage = false;
    private static boolean isDeliveryMessage = false;
    private static boolean isSaleMessage = false;

    public static void setFirstMessage(boolean firstMessage) {
        isFirstMessage = firstMessage;
    }
    public static void setSecondMessage(boolean secondMessage) {
        isSecondMessage = secondMessage;
    }
    public static void setDeliveryMessage(boolean deliveryMessage){isDeliveryMessage = deliveryMessage;}
    public static void setSaleMessage(boolean saleMessage){isSaleMessage = saleMessage;}


    /**---------another methods for work frames --------------------*/
    @FXML
    private Label messageNotFoundElement;
    @FXML
    private Button buttonClose;


}





