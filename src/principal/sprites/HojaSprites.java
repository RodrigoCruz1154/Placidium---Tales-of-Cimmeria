package principal.sprites;

import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;

public class HojaSprites {
//atributos    

    final private int anchoHojaenPixeles;
    final private int altoHojaenPixeles;

    final private int anchoHojaenSprites;
    final private int altoHojaenSprites;

    final private int anchoSprites;
    final private int altoSprites;

    final private Sprite[] sprites;

    public HojaSprites(final String ruta, final int tamañoSprites, final boolean hojaOpaca) {
        BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTraslucida(ruta);
        }
        anchoHojaenPixeles = imagen.getWidth();
        altoHojaenPixeles = imagen.getHeight();

        anchoHojaenSprites = anchoHojaenPixeles / tamañoSprites;
        altoHojaenSprites = altoHojaenPixeles / tamañoSprites;

        anchoSprites = tamañoSprites;
        altoSprites = tamañoSprites;

        sprites = new Sprite[anchoHojaenSprites * altoHojaenSprites];
        extraerSprites(imagen);
    }

    public HojaSprites(final String ruta, final int anchoSprites, final int altoSprites, final boolean hojaOpaca) {
        BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTraslucida(ruta);
        }
        anchoHojaenPixeles = imagen.getWidth();
        altoHojaenPixeles = imagen.getHeight();

        anchoHojaenSprites = anchoHojaenPixeles / anchoSprites;
        altoHojaenSprites = altoHojaenPixeles / altoSprites;

        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;

        sprites = new Sprite[anchoHojaenSprites * altoHojaenSprites];
        extraerSprites(imagen);
    }
    /**
     * Rellena los sprites desde una imagen
     * @param imagen 
     */
    private void extraerSprites(final BufferedImage imagen){
        for(int y = 0; y < altoHojaenSprites; y++){
            for(int x = 0; x < anchoHojaenSprites; x++){
                final int posisionX = x * anchoSprites;
                final int posicionY = y * altoSprites;
                
                sprites[x+y*anchoHojaenSprites] = new Sprite(imagen.getSubimage(posisionX, posicionY, anchoSprites, altoSprites));
            }
        }
    }
//getters para poder obtener ciertos sprites.    
    public Sprite getSprite(final int indice){
        return sprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return sprites[x + y * anchoHojaenSprites];
    }
}
