package principal.maquinadeestado;

import java.awt.Graphics;
import principal.Constantes;
import principal.maquinadeestado.estados.juego.Gestordejuego;
import principal.maquinadeestado.estados.menudejuego.GestorMenu;
import principal.maquinadeestado.estados.menuinicial.GestorTitulo;
import principal.sonido.Sonido;

/**
 * Controla el estado en que se encuentra el juego (titulo, main, batalla, etc.) y se dibuje a si mismo.
 */
public class GestorDeEstados {
    
    private EstadoJuego[] estados; //guarda todos los estados que puede tener el juego
    private EstadoJuego estadoActual; //verifica cual es el estado en el que nos encontramos
    private Sonido musica = new Sonido(Constantes.TITULO);
    private Sonido ciudad = new Sonido(Constantes.CIUDAD);
    
    public GestorDeEstados(){
        iniciarEstados();
        inicarEstadoActual();
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[3];
        estados[0] = new GestorTitulo();
        estados[1] = new Gestordejuego();
        estados[2] = new GestorMenu();
        //añadir demás estados a medida que los creemos.
    }

    private void inicarEstadoActual() {
        estadoActual = estados[0];
    }
    
    public void actualizar(){
        estadoActual.actualizar();
    }
    
    public void dibujar(final Graphics g){
        estadoActual.dibujar(g);
    }
    
    public void cambiarEstadoActual(final int nuevoEstado){
        estadoActual = estados[nuevoEstado];
    }
    
    public EstadoJuego obtenerEstadoActual(){
        return estadoActual;
    }

    public EstadoJuego[] getEstados() {
        return estados;
    }
}
