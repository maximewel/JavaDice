
package ch.hearc.dice.gui.result.graph;

import java.awt.BorderLayout;
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

import ch.hearc.tools.Chrono;

/**
 * <pre>
 * Use JFreeChart to display a chart of calcul duration
 * <br>
 * @author maxime.welcklen, mendesreis.steve
 *
 */
public class JGrapheChrono extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JGrapheChrono()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void updateData(Map<Integer, Chrono> data)
		{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(Map.Entry<Integer, Chrono> entry:data.entrySet())
			{
			dataset.addValue(entry.getValue().getTimeMS(), "Serie1", entry.getKey() + " Faces");
			}

		((CategoryPlot)chart.getPlot()).setDataset(0, dataset);
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.setLayout(new BorderLayout());

		chart = ChartFactory.createBarChart("Temps d'execution par dé", // chart title
				"Nombre de faces", // domain axis label
				"temps en [ms]", // range axis label
				new DefaultCategoryDataset(), // data
				PlotOrientation.VERTICAL, false, // include legend
				true, // tooltips?
				false // URL generator?  Not required...
		);

		// get a reference to the plot for further customisation...
		CategoryPlot plot = (CategoryPlot)chart.getPlot();

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
		//pass
		}

	private void appearance()
		{
		//pass
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tools
	private JFreeChart chart;

	}
