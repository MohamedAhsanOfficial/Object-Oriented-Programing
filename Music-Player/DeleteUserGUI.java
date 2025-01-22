import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class DeleteUserGUI extends JFrame{
    JLabel l;
    JTextField t;
    JButton b1,b2,b3,bHome,bBack;
    JPanel p,p2;

    public DeleteUserGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        l = new JLabel("Delete");

        
        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        b1 = new JButton("Delete User");
        b1.addActionListener(a);
        b1.setPreferredSize(new Dimension(150,20));

        b2 = new JButton("Delete Admin");
        b2.addActionListener(a);
        b2.setPreferredSize(new Dimension(150,20));


        b3 = new JButton("Delete Song");
        b3.addActionListener(a);
        b3.setPreferredSize(new Dimension(150,20));


        t = new JTextField("ID of user",20);

        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bBack);
        p.add(bHome);


        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(t);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        add(l,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p,BorderLayout.SOUTH);

        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Delete User"))
            {
                try
                {
                    // System.out.println("Hello 1");
                    int i = Integer.parseInt(t.getText());
                    User u = User.searchUserFromFile(i);
                    if(u==null)
                    {
                        // System.out.println("Hello 2");
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                    else{
                        User.deleteUserFromFile(u.getId());
                        JOptionPane.showMessageDialog(null, "User "+u.getName()+" deleted");

                    }
                }
                catch(NumberFormatException e)
                {
                    String n = t.getText();
                    User u = User.searchUserFromFile(n);
                    if(u==null)
                    {
                        // System.out.println("Hello 3");
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                    else{
                        // System.out.println("Hello 4");
                        User.deleteUserFromFile(u.getId());
                        JOptionPane.showMessageDialog(null, "User "+u.getName()+" deleted");
                    }
                }

            }
            if(ae.getActionCommand().equals("Delete Admin"))
            {
                try
                {
                    // System.out.println("Hello 1");
                    int i = Integer.parseInt(t.getText());
                    Admin u = Admin.searchAdminFromFile(i);
                    if(u==null)
                    {
                        // System.out.println("Hello 2");
                        JOptionPane.showMessageDialog(null, "Admin not found");
                    }
                    else{
                        Admin.deleteAdminFromFile(u.getId());
                        JOptionPane.showMessageDialog(null, "Admin "+u.getName()+" deleted");

                    }
                }
                catch(NumberFormatException e)
                {
                    String n = t.getText();
                    Admin a = Admin.searchAdminFromFile(n);
                    if(a==null)
                    {
                        // System.out.println("Hello 3");
                        JOptionPane.showMessageDialog(null, "Admin not found");
                    }
                    else{
                        // System.out.println("Hello 4");
                        Admin.deleteAdminFromFile(a.getId());
                        JOptionPane.showMessageDialog(null, "Admin "+a.getName()+" deleted");
                    }
                }
            }
            if(ae.getActionCommand().equals("Delete Song"))
            {
                String n = t.getText();
                Song s = Song.searchSongFromFile(n);
                if(s==null)
                {
                    // System.out.println("Hello 3");
                    JOptionPane.showMessageDialog(null, "Song not found");
                }
                else{
                    // System.out.println("Hello 4");
                    Song.deleteSongFromFile(s.getName());
                    JOptionPane.showMessageDialog(null, "Song "+s.getName()+" deleted");
                }
            }
            else if(ae.getActionCommand().equals("<Back"))
                dispose();
            else if(ae.getActionCommand().equals("Home"))
                new WelcomePageGUI();
        }
    }
    public static void main(String[] args) {
        new DeleteUserGUI();
    }
}

