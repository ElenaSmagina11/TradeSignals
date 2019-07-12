

/**
 * @author Nagesh Chauhan
 * 
 */
public class App {
	public static void main(String[] args) {

		// reading data from a csv file
		System.out.println("Reading data from csv :");
//		ReadCsv csvToRead = new ReadCsv();
//		 csvToRead.readCsv();
//		ReadCsv readCsvChart = new ReadCsv();
//		 readCsvChart.readCsv();


		// reading data from a csv file and convert to java object
		System.out.println("Reading data from csv and convert to java object:");
		CsvToJavaObject csvToJavaObject = new CsvToJavaObject();
		csvToJavaObject.convertCsvToJava();
//		CsvToJavaObject newObject = new CsvToJavaObject();
//		newObject.convertCsvToJava();
	}
}
