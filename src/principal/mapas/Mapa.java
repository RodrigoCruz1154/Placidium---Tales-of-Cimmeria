package principal.mapas;

import java.io.IOException;
import java.util.ArrayList;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

    private final String[] partes;

    private final int ancho;
    private final int alto;

    private final Sprite[] paleta;

    private final boolean[] colisiones;

    private final int[] sprites;

//constructores    
    public Mapa(final String ruta) {
        
        String contenido = CargadorRecursos.leerArchivoTexto(ruta);
        partes = contenido.split("\\*"); //romper la cadena hasta donde se le indique en el parámetro. Si es un patrón de expresión regular, entonces se colocan las barras invertidas para expresar que será propiamente el contenido
        
        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[0]);

        String NombresHojasSpritesUtilizadas = partes[2];
        String[] HojasSeparadas = NombresHojasSpritesUtilizadas.split(",");

        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");
        
        paleta = asignarSprites(partesPaleta,HojasSeparadas);

        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);

        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprites = extraerSprites(cadenasSprites);
    }

//métodos
    /**
     * Traduce la Paleta de Sprites en información que proporciona
     * <p>
     * El formato del texto es el siguiente:
     * <p>
     * Orden normal - Hoja utilizada - Posición en la hoja de Sprites
     * <p>
     * <code>EXAMPLE:</code> 69-4-20
     *
     * @param partesPaleta
     * @return
     */
    private Sprite[] asignarSprites(final String[] partesPaleta, final String[] hojasSeparadas) {
        
        Sprite[] paleta = new Sprite[partesPaleta.length];
        
        HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/"+ hojasSeparadas[0]+ ".png",32,false);
        for(int i=0;i<partesPaleta.length;i++){
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");
            
            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);
            
            paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);            
        }
        return paleta;
    }

    /**
     * Convierte el <code>Texto Binario</code> de un archivo de texto a
     * <code>TRUE</code> ó <code>FALSE</code> según convenga.
     *
     * @param cadenaColisiones
     * @return
     */
    private boolean[] extraerColisiones(final String cadenaColisiones) {
        boolean[] colisiones = new boolean[cadenaColisiones.length()];

        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = false;
            } else {
                colisiones[i] = true;
            }
        }
        return colisiones;
    }

    /**
     * Evita que la plantilla del mapa de un archivo de texto
     * <code>concatene</code> más de dos caracteres.
     *
     * @param cadenaSprites
     * @return
     */
    private int[] extraerSprites(final String[] cadenaSprites) {
        ArrayList<Integer> sprites = new ArrayList<Integer>();

        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2) {
                sprites.add(Integer.parseInt(cadenaSprites[i]));
            } else {
                String uno = "", dos = "", error = cadenaSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);

                dos += error.charAt(2);
                dos += error.charAt(3);

                sprites.add(Integer.parseInt(uno));
                sprites.add(Integer.parseInt(dos));
            }
        }

        int[] VectorDeSprites = new int[sprites.size()];

        for (int i = 0; i < sprites.size(); i++) {
            VectorDeSprites[i] = sprites.get(i);
        }

        return VectorDeSprites;
    }
//getters

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }
    
    public Sprite getSprite(final int indice){
        return paleta[indice];
    }
    
    public Sprite getSpritePaleta(final int x, final int y){
        return paleta[x+y*this.ancho];
    }
    
    public Sprite[] getPaleta(){
        return paleta;
    }
    
}
