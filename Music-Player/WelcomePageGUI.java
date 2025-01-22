import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

// import javax.swing.*;

public class WelcomePageGUI extends JFrame{
    private JButton b1,b2,b3;
    private JPanel p1,p2,p3;
    private JLabel l1;
    JTextField t1,t2;
    

    //private JLabel l1;
    // private JButton b2;

    public WelcomePageGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        MyActionListener a = new MyActionListener();

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());

        l1 = new JLabel("Music Player");
        p3.add(l1);

        // l2 = new JLabel("");
        // l3 = new JLabel("");



        b1 = new JButton("User Login");
        b1.setPreferredSize(new Dimension(110,20));
        b1.addActionListener(a);

        b2 = new JButton("Admin Login");
        b2.setPreferredSize(new Dimension(110,20));
        b2.addActionListener(a);

        b3 = new JButton("Signup");
        b3.setPreferredSize(new Dimension(100,20));
        b3.addActionListener(a);

        t1 = new JTextField("ID",20);

        t2 = new JTextField("Password",20);

        p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        p1.add(t1);
        p1.add(t2);
        p1.add(b1);
        p1.add(b2);
        
        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        p2.add(b3);



        

        add(p3,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);

        
        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Admin Login"))
                {
                    try{
                        boolean flag = false;
                        // System.out.println("Hello 1");
                        int id = Integer.parseInt(t1.getText());
                        String pass = t2.getText();

                        ArrayList<Admin> adminList = Admin.readAdminFromFile();
                        // System.out.println("Hello 2");
                        for(int i = 0; i<adminList.size(); i++)
                        {
                            // System.out.println("World 1");
                            if(adminList.get(i).getId()==id && adminList.get(i).getPassword().equals(pass))
                            {
                                flag = true;
                                dispose();
                                new AdminHomePageGUI();
                            }
                        }
                        if(!flag)
                            JOptionPane.showMessageDialog(null, "Wrong password or ID");

                    }catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid Input");}
                }
                if(ae.getActionCommand().equals("User Login"))
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
                                dispose();
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
            else if(ae.getActionCommand().equals("Signup"))
            {
                dispose();
                new SignupGUI();
            }

            
        }
        
    }

   public static void main(String[] args) {
    // new WelcomePageGUI();

   } 
}
