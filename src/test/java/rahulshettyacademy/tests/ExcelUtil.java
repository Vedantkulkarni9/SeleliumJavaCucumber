
package rahulshettyacademy.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static ArrayList<String> getData(String testcaseName) throws IOException {

        ArrayList<String> data = new ArrayList<>();

        FileInputStream fis = new FileInputStream("C://Users//vedan//demodata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {

                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();

                Iterator<Cell> ce = firstRow.cellIterator();

                int colIndex = 0;
                int k = 0;

                while (ce.hasNext()) {
                    Cell cell = ce.next();
                    if (cell.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        colIndex = k;
                    }
                    k++;
                }

                while (rows.hasNext()) {
                    Row r = rows.next();
                    Cell testCell = r.getCell(colIndex);

                    if (testCell != null
                            && testCell.getCellType() == CellType.STRING
                            && testCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {

                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                data.add(c.getStringCellValue());
                            } else {
                                data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}
