

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Nagesh Chauhan
 * 
 */
public class ReadCsv {
	public void readCsv() {
		String csvFileToRead = "Resourses/XAUUSD1440-short.csv";
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null) {

				String[] chart = line.split(splitBy);
				System.out.println("ChARtS as String from CSV[date= " + chart[0] + " , time= "
						+ chart[1] + " , priceOpen= " + chart[2] + " , priceHigh= "
						+ chart[3] + " , priceLow= " + chart[4] 
						+ " , priceClose= " + chart[5] + " , volume= " + chart[6]+"]");

			}

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

		System.out.println("Done with reading CSV");
	}
}
