import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Delete extends JFrame {
    Container c;
    JLabel labName, labEmail;
    JTextField txtName, txtEmail;
    JButton btnSubmit, btnBack;

    Delete(){
        c=getContentPane();
        c.setLayout(null);

        labName = new JLabel("Enter Name To Delete");
        txtName = new JTextField(20);
        labEmail = new JLabel("Enter Email To Delete");
        txtEmail = new JTextField(20);
        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");

        Font f = new Font("Arial", Font.BOLD, 30);
        labName.setFont(f);
        txtName.setFont(f);
        labEmail.setFont(f);
        txtEmail.setFont(f);
        btnSubmit.setFont(f);
        btnBack.setFont(f);

        labName.setBounds(100,50,200,40);
        txtName.setBounds(100,100,300,40);
        labEmail.setBounds(100,170,200,40);
        txtEmail.setBounds(100,230,300,40);
        btnSubmit.setBounds(100,300,200,40);
        btnBack.setBounds(100,370,200,40);

        ActionListener a1 = (ae) ->{
            String name = txtName.getText();
            String email = txtEmail.getText();
            
           try {
            String url = "jdbc:mysql://localhost:3306/fb_1723";
            Connection con = DriverManager.getConnection(url,"root", "abc123");
            String sql = "delete from user where name=? AND email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2,email);
            long rc = pst.executeUpdate();
            if(rc == 0){
                JOptionPane.showMessageDialog(c,"No record exist with name = " + name + " And email = "+ email);
            }else{
            JOptionPane.showMessageDialog(c,rc + "delete");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("issue " + e);
        } catch(Exception e){
		System.out.println(e);
	}
        };
        btnSubmit.addActionListener(a1);

        ActionListener a2 = (ae) ->{
            Connect c2 = new Connect();
           dispose();
        };
        btnBack.addActionListener(a2);


        c.add(labName);
        c.add(txtName);
        c.add(labEmail);
        c.add(txtEmail);
        c.add(btnSubmit);
        c.add(btnBack);
        c.setBackground(Color.cyan);


        setTitle("Feedback app");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
