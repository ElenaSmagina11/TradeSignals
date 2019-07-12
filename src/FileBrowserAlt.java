

import java.io.*;
import java.net.URI;
import java.net.URL;
import javax.swing.*;

public class FileBrowserAlt {
	
	private JTextField jtfStatus;
	private JFileChooser fileChooser;
	public File fileName;
//	private File file;
	public FileBrowserAlt (JTextField jtfStatus){
		this.jtfStatus = jtfStatus;
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
	
//Method "chooseText"
	public String chooseText(String title,String startDir){
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
//Method "save File"		
			public void saveFile(File fileName, String text){
				try {
					BufferedWriter bufWriter = new BufferedWriter (new FileWriter(fileName));
					bufWriter.write(text);
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
