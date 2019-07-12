

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import javax.swing.*;

import org.decimal4j.util.DoubleRounder;

public class QuotLoader {
	
//	private static int BARS = 0;
	private JTextField jtfStatus;
	private JFileChooser fileChooser;
	public File fileName;
	FileReader reader = null;
	
//	List chartList1 = new ArrayList();
	List chartListAsObject = new ArrayList<Chart>();//List of JavaObjects kotirovki 
	HashMap<String,List> workCharts ;
	//List<Double> chartListInDouble = new ArrayList<Double>();
	//private List<Double> chartListQuot= new ArrayList<Double>();

	private Chart chartObject;
	private Chart chart;
	private static final int MA_Period = 4;
		
	
	
//	private File file;
	public QuotLoader (JTextField jtfStatus){
		this.jtfStatus = jtfStatus;
	}
//NewReader
	public  Chart FileToChartObject(){
	//	chooseFile(String title,String startDir);
	//	createChart();
		return createChart();
	}
	
	public  String getQuotName(){
		 String quotName = fileName.getName().substring(0,fileName.getName().lastIndexOf('.'));
			return quotName;
		}
//Method "chooseText"
	public  List<Double> chooseFileToRead(){
		
//		File file = null;
//		fileChooser = new JFileChooser( new File(startDir));
//		int returnVal = fileChooser.showDialog(null, title);
//		if (returnVal == JFileChooser.APPROVE_OPTION){
//			file = fileChooser.getSelectedFile();
//			fileName = file;
//			}
//		else if(returnVal == JFileChooser.CANCEL_OPTION){
//			return null;
//		}
//		FileReader reader = null;
//		try {
//			reader = new FileReader(file);
//			}
//		catch (FileNotFoundException fnfe) {
//			System.out.println("FileNotFoundException " + fnfe.getMessage());
//			}
//		chooseFile("Datei waehlen", "./");

		return toReadInChart();
	
		}
	public static void printChartListObjects(List<Chart> chartIn) {
		for (int i = 0; i < chartIn.size(); i++) {
			System.out.println("ChARtS as Object!!![date= " +  chartIn.get(i).getDate()
					+ " , time= " + chartIn.get(i).getTime()
					+ " , PriceOpen= " +chartIn.get(i).getPriceOpen()
					+ " , PriceHigh= "+ chartIn.get(i).getPriceHigh()
					+ " , PriceLow= "+ chartIn.get(i).getPriceLow()
					+ " , PriceClose= "+ chartIn.get(i).getPriceClose()
					+ " , Volume= "+ chartIn.get(i).getVolume()
					+ "]");
			
		}
	}
	
	//vmesto etogo metoda nado sdelat massiv iz string -> double
	public void printPriceCloseList(List<Chart> chartListIn) {
		List<Double> chartListQuot= new ArrayList<Double>();
		for (int i = 0; i < chartListIn.size(); i++) {
			System.out.println("PriceClose= " + chartListIn.get(i).getPriceClose());
		}
	}
	
	public List<Double> listToDouble(List<Chart> chartListIn) {//sohranaem toka PriceClose dla dalneishei obrabotki
		List<Double> chartListQuot = new ArrayList<Double>();
		for (int i = 0; i < chartListIn.size(); i++) {
		//	System.out.println("PriceClose= " + chartListI.get(i).getPriceClose());
			double d = Double.parseDouble(chartListIn.get(i).getPriceClose());
			chartListQuot.add(d);
		//	System.out.println("PriceClose from  listToDouble= " + chartListInDouble.get(i));
		}
		return chartListQuot;
	}
	
