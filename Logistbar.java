/*
<title>Logistic Bar </title>
</hr>
<center>
<applet code="Logistbar.class" width=700 height=500>
<b>Your browser does not support Java applets.</b>
</applet>
</center>

<p align=right>
<small>
<small>
Written by
<A HREF="http://www.neuroinformatik.ruhr-uni-bochum.de/ini/PEOPLE/loos">
Hartmut S. Loos</A>
</small>
</small>
</hr>

*/


import java.awt.*;
import java.applet.Applet;

/**
 * A class implementing the first exercise
 *
 */
public class Logistbar extends Applet {
  final String VERSION = "v1.0";
  final boolean DEBUG = false;
  double alpha = 1.0;
  double delta = 0.1;
  TextField alphaT;
  TextField deltaT;
  bar_Graph graph1;
  
   private int orientation;
   private String title;
   private int columns;
   private int values[];
   private Color colors[];
   private String labels[];
   private int styles[];
   private int scale = 10;

  protected final static String CLEAR = "Clear";
  protected final static String PLOT = "Plot";
  
  public void init() {
  }

  public Logistbar() {
    int orientation = 0;
    int columns=4;
    String title="Chart";
    int scale=5;
    values = new int[columns];
    labels = new String[columns];
    styles = new int[columns];
    colors = new Color[columns];
    
      values[0]=10;
      values[1]=20; 
      values[2]=5;
      values[3]=30;
      
      labels[0]="Q1";
      labels[1]="Q2";
      labels[2]="Q3";
      labels[3]="Q4";
      
      styles[0]=0;
      styles[1]=0;
      styles[2]=0;
      styles[3]=0;
      
      colors[0]=Color.red;
      colors[1]=Color.red;
      colors[2]=Color.red;
      colors[3]=Color.red;    
     //System.out.println(columns + ":" + orientation + ":" + title + ":" + scale); 
	graph1 = new bar_Graph(columns, orientation,title,scale, 
	values,styles,labels,colors);
	
	setLayout(new BorderLayout());

    Panel gridP = new Panel();
    gridP.setLayout(new GridLayout(2,3));
	
    Panel p11 = new Panel();
	p11.setLayout(new BorderLayout());
	p11.add("North", new Label("Graph 1: x_0 = 0.0"));
	p11.add("Center", graph1);
    gridP.add(p11);

	add("Center", gridP);
	Panel pSouth = new Panel();

	Double aD = new Double(alpha);
	Double dD = new Double(delta);

	pSouth.add( new Label("Alpha =", Label.RIGHT) );
	pSouth.add(alphaT = new TextField(aD.toString(), 4) );
	pSouth.add( new Label("Delta =", Label.RIGHT) );
	pSouth.add(deltaT = new TextField(dD.toString(), 4) );
	pSouth.add(new Button(PLOT));
	pSouth.add(new Button(CLEAR));
	add("South", pSouth);
  }
  
 
  public boolean handleEvent(Event evt) {
	if (CLEAR.equals(evt.arg)) {
	  graph1.clear();
	  return true;

	} else if (PLOT.equals(evt.arg)) {

	  try {
		alpha = (Double.valueOf(alphaT.getText().trim())).doubleValue();
	  } catch (NumberFormatException e) {};

	  try {
		delta = (Double.valueOf(deltaT.getText().trim())).doubleValue();
	  } catch (NumberFormatException e) {};

	  if (DEBUG)
		System.out.println( "(alpha, delta) =  (" + alpha
							+ ", " + delta + ")" );
	  graph1.paint();
	  return true;

	}
	return super.handleEvent(evt);
  }



  public String getAppletInfo() {
    String versionInfo = "Logist " + VERSION + ". Written by Hartmut S. Loos";
    return versionInfo;
  }

}
