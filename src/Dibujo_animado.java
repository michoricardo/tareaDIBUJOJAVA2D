


import javax.swing.*;

import java.awt.*;

import java.awt.geom.*;

import java.awt.event.*;

public class Dibujo_animado extends JPanel 
{
 GeneralPath  montana = new GeneralPath();

 GeneralPath pala_molino = new GeneralPath();
 
 static int numero;
   
 float tamano;   
 
 double [] limites = new double[] {0,7,4,-1};

 Dibujo_animado() 
 {
   setPreferredSize(new Dimension(720,500));
   
   montana.moveTo(0,-2);
   
   montana.lineTo(0,0.9);
   
   montana.lineTo(1.5,1.65);
   
   montana.lineTo(1.8,1.3);

   montana.lineTo(3,2.1);
   
   montana.lineTo(4.7,0.7);
   
   montana.lineTo(7.4,1.2);
   
   montana.lineTo(9,0.8);
   
   montana.lineTo(8,-3);
   
   montana.closePath();
         
   pala_molino.moveTo(0,0);
   
   pala_molino.lineTo(0.5,0.1);
   
   pala_molino.lineTo(1.5,0);
   
   pala_molino.lineTo(0.5,-0.1);
   
   pala_molino.closePath();

   new Timer(30,new ActionListener() 
   {
     public void actionPerformed(ActionEvent evt) 
     {
       numero++;
       
      repaint();
      
     }
      }).start();
   }
   
  protected void paintComponent(Graphics g) 
   {
      Graphics2D g2D = (Graphics2D)g;

      g2D.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      controlar_limites(g2D, getWidth(), getHeight(), limites);
      
      g2D.setColor( new Color(154,243,252) );
      
      g2D.fillRect(0,0,7,4);

      //g2D.setColor( new Color(51,204,51) );
      g2D.setColor( new Color(102,61,61) );
      g2D.fill(montana);

      g2D.setColor(new Color(119,119,165));
      
      g2D.fill(new Rectangle2D.Double(0,-0.4,7,0.8));

      g2D.setStroke( new BasicStroke(0.1F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,new float[] { 0.2F, 0.2F }, 1) );
      
      g2D.setColor(Color.WHITE);
      
      g2D.drawLine(0,0,10,0);
      
      g2D.setStroke( new BasicStroke(tamano));
      
      AffineTransform transformacion = g2D.getTransform();
      
      g2D.translate(5,3.3);
      
      dibujar_sol(g2D);
      
     g2D.setTransform(transformacion);
      
      g2D.translate(0.85,1);
      
      g2D.scale(0.6,0.6);
      
      dibujar_molino(g2D);
      
      g2D.setTransform(transformacion);

      g2D.translate(2.3,1.5);
      
      g2D.scale(0.4,0.4);
      
      dibujar_molino(g2D);
      
      g2D.setTransform(transformacion);

      g2D.translate(4.0,0.8);
      
      g2D.scale(0.7,0.7);
      
      dibujar_molino(g2D);
      
      g2D.setTransform(transformacion);
      
      g2D.translate(-3 + 13*(numero % 300) / 300.0, 0);
      
      g2D.scale(0.3,0.3);
      
      dibujar_vehiculo(g2D);
   }
   
    void controlar_limites(Graphics2D g2D, int anchura, int altura, double [] limites) 
   {
      double ancho = Math.abs(( limites[1] - limites[0] ) / anchura);
      
      double alto = Math.abs(( limites[3] - limites[2] ) / altura);
      
      tamano = (float) Math.min(ancho,alto);

      g2D.scale( anchura / (limites[1]-limites[0]), altura / (limites[3]-limites[2]) );
      
      g2D.translate( -limites[0], -limites[2] );
   }

   void dibujar_sol(Graphics2D g2D) 
   {
      g2D.setColor(Color.red);

      for (int i = 0; i < 15; i++) 
      { 
         g2D.rotate( 2*Math.PI / 15 ); 
         
         g2D.draw( new Line2D.Double(0,0,0.9,0.9) ); 
      }

      g2D.fill( new Ellipse2D.Double(-0.5,-0.5,1,1) );
      
      }
   
   
  void dibujar_molino(Graphics2D g2D) 
  {
      g2D.setColor(Color.GRAY);
      
      //g2D.fill(new Rectangle2D.Double(-0.05,0,0.1,3));
      g2D.fill(new Rectangle2D.Double(-0.05,0,0.2,3));
      g2D.translate(0,3);

      g2D.rotate(numero/23.0);
      
      g2D.setColor(new Color(25, 79, 248 ));

      for (int i = 0; i < 6; i++) 
      {
         g2D.rotate(Math.PI/2);
         
         g2D.fill(pala_molino); 
      }
   }
   
  void dibujar_vehiculo(Graphics2D g2D) 
  {
      AffineTransform transformacion = g2D.getTransform();
      
      g2D.translate(-1.5,-0.1);
      
      g2D.scale(0.8,0.8);
      
      dibujar_rueda(g2D);

      g2D.setTransform(transformacion);

      g2D.translate(3.5,-0.1);

      
      //g2D.scale(0.8,0.8);
      g2D.scale(1, 1);
      dibujar_rueda(g2D);

      g2D.setTransform(transformacion);
      
      g2D.setColor(Color.GREEN);

      g2D.fill(new Rectangle2D.Double(-2.5,0,7,3) );
      
      g2D.fill(new Rectangle2D.Double(4,0,2,2) );
      
      g2D.setColor(Color.WHITE);
      
      g2D.fill(new Rectangle2D.Double(-2.0,1.5,2,1) );
      
      g2D.fill(new Rectangle2D.Double(1.5,1.5,2,1) );
      
      g2D.translate(-8.5,-0.1);
      
      g2D.scale(0.8,0.8);
      
      dibujar_rueda(g2D);

      g2D.setTransform(transformacion);

      g2D.translate(-11.5,-0.1);
      
      g2D.scale(0.8,0.8);
      
      dibujar_rueda(g2D);

      g2D.setTransform(transformacion);
      
      g2D.setColor(Color.PINK);

      //g2D.fill(new Rectangle2D.Double(-10.5,1,2,1.5) );
      g2D.fill(new Rectangle2D.Double(-11.5,1,4,4) );
      g2D.fill(new Rectangle2D.Double(-11.5,0,5,1.5) );
      
      g2D.setColor(Color.WHITE);
      
      g2D.fill(new Rectangle2D.Double(-9.7,1,0.8,1) );
   }
   
   void dibujar_rueda(Graphics2D g2D) 
   {
      g2D.setColor(Color.BLACK);
      
      g2D.fill( new Ellipse2D.Double(-1,-1,2,2) );
      
      g2D.setColor(Color.LIGHT_GRAY);
      
      g2D.fill( new Ellipse2D.Double(-0.8,-0.8,1.6,1.6) );
      
      g2D.setColor(Color.BLACK);
      
      g2D.fill( new Ellipse2D.Double(-0.2,-0.2,0.4,0.4) );
      
      g2D.rotate( -numero/10.0 );

       for (int i = 0; i < 15; i++) 
       {  
         g2D.rotate(2*Math.PI/15);
         
         g2D.draw( new Rectangle2D.Double(0,-0.1,1,0.2) );
      }
   }

   public static void main(String[] args) 
   {
      JFrame ventana = new JFrame();
      
      ventana.setContentPane(new Dibujo_animado());
      
      ventana.pack();
      
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      ventana.setLocationRelativeTo(null);
      
      ventana.setVisible(true);
   }
}