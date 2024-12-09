import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Components
    private JTextField display;
    private JPanel buttonPanel;
    private String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
    };
    private String operand1 = "", operand2 = "", operator = "";

    public Calculator() {
        // Setting up the frame
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if ("0123456789.".contains(input)) {
            // Handle numbers and decimal
            if (operator.isEmpty()) {
                operand1 += input;
                display.setText(operand1);
            } else {
                operand2 += input;
                display.setText(operand2);
            }
        } else if ("/+-*".contains(input)) {
            // Handle operators
            if (!operand1.isEmpty()) {
                operator = input;
                display.setText("");
            }
        } else if ("=".equals(input)) {
            // Handle equals
            if (!operand1.isEmpty() && !operand2.isEmpty()) {
                double num1 = Double.parseDouble(operand1);
                double num2 = Double.parseDouble(operand2);
                double result = 0;

                switch (operator) {
                    case "/": result = num1 / num2; break;
                    case "*": result = num1 * num2; break;
                    case "-": result = num1 - num2; break;
                    case "+": result = num1 + num2; break;
                }

                display.setText(String.valueOf(result));
                operand1 = String.valueOf(result);
                operand2 = "";
                operator = "";
            }
        } else {
            // Handle clear
            operand1 = operand2 = operator = "";
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
