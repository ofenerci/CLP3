                  /*  File:  polar2A.java    */


import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;


public class polar2A extends Template {

     static {templateClass = new polar2A() ; }

     public void setup() {
          filePrefix = "polar2A" ;  // used as the prefix for any ps or lbl files written
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 3.0*0.7 ;      // in inches
          ysize = 1.6*0.7 ;   // in inches. 
          xmin = -1.5 ;
          xmax = 1.5 ;
          ymin = -0.1 ;
          ymax =  1.5 ;
          topmargin = 0.0 ;   // in inches
          bottommargin = 0 ;   // in inches
          leftmargin = 0 ;   // in inches
          rightmargin = 0 ;   // in inches
          useZoom = true ;
          useDrag = true ;
          showCoords = true ;
          showCanvasBoundary = true ;   
     }
     
     public  void prepareFigure(Figure canvas) {
          fEnv("lineWidth", 0.5) ;
          fLine(xmin,0,xmax,0) ;
          fLine(0,ymin,0,ymax) ;
          fTeXlabel(xmax,-0.05, "tr", "$x$") ;
          fTeXlabel(-0.05, ymax, "tr", "$y$") ;

          double x1 = 1  ; double y1 = 1 ;
          double xa1 = 1/Math.sqrt(2)  ; double ya1 = 1/Math.sqrt(2)  ;
          double th1 = 45 ; double thrad1=th1*Math.PI/180.0 ;
          double x2 = -1 ; double y2 = 1 ;
          double th2 = 135 ; double thrad2=th2*Math.PI/180.0 ;
          double xa2 = -1/Math.sqrt(2)  ; double ya2 = 1/Math.sqrt(2)  ;

          fEnv("useColorPs", "true") ;
          String Black = "0 0 0 1 setcmykcolor  " ;
          String Blue = "0 0.53333 0.8 setrgbcolor  " ;
          String Pink = "1 0.3333 0.6666 setrgbcolor  " ;

          canvas.append( new fPsWrite(Blue+"\n")) ;
          fEnv("psFillColor", Blue) ;
          double rr = 0.05 ;
          double r1 = 0.4 ;
          double r2 = 0.3 ;
          fLine(0, 0,  x1, y1) ;
          fDisk(x1,y1, rr, rr, FILLED) ;
          fTeXlabel(x1,y1+0.1, "bc", "$(1,1)$") ;
          fTeXlabel(0.6*x1,0.6*y1, "br", "$\\sqrt{2}$") ;

          fLine(0, 0,  x2, y2) ;
          fDisk(x2,y2, rr, rr, FILLED) ;
          fTeXlabel(x2-0.1,y2+0.1, "bc", "$(-1,1)$") ;


          fEnv("lineWidth", 0.5) ;
          canvas.append( new fPsWrite(Black+"\n")) ;

          fEllipticalArc(0, 0, r1, r1, 0, th1, OPEN) ;
          fTeXlabel(r1, 0.07, "bl", "$\\frac{\\pi}{4}$") ;
          fEllipticalArc(0, 0, r2, r2, 0, th2, OPEN) ;
          fTeXlabel(-0.05, r2, "br", "$\\frac{3\\pi}{4}$") ;

     }
}
