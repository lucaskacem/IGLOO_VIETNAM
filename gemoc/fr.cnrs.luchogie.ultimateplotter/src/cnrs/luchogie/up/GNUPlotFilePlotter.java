package cnrs.luchogie.up;




import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;

import cnrs.luchogie.up.data.Figure;
import cnrs.luchogie.up.data.Point;

/**
 * The user may want to get an image object or the data of an
 * image file (PNG, JPEG, SVG...) for, for instance, return it to a 
 * HTTP-client that will have to draw the image on the web page
 * it will show.
 * 
 * @author Luc Hogie
 */
public class GNUPlotFilePlotter extends DelegPlotter
{
	/**
	 * Creates the GNUplot data.
	 * @return String
	 */
	public String getGNUplotData()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("# GNUPlot file generated by UP (http://amy.sunsite.dk/up)"
			+ " on " + new Date(System.currentTimeMillis()).toString()
			+ " at " + new Time(System.currentTimeMillis()).toString()
			+ "\n");

		if ( getGraphics2DPlotter().getFigure() != null )
		{
			Collection collection = getGraphics2DPlotter().getFigure().retrieveAllFigures();
			buf.append("# " + collection.size() + " figure(s) listed\n\n");
			Iterator figures = collection.iterator();
		
			while (figures.hasNext())
			{
				Figure fig = (Figure) figures.next();
		
				buf.append("# figure '" + fig.getName() + "' composed of " + fig.getPointCount() + " point(s)\n");
				String xText = getGraphics2DPlotter().getSpace().getXDimension().getLegend().getText();
				String yText = getGraphics2DPlotter().getSpace().getXDimension().getLegend().getText();
				buf.append("# \t" +  xText + "\t" + yText + "\n\n");

				for (int i = 0; i < fig.getPointCount(); ++i)
				{
					Point p = fig.getPointAt(i);
					buf.append("\t");
					buf.append(p.getX());
					buf.append("\t");
					buf.append(p.getY());
					buf.append("\n");
				}

				buf.append("\n");
			}
		}

		buf.append("# end of file");

		return buf.toString();
	}
}
