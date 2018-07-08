package principal.maquinadeestado;

import java.awt.Graphics;
import principal.maquinadeestado.estados.juego.Gestordejuego;

/**
 * Controla el estado en que se encuentra el juego (titulo, main, batalla, etc.) y se dibuje a si mismo.
 */
public class GestorDeEstados {
    
    private EstadoJuego[] estados; //guarda todos los estados que puede tener el juego
    private EstadoJuego estadoActual; //verifica cual es el estado en el que nos encontramos
    
    public GestorDeEstados(){
        iniciarEstados();
        inicarEstadoActual();
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[1];  
        estados[0] = new Gestordejuego();
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
}
