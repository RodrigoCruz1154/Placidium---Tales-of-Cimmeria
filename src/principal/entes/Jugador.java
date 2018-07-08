package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Jugador {

    private double posicionX;
    private double posicionY;

    private int direccion;

    private double velocidad = 1;

    private Sprite sprite;

    private HojaSprites hs;

    private BufferedImage imagenActual;

    private boolean enMovimiento;

    private int animacion;

    private int estado;
    
    private Mapa mapa;
    
    private int ANCHO_JUGADOR = 16;
    private int ALTO_JUGADOR = 16;
    
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X-8,Constantes.CENTRO_VENTANA_Y,16,1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X-8,Constantes.CENTRO_VENTANA_Y+16,16,1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X-8,Constantes.CENTRO_VENTANA_Y,1,16);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X+8,Constantes.CENTRO_VENTANA_Y,1,16);

    public Jugador(double posicionX, double posicionY, Mapa mapa) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        direccion = 0;
        hs = new HojaSprites(Constantes.RUTA_JUGADOR, Constantes.LADOSPRITE, false);
        imagenActual = hs.getSprite(0).getImagen();
        enMovimiento = false;
        animacion = 0;
        estado = 0;
        this.mapa = mapa;
    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();
        if(GestorControles.teclado.corriendo){
            velocidad = 1.8;
            cambiarAnimacionEstadoCorriendo();
            animar();
        } else{
            velocidad = 1;
        }
        if(GestorControles.teclado.esc.isPulsada()){
            System.exit(0);
        }
    }

    
    private void cambiarAnimacionEstado() {
        
        if(animacion<30){
            animacion++;
        } else{
            animacion = 0;
        }
        
        if(animacion < 15){
            estado = 1;
        } else {
            estado = 2;
        }
    }
    private void cambiarAnimacionEstadoCorriendo() {
        if(animacion<60){
            animacion++;
        } else{
            animacion = 0;
        }
        if(animacion < 15){
            estado = 3;
        } else if(animacion < 30){
            estado = 4;
        }
        else if(animacion < 45){
            estado = 5;
        }
    }
    
    private void determinarDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }
        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            mover(velocidadX, velocidadY);
        } else{
            //izquierda y arriba a la vez
            if(velocidadX == -1 && velocidadY == -1){
                if(GestorControles.teclado.izquierda.getUltimaPulsacion()>GestorControles.teclado.arriba.getUltimaPulsacion()){
                    mover(velocidadX,0);
                } else{
                    mover(0,velocidadY);    
                }
            }
            // izquierda y abajo a la vez
            if(velocidadX == -1 && velocidadY == 1){
                if(GestorControles.teclado.izquierda.getUltimaPulsacion()>GestorControles.teclado.abajo.getUltimaPulsacion()){
                    mover(velocidadX,0);
                } else{
                    mover(0,velocidadY);    
                }
            }
            // derecha y arriba a la vez
            if(velocidadX == 1 && velocidadY == -1){
                if(GestorControles.teclado.derecha.getUltimaPulsacion()>GestorControles.teclado.arriba.getUltimaPulsacion()){
                    mover(velocidadX,0);
                } else{
                    mover(0,velocidadY);    
                }
            }
            // derecha y abajo a la vez
            if(velocidadX == 1 && velocidadY == 1){
                if(GestorControles.teclado.derecha.getUltimaPulsacion()>GestorControles.teclado.abajo.getUltimaPulsacion()){
                    mover(velocidadX,0);
                } else{
                    mover(0,velocidadY);    
                }
            }
        }
    }

    private int evaluarVelocidadX() {
        int velocidadX = 0;

        if (GestorControles.teclado.izquierda.isPulsada() && !GestorControles.teclado.derecha.isPulsada()) {
            velocidadX = -1;
        } else if (GestorControles.teclado.derecha.isPulsada() && !GestorControles.teclado.izquierda.isPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    private int evaluarVelocidadY() {
        int velocidadY = 0;

        if (GestorControles.teclado.arriba.isPulsada() && !GestorControles.teclado.abajo.isPulsada()) {
            velocidadY = -1;
        } else if (GestorControles.teclado.abajo.isPulsada() && !GestorControles.teclado.arriba.isPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;
    }

    private void mover(int velocidadX, int velocidadY) {
        enMovimiento = true;
        cambiarDireccion(velocidadX, velocidadY);
        if(!fueraMapa(velocidadX,velocidadY)){
            if(velocidadX == -1&&!enColisionIzquierda(velocidadX)){
                posicionX += velocidadX * velocidad;
            }
            if(velocidadX == 1 && !enColisionDerecha(velocidadX)){
                posicionX += velocidadX * velocidad;
            }
            if(velocidadY == -1 && !enColisionArriba(velocidadY)){
                posicionY += velocidadY * velocidad;
            }
            if(velocidadY == 1 && !enColisionAbajo(velocidadY)){
                posicionY += velocidadY * velocidad;
            }
        }
    }
    
    private boolean enColisionArriba(int velocidadY){
        for(int r =0 ; r < mapa.AreasColision.size();r++){
            final Rectangle area = mapa.AreasColision.get(r);
            
            int origenX = area.x;
            int origenY = area.y + velocidadY * (int)velocidad + 3 * (int)velocidad;
            
            final Rectangle areaFutura = new Rectangle(origenX,origenY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
            
            if(LIMITE_ARRIBA.intersects(areaFutura)){
                return true;
            }
        }
        
        return false;
    }
    private boolean enColisionAbajo(int velocidadY){
        for(int r =0 ; r < mapa.AreasColision.size();r++){
            final Rectangle area = mapa.AreasColision.get(r);
            
            int origenX = area.x;
            int origenY = area.y + velocidadY * (int)velocidad - 3 * (int)velocidad;
            
            final Rectangle areaFutura = new Rectangle(origenX,origenY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
            
            if(LIMITE_ABAJO.intersects(areaFutura)){
                return true;
            }
        }
        
        return false;
    }
    private boolean enColisionIzquierda(int velocidadX){
        for(int r =0 ; r < mapa.AreasColision.size();r++){
            final Rectangle area = mapa.AreasColision.get(r);
            
            int origenX = area.x + velocidadX * (int)velocidad + 3 * (int)velocidad;
            int origenY = area.y;
            
            final Rectangle areaFutura = new Rectangle(origenX,origenY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
            
            if(LIMITE_IZQUIERDA.intersects(areaFutura)){
                return true;
            }
        }
        
        return false;
    }
    private boolean enColisionDerecha(int velocidadX){
        for(int r =0 ; r < mapa.AreasColision.size();r++){
            final Rectangle area = mapa.AreasColision.get(r);
            
            int origenX = area.x + velocidadX * (int)velocidad - 3 * (int)velocidad;
            int origenY = area.y;
            
            final Rectangle areaFutura = new Rectangle(origenX,origenY,Constantes.LADOSPRITE,Constantes.LADOSPRITE);
            
            if(LIMITE_DERECHA.intersects(areaFutura)){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Evita que el jugador se salga del mapa.
     * @param velocidadX
     * @param velocidadY
     * @return 
     */
    private boolean fueraMapa(final int velocidadX, final int velocidadY){
        
        int posicionFuturaX = (int) posicionX + velocidadX * (int)velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int)velocidad;
        
        final Rectangle bordesMapa = mapa.obtenerBordes(posicionFuturaX,posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);
        
        final boolean fuera;
        
        if(LIMITE_ARRIBA.intersects(bordesMapa)|| LIMITE_ABAJO.intersects(bordesMapa)||LIMITE_IZQUIERDA.intersects(bordesMapa)||LIMITE_DERECHA.intersects(bordesMapa)){
            fuera = false;
        } else{
            fuera = true;
        }
        return fuera;
    }

    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        
        if (velocidadX == -1) {
            direccion = 3;
        } else if (velocidadX == 1) {
            direccion = 2;
        }
        if (velocidadY == -1) {
            direccion = 1;
        } else if (velocidadY == 1) {
            direccion = 0;
        }
    }

    private void animar() {
        if(!enMovimiento){
            estado = 0;
            animacion = 0;
        }
        imagenActual = hs.getSprite(direccion, estado).getImagen();
    }

    public void dibujar(Graphics g) {
        final int centroX = Constantes.ANCHO_PANTALLA / 2 - Constantes.LADOSPRITE / 2;
        final int centroY = Constantes.ALTO_PANTALLA / 2 - Constantes.LADOSPRITE / 2;

        g.setColor(Color.WHITE);
        g.drawImage(imagenActual, centroX, centroY, null);
        /*
        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
        */
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

}