	///________________________
	
		
	List<Double>	averageForSMAPeriod(List<Double> chartListInDoubleIn){//if bars<MA-Period, to oshibku ne vidaet_ ptosto massiv pustoj
		int BARS = chartListInDoubleIn.size();
		System.out.println("bars ="+BARS );
	//	chartListQuot.clear();
		List<Double> chartListQuot= new ArrayList<Double>();
		System.out.println("chartListQuot.size() posle CLEAR ="+ chartListQuot.size());
		
		double sum = 0 ;
		double sma_unit = 0;
		
		for (int i = 0; i< BARS-MA_Period+1; i++){
	//	while (pos >=0) {
		for (int j = 0; j < MA_Period; j++) { //eto sa 14 periodov
		//sum += (chartListInDoubleIn.get(j)+chartListInDoubleIn.get(j++));
//		System.out.println("chartListInDoubleIn.get(j+i)= "+chartListInDoubleIn.get(j+i) );
		sum += chartListInDoubleIn.get(j+i);
		}
//		System.out.println("sum  za 14= "+sum );
		sma_unit = DoubleRounder.round(sum/MA_Period, 3);//okruglennoe do 3 snac posle comma zne MA
		chartListQuot.add(sma_unit);
		System.out.println("MA="+i +" :"+ chartListQuot.get(i));
//		chartListInDouble.add(sma_unit);
//		System.out.println("MA="+i +" :"+ chartListInDouble.get(i));
		sum = 0;
		}
	//return  chartListInDouble;
		System.out.println("chartListQuot.size()="+ chartListQuot.size());
		BARS = 0;
		return  chartListQuot;//nado etot vozvrashennij list v work map sagnat
	}
		
//vibor fila-ROBIT!!!
	FileReader chooseFile(String title,String startDir){
	
	File file = null;
	fileChooser = new JFileChooser( new File(startDir));
	int returnVal = fileChooser.showDialog(null, title);
	if (returnVal == JFileChooser.APPROVE_OPTION){
		file = fileChooser.getSelectedFile();
		fileName = file;
		}
	else if(returnVal == JFileChooser.CANCEL_OPTION){
		return null;
	}
	FileReader reader = null;
	try {
		reader = new FileReader(file);
		}
	catch (FileNotFoundException fnfe) {
		System.out.println("FileNotFoundException " + fnfe.getMessage());
	}
	System.out.println("reader " + reader);
	return reader;
	}
	
	
//New chtenie objekta chart
	Chart createChart(){
		BufferedReader bufReader = new BufferedReader(chooseFile("Datei waehlen", "./"));
		String line = null;
		String splitBy = ",";
		//StringBuffer sb = new StringBuffer();
			
			try {
				while(( line = bufReader.readLine()) != null){
					
//	sb.append(str);
					// split on comma(',')
					String[] charts = line.split(splitBy);
					// create chart object to store values
					Chart chartObject = new Chart();
					// add values from csv to car object
					chartObject.setDate(charts[0]);
					chartObject.setTime(charts[1]);
					chartObject.setPriceOpen(charts[2]);
					chartObject.setPriceHigh(charts[3]);
					chartObject.setPriceLow(charts[4]);
					chartObject.setPriceClose(charts[5]);
					chartObject.setVolume(charts[6]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		//	sb.append(System.getProperty("line.separator"));
			// v etom meste nado str postrocno v massiv
		//	chartList.add(chartObject);
			return chartObject;
	}

	
/// GLAVNIJ METHOD CTENIE CSV V LIST OBJEKTOV	
//	List<Double> toReadInChart(){ //chitaet csv file v java object
	List toReadInChart(){ //chitaet csv file v java object
	BufferedReader bufReader = new BufferedReader(chooseFile("Datei waehlen", "./"));
	String name = fileName.getName().substring(0,fileName.getName().lastIndexOf('.'));
	System.out.println( "Name = " + name );
	chartListAsObject.clear();
		
	String line = null;
	String splitBy = ",";
	//StringBuffer sb = new StringBuffer();
	
	try {
		while(( line = bufReader.readLine()) != null){
			
	//	sb.append(str);
			// split on comma(',')
			String[] charts = line.split(splitBy);
			// create chart object to store values
			Chart chartObject = new Chart();
			// add values from csv to car object
			chartObject.setDate(charts[0]);
			chartObject.setTime(charts[1]);
			chartObject.setPriceOpen(charts[2]);
			chartObject.setPriceHigh(charts[3]);
			chartObject.setPriceLow(charts[4]);
			chartObject.setPriceClose(charts[5]);
			chartObject.setVolume(charts[6]);
			
	//	sb.append(System.getProperty("line.separator"));
		// v etom meste nado str postrocno v massiv
		chartListAsObject.add(chartObject);
		}
//	 System.out.println("volume " +((Chart) chartListAsObject.get(0)).getVolume());
		
	//	listToDouble(chartList);//sdes shas toka PriceClose  uberem poka toka close zenu

	//	printChartListObjects(chartListAsObject);
		bufReader.close();
		 File myFile = new File(name);
		 myFile.createNewFile();
//		
//		 try {
//			 chart.createObjectChartList(name);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		}
	catch (IOException ioe){
		System.out.println("IOException " + ioe.getMessage());
		}
	//System.out.println("sb.toString(): " + sb.toString());
//	return sb.toString();// vozvrashaem string _u nas on = 0 vsegda vrode...
//	return chartListInDouble;
	return chartListAsObject;
	}
	
	
//________ctenie textovogo formata
	String toReadTxtData(){
		BufferedReader bufReader = new BufferedReader (reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		try {
			while(( str = bufReader.readLine()) != null){
			sb.append(str);
			sb.append(System.getProperty("line.separator"));
			}
			bufReader.close();
			}
		catch (IOException ioe){
			System.out.println("IOException " + ioe.getMessage());
			}
		return sb.toString();
		}
//to clone chart for konkrete quot
	
	
//	public void cloneChartList(String chartName, List chartIn ){
//		workChartElements.put(chartName, chartIn) ;
//	//	return chartOut;
//		
//	}

		
//Method "save File"		
			public void saveFile(File fileName, String text){
				try {
					BufferedWriter bufWriter = new BufferedWriter (new FileWriter(fileName));
					bufWriter.write(text);
				//	System.out.println("text: " + text);
					
					bufWriter.flush();
					bufWriter.close();
					}
				catch (IOException ioe){
					jtfStatus.setText("IOException"  + ioe.getMessage());
				}
			}
//Method "save File as ..."	
			public File saveFileAs(String text, String title, String startDir){
				File file = null;
				fileChooser = new JFileChooser( new File(startDir));
				fileChooser.setDialogType(JFileChooser .SAVE_DIALOG);
				fileChooser.setApproveButtonText(title);
				int returnVal = fileChooser.showDialog(jtfStatus, null);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					file = fileChooser.getSelectedFile();
					saveFile(file, text);
					fileName = file;
					return fileName;
					}
				else if (returnVal == JFileChooser.CANCEL_OPTION){
					jtfStatus.setText("Speicherdialog abgebrochen");
				}
				return null;
				}
			
			// Method "WEB open"
			public URL  chooseURL(String title,String startDir){
				URL url = null;
				fileChooser = new JFileChooser(new File(startDir));
				int returnVal = fileChooser.showDialog (null, title);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					try {
					url = fileChooser.getSelectedFile().toURL();
						}
					catch (java.net.MalformedURLException murlex) {
						jtfStatus.setText("MalformedURLException: "+ murlex.getMessage() );
						}
				}
					else if (returnVal == JFileChooser.CANCEL_OPTION){
						jtfStatus.setText("Keine Datei ausgewÃ¤hlt " );
					}
				return url;
				}
		}

/*Ð­Ñ‚Ð¾ Ð¾Ñ€Ð¸Ð³Ð¸Ð½Ð°Ð» Stochasti.mq4:
MQL-ÐšÐ¾Ð´:

//--- name for DataWindow and indicator subwindow label
   short_name="Sto("+IntegerToString(InpKPeriod)+","+IntegerToString(InpDPeriod)+","+IntegerToString(InpSlowing)+")";
   IndicatorShortName(short_name);
   SetIndexLabel(0,short_name);
   SetIndexLabel(1,"Signal");

Ð� Ñ�Ñ‚Ð¾ Ð¸Ð·Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ� Ð´Ð»Ñ� Ñ‚Ð¾Ð³Ð¾, Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð½Ðµ Ð±Ñ‹Ð»Ð¾ ÐºÐ¾Ñ€Ð¾Ñ‚ÐºÐ¾Ð³Ð¾ Ð½Ð°Ð·Ð²Ð°Ð½Ð¸Ñ�, Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ†Ð¸Ñ„Ñ€Ð¾Ð²Ñ‹Ðµ Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ� Ð»Ð¸Ð½Ð¸Ð¹:
MQL-ÐšÐ¾Ð´:

//--- name for DataWindow and indicator subwindow label
   
   IndicatorShortName(short_name);
   short_name="Sto("+IntegerToString(InpKPeriod)+","+IntegerToString(InpDPeriod)+","+IntegerToString(InpSlowing)+")";
   SetIndexLabel(0,short_name);
   SetIndexLabel(1,"Signal");

*/
