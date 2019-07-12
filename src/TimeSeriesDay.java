

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesDay extends ApplicationFrame

{
    private static final long serialVersionUID = 1L;
	private static final String chartTitle = null;
 //   static String TITLE = "Курс валюты, цена нефти марки Brent";
	
	
   
    public TimeSeriesDay(final String title, XYDataset tsc) {
        
        super(title);
     //   
     // final XYDataset datasetAll =  Dataset2.createAllDataset(timeSeries );
        final JFreeChart chart = createChart(tsc);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 480));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(final XYDataset dataset) 
    {
//        JFreeChart chart = ChartFactory.createTimeSeriesChart (
//            "Валюта, нефть с 11.05.2017 по 25.05.2017", null, null,
//            dataset, true, true, false);
//        
        JFreeChart chart = ChartFactory.createTimeSeriesChart (
        		chartTitle, null, null,
                dataset, true, true, false);

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        
        plot.setBackgroundPaint(new Color(232, 232, 232));
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint (Color.lightGray);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible (true);
        
        // Скрытие осевых линий
        ValueAxis vaxis = plot.getDomainAxis();
        vaxis.setAxisLineVisible (false);
        vaxis = plot.getRangeAxis();
        vaxis.setAxisLineVisible (false);

        plot.getRenderer().setSeriesPaint(2, new Color(64, 255, 64));
        // Определение временной оси
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        // Формат отображения осевых меток
        axis.setDateFormatOverride(new SimpleDateFormat("dd.MM"));
        return chart;
    }
//    
//    Object createAllDataset(){
//    final TimeSeriesCollection allDataSeries = new TimeSeriesCollection();//eto d b!!
//    allDataSeries.addSeries(dataset2.createOneDataset(String name,List<Double> list));
//    dataset.addSeries(s2);//NADO ORGANIZOVAT PROMEGUTOCNOE MESTO HRANENIA s1
//    dataset.addSeries(s3);
//    return dataset2;
//}
}