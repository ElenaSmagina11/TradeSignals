import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ComboBoxModel;


public class SignalElements {
	// public static final ComboBoxModel<String> signalElementsArray = null;

	//public static void main(String[] args) {
		 
	 
	final static String[] SIGNALSarray = new String[] { " Who is faster?", " SIGNAL 2", " SIGNAL 3" };
	final List signalElementsArray2 = new List();
	final static Map<String, Integer> allSignalElements = new HashMap<>();//sootvetstvie nasvania signala i kolichestva kotirovok dla ego podscheta

//	Map<String, Integer> allSignalElements = new Map<String, Integer>();
	static void toHashMap(){
	allSignalElements.put(SIGNALSarray[0], 3);//dla signala 1 nado zagrusit 3 st kotirovok
	allSignalElements.put(SIGNALSarray[1], 2);
	allSignalElements.put(SIGNALSarray[2], 4);
	 Set<String> keys = allSignalElements.keySet();
	 System.out.println("Signali: " + keys);
	 zeigen();
	}
//	allSignalElements.put(" SIGNAL 2", 2);
//	allSignalElements.put(" SIGNAL 3", 4);
	
//	System.out.println(allSignalElements.get(" SIGNAL 3"));
//	 Set<String> keys = allSignalElements.keySet();
//	 String key = ((Object) allSignalElements).getKey(1);
//	 System.out.println(keys);
//	 keys.toArray();
	 static void zeigen(){ //vivod na pechat skoka kotirovok kakomu signalu nado
	 for (Entry<String, Integer> pair :allSignalElements.entrySet())
	 {
	 String key = pair.getKey();
	 int value = pair.getValue();
	 System.out.println("QQQQQQ!" + key + ":" + value + "stuk quotes");
	 }
	}
//	 System.out.printf("TO: " + signalElementsArray2.getItem(0));
	 


 }
