/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.plot.ValueMarker;
/**
 *
 * @author Monalisa
 */
public class GraphPanel extends JPanel implements ActionListener{
    // Time series
    private TimeSeries series;
    private String strMIN;
    private String strHOR;
    private long watchStart, watchEnd;
    /**
     * The most recent value added.
     */
    private double lastValue = 100.0;
    public int mints = 1280;//
    public GraphPanel()
    {
     this.series = new TimeSeries("", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
        //JFreeChart ch=
       
        this.setLayout(new BorderLayout());

        final ChartPanel chartPanel = new ChartPanel(chart);
        final JButton button = new JButton("Add New Data Item");
        button.setActionCommand("ADD_DATA");
        button.addActionListener(this);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        //   content.add(button, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 255));
        this.setBounds(0, 0, 850, 255);
        this.add(content, BorderLayout.CENTER);
        
        ValueMarker marker = new ValueMarker(80);
        marker.setPaint(Color.BLUE);
        XYPlot plot = chart.getXYPlot();
        plot.addRangeMarker(marker);
        
        ValueMarker marker2 = new ValueMarker(120);
        marker2.setPaint(Color.BLUE);
        XYPlot plot2 = chart.getXYPlot();
        plot.addRangeMarker(marker2);
        // setContentPane(content);
        watchStart = System.currentTimeMillis();
    }

   
     public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("ADD_DATA")) {
            final double factor = 0.90 + 0.2 * Math.random();
            this.lastValue = this.lastValue * factor;
            final Millisecond now = new Millisecond();
            System.out.println("Now = " + now.toString());
            this.series.add(new Millisecond(), this.lastValue);
           
        }
    }
     
     public void changechartLine(int s, double m, int h, double bsl) {
        final double factor = 0.90 + 0.2 * Math.random();
        this.lastValue = bsl;//this.lastValue * factor;

        int secondss = (int) ((int) (System.currentTimeMillis() - watchStart) / 50);
        // System.out.println("miliseconds"+System.currentTimeMillis());
        int dayss = secondss / 86400;

        int hours = (secondss / 3600) - (dayss * 24);
        int min = (secondss / 60) - (dayss * 1440) - (hours * 60);
        min = (int) m * 2;
        min = mints;
        hours = min / 60;
        min = mints % 60;
        int sec = secondss % 60;
        sec = 0;
        if (min < 10) {
            strMIN = "0" + min;
        } else {
            strMIN = "" + min;
        }

        if (hours < 10) {
            strHOR = "0" + hours;
        } else {
            strHOR = "" + hours;
        }


        mints += 20;
        String ss = new String(" " + strHOR + ":" + strMIN + ":" + sec + "");
        System.out.println("====" + ss);

        final Millisecond now = new Millisecond(0, sec, min, hours, 02, 6, 2014);





        //  final Millisecond now = new Millisecond();

        // System.out.println("Now = " + now.toString());
        // this.series.add(new Millisecond(), this.lastValue);
        this.series.addOrUpdate(now, this.lastValue);
        // this.series.addOrUpdate(now1, this.lastValue+23);


    }
     
     private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "",
                "Time(sec)",
                "BSL(mg/dL)",
                dataset,
                true,
                true,
                false);
        final XYPlot plot = result.getXYPlot();
        plot.setBackgroundPaint(new Color(0xd3d3d3));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);

        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        /*X axis time*/
        axis.setFixedAutoRange(96000000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        //Y axis
        axis.setRange(0.0, 200.0);
        //  axis.setf
        return result;
    }

     public JPanel getPanel() {
        return this;
    }
     public void ClearGraphPanel()
     {
         this.series.clear();
     }
}
