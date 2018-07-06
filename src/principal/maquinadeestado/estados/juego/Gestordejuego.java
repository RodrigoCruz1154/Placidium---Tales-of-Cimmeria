package principal.maquinadeestado.estados.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.maquinadeestado.EstadoJuego;
import principal.sprites.HojaSprites;
/**
 * Clase encargada de dibujar los graficos en pantalla.
 */
public class Gestordejuego implements EstadoJuego{
    
    private GestorMapa gestorMapa;
    HojaSprites hs = new HojaSprites("/imagenes/hojasTexturas/texturas.png",32,true);
    
    
    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics g) {
        BufferedImage imagen = hs.getSprite(0,0).getImagen();
        g.drawImage(imagen, 150, 100,null);
    }
    
}
