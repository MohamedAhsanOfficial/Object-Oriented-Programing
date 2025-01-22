import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class SignupGUI extends JFrame {
    JPanel p1,p2,p3,sp1,sp2,sp3,sp4;
    JTextField t1,t2,t3,t4;
    JButton b1,b2;
    JLabel l1;
    
    public SignupGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        MyActionListener a = new MyActionListener();

        p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        p2 = new JPanel();
        p2.setLayout(new GridLayout(4,1));

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());

        sp1 = new JPanel();
        sp1.setLayout(new FlowLayout());

        sp2 = new JPanel();
        sp2.setLayout(new FlowLayout());

        sp3 = new JPanel();
        sp3.setLayout(new FlowLayout());

        sp4 = new JPanel();
        sp4.setLayout(new FlowLayout());

        l1 = new JLabel("User Signup");


        t1 = new JTextField("Name",20);
        t2 = new JTextField("Email",20);
        t3 = new JTextField("ID",20);
        t4 = new JTextField("Password",20);

        b1 = new JButton("Signup");
        b1.addActionListener(a);
        b1.setPreferredSize(new Dimension(110,20));


        b2 = new JButton("<Back");
        b2.addActionListener(a);
        b2.setPreferredSize(new Dimension(110,20));


        sp1.add(t1);
        sp2.add(t2);
        sp3.add(t3);
        sp4.add(t4);
        p1.add(l1);
        p2.add(sp1);
        p2.add(sp2);
        p2.add(sp3);
        p2.add(sp4);
        p3.add(b2);
        p3.add(b1);

        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);

        
        
        
        setVisible(true);
    }

    //For edit user profile
    public SignupGUI(User u)
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        MyActionListener a = new MyActionListener();

        p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        p2 = new JPanel();
        p2.setLayout(new GridLayout(4,1));

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());

        sp1 = new JPanel();
        sp1.setLayout(new FlowLayout());

        sp2 = new JPanel();
        sp2.setLayout(new FlowLayout());

        sp3 = new JPanel();
        sp3.setLayout(new FlowLayout());

        sp4 = new JPanel();
        sp4.setLayout(new FlowLayout());

        l1 = new JLabel("User Signup");


        t1 = new JTextField(u.getName(),20);
        t2 = new JTextField(u.getEmail(),20);
        t3 = new JTextField(u.getId()+"",20);
        t3.setEditable(false);
        t4 = new JTextField(u.getPassword(),20);

        
        b1 = new JButton("Save Changes");
        b1.addActionListener(a);
        b1.setPreferredSize(new Dimension(110,20));


        b2 = new JButton("< Back");
        b2.addActionListener(a);
        b2.setPreferredSize(new Dimension(110,20));


        sp1.add(t1);
        sp2.add(t2);
        sp3.add(t3);
        sp4.add(t4);
        p1.add(l1);
        p2.add(sp1);
        p2.add(sp2);
        p2.add(sp3);
        p2.add(sp4);
        p3.add(b2);
        p3.add(b1);

        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
        
        
        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
                
                if(ae.getActionCommand().equals("Signup"))
                {
                    try{
                        User u = new User(t1.getText(),t2.getText(),Integer.parseInt(t3.getText()),t4.getText());
                        boolean added = User.addUserToFile(u);
                        if(added)JOptionPane.showMessageDialog(null,"Your account has been created. Go back to login Section and enter your id and password");
                        else JOptionPane.showMessageDialog(null, "ID is already in use. try another ID");
                    }
                    catch(NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }

                }
                if(ae.getActionCommand().equals("Save Changes"))
                {
                    try{
                        User u = new User(t1.getText(),t2.getText(),Integer.parseInt(t3.getText()),t4.getText());
                        User.deleteUserFromFile(u.getId());
                        User.addUserToFile(u);
                        dispose();
                        new UserHomePage(u);

                        // else JOptionPane.showMessageDialog(null, "ID is already in use. try another ID");
                    }
                    catch(NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }

                }
                else if(ae.getActionCommand().equals("<Back"))
                {
                    dispose();
                    new WelcomePageGUI();
                }
                else if(ae.getActionCommand().equals("< Back"))
                {
                    dispose();
                }
        }
    }
    public static void main(String[] args) {
        // new SignupGUI();
    }
    
}
