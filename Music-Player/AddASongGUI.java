import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class AddASongGUI extends JFrame{
    JLabel l;
    JTextField t1,t2;
    JButton b,bHome,bBack;
    JPanel p,p2;
    boolean flag;

    public AddASongGUI()
    {
        flag = false;
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        l = new JLabel("Add a song");
        
        p = new JPanel();
        p.setLayout(new FlowLayout());


        p2 = new JPanel();
        p2.setLayout(new FlowLayout());

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        b = new JButton("Add");
        b.addActionListener(a);

        t1 = new JTextField("Song name",20);
        t2 = new JTextField("Song Duration",20);

        p2.add(t1);
        p2.add(t2);

        p.add(bBack);
        p.add(b);
        p.add(bHome);
        add(l,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p,BorderLayout.SOUTH);

        
        setVisible(true);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Add"))
            {
                try{
                    String n = t1.getText();
                    int duration = Integer.parseInt(t2.getText());
                    if(!Song.addSongToFile(new Song(n, duration)))
                        JOptionPane.showMessageDialog(null, "Song already exist");
                    else
                        JOptionPane.showMessageDialog(null, "Song added");

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
        new AddASongGUI();
    }
}

