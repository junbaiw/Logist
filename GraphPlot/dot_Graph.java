//This program originally written by  Hartmut S. Loos 
//Then modified by J.B.Wang
//
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class dot_Graph extends Canvas 
implements MouseListener, MouseMotionListener
{
  private static final int LINE = 0;
  private static final int DOT = 1;
  private final boolean DEBUG = false;
  private double startX;
  private double endX;
  private double startY;
  private double endY;
  private int style;
  private double realx,realy;
  int mousex=0, mousey=0;
  String msg="";
  
  Graphics g;
  private Vector  data;
  double width;
  double height;

  public dot_Graph(double x1, double y1, double x2, double y2, int temp_style) {
    super();
    addMouseListener(this);
    style=temp_style;
	startX = x1;
	endX = x2;
	startY = y1;
	endY = y2;
	width = x2 - x1;
	height = y2 - y1;
    data = new Vector();
    show();
  }
  
  public void update(Graphics g) {
    paint(g);
  }

  public void paint() {
    paint(this.getGraphics());
  }

  public void paint(Graphics g) {
    double sx, sy;
    Graphics da;
    Rectangle size;
    Rectangle Diagram;
    // get bounds
    da = this.getGraphics();
    size = this.bounds();

   
	if (DEBUG) {
	  System.out.println( "(width, height) =  (" + width
						  + ", " + height + ")" );
	  System.out.println( "(sizeX, sizeY) =  (" + size.width
						  + ", " + size.height + ")" );
	}

    Diagram = new Rectangle(40, 10, size.width - 50, size.height - 30);

    da.setColor(Color.gray);
    da.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
    da.setColor(Color.white);
    da.fillRect(1, 1, size.width - 2, size.height - 2);

    // paint drawing aera
    da.translate(Diagram.x, Diagram.y);
    Color paleyellow = new Color(255, 250, 230);
    da.setColor(paleyellow);
    da.fillRect(0, 0, Diagram.width, Diagram.height);

    sx = ((double) Diagram.width) / width;
    sy = ((double) Diagram.height) / height;

	if (DEBUG)
	  System.out.println( "(sx, sy) = (" + sx + ", " + sy + ")" );

    // draw grids
    da.setFont( new Font("Dialog", Font.PLAIN, 12) );
    da.setColor( new Color(255, 200, 100) );

    for (int i = 0; i < 6; i++) {
	  double x = (Diagram.width/5.0) * i;

	  da.setColor(Color.orange);
	  da.drawLine((int) x, 0, (int) x, Diagram.height);
	  da.setColor(Color.red);
	  String temps=Double.toString(startX + x / sx);
	  int ln_temps=temps.length();
	  if (ln_temps > 5) {  
	     String s=temps.substring(0,5);
	     da.drawString(s, (int) x - 10, Diagram.height + 15);
	  } else { 
	     String s=temps; 
	     da.drawString(s, (int) x - 10, Diagram.height + 15);
	  }
	}
	

    for (int i = 0; i < 11; i++) {
	  double y = (Diagram.height/10.0) * i;
	  int ry = (int)(Diagram.height - y);

	  da.setColor(Color.orange);
	  da.drawLine(0, ry, Diagram.width, ry);
	  da.setColor(Color.red);
	  String temps=Double.toString(startY + y / sy);
	  int ln_temps=temps.length();
	  if (ln_temps > 5) {  
	     String s=temps.substring(0,5);
	     da.drawString(s, -35, ry + 5);
	  } else { 
	     String s=temps; 
	     da.drawString(s, -35, ry + 5);
	  }  
	}
	
    //jbw auto detect Xasis and Yaxus
    int zerox, zeroy;
    double tp_zx, tp_zy;
    tp_zx=(0-startX)*sx;
    tp_zy=(0-startY)*sy;
    zerox=(int) tp_zx;
    zeroy=Diagram.height-(int) tp_zy;
    da.setColor(Color.red);
	// x-axis
    da.drawLine(0, zeroy,Diagram.width, zeroy);
	// y-axis
    da.drawLine(zerox, 0, zerox, Diagram.height);

    
    
    
    // paint traces
    Enumeration enum = data.elements();
	int nx, ny, mouse_nx, mouse_ny, ox = -1, oy = -1;
	double npx, mouse_npx;
	double npy, mouse_npy;
	

	da.setColor(Color.black);

    while ( enum.hasMoreElements() ) {

	  DPoint p = (DPoint) (enum.nextElement());

	  if ( (p.x == Double.NEGATIVE_INFINITY)
		   && (p.y == Double.POSITIVE_INFINITY) ) {
		ox = oy = -1;
		continue;
	  }

	  npx = (p.x - startX) * sx;
	  npy = (p.y - startY) * sy;

	  nx = (int) npx;
	  ny = Diagram.height - (int) npy;
	  if (oy > 0) {

		if (DEBUG)
		  System.out.print( " (" + npx + ", " + npy + ")" );
        //jbw draw dots  or lines  
	   if(style == LINE) {
            da.drawLine(ox, oy, nx, ny);
        } else {  // DOT
            da.fillOval(nx,ny,5,5);
        }
	  }
	  ox = nx;
	  oy = ny;
	}
	
    int mx, my;
    mx=mousex-40;
    my=mousey-10;
    realx=mx/sx+startX;
    realy=(Diagram.height-my)/sy+startY;
    if (msg!=""){
        msg=new String(Double.toString(realx)+":"+Double.toString(realy));
   	    da.drawString(msg,mx,my);
   	} else {
   	    da.drawString(msg,mx,my);
   	}

	if (DEBUG)
	  System.out.println();

	da.setColor(Color.magenta);
  }
    
  public void add(DPoint p) {

    // add the value to the storage vector
    data.addElement( new DPoint(p) );

	if ( (p.x == Double.NEGATIVE_INFINITY)
		 && (p.y == Double.POSITIVE_INFINITY) )
	  return;

    if ( p.y > height) {
	  height = (int)(1.05 * p.y);
	  //paint(this.getGraphics());
	}

    if (p.x > width) {
	  width = (int)(1.05 * p.x);
	  //paint(this.getGraphics());
	}

  }  

  public void clear() {
	//    width = endX - startX;
	//    height = endY - startY;
	data.removeAllElements();
    // data.addElement( new Vector() );
    paint(this.getGraphics());
  }
  
    
  //jbw 1.1 event handling
   public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    addMouseMotionListener(this);
	mousex = e.getX();
	mousey = e.getY();
	msg=new String(Double.toString(realx)+":"+Double.toString(realy));
    repaint();
	//e.consume();
    }

    public void mouseReleased(MouseEvent e) {
        removeMouseMotionListener(this);
	mousex = e.getX();
	mousey = e.getY();
	msg="";
	repaint();
	//e.consume();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}
