package principal.control;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;

/**
 * Establece un puntero distinto.
 */
public class Puntero {
    
    private final Cursor cursor;

    public Puntero() {
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        
        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTraslucida(Constantes.RUTA_PUNTERO);
        
        Point punta = new Point(0,0);
        
        this.cursor = configuracion.createCustomCursor(icono, punta, "Default");
    }

    public Cursor getCursor() {
        return this.cursor;
    }
    
}
