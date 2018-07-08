package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
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

        this.raton = new Puntero();
        
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
        Graphics g = buffer.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);
        ge.dibuajar(g);
        Toolkit.getDefaultToolkit().sync(); //intenta pintar solo entre actualizaciones de la pantalla
        g.dispose();
        buffer.show();
    }

//Getters    
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    
}
