import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeForm {
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:employee.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS employee (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    salary real\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewTable();

        JFrame frame = new JFrame("Employee Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Employee ID:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(100, 20, 165, 25);
        panel.add(idText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 50, 165, 25);
        panel.add(nameText);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(10, 80, 80, 25);
        panel.add(salaryLabel);

        JTextField salaryText = new JTextField(20);
        salaryText.setBounds(100, 80, 165, 25);
        panel.add(salaryText);

        JButton insertButton = new JButton("Insert");
        insertButton.setBounds(10, 110, 80, 25);
        panel.add(insertButton);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(100, 110, 80, 25);
        panel.add(viewButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(190, 110, 80, 25);
        panel.add(deleteButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                String name = nameText.getText();
                String salary = salaryText.getText();

                if (id.isEmpty() || name.isEmpty() || salary.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO employee(id, name, salary) VALUES(?, ?, ?)";

                try (Connection conn = connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    pstmt.setString(2, name);
                    pstmt.setDouble(3, Double.parseDouble(salary));
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Employee ID is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "SELECT id, name, salary FROM employee WHERE id = ?";

                try (Connection conn = connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "ID: " + rs.getInt("id") + "\nName: " + rs.getString("name") + "\nSalary: " + rs.getDouble("salary"), "Employee Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No record found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Employee ID is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "DELETE FROM employee WHERE id = ?";

                try (Connection conn = connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Record deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No record found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
