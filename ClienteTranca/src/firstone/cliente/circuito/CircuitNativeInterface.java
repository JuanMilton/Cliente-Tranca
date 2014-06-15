/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Milton
 */
public class CircuitNativeInterface {
    
    public static final int LEVANTAR_BRAZO = 1;
    public static final int BAJAR_BRAZO = 2;
    public static final int ENCENDER_ALARMA = 3;
    public static final int APAGAR_ALARMA = 4;
    public static final int OBTENER_CODIGO_VEHICULO = 5;
    public static final int OBTENER_DATOS_SENSOR = 6;
    
    public String callCircuit(int task) throws Exception
    {
        Socket socket = new Socket("localhost", 65454);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeBytes(""+task);
        
        DataInputStream inToServer = new DataInputStream(socket.getInputStream());
        String res = inToServer.readLine();
        socket.close();
        return res;
    }
}
