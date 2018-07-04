package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener{
    
    private final static int NUMERO_TECLAS = 256;
    private final boolean[] teclas = new boolean[NUMERO_TECLAS];

    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean espacio;
    public boolean shift;
    public boolean salir;
    
    public void actualizar(){
        salir = teclas[KeyEvent.VK_ESCAPE];
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        espacio = teclas[KeyEvent.VK_SPACE];
        shift = teclas[KeyEvent.VK_SHIFT];
    }
    
    public void keyPressed(KeyEvent e) { //método para cuando se pulse una tecla
        teclas[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) { //método para cuando se suelte una tecla
        teclas[e.getKeyCode()] = false;
    }
    
        public void keyTyped(KeyEvent e) { // LA ACCIÓN COMPLETA DE SOLTAR Y PRESIONAR LA TECLA
        
    }

    
}