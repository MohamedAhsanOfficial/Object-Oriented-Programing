import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class DeleteAdminGUI extends JFrame{
    JLabel l;
    JTextField t;
    JButton b,bHome,bBack;
    JPanel p;

    public DeleteAdminGUI()
    {
        setSize(300,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        l = new JLabel("Delete Admin");

        p = new JPanel();
        p.setLayout(new FlowLayout());

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        b = new JButton("Delete");
        b.addActionListener(a);

        t = new JTextField("ID of Admin");

        p.add(bBack);
        p.add(b);
        p.add(bHome);
        add(l,BorderLayout.NORTH);
        add(t,BorderLayout.CENTER);
        add(p,BorderLayout.SOUTH);

        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Delete"))
            {
                try{
                    int id = Integer.parseInt(t.getText());
                    boolean flag = Admin.deleteAdminFromFile(id);
                    if(flag)
                    {
                        JOptionPane.showMessageDialog(null, "Done");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Admin not found");
                    }

                }
                catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Invlid Input");}
            }
            else if(ae.getActionCommand().equals("<Back"))
                dispose();
            else if(ae.getActionCommand().equals("Home"))
                new WelcomePageGUI();
        }
    }
    public static void main(String[] args) {
        // new DeleteAdminGUI();
    }
}

