package principal;

import principal.sonido.Sonido;

public class Constantes {
    
    public static final int LADOSPRITE = 32;

    public static final int LADOTILE = 32;
    
    public static int ANCHO_JUEGO = 840;
    public static int ALTO_JUEGO = 560;
    
    public static int ANCHO_PANTALLA_COMPLETA =  1366;
    public static int ALTO_PANTALLA_COMPLETA =  768;
    
    public static double FACTOR_ESCALADO_X = ANCHO_PANTALLA_COMPLETA / ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = ALTO_PANTALLA_COMPLETA / ALTO_JUEGO;
    
    public static int CENTRO_VENTANA_X = ANCHO_JUEGO /2;
    public static int CENTRO_VENTANA_Y = ALTO_JUEGO /2;
    
    public static String RUTA_MAPA = "/mapas/01.txt";
    public static String RUTA_PUNTERO = "/imagenes/iconos/iconoCursor.png";
    public static String RUTA_JUGADOR = "/imagenes/hojasPersonajes/Player.png";
    public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/icono.png";
    
    public static String TITULO = "/sonidos/musica.wav";
    public static String CIUDAD = "/sonidos/ciudad.wav";
    
    
}
