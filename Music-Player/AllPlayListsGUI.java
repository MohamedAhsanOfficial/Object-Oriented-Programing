import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AllPlayListsGUI extends JFrame {

    private JList<String> objectList;
    private JButton selectButton;
    private ArrayList<PlayList> allPlayLists = new ArrayList<PlayList>();

    public AllPlayListsGUI()
    {
        allPlayLists.add(new PlayList("All songs", Song.readSongFromFile()));
        allPlayLists.addAll(0, PlayList.readPlayListFromFile());
        
        
        String[] objects = new String[allPlayLists.size()];
        for(int i=0; i<allPlayLists.size();i++)
        {
            objects[i] = allPlayLists.get(i).getName()+"      "+ allPlayLists.get(i).getSongsList().size();
        }

        setTitle("Object Selection Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());


        objectList = new JList<>(objects);

        objectList.addListSelectionListener(new MyListSelectionListener());

        selectButton = new JButton("Select");

        selectButton.addActionListener(new MyActionListener());


        JScrollPane scrollPane = new JScrollPane(objectList);
        add(scrollPane, BorderLayout.CENTER);

        add(selectButton, BorderLayout.SOUTH);

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
                        for(int i=0;i<allPlayLists.size();i++)
                        {
                            
                            if(selectedObject.split("   ")[0].equalsIgnoreCase(allPlayLists.get(i).getName()))
                                new DisplaySongsInPlayListGUI(allPlayLists.get(i));
                        }

                    }
                }
            }
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
                String selectedObject = objectList.getSelectedValue();

                if (selectedObject != null) 
                {
                    JOptionPane.showMessageDialog(AllPlayListsGUI.this,
                            "Selected Object: " + selectedObject);
                } else 
                {
                    JOptionPane.showMessageDialog(AllPlayListsGUI.this,
                            "No object selected.");
                }
        }
    }
    public static void main(String[] args) {
        // new AllPlayListsGUI();
        

    }
}
