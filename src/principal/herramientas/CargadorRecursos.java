package principal.herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * Obtiene imagenes, sonidos, fuentes y todo aquello que se vaya a utlizar en el
 * juego.
 */
public class CargadorRecursos {

    /**
     * Carga una imagen desde una ruta y la hace compatible con el monitor que
     * se esté usando para mejorar el rendimiento del juego. Además la coloca en
     * la <code>BufferedImage</code> para acelerar el proceso de lectura de
     * imagenes. Aquí se podrán colocar <code>imagenes</code> como el terreno,
     * edificaciones, etc.
     *
     * @param ruta
     * @return
     */
    public static BufferedImage cargarImagenCompatibleOpaca(final String ruta) {
        Image imagen = null;

        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(); // se obtiene la configuración gráfica del monitor que se está usando para poder crear una imagen compatible.
        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    /**
     * Carga una imagen desde una ruta y la hace compatible con el monitor que
     * se esté usando para mejorar el rendimiento del juego. Además la coloca en
     * la <code>BufferedImage</code> para acelerar el proceso de lectura de
     * imagenes. Aquí se podrán colocar <code>imagenes</code> como el jugadores,
     * npc, objetos, etc.
     *
     * @param ruta
     * @return
     */
    public static BufferedImage cargarImagenCompatibleTraslucida(final String ruta) {
        Image imagen = null;

        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(); // se obtiene la configuración gráfica del monitor que se está usando para poder crear una imagen compatible.
        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagenAcelerada;
    }

    /**
     * Lee un archivo de texto.
     *
     * @param ruta
     * @return
     */
    public static String leerArchivoTexto(final String ruta) {
        String contenido = "";

        InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));

        String linea;

        try {
            while ((linea = lector.readLine()) != null) {
                contenido += linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (entradaBytes != null) {
                    entradaBytes.close();
                }
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
        return contenido;
    }
}
