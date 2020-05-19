                  /*  File:  dblIntV0c.java    */

import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;

public class dblIntV0c extends Template {

     static {templateClass = new dblIntV0c() ; }

     public void setup() {
          filePrefix = "dblIntV0c" ;
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 2.5*1.0 ;   
          ysize = 1.5*1.0 ;   
          xmin = -1.1  ;
          xmax = 1.4 ;
          ymin = -0.1 ;
          ymax = 1.4 ;
          topmargin = 0.0 ;
          bottommargin = 0 ;
          leftmargin = 0.0 ;
          rightmargin = 0 ;
          useZoom = true ;
          useDrag = true ;
          showCoords = true ;
          showCanvasBoundary = true ;
     }

     public  void prepareFigure(Figure canvas) {
          fEnv("lineWidth", 0.5) ;
          fEnv("psFillGray", 0.9) ;
          fCurve("y=1-x*x", 0, -1, 1, FILLED) ;

    

          fLine(xmin, 0.0, xmax, 0.0) ;
          fLine(0.0, ymin, 0.0, ymax) ;
          fCurve("y=1-x*x", 0, xmin, xmax, OPEN) ;


          String Red = "0 1 1 0 setcmykcolor  " ;
          String Blue = "1 1 0 0 setcmykcolor  " ;
          canvas.append( new fPsWrite(Red+"\n")) ;
          fEnv("useColorPs", "true") ;
          fEnv("psFillColor", Red) ;

          fEnv("lineWidth", 2.0) ;
          fCurve("y=1-x*x", 0, -1, 1, OPEN) ;
          fLine(-1,0, 1,0) ;

          double r = 0.04 ;
          fDisk(-1,0,r,r,FILLED) ;
          fDisk(1,0,r,r,FILLED) ;
          fDisk(0,1,r,r,FILLED) ;

          double y = 0.5 ; double x = Math.sqrt(1-y) ;
          fEnv("lineWidth", 4.0) ;
          canvas.append( new fPsWrite(Blue+"\n")) ;
          fLine(-x,y, x,y) ;



          fTeXlabel(xmax, -0.05, "tr", "$x$") ;
          fTeXlabel(-0.03, ymax, "tr", "$y$") ;
          fTeXlabel(-x-0.07, y, "cr", "$x=L(y)=-\\sqrt{1-y}$") ;
          fTeXlabel(x+0.07, y, "cl", "$x=R(y)=\\sqrt{1-y}$") ;
//          fTeXlabel(0.2, 0.5, "cc", "$\\cR$") ;
          fTeXlabel(-0.97, -0.05, "tl", "$\\scriptstyle (-1,0)$") ;
          fTeXlabel(0.97, -0.05, "tr", "$\\scriptstyle (1,0)$") ;
          fTeXlabel(-0.03, 1.03, "br", "$\\scriptstyle (0,1)$") ;
     }
}
