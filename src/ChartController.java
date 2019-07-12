import java.util.ArrayList;
import java.util.List;


public class ChartController {
	
	
	private static List<Chart> chartPriseClose;
	List<Double> chartListInDouble = new ArrayList<Double>();

	public List<Double> stringToDouble(List<Chart> chartListIn) {// sohranaem	PriceClose														// toka
		
		for (int i = 0; i < chartListIn.size(); i++) {
			// System.out.println("PriceClose= " +
			// chartListI.get(i).getPriceClose());
			double d = Double.parseDouble(chartListIn.get(i).getPriceClose());
			chartListInDouble.add(d);
			System.out.println("PriceClose= " + chartListInDouble.get(i));
		}
		return chartListInDouble;
	}

	  static List<Chart>  selectChart(List<Chart> chartListToCount, String chartName) {
	//	allWorkCharts.get(chartName) ;
		
		return chartPriseClose;
		
	}
	
	
	
//vivod na pechat	
public void printChartList(List<Chart> chartListToPrint) {
		
		for (int i = 0; i < chartListToPrint.size(); i++) {
			System.out.println("PriceClose= " + chartListToPrint.get(i).getPriceClose()+ "   PriceOpen= "+ chartListToPrint.get(i).getPriceOpen() );
		}
	}



}
