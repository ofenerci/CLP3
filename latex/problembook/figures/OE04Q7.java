                  /*  File:  OE04Q7.java    */


import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;


public class OE04Q7 extends Template {

     static {templateClass = new OE04Q7() ; }

     public void setup() {
          filePrefix = "OE04Q7" ;    
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 1.3*1.2 ;  
          ysize = 1.4*1.2 ;   
          xmin = -0.1 ;
          xmax = 1.2 ;
          ymin = -0.1 ;
          ymax = 1.3 ;
          topmargin = 0.0 ;   // in inches
          bottommargin = 0.0 ;   // in inches
          leftmargin = 0 ;   // in inches
          rightmargin = 0 ;   // in inches
          useZoom = true ;
          useDrag = true ;
          showCoords = true ;
          showCanvasBoundary = true ;   
     }
     
     public  void prepareFigure(Figure canvas) {
          fEnv("lineWidth", 0.5) ;
          fLine(xmin, 0, xmax, 0) ;
          fLine(0, ymin, 0, ymax) ;
          fLine(ymin, ymin, ymax, ymax) ;
          fLine(-0.05, 1, xmax, 1) ;
          fTeXlabel(xmax, -0.04, "tr", "$x$") ;
          fTeXlabel(-0.02, ymax, "tr", "$y$") ;
          fTeXlabel(-0.07, 1, "rc", "$1$") ;
          fLine(1, -0.05, 1, 0) ;
          fTeXlabel(1, -0.09, "tc", "$1$") ;

          fEnv("psFillGray", 0.8) ;
          double[] x = {0, 0, 1} ;
          double[] y = {0, 1, 1} ;
          fPolygon(x, y, 3, FILLED) ;
          fTeXlabel(xmax+0.05, xmax, "lc", "$x=y$") ;
          fTeXlabel(xmax+0.05, 1, "lc", "$y=1$") ;

          fEnv("lineWidth", 4.0) ;
          String Blue = "0 0.53333 0.8 setrgbcolor  " ;
          String Pink = "1 0.3333 0.6666 setrgbcolor  " ;
          String Black = "0 0 0 setrgbcolor  " ;
          canvas.append( new fPsWrite(Pink+"\n")) ;
          fLine(0, 0.7, 0.7, 0.7) ;
          canvas.append( new fPsWrite(Blue+"\n")) ;
          fLine(0.3, 0.3, 0.3, 1.0) ;
          canvas.append( new fPsWrite(Black+"\n")) ;

          fEnv("lineWidth", 1.0) ;
          fEnv("psGray", 0.0) ;
          fPolygon(x, y, 3, CLOSED) ;

          
     }
}
