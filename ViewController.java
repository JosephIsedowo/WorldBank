package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.data.xy.XYSeriesCollection;


public class ViewController {
    private static ArrayList<ChartPanel> panels;
    private static ChartPanel bar;
    private static ChartPanel line;
    private static ChartPanel pie;
    private static ChartPanel scatter;
    private static ChartPanel timeseries;
    private static ViewController instance;
    private static JScrollPane outputScrollPane;
    public ViewController() {
        if(panels == null) {
            panels = new ArrayList<ChartPanel>();
        }
    }
    public static ViewController getViewController() {
        if(instance == null) {
            instance = new ViewController();
        }
        return instance;
    }
    public static void removeAll(JPanel west) {
        //mostly to refresh the entire panel
        west.repaint();//  tells Swing that an area of the window is dirty
        west.removeAll(); // necessary to erase the image of the old children removed by removeAll()
        west.revalidate(); // tells the layout manager to recalculate the layout (which is necessary when adding components)
    }
    public static JScrollPane getReport() {
        if(outputScrollPane != null) {
            JScrollPane  pan = outputScrollPane;
            return pan;
        }
        return null;
    }
    public static ChartPanel getPanelForRemoval(String panel) {
        if(panel == "bar") {
            if(panels.contains(bar)) {
                ChartPanel pan = bar;
                panels.remove(bar);
                return pan;
            }
        }
        else if(panel == "line") {
            if(panels.contains(line)) {
                ChartPanel pan = line;
                panels.remove(line);
                return pan;
            }
        }
        else if(panel == "pie") {
            if(panels.contains(pie)) {
                ChartPanel pan = pie;
                panels.remove(pie);
                return pan;
            }
        }
        else if(panel == "scatter") {
            if(panels.contains(scatter)) {
                ChartPanel pan = scatter;
                panels.remove(scatter);
                return pan;
            }
        }
        return null;
       
    }
    public void barView(String analysis,DefaultCategoryDataset dataset) {
        if(!panels.contains(ViewController.getPanelForRemoval("bar"))) {
            CategoryPlot plot = new CategoryPlot();
            BarRenderer barrenderer1 = new BarRenderer();
           
            plot.setDataset(0, dataset);
            plot.setRenderer(0, barrenderer1);
            CategoryAxis domainAxis = new CategoryAxis("Year");
            plot.setDomainAxis(domainAxis);
            plot.setRangeAxis(new NumberAxis(""));


            plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
           

            JFreeChart barChart = new JFreeChart(analysis,
                    new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

            // Different way to create bar chart
            /*
             * dataset = new DefaultCategoryDataset();
             * 
             * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
             * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
             * dataset.addValue(96.163, "Employed", "Women"); barChart =
             * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
             * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
             */
           bar = new ChartPanel(barChart);
           bar.setPreferredSize(new Dimension(400, 300));
           bar.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
           bar.setBackground(Color.white);
           panels.add(bar);
            }
    }
        
    public void lineView(String analysis, XYSeriesCollection dataset) {
        if(!panels.contains(ViewController.getPanelForRemoval("line"))) {
        JFreeChart lineChart = ChartFactory.createXYLineChart(analysis,"Year","",dataset,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = lineChart.getXYPlot();
        
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);



        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        lineChart.getLegend().setFrame(BlockBorder.NONE);
        
        line = new ChartPanel(lineChart);
        line.setPreferredSize(new Dimension(400, 300));
        line.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        line.setBackground(Color.white);
        panels.add(line);
        }
    }
    public void pieView(String analysis,DefaultCategoryDataset dataset) {
        if(!panels.contains(ViewController.getPanelForRemoval("pie"))) {
        JFreeChart panel = ChartFactory.createMultiplePieChart(analysis, dataset,
                TableOrder.BY_COLUMN, true, true, false);
       
        pie = new ChartPanel(panel);
        pie.setPreferredSize(new Dimension(400, 300));
        pie.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pie.setBackground(Color.white);
        panels.add(pie);
        }
    }

    public void scatterView(String analysis,TimeSeriesCollection dataset) {
        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart scatterChart = new JFreeChart(analysis,
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        scatter = new ChartPanel(scatterChart);
        scatter.setPreferredSize(new Dimension(400, 300));
        scatter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        scatter.setBackground(Color.white);
        panels.add(scatter);
    }
    public void scatterView2(String analysis,TimeSeriesCollection  dataset,TimeSeriesCollection  dataset2) {
        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, itemrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart scatterChart = new JFreeChart(analysis,
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        scatter = new ChartPanel(scatterChart);
        scatter.setPreferredSize(new Dimension(400, 300));
        scatter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        scatter.setBackground(Color.white);
       panels.add(scatter);
    }
    public void timeSeriesView(String analysis,TimeSeriesCollection  dataset) {
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(analysis,"Year","", dataset, true, false, false);

        timeseries = new ChartPanel(chart);
        timeseries.setPreferredSize(new Dimension(400, 300));
        timeseries.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        timeseries.setBackground(Color.white);
        panels.add(timeseries);
    }
    public static void createReport(String data) {
        JTextArea report = new JTextArea();
        report.setEditable(false);
        report.setPreferredSize(new Dimension(300, 400));
        report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        report.setBackground(Color.white);
        report.setLineWrap(true);
        report.setWrapStyleWord(true);
        String reportMessage;
      
        

        reportMessage = data; 

        report.setText(reportMessage);
        
        outputScrollPane = new JScrollPane(report);
        
        
    }
    public static void displayGraphs(JPanel west) {
        removeAll(west);
        for(int i = 0; i < panels.size(); i++) {
            west.add(panels.get(i));
        }
    }
    public static void displayReport(JPanel west) {
        removeAll(west);
        for(int i = 0; i < panels.size(); i++) {
            west.add(panels.get(i));
        }
        west.add(outputScrollPane);
    }
}
