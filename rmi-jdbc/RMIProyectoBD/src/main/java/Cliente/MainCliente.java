/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

//<editor-fold defaultstate="collapsed" desc="Imports">
import RMI.InterfazRemoto;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.JOptionPane;
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="Clase De Cliente De Prueba">
public class MainCliente {
    
    public static void main(String[] args) {
        try {
            String valorA = JOptionPane.showInputDialog(null,
                    "Ingrese El numero 1: ", "Entrada De Datos...",
                    JOptionPane.QUESTION_MESSAGE);
            String valorB = JOptionPane.showInputDialog(null,
                    "Ingrese El numero 2: ", "Entrada De Datos...",
                    JOptionPane.QUESTION_MESSAGE);
            int a = Integer.parseInt(valorA);
            int b = Integer.parseInt(valorB);
            Registry miR = LocateRegistry.getRegistry("127.0.0.1", 1099);
            InterfazRemoto s = (InterfazRemoto) miR.lookup("COMUNICACION");
            JOptionPane.showMessageDialog(null, "Resultado Suma: " + s.suma(a, b),
                    "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Resultado Resta: " + s.resta(a, b),
                    "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Resultado Multiplicacion: " + s.multiplicacion(a, b),
                    "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Resultado Division: " + s.division(a, b),
                    "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            String info = "adios";
            String msj = "";
            Scanner sc = new Scanner(System.in);
            while (!msj.equalsIgnoreCase(info)){
                s.actualizar();
                System.out.print("Ingrese el mensaje a enviar: ");
                msj = sc.nextLine();
                System.out.println(s.conversacion("24/09/2023","Manuell",msj, "2000"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
//</editor-fold>
