package main.application.work_documents.reports;

import main.application.Controller;
import main.application.work_documents.UtilMethodsDocuments;
import main.data_base.base.load_and_save.StorageFileNames;
import main.data_base.objects.delivery.Delivery;
import main.data_base.objects.products_and_components.products.CellPhone;
import main.data_base.objects.products_and_components.warehouse.Warehouse;
import main.data_base.objects.sales.Sale;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;

public class ReportsMaker {
    private static ReportsMaker reportsMaker;
    private ReportsMaker(){}
    public static ReportsMaker getInstance(){
        if(reportsMaker == null){
            reportsMaker = new ReportsMaker();
            reportsMaker.updateDate();
            reportsMaker.getPathNameReportsFile();
            reportsMaker.getTotalWorkbookReports();

        }
        return reportsMaker;
    }

    private void writeDataReports(String pathNameDirectoryReports) {
        try (FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx")){
            workbook.write(fos);
        }catch (IOException e){
            System.out.println("class: ReportsMaker.java; Cant generate reports!");
        }
    }

    public void generateDeliveriesReports(Delivery[] deliveries) throws IOException{
        String titleReport = "Отчет по Доставкам";

        String pathNameDirectoryReports = pathNameFile_Reports + "\\reports";
        UtilMethodsDocuments.checkingExistDirectory(pathNameDirectoryReports);
        try{
            Workbook rBook  = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));


