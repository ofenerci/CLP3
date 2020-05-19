                  /*  File:  OE15D_7C.java    */


import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;


public class OE15D_7C extends Template {

     static {templateClass = new OE15D_7C() ; }
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
     static double[] fwd = {1, 0.2, 0.9} ; 
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
          filePrefix = "OE15D_7C" ;  
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 4.0 ;    
          ysize = 4.0 ;  

          double Xmin= -10 ;
          double Xmax= 90 ;
          double Ymin= -90 ;
          double Ymax= 90 ;
          double Zmin= -30 ;
          double Zmax= 90 ;
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
          double r = 30 ;
          double rr = 2 ;
//          double xm = 100 ;
//          double ym = 90 ;
//          double zm = 90 ;


          fEnv("lineWidth", 0.5) ;
//          fLine3d(0,0,0, 0,0,zm) ;
//          fLine3d(0,0,0, 0,ym,0) ;
//          fLine3d(0,0,0, xm,0,0) ;
//          fTeXlabel3d(0,0,zm+3, "cb", "$z$") ;
//          fTeXlabel3d(0,ym+3,0, "cl", "$y$") ;
//          fTeXlabel3d(xm,-1,-1, "tr", "$x$") ;


//          fEnv("useColorPs", "true") ;
          String Red = "0 1 1 0 setcmykcolor  " ;
          String Black = "0 0 0 1 setcmykcolor  " ;
          String Blue = "1 1 0 0 setcmykcolor  " ;

          fEnv("psFillGray", 0.8) ;
          double X = 0.7*r ;
          double Y = Math.sqrt(r*r-X*X) ;
          {  double[] xlist = { X,  X,  X, X} ;
             double[] ylist = {-Y,  Y,  Y, -Y} ;
             double[] zlist = {-r, -r,  Y, -Y} ;
             fPolygon3d(xlist, ylist, zlist, 4, FILLED) ; }
//          fEnv("psFillGray", 0.8) ;
//          fCurve(new circ3d(proj,   0,0,z1 , r1),0,90,FILLED)  ;
//          double sq2 = Math.sqrt(2) ;
//          {  double[] xlist = {0,  r1, r1/sq2,  0} ;
//             double[] ylist = {0,   0, r1/sq2, r1} ;
//             double[] zlist = {z1, z1,     z1, z1} ;
//             fPolygon3d(xlist, ylist, zlist, 4, FILLED) ; }

          fEnv("lineWidth", 1.2) ;
          canvas.append( new fPsWrite(Red+"\n")) ;
               fCurve(new circ3d(proj,   0,0,-r, r,0,0, 0,r,0),
                      -90,90,OPEN)  ;
               fTeXlabel3d(0,r+3,-0.3*r, "cl", "$x^2+y^2=1$") ;
               fCurve(new interSect(proj, r),
                      -90,90,OPEN)  ;
               fLine3d(0,-r,-r, 0,r,r) ;
               fLine3d(0,-r,-r, 0,r,-r) ;
               fLine3d(0, r,-r, 0,r, r) ;

          canvas.append( new fPsWrite("0.7 setgray\n")) ;
//               fCurve(new circ3d(proj,   0,0, r, r,0,0, 0,r,0),
//                      -90,90,OPEN)  ;
//               fLine3d(0,-r,  r, 0,r,r) ;
//               fLine3d(0, -r,-r, 0,-r,r) ;
//               double X =2.0*r ;
//               fLine3d(0, -r,-r, X,-r,-r) ;
//               fLine3d(0,  r, r, X, r, r) ;
//               fLine3d(X,-r,-r, X, r, r) ;
//               fTeXlabel3d(X,-r-2,-r, "br", "$y=z$") ;

          fEnv("psFillGray", 0.0) ;
               fDisk3d(0,-r,-r, rr, rr) ;
               fTeXlabel3d(0,-r-3,-r, "br", "$(0,-1,-1)$") ;
               fDisk3d(0, r, r, rr, rr) ;
               fTeXlabel3d(0, r+4, r, "cl", "$(0, 1, 1)$") ;
               fDisk3d(X,-Y,-r, rr, rr) ;
               fTeXlabel3d(X,-Y-3,-r, "tr", "$(x,-\\sqrt{1-x^2},-1)$") ;
               fDisk3d(X, Y, -r, rr, rr) ;
               fTeXlabel3d(X, Y+4, -r, "tl", "$(x, \\sqrt{1-x^2}, -1)$") ;
               fDisk3d(X, Y, Y, rr, rr) ;
               fDisk3d(X, -Y, -Y, rr, rr) ;
          canvas.append( new fPsWrite("0.0 setgray\n")) ;
          fEnv("lineWidth", 0.5) ;
          fEnv("headHalfWidth", 2*1.2) ;
          fEnv("headLength", 6*1.2) ;
             fArrow3d(X, 1.35*r, 1.4*Y, X,Y+1.5,Y) ;
             fTeXlabel3d(X, 1.38*r, 1.42*Y, "cl", "$(x, \\sqrt{1-x^2}, \\sqrt{1-x^2})$") ;
             fArrow3d(X, -2.0*Y, -1.2*Y+1, X,-Y-1.5,-Y) ;
             fTeXlabel3d(X, -2.0*Y-1,-1.2*Y+1, "cr", "$(x,-\\sqrt{1-x^2},-\\sqrt{1-x^2})$");



          fEnv("lineWidth", 1.2) ;
          canvas.append( new fPsWrite(Red+"\n")) ;
//               canvas.append( new fPsWrite("[2 2] 1.0 setdash\n")) ;

          
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

class interSect implements S2V {

     double[][] proj = { {1, 0, 0},   { 0,  1, 0}   };
     double r = 10 ;

   public interSect(double[][] proj, double r) {
        this.proj = proj ;
        this.r = r ;
   }


   public double[] map(double t) {
          
          double[] out = {0,0} ;
          double x = r*Math.cos(t*Math.PI/180) ;
          double y = r*Math.sin(t*Math.PI/180) ;
          double z = y ;
          out[0] = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          out[1] = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;
          return out ;
   }
}


