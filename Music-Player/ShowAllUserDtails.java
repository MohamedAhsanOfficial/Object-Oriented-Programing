import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShowAllUserDtails extends JFrame {
    
    private JList<String> jNameList;
    private JButton bHome,bBack;
    private JPanel p;
    private ArrayList<User> allUsers;

    public ShowAllUserDtails()
    {
        allUsers = User.readUserFromFile();
                
        String[] names = new String[allUsers.size()];
        for(int i=0; i<allUsers.size();i++)
        {
            names[i] = allUsers.get(i).getId()+"      "+ allUsers.get(i).getName();
        }

        setTitle("Object Selection Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        MyListSelectionListener s = new MyListSelectionListener();
        MyActionListener a = new MyActionListener();
        
        jNameList = new JList<>(names);

        
        jNameList.addListSelectionListener(s);

        bHome = new JButton("Home");
        bHome.addActionListener(a);

        bBack = new JButton("<Back");
        bBack.addActionListener(a);
        
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bBack);
        p.add(bHome);


        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(jNameList);
        add(scrollPane, BorderLayout.CENTER);

        add(p, BorderLayout.SOUTH);

        setVisible(true);
    }
    public class MyListSelectionListener implements ListSelectionListener
    {
        @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                if (!e.getValueIsAdjusting()) 
                {
                    String selectedObject = jNameList.getSelectedValue();
                    if (selectedObject != null) 
                    {
                        for(int i=0;i<allUsers.size();i++)
                        {
                            if(Integer.parseInt(selectedObject.split("   ")[0])==allUsers.get(i).getId())
                                JOptionPane.showMessageDialog(null, "ID: "+allUsers.get(i).getId()+"\nName: "+allUsers.get(i).getName()+"\nEmail:"+allUsers.get(i).getEmail());
                        }

                    }
                }
            }
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae) {
                if(ae.getActionCommand().equals("Home"))
                    new WelcomePageGUI();
                else if(ae.getActionCommand().equals("<Back"))
                    dispose();
        }
    }
    public static void main(String[] args) {
        // new ShowAllUserDtails();
    }
}
