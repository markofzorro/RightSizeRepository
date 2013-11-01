package rightSize;

import javax.swing.JDesktopPane;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Called by SRS doc and ClusterDoc to show charts of their data. Generates a
 * set of assumptions similar to those of the one user entered. then calculates
 * sample sizes for these and displays a line graph showing how the assumption
 * affects the required sample size.
 * 
 * @author markofzero
 * 
 */
public class RSChartDoc
	{
		private JDesktopPane desktop = null;

		public RSChartDoc(JDesktopPane desktop)
			{
				this.desktop = desktop;
				D.b("In RSChartDoc constructor.");
				createChartFrame();

			}

		// Create a new internal frame.
		private void createChartFrame()
			{

				//static public void calculate(double arg_population, double arg_proportion, double arg_confidenceInterval, double arg_confidenceCoefficient )
				RSCalculator.calculate(100, 50, 10, 95);
		         double size = RSCalculator.getN();

				DefaultCategoryDataset dataset = variationsAdd(50, 1, 99, 11);
						
						
				
				/*
				 * You must follow the strict arg conventions or chart ignores your input
				 * 
				 * First arg is the value of the point as a double. the x value.
				 * Second is the series (the line the point belongs on)
				 * Third is the column the y value.
				 */
			/*	for (int i = 0; i < 5; i++)
					{
						dataset.addValue(i * 10, "Proportion", "Category" + Integer.toString(i));
						RSCalculator.calculate(100, i * 10, 10, 95);
						
					}
				*/	
			//	dataset.addValue(1, "Series 1", "Category 6");
			//	dataset.addValue(10, "Series 1", "Category 6");
			/*	dataset.addValue(10, "Series 1", "Category 1");
				dataset.addValue(50, "Series 1", "Category 2");
				dataset.addValue(152, "Series 1", "Category 3");
				dataset.addValue(184, "Series 1", "Category 4");
				dataset.addValue(299, "Series 1", "Category 5");
			/*	dataset.addValue(275, "Series 2", "Category 1");
				dataset.addValue(121, "Series 2", "Category 2");
				dataset.addValue(98, "Series 2", "Category 3");
				dataset.addValue(103, "Series 2", "Category 4");
				dataset.addValue(210, "Series 2", "Category 5");
				dataset.addValue(198, "Series 3", "Category 1");
				dataset.addValue(165, "Series 3", "Category 2");
				dataset.addValue(55, "Series 3", "Category 3");
				dataset.addValue(34, "Series 3", "Category 4");
				dataset.addValue(77, "Series 3", "Category 5");
				*/

				RSChartView frame = new RSChartView(dataset,
						"Simple Random Sample With Varied Assumptions");
				frame.setVisible(true); // necessary as of 1.3
				desktop.add(frame);
				try
					{
						frame.setSelected(true);
					} catch (java.beans.PropertyVetoException e)
					{
					}

			}

		public DefaultCategoryDataset variationsAdd(float userVal, float min, float max, int cols)
			{
				//float[] array = new float[cols];
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				// be sure we have odd number of columns so userVal will be in
				// center if possible
				int remainder = cols % 2;
				if (remainder == 0) // it is even
					{
						D.b("********* Aborting. Vary: col should be odd. Here it is "
								+ cols);
						System.exit(0);
					}

				int halfarraysize = cols / 2;
				
				// Arbitrary number to start the ball rolling.
				// May be able to improve by choosing intervals to i power of 10 below userVal 
				float distance = 10; 
				

				// does the expansion fit the range?
				if ((min + distance * cols) > max) // the expansion is too big
					{
						// chop the bigger segment into narray bits
						float top = max - userVal;
						float bottom = userVal - min;
						if (top >= bottom)
							distance = top / cols;
						else
							distance = bottom / cols;
					} // if

				// Can we vary down?
				for (int i = 0; i < halfarraysize; i++)
					if ((userVal - distance * halfarraysize) < min) // oops, it
																	// will run
																	// below min
						userVal += distance; // move it up

				// Can we vary up?
				for (int i = 0; i < halfarraysize; i++)
					{	if ((userVal + distance * halfarraysize) > max) // oops, it
																	// will run
																	// below min
						userVal -= distance; // move it down
					}
			
				/*
				 * #ifdef _DEBUG // check to be sure it worked ASSERT( (userVal
				 * - halfarraysize * distance) >= min); ASSERT( (userVal +
				 * distance * halfarraysize) <= max); #endif
				 */
				// now fill the array
				float start = userVal - distance * halfarraysize;
				
				// Make sure start and ddistance are 
				for (int i = 0; i < cols; i++)
					{	
					//dataset.addValue(i * 10, "Proportion", "Category" + Integer.toString(i));
					RSCalculator.calculate(100, start, 10, 95);
					double  n0 = RSCalculator.getN0();
					D.b("n0 is " + n0);
					dataset.addValue(n0, "Proportion",  Float.toString(start));
					start += distance;
					//array[i] = start + distance * i;
					}
				// Debugger
			//	D.b("RSVariations: varyAdd:");
			//	for (int i = 0; i < cols; i++)
			//		D.b("array[" + i + "] is " + array[i]);

				return dataset;
			} // variationsAdd

		
	} // end of class
