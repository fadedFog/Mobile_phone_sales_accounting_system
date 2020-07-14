package main.application;

import javafx.scene.control.TableView;
import main.data_base.objects.delivery.StructureOfDelivery;
import main.data_base.objects.sales.Sale;
import main.data_base.objects.sales.StructureSales;
import main.data_base.objects.sales.client.Client;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.delivery.Provider;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.workers_and_data.workers.Worker;


public class Properties {
    public static boolean systemWasLoad = false;
    public static boolean isInSystem = false;

    public static boolean isAddingNewDelivery = false;
    public static TableView<Delivery> deliveries;

    public static boolean isAddingNewOfDelivery = false;
    public static TableView<StructureOfDelivery> ofDelivery;

    public static boolean isAddingNewCellPhone = false;
    public static TableView<CellPhone> cellPhones;

    public static boolean isAddingNewWorker = false;
    public static TableView<Worker> workers;

    public static boolean isAddingNewProvider = false;
    public static TableView<Provider> providers;

    public static boolean isAddingNewClient = false;
    public static TableView<Client> clients;

    public static boolean isAddingNewSale = false;
    public static TableView<Sale> sales;

    public static boolean isAddingNewOfSale = false;
    public static TableView<StructureSales> ofSales;

    public static boolean isCheckWorker = false;
    public static Worker checkingWorker;

    public static boolean isCheckProvider = false;
    public static Provider checkingProvider;

    public static boolean isCheckCellPhone = false;
    public static CellPhone checkingCellPhone;

    public static boolean isCheckDelivery = false;
    public static Delivery checkingDelivery;

    public static boolean isCheckOfDelivery = false;
    public static StructureOfDelivery checkingOfDelivery;

    public static boolean isCheckClient = false;
    public static Client checkingClient;

    public static boolean isCheckSale = false;
    public static Sale checkingSale;

    public static boolean isCheckOfSale = false;
    public static StructureSales checkingOfSale;

    public static boolean isNotFountElement = false;
    public static String messageNotFountElement;

    public static boolean isTablePartDelivery = false;
    public static boolean isTablePartSellProduct = false;


    private static String password;
    private static String phoneShop;
    private static String address;
    private static int codeShop;

    public static void setPassword(String password) {
        Properties.password = password;
    }
    public static void setPhoneShop(String phoneShop){
        Properties.phoneShop = phoneShop;
    }
    public static void setAddress(String address) {
        Properties.address = address;
    }
    public static void setCodeShop(int codeShop) {
        Properties.codeShop = codeShop;
    }

    public static String getPassword() {
        return password;
    }
    public static String getPhoneShop() {
        return phoneShop;
    }
    public static String getAddress() {
        return address;
    }
    public static int getCodeShop() {
        return codeShop;
    }
}
