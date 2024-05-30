import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registro extends JFrame {
    private JPanel RegistroPanel;
    private JTextField CedulaUsertextField;
    private JTextField NombreUsertextField;
    private JPasswordField ContrasenaUserField;
    private JButton REGISTRARSOATButton;
    private JTextField Fecha_expSoatnotextField;
    private JTextField fecha_vigSoattextField;
    private JTextField Fecha_expTecnotextField;
    private JTextField Fecha_vigTecnotextField;
    private JButton REGISTRARTECNOMECANICAButton;
    private JButton REGISTRARUSUARIOButton1;
    private JButton VOLVERButton;
    Connection conexion;
    DefaultListModel mod = new DefaultListModel<>();
    PreparedStatement ps;
    String[] campos = {"id", "nombre", "rol"};
    String[] registros = new String[10];
    Statement st2;
    ResultSet r;

    public Registro() {
        REGISTRARUSUARIOButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registarDatosCliente();
            }
        });
        REGISTRARSOATButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registarDatosSoat();
            }
        });
        REGISTRARTECNOMECANICAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registarDatosTecno();
            }
        });
        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaUsuario();
            }
        });
    }

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proyectoi", "root", "perseo2024");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void registarDatosCliente() {
        conectar();
        String cedula = CedulaUsertextField.getText();
        String nombre = NombreUsertextField.getText();
        String contraseña = String.valueOf(ContrasenaUserField.getText());
        try {
            st2 = conexion.createStatement();
            r = st2.executeQuery("insert into cliente ('" + cedula + "','" + nombre + "','" + contraseña + "');");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    void registarDatosSoat() {
        conectar();
        String fecha_expSoat = Fecha_expSoatnotextField.getText();
        String fecha_vigSoat = fecha_vigSoattextField.getText();
        try {
            st2 = conexion.createStatement();
            r = st2.executeQuery("insert into soat ('" + fecha_expSoat + "','" + fecha_vigSoat + "');");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    void registarDatosTecno() {
        conectar();
        String fecha_expTecno = Fecha_expTecnotextField.getText();
        String fecha_vigTecno = Fecha_vigTecnotextField.getText();
        try {
            st2 = conexion.createStatement();
            r = st2.executeQuery("insert into tecnomecanica ('" + fecha_expTecno + "','" + fecha_vigTecno+ "');");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    public void mostrarVentanaUsuario(){
        Registro registro1 = new Registro();
        registro1.setContentPane(new Registro().RegistroPanel);
        registro1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registro1.setVisible(true);
        registro1.pack();
    }
    public void volver(){
        Registro enlazar = new Registro();
        enlazar.mostrarVentanaUsuario();
    }
}