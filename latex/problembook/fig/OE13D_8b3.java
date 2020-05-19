                  /*  File:  OE13D_8b3.java    */


import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;


public class OE13D_8b3 extends Template {

     static {templateClass = new OE13D_8b3() ; }
     static double x0 = 100 ;
     static double x1 =   0 ;




// Oblique projection with direction of view (1,-Xy, -Xz). The point 
// (x,y,z) is projected to (0, yp, zp) = (x,y,z) + a (1,-Xy, -Xz) with a 
// chosen so that x+a=0. 
//     static double al = 60 ;
//     // cabinet projection
//     static double[][] proj = { {0.5*Math.cos(Math.PI*(1+al/180)), 1, 0},
//                                {0.5*Math.sin(Math.PI*(1+al/180)), 0, 1}   } ;
//     // cavalier projection
//     static double[][] proj = { {Math.cos(Math.PI*(1+al/180)), 1, 0},
//                                {Math.sin(Math.PI*(1+al/180)), 0, 1}   } ;
//     static double[] view = {1, -proj[0][0], -proj[1][0]} ;

////  Orthogonal isometric projection with direction of view (1,1,1).  
//     static double[][] proj = { {-Math.sqrt(3)/2.0, Math.sqrt(3)/2.0, 0},
//                                {             -0.5,             -0.5, 1}   } ;
//     static double[] view = {1, 1, 1} ;

////  General orthogonal projection.  
     static double[] fwd = {1, 0.2, 0.5} ; 
     static double[] up = {0,0,1} ;
     static double Lfwd = Math.sqrt(fwd[0]*fwd[0]+fwd[1]*fwd[1]+fwd[2]*fwd[2]) ; 
     static double[] Nfwd = {fwd[0]/Lfwd,fwd[1]/Lfwd,fwd[2]/Lfwd} ; 

     static double Dot = Nfwd[0]*up[0]+Nfwd[1]*up[1]+Nfwd[2]*up[2] ; 
     static double[] Iup = {up[0]-Dot*Nfwd[0],up[1]-Dot*Nfwd[1],up[2]-Dot*Nfwd[2]};
     static double Lup = Math.sqrt(Iup[0]*Iup[0]+Iup[1]*Iup[1]+Iup[2]*Iup[2]) ; 
     static double[] Nup = {Iup[0]/Lup,Iup[1]/Lup,Iup[2]/Lup} ; 
     static double[] Nleft = {-Nfwd[1]*Nup[2]+Nfwd[2]*Nup[1],
                              -Nfwd[2]*Nup[0]+Nfwd[0]*Nup[2],
                              -Nfwd[0]*Nup[1]+Nfwd[1]*Nup[0]} ; 

     static double[][] proj = { {Nleft[0], Nleft[1], Nleft[2]},
                                { Nup[0],  Nup[1], Nup[2]}   } ;
     static double[] view = {fwd[0], fwd[1], fwd[2]} ;



     public void setup() {
          filePrefix = "OE13D_8b3" ;  
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 4.0*0.6 ;    
          ysize = 4.0*0.4 ;  

          double Xmin= -1.2 ;
          double Xmax= 1.2 ;
          double Ymin= -0.5 ;
          double Ymax= 1.5 ;
          double Zmin= -0.2 ;
          double Zmax= 1.2 ;
          xmin = xmax = Xmin*proj[0][0]+Ymin*proj[0][1]+Zmin*proj[0][2] ;
          ymin = ymax = Xmin*proj[1][0]+Ymin*proj[1][1]+Zmin*proj[1][2] ;
          double Dx = (Xmax-Xmin)*proj[0][0] ;
          double Dy = (Xmax-Xmin)*proj[1][0] ;
          if (Dx>0) xmax += Dx ; else xmin += Dx ;
          if (Dy>0) ymax += Dy ; else ymin += Dy ;
          Dx = (Ymax-Ymin)*proj[0][1] ;
          Dy = (Ymax-Ymin)*proj[1][1] ;
          if (Dx>0) xmax += Dx ; else xmin += Dx ;
          if (Dy>0) ymax += Dy ; else ymin += Dy ;
          Dx = (Zmax-Zmin)*proj[0][2] ;
          Dy = (Zmax-Zmin)*proj[1][2] ;
          if (Dx>0) xmax += Dx ; else xmin += Dx ;
          if (Dy>0) ymax += Dy ; else ymin += Dy ;


//          xmin = -70 ;
//          xmax =  90 ;
//          ymin = -70 ;
//          ymax =  90 ;
          topmargin = 0.0 ;   // in inches
          bottommargin = 0.0 ;   // in inches
          leftmargin = 0.0 ;   // in inches
          rightmargin = -0.2 ;   // in inches
          useZoom = true ;
          useDrag = true ;
          showCoords = true ;
          showCanvasBoundary = true ;   
     }
     
