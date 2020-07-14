package main.application.work_documents.cheque;

import main.application.Controller;
import main.application.Properties;
import main.application.work_documents.UtilMethodsDocuments;
import main.data_base.base.AccountingSystem;
import main.data_base.base.load_and_save.StorageFileNames;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.delivery.Provider;
import main.data_base.objects.delivery.StructureOfDelivery;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.sales.Sale;
import main.data_base.objects.sales.StructureSales;
import main.data_base.objects.sales.client.Client;
import main.data_base.objects.workers_and_data.workers.Worker;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ChequeAndDocumentInfoMaker {
    private static ChequeAndDocumentInfoMaker chequeMaker;
    private ChequeAndDocumentInfoMaker(){}
    public static ChequeAndDocumentInfoMaker getInstance(){
        if(chequeMaker == null){
            chequeMaker = new ChequeAndDocumentInfoMaker();
            chequeMaker.updateDate();
            chequeMaker.getPathNameReportsFileReports();
            chequeMaker.getPathNameReportsFileDocuments();
            chequeMaker.getTotalWorkbookCheque();
            chequeMaker.getTotalWorkbookDocuments();
        }
        return chequeMaker;
    }

    private void writeDataCheque(String pathNameFile_Cheque) {
        try (FileOutputStream fos = new FileOutputStream(pathNameFile_Cheque + "\\cheque.xlsx")){
            workbookReports.write(fos);
        }catch (IOException e){
            System.out.println("class: ChequeAndDocumentInfoMaker.java; Cant generate cheque!");
        }
    }
    
    private void writeDataDelivery(String pathNameFile_Document) {
        try (FileOutputStream fos = new FileOutputStream(pathNameFile_Document + "\\documents.xlsx")){
            workbookDocumentsDelivery.write(fos);
        }catch (IOException e){
            System.out.println("class: ChequeAndDocumentInfoMaker.java; Cant generate document delivery!");
        }
    }

    public void generateChequeOnSale(Sale sale) throws IOException {
        String titleReport = "Чек на продажу " + sale.getCodeSale();

        String pathNameFileCheque = pathNameFile_Cheque + "\\cheque";
        UtilMethodsDocuments.checkingExistDirectory(pathNameFileCheque);
        try{
            Workbook rBook  = new XSSFWorkbook(new FileInputStream(pathNameFileCheque + "\\cheque.xlsx"));
            if (rBook.getSheetIndex(titleReport) != -1) {
                int idSheet = rBook.getSheetIndex(titleReport);
                rBook.removeSheetAt(idSheet);

                File file = new File(pathNameFileCheque + "\\cheque.xlsx");

                FileOutputStream fos = new FileOutputStream(file);
                rBook.write(fos);
                fos.close();
                rBook.close();

                workbookReports = new XSSFWorkbook(new FileInputStream(pathNameFileCheque + "\\cheque.xlsx"));

            }

        }catch (EmptyFileException e){
            System.out.println("File was empty!");

            if(workbookReports.getSheetIndex(titleReport) != -1){
                int idSheet = workbookReports.getSheetIndex(titleReport);
                workbookReports.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameFileCheque + "\\cheque.xlsx");
                workbookReports.write(fos);
                fos.close();

                workbookReports = new XSSFWorkbook(new FileInputStream(pathNameFileCheque + "\\cheque.xlsx"));
            }
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }

        try {
            Sheet sheetDelivery = workbookReports.createSheet(titleReport);
            Controller.setSaleMessage(false);
            createStructureChequeSale(sheetDelivery, sale);
            fillingTableChequeSale(sheetDelivery, sale);
            writeDataCheque(pathNameFileCheque);

            System.out.println("cheque about Cheque of Sale " + sale.getCodeSale() + " was generate.");
        }catch (IllegalArgumentException e){
            Controller.setSaleMessage(true);
        }
    }

    private void createStructureChequeSale(Sheet sheetChequeSale, Sale sale){
        Worker worker = AccountingSystem.getInstance().getWorker(sale.getCodeWorker());
        Client client = AccountingSystem.getInstance().getClient(sale.getCodeClient());

        String labelShopAndPhone = "NicePrice62        " + Properties.getPhoneShop();
        String labelWorker = "Продавец:    " + worker.getServiceNumber() + ": "
                + worker.getSecondName() + " " + worker.getName();
        String labelAddressShop = "Адрес магазина:    " + Properties.getAddress();
        String labelClient = "Покупатель:    " + client.getCodeClient() + ": " + client.getSecondName() + " " + client.getName();
        String labelAddressClient = "Адрес доставки:    " + client.getAddress();

        String numberRow = "№";
        String codeProduct = "Код товара";
        String nameProduct = "Наименование товара";
        String price = "Цена (руб.)";
        String quantity = "Количество";
        String amount = "Сумма (руб.)";

        Row rowOver = sheetChequeSale.createRow(0);
        rowOver.setHeightInPoints(15);
        Cell cellLabelShopAndPhone = rowOver.createCell(4);
        cellLabelShopAndPhone.setCellValue(labelShopAndPhone);

        Row rowFirstOverHead = sheetChequeSale.createRow(1);
        rowFirstOverHead.setHeightInPoints(15);
        Cell cellLabelWorker = rowFirstOverHead.createCell(0);
        cellLabelWorker.setCellValue(labelWorker);

        Cell cellLabelAddressShop = rowFirstOverHead.createCell(3);
        cellLabelAddressShop.setCellValue(labelAddressShop);

        Row rowSecondOverHead = sheetChequeSale.createRow(2);
        rowSecondOverHead.setHeightInPoints(15);
        Cell cellLabelClient = rowSecondOverHead.createCell(0);
        cellLabelClient.setCellValue(labelClient);

        Cell cellLabelAddressClient = rowSecondOverHead.createCell(3);
        cellLabelAddressClient.setCellValue(labelAddressClient);

        CellStyle styleCell = UtilMethodsDocuments.getStyleForHeadBottom(workbookReports);

        Row rowHead = sheetChequeSale.createRow(3);
        rowHead.setHeightInPoints(23);
        Cell cellNumber = rowHead.createCell(0);
        cellNumber.setCellValue(numberRow);
        cellNumber.setCellStyle(styleCell);

        Cell cellCodeProduct = rowHead.createCell(1);
        cellCodeProduct.setCellValue(codeProduct);
        cellCodeProduct.setCellStyle(styleCell);

        Cell cellNameProduct = rowHead.createCell(2);
        cellNameProduct.setCellValue(nameProduct);
        cellNameProduct.setCellStyle(styleCell);

        Cell cellPrice = rowHead.createCell(3);
        cellPrice.setCellValue(price);
        cellPrice.setCellStyle(styleCell);

        Cell cellQuantity = rowHead.createCell(4);
        cellQuantity.setCellValue(quantity);
        cellQuantity.setCellStyle(styleCell);

        Cell cellAmount = rowHead.createCell(5);
        cellAmount.setCellValue(amount);
        cellAmount.setCellStyle(styleCell);

        sheetChequeSale.addMergedRegion(new CellRangeAddress(0,0,4,5));
        sheetChequeSale.addMergedRegion(new CellRangeAddress(1,1,0,2));
        sheetChequeSale.addMergedRegion(new CellRangeAddress(1,1,3,5));
        sheetChequeSale.addMergedRegion(new CellRangeAddress(2,2,0,2));
        sheetChequeSale.addMergedRegion(new CellRangeAddress(2,2,3,5));

        sheetChequeSale.setColumnWidth(0, 3500);
        sheetChequeSale.setColumnWidth(1, 3500);
        sheetChequeSale.setColumnWidth(2, 7500);
        sheetChequeSale.setColumnWidth(3, 4500);
        sheetChequeSale.setColumnWidth(4, 5000);
        sheetChequeSale.setColumnWidth(5, 5000);
    }
    private void fillingTableChequeSale(Sheet sheetChequeSale, Sale sale){
        int rowCount = 4;

        StructureSales[] ofSales = sale.getOfSales();

        for(int i = 0; i < ofSales.length; i++){
            StructureSales ofSale = ofSales[i];
            boolean isEnd = (i == ofSales.length-1);

            CellPhone phone = AccountingSystem.getInstance().getCellPhone(ofSale.getCodeProduct());

            int codeProduct = ofSale.getCodeProduct();
            String nameProduct = phone.getName();
            double price = phone.getPrice();
            int quantity = ofSale.getQuantity();
            double amount = ofSale.getAmount();

            Row newRow = sheetChequeSale.createRow(rowCount);

            Cell addNumberSale = newRow.createCell(0);
            addNumberSale.setCellValue(i + 1);
            addNumberSale.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            Cell addCodeProduct = newRow.createCell(1);
            addCodeProduct.setCellValue(codeProduct);
            addCodeProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            Cell addNameProduct = newRow.createCell(2);
            addNameProduct.setCellValue(nameProduct);
            addNameProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            Cell addPriceProduct = newRow.createCell(3);
            addPriceProduct.setCellValue(price);
            addPriceProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            Cell addQuantity = newRow.createCell(4);
            addQuantity.setCellValue(quantity);
            addQuantity.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            Cell addAmount = newRow.createCell(5);
            addAmount.setCellValue(amount);
            addAmount.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookReports, isEnd));

            rowCount += 1;
        }

        Row endRowAllProducts = sheetChequeSale.createRow(rowCount);
        Cell labelAllProduct = endRowAllProducts.createCell(4);
        labelAllProduct.setCellValue("Всего записей: ");
        labelAllProduct.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,true));

        Cell allProduct = endRowAllProducts.createCell(5);
        allProduct.setCellValue(sale.getOfSales().length);
        allProduct.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,false));

        rowCount += 1;

        Row endRowTotalAmount = sheetChequeSale.createRow(rowCount);
        Cell labelAllAmount = endRowTotalAmount.createCell(4);
        labelAllAmount.setCellValue("Общая сумма: ");
        labelAllAmount.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,true));

        Cell allAmount = endRowTotalAmount.createCell(5);
        allAmount.setCellValue(sale.getTotalAmount());
        allAmount.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,false));

        rowCount += 1;

        Row endRowTotalQuantity = sheetChequeSale.createRow(rowCount);
        Cell labelAllQuantity = endRowTotalQuantity.createCell(4);
        labelAllQuantity.setCellValue("Общее количество: ");
        labelAllQuantity.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,true));

        Cell allQuantity = endRowTotalQuantity.createCell(5);
        allQuantity.setCellValue(sale.getTotalQuantity());
        allQuantity.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookReports,false));

        generateDetailsSale(sheetChequeSale, rowCount, sale);
    }

    private void generateDetailsSale(Sheet sheetCheque, int lastRow, Sale sale){
        lastRow += 10;

        Row rowDetailSale = sheetCheque.createRow(lastRow);
        Cell labelDetailSale = rowDetailSale.createCell(0);
        labelDetailSale.setCellValue("Данные заказа");
        labelDetailSale.setCellStyle(getStyleForDetailSale(true, true, false));
        drawBordersOutside(sheetCheque, lastRow, lastRow, 0, 5);

        sheetCheque.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 5));

        lastRow += 1;

        Row rowNumberAndDateSale = sheetCheque.createRow(lastRow);
        Cell labelNumberSale = rowNumberAndDateSale.createCell(0);
        labelNumberSale.setCellValue("№ Заказа");
        labelNumberSale.setCellStyle(getStyleForDetailSale(false, false, true));
        Cell numberSale = rowNumberAndDateSale.createCell(1);
        numberSale.setCellValue(sale.getCodeSale());
        numberSale.setCellStyle(getStyleForDetailSale(false, false, false));

        Cell labelDateSale = rowNumberAndDateSale.createCell(2);
        labelDateSale.setCellValue("Дата заказа");
        labelDateSale.setCellStyle(getStyleForDetailSale(false, false, true));
        Cell dataSale = rowNumberAndDateSale.createCell(3);
        dataSale.setCellValue(dateMake);
        dataSale.setCellStyle(getStyleForDetailSale(false, false, false));

        lastRow += 1;

        Row rowCodeShop = sheetCheque.createRow(lastRow);
        Cell labelCodeShop = rowCodeShop.createCell(0);
        labelCodeShop.setCellValue("№ магазина");
        labelCodeShop.setCellStyle(getStyleForDetailSale(false, false, true));

        Cell codeShop = rowCodeShop.createCell(1);
        codeShop.setCellValue(Properties.getCodeShop());
        codeShop.setCellStyle(getStyleForDetailSale(false, false, false));


        drawBordersOutside(sheetCheque, 0,lastRow,0, 5);
    }


    public void generateDocumentInfoSpecificDelivery(Delivery delivery) throws IOException{
        String titleReport = "Поставка " + delivery.getNumber();

        String pathNameFileDocument = pathNameFile_Document + "\\documents";
        UtilMethodsDocuments.checkingExistDirectory(pathNameFileDocument);
        try{
            Workbook rBook  = new XSSFWorkbook(new FileInputStream(pathNameFileDocument + "\\documents.xlsx"));

            if (rBook.getSheetIndex(titleReport) != -1) {
                int idSheet = rBook.getSheetIndex(titleReport);
                rBook.removeSheetAt(idSheet);

                File file = new File(pathNameFileDocument + "\\documents.xlsx");

                FileOutputStream fos = new FileOutputStream(file);
                rBook.write(fos);
                fos.close();
                rBook.close();

                workbookDocumentsDelivery = new XSSFWorkbook(new FileInputStream(pathNameFileDocument + "\\documents.xlsx"));

            }

        }catch (EmptyFileException e){
            System.out.println("File was empty!");

            if(workbookDocumentsDelivery.getSheetIndex(titleReport) != -1){
                int idSheet = workbookDocumentsDelivery.getSheetIndex(titleReport);
                workbookDocumentsDelivery.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameFileDocument + "\\documents.xlsx");
                workbookDocumentsDelivery.write(fos);
                fos.close();

                workbookDocumentsDelivery = new XSSFWorkbook(new FileInputStream(pathNameFileDocument + "\\documents.xlsx"));
            }
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }

        try {
            Sheet sheetDelivery = workbookDocumentsDelivery.createSheet(titleReport);
            Controller.setDeliveryMessage(false);
            createStructureDocumentDelivery(sheetDelivery, delivery);
            fillingTableDocumentDelivery(sheetDelivery, delivery);

            writeDataDelivery(pathNameFileDocument);

            System.out.println("document  about Delivery " + delivery.getNumber() + " was generate.");
        }catch (IllegalArgumentException e){
            Controller.setDeliveryMessage(true);
        }
    }

    private void createStructureDocumentDelivery(Sheet sheetDelivery, Delivery delivery){
        Worker worker = AccountingSystem.getInstance().getWorker(delivery.getServiceNumberWorker());
        Provider provider = AccountingSystem.getInstance().getProvider(delivery.getProviderCode());

        String numberDelivery = "Номер поставки:    " + delivery.getNumber();
        String dateDelivery = "Дата:    " + delivery.getDate();
        String workerDelivery = "Сотрудник:    " + worker.getServiceNumber() + ": "
                + worker.getSecondName() +  " " + worker.getName() + ", " + worker.getPosition();
        String providerDelivery = "Поставщик:    " + provider.getCode() + ": "
                + provider.getName() + ", " + provider.getNumberPhone();

        String nameProduct = "Товар";
        String quantity = "Количество";

        CellStyle styleCell = UtilMethodsDocuments.getStyleForHeadBottom(workbookDocumentsDelivery);

        Row rowOver = sheetDelivery.createRow(0);
        rowOver.setHeightInPoints(20);
        Cell cellNumberDelivery = rowOver.createCell(0);
        cellNumberDelivery.setCellValue(numberDelivery);
//        cellNumberDelivery.setCellStyle(styleCell);

        Cell cellDateDelivery = rowOver.createCell(2);
        cellDateDelivery.setCellValue(dateDelivery);
//        cellDateDelivery.setCellStyle(styleCell);

        Row rowWorker = sheetDelivery.createRow(1);
        rowWorker.setHeightInPoints(20);
        Cell cellWorker = rowWorker.createCell(0);
        cellWorker.setCellValue(workerDelivery);
//        cellWorker.setCellStyle(styleCell);

        Row rowProvider = sheetDelivery.createRow(2);
        rowProvider.setHeightInPoints(20);
        Cell cellProviderDelivery = rowProvider.createCell(0);
        cellProviderDelivery.setCellValue(providerDelivery);
//        cellProviderDelivery.setCellStyle(styleCell);

        Row rowHeadTable = sheetDelivery.createRow(3);
        rowHeadTable.setHeightInPoints(20);
        Cell cellNameProduct = rowHeadTable.createCell(0);
        Cell cellRightNP = rowHeadTable.createCell(1);
        cellNameProduct.setCellValue(nameProduct);
        cellNameProduct.setCellStyle(styleCell);
        cellRightNP.setCellStyle(styleCell);

        Cell cellQuantity = rowHeadTable.createCell(2);
        Cell cellRightQ = rowHeadTable.createCell(3);
        cellQuantity.setCellValue(quantity);
        cellQuantity.setCellStyle(styleCell);
        cellRightQ.setCellStyle(styleCell);

        sheetDelivery.addMergedRegion(new CellRangeAddress(0,0,0,1));
        sheetDelivery.addMergedRegion(new CellRangeAddress(0,0,2,3));
        sheetDelivery.addMergedRegion(new CellRangeAddress(1,1,0,3));
        sheetDelivery.addMergedRegion(new CellRangeAddress(2,2,0,3));
        sheetDelivery.addMergedRegion(new CellRangeAddress(3,3,0,1));
        sheetDelivery.addMergedRegion(new CellRangeAddress(3,3,2,3));

        sheetDelivery.setColumnWidth(0, 5000);
        sheetDelivery.setColumnWidth(1, 2500);
        sheetDelivery.setColumnWidth(2, 5000);
        sheetDelivery.setColumnWidth(3, 2500);

    }
    private void fillingTableDocumentDelivery(Sheet sheetDelivery, Delivery delivery){
        int rowCount = 4;

        StructureOfDelivery[] ofDeliveries = delivery.getOfDeliveries();

        for(int i = 0; i < ofDeliveries.length; i++){
            StructureOfDelivery ofDelivery = ofDeliveries[i];
            boolean isEnd = (i == ofDeliveries.length-1);

            CellPhone phone = AccountingSystem.getInstance().getCellPhone(ofDelivery.getArticleProduct());

            int quantity = ofDelivery.getQuantity();

            Row newRow = sheetDelivery.createRow(rowCount);
            Cell addNameProduct = newRow.createCell(0);
            Cell editRightANP = newRow.createCell(1);
            addNameProduct.setCellValue(phone.getShortName());
            addNameProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookDocumentsDelivery, isEnd));
            editRightANP.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookDocumentsDelivery, isEnd));

            Cell addQuantity = newRow.createCell(2);
            Cell editRightAQ = newRow.createCell(3);
            addQuantity.setCellValue(quantity);
            addQuantity.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookDocumentsDelivery, isEnd));
            editRightAQ.setCellStyle(UtilMethodsDocuments.getStyleForData(workbookDocumentsDelivery, isEnd));

            sheetDelivery.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,1));
            sheetDelivery.addMergedRegion(new CellRangeAddress(rowCount,rowCount,2,3));

            rowCount += 1;
        }

        Row endRowAmount = sheetDelivery.createRow(rowCount);
        String amount = "Сумма: " + delivery.getAmount() + " руб.";
        Cell cellAmount = endRowAmount.createCell(2);
        Cell cellRightA = endRowAmount.createCell(3);

        cellAmount.setCellValue(amount);
        cellAmount.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookDocumentsDelivery,true));
        cellRightA.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbookDocumentsDelivery,true));
        sheetDelivery.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2,3));

        rowCount += 1;
        Row endRowNumberOfDelivery = sheetDelivery.createRow(rowCount);
        String numberOfDeliveries = "Кол-во записей: " + ofDeliveries.length;
        Cell cellNumberOfDelivery = endRowNumberOfDelivery.createCell(2);
        Cell cellRightNOF = endRowNumberOfDelivery.createCell(3);

        cellNumberOfDelivery.setCellValue(numberOfDeliveries);
        cellNumberOfDelivery.setCellStyle(
                UtilMethodsDocuments.getStyleForResultsReport(workbookDocumentsDelivery,true));
        cellRightNOF.setCellStyle
                (UtilMethodsDocuments.getStyleForResultsReport(workbookDocumentsDelivery,true));
        sheetDelivery.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2,3));

        drawBordersOutside(sheetDelivery, 0, rowCount, 0, 3);
    }


    private void drawBordersOutside(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        PropertyTemplate borderTotalTable = new PropertyTemplate();
        borderTotalTable.drawBorders(new CellRangeAddress(firstRow,lastRow,firstCol, lastCol),
                BorderStyle.MEDIUM, BorderExtent.OUTSIDE);

        borderTotalTable.applyBorders(sheet);
    }

    private CellStyle getStyleForDetailSale(boolean isItalic, boolean isRightPosition, boolean isDefaultPosition){
        CellStyle styleDetailSale = workbookReports.createCellStyle();
        styleDetailSale.setBorderBottom(BorderStyle.THIN);
        styleDetailSale.setBorderLeft(BorderStyle.THIN);
        styleDetailSale.setBorderRight(BorderStyle.THIN);
        styleDetailSale.setBorderTop(BorderStyle.THIN);

        if(isItalic){
            styleDetailSale.setFont(getFontItalic());
        }

        styleDetailSale.setVerticalAlignment(VerticalAlignment.CENTER);

        if(isRightPosition){
            styleDetailSale.setAlignment(HorizontalAlignment.RIGHT);
        }else if(isDefaultPosition){
            styleDetailSale.setAlignment(HorizontalAlignment.LEFT);
        }


        return styleDetailSale;
    }

    private Font getFontItalic(){
        Font font = workbookReports.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setItalic(true);

        return font;
    }

    private void getTotalWorkbookCheque(){
        workbookReports = new XSSFWorkbook();
    }

    private void getTotalWorkbookDocuments(){
        workbookDocumentsDelivery = new XSSFWorkbook();
    }

    private void getPathNameReportsFileReports(){
        pathNameFile_Cheque = StorageFileNames.mainDirectory;
        System.out.println("pathNameFile_Cheque: " +pathNameFile_Cheque);
    }

    private void getPathNameReportsFileDocuments(){
        pathNameFile_Document = StorageFileNames.mainDirectory;
        System.out.println("pathNameFile_Document: " + pathNameFile_Document);
    }

    private void updateDate(){
        dateMake = UtilMethodsDocuments.updateDate();
    }

    public String getPathNameFile_Cheque() {
        return pathNameFile_Cheque + "\\cheque\\cheque.xlsx";
    }

    public String getPathNameFile_Document(){
        return pathNameFile_Document + "\\documents\\documents.xlsx";
    }

    private String pathNameFile_Document;
    private String pathNameFile_Cheque;
    private Workbook workbookReports;
    private Workbook workbookDocumentsDelivery;
    private String dateMake;
}
