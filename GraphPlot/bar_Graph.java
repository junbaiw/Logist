import java.awt.*;
import java.util.*;

public class bar_Graph extends Canvas {
    private static final int VERTICAL = 0;
    private static final int HORIZONTAL = 1;

    private static final int SOLID = 0;
    private static final int STRIPED = 1;

    private int orientation;
    private String title;
    private Font font;
    private FontMetrics metrics;
    private int fontHeight = 15;
    private int columns;
    private int values[];
    private Color colors[];
    private String labels[];
    private int styles[];
    private int scale = 10;
    private int maxLabelWidth = 0;
    private int barSpacing = 10;
    private int maxValue = 0;
    private int isClear=0;    
    Graphics g;
    
    // JBW The construct
    public bar_Graph(int temp_columns, int temp_orientation,
    String temp_title, int temp_scale, int temp_values[], int temp_styles[],
    String temp_labels[], Color temp_colors[]){
      super();
      font = new java.awt.Font("Courier", Font.BOLD, 12);
      metrics = getFontMetrics(font);
      orientation =temp_orientation;
      columns=temp_columns;
      title=temp_title;
      scale=temp_scale;
      
      
      values = new int[columns];
      labels = new String[columns];
      styles = new int[columns];
      colors = new Color[columns];
      
      for (int i=0; i<columns; i++)  {
        values[i]=temp_values[i];
        labels[i]=temp_labels[i];
        styles[i]=temp_styles[i];
        colors[i]=temp_colors[i];
        maxValue=Math.max(maxValue,values[i]);
        maxLabelWidth = Math.max(metrics.stringWidth
                                 ((String) (labels[i])), maxLabelWidth);
      }
      show();
    }

    public void update(Graphics g) {
      paint(g);
    }

    public void paint() {
     paint(this.getGraphics());
    }

    public void paint(Graphics g) {
        // draw the title centered at the bottom of the bar graph
        g.setColor(Color.black);
        g.setFont(font);
        
        if (isClear== 0) {
          g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);

          int titleWidth = metrics.stringWidth(title);
          int cx = Math.max((getSize().width - titleWidth) / 2, 0);
          int cy = getSize().height - metrics.getDescent();
          g.drawString(title, cx, cy);
        
          // draw the bars and their titles
          if(orientation == HORIZONTAL) {
            paintHorizontal(g);
          } else {  // VERTICAL
            paintVertical(g);
          }
        } else {
          g.clearRect(0, 0, getSize().width - 1, getSize().height - 1);
          isClear=0;
        }
    }
        
    private void paintHorizontal(Graphics g) {
        // x and y coordinates to draw/write to 
        int cx, cy;
        int barHeight = metrics.getHeight();

        for (int i = 0; i < columns; i++) {
            
            // set the X coordinate for this bar and label and center it
            int widthOfItems = maxLabelWidth + 3 + (maxValue * scale) + 5  
                + metrics.stringWidth(Integer.toString(maxValue));
            cx = Math.max((getSize().width - widthOfItems) / 2, 0);
            
            // set the Y coordinate for this bar and label
            cy = getSize().height - metrics.getDescent() - metrics.getHeight()
                - barSpacing - ((columns - i - 1) * (barSpacing + barHeight));

            // draw the label
            g.setColor(Color.black);
            g.drawString(labels[i], cx, cy);
            cx += maxLabelWidth + 3;

        
            // draw the shadow
            g.fillRect(cx + 4, cy - barHeight + 4,
                       (values[i] * scale), barHeight);

            // draw the bar
            g.setColor(colors[i]);
            if (styles[i] == STRIPED) {
                for (int k = 0; k <= values[i] * scale; k += 2) {
                    g.drawLine(cx + k, cy - barHeight, cx + k, cy);
                }
            } else {      // SOLID
                g.fillRect(cx, cy - barHeight, 
                           (values[i] * scale) + 1, barHeight + 1);
            }
            cx += (values[i] * scale) + 4;
            
            // draw the value at the end of the bar 
            g.setColor(g.getColor().darker());
            g.drawString(Integer.toString(values[i]), cx, cy);
        } 
    }
    
    private void paintVertical(Graphics g) {
        int barWidth = maxLabelWidth;
        for (int i = 0; i < columns; i++) {
            // X coordinate for this label and bar (centered)
            int widthOfItems = (barWidth + barSpacing) * columns - barSpacing;
            int cx = Math.max((getSize().width - widthOfItems) / 2, 0);
            cx += (maxLabelWidth + barSpacing) * i;
            
            // Y coordinate for this label and bar
            int cy = getSize().height - metrics.getHeight()
                - metrics.getDescent() - 4;

            // draw the label
            g.setColor(Color.black);
            g.drawString(labels[i], cx, cy);
            cy -= metrics.getHeight() - 3;

            // draw the shadow
            g.fillRect(cx + 4, cy - (values[i] * scale) - 4, 
                       barWidth, (values[i] * scale));
            
            // draw the bar
            g.setColor(colors[i]);
            if (styles[i] == STRIPED) {
                for (int k=0; k <= values[i] * scale; k+=2) {
                    g.drawLine(cx, cy - k, 
                               cx + barWidth, cy - k);
                }
            } else {
                g.fillRect(cx, cy - (values[i] * scale), 
                           barWidth + 1, (values[i] * scale) + 1);
            }
            cy -= (values[i] * scale) + 5;

            // draw the value on top of the bar
            g.setColor(g.getColor().darker());
            g.drawString(Integer.toString(values[i]), cx, cy);
        }
    }    
    
    public void clear() {
      isClear=1;
      //data.removeAllElements();
      paint(this.getGraphics());
    }
    
    public String getAppletInfo() {
        return "Title: Bar Chart \n"
            + "Author: Sami Shaio \n"
            + "A simple bar chart demo.";
    }
    
    public String[][] getParameterInfo() {
        String[][] info = {
            {"title", "string", "The title of bar graph.  Default is 'Chart'"},
            {"scale", "int", "The scale of the bar graph.  Default is 10."},
            {"columns", "int", "The number of columns/rows.  Default is 5."},
            {"orientation", "{VERTICAL, HORIZONTAL}", 
             "The orienation of the bar graph.  Default is VERTICAL."},
            {"c#", "int", "Subsitute a number for #.  " 
             + "The value/size of bar #.  Default is 0."},
            {"c#_label", "string", "The label for bar #.  "
             + "Default is an empty label."},
            {"c#_style", "{SOLID, STRIPED}", "The style of bar #.  "
             + "Default is SOLID."},
            {"c#_color", "{RED, GREEN, BLUE, PINK, ORANGE, MAGENTA, CYAN, "
             + "WHITE, YELLOW, GRAY, DARKGRAY}", 
             "The color of bar #.  Default is GRAY."}
        };
        return info;
    }
}
