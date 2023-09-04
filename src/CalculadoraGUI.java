import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI {
    private JFrame frame; //ventana principal
    private JPanel panel; //panel de botones
    private JTextField display; //campo de entrada y salida
    private double num1 = 0;
    private double num2 = 0;
    private char operacion;


    public CalculadoraGUI() {
        //ventana pricipal
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        // panel para botones
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4)); // 5 filas 4 columnas GRID

        //campo de texto

        display = new JTextField();
        display.setEditable(false); //CAMPO NO EDITABLE
        panel.add(display);

        //Botones en el grid
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };
        //logica de los botones y los eventos
        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String buttonText = ((JButton) e.getSource()).getText();
                    if (Character.isDigit(buttonText.charAt(0))) {
                        //si el boton es un digito, lo agrego al campo de texto
                        display.setText(display.getText() + buttonText);
                    }
                    else if (buttonText.equals(".")) {
                        if (!display.getText().contains(".")) {
                            display.setText(display.getText() + buttonText);
                        }
                    } else if (buttonText.equals("=")) {
                        num2 = Double.parseDouble(display.getText());
                        double resultado = realizarOperacion();
                        display.setText(Double.toString(resultado));
                    } else if (buttonText.equals("C")) {
                        display.setText("");
                    } else {
                        num1 = Double.parseDouble(display.getText());
                        operacion = buttonText.charAt(0);
                        display.setText("");
                    }
                }
            });
            panel.add(btn);
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    //Operacion actual
    private double realizarOperacion() {
        double resultado = 0;
        switch (operacion) {
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case '*':
                resultado = num1 * num2;
                break;
            case '/':

                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(frame, "ERROR, Division por cero");
                }
                break;
        }
        return resultado;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}
