package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Puntero;
import principal.control.Teclado;
import principal.maquinadeestado.GestorDeEstados;

public class SuperficieDeDibujo extends Canvas {

    private int ancho;
    private int alto;
    private Puntero raton;

    public SuperficieDeDibujo(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        this.raton = new Puntero(this);
        
        setCursor(raton.getCursor());
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus(); //método por defecto de java que al añadirlo, hace que automáticamente la ventana que se cree sea el foco de uso y se pueda usar inmediatamente.
    }

    public void dibujar(final GestorDeEstados ge) {
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        ge.dibujar(g);
        
        if(Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0){
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y); //escalado a pantalla completa
        }        
        
        g.drawString("FPS: " + GestorPrincipal.getFps(), 20, 40);
        g.drawString("APS: " + GestorPrincipal.getAps(), 75, 40);
        raton.dibujar(g);        
        Toolkit.getDefaultToolkit().sync(); //intenta pintar solo entre actualizaciones de la pantalla
        g.dispose();
        buffer.show();
    }
    
    public void actualizar(){
        raton.actualizar(this);
    }

//Getters    
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    
}
