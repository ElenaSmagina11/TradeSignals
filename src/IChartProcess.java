import java.util.ArrayList;
import java.util.List;


public interface IChartProcess {
	List<Chart> chartList = new ArrayList<Chart>();
	List<Double> chartListInDouble = new ArrayList<Double>();
public void printChartList(List<Chart> chartListToPrint);//pechat kotirovok
public List<Double> listToDoublePriceClose(List<Chart> chartListI);//perevod string->double tolko PriceClose-tak ge kak v klasse FileBrowser
List<Double> toReadInChart();//chtenie FileBrauserom v Chart
void cloneChartList();//clone s opr nasvaniem fila dla dannoj kotiri

}
