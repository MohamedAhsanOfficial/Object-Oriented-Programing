import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class MakeUserAadminGUI extends JFrame{
    JLabel l;
    JTextField t;
    JButton b,bHome,bBack;
    JPanel p,pForT;

    public MakeUserAadminGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        l = new JLabel("Enter the user's ID you want to make admin");

        p = new JPanel();
        p.setLayout(new FlowLayout());

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        b = new JButton("Proceed>");
        b.addActionListener(a);
        b.setPreferredSize(new Dimension(100,20));

        t = new JTextField("ID of user",20);

        pForT = new JPanel();
        pForT.setLayout(new FlowLayout());
        pForT.add(t);
        pForT.add(b);

        p.add(bBack);
        // p.add(b);
        p.add(bHome);
        add(l,BorderLayout.NORTH);
        add(pForT,BorderLayout.CENTER);
        add(p,BorderLayout.SOUTH);

        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Proceed>"))
            {
                try{
                    int id = Integer.parseInt(t.getText());
                    boolean flag = Admin.makeAUserAdmin(id);
                    if(flag)
                    {
                        JOptionPane.showMessageDialog(null, "Done");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }

                }catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Invlid Input");}
            }
            else if(ae.getActionCommand().equals("Search"))
                new SearchGUI();
            else if(ae.getActionCommand().equals("<Back"))
                dispose();
            else if(ae.getActionCommand().equals("Home"))
                new WelcomePageGUI();
        }
    }
    public static void main(String[] args) {
        new MakeUserAadminGUI();
    }
}
