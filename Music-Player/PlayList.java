import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayList implements Serializable{
    private String name;
    private ArrayList<Song> songsList;


    public PlayList() {
        this.name = "";
        songsList = new ArrayList<Song>();
    }
    public PlayList(String name) {
        this.name = name;
        songsList = new ArrayList<Song>();
    }

    public PlayList(String name, ArrayList<Song> sL) {
        this.name = name;
        songsList = sL;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongsList() {
        return songsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString()
    {
        String s=name;
        return s;
    }
    

    

    public static boolean addPlayListToFile(PlayList s) {
            
        try {
            File f = new File("PlayList.ser");
            ObjectOutputStream oos;
            
            if (f.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }
    
            oos.writeObject(s);
            // oos.close();
            } catch (IOException e) {
                System.out.println("Error in writing file: " + e.getMessage());
                e.printStackTrace();
            }
            return true;
    }
    public static ArrayList<PlayList> readPlayListFromFile() {
        ArrayList<PlayList> list = new ArrayList<PlayList>();
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PlayList.ser"))) {
                while (true) {
                    PlayList a1 = (PlayList) ois.readObject();
                    list.add(a1);
                    // System.out.println(a1.getName()+"___________");
                }
            }
        } catch (ClassNotFoundException e) {
            // System.out.println(e.getMessage());
        } catch (EOFException e) {
        } catch (IOException e) {
            // System.out.println(e.getMessage());
        }
        return list;
    }

    public static void deletePlayListFromFile(String PlayListName)
    {
        ArrayList<PlayList> list = readPlayListFromFile();
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).getName().equalsIgnoreCase(PlayListName))
            {
                list.remove(i);
            }
        }
        try
        {
            File f = new File("PlayList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for(int i=0; i<list.size();i++)
            {
                oos.writeObject(list.get(i));
            }
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
    }

    public static void addASongToPlayList(String PlayListName)
    {
        ArrayList<PlayList> list = readPlayListFromFile();
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).getName().equalsIgnoreCase(PlayListName))
            {
                list.remove(i);
            }
        }
        try
        {
            File f = new File("PlayList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for(int i=0; i<list.size();i++)
            {
                oos.writeObject(list.get(i));
            }
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
    }
    public static PlayList searchPlayListFromFile(String n)
    {
        ArrayList<PlayList> list = PlayList.readPlayListFromFile();
        for(int i = 0; i<list.size();i++)
        {
            // System.out.println("Comparing: " + n + " with " + list.get(i).getName());
            if(list.get(i)!=null && list.get(i).getName().trim().equalsIgnoreCase(n.trim()))
                {return list.get(i);}
                
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        
        // PlayList.addPlayListToFile(new PlayList("All Songs"));

        // ArrayList<PlayList> AllPlayLists = PlayList.readPlayListFromFile();

        // System.out.println(AllPlayLists.get(0).getName());

    }
    
}
