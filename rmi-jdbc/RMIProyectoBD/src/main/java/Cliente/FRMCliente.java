/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

//<editor-fold defaultstate="collapsed" desc="Imports">
import RMI.InterfazRemoto;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Autor">


//<editor-fold defaultstate="collapsed" desc="Programa Cliente">
public class FRMCliente extends JFrame implements ActionListener {

//<editor-fold defaultstate="collapsed" desc="Variables">
    private JLabel lblTitulo, lblMensaje;
    private Font tLetra;
    private JButton btnEnviar, btnSalir;
    private JTextArea jTxtHistorial;
    private JTextField txtMensaje;
    private String msj = "";
    private Registry miR;
    private InterfazRemoto s;
    private String clave = "1234";
    private String user = "Alfonso"; 
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public FRMCliente() {
        try {
            miR = LocateRegistry.getRegistry("127.0.0.1", 1099);
            s = (InterfazRemoto) miR.lookup("COMUNICACION");
            System.out.println("esperando la aceptacion del servidor...");
            if (s.peticion(user, clave)) {
                System.out.println("Usuario en linea...");
                iniciarComponentes();
                txtMensaje.requestFocus();
                s.mostrarMensajes();
            } else {
                System.out.println("El servidor rechazo la conexion...");
                System.exit(0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
//</editor-fold>

        
//<editor-fold defaultstate="collapsed" desc="Metodo Principal">
    public static void main(String[] args) throws Exception {
    // Configura el SecurityManager

    FRMCliente frm = new FRMCliente();
    frm.setVisible(true);
    frm.startOido();
}

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Propiedades del frame">
    private void iniciarComponentes() {
        this.setTitle("Inicio De Sesion...");
        this.setSize(500, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);

        tLetra = new Font("Century", Font.BOLD, 20);

        lblTitulo = new JLabel("COMUNICACION...");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(150, 0, 200, 40);
        lblTitulo.setFont(tLetra);

        jTxtHistorial = new JTextArea();
        jTxtHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        jTxtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(jTxtHistorial);
        scroll.setBounds(10, 60, 460, 300);
        jTxtHistorial.setBorder(new LineBorder(Color.BLACK, 3, rootPaneCheckingEnabled));

        lblMensaje = new JLabel("Ingrese Su Mensaje:");
        lblMensaje.setForeground(Color.WHITE);
        lblMensaje.setBounds(10, 370, 350, 40);
        lblMensaje.setFont(tLetra);

        txtMensaje = new JTextField();
        txtMensaje.setBounds(10, 430, 330, 40);
        txtMensaje.setBorder(new LineBorder(Color.BLACK, 1, rootPaneCheckingEnabled));
        txtMensaje.setFont(tLetra);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(350, 430, 120, 40);
        btnEnviar.setFont(tLetra);
        btnEnviar.addActionListener(this);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 500, 120, 40);
        btnSalir.setFont(tLetra);
        btnSalir.addActionListener(this);

        this.add(lblTitulo);
        this.add(lblMensaje);
        this.add(txtMensaje);
        this.add(btnEnviar);
        this.add(btnSalir);
        this.add(scroll);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Logica de los botones">
    @Override
    public void actionPerformed(ActionEvent clic) {
        if (clic.getSource() == btnEnviar) {
            try {
                jTxtHistorial.setText("");
                jTxtHistorial.setText(s.actualizar());
                msj = txtMensaje.getText();
                // Obtener la fecha y hora actual
                Date fechaHoraActual = new Date();

                // Crear un formato para la fecha y hora
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                // Convertir la fecha y hora actual a una cadena formateada
                String fechaHoraFormateada = formato.format(fechaHoraActual);
                jTxtHistorial.setText(s.conversacion(fechaHoraFormateada, user, msj, clave));
                txtMensaje.setText("");
            } catch (Exception ex) {
                Logger.getLogger(FRMCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (clic.getSource() == btnSalir) {
            System.exit(0);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Hilo">
    public void startOido() throws Exception {
        while (true) {
            update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Metodos">
    public void update() throws Exception {
        jTxtHistorial.setText(s.actualizar());
    }

    public void bloquear() {
        txtMensaje.setEditable(false);
        btnEnviar.setEnabled(false);
    }

    public void desBloquear() {
        txtMensaje.setEditable(true);
        btnEnviar.setEnabled(true);
    }
//</editor-fold>

}
//</editor-fold>
