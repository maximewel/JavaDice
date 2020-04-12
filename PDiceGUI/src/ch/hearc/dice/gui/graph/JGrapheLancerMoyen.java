package ch.hearc.dice.gui.graph;

import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;

public class JGrapheLancerMoyen extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JGrapheLancerMoyen()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void updateData(Map<Integer, Integer> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
	    	dataset.addValue(entry.getValue().doubleValue(), "Serie1", entry.getKey() + " Faces");
		}

	    ((CategoryPlot)chart.getPlot()).setDataset(0, dataset);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		chart = ChartFactory.createBarChart(
	            "Lancer moyen par dé",          // chart title
	            "Nombre de faces",               // domain axis label
	            "Nombre de lancer",                  // range axis label
	            new DefaultCategoryDataset(),         // data
	            PlotOrientation.VERTICAL,
	            false,                    // include legend
	            true,                     // tooltips?
	            false                     // URL generator?  Not required...
	        );


	        // get a reference to the plot for further customisation...
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();

	        plot.mapDatasetToRangeAxis(1, 1);

	        CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

	        LegendTitle legend1 = new LegendTitle(plot.getRenderer(0));
	        legend1.setMargin(new RectangleInsets(2, 2, 2, 2));
	        legend1.setFrame(new BlockBorder());


	        BlockContainer container = new BlockContainer(new BorderArrangement());
	        container.add(legend1, RectangleEdge.LEFT);
	        container.add(new EmptyBlock(2000, 0));
	        CompositeTitle legends = new CompositeTitle(container);
	        legends.setPosition(RectangleEdge.BOTTOM);
	        chart.addSubtitle(legends);

	        ChartPanel cp = new ChartPanel(chart);

			add(cp);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs


	// Tools
	private JFreeChart chart;


	}
