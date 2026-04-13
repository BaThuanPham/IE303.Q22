import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator_GUI extends JFrame implements ActionListener {
    private final JTextField display = new JTextField("0");
    private String firstOperand = "";
    private String operator = "";
    private boolean startNewInput = true;

    public Calculator_GUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.weightx = 1;
        gbc.weighty = 1;

        addButton(panel, gbc, "AC", 0, 0, 1, 1);
        addButton(panel, gbc, "/", 1, 0, 1, 1);
        addButton(panel, gbc, "*", 2, 0, 1, 1);
        addButton(panel, gbc, "-", 3, 0, 1, 1);

        addButton(panel, gbc, "7", 0, 1, 1, 1);
        addButton(panel, gbc, "8", 1, 1, 1, 1);
        addButton(panel, gbc, "9", 2, 1, 1, 1);
        addButton(panel, gbc, "+", 3, 1, 1, 2);

        addButton(panel, gbc, "4", 0, 2, 1, 1);
        addButton(panel, gbc, "5", 1, 2, 1, 1);
        addButton(panel, gbc, "6", 2, 2, 1, 1);

        addButton(panel, gbc, "1", 0, 3, 1, 1);
        addButton(panel, gbc, "2", 1, 3, 1, 1);
        addButton(panel, gbc, "3", 2, 3, 1, 1);
        addButton(panel, gbc, "=", 3, 3, 1, 2);

        addButton(panel, gbc, "Del", 0, 4, 1, 1);
        addButton(panel, gbc, "0", 1, 4, 1, 1);
        addButton(panel, gbc, ".", 2, 4, 1, 1);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(JPanel panel, GridBagConstraints gbc, String text,
                           int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;

        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(56, 48));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.addActionListener(this);
        panel.add(button, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();

        if (command.matches("[0-9]")) {
            if (startNewInput) {
                display.setText(command);
                startNewInput = false;
            } else {
                display.setText(display.getText() + command);
            }
            return;
        }

        if (".".equals(command)) {
            if (startNewInput) {
                display.setText("0.");
                startNewInput = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        if ("AC".equals(command)) {
            display.setText("0");
            firstOperand = "";
            operator = "";
            startNewInput = true;
            return;
        }

        if ("Del".equals(command)) {
            if (!startNewInput) {
                String text = display.getText();
                if (text.length() > 1) {
                    display.setText(text.substring(0, text.length() - 1));
                } else {
                    display.setText("0");
                    startNewInput = true;
                }
            }
            return;
        }

        if (command.matches("[+\\-*/]")) {
            firstOperand = display.getText();
            operator = command;
            startNewInput = true;
            return;
        }

        if ("=".equals(command) && !operator.isEmpty() && !firstOperand.isEmpty()) {
            try {
                double a = Double.parseDouble(firstOperand);
                double b = Double.parseDouble(display.getText());
                double result;

                switch (operator) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0) {
                            display.setText("Error");
                            firstOperand = "";
                            operator = "";
                            startNewInput = true;
                            return;
                        }
                        result = a / b;
                        break;
                    default:
                        return;
                }

                if (result == (long) result) {
                    display.setText(String.valueOf((long) result));
                } else {
                    display.setText(String.valueOf(result));
                }
                firstOperand = display.getText();
                operator = "";
                startNewInput = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
                firstOperand = "";
                operator = "";
                startNewInput = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator_GUI::new);
    }
}
