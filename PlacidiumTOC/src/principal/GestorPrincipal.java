package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDeDibujo;
import principal.graficos.Ventana;
import principal.maquinadeestado.EstadoJuego;
import principal.maquinadeestado.GestorDeEstados;
import principal.sonido.Sonido;

public class GestorPrincipal {

    private static volatile boolean enFuncionamiento = false; //Nos dice si se está iniciando (el tipo volatile sirve para que no se pueda utilizar el método de forma simultánea y evitar crasheos)
    //sobre ventana
    private String titulo;
    private int ancho;
    private int alto;
    
    private SuperficieDeDibujo sd;
    private Ventana ventana;
    private GestorDeEstados ge;
    
    private static int fps = 0;
    private static int aps = 0;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(String[] args) {
        GestorPrincipal juego = new GestorPrincipal("Placidium", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
       
        juego.iniciarJuego();
        juego.iniciarBuclePrincipal();
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }

    private void iniciarBuclePrincipal() {

        int ActualizacionesAcumuladas = 0;
        int FramesAcumulados = 0;

        final int NS_POR_SEGUNDO = 1000000000; //La cantidad de nanosegundos en un segundo.
        final int APS_OBJETIVO = 60; //ENTRE MENOR EL NÚMERO DE ACTUALIZACIONES, ES MÁS EFICIENTE EL JUEGO, PERO TAMPOCO DEBE SER MUY BAJO. La cantidad de actualizaciones por segundo
        final double NS_POR_ACTUALIZACIÓN = NS_POR_SEGUNDO / APS_OBJETIVO; //CUANTOS NANO SEGUNDOS TIENEN QUE PASAR PARA ACTUALIZAR A 60 APS. La cantidad de nanosegundos que ocurren por atualización.

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0; //cantidad de tiempo recorrido hasta que se realiza una actualización

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACIÓN;

            while (delta >= 1) {
                actualizar();
                ActualizacionesAcumuladas++;
                delta--;
            }

            dibujar();
            FramesAcumulados++;
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                
                this.fps = FramesAcumulados;
                this.aps = ActualizacionesAcumuladas;
                
//                ventana.setTitle(NOMBRE + " || APS: " + ActualizacionesAcumuladas +  " || FPS: " + FramesAcumulados);
                ActualizacionesAcumuladas = 0; //se reinicializan para que vuelva a contar y no tienda al infinito, lo mismo con FramesAcumulados.
                FramesAcumulados = 0;
                referenciaContador = System.nanoTime();
            }
        }

    }

    /**
     * Inicia todos los componentes a usar en el juego
     */
    private void inicializar() {
        sd = new SuperficieDeDibujo(ancho,alto);
        ventana = new Ventana(titulo,sd);
        ge = new GestorDeEstados();
    }

    private void actualizar() {           
        if (GestorControles.teclado.espacio.isPulsada()) {            
            ge.cambiarEstadoActual(1);
            if(GestorControles.teclado.menu){
                ge.cambiarEstadoActual(2);
            } else{
                ge.cambiarEstadoActual(1);
            }
        }
        ge.actualizar();
        sd.actualizar();
    }

    private void dibujar() {
        sd.dibujar(ge);
    }
//getters
    public static int getFps() {
        return fps;
    }

    public static int getAps() {
        return aps;
    }
    
}
