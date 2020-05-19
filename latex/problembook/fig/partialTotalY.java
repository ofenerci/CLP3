                  /*  File:  partialTotalY.java    */


import figPac.* ;
import fnPac.* ;
import java.applet.* ;
import java.awt.* ;


public class partialTotalY extends Template {

     static {templateClass = new partialTotalY() ; }
     static double x0 = 100 ;
     static double x1 =   0 ;




// Oblique projection with direction of view (1,-Xy, -Xz). The point 
// (x,y,z) is projected to (0, yp, zp) = (x,y,z) + a (1,-Xy, -Xz) with a 
// chosen so that x+a=0. 
     static double al = 40 ;
     // cabinet projection
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
     static double[] fwd = {1, 1, 0.4} ; 
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
          filePrefix = "partialTotalY" ;  
          outputPsFile = true ;
          outputLblFile = true ;
          xsize = 2.0 ;    
          ysize = 2.0 ;  

          double Xmin= -10 ;
          double Xmax= 90 ;
          double Ymin= -10 ;
          double Ymax= 90 ;
          double Zmin= -10 ;
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
          rightmargin = 0.0 ;   // in inches
          useZoom = true ;
          useDrag = true ;
          showCoords = true ;
          showCanvasBoundary = true ;   
     }
     
     public  void prepareFigure(Figure canvas) {
          double al = 60 ;
          x0 = 0.5*Math.cos(Math.PI*(1+al/180)) ;  // cabinet projection
          x1 = 0.5*Math.sin(Math.PI*(1+al/180)) ;
//          x0 = Math.cos(Math.PI*(1+al/180)) ;  // cavalier projection
//          x1 = Math.sin(Math.PI*(1+al/180)) ;
          double a = 65 ;
          double r = 80 ;
          double xm = 90 ;
          double ym = 90 ;
          double zm = 90 ;
          int nth = 4 ;
          int nphi = 1 ;
          double dth = 90/nth ;
          double dphi = 90/nphi ;
          double th1 = dth*2 ;
          double th2 = dth*3 ;
          double phi1 = 55 ;
          double phi2 = 40 ;
          double rr = 2.5 ;


          fEnv("lineWidth", 0.5) ;
          canvas.append( new fPsWrite("0.7 setgray\n")) ;
          fLine3d(0,0,0, 0,0,zm) ;
          fLine3d(0,0,0, 0,ym,0) ;
          fLine3d(0,0,0, xm,0,0) ;
          fTeXlabel3d(0,0,zm+3, "cb", "$z$") ;
          fTeXlabel3d(0,ym+3,0, "cl", "$y$") ;
          fTeXlabel3d(xm,-1,-1, "tr", "$x$") ;

          double xh, yh, xv, yv, zv, th, phi, xpsi, ypsi ;
          double y1, y2, y3, x1, x3, z1, z2, z3, z4, r3 ;

          fEnv("lineWidth", 1.0) ;
          canvas.append( new fPsWrite("0.0 setgray\n")) ;
          th = 0 ;
            xh = Math.cos(th*Math.PI/180);
            yh = Math.sin(th*Math.PI/180);
            fCurve(new circ3d(proj,   0,0,0, 0,0,r, r*xh,r*yh,0),  0,90,OPEN)  ;
          th = 90 ;
            xh = Math.cos(th*Math.PI/180);
            yh = Math.sin(th*Math.PI/180);
            fCurve(new circ3d(proj,   0,0,0, 0,0,r, r*xh,r*yh,0),  0,90,OPEN)  ;
          phi = 90 ;
            yv = Math.sin(phi*Math.PI/180);
            zv = Math.cos(phi*Math.PI/180);
            fCurve(new circ3d(proj,   0,r*zv,0, r*yv),0,90,OPEN)  ;

          fEnv("lineWidth", 0.5) ;
          canvas.append( new fPsWrite("0.7 setgray\n")) ;
          y1 = 0.75*r ; 
            z1 = Math.sqrt(r*r-y1*y1);
            fCurve(new circ3d(proj, 0,y1,0, 0,0,z1, z1,0,0),0,90,OPEN)  ;
            fLine3d(0,y1,0, 0,y1,z1) ;
            fLine3d(0,y1,0, z1,y1,0) ;
            fDisk3d(0,y1,z1, rr, rr, FILLED) ;
          y2 = 0.6*r  ;
            z2 = Math.sqrt(r*r-y2*y2);
            fCurve(new circ3d(proj, 0,y2,0, 0,0,z2, z2,0,0),0,90,OPEN)  ;
            fLine3d(0,y2,0, 0,y2,z2) ;
            fLine3d(0,y2,0, z2,y2,0) ;

          x1 = Math.sqrt(r*r-z1*z1) ;
            fCurve(new circ3d(proj,   0,0,z1, x1),0,90,OPEN)  ;

          z3 = z1 ;
            y3 = y2 ;
            x3 = Math.sqrt(r*r-y3*y3 - z3*z3);
            r3 = Math.sqrt(r*r-x3*x3);
            fCurve(new circ3d(proj, x3,0,0, 0,0,r3, 0,r3,0),0,90,OPEN)  ;
            fLine3d(x3,0,0, x3,r3,0) ;
            fLine3d(x3,0,0, x3,0,r3) ;
            fDisk3d(x3,y3,z3, rr, rr, FILLED) ;
            z4 = Math.sqrt(r*r-x3*x3 - y1*y1);
            fDisk3d(x3,y1,z4, rr, rr, FILLED) ;


          fEnv("useColorPs", "true") ;
          String Black = "0 0 0  setrgbcolor  " ;
          String Blue = "0 0.53333 0.8 setrgbcolor  " ;
          String Orange = "0.93333 0.46666 0.2 setrgbcolor  " ;
          String Pink = "1 0.3333 0.6666 setrgbcolor  " ;

          canvas.append( new fPsWrite("0.0 setgray\n")) ;
//          fCurve(new circ3d(proj,   0,0,0, 0.3*r),th1,th2,OPEN)  ;
//          double thm =0.5*th1 +0.5*th2 ;
//          xh = Math.cos(thm*Math.PI/180);
//          yh = Math.sin(thm*Math.PI/180);
//          fArrow3d(1.3*r*xh, 1.3*r*yh, 0, 0.34*r*xh, 0.34*r*yh, 0) ;
//          fTeXlabel3d(1.3*r*xh, 1.3*r*yh+1, 0, "cl", "$\\dee{\\theta}$") ; 


          canvas.append( new fPsWrite(Blue+"\n")) ;
          fEnv("lineWidth", 1.5) ;
          
     }

     public void fLine3d(double xl, double yl, double zl,
                         double xr, double yr, double zr) {
          double X1 = xl*proj[0][0]+yl*proj[0][1]+zl*proj[0][2] ;
          double Y1 = xl*proj[1][0]+yl*proj[1][1]+zl*proj[1][2] ;
          double X2 = xr*proj[0][0]+yr*proj[0][1]+zr*proj[0][2] ;
          double Y2 = xr*proj[1][0]+yr*proj[1][1]+zr*proj[1][2] ;
          fLine(X1,Y1,X2,Y2) ;
     }

     public void fDisk3d(double x, double y, double z,
                         double r1, double r2, long TYPE) {
          double X = x*proj[0][0]+y*proj[0][1]+z*proj[0][2] ;
          double Y = x*proj[1][0]+y*proj[1][1]+z*proj[1][2] ;
          fDisk(X,Y,r1,r2,TYPE) ;
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



