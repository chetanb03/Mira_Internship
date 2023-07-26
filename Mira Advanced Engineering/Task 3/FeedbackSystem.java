import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

class MainFrame extends JFrame {
    private Container container;
    private JLabel nameLabel, emailLabel, feedbackLabel;
    private JTextField nameField, emailField;
    private JTextArea feedbackArea;
    private JRadioButton excellentBtn, veryGoodBtn, goodBtn, satisfactoryBtn, poorBtn;
    private JButton submitButton, adminButton;

    MainFrame() {
        container = getContentPane();
        container.setLayout(null);

        nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField();

        emailLabel = new JLabel("Enter Email:");
        emailField = new JTextField();

        feedbackLabel = new JLabel("Enter Feedback:");
        feedbackArea = new JTextArea();

        excellentBtn = new JRadioButton("Excellent");
        veryGoodBtn = new JRadioButton("Very Good");
        goodBtn = new JRadioButton("Good");
        satisfactoryBtn = new JRadioButton("Satisfactory");
        poorBtn = new JRadioButton("Poor");

        submitButton = new JButton("Submit");
        adminButton = new JButton("Admin");

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        nameLabel.setFont(labelFont);
        nameField.setFont(textFieldFont);
        emailLabel.setFont(labelFont);
        emailField.setFont(textFieldFont);
        feedbackLabel.setFont(labelFont);
        feedbackArea.setFont(textFieldFont);

        excellentBtn.setFont(labelFont);
        veryGoodBtn.setFont(labelFont);
        goodBtn.setFont(labelFont);
        satisfactoryBtn.setFont(labelFont);
        poorBtn.setFont(labelFont);

        submitButton.setFont(buttonFont);
        adminButton.setFont(buttonFont);

        nameLabel.setBounds(50, 50, 150, 30);
        nameField.setBounds(200, 50, 200, 30);

        emailLabel.setBounds(50, 100, 150, 30);
        emailField.setBounds(200, 100, 200, 30);

        feedbackLabel.setBounds(50, 150, 150, 30);
        feedbackArea.setBounds(200, 150, 200, 100);

        excellentBtn.setBounds(50, 270, 150, 30);
        veryGoodBtn.setBounds(200, 270, 150, 30);
        goodBtn.setBounds(350, 270, 100, 30);
        satisfactoryBtn.setBounds(50, 310, 150, 30);
        poorBtn.setBounds(200, 310, 100, 30);

        submitButton.setBounds(100, 400, 150, 30);
        adminButton.setBounds(270, 400, 150, 30);

        ButtonGroup ratingGroup = new ButtonGroup();
        ratingGroup.add(excellentBtn);
        ratingGroup.add(veryGoodBtn);
        ratingGroup.add(goodBtn);
        ratingGroup.add(satisfactoryBtn);
        ratingGroup.add(poorBtn);

        ActionListener submitListener = (ae) -> {
            if (validateFields()) {
                try {
                    String url = "jdbc:mysql://localhost:3306/fb_1723";
                    Connection con = DriverManager.getConnection(url, "root", "abc123");

                    String name = nameField.getText();
                    String email = emailField.getText();
                    String fb = feedbackArea.getText();
                    String rating = "";

                    if (excellentBtn.isSelected()) rating = "Excellent";
                    else if (veryGoodBtn.isSelected()) rating = "Very Good";
                    else if (goodBtn.isSelected()) rating = "Good";
                    else if (satisfactoryBtn.isSelected()) rating = "Satisfactory";
                    else rating = "Poor";

                    String sql = "insert into user values (?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setString(2, email);
                    pst.setString(3, fb);
                    pst.setString(4, rating);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(container, "Added");

                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(container, "Issue: " + e);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(container, "Issue: " + e);
                }
            }
        };
        submitButton.addActionListener(submitListener);

        ActionListener adminListener = (ae) -> {
            Admin a = new Admin();
            // c.setVisible(true);
            dispose();
        };
        adminButton.addActionListener(adminListener);

        container.add(nameLabel);
        container.add(nameField);
        container.add(emailLabel);
        container.add(emailField);
        container.add(feedbackLabel);
        container.add(feedbackArea);
        container.add(excellentBtn);
        container.add(veryGoodBtn);
        container.add(goodBtn);
        container.add(satisfactoryBtn);
        container.add(poorBtn);
        container.add(submitButton);
        container.add(adminButton);
        container.setBackground(Color.cyan);

        setTitle("Feedback Management System");
        setSize(500, 500); // Adjusted frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private boolean validateFields() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || feedbackArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(container, "Please fill in all fields.");
            return false;
        }

        if (!isValidEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(container, "Please enter a valid email address.");
            return false;
        }

        if (!excellentBtn.isSelected() && !veryGoodBtn.isSelected() && !goodBtn.isSelected()
                && !satisfactoryBtn.isSelected() && !poorBtn.isSelected()) {
            JOptionPane.showMessageDialog(container, "Please select a rating.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

        

class Admin extends JFrame {
       Container c;
    JLabel labEmail, labPassword;
    JTextField txtEmail ;
    JPasswordField txtPassword;
    JButton btnSubmit, btnBack;

    Admin(){
        c=getContentPane();
        c.setLayout(null);

        labEmail = new JLabel("Enter Email");
        txtEmail = new JTextField(20);
        labPassword = new JLabel("Enter Password");
        txtPassword = new JPasswordField(20);
        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");

        Font f = new Font("Arial", Font.BOLD, 40);
        labEmail.setFont(f);
        txtEmail.setFont(f);
        labPassword.setFont(f);
        txtPassword.setFont(f);
        btnSubmit.setFont(f);
        btnBack.setFont(f);

        labEmail.setBounds(80,50,350,40);
        txtEmail.setBounds(80,100,350,40);
        labPassword.setBounds(80,150,350,40);
        txtPassword.setBounds(80,200,350,40);
        btnSubmit.setBounds(150,270,200,40);
        btnBack.setBounds(150,340,200,40);

        ActionListener a1 = (ae) ->{
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            
            try {
                // /DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/fb_1723";
                Connection con = DriverManager.getConnection(url, "root", "abc123");

                String sql = "select * from admin where email=? and password=?";
                PreparedStatement pst =  con.prepareStatement(sql);
                pst.setString(1,email);
                pst.setString(2,password);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    Connect c = new Connect();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(c,"Invalid login");
                }
                con.close();
                
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(c,"Issue " + e);
            }
        };
        btnSubmit.addActionListener(a1);

        ActionListener a2 = (ae) ->{
            MainFrame m = new MainFrame();
           dispose();
        };
        btnBack.addActionListener(a2);


        c.add(labEmail);
        c.add(txtEmail);
        c.add(labPassword);
        c.add(txtPassword);
        c.add(btnSubmit);
        c.add(btnBack);
        c.setBackground(Color.cyan);


        setTitle("Admin Panel");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    
    }

}
}
public class FeedbackSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}

 