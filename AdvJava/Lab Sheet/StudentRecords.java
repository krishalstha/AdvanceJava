import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class StudentRecords extends JFrame {
    private JTextField rollNumberField, nameField, levelField, ageField;
    private JButton addButton, deleteButton, findButton, modifyButton;
    private Map<String, Student> studentDatabase;

    public StudentRecords() {
        // Initialize student database
        studentDatabase = new HashMap<>();

        // Set up the frame
        setTitle("Student Records");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Create and add components
        add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        add(rollNumberField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Level:"));
        levelField = new JTextField();
        add(levelField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        addButton = new JButton("Add");
        addButton.addActionListener(new ButtonClickListener());
        add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ButtonClickListener());
        add(deleteButton);

        findButton = new JButton("Find");
        findButton.addActionListener(new ButtonClickListener());
        add(findButton);

        modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ButtonClickListener());
        add(modifyButton);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String rollNumber = rollNumberField.getText();
            String name = nameField.getText();
            String level = levelField.getText();
            String age = ageField.getText();

            if (e.getSource() == addButton) {
                if (!studentDatabase.containsKey(rollNumber)) {
                    studentDatabase.put(rollNumber, new Student(rollNumber, name, level, Integer.parseInt(age)));
                    JOptionPane.showMessageDialog(null, "Student added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student with this roll number already exists.");
                }
            } else if (e.getSource() == deleteButton) {
                if (studentDatabase.containsKey(rollNumber)) {
                    studentDatabase.remove(rollNumber);
                    JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student with this roll number does not exist.");
                }
            } else if (e.getSource() == findButton) {
                Student student = studentDatabase.get(rollNumber);
                if (student != null) {
                    nameField.setText(student.getName());
                    levelField.setText(student.getLevel());
                    ageField.setText(String.valueOf(student.getAge()));
                } else {
                    JOptionPane.showMessageDialog(null, "Student with this roll number does not exist.");
                }
            } else if (e.getSource() == modifyButton) {
                if (studentDatabase.containsKey(rollNumber)) {
                    studentDatabase.put(rollNumber, new Student(rollNumber, name, level, Integer.parseInt(age)));
                    JOptionPane.showMessageDialog(null, "Student record updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student with this roll number does not exist.");
                }
            }
        }
    }

    private class Student {
        private String rollNumber, name, level;
        private int age;

        public Student(String rollNumber, String name, String level, int age) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.level = level;
            this.age = age;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentRecords().setVisible(true);
            }
        });
    }
}
