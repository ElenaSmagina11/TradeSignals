
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.*;


public class MyFrame extends JFrame implements ActionListener {

	// Fenster fÃ¼r Hilfe und Info
	private JLayeredPane desktop;
	private Object rb;
	private Chart chart;
	// Fenster fÃ¼rHTML-formatierten Text
	
	private static QuotLoader loader;
	private static QuotPrinter quotPrinter;
//	static String quotName = loader.fileName.getName().substring(0,loader.fileName.getName().lastIndexOf('.'));
	private static  QuotLoader fileBrowserAlt;
	private static JTextField jtfStatus;
	private int numberOfQuotes;
	private static DrawChart drawChart;
	private static TimeSeriesDay seriesDay;
	 static TimeSeriesCollection tsc;
	 private static  String quotName;
	 private static String chartTitle;
	

	public MyFrame() {
			super("Börse Signals");
			this.setSize(700,400);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container contentPane = this.getContentPane();
// Status-Zeile
			final JTextField jtfStatus = new JTextField ();
			final JTextField jtf = new JTextField();
			jtfStatus.setBackground(Color.blue);
			jtfStatus.setForeground(Color.CYAN);
			jtfStatus.setEditable(false);
			jtfStatus.setText("Alles arbeitet! Hurraaa!!!");		
			
//Einstellungen der Panel			
			final JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(BorderLayout.SOUTH, jtfStatus);
			contentPane.add(BorderLayout.CENTER, panel);
			final JEditorPane editorPane = new JEditorPane();
			JScrollPane editorSP = new JScrollPane(editorPane);
			panel.add(BorderLayout.CENTER, editorSP);
			
// MenÃ¼ und Toolbar-Objekte	
			final JToolBar toolbar = new JToolBar();
			getContentPane().add(toolbar, BorderLayout.NORTH);
			final JButton toLoadQuot = new JButton("Load Quotation",new ImageIcon("Resourses/Open.png"  ));
			final JButton toCalculate = new JButton("To Calculate",new ImageIcon("Resourses/Open.png"  ));
//			JMenuBar menubar = new JMenuBar();
//			this.setJMenuBar(menubar);	
//		
//			JMenu menuFile = new JMenu("Datei");
//			JMenu menuHelp = new JMenu("?");
//			menubar.add(menuFile);
//			menubar.add(menuHelp);
				
// Toolbar-ToolTips
			
			final String TTT_Open = "File oeffnen";
			final String TTT_Web = "Web oeffnen";
			final String TTT_Save = "File speichern";
			final String TTT_Help = "Hilfe";
			final String TTT_Info = "Info";
//_____________________________________________________
			JLabel selectSignalLabel = new JLabel("SELECT SIGNAL  ");
			toolbar.add(selectSignalLabel);
	
			final JComboBox<String> selectSignalCombo = new JComboBox<String>(SignalElements.SIGNALSarray);
//			selectSignalCombo.setSize(new Dimension(50, 30));
//			selectSignalCombo.setSize(30,selectSignalCombo.getPreferredSize().height);
			selectSignalCombo.setPrototypeDisplayValue("0123456789");
//			Dimension d = selectSignalCombo.getPreferredSize();
			selectSignalCombo.setSelectedIndex(-1);
	
			selectSignalCombo.setSelectedItem(SignalElements.SIGNALSarray);
			toolbar.add(selectSignalCombo);
         	
		//	toolbar.add(toLoadQuot);
			
			selectSignalCombo.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event) {
					String selectedSignal = (String) selectSignalCombo.getSelectedItem();
					//______________
				
				if (selectedSignal.equals(SignalElements.SIGNALSarray[0])) {
					toolbar.add(toLoadQuot);
					numberOfQuotes = SignalElements.allSignalElements.get( SignalElements.SIGNALSarray[0]);
					jtfStatus.setText(SignalElements.SIGNALSarray[0]+ " was selected. You schould " +
					numberOfQuotes + " Quotes to upload.");
					chartTitle = SignalElements.SIGNALSarray[0];
			
			//		toLoadQuotes(numberOfQuotes);
			//		void toLoadQuotes(int numberOfQuotes){
			//		for(int i=0,i++, i<=numberOfQuotes ){
//						toLoadQuotes(numberOfQuotes)}
					
				//	System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( SignalElements.signalElementsArray[0]));
				} else if (selectedSignal.equals(SignalElements.SIGNALSarray[1])) {
					
					jtfStatus.setText(SignalElements.SIGNALSarray[1]+" was selected");
					numberOfQuotes = SignalElements.allSignalElements.get( SignalElements.SIGNALSarray[1]);
					chartTitle = SignalElements.SIGNALSarray[1];
					System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( SignalElements.SIGNALSarray[1]));
				} else {
					jtfStatus.setText(SignalElements.SIGNALSarray[2] + " was selected");
					chartTitle = SignalElements.SIGNALSarray[2];
					System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( SignalElements.SIGNALSarray[2]));
				}
				}
		});
								
