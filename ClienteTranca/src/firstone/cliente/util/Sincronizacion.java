package firstone.cliente.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.log4j.Logger;

public class Sincronizacion {

    public static Logger log = Logger.getLogger(Sincronizacion.class);

    // Lee los datos del Archivo de configuración
    protected final static String configFile = "etc" + File.separator + "sincronizacion.properties";
    public static Properties prop = getProps(configFile);

    public static Long ULTIMO_ID_SINCRONIZADO = Long.parseLong(prop.getProperty("ULTIMO_ID_SINCRONIZADO"));

    private static InputStream configFileStream;
    private static OutputStream configFileOut = null;
    
    public static void iniciar()
    {
        ULTIMO_ID_SINCRONIZADO = Long.parseLong(prop.getProperty("ULTIMO_ID_SINCRONIZADO"));
    }
    
    public static void save() {
        try {
            configFileOut = new FileOutputStream(configFile);
            prop.store(configFileOut, "");
            Sincronizacion.prop = getProps(configFile);
            iniciar();
        } catch (FileNotFoundException ex) {
            log.error("Archivo no encontrado",ex);
        } catch (IOException ex) {
            log.error("Error al acceder al archivo",ex);
        }
    }

    public static Properties getProps(String configFile) {
        Properties props = new Properties();
        try {
            // configFileStream = new FileInputStream("etc/" + configFile);
            configFileStream = new FileInputStream(configFile);
            props.load(configFileStream);
        } catch (IOException e) {
            log.error("[getProps]: Error al cargar archivo" + e.getMessage());
        }
        return props;
    }
    
    public static void main(String[] args) {
        System.out.println(""+Sincronizacion.ULTIMO_ID_SINCRONIZADO);
        Sincronizacion.prop.setProperty("ULTIMO_ID_SINCRONIZADO", "0");
        Sincronizacion.save();
        System.out.println(""+Sincronizacion.ULTIMO_ID_SINCRONIZADO);
    }

}
