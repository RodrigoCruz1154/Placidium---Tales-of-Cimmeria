package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {
//atributos

    private final BufferedImage imagen;
    private final int ancho;
    private final int alto;

//constructor    
    public Sprite(final BufferedImage imagen) {
        this.imagen = imagen;
        ancho = imagen.getWidth();
        alto = imagen.getHeight();
    }

//métodos    
//Getters
    public BufferedImage getImagen() {
        return imagen;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

}