     public  void prepareFigure(Figure canvas) {
//          double al = 60 ;
//          x0 = 0.5*Math.cos(Math.PI*(1+al/180)) ;  // cabinet projection
//          x1 = 0.5*Math.sin(Math.PI*(1+al/180)) ;
//          x0 = Math.cos(Math.PI*(1+al/180)) ;  // cavalier projection
//          x1 = Math.sin(Math.PI*(1+al/180)) ;
          double r = 0.03 ;
          double aspect = (ymax-ymin)/(xmax-xmin)*xsize/ysize ;
          double xm = 1.5 ;
          double ym = 1.3 ;
          double zm = 1.2 ;


          fEnv("lineWidth", 0.5) ;
          double xt=1.0 ;
          double yt=0.83 ;
          double zt=1.0 ;
          fLine3d(xt,0,0, xm,0,0) ;
          fLine3d(0,yt,0, 0,ym,0) ;
          fLine3d(0,0,zt, 0,0,zm) ;
          canvas.append( new fPsWrite("[2 2] 1.0 setdash\n")) ;
          fLine3d(0,0,0, xt,0,0) ;
          fLine3d(0,0,0, 0,yt,0) ;
          fLine3d(0,0,0, 0,0,zt) ;
          canvas.append( new fPsWrite("[  ] 0 setdash\n")) ;
          fTeXlabel3d(0,0,zm+0.03, "cb", "$z$") ;
          fTeXlabel3d(0,ym+0.03,0, "cl", "$y$") ;
          fTeXlabel3d(xm,-0.01,-0.01, "tr", "$x$") ;


//          fEnv("useColorPs", "true") ;
          String Red = "0 1 1 0 setcmykcolor  " ;
          String Black = "0 0 0 1 setcmykcolor  " ;
          String Blue = "1 1 0 0 setcmykcolor  " ;


          fEnv("lineWidth", 1.2) ;
          canvas.append( new fPsWrite(Red+"\n")) ;
               fCurve(new parabola(proj, 0),
                      -1,1,OPEN)  ;
               fCurve(new parabola(proj, 1),
                      -1,1,OPEN)  ;
               fLine3d(1,0,0, 1,1,0) ;
               fLine3d(-1,0,0, -1,1,0) ;
               double X = -0.2 ;
               fLine3d(X, 0,1-X*X, X,1, 1-X*X) ;

          canvas.append( new fPsWrite(Blue+"\n")) ;
               fCurve(new interSect(proj),
                      -1,1,OPEN)  ;
               fLine3d(1,0,0, -1,0,0) ;
          fEnv("lineWidth", 0.5) ;
          canvas.append( new fPsWrite("[2 2] 1.0 setdash\n")) ;
               fCurve(new parabolaB(proj),
                      -1,1,OPEN)  ;
          canvas.append( new fPsWrite("[  ] 0 setdash\n")) ;

          canvas.append( new fPsWrite(Black+"\n")) ;
          fDisk3d(1,0,0, r,r*aspect) ; 
          fArrow3d(1,-0.2, 0.3, 1,-0.02,0.02) ;
          fTeXlabel3d(1,-0.2, 0.3, "br", "$(1,0,0)$") ;
//          fTeXlabel3d(0,0.5, 1.1, "bc", "$\\scriptstyle z=1-x^2$") ;
//          fTeXlabel3d(0,0.6, 1.15, "bc", "$z=1\\!-\\!x^2$") ;
          fTeXlabel3d(-1,0.97, 0.4, "cl", "$z=1\\!-\\!x^2$") ;
          X = 0.63 ; double Y=1-X*X ; double Z=Y ;
          fArrow3d(1,1.3, 0, X,Y,Z) ;
          fTeXlabel3d(1,1.32, 0, "cl", "$z=y=1-x^2$") ;
          fArrow3d(1,1.33, -0.4, X,Y,0) ;
          fTeXlabel3d(1,1.3, -0.35, "cl", "$y=1-x^2,\\ z=0$") ;


          
     }

     public void fLine3d(double xl, double yl, double zl,
                         double xr, double yr, double zr) {
          double X1 = xl*proj[0][0]+yl*proj[0][1]+zl*proj[0][2] ;
          double Y1 = xl*proj[1][0]+yl*proj[1][1]+zl*proj[1][2] ;
          double X2 = xr*proj[0][0]+yr*proj[0][1]+zr*proj[0][2] ;
          double Y2 = xr*proj[1][0]+yr*proj[1][1]+zr*proj[1][2] ;
          fLine(X1,Y1,X2,Y2) ;
     }


