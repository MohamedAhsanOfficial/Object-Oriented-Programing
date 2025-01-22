import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable{
    private String name;
    private int duration;
    

    public Song() 
    {
        this.name = "";
        this.duration = 0;
    }
    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
    public static boolean uniqueName(Song s)
    {
        ArrayList<Song> sList = Song.readSongFromFile();
        for(int i=0;i<sList.size();i++)
        {
            if(sList.get(i)!=null)
                if(sList.get(i).getName().equalsIgnoreCase(s.getName()))
                    return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Song [name=" + name + ", duration=" + duration + "]";
    }

    public static boolean addSongToFile(Song s) {
        try {
            if(Song.uniqueName(s)) 
            {
                File f = new File("Song.ser");
                ObjectOutputStream oos;
                
                if (f.exists()) oos = new MyObjectOutputStream(new FileOutputStream(f, true));
                else  oos = new ObjectOutputStream(new FileOutputStream(f));
                
        
                oos.writeObject(s);
                oos.close();
                return true;
            }
        } 
        catch (IOException e) {}
        return false;
    }
    public static ArrayList<Song> readSongFromFile() {
        ArrayList<Song> list = new ArrayList<Song>();
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Song.ser"))) {
                while (true) {
                    Song a1 = (Song) ois.readObject();
                    list.add(a1);
                }
            }
        } 
        catch (ClassNotFoundException e){}
        catch (EOFException e){}
        catch (IOException e){}
        return list;
    }

    public static void deleteSongFromFile(String songName)
    {
        ArrayList<Song> list = readSongFromFile();
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).getName().equalsIgnoreCase(songName))
            {
                list.remove(i);
            }
        }
        try
        {
            File f = new File("Song.ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for(int i=0; i<list.size();i++)
            {
                oos.writeObject(list.get(i));
            }
            oos.close();
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
    }
    public static Song searchSongFromFile(String n)
    {
        ArrayList<Song> list = Song.readSongFromFile();
        
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i)!=null && list.get(i).getName().trim().equalsIgnoreCase(n.trim()))
                return list.get(i);
                
        }
        return null;
    }



    public static void main(String[] args) {
        // Song.addSongToFile(new Song("abc",280));

        // Song.deleteSongFromFile("abc");

        // ArrayList<Song> songList = Song.readSongFromFile();

        // for(int i = 0; i<songList.size();i++)
        //     System.out.println(songList.get(i).getName());
    }
}