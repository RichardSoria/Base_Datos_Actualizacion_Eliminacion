package clases_base_datos;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actualizar_registro_form extends actualizar_registro{
    public JPanel mainPanel;
    private JTextField nota_1_campo;
    private JTextField nota_2_campo;
    private JButton actualizar_boton;
    private JButton regresar_boton;
    private JTextField cedula_estudainte;

    String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
    String user = "root";
    String password = "123456";
    String sql = "UPDATE estudiante SET b1 = ?, b2 = ? WHERE cedula = ?";

    public actualizar_registro_form() {
        actualizar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar_registro actualizar_registro = new actualizar_registro();

                if (cedula_estudainte.getText().isEmpty() || nota_1_campo.getText().isEmpty() || nota_2_campo.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                }

                if (cedula_estudainte.getText().length() != 10)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                    return;
                }

                if (Double.parseDouble(nota_1_campo.getText()) < 0 || Double.parseDouble(nota_1_campo.getText()) > 20)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una nota válida.");
                    return;
                }

                if (Double.parseDouble(nota_2_campo.getText()) < 0 || Double.parseDouble(nota_2_campo.getText()) > 20)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una nota válida.");
                    return;
                }

                actualizar_registro.setNota_1(Double.parseDouble(nota_1_campo.getText()));
                actualizar_registro.setNota_2(Double.parseDouble(nota_2_campo.getText()));

                try
                {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setDouble(1, actualizar_registro.getNota_1());
                    sentencia.setDouble(2, actualizar_registro.getNota_2());
                    sentencia.setString(3, cedula_estudainte.getText());
                    int resultado = sentencia.executeUpdate();
                    cedula_estudainte.setText("");
                    nota_1_campo.setText("");
                    nota_2_campo.setText("");

                    if (resultado > 0)
                    {
                        JOptionPane.showMessageDialog(null, "Registro actualizado");
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
