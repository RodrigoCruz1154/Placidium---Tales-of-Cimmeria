package principal.mapas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

    private final String[] partes;

    private final int ancho;
    private final int alto;

    private final Sprite[] paleta;
    
    private final Point posicionInicial;
    private final Point puntoSalida;
    
    private final String siguienteMapa;

    private final boolean[] colisiones;

    private Rectangle zonaSalida;
    
    public ArrayList<Rectangle> AreasColision = new ArrayList <Rectangle>();
    
    private final int[] sprites;

    private final int MARGEN_X = Constantes.ANCHO_JUEGO / 2 - Constantes.LADOSPRITE / 2;
    private final int MARGEN_Y = Constantes.ALTO_JUEGO / 2 - Constantes.LADOSPRITE / 2;

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

        paleta = asignarSprites(partesPaleta, HojasSeparadas);

        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);

        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprites = extraerSprites(cadenasSprites);
        
        String posicion = partes[6];
        String[] posiciones = posicion.split("-");
        
        posicionInicial = new Point();
        posicionInicial.x = Integer.parseInt(posiciones[0])*Constantes.LADOSPRITE;
        posicionInicial.y = Integer.parseInt(posiciones[1])*Constantes.LADOSPRITE;
        
        String transicion = partes[7];
        String[] datosTransicion = transicion.split("-");
        
        puntoSalida = new Point();
        puntoSalida.x = Integer.parseInt(datosTransicion[0]);
        puntoSalida.y = Integer.parseInt(datosTransicion[1]);        
        siguienteMapa = datosTransicion[2];
        
        zonaSalida = new Rectangle();
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

        HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/" + hojasSeparadas[0] + ".png", 32, false);
        for (int i = 0; i < partesPaleta.length; i++) {
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

    public void dibujar(Graphics g, int posicionX, int posicionY) {
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {

                int puntoX = x * Constantes.LADOSPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADOSPRITE - posicionY + MARGEN_Y;

                g.drawImage(paleta[sprites[x + y * this.ancho]].getImagen(), puntoX, puntoY, null);
            }
        }
    }
    
    public void actualizar(final int posicionX, final int posicionY){
        actualizarAreasColision(posicionX,posicionY);
        actualizarZonaTransicion(posicionX,posicionY);
    }
    
    private void actualizarAreasColision(final int posicionX, final int posicionY){
        if(!AreasColision.isEmpty()){
            AreasColision.clear();
        }
        
        for(int y = 0; y<this.alto;y++){
            for (int x = 0; x < this.ancho; x++) {
                int puntoX = x * Constantes.LADOSPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADOSPRITE - posicionY + MARGEN_Y;
                
                if(colisiones[x+y*this.ancho]){
                    final Rectangle r = new Rectangle(puntoX,puntoY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
                    AreasColision.add(r);
                }
            }
        }
    }

    private void actualizarZonaTransicion(final int posicionX, final int posicionY) {
        int puntoX = ((int)puntoSalida.getX())*Constantes.LADOSPRITE - posicionX + MARGEN_X;
        int puntoY = ((int)puntoSalida.getY())*Constantes.LADOSPRITE - posicionY + MARGEN_Y;
        
        zonaSalida = new Rectangle(puntoX,puntoY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
    }
    public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * Constantes.LADOSPRITE - anchoJugador * 2;
        int alto = this.alto * Constantes.LADOSPRITE - altoJugador * 2;

        return new Rectangle(x, y, ancho, alto);
    }
//getters

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public Sprite getSprite(final int indice) {
        return paleta[indice];
    }

    public Sprite getSpritePaleta(final int x, final int y) {
        return paleta[x + y * this.ancho];
    }

    public Sprite[] getPaleta() {
        return paleta;
    }

    public Point getPosicionInicial() {
        return posicionInicial;
    }

    public Point getPuntoSalida() {
        return puntoSalida;
    }

    public String getSiguienteMapa() {
        return siguienteMapa;
    }

    public Rectangle getZonaSalida() {
        return zonaSalida;
    }
    

}
