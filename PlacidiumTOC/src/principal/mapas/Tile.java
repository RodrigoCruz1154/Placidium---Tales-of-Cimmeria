package principal.mapas;

import java.awt.Rectangle;
import principal.sprites.Sprite;

public class Tile {
    //atributos

    private final Sprite sprite;
    private final int id;
    private boolean solido;

    //constructores
    /**
     * Permite crear un <code>tile</code> que se utlizará para aquellos por los
     * cuales se puede andar libremente.
     *
     * @param sprite
     * @param id
     */
    public Tile(Sprite sprite, int id) {
        this.sprite = sprite;
        this.id = id;
        solido = false;
    }

    /**
     * Permite crear un <code>tile</code> y deja que se pueda modificar el
     * sólido.
     *
     * @param sprite
     * @param id
     * @param solido
     */
    public Tile(Sprite sprite, int id, boolean solido) {
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }

    //getters
    public Sprite getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public void setSolido(boolean solido) {
        this.solido = solido;
    }
        
//Servirá para las colisiones    
    public Rectangle getLimites(final int x, final int y){
        return new Rectangle(x,y,sprite.getAncho(),sprite.getAlto());
    }
}
