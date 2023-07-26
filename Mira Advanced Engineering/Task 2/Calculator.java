import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton decimalButton, equalsButton, clearButton, negateButton, percentButton;
    private JPanel panel;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean isDecimalPressed = false;
    private boolean isDarkTheme = false; // flag for theme

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Set icon for the frame
        ImageIcon icon = new ImageIcon("C:\\Users\\chetan bhandari\\OneDrive\\Desktop\\Mira Advanced Engineering\\Task 2\\calculator.jpeg");
        frame.setIconImage(icon.getImage());

        displayField = new JTextField();
        displayField.setBounds(30, 20, 240, 30);
        displayField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        functionButtons = new JButton[8];
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        negateButton = new JButton("+/-");
        percentButton = new JButton("%");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalsButton;
        functionButtons[6] = clearButton;
        functionButtons[7] = negateButton;

        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(new FunctionButtonListener());
        }

        panel = new JPanel();
        panel.setBounds(30, 60, 240, 240);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(clearButton);
        panel.add(negateButton);
        panel.add(percentButton);
        panel.add(divideButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(multiplyButton);

        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(equalsButton);

        // Set orange background color for the equals button
        equalsButton.setBackground(Color.ORANGE);

        JButton themeButton = new JButton("Toggle Theme");
        themeButton.setBounds(30, 310, 240, 30);
        themeButton.addActionListener(new ThemeButtonListener());

        frame.add(displayField);
        frame.add(panel);
        frame.add(themeButton);
        frame.setVisible(true);
    }

    class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    displayField.setText(displayField.getText().concat(String.valueOf(i)));
                }
            }
        }
    }

    class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton || e.getSource() == subtractButton ||
                    e.getSource() == multiplyButton || e.getSource() == divideButton) {
                num1 = Double.parseDouble(displayField.getText());
                operator = ((JButton) e.getSource()).getText().charAt(0);
                displayField.setText("");
                isDecimalPressed = false;
            } else if (e.getSource() == decimalButton) {
                if (!isDecimalPressed) {
                    displayField.setText(displayField.getText().concat("."));
                    isDecimalPressed = true;
                }
            } else if (e.getSource() == equalsButton) {
                if (displayField.getText().isEmpty()) {
                    displayField.setText("Error: No input");
                    return;
                }
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            displayField.setText("Error: Division by zero");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                displayField.setText(String.valueOf(result));
                num1 = result;
                isDecimalPressed = false;
            } else if (e.getSource() == clearButton) {
                displayField.setText("");
                isDecimalPressed = false;
            } else if (e.getSource() == negateButton) {
                if (displayField.getText().isEmpty()) {
                    displayField.setText("Error: No input");
                    return;
                }
                double currentValue = Double.parseDouble(displayField.getText());
                displayField.setText(String.valueOf(-currentValue));
            } else if (e.getSource() == percentButton) {
                if (displayField.getText().isEmpty()) {
                    displayField.setText("Error: No input");
                    return;
                }
                double currentValue = Double.parseDouble(displayField.getText());
                displayField.setText(String.valueOf(currentValue / 100));
            }
        }
    }

    class ThemeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isDarkTheme) {
                // Switch to light theme
                frame.getContentPane().setBackground(Color.WHITE);
                displayField.setBackground(Color.WHITE);
                displayField.setForeground(Color.BLACK);
                panel.setBackground(Color.WHITE);
                for (JButton button : numberButtons) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                }
                for (JButton button : functionButtons) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                }
                equalsButton.setBackground(Color.ORANGE);
                isDarkTheme = false;
            } else {
                // Switch to dark theme
                frame.getContentPane().setBackground(Color.DARK_GRAY);
                displayField.setBackground(Color.DARK_GRAY);
                displayField.setForeground(Color.WHITE);
                panel.setBackground(Color.DARK_GRAY);
                for (JButton button : numberButtons) {
                    button.setBackground(Color.DARK_GRAY);
                    button.setForeground(Color.WHITE);
                }
                for (JButton button : functionButtons) {
                    button.setBackground(Color.DARK_GRAY);
                    button.setForeground(Color.WHITE);
                }
                equalsButton.setBackground(Color.ORANGE);
                isDarkTheme = true;
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
