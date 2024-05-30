import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Usuario extends JFrame {
    private JPanel Login;
    private JButton iniciarsesionButton;
    private JButton registrarseButton;
    private JTextField cedulaTextField;
    private JPasswordField contraseñaPasswordField;
    private JTextField nombreTextField1;
    Connection conexion;
    PreparedStatement ps;
    Statement st;
    ResultSet r;

    public Usuario() {
        iniciarsesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             validar();
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ingresar registro
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            mostrarVentanaRegistro();
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

    void validar() {
        conectar();
        int validacion = 0;
        String cedula = cedulaTextField.getText();
        String nombre = nombreTextField1.getText();
        String contraseña = String.valueOf(contraseñaPasswordField.getText());
        try {
            st = conexion.createStatement();
            r = st.executeQuery("select * from cliente where cedula = '" + cedula + "'and contraseña = '" + contraseña + "'");
            if (r.next()) {
                validacion = 1;
                if (validacion == 1) {
                JOptionPane.showMessageDialog(null, "Usuario exitoso");
                    Usuario enlazar = new Usuario();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no registrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }

    }
    public void mostrarVentanaRegistro(){
        Registro registro1 = new Registro();
        registro1.setContentPane(new Usuario().Login);
        registro1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registro1.setVisible(true);
        registro1.pack();
    }
    public void registrarse(){
        Usuario enlazar = new Usuario();
        enlazar.mostrarVentanaRegistro();
    }

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario();
        usuario1.setContentPane(new Usuario().Login);
        usuario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usuario1.setVisible(true);
    }
}
