
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class workChartElements {
	 
		 String[] workChartNameElements = new String[] { };//ZAchem eta stroka?
		
		 static Map<String, List<Chart>> workChart = new HashMap<>();
		 
	//	List workChartAll = new List();// cto eto bilo??
		 static Map<String, List<Double>> allWorkCharts = new HashMap<>();//sootvetstvie nasvania signala i kolichestva kotirovok dla ego podscheta
		 static QuotPrinter quotPrinter;

		private static List<Chart> chartListAUD;
		
//rabochij chart AUD		
		public static void putInWorkChart(String chartName, List<Chart> chartIn) {
			workChart.put(chartName, chartIn);
			Set<String> keys = workChart.keySet();
		//	Set<String> allCharts = allWorkCharts.keySet();
						
			System.out.println(keys);
			
		//	quotPrinter.printChartListObjects(allWorkCharts.....allWorkCharts);
			 System.out.println( "chartIn!!!!!! " );	
		}
// bolshoj map iz 3 name i 3h rabochih chartov
		public static void put(String chartName, List<Double> chartListQuot) {
			allWorkCharts.put(chartName, chartListQuot);
			Set<String> keys = allWorkCharts.keySet();
			System.out.println("Keys: " +keys);
			//	quotPrinter.printChartListObjects(allWorkCharts.....allWorkCharts);
			 System.out.println( "chartIn!!!!!! " + allWorkCharts.values().size());	
			 allWorkCharts.values().size();
		}

//vibor kotirovok AUD  napr		
	static List<Chart> selectChart(List<Chart> chartListIn, String quotName) {
		workChart.get(quotName);

		for (int i = 0; i < workChart.get(quotName).size(); i++) {
			System.out.println("PriceClose= " + workChart.get(quotName).get(i).toString());
		}
		return chartListAUD;
	}
	
	static void zeigen() { // vivod na pechat skoka kotirovok kakomu signalu
							// nado
		// for (Entry<String, List<Chart>> pair :workChart.entrySet())
		for (Entry<String, List<Double>> pair : allWorkCharts.entrySet()) {
			String key = pair.getKey();
			List<Double> value = pair.getValue();
			System.out.println("workArray Ima!" + key + ":" + value + "quotChart");
			// System.out.println("workArray Ima!" + key );
		}
		System.out.println("size work: " + allWorkCharts.size());
		// allWorkCharts.
	}
}
