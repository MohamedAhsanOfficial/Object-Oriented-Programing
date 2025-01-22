import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SearchGUI extends JFrame{
    JTextField t;
    JLabel l;
    JButton b1,b2,b3, bHome, bBack;
    JPanel p1,p2,p3;

    public SearchGUI()
    {
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        MyActionListener a = new MyActionListener();

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        l = new JLabel("Search");
        add(l,BorderLayout.NORTH);
        
        t = new JTextField("Search",20);
        t.setPreferredSize(new Dimension(200, 20));

        b1 = new JButton("Search User");
        b1.addActionListener(a);
        b1.setPreferredSize(new Dimension(150,20));

        
        b2 = new JButton("Search Song");
        b2.addActionListener(a);
        b2.setPreferredSize(new Dimension(150,20));
        
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(t);
        p1.add(b1);
        p1.add(b2);

        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        p2.add(bBack);
        p2.add(bHome);

        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("Search User"))
            {
                try
                {
                    int i = Integer.parseInt(t.getText());
                    User u = User.searchUserFromFile(i);

                    if(u==null)
                        JOptionPane.showMessageDialog(null, "User not found");
                    
                    else
                        JOptionPane.showMessageDialog(null, "Name: "+u.getName()+"\nID :"+u.getId()+"\nEmail: "+u.getEmail());
                }
                catch(NumberFormatException e)
                {
                    String n = t.getText();
                    User u = User.searchUserFromFile(n);

                    if(u==null)
                        JOptionPane.showMessageDialog(null, "User not found");
                    
                    else
                        JOptionPane.showMessageDialog(null, "Name: "+u.getName()+"\nID :"+u.getId()+"\nEmail: "+u.getEmail());
                    
                }

            }

            if(ae.getActionCommand().equals("Search Song"))
            {
                String n = t.getText();
                Song s = Song.searchSongFromFile(n);

                if(s==null)
                {
                    JOptionPane.showMessageDialog(null, "Song not found");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Name: "+s.getName()+"\nDuration :"+s.getDuration());
                }
                
            }
                
            if(ae.getActionCommand().equals("Search PlayList"))
            {
                String n = t.getText();
                PlayList s = PlayList.searchPlayListFromFile(n);

                if(s==null)
                    JOptionPane.showMessageDialog(null, "PlayList not found");
                
                else
                    JOptionPane.showMessageDialog(null, s.getName()+" exist");
                
                
            }

            else if(ae.getActionCommand().equals("Home"))
                new WelcomePageGUI();

            else if(ae.getActionCommand().equals("<Back"))
                dispose();
            
        }
    }
    public static void main(String[] args) {
        new SearchGUI();
        // Song.addSongToFile(new Song("Song", 300));
        // ArrayList<Song> list = Song.readSongFromFile();
        // System.out.println(list.get(0).getName());
        // PlayList.addPlayListToFile(new PlayList("Test PlayList 1"));
        // PlayList.readPlayListFromFile();

    }
}


