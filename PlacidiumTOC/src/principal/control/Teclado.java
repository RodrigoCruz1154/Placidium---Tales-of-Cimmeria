package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    public Tecla arriba = new Tecla();
    public Tecla abajo = new Tecla();
    public Tecla izquierda = new Tecla();
    public Tecla derecha = new Tecla();
    public Tecla esc = new Tecla();
    public Tecla espacio = new Tecla();

    public boolean corriendo = false;
    public boolean debug = false;
    public boolean menu = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaPulsada();
                break;
            case KeyEvent.VK_ESCAPE:
                esc.teclaPulsada();
                break;
            case KeyEvent.VK_F1:
                debug = !debug;
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = true;
                break;
            case KeyEvent.VK_P:
                menu = !menu;
                break;
            case KeyEvent.VK_SPACE:
                espacio.teclaPulsada();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaLiberada();
                break;
            case KeyEvent.VK_ESCAPE:
                esc.teclaLiberada();
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = false;
                break;
            case KeyEvent.VK_SPACE:
                espacio.teclaLiberada();
        }
    }

}
