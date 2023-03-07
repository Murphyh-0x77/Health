package com.liutao.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class POITest {

    public void test() throws Exception{
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("F:\\7、传智健康项目\\资料-传智健康项目\\day05\\资源\\预约设置模板文件\\ordersetting_template.xlsx")));
        XSSFSheet sheetAt = excel.getSheetAt(1);
        for (Row cells : sheetAt) {
            for (Cell cell : cells) {
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }

    public void test2() throws Exception{
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("F:\\7、传智健康项目\\资料-传智健康项目\\day05\\资源\\预约设置模板文件\\ordersetting_template.xlsx")));
        XSSFSheet sheetAt = excel.getSheetAt(1);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }


    public void test3() throws IOException {
        XSSFWorkbook excel = new XSSFWorkbook();

        XSSFSheet newSheet = excel.createSheet("newSheet");

        XSSFRow row = newSheet.createRow(0);

        row.createCell(0).setCellValue("111");
        row.createCell(1).setCellValue("222");
        row.createCell(2).setCellValue("333");

        XSSFRow row1 = newSheet.createRow(1);
        row1.createCell(0).setCellValue("111111");
        row1.createCell(1).setCellValue("222222");
        row1.createCell(2).setCellValue("333333");

        FileOutputStream outputStream = new FileOutputStream(new File("F:\\7、传智健康项目\\资料-传智健康项目\\day05\\资源\\预约设置模板文件\\test.xlsx"));
        excel.write(outputStream);
        outputStream.flush();
        excel.close();
    }
}
