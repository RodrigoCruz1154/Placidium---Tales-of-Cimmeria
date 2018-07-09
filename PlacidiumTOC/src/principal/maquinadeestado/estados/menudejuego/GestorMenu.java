package principal.maquinadeestado.estados.menudejuego;

import java.awt.Color;
import java.awt.Graphics;
import principal.herramientas.DibujoDebug;
import principal.maquinadeestado.EstadoJuego;

public class GestorMenu implements EstadoJuego{

    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.CYAN);
        DibujoDebug.dibujarRectanguloContorno(g, 100, 100,100,100);
    }
    
}
