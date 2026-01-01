package rahulshettyacademy.tests;


import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {

        String excelPath = "C://Users//vedan//loginData.xlsx";  // <-- update if needed

        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("loginData");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        // Start from row 1 to skip header
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
 
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);

                if (cell.getCellType() == CellType.STRING) {
                    data[i - 1][j] = cell.getStringCellValue();
                
                } else {
                    data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}