//					switch (selectedSignal) {
//			        case " SIGNAL 1": jtfStatus.setText("SIGNAL 1 was selected");
//			        		
//			        		  System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( SignalElements.signalElementsArray[0]));
//			        		 
//			        	//	  JButton jbtWeb = toolbar.add();
//			        	//	  toolbar.add(toLoadQuot);
//			        		  toolbar.add(toLoadQuot, MAXIMIZED_VERT);
//			        		break;
//			        case " SIGNAL 2": 
//			        		jtfStatus.setText("SIGNAL 2 was selected");
//			        		 System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( " SIGNAL 2"));
//			        case " SIGNAL 3": 
//	        		jtfStatus.setText("SIGNAL 3 was selected");
//	        		 System.out.println("!!!  kolvo kotirovok: " + SignalElements.allSignalElements.get( " SIGNAL "));
//		            break;
//		            //	add(toLoadQuot);
//		          
//					}

//obrabotka nagatia knopki sagrusit kotiri					
					toLoadQuot.addActionListener(new ActionListener() {//dolgen uge snat skoka kakih kotirov
						
					

						private Dataset2 dataset2;
						
						

						public void actionPerformed(ActionEvent e) {
							do{
							jtfStatus.setText("\"File - open- " +"file system...\" was chosen.");
						//			 List<Double> chartListPriceClose =  fileBrowser.chooseText("Datei waehlen", "./");//zagnali ves tet v stroku
						//	 List chartList =  fileBrowser.chooseFileToRead();
							 List chartList =  loader. toReadInChart();
						//	 quotPrinter.printChartListObjects(chartList);vse robit_ vivodit pravilno
							 
						//	 String quotName = loader.fileName.getName().substring(0,loader.fileName.getName().lastIndexOf('.'));
						//	 loader.listToDouble(chartList);
						 quotName = loader.getQuotName();
							 
							 List<Double> chartListQuot = loader.averageForSMAPeriod(loader.listToDouble(chartList));//polucili List MA odnoj pari
					///!!! NE TO TUDA KLADU!! NADO NE CHARTlIST; A DOUBLE IZ SMA!!!		 
							 workChartElements.put(quotName,chartListQuot);//zdes ChartList stanovitsa <Double>-NADO ETO  UREGULIROVAT!!
							
							 	 System.out.println( "quotName = " + quotName );
							 	
							 	dataset2.createOneDataset(quotName,chartListQuot);
							 	
							 	 tsc = dataset2.createAllDataset(dataset2.createOneDataset(quotName,chartListQuot));
							 	 System.out.println( "tsc = " + tsc.getSeriesCount());
//							 try {
//								 chart.createObjectChartList(name);
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
		 
				//			 workChartList = chartList;
							// quotPrinter.cloneChartList(quotName, chartList);
						 workChartElements.zeigen();
						 System.out.println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " );	
					///	 workChartElements.selectChart(chartList, quotName);
							
									System.out.println(editorPane.getText());
									jtfStatus.setText("OK! quotes of " + quotName + "are uploaded!");
									--numberOfQuotes;
								}
						
					//	while ( numberOfQuotes != 0 && workChartElements.menshe chem numberOfQuotes to soobshenie cto ctoto poshlo ne tak);
						while (numberOfQuotes != 0);
						editorPane.setText("OK! All quotes are uploaded!");
						jtfStatus.setText("OK! All quotes are uploaded!");
						System.out.println("OK! All quotes are uploaded!");
						toolbar.remove(toLoadQuot);
						toolbar.add(toCalculate);
						}

					});
					
/// Calculate					
					toCalculate.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							System.out.println("Calculate doch!");
						//    workChartElements.zeigen();

						//    String TITLE = "Who is faster";
						    TimeSeriesDay demo = new TimeSeriesDay(chartTitle, tsc);
						    System.out.println("demo is creaed:" + demo.getClass());
					        demo.pack();
					        RefineryUtilities.centerFrameOnScreen(demo);
					        demo.setVisible(true);
											    
						}
						});
						
				//	toLoadQuot.
