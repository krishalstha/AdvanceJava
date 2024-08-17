import javax.swing.*;
import java.awt.*;
class EmpDB extends JFrame implements ActionListener{
    JLabel leid, lename,lage,lbaner;
    JTextField teid, tename, tage;
    JButton insert, delete, update, search;
    EmpDB(){
        setSize(450,300);
        setTitle("Employee Data Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        P1 = new JPanel();
        P2 = new JPanel();
        P3 = new JPanel();
        P4 = new JPanel();
        P5 = new JPanel();
        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        add(p3, BorderLayout.NORTH);
        p1.setLayout(new GridLayuot(1,2));
        p1.add(p4);
        p1.add(p5);

        p3.setLayout(new FlowLayout(FlowLayout.CENTER,75,20));
        lbaber = new JLabel("Employee Data Management System");
        lbaner.setFont(new Font("Elephant",Font.PLATN,24));
        lbaner.setOpaque(true);
        lbaner.setBackground(Color.CYAN);
        lbaner.setForground(Color.BLUE);
        p3.add(lbaner);

        p4.setLayout (new FlowLayout(FlowLayout.LEFT,75,20));
        p5.setLayout (new FlowLayout((FlowLayout.LEFT,25,20));
    }
}
public class EmpDB {
    
}
