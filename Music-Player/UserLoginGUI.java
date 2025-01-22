import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.GridLayout;

public class UserLoginGUI extends JFrame {
    JPanel p1,p2,p3,sp1,sp2;
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1;
    
    public UserLoginGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        MyActionListener a = new MyActionListener();

        b2 = new JButton("Home");
        b2.addActionListener(a);

        b3 = new JButton("<Back");
        b3.addActionListener(a);

        p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        p2 = new JPanel();
        p2.setLayout(new GridLayout(2,1));

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());

        sp1 = new JPanel();
        sp1.setLayout(new FlowLayout());

        sp2 = new JPanel();
        sp2.setLayout(new FlowLayout());

        l1 = new JLabel("User Login");

        t1 = new JTextField("ID",20);
        t2 = new JTextField("Password",20);
        b1 = new JButton("Login");

        b1.addActionListener(a);

        sp1.add(t1);
        sp2.add(t2);
        p1.add(l1);
        p2.add(sp1);
        p2.add(sp2);
        p3.add(b3);
        p3.add(b1);
        p3.add(b2);

        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);

        
        
        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            
            if(ae.getActionCommand().equals("Login"))
                {
                    try
                    {
                        // System.out.println("Hello 1");
                        int id = Integer.parseInt(t1.getText());
                        String pass = t2.getText();
                        boolean flag = false;
                        ArrayList<User> userList = User.readUserFromFile();
                        // System.out.println("Hello 2");
                        for(int i = 0; i<userList.size(); i++)
                        {
                            // System.out.println("World 1");
                            if(userList.get(i).getId()==id && userList.get(i).getPassword().equals(pass))
                            {
                                User u = userList.get(i);
                                new UserHomePage(u);
                                flag = true;
                            }
                        }
                        if(!flag)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid ID or password.");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }
                }
            else if(ae.getActionCommand().equals("Home"))
                new WelcomePageGUI();
            else if(ae.getActionCommand().equals("<Back"))
                new WelcomePageGUI();
            
            //System.out.println("Hello");
        }
    }
    public static void main(String[] args) {
        new UserLoginGUI();
    }
    
}
