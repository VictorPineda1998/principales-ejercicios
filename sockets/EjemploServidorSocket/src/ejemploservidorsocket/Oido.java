
package ejemploservidorsocket;

// @author manuell dirzo

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class Oido extends Thread{
    
    DataInputStream canalEntrada;
    JTextArea txtHistorial;
    
    public Oido(DataInputStream canalEntrada, JTextArea txtHistorial){
        this.canalEntrada = canalEntrada;
        this.txtHistorial = txtHistorial;
    }
    
    @Override
    public void run() {
        String mensaje = "";
        while (!mensaje.equalsIgnoreCase("bye")) {
            try {
                mensaje = canalEntrada.readUTF();
                //System.out.println(mensaje);
                txtHistorial.append(mensaje + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Oido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.exit(0);
    }
    
}
