import java.awt.*;
import java.applet.Applet;

public class Logist2 extends Applet 
{
  final String VERSION = "v1.0";
  final boolean DEBUG = false;
  double alpha = 1.0;
  double delta = 0.1;
  int LINE=0;
  int DOT=1;
  
  TextField alphaT;
  TextField deltaT;

  dot_Graph graph1;

  protected final static String CLEAR = "Clear";
  protected final static String PLOT = "Plot";

  public Logist2() {
	graph1 = new dot_Graph(-1, -1, 1, 1,DOT);
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

  double dgl(double x) {
	return  Math.sin(x);
  }

  void calculate() {
	double t = 0;
	double x_01 = 1;

	DPoint p = new DPoint();
    for (t = -1; t <= 1; t += delta) {	  
	  x_01 = dgl(t);
	  if (DEBUG)
		System.out.println( "(t, x) = (" + t + ", " + x_01 + ")" );
	  p.set(t, x_01);
	  graph1.add(p);
    }

	// sign for end-of-plot
	p.set(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	graph1.add(p);
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
	  calculate();
	  graph1.paint();
	  return true;

	}
	return super.handleEvent(evt);
  }

  public void init() {
	calculate();
  }

  public String getAppletInfo() {
    String versionInfo = "Logist " + VERSION + ". Written by Hartmut S. Loos";
    return versionInfo;
  }

}
