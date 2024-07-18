package clases_base_datos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class eliminar_registro_form {
    public JPanel mainPanel;
    private JButton eliminar_registro;
    private JTextField campo_cedula_buscar;
    private JButton regresar_boton;

    String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
    String user = "root";
    String password = "123456";
    String sql = "Delete from estudiante where cedula = ?";


    public eliminar_registro_form() {
        eliminar_registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if(campo_cedula_buscar.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                        return;
                    } else if (campo_cedula_buscar.getText().length() != 10)
                    {
                        JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                        return;
                    }

                    try
                    {
                        Connection conexion = DriverManager.getConnection(url, user, password);
                        PreparedStatement sentencia = conexion.prepareStatement(sql);
                        sentencia.setString(1, campo_cedula_buscar.getText());
                        int resultado = sentencia.executeUpdate();
                        campo_cedula_buscar.setText("");

                        if (resultado > 0)
                        {
                            JOptionPane.showMessageDialog(null, "Registro eliminado");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se encontraron registros.");
                        }
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally {
                        try {
                            DriverManager.getConnection(url, user, password).close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
            }
        });


        regresar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de gestión de estudiantes");
                frame.setContentPane(new pantalla_inicio_form().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                login_frame.dispose();
            }
        });
    }
}
