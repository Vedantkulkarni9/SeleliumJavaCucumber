package rahulshettyacademy.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

//Identify Testcases coloum by scanning the entire 1st row
//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
//after you grab purchase testcase row = pull all the data of that row and feed into test

	public ArrayList<String> getData(String testcaseName) throws IOException {

	    ArrayList<String> a = new ArrayList<>();

	    FileInputStream fis = new FileInputStream("C://Users//vedan//demodata.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);

	    int sheets = workbook.getNumberOfSheets();

	    for (int i = 0; i < sheets; i++) {

	        if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {

	            XSSFSheet sheet = workbook.getSheetAt(i);

	            Iterator<Row> rows = sheet.iterator();
	            Row firstrow = rows.next();

	            Iterator<Cell> ce = firstrow.cellIterator();

	            int k = 0;
	            int coloumn = 0;

	            while (ce.hasNext()) {
	                Cell value = ce.next();

	                if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
	                    coloumn = k;
	                }
	                k++;
	            }

	            // Scan entire TestCases column
	            while (rows.hasNext()) {

	                Row r = rows.next();
	                Cell testCell = r.getCell(coloumn);

	                // FIX: Check if null + check if text
	                if (testCell != null &&
	                    testCell.getCellType() == CellType.STRING &&
	                    testCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {

	                    // Pull the entire row data
	                    Iterator<Cell> cv = r.cellIterator();

	                    while (cv.hasNext()) {
	                        Cell c = cv.next();

	                        if (c.getCellType() == CellType.STRING) {
	                            a.add(c.getStringCellValue());
	                        } else {
	                            a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
	                        }
	                    }
	                }
	            }
	        }
	    }

	    workbook.close();
	    fis.close();
	    return a;
	}
	public static void main(String[] args) throws IOException { // TODO Auto-generated method stub
		dataDriven d = new dataDriven();
	    ArrayList<String> data = d.getData("Login");  // Your test case name

	    for (String value : data) {
	        System.out.println(value);
	    }
		}
	}

