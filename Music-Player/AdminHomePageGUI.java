import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

// import LoginGUIselection.MyActionListener;

import java.awt.*;
public class AdminHomePageGUI extends JFrame{
    private JLabel l1;
    private JButton b1,b2,b3,b4,b5,b6,b7,b8,bHome,bBack;
    private JPanel p1,p2,p3,sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8;

    public AdminHomePageGUI()
    {
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        bHome = new JButton("Logout");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        l1 = new JLabel("Admin");
        
        
        p2 = new JPanel();
        p2.setLayout(new FlowLayout());

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(6,1));
        
        b1 = new JButton("Show all user datils");
        b1.addActionListener(a);
        b1.setPreferredSize(new Dimension(150, 20));
        sp1 = new JPanel();
        sp1.setLayout(new FlowLayout());
        sp1.add(b1);

        b2 = new JButton("Search");
        b2.addActionListener(a);
        b2.setPreferredSize(new Dimension(150, 20));
        sp2 = new JPanel();
        sp2.setLayout(new FlowLayout());
        sp2.add(b2);

        b3 = new JButton("Make user Admin");
        b3.addActionListener(a);
        b3.setPreferredSize(new Dimension(150, 20));
        sp3 = new JPanel();
        sp3.setLayout(new FlowLayout());
        sp3.add(b3);

        b4 = new JButton("Delete");
        b4.addActionListener(a);
        b4.setPreferredSize(new Dimension(150, 20));
        sp4 = new JPanel();
        sp4.setLayout(new FlowLayout());
        sp4.add(b4);

        // b5 = new JButton("Delete Admin");
        // b5.addActionListener(a);
        // b5.setPreferredSize(new Dimension(150, 20));
        // sp5 = new JPanel();
        // sp5.setLayout(new FlowLayout());
        // sp5.add(b5);

        b6 = new JButton("Show all songs");
        b6.addActionListener(a);
        b6.setPreferredSize(new Dimension(150, 20));
        sp6 = new JPanel();
        sp6.setLayout(new FlowLayout());
        sp6.add(b6);

        b7 = new JButton("Add a song");
        b7.addActionListener(a);
        b7.setPreferredSize(new Dimension(150, 20));
        sp7 = new JPanel();
        sp7.setLayout(new FlowLayout());
        sp7.add(b7);

        // p3.add(bBack);
        p3.add(bHome);



        p1.add(sp1);
        p1.add(sp3);
        p1.add(sp6);
        p1.add(sp7);
        p1.add(sp2);
        p1.add(sp4);
        // p1.add(sp5);
        p2.add(l1);


        add(p2,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
        setVisible(true);
    }

    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Show all user datils"))
                new ShowAllUserDtails();
            else if(ae.getActionCommand().equals("Search"))
                new SearchGUI();
            else if(ae.getActionCommand().equals("Make user Admin"))
                new MakeUserAadminGUI();
            else if(ae.getActionCommand().equals("Delete"))
                new DeleteUserGUI();
            else if(ae.getActionCommand().equals("Show all songs"))
                new DisplaySongsInPlayListGUI(new PlayList("All songs", Song.readSongFromFile()));
            else if(ae.getActionCommand().equals("Add a song"))
                new AddASongGUI();
            else if(ae.getActionCommand().equals("Logout"))
            {
                dispose();
                new WelcomePageGUI();
            }
        }
    }
    public static void main(String[] args) {
        new AdminHomePageGUI();
        // Song.addSongToFile(new Song("Song 1", 300));
        // Song.addSongToFile(new Song("Song 2", 200));
    }
    
}
