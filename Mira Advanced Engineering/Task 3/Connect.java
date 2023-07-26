import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Connect extends JFrame{
    Container c;
    JButton btnView, btnDelete;
    JButton btnLogout;

    Connect(){
        c=getContentPane();
        c.setLayout(null);

        btnView = new JButton("View");
        btnDelete = new JButton("Delete");
        btnLogout = new JButton("Logout");

        Font f= new Font("Arial", Font.BOLD, 40);
        btnView.setFont(f);
        btnDelete.setFont(f);
        btnLogout.setFont(f);

        btnView.setBounds(115,100,250,40);
        btnDelete.setBounds(115,200,250,40);
        btnLogout.setBounds(115, 300, 250, 40);

        ActionListener a1 = (ae) ->{
            View v = new View();
            dispose();
        };
        btnView.addActionListener(a1);

        ActionListener a2 = (ae) ->{
            Delete d = new Delete();
            dispose();
        };
        btnDelete.addActionListener(a2);

        ActionListener a3 = (ae) ->{
            MainFrame ma = new MainFrame();
            dispose();
        };
        btnLogout.addActionListener(a3);

        c.add(btnView);
        c.add(btnDelete);
        c.add(btnLogout);
        c.setBackground(Color.cyan);

        setTitle("Feedback app");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}