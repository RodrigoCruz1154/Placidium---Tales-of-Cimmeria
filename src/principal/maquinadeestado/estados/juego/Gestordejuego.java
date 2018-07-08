package principal.maquinadeestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.mapas.Mapa;
import principal.maquinadeestado.EstadoJuego;
import principal.sprites.HojaSprites;
/**
 * Clase encargada de dibujar los graficos en pantalla.
 */
public class Gestordejuego implements EstadoJuego{    
    Mapa mapa;
    Jugador jugador;
    
    public Gestordejuego() {
        iniciarMapa(Constantes.RUTA_MAPA);
        iniciarJugador();
    }
    
    private void recargarJuego(){
        final String ruta = "/mapas/" + mapa.getSiguienteMapa();
        iniciarMapa(ruta);
        iniciarJugador();
    }
    
    private void iniciarMapa(final String ruta){
        mapa = new Mapa(ruta);
    }
    private void iniciarJugador(){
        jugador = new Jugador(mapa);
    }
    
    @Override
    public void actualizar() {
        if(jugador.getLIMITE_ARRIBA().intersects(mapa.getZonaSalida())){
            recargarJuego();
        }
        jugador.actualizar();
        mapa.actualizar((int)jugador.getPosicionX(),(int)jugador.getPosicionY());
    }

    @Override
    public void dibujar(Graphics g) {
        mapa.dibujar(g,(int)jugador.getPosicionX(),(int)jugador.getPosicionY());
        jugador.dibujar(g);
        
        g.setColor(Color.YELLOW);
        g.drawString("x: " + jugador.getPosicionX(), 20, 20);
        g.drawString("y: " + jugador.getPosicionY(), 20, 30);
        //g.fillRect(((int)mapa.getZonaSalida().getX()), ((int)mapa.getZonaSalida().getY()), ((int)mapa.getZonaSalida().getWidth()), ((int)mapa.getZonaSalida().getHeight()));
    }
    
}