            if (rBook.getSheetIndex(titleReport) != -1) {
                int idSheet = rBook.getSheetIndex(titleReport);
                rBook.removeSheetAt(idSheet);

                File file = new File(pathNameDirectoryReports + "\\reports.xlsx");

                FileOutputStream fos = new FileOutputStream(file);
                rBook.write(fos);
                fos.close();
                rBook.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));

            }

        }catch (EmptyFileException e){
            System.out.println("File was empty!");

            if(workbook.getSheetIndex(titleReport) != -1){
                int idSheet = workbook.getSheetIndex(titleReport);
                workbook.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx");
                workbook.write(fos);
                fos.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));
            }
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }

        try {
            Sheet sheetDelivery = workbook.createSheet(titleReport);
            Controller.setFirstMessage(false);
            Controller.setSecondMessage(false);
            createStructureTableDelivery(sheetDelivery);
            fillingTableReportDelivery(sheetDelivery, deliveries);
            writeDataReports(pathNameDirectoryReports);

//            System.out.println("reports about Delivery was generate.");
        }catch (IllegalArgumentException e){
            Controller.setFirstMessage(true);
        }
    }

    private void createStructureTableDelivery(Sheet sheetDelivery){
        String headTable = "Отчёт по Поставка " + dateMake;
        String number = "Номер";
        String date = "Дата";
        String provider = "Поставщик";
        String worker = "Сотрудник";
        String amount = "Сумма (руб.)";

        Row rowHead = sheetDelivery.createRow(0);
        rowHead.setHeightInPoints(45);
        Row rowTypes = sheetDelivery.createRow(1);

        Cell cellHead = rowHead.createCell(0);
        cellHead.setCellValue(headTable);
        cellHead.setCellStyle(getStyleForOnlyHead());
        Cell cellRightHead = rowHead.createCell(5);
        cellRightHead.setCellStyle(getStyleForHeadRightBorder());

        CellStyle styleBottomHead = UtilMethodsDocuments.getStyleForHeadBottom(workbook);

        Cell cellNumber = rowTypes.createCell(0);
        cellNumber.setCellValue(number);
        cellNumber.setCellStyle(styleBottomHead);

        Cell cellDate = rowTypes.createCell(1);
        cellDate.setCellValue(date);
        cellDate.setCellStyle(styleBottomHead);

        Cell cellProvider = rowTypes.createCell(2);
        cellProvider.setCellValue(provider);
        cellProvider.setCellStyle(styleBottomHead);

        Cell cellWorker = rowTypes.createCell(3);
        cellWorker.setCellValue(worker);
        cellWorker.setCellStyle(styleBottomHead);

        Cell cellAmount = rowTypes.createCell(4);
        cellAmount.setCellValue(amount);
        cellAmount.setCellStyle(styleBottomHead);


        sheetDelivery.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));


        sheetDelivery.setColumnWidth(0, 2500);
        sheetDelivery.setColumnWidth(1, 3500);
        sheetDelivery.autoSizeColumn(2);
        sheetDelivery.setColumnWidth(3, 4500);
        sheetDelivery.setColumnWidth(4, 3500);
    }
    private void fillingTableReportDelivery(Sheet sheetDeliver, Delivery[] deliveries){
        int rowCount = 2;
        for(int i = 0; i < deliveries.length; i++){
            Delivery delivery = deliveries[i];
            boolean isEnd = (i == deliveries.length-1);

            Row newRow = sheetDeliver.createRow(rowCount);

            Cell addNumber = newRow.createCell(0);
            addNumber.setCellValue(delivery.getNumber());
            addNumber.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));
            Cell addDate = newRow.createCell(1);
            addDate.setCellValue(delivery.getDate());
            addDate.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));
            Cell addProvider = newRow.createCell(2);
            addProvider.setCellValue(delivery.getProviderCode());
            addProvider.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));
            Cell addWorker = newRow.createCell(3);
            addWorker.setCellValue(delivery.getServiceNumberWorker());
            addWorker.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));
            Cell addAmount = newRow.createCell(4);
            addAmount.setCellValue(delivery.getAmount());
            addAmount.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));

            rowCount += 1;
        }


        Row endRowAmount = sheetDeliver.createRow(rowCount);
        Cell textAllAmount = endRowAmount.createCell(3);
        textAllAmount.setCellValue("Итоги: ");
        textAllAmount.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,true));

        Cell allAmount = endRowAmount.createCell(4);
        int resultAmount = getResultAmount(4, rowCount, sheetDeliver);
        allAmount.setCellValue(resultAmount);
        allAmount.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,false));

        rowCount += 1;

        Row endRowNumberDelivery = sheetDeliver.createRow(rowCount);
        Cell textNumberDelivery = endRowNumberDelivery.createCell(3);
        textNumberDelivery.setCellValue("Всего поставок: ");
        textNumberDelivery.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,true));

        Cell numberDelivery = endRowNumberDelivery.createCell(4);
        numberDelivery.setCellValue(deliveries.length);
        numberDelivery.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,false));

    }

    private int getResultAmount(int column, int to, Sheet sheet){
        int resultAmount = 0;

        for(int i = 2; i < to; i++){
            Row row = sheet.getRow(i);
            Cell cellPrice = row.getCell(column);
            resultAmount += cellPrice.getNumericCellValue();
        }

        return resultAmount;
    }


    public void generateSalesReports(Sale[] sales) throws IOException{
        String nameSheet = "Отчет по Реализации товаров";
        
        String pathNameDirectoryReports = pathNameFile_Reports + "\\reports";
        UtilMethodsDocuments.checkingExistDirectory(pathNameDirectoryReports);
        
        try{
            Workbook rBook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));

            if (rBook.getSheetIndex(nameSheet) != -1) {
                int idSheet = rBook.getSheetIndex(nameSheet);
                rBook.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx");
                rBook.write(fos);
                fos.close();
                rBook.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));
            }

        }catch (EmptyFileException e){
            System.out.println("File was empty!");

            if(workbook.getSheetIndex(nameSheet) != -1){
                int idSheet = workbook.getSheetIndex(nameSheet);
                workbook.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx");
                workbook.write(fos);
                fos.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));
            }
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }

        try {
            Sheet sheetSales = workbook.createSheet(nameSheet);
            Controller.setFirstMessage(false);
            Controller.setSecondMessage(false);
            createStructureTableSales(sheetSales);
            fillingTableReportSales(sheetSales, sales);
            writeDataReports(pathNameDirectoryReports);

//            System.out.println("reports about Sale was generate.");
        }catch (IllegalArgumentException e){
            Controller.setSecondMessage(true);
        }
    }

    private void createStructureTableSales(Sheet sheetSales){
        String headTable = "Отчет по Реализации товаров " + dateMake;
        String codeClient = "Код продажи";
        String codeWorker = "Код сотрудника";
        String codeProduct = "Код кдиента";
        String date = "Дата";

        Row rowHead = sheetSales.createRow(0);
        rowHead.setHeightInPoints(45);
        Row rowTypes = sheetSales.createRow(1);

        Cell cellHead = rowHead.createCell(0);
        cellHead.setCellValue(headTable);
        cellHead.setCellStyle(getStyleForOnlyHead());
        Cell cellRightHead = rowHead.createCell(4);
        cellRightHead.setCellStyle(getStyleForHeadRightBorder());

        CellStyle styleBottomHead = UtilMethodsDocuments.getStyleForHeadBottom(workbook);

        Cell cellCodeClient = rowTypes.createCell(0);
        cellCodeClient.setCellValue(codeClient);
        cellCodeClient.setCellStyle(styleBottomHead);

        Cell cellCodeWorker = rowTypes.createCell(1);
        cellCodeWorker.setCellValue(codeWorker);
        cellCodeWorker.setCellStyle(styleBottomHead);

        Cell cellCodeProduct = rowTypes.createCell(2);
        cellCodeProduct.setCellValue(codeProduct);
        cellCodeProduct.setCellStyle(styleBottomHead);

        Cell cellDate = rowTypes.createCell(3);
        cellDate.setCellValue(date);
        cellDate.setCellStyle(styleBottomHead);


        sheetSales.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));


        sheetSales.setColumnWidth(0, 5000);
        sheetSales.setColumnWidth(1, 5000);
        sheetSales.setColumnWidth(2, 4500);
        sheetSales.setColumnWidth(3, 4500);
        sheetSales.setColumnWidth(4, 4000);
        sheetSales.setColumnWidth(5, 3500);


    }
    private void fillingTableReportSales(Sheet sheetSales, Sale[] sales){
        int rowCount = 2;
        for(int i = 0; i < sales.length; i++){
            Sale sale = sales[i];
            boolean isEnd = (i == sales.length-1);

            Row newRow = sheetSales.createRow(rowCount);

            Cell addCodeClient = newRow.createCell(0);
            addCodeClient.setCellValue(sale.getCodeSale());
            addCodeClient.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));

            Cell addCodeWorker = newRow.createCell(1);
            addCodeWorker.setCellValue(sale.getCodeWorker());
            addCodeWorker.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));

            Cell addCodeProduct = newRow.createCell(2);
            addCodeProduct.setCellValue(sale.getCodeClient());
            addCodeProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));

            Cell addDate = newRow.createCell(3);
            addDate.setCellValue(sale.getDate());
            addDate.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, isEnd));

            rowCount += 1;
        }

        Row endRowAllLine = sheetSales.createRow(rowCount);
        Cell cellTextAllLine = endRowAllLine.createCell(2);
        cellTextAllLine.setCellValue("Всего записей: ");
        cellTextAllLine.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,true));

        Cell cellAllLine = endRowAllLine.createCell(3);
        cellAllLine.setCellValue(sales.length);
        cellAllLine.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,false));

    }


    public void generateRemainProductsReports() throws IOException{
        Warehouse warehouse = Warehouse.getInstance();
        String nameSheet = "Отчет по остаткам товара в мага";

        String pathNameDirectoryReports = pathNameFile_Reports + "\\reports";
        UtilMethodsDocuments.checkingExistDirectory(pathNameDirectoryReports);



        try{
            Workbook rBook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));

            if(rBook.getSheetIndex(nameSheet) != -1){
                int idSheet = rBook.getSheetIndex(nameSheet);
                rBook.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx");
                rBook.write(fos);
                fos.close();
                rBook.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));
            }

        }catch (EmptyFileException e){
            System.out.println("File was empty!");

            if(workbook.getSheetIndex(nameSheet) != 1){
                int idSheet = workbook.getSheetIndex(nameSheet);
                workbook.removeSheetAt(idSheet);

                FileOutputStream fos = new FileOutputStream(pathNameDirectoryReports + "\\reports.xlsx");
                workbook.write(fos);
                fos.close();

                workbook = new XSSFWorkbook(new FileInputStream(pathNameDirectoryReports + "\\reports.xlsx"));
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }

        try{
            Sheet sheetRemain = workbook.createSheet(nameSheet);
            Controller.setFirstMessage(false);
            Controller.setSecondMessage(false);
            createStructureTableRemains(sheetRemain);
            fillingTableReportRemains(sheetRemain, warehouse);
            writeDataReports(pathNameDirectoryReports);
        }catch (IllegalArgumentException e){
            System.err.println("IllegalArgumentException in generateRemainProductsReports");
            e.printStackTrace();
        }
    }

    private void createStructureTableRemains(Sheet sheetRemains){
        String headTable = "Отчет по остаткам товара в магазине " + dateMake;
        String productName = "Товар";
        String quantity = "Количество";

        Row rowHead = sheetRemains.createRow(0);
        rowHead.setHeightInPoints(45);
        Row rowTypes = sheetRemains.createRow(1);

        Cell cellHead = rowHead.createCell(0);
        cellHead.setCellValue(headTable);
        cellHead.setCellStyle(getStyleForOnlyHead());
        Cell cellRightHead = rowHead.createCell(2);
        cellRightHead.setCellStyle(getStyleForHeadRightBorder());

        CellStyle styleBottomHead = UtilMethodsDocuments.getStyleForHeadBottom(workbook);

        Cell cellNameProduct = rowTypes.createCell(0);
        cellNameProduct.setCellValue(productName);
        cellNameProduct.setCellStyle(styleBottomHead);

        Cell cellQuantity = rowTypes.createCell(1);
        cellQuantity.setCellValue(quantity);
        cellQuantity.setCellStyle(styleBottomHead);

        sheetRemains.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        sheetRemains.setColumnWidth(0, 13000);
        sheetRemains.setColumnWidth(1, 5000);
    }
    private void fillingTableReportRemains(Sheet sheetRemains, Warehouse warehouse){
        int rowCount = 2;
        Map<CellPhone, Integer> map = warehouse.getCellPhones();

        Cell addNameProduct = null;
        Cell addQuantity = null;

        for(Map.Entry<CellPhone, Integer> tmp: map.entrySet()){
            CellPhone phone = tmp.getKey();
            String productName = phone.getShortName() + ", " + phone.getPrice() + " руб.";

            Row newRow = sheetRemains.createRow(rowCount);

            addNameProduct = newRow.createCell(0);
            addNameProduct.setCellValue(productName);
            addNameProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, false));

            addQuantity = newRow.createCell(1);
            addQuantity.setCellValue(tmp.getValue());
            addQuantity.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, false));

            rowCount += 1;
        }

        if(addNameProduct != null) {
            addNameProduct.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, true));
        }
        if(addQuantity != null) {
            addQuantity.setCellStyle(UtilMethodsDocuments.getStyleForData(workbook, true));
        }

        Row endRowAllLine = sheetRemains.createRow(rowCount);
        Cell cellTextAllLine = endRowAllLine.createCell(0);
        cellTextAllLine.setCellValue("Всего записей: ");
        cellTextAllLine.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,true));

        Cell cellAllLine = endRowAllLine.createCell(1);
        cellAllLine.setCellValue(map.size());
        cellAllLine.setCellStyle(UtilMethodsDocuments.getStyleForResultsReport(workbook,false));
    }



    private CellStyle getStyleForOnlyHead(){
        CellStyle cellStyleHeadText = workbook.createCellStyle();
        cellStyleHeadText.setFont(getFontForHead());

        cellStyleHeadText.setWrapText(true);

        cellStyleHeadText.setAlignment(HorizontalAlignment.CENTER);
        cellStyleHeadText.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyleHeadText;
    }

    private CellStyle getStyleForHeadRightBorder(){
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.MEDIUM);
        return style;
    }

    private Font getFontForHead(){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 15);

        return font;
    }
    

    private void getPathNameReportsFile(){
        pathNameFile_Reports = StorageFileNames.mainDirectory;
        System.out.println("PathNameFile_Reports: " + pathNameFile_Reports);
    }

    private void getTotalWorkbookReports(){
        workbook = new XSSFWorkbook();
    }

    private void updateDate(){
        dateMake = UtilMethodsDocuments.updateDate();
    }

    public String getPathNameFile_Reports(){
        return pathNameFile_Reports + "\\reports\\reports.xlsx";
    }

    private static String pathNameFile_Reports;
    private Workbook workbook;
    private String dateMake;
}
