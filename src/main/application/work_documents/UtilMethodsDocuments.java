package main.application.work_documents;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilMethodsDocuments {

    public static CellStyle getStyleForHeadBottom(Workbook workbook){
        CellStyle styleHeadTable = workbook.createCellStyle();
        styleHeadTable.setBorderTop(BorderStyle.MEDIUM);
        styleHeadTable.setBorderRight(BorderStyle.THIN);
        styleHeadTable.setBorderBottom(BorderStyle.THIN);

        styleHeadTable.setFont(getFontForTypes(workbook));

        styleHeadTable.setAlignment(HorizontalAlignment.CENTER);
        styleHeadTable.setVerticalAlignment(VerticalAlignment.CENTER);
        return styleHeadTable;
    }

    public static CellStyle getStyleForData(Workbook workbook, boolean isEnd){
        CellStyle style = workbook.createCellStyle();
        style.setBorderRight(BorderStyle.THIN);
        if(isEnd){
            style.setBorderBottom(BorderStyle.THIN);
        }
        style.setFont(getFontForTypes(workbook));
        return style;
    }

    public static CellStyle getStyleForResultsReport(Workbook workbook, boolean isText){
        CellStyle style = workbook.createCellStyle();
        style.setFont(getFontForTypes(workbook));
        if(isText){
            style.setBorderLeft(BorderStyle.THIN);
        }else {
            style.setBorderRight(BorderStyle.THIN);
        }
        style.setBorderBottom(BorderStyle.THIN);
        return style;
    }

    public static String updateDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Date today = new Date();

        return format.format(today);
    }

    private static Font getFontForTypes(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 12);

        return font;
    }

    public static void checkingExistDirectory(String pathNameDirectory){
        File directory = new File(pathNameDirectory);
        if(!directory.exists()){
            directory.mkdir();
        }
    }

}
