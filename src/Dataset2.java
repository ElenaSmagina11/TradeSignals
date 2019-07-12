import java.util.Iterator;
import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Dataset2 

{
	static TimeSeriesCollection allDataSeries = new TimeSeriesCollection();
   // public static TimeSeries createOneDataset(String name,List<Double> list) //ROBIT!!
	
	 public static TimeSeries createOneDataset(String name,List<Double> list) //ROBIT!!
    
    {
    	 final TimeSeries s1 = new TimeSeries(name);
    	 System.out.println("name: " + name);
    	 Iterator<Double> iterator = list.iterator();
    	 
    	 while (iterator.hasNext())
    	 { 
    		 Double quot = iterator.next();
    		 s1.addOrUpdate( new Day(11, 5, 2017), quot);
    	 System.out.println("quot :" +quot );
    	 System.out.println("day :" +new Day(11, 5, 2017) );
    	
    	 }
 //   	}
//´
    	 final TimeSeriesCollection dataset2 = new TimeSeriesCollection();
//	
//    dataset2.addSeries(s1);
//    dataset2.addSeries(s2);
//    dataset2.addSeries(s3);
    
//    return s1;
   	 return s1;
    }
	 
	 public static TimeSeriesCollection createAllDataset(TimeSeries s){
		 allDataSeries.addSeries(s);
			return allDataSeries;
}
}