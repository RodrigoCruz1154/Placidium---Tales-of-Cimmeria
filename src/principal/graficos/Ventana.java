package principal.graficos;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame{
    private String titulo;
    
    public Ventana(final String titulo,final SuperficieDeDibujo sd){
        this.titulo = titulo;
        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDeDibujo sd) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setIconImage();
        setLayout(new BorderLayout());
        add(sd,BorderLayout.CENTER);
        //setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
