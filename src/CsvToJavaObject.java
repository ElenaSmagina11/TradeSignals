

/**
 * @author Nagesh Chauhan
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CsvToJavaObject {

	public static void convertCsvToJava() {
		String csvFileToRead = "Resourses/XAUUSD1440-short.csv";
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
	//	List carList = new ArrayList();
		List chartList = new ArrayList();

		try {

			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null) {
		
				String[] chart = line.split(splitBy);// split on comma(',')
				Chart chartObject = new Chart();// create car object to store values
			
				chartObject.setDate(chart[0]);// add values from csv to car object
				chartObject.setTime(chart[1]);
				chartObject.setPriceOpen(chart[2]);
				chartObject.setPriceHigh(chart[3]);
				chartObject.setPriceLow(chart[4]);
				chartObject.setPriceClose(chart[5]);
				chartObject.setVolume(chart[6]);
			
				chartList.add(chartObject);
			}
			printChartListNeu(chartList);// print values stored in carList

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void printChartListNeu(List<Chart> chartListToPrint) {
		for (int i = 0; i < chartListToPrint.size(); i++) {
			System.out.println("ChARtS as Object!!![date= " +  chartListToPrint.get(i).getDate()
					+ " , time= " + chartListToPrint.get(i).getTime()
					+ " , PriceOpen= " +chartListToPrint.get(i).getPriceOpen()
					+ " , PriceHigh= "+ chartListToPrint.get(i).getPriceHigh()
					+ " , PriceLow= "+ chartListToPrint.get(i).getPriceLow()
					+ " , PriceClose= "+ chartListToPrint.get(i).getPriceClose()
					+ " , Volume= "+ chartListToPrint.get(i).getVolume()
					+ "]");
			
		}
	}
}
