package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
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

    public Jugador(double posicionX, double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        direccion = 0;
        hs = new HojaSprites("/imagenes/hojasPersonajes/Player.png", Constantes.LADOSPRITE, false);
        imagenActual = hs.getSprite(0).getImagen();
        enMovimiento = false;
        animacion = 0;
        estado = 0;
    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();
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
        posicionX += velocidadX * velocidad;
        posicionY += velocidadY * velocidad;
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
