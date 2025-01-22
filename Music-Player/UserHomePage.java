import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserHomePage extends JFrame{
    private ArrayList<PlayList> allPlayLists = new ArrayList<PlayList>();
    private JList<String> jNameList;
    private JButton bCreatePlayList,bAddSongToPlayList,bHome,bBack,bDeleteList,bNew;
    User UserOfTheClass;
    private JPanel pNorth,pSouth,pNortMain,hiddenPanel;
    private JTextField t,t1,t2;
    boolean flag1,flag2;



    public UserHomePage(User u)
    {
        UserOfTheClass = u;
        flag1 = false;
        flag2 = false;

        allPlayLists.add(new PlayList("AllSongs", Song.readSongFromFile()));
        
        for(int i =0; i<u.getPersonlLists().size();i++)
        {
            if(u.getPersonlLists().get(i)!=null)
                allPlayLists.add(u.getPersonlLists().get(i));
        }
        
        String[] namesOfSongs = new String[allPlayLists.size()];
        for(int i=0; i<allPlayLists.size();i++)
        {
            namesOfSongs[i] = allPlayLists.get(i).getName().trim()+"      "+ allPlayLists.get(i).getSongsList().size();
        }

        setTitle("Object Selection Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 220);
        setLayout(new BorderLayout());

        MyActionListener a = new MyActionListener();
        MyListSelectionListener s = new MyListSelectionListener();


        bCreatePlayList = new JButton("Create PlayList");
        bCreatePlayList.addActionListener(a);

        bAddSongToPlayList = new JButton("Add Song to PlayList");
        bAddSongToPlayList.addActionListener(a);


        bHome = new JButton("Edit Profile");
        bHome.addActionListener(a);

        bBack = new JButton("<");
        bBack.addActionListener(a);

        bDeleteList = new JButton("Delete PlayList");
        bDeleteList.addActionListener(a);

        bNew = new JButton("Delete Song From PlayList");
        bNew.addActionListener(a);

        jNameList = new JList<String>(namesOfSongs);
        jNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jNameList.addListSelectionListener(s);

        JScrollPane scrollPane = new JScrollPane(jNameList);

        pNorth = new JPanel();
        pNorth.setLayout(new FlowLayout());
        pNorth.add(bCreatePlayList);
        pNorth.add(bDeleteList);

        pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout());
        pSouth.add(bNew);
        pSouth.add(bBack);
        pSouth.add(bHome);
        pSouth.add(bAddSongToPlayList);

        pNortMain = new JPanel();
        pNortMain.setLayout(new GridLayout(2, 0));
        pNortMain.add(pNorth);

        add(scrollPane, BorderLayout.CENTER);
        add(pSouth, BorderLayout.SOUTH);
        add(pNortMain, BorderLayout.NORTH);

        t1 = new JTextField("PlayList",10);

        t2 = new JTextField("Song",10);

        t = new JTextField("PlayList name",0);

        hiddenPanel = new JPanel();
        hiddenPanel.setLayout(new FlowLayout());
        hiddenPanel.add(t1);
        hiddenPanel.add(t2);

        setVisible(true);
    }
    public class MyListSelectionListener implements ListSelectionListener
    {
        @Override
            public void valueChanged(ListSelectionEvent lse) 
            {
                if (!lse.getValueIsAdjusting()) 
                {
                    String selectedObject = jNameList.getSelectedValue();

                    if (selectedObject != null) 
                    {
                        for(int i=0;i<allPlayLists.size();i++)
                        {
                            if(selectedObject.split("   ")[0].equalsIgnoreCase(allPlayLists.get(i).getName().trim()))
                            {
                                new DisplaySongsInPlayListGUI(allPlayLists.get(i));
                            }
                        }

                    }
                }
            }
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            ArrayList<Song> allSongs;
                if(e.getActionCommand().equals("Create PlayList"))
                {
                    pNortMain.remove(hiddenPanel);
                    flag2 = false;
                    if(flag1)
                    {
                        if(UserOfTheClass.CreatePlayList(t.getText()))
                        {
                            User.deleteUserFromFile(UserOfTheClass.getId());
                            User.addUserToFile(UserOfTheClass);
                            JOptionPane.showMessageDialog(null, "PlayList created");
                            dispose();
                            new UserHomePage(UserOfTheClass);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "PlayList already exist");
                        }

                    }
                    else
                    {
                        t = new JTextField("PlayList name");
                        t.setPreferredSize(new Dimension(200,20));
                        hiddenPanel = new JPanel();
                        hiddenPanel.setLayout(new FlowLayout());
                        hiddenPanel.add(t);
                        pNortMain.add(hiddenPanel);
                        setVisible(true);
                        flag1=true;
                    }
                }

                else if(e.getActionCommand().equals("Delete Song From PlayList"))
                {   
                    int x=-1;
                    pNortMain.remove(hiddenPanel);
                    flag1 = false;
                    if(flag2)
                    {
                        PlayList p = null;
                        allSongs = null;
                        allSongs = Song.readSongFromFile();
                        ArrayList<PlayList> list = UserOfTheClass.getPersonlLists();
                        for(int i=0; i<list.size();i++)
                            if(t1.getText().equals(list.get(i).getName()))
                            {
                                p=list.get(i);
                                // list.remove(i);
                            }
                        if(p==null)
                        {
                            JOptionPane.showMessageDialog(null, "PlayList not found");
                        }
                        for(int i=0; i<allSongs.size();i++)
                            if(allSongs.get(i).getName().equals(t2.getText()))
                                x =i;
                        if(x==-1)
                        {
                            JOptionPane.showMessageDialog(null, "Song not found");
                        }
                        if(x!=-1 && p!=null)
                        {
                            p.getSongsList().remove(x);
                            // list.add(p);
                            UserOfTheClass.setPersonlLists(list);
                            User.deleteUserFromFile(UserOfTheClass.getId());
                            User.addUserToFile(UserOfTheClass);
                            JOptionPane.showMessageDialog(null, "Song added to the PlayList");
                            dispose();
                            new UserHomePage(UserOfTheClass);
                        }
                    }
                    else
                    {
                        t1 = new JTextField("PlayList name",10);
                        t2 = new JTextField("Song name",10);

                        hiddenPanel = new JPanel();
                        hiddenPanel.setLayout(new FlowLayout());
                        hiddenPanel.add(t1);
                        hiddenPanel.add(t2);

                        pNortMain.add(hiddenPanel);
                        setVisible(true);
                        flag2=true;
                    }
                }
                else if(e.getActionCommand().equals("Edit Profile"))
                    {
                        new SignupGUI(UserOfTheClass);
                    }
                else if(e.getActionCommand().equals("<"))
                {
                    dispose();
                    new WelcomePageGUI();
                }
                else if(e.getActionCommand().equals("Delete PlayList"))
                {
                    pNortMain.remove(hiddenPanel);
                    flag2 = false;
                    if(flag1)
                    {
                        if(UserOfTheClass.removePlayList(t.getText()))
                        {
                            // PlayList newPlayList = new PlayList(t.getText());
                            // UserOfTheClass.getPersonlLists().add(newPlayList);
                            User.deleteUserFromFile(UserOfTheClass.getId());
                            User.addUserToFile(UserOfTheClass);
                            // System.out.println("_________"+UserOfTheClass.toString()+"_ "+UserOfTheClass.getPersonlLists().size());
                            JOptionPane.showMessageDialog(null, "PlayList Deleted");
                            dispose();
                            new UserHomePage(UserOfTheClass);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "PlayList not found");
                        }

                    }
                    else
                    {
                        t = new JTextField("PlayList name");
                        t.setPreferredSize(new Dimension(200,20));
                        hiddenPanel = new JPanel();
                        hiddenPanel.setLayout(new FlowLayout());
                        hiddenPanel.add(t);
                        pNortMain.add(hiddenPanel);
                        setVisible(true);
                        flag1=true;
                    }
                }
                else if(e.getActionCommand().equals("Add Song to PlayList"))
                {   
                    pNortMain.remove(hiddenPanel);
                    flag1 = false;
                    if(flag2)
                    {
                        PlayList p = null;
                        Song s=null;
                        allSongs = null;
                        allSongs = Song.readSongFromFile();
                        ArrayList<PlayList> list = UserOfTheClass.getPersonlLists();
                        for(int i=0; i<list.size();i++)
                            if(t1.getText().equals(list.get(i).getName()))
                            {
                                p=list.get(i);
                                list.remove(i);
                            }
                        if(p==null)
                        {
                            JOptionPane.showMessageDialog(null, "PlayList not found");
                        }
                        for(int i=0; i<allSongs.size();i++)
                            if(allSongs.get(i).getName().equals(t2.getText()))
                                s = allSongs.get(i);
                        if(s==null)
                        {
                            JOptionPane.showMessageDialog(null, "Song not found");
                        }
                        if(s!=null && p!=null)
                        {
                            p.getSongsList().add(s);
                            list.add(p);
                            UserOfTheClass.setPersonlLists(list);
                            User.deleteUserFromFile(UserOfTheClass.getId());
                            User.addUserToFile(UserOfTheClass);
                            JOptionPane.showMessageDialog(null, "Song added to the PlayList");
                            dispose();
                            new UserHomePage(UserOfTheClass);
                        }
                    }
                    else
                    {
                        t1 = new JTextField("PlayList name",10);
                        t2 = new JTextField("Song name",10);

                        hiddenPanel = new JPanel();
                        hiddenPanel.setLayout(new FlowLayout());
                        hiddenPanel.add(t1);
                        hiddenPanel.add(t2);

                        pNortMain.add(hiddenPanel);
                        setVisible(true);
                        flag2=true;
                    }
                }
        }
    }
    public static void main(String[] args) {
        new UserHomePage(new User("Ahsan","@gmail",12345,"qwertyuio"));
    }
}
