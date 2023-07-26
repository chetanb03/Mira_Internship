import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

class View extends JFrame {
    Container c;
    JLabel labName, labEmail, labFeedback, labRating;
    JButton btnBack;
    JTextArea txtRes;
    JScrollPane scrollPane;

    View(){
        c=getContentPane();
        c.setLayout(null);

        labName = new JLabel("Name");
        labEmail = new JLabel("Email");
        labFeedback = new JLabel("Feedback");
        labRating = new JLabel("Rating");
        txtRes = new JTextArea(20,20);
        scrollPane = new JScrollPane(txtRes);
        btnBack=new JButton("Back");
        
        Font f = new Font("Arial", Font.BOLD, 30);
        Font f2 = new Font("Arial", Font.PLAIN, 20);
        labName.setFont(f);
        labEmail.setFont(f);
        labFeedback.setFont(f);
        labRating.setFont(f);
        txtRes.setFont(f2);
        scrollPane.setFont(f2);
        btnBack.setFont(f);

        labName.setBounds(150,100,100,40);
        labEmail.setBounds(250,100,100,40);
        labFeedback.setBounds(350,100,150,40);
        labRating.setBounds(500,100,100,40);
        scrollPane.setBounds(150, 180, 500, 300);
        // txtRes.setBounds(150,150,400,300);
        btnBack.setBounds(300,500,200,40);

        c.add(labName);
        c.add(labEmail);
        c.add(labFeedback);
        c.add(labRating);
        // c.add(txtRes);
        c.add(scrollPane);
        c.add(btnBack);
        c.setBackground(Color.cyan);

       String info ="";
        try {
            String url = "jdbc:mysql://localhost:3306/fb_1723";
            Connection con = DriverManager.getConnection(url, "root", "abc123");

            String sql = "select * from user";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String email = rs.getString(2);
                String feedback = rs.getString(3);
                feedback= feedback.substring(0, Math.min(feedback.length(), 20));

                String rating = rs.getString(4);
                info+= "Name: " + name + "; Email: "+ email+"; Feedback: "+feedback+"; Rating: "+rating+ "\n";
            }

            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(c, "issue" + e);
        }

        txtRes.setText(info);
	txtRes.setEditable(false);

        ActionListener a = (ae) -> {
            Connect c = new Connect();
            dispose();
        };

        btnBack.addActionListener(a);

        setTitle("Feedback app");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
