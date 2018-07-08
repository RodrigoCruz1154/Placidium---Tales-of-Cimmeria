package principal.graficos;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import principal.herramientas.CargadorRecursos;

public class Ventana extends JFrame{
    private String titulo;
    
    private final ImageIcon icono;
    
    public Ventana(final String titulo,final SuperficieDeDibujo sd){
        this.titulo = titulo;
        BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTraslucida("/imagenes/iconos/icono.png");
        this.icono = new ImageIcon(imagen);
        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDeDibujo sd) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());
        add(sd,BorderLayout.CENTER);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