     public void fArrow3d(double xl, double yl, double zl,
                         double xr, double yr, double zr) {
          double X1 = xl*proj[0][0]+yl*proj[0][1]+zl*proj[0][2] ;
          double Y1 = xl*proj[1][0]+yl*proj[1][1]+zl*proj[1][2] ;
          double X2 = xr*proj[0][0]+yr*proj[0][1]+zr*proj[0][2] ;
          double Y2 = xr*proj[1][0]+yr*proj[1][1]+zr*proj[1][2] ;
          fArrow(X1,Y1,X2,Y2) ;
     }


     public void fTeXlabel3d(double xl, double yl, double zl,
                         String biase, String label) {
          double X = xl*proj[0][0]+yl*proj[0][1]+zl*proj[0][2] ;
          double Y = xl*proj[1][0]+yl*proj[1][1]+zl*proj[1][2] ;

          fTeXlabel(X, Y, biase, label) ;
     }

     public void fPolygon3d(double[] xlist, double[] ylist, double[] zlist,
                                  int nopoints, long type) {
          double[] xx = new double[nopoints] ;
          double[] yy = new double[nopoints] ;
          for (int i = 0 ; i < nopoints ; i++) {
               xx[i] = xlist[i]*proj[0][0]+ylist[i]*proj[0][1]+zlist[i]*proj[0][2] ;
               yy[i] = xlist[i]*proj[1][0]+ylist[i]*proj[1][1]+zlist[i]*proj[1][2] ;
          }
          fPolygon(xx,yy,nopoints,type) ;
     }

     public void fDisk3d(double x, double y, double z,
                         double rx, double ry) {
          double X = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          double Y = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;

          fDisk(X, Y, rx, ry, FILLED) ;
     }


}




class circ3d implements S2V {

     double[][] proj = { {1, 0, 0},   { 0,  1, 0}   };
     double cx = 0.0 ;
     double cy = 0.0 ;
     double cz = 0.0 ;
     double xa = 100.0 ;
     double ya = 0.0 ;
     double za = 0.0 ;
     double xb = 0.0 ;
     double yb = 100.0 ;
     double zb = 0.0 ;

   public circ3d(double[][] proj, 
                 double cx, double cy, double cz, double r) {
        this.proj = proj ;
        this.cx = cx ;
        this.cy = cy ;
        this.cz = cz ;
        this.xa = r ; ;
        this.ya = 0 ;
        this.za = 0 ;
        this.xb = 0 ;
        this.yb = r ;
        this.zb = 0 ;
   }


   public circ3d(double[][] proj, 
                 double cx, double cy, double cz, 
                 double xa, double ya, double za, 
                 double xb, double yb, double zb) {
        this.proj = proj ;
        this.cx = cx ;
        this.cy = cy ;
        this.cz = cz ;
        this.xa = xa ;
        this.ya = ya ;
        this.za = za ;
        this.xb = xb ;
        this.yb = yb ;
        this.zb = zb ;
   }

     public double[] map(double t) {
          
//          double x0 = projFactor*Math.cos(Math.PI*(1+al/180)) ; 
//          double x1 = projFactor*Math.sin(Math.PI*(1+al/180)) ;
          double[] out = {0,0} ;
          double x = cx+xa*Math.cos(t*Math.PI/180)+xb*Math.sin(t*Math.PI/180) ;
          double y = cy+ya*Math.cos(t*Math.PI/180)+yb*Math.sin(t*Math.PI/180) ;
          double z = cz+za*Math.cos(t*Math.PI/180)+zb*Math.sin(t*Math.PI/180) ;
          out[0] = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          out[1] = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;

//          out[0] = x*x0+y ;
//          out[1] = x*x1+z;
          return out ;
   }
}

class parabola implements S2V {

     double[][] proj = { {1, 0, 0},   { 0,  1, 0}   };
     double Y = 1 ;

   public parabola(double[][] proj, double Y) {
        this.proj = proj ;
        this.Y = Y ;
   }


   public double[] map(double t) {
          
          double[] out = {0,0} ;
          double x = t ;
          double y = Y ;
          double z = 1-t*t ;
          out[0] = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          out[1] = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;
          return out ;
   }
}

class parabolaB implements S2V {

     double[][] proj = { {1, 0, 0},   { 0,  1, 0}   };

   public parabolaB(double[][] proj) {
        this.proj = proj ;
   }


   public double[] map(double t) {
          
          double[] out = {0,0} ;
          double x = t ;
          double y = 1-t*t ;
          double z = 0 ;
          out[0] = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          out[1] = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;
          return out ;
   }
}


class interSect implements S2V {

     double[][] proj = { {1, 0, 0},   { 0,  1, 0}   };

   public interSect(double[][] proj) {
        this.proj = proj ;
   }


   public double[] map(double t) {
          
          double[] out = {0,0} ;
          double x = t ;
          double y = 1-t*t ;
          double z = y ;
          out[0] = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          out[1] = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;
          return out ;
   }
}



