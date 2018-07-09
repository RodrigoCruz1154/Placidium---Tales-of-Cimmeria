package principal.control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import principal.Constantes;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.CargadorRecursos;

/**
 * Establece un puntero distinto.
 */
public class Puntero extends MouseAdapter{
    
    private final Cursor cursor;
    private Point posicion; //guarda un punto x y un punto y del espacio dimensional

    public Puntero(final SuperficieDeDibujo sd) {
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        
        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTraslucida(Constantes.RUTA_PUNTERO);
        
        Point punta = new Point(0,0);
        
        this.cursor = configuracion.createCustomCursor(icono, punta, "Default");
        
        posicion = new Point();
        actualizarPosicion(sd);
    }

    public Cursor getCursor() {
        return this.cursor;
    }
    
    public void actualizar(final SuperficieDeDibujo sd){
        actualizarPosicion(sd);
    }
    
    public void dibujar(Graphics g){
        g.setColor(Color.RED);
        //g.drawString("Posicion Puntero X: " + posicion.getX(),20, 60);
        //g.drawString("Posicion Puntero Y: " + posicion.getY(),20, 80);
    }
    
    private void actualizarPosicion(final SuperficieDeDibujo sd){
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        
        SwingUtilities.convertPointFromScreen(posicionInicial, sd);
        
        posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
    }
}
