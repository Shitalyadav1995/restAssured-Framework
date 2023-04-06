package common_method;

import java.io.FileInputStream;
import java.lang.Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {
	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException 
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		// step 1 locate excel data file,by creating the object of fileinputstream
		FileInputStream fis = new FileInputStream("C:\\Users\\K12\\Desktop\\test_data.xlsx");

		// step 2 create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// step 3 access the desired sheet
		// step 3.1 fetch the count of sheets available in the excel file
		int countofSheet = workbook.getNumberOfSheets();

		// step 3.2 fetch the name of sheet and compare again desired sheet name
		for (int i = 0; i < countofSheet; i++) {
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testSheetName)) {
				// step 4 access the sheet and iterate through rows to fetch the column in which
				// test case name
				XSSFSheet Sheet = workbook.getSheetAt(i);

				// step 4.1 create iterator for rows
				Iterator<Row> Rows = Sheet.iterator();
				Row firstRow = Rows.next();
				// step 4.2 create iterator for cells
				Iterator<Cell> Cells = firstRow.cellIterator();
				int j = 0;
				int tc_column = 0;
				// step 4.3 Read the cell values of Row no1 to compare against the test case
				// name
				while (Cells.hasNext()) {
					Cell cellvalue = Cells.next();
					// System.out.println(cellvalue);
					if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name")) {
						tc_column = j;
						// System.out.println(tc_column);
					}
					j++;
				}
				// step 5 fetch the data for designated test case
				while (Rows.hasNext()) {
					Row dataRow = Rows.next();

					if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> dataCellvalue = dataRow.cellIterator();

						while (dataCellvalue.hasNext())
						{
							Cell dataOfCell = dataCellvalue.next();
							// Method 1 ---- To Extract the cell value by using try and catch
							try 
							{
								String testdata = dataOfCell.getStringCellValue();
//								System.out.println(testdata);
								arrayData.add(testdata);
							}
							catch (IllegalStateException e)
							{
								int inttestdata = (int) dataOfCell.getNumericCellValue();
								String stringtestData = Integer.toString(inttestdata);
//				         		System.out.println(stringtestData);
								arrayData.add(stringtestData);
							}
							
							
							// Method 2 To Extract the cell value by using if and else
//						CellType datatype = dataOfCell.getCellType();
//					 if (datatype.toString() == "NUMERIC")
//				 {
//					 int inttestData = (int) dataOfCell.getNumericCellValue();
//				 System.out.println(inttestData);	
//							String stringtestData = Integer.toString(inttestData);
//						arrayData.add(stringtestData);
//			}
//			 else if(datatype.toString() == "STRING");
//				 {
//						 String testData = dataOfCell.getStringCellValue();
//				         System.out.println(testData);
//				       	arrayData.add(stringtestData);

//		     	 }
				       	//Method 3 ---Extract the data by converting it into String
//					String testData = dataCellvalue.next().toString().replaceAll("\\.\\d+$","");
//				System.out.println(testData);
				       	
//				//Method 4 by using Data format
//				DataFormatter format = new DataFormatter();
//				String testdata = format.formatCellValue(dataCellvalue.next());
//				System.out.println(testData);
						}
					}
				}
			}
		}
		return arrayData;
	}

}