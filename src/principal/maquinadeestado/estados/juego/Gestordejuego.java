package principal.maquinadeestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;
import principal.mapas.Mapa;
import principal.maquinadeestado.EstadoJuego;
import principal.sprites.HojaSprites;
/**
 * Clase encargada de dibujar los graficos en pantalla.
 */
public class Gestordejuego implements EstadoJuego{    
    
    Mapa mapa = new Mapa("/texto/mapa.txt");
    
    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics g) {
        for(int i=0;i<mapa.getPaleta().length;i++){
            g.drawImage(mapa.getSprite(i).getImagen(), i*32, 0,null);
        }
    }
    
}
