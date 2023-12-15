/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import ws.WSOperaciones;
import ws.WSOperaciones_Service;

/**
 *
 * @author manuell
 */
public class Interfaz extends JFrame implements ActionListener {

    WSOperaciones_Service servicio = new WSOperaciones_Service();
    WSOperaciones cliente = servicio.getWSOperacionesPort();
    ConexionJDBC con = new ConexionJDBC();
    Connection conn = con.getConexion();
    PreparedStatement ps = null;
    public JTextField txtUser, txtContra, txtPago, txtTotal;
    public JLabel lblUser, lblContra, lblPago, lblTotal;
    public Font tLetra;
    public JButton btnResultado, btnSalir;

    public Interfaz() {
        initComponents();
    }

    public static void main(String[] args) {
        Interfaz ventana = new Interfaz();
        ventana.setVisible(true);
    }

    private void initComponents() {
        this.setTitle("Ventana Clientes");
        this.setSize(360, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);

        Font tLetra = new Font("ARIAL", Font.BOLD, 35);

        lblUser = new JLabel("USUARIO");
        lblUser.setBounds(40, 40, 220, 30);
        lblUser.setFont(tLetra);
        lblUser.setForeground(Color.WHITE);

        txtUser = new JTextField();
        txtUser.setBounds(40, 80, 260, 30);
        txtUser.setFont(tLetra);

        lblContra = new JLabel("CONTRASEÑA");
        lblContra.setBounds(40, 130, 280, 30);
        lblContra.setFont(tLetra);
        lblContra.setForeground(Color.WHITE);

        txtContra = new JTextField();
        txtContra.setBounds(40, 170, 260, 30);
        txtContra.setFont(tLetra);

        lblPago = new JLabel("TOTAL");
        lblPago.setBounds(40, 210, 260, 30);
        lblPago.setFont(tLetra);
        lblPago.setForeground(Color.WHITE);

        txtPago = new JTextField();
        txtPago.setBounds(40, 250, 260, 30);
        txtPago.setFont(tLetra);

        lblTotal = new JLabel("PAGO");
        lblTotal.setBounds(40, 290, 260, 30);
        lblTotal.setFont(tLetra);
        lblTotal.setForeground(Color.WHITE);

        txtTotal = new JTextField();
        txtTotal.setBounds(40, 330, 260, 30);
        txtTotal.setFont(tLetra);

        btnResultado = new JButton("PROBAR");
        btnResultado.setBounds(200, 380, 100, 40);
        btnResultado.setBackground(Color.WHITE);
        btnResultado.addActionListener(this);

        btnSalir = new JButton("SALIR");
        btnSalir.setBounds(40, 380, 100, 40);
        btnSalir.setBackground(Color.WHITE);
        btnSalir.addActionListener(this);

        this.add(lblUser);
        this.add(txtUser);
        this.add(lblContra);
        this.add(txtContra);
        this.add(lblPago);
        this.add(txtPago);
        this.add(lblTotal);
        this.add(txtTotal);
        this.add(btnResultado);
        this.add(btnSalir);
    }

    @Override
    public void actionPerformed(ActionEvent clic) {
        if (clic.getSource() == btnResultado) {
            if (cajasVacias()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            } else {
                //Utilizar Metodos
                //1.- Login
                String status = "", resultado = "";
                if (cliente.login(txtUser.getText(), txtContra.getText())) {
                    JOptionPane.showMessageDialog(null, "Credenciales correctas");
                    status = "Credenciales correctas";
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                    status = "Credenciales incorrectas";
                }

                // 2.- procesar pago
                int cambio = 0;
                cambio = cliente.procesarPago(Integer.parseInt(txtPago.getText()),Integer.parseInt(txtTotal.getText()));
                if (cambio == -1) {
                    JOptionPane.showMessageDialog(null, "Dinero insuficiente");
                    resultado = "resultado Dinero insuficiente";
                } else {
                    System.out.println("Credenciales correctas");
                    JOptionPane.showMessageDialog(null, "CAMBIO " + cambio);
                    resultado = "resultado " + cambio;
                }
                AgregarDatos(status, resultado);
                vaciarCampos();
            }
        } else if (clic.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public void AgregarDatos(String validaruser, String resultadooper) {
        ps = null;
        try {
            ps = conn.prepareCall("insert into info(validaruser,resultadooper) values (?,?)");
            ps.setString(1, validaruser);//leemos la caja de texto
            ps.setString(2, resultadooper);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Base de datos actualizada");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private boolean cajasVacias() {
        if (txtUser.getText().equals("") || txtContra.getText().equals("")
                || txtPago.getText().equals("") || txtTotal.getText().equals("")) {
            return true;
        }
        return false;
    }
    
    public void vaciarCampos() {
        txtUser.setText("");
        txtContra.setText("");
        txtPago.setText("");
        txtTotal.setText("");
    }

}
