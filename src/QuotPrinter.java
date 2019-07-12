import java.util.ArrayList;
import java.util.List;

public class QuotPrinter {

	List<Double> chartListInDouble = new ArrayList<Double>();
	
	
    // vivod  na peshat kak Object
	public static void printChartListObjects(List<Chart> chartListIn) {
		for (int i = 0; i < chartListIn.size(); i++) {
			System.out.println("ChARtS as Object!!![date= " + chartListIn.get(i).getDate() + " , time= "
					+ chartListIn.get(i).getTime() + " , PriceOpen= " + chartListIn.get(i).getPriceOpen()
					+ " , PriceHigh= " + chartListIn.get(i).getPriceHigh() + " , PriceLow= "
					+ chartListIn.get(i).getPriceLow() + " , PriceClose= "
					+ chartListIn.get(i).getPriceClose() + " , Volume= " + chartListIn.get(i).getVolume()
					+ "]");

		}
	}

	// vivod na pechat zen

	public static void printChartList(List<Chart> chartListToPrint) {

		for (int i = 0; i < chartListToPrint.size(); i++) {
			System.out.println("PriceClose= " + chartListToPrint.get(i).getPriceClose() + "   PriceOpen= "
					+ chartListToPrint.get(i).getPriceOpen());
		}
	}
	
	public static void cloneChartList(String chartName, List<Chart> chartIn ){
		workChartElements.putInWorkChart(chartName, chartIn) ;
//	return chartOut;
	
	}
	
	public List<Double> listToDouble(List<Chart> chartListI) {// sohranaem toka
		// PriceClose
		// dla
		// dalneishei
		// obrabotki

for (int i = 0; i < chartListI.size(); i++) {
// System.out.println("PriceClose= " +
// chartListI.get(i).getPriceClose());
double d = Double.parseDouble(chartListI.get(i).getPriceClose());
chartListInDouble.add(d);
System.out.println("PriceClose= " + chartListInDouble.get(i));
}
return chartListInDouble;
}
}
