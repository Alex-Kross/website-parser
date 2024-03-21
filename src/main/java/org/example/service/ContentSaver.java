package org.example.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.entity.Product;
import org.example.entity.TypeProduct;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ContentSaver {
    private static final String FILE_NAME = "src/main/resources/Content.xlsx";
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    public void saveProduct(Map<TypeProduct, List<Product>> products){
        for (Map.Entry<TypeProduct, List<Product>> product : products.entrySet()) {
            // create sheet
            XSSFSheet sheet = workbook.createSheet(product.getKey().name());
            int rowNum = 0;

            //create table data
            for (Product itemProduct : product.getValue()) {
                Row row = sheet.createRow(rowNum++);
                Object[] attributes = itemProduct.getAttributes();
                int colNum = 0;
                for (int i = 0; i < attributes.length; i++) {
                    Cell cell = row.createCell(colNum++);
                    if (attributes[i] instanceof String)
                        cell.setCellValue((String) attributes[i]);
                    else if (attributes[i] instanceof Double)
                        cell.setCellValue((Double) attributes[i]);
                }
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

