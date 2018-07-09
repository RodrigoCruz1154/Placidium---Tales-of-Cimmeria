package principal.maquinadeestado.estados.menuinicial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.maquinadeestado.EstadoJuego;
import principal.sonido.Sonido;

public class GestorTitulo implements EstadoJuego {

    private String titulo5 = "/imagenes/iconos/5.png";

    @Override
    public void actualizar() {
        if (GestorControles.teclado.esc.isPulsada()) {
            System.exit(0);
        }
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        BufferedImage imagen5 = CargadorRecursos.cargarImagenCompatibleOpaca(titulo5);
        g2.drawImage(imagen5, 0, 0, null);
        g2.dispose();
    }
}