//Action "WEB oeffnen"
//			toSetButton(){
//			 AbstractAction actWeb = new AbstractAction("Web",new ImageIcon("Resourses/Web.png"  )){
//				 public void actionPerformed(ActionEvent ae) {
//					 jtfStatus.setText("Web " + "wurde gewÃ¤lt.");
//						java.net.URL url = fileBrowser.chooseURL("HTML-Seite wÃ¤hlen", "./");
//						if (url!=null){
//							try{
//								editorPane.setPage(url);
//								jtfStatus.setText("Datei \"" + url.getPath() + "\" geladen");
//								}
//							catch (IOException ioe) {
//							jtfStatus.setText(ioe.toString());
//							}}}
//					};
//	//		 	JMenuItem jmiWeb = menuFile.add(actWeb);
//				JButton jbtWeb = toolbar.add(actWeb);
//				jbtWeb.addActionListener( this);
//	//			jmiWeb.setMnemonic('w');
//	//			jmiWeb.setToolTipText( TTT_Web);
//				actWeb.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
//				actWeb.putValue(Action.SHORT_DESCRIPTION, TTT_Web);			
//			}
////_____________________________________________________________________________
			
//				AbstractAction actOpen1 = new AbstractAction("Open",new ImageIcon("Resourses/Open.png"  )){
//					
//					public void actionPerformed(ActionEvent ae) {
//					 jtfStatus.setText("\" Datei - oeffnen- "  +"Dateisystem...\" gewaehlt.");
//					 List<Double> chartListPriceClose =  fileBrowser.chooseText("Datei waehlen", "./");//zagnali ves tet v stroku
//					// editorPane.setContentType("text/plain");
//					 	if (chartListPriceClose.isEmpty()){
//							jtfStatus.setText("Keine Datei ausgewaehlt.");
//							}
//						else {
//							String file = "Datei \"" + fileBrowser.fileName.getAbsolutePath() + "\" wurde geoffnet";
//							jtfStatus.setText("Datei \"" + fileBrowser.fileName.getAbsolutePath() + "\" wurde geoffnet");
//							
//							file = editorPane.getText();
//							System.out.println(editorPane.getText());
//							editorPane.setText( "Datei \"" + fileBrowser.fileName.getAbsolutePath() + "\" wurde geoffnet");
//							//editorPane.setText(" ");
//						}
//							}
//			
//						};
						
					
//		//	 	JMenuItem jmiOpen1 = menuFile.add(actOpen1);
//				JButton jbtOpen1 = toolbar.add(actOpen1);
//				jbtOpen1.addActionListener( this);
//			//	jmiOpen1.setToolTipText( TTT_Open);
//				actOpen1.putValue(Action.SHORT_DESCRIPTION, TTT_Open);
//				actOpen1.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
//		//		jmiOpen1.setMnemonic('o');
//				
			

//Action "Datei speichern"
//		
//		AbstractAction actSave = new AbstractAction("Save" ,new ImageIcon("Resourses/Save.png"  )){
//			public void actionPerformed(ActionEvent ae) {
//				jtfStatus.setText("Datei Speichern " + "wurde gewÃ¤lt.");
//				if (fileBrowser.fileName != null){
//				fileBrowser.saveFile(fileBrowser.fileName, editorPane.getText());
//				jtfStatus.setText("Datei\"" + fileBrowser.fileName.getAbsolutePath() + "\" gespeichert");
//			    }
//			  else{
//				 jtfStatus.setText("Datei Speichern as.." + "wurde gewÃ¤lt.");
//				 fileBrowser.saveFileAs( editorPane.getText(), "Speichern", ".");
//				 jtfStatus.setText("Datei\"" + fileBrowser.fileName.getAbsolutePath() + "\" gespeichert");
//			  }	} 
//			};
//	//		JMenuItem jmiSave = menuFile.add(actSave);
//		   	JButton jbtSave = toolbar.add(actSave);
//		   	jbtSave.addActionListener( this);
//		//	jmiSave.setToolTipText( TTT_Save);
//			actSave.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//			actSave.putValue(Action.SHORT_DESCRIPTION, TTT_Save);
//}
//}
//);};
//Trennlinie einfÃ¼gen
//			toolbar.addSeparator();
//Action "Help"
		AbstractAction actHelp = new AbstractAction("Help",new ImageIcon("Resourses/Help.png"  )){
			 public void actionPerformed(ActionEvent ae) {
			 jtfStatus.setText("Datei  HELP geladen");
				 showHelp ();
				}
		 };
