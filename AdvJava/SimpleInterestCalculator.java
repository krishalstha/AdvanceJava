import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleInterestCalculator extends JFrame {
    private JTextField principleField, timeField, rateField, resultField;

    public SimpleInterestCalculator() {
        // Set up the frame
        setTitle("Simple Interest Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Create labels and text fields
        add(new JLabel("Principle:"));
        principleField = new JTextField();
        add(principleField);

        add(new JLabel("Time:"));
        timeField = new JTextField();
        add(timeField);

        add(new JLabel("Rate:"));
        rateField = new JTextField();
        add(rateField);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        // Create buttons
        JButton calcInterestButton = new JButton("Simple Interest");
        add(calcInterestButton);

        JButton calcAmountButton = new JButton("Amount");
        add(calcAmountButton);

        JButton clearButton = new JButton("Clear");
        add(clearButton);

        // Button action listeners
        calcInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSimpleInterest();
            }
        });

        calcAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAmount();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void calculateSimpleInterest() {
        try {
            double principle = Double.parseDouble(principleField.getText());
            double time = Double.parseDouble(timeField.getText());
            double rate = Double.parseDouble(rateField.getText());
            double interest = (principle * time * rate) / 100;
            resultField.setText(String.valueOf(interest));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateAmount() {
        try {
            double principle = Double.parseDouble(principleField.getText());
            double interest = Double.parseDouble(resultField.getText());
            double amount = principle + interest;
            resultField.setText(String.valueOf(amount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please calculate the interest first or enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        principleField.setText("");
        timeField.setText("");
        rateField.setText("");
        resultField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleInterestCalculator().setVisible(true);
            }
        });
    }
}