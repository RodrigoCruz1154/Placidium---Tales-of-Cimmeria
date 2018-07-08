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
    Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    Jugador jugador = new Jugador(20,130,mapa);
    
    @Override
    public void actualizar() {
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
    }
    
}