//	//	 	JMenuItem jmiHelp = menuHelp.add(actHelp);
//			JButton jbtHelp = toolbar.add(actHelp);
//			jbtHelp.addActionListener( this);
//	//		jmiHelp.setMnemonic('h');
//	//		jmiHelp.setToolTipText( TTT_Help);
//			actHelp.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
//			actHelp.putValue(Action.SHORT_DESCRIPTION, TTT_Help);
//			
//Action "Info"
		 AbstractAction actInfo = new AbstractAction("Info",new ImageIcon("Resourses/Info.png"  )){
			 public void actionPerformed(ActionEvent ae) {
			 jtfStatus.setText("Datei  INFO geladen");
			 showInfo();
				}
		 };
	//	 	JMenuItem jmiInfo = menuHelp.add(actInfo);
			JButton jbtInfo = toolbar.add(actInfo);
			jbtInfo.addActionListener( this);
	//		jmiInfo.setMnemonic('i');
	//		jmiInfo.setToolTipText( TTT_Info);
			actInfo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
			actInfo.putValue(Action.SHORT_DESCRIPTION, TTT_Info);
		
			desktop = getLayeredPane();
			setVisible(true);
}
protected void toLoadQuotes(int numberOfQuotes2) {
		// TODO Auto-generated method stub
		
	}



/// M A I N
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
		loader = new QuotLoader(jtfStatus);
		SignalElements.toHashMap(); //zagruska sootvetstvia kotirovok
	}
	
	
//_______________________________________--
//public int toSwitch(String type){ //NE ROBIT
//	switch (type) {
//    case " SIGNAL 1": jtfStatus.setText("SIGNAL 1 was selected");
//    		signalType=1;
//    		break;
//    case " SIGNAL 2": signalType=2;
//    		jtfStatus.setText("SIGNAL 2 was selected");
//    case " SIGNAL 3": signalType=2;
//	jtfStatus.setText("SIGNAL 2 was selected");
//    break;
//	}
//	return signalType;
//}
	// showHelp Methode
	private void showHelp() {
		Container helpPane = this.getContentPane();
		System.out.println("HelpPane " + helpPane.getLocation() + "H ="
				+ helpPane.getHeight() + "W = " + helpPane.getWidth());
		final int MODAL_LAYER = 0;

		// JInternalFrame erzeugen
		JInternalFrame internalFrameHelp = new JInternalFrame("Hilfe", true,
				true);
		internalFrameHelp.setSize(400, 320);
		internalFrameHelp.setLocation(
				(helpPane.getWidth() - internalFrameHelp.getWidth()) / 2,
				(helpPane.getHeight() - internalFrameHelp.getHeight()) / 2 + 2
						* helpPane.getY() - helpPane.getY() / 2);
		internalFrameHelp
				.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		desktop.add(internalFrameHelp, MODAL_LAYER);

		JEditorPane editorPane = new JEditorPane();
		JScrollPane editorSP = new JScrollPane(editorPane);
		internalFrameHelp.add(BorderLayout.CENTER, editorSP);
		internalFrameHelp.setVisible(true);

		String text = "<html>Dies ist ein <b>Hilfetext</b> zur Swing-applikation MyFrame.</html>";
		JTextPane helpText = new JTextPane();
		helpText.setContentType("text/html");
		helpText.setText(text);
		// der Text ist nicht editiert
		helpText.setEditable(false);

		// der Text ist scrollbar
		internalFrameHelp.add(new JScrollPane(helpText));
		internalFrameHelp.setVisible(true);

	}

	// __________________________________________________

	// showInfo Methode
	private void showInfo() {
		// Das Info-Fenster liegt immer oben
		final int POPUP_LAYER = 1;
		JInternalFrame internalFrameInfo = new JInternalFrame("Info", false,
				true);
		Container helpPane = this.getContentPane();
		internalFrameInfo.setSize(260, 220);
		internalFrameInfo.setLocation(
				(helpPane.getWidth() - internalFrameInfo.getWidth()) / 2,
				(helpPane.getHeight() - internalFrameInfo.getHeight()) / 2 + 2
						* helpPane.getY());

		// oder mit UnverÃ¤nderte GrÃ¶ÃŸe
		// internalFrameInfo.setResizable(false);
		// Text im Fenster "Info"
		String text = "<html><center><h2> Swing-applikation MyFrame</h2><br />"
				+ "<b>Autor: </b>Elena Smagina<br />"
				+ "<b>Version: </b>03.10.2016<br />"
				+ "<b>Projekt: </b>Lernheft JAV05N<br />"
				+ "<b>Abschnitt: </b>Einsendeaufgaben</html>";

		desktop.add(internalFrameInfo, POPUP_LAYER);
		internalFrameInfo.setVisible(true);
		JTextPane infoText = new JTextPane();
		infoText.setContentType("text/html");
		infoText.setText(text);
		infoText.setEditable(false);
		internalFrameInfo.add(infoText);
		internalFrameInfo
				.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrameInfo.setVisible(true);
	}

	// @Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String getPage(URL url) throws IOException {
		return null;
	}
//to load N stück Quotes	
	
	
}
