package com.framework.utils;

import com.framework.exceptions.FrameworkException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtil {

  public static List<Map<String, String>> getTestDetails(String excelFilePath, String sheetName) {
    List<Map<String, String>> list = null;

    try (FileInputStream fs = new FileInputStream(excelFilePath)) {

      XSSFWorkbook workbook = new XSSFWorkbook(fs);
      XSSFSheet sheet = workbook.getSheet(sheetName);

      int lastRowNum = sheet.getLastRowNum();
      int lastColNum = sheet.getRow(0).getLastCellNum();

      Map<String, String> map = null;
      list = new ArrayList<>();

      for (int i = 1; i <= lastRowNum; i++) {
        map = new HashMap<>();
        for (int j = 0; j < lastColNum; j++) {
          String key = sheet.getRow(0).getCell(j).getStringCellValue();
          String value = sheet.getRow(i).getCell(j).getStringCellValue();
          map.put(key, value);
        }
        list.add(map);
      }

    } catch (FileNotFoundException e) {
      System.err.println("Error: The Excel file was not found.");
      e.printStackTrace();  // This will print the stack trace to the console
      throw new FrameworkException("Excel file you are trying to read is not found.",
        e);  // Rethrow the exception
    } catch (IOException e) {
      System.err.println("Error: Some IO exception occurred while reading the Excel file.");
      e.printStackTrace();  // This will print the stack trace to the console
      throw new FrameworkException("An IO exception occurred while reading the Excel file.",
        e);  // Rethrow the exception
    }
    System.out.println(list);
    return list;
  }

}