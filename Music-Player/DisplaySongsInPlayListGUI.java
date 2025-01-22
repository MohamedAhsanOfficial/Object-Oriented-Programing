import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DisplaySongsInPlayListGUI extends JFrame {

    private JList<String> objectList;
    private JButton bHome,bBack;
    private ArrayList<Song> songsList;
    private JPanel panel;

    public DisplaySongsInPlayListGUI(PlayList p)
    {
        songsList = p.getSongsList();
        
        String[] objects = new String[songsList.size()];
        for(int i=0; i<songsList.size();i++)
        {
            objects[i] = songsList.get(i).getName()+"      "+ songsList.get(i).getDuration();
        }

        setTitle("Object Selection Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 220);
        setLayout(new BorderLayout());
        ActionListener a = new MyActionListener();

        objectList = new JList<>(objects);

        objectList.addListSelectionListener(new MyListSelectionListener());

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(bBack);
        panel.add(bHome);

        JScrollPane scrollPane = new JScrollPane(objectList);
        add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
    public class MyListSelectionListener implements ListSelectionListener
    {
        @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                if (!e.getValueIsAdjusting()) 
                {
                    String selectedObject = objectList.getSelectedValue();

                    if (selectedObject != null) 
                    {
                        for(int i=0;i<songsList.size();i++)
                        {
                            
                            if(selectedObject.split("   ")[0].equalsIgnoreCase(songsList.get(i).getName())){
                                JOptionPane.showMessageDialog(null, "Name: "+songsList.get(i).getName()+"\nDuration: "+songsList.get(i).getDuration());
                                break;
                            }
                        }

                    }
                }
            }
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Home")) 
                {
                    new WelcomePageGUI();
                } else if(e.getActionCommand().equals("<Back"))
                {
                    dispose();
                }
        }
    }
    public static void main(String[] args) {

        // new DisplaySongsInPlayListGUI(new PlayList("All Songs", Song.readSongFromFile()));
    }
}
