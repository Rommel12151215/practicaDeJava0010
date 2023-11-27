import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class GradeSystem extends JFrame {
   private JTextField nameField;
   private JTextField gradeField;
   private JButton addButton;
   private JLabel statusLabel;

   public GradeSystem() {
       setLayout(new FlowLayout());
       setSize(300, 200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       nameField = new JTextField(20);
       gradeField = new JTextField(20);
       addButton = new JButton("Add Grade");
       statusLabel = new JLabel("Status: Waiting...");

       addButton.addActionListener(e -> {
           String name = nameField.getText();
           String grade = gradeField.getText();
           try {
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");
               Statement stmt = conn.createStatement();
               String sql = "INSERT INTO grades (name, grade) VALUES ('" + name + "', '" + grade + "')";
               stmt.executeUpdate(sql);
               statusLabel.setText("Status: Success");
           } catch (SQLException ex) {
               statusLabel.setText("Status: Error");
           }
       });

       add(new JLabel("Name:"));
       add(nameField);
       add(new JLabel("Grade:"));
       add(gradeField);
       add(addButton);
       add(statusLabel);
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new GradeSystem().setVisible(true));
   }
}
