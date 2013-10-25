/* -------------------
 * LineChartDemo7.java
 * -------------------
 * (C) Copyright 2005-2011, by Object Refinery Limited.
 *
 */

package jFreeChart;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.ShapeUtilities;

/**
 * Modified from LineChartDemo7
 * 
 * In this line chart, the visibility of lines and shapes is controlled on a per
 * series basis.
 */
public class RSChart extends JPanel
	{
	//	JDesktopPane desktop = null; 
	//	JPanel chartPanel = null;

		/**
		 * Creates a new demo.
		 * 
		 * @param title
		 *            the frame title.
		 */
		public RSChart()
			{
				//super(title);
				//this.desktop = desktop;
				//setTitle(title);
				JPanel chartPanel = createDemoPanel();
				 chartPanel.setPreferredSize(new Dimension(1000, 800));
			
			//	desktop.add(this);
			}

		/**
		 * Creates a sample dataset.
		 * 
		 * @return The dataset.
		 */
		private  CategoryDataset createDataset()
			{
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.addValue(21, "Series 1", "Category 1");
				dataset.addValue(50, "Series 1", "Category 2");
				dataset.addValue(152, "Series 1", "Category 3");
				dataset.addValue(184, "Series 1", "Category 4");
				dataset.addValue(299, "Series 1", "Category 5");
				dataset.addValue(275, "Series 2", "Category 1");
				dataset.addValue(121, "Series 2", "Category 2");
				dataset.addValue(98, "Series 2", "Category 3");
				dataset.addValue(103, "Series 2", "Category 4");
				dataset.addValue(210, "Series 2", "Category 5");
				dataset.addValue(198, "Series 3", "Category 1");
				dataset.addValue(165, "Series 3", "Category 2");
				dataset.addValue(55, "Series 3", "Category 3");
				dataset.addValue(34, "Series 3", "Category 4");
				dataset.addValue(77, "Series 3", "Category 5");
				return dataset;
			}

		/**
		 * Creates a sample chart.
		 * 
		 * @param dataset
		 *            a dataset.
		 * 
		 * @return The chart.
		 */
		private  JFreeChart createChart(CategoryDataset dataset)
			{

				// create the chart...
				JFreeChart chart = ChartFactory
						.createLineChart(
								"It Should be Relatively Easy to Modify This For Your Application", // chart
																									// title
								"Category", // domain axis label
								"Count", // range axis label
								dataset, // data
								PlotOrientation.VERTICAL, // orientation
								true, // include legend
								true, // tooltips
								false // urls

						);

				// Add Subtitle
				chart.addSubtitle(new TextTitle(
						"JFreeChart Should Include and Example Like This"));

				CategoryPlot plot = (CategoryPlot) chart.getPlot();

				// customise the range axis...
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());

				// customise the renderer...
				LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
						.getRenderer();
				renderer.setSeriesShapesVisible(0, true);
				renderer.setSeriesShapesVisible(1, false);
				renderer.setSeriesShapesVisible(2, true);
				renderer.setSeriesLinesVisible(2, false);
				renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
				renderer.setDrawOutlines(true);
				renderer.setUseFillPaint(true);
				renderer.setBaseFillPaint(Color.white);

				return chart;
			}

		/**
		 * Creates a panel for the demo (used by SuperDemo.java).
		 * 
		 * @return A panel.
		 */
		public  JPanel createDemoPanel()
			{
				JFreeChart chart = createChart(createDataset());
			//	chartPanel.add(chart);
				return new ChartPanel(chart);
			}

		/**
		 * Starting point for the demonstration application.
		 * 
		 * @param args
		 *            ignored.
		 */
/*		public  void main(String[] args)
			{
				RSChart demo = new RSChart("LineChartDemo7 in a JFrame");
				demo.pack();
				// RefineryUtilities.centerFrameOnScreen(demo);
				demo.setVisible(true);
			}
*/
	}
