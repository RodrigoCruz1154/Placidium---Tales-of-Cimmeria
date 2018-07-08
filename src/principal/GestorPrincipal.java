package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDeDibujo;
import principal.graficos.Ventana;
import principal.maquinadeestado.GestorDeEstados;

public class GestorPrincipal {

    private static volatile boolean enFuncionamiento = false; //Nos dice si se está iniciando (el tipo volatile sirve para que no se pueda utilizar el método de forma simultánea y evitar crasheos)
    //sobre ventana
    private String titulo;
    private int ancho;
    private int alto;

    private SuperficieDeDibujo sd;
    private Ventana ventana;
    private GestorDeEstados ge;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(String[] args) {
        GestorPrincipal juego = new GestorPrincipal("Placidium", 840, 560);
        
        Constantes.ANCHO_PANTALLA = 840;
        Constantes.ALTO_PANTALLA = 560;
        
        juego.iniciarJuego();
        juego.iniciarBuclePrincipal();
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }

    private void iniciarBuclePrincipal() {

        int aps = 0;
        int fps = 0;

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
                aps++;
                Constantes.APS = aps;
                delta--;
            }

            dibujar();
            fps++;
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                System.out.println("APS: " + aps +  " FPS: " + fps);
//                ventana.setTitle(NOMBRE + " || APS: " + aps +  " || FPS: " + fps);
                aps = 0; //se reinicializan para que vuelva a contar y no tienda al infinito, lo mismo con fps.
                Constantes.APS = aps;
                fps = 0;
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
        ge.actualizar();
    }

    private void dibujar() {
        sd.dibujar(ge);
    }
}
