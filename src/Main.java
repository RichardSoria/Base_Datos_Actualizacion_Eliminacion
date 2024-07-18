import javax.swing.*;
import clases_base_datos.pantalla_inicio_form;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de gestión de estudiantes");
        frame.setContentPane(new pantalla_inicio_form().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}