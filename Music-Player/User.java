import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.io.*;

public class User extends Person implements Serializable{
    private String email;
    private ArrayList<PlayList> personlLists = new ArrayList<PlayList>();

    public User()
    {
        super();
        personlLists = null;
        email = "";
    }
    public User(String name, int id, String password, String email, ArrayList<PlayList> list)
    {
        super(name, id,password);
        this.email = email;

        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)!=null)
            {
                personlLists.set(i, list.get(i));
            }
        }
        
    }
    public User(String name, String email, int id, String password)
    {
        super(name, id,password);
        this.email = email;

    }
    //Copy Constructor 
    public User(User u)
    {
        super(u);
        this.email = u.getEmail();

        ArrayList<PlayList> list = u.getPersonlLists();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)!=null)
            {
                this.personlLists.add(i, list.get(i));
            }
        }
        
    }



    public void setPersonlLists(ArrayList<PlayList> lists)
    {
        this.personlLists = lists;
    }
    public ArrayList<PlayList> getPersonlLists() {
        return personlLists;
    }

    public boolean uniquePlayListName(String name)
    {
        ArrayList<PlayList> tempList = this.getPersonlLists();
        for(int i = 0; i<tempList.size();i++)
            if(tempList.get(i)!=null)
                if(tempList.get(i).getName().equalsIgnoreCase(name))
                    return false;
        return true;
    }


    public boolean CreatePlayList(String name)
    {
        if(uniquePlayListName(name))
        {
            PlayList newPlayList = new PlayList(name);
            personlLists.add(newPlayList);
            deleteUserFromFile(super.getId());
            addUserToFile(this);
            return true;
        }
        return false;
    }
    public boolean removePlayList(String name)
    {
        for(int i=0;i<this.getPersonlLists().size();i++)
        {
            if(this.getPersonlLists().get(i).getName().equalsIgnoreCase(name))
            {
                this.getPersonlLists().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean uniqueId()
    {
        ArrayList<User> userList = User.readUserFromFile();
        
        for(int i=0;i<userList.size();i++)
        {
            if(userList.get(i).getId()==super.getId())
                return false;
        }
        return true;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    @Override
    public String toString() {
        return super.toString()+", "+email;
    }

    public boolean addSong(Song s,PlayList sL)
    {
        int i=0;
        while(personlLists.get(i)!=null && i<personlLists.size())
        {
            if(sL.getName().equalsIgnoreCase(personlLists.get(i).getName()))
            {
                for(int j = 0; j<personlLists.get(i++).getSongsList().size();j++)
                {
                    if(personlLists.get(i).getSongsList().get(j).getName().equalsIgnoreCase(s.getName()))
                    return false;
                }
                personlLists.get(i++).getSongsList().add(s);
                return true;
            }
            i++;
        }
        return false;        
    }
    public static boolean addUserToFile(User u) {
        
        try {
                if (u.uniqueId()) 
                {
                    File f = new File("User.ser");
                    ObjectOutputStream oos;
                    
                    if (f.exists()) {
                        oos = new MyObjectOutputStream(new FileOutputStream(f, true));
                    } else {
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                    }
            
                    oos.writeObject(u);
                    oos.close();
                    return true;
                }
                
            } 
            catch (IOException e) 
            {
                System.out.println("Error in writing file: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
    }

    

    public static ArrayList<User> readUserFromFile() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("User.ser"))) {
                while (true) {
                    User a1 = (User) ois.readObject();
                    list.add(a1);
                    // System.out.println(a1.toString());
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

    public static boolean deleteUserFromFile(int x)
    {
        boolean flag = false;
        ArrayList<User> list = readUserFromFile();
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).getId()==x)
            {
                list.remove(i);
                flag = true;
            }
        }
        try
        {
            File f = new File("User.ser");
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
        return flag;
    }

    public static User searchUserFromFile(int id)
    {
        ArrayList<User> list = User.readUserFromFile();
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i).getId()==id)
                return list.get(i);
                
        }
        return null;
    }
    public static User searchUserFromFile(String n)
    {
        ArrayList<User> list = User.readUserFromFile();
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i).getName().equalsIgnoreCase(n))
                return list.get(i);
                
        }
        return null;
    }


    public static void main(String[] args) {
        // User u1 = new User("Robert", "@gmail.com", 7777, "qwrty");
        //System.out.println(u1.getId());
        
        // User.addUserToFile(u1);

        // ArrayList<User> list1 = User.readUserFromFile();
        
        // for(int i=0; i<list1.size();i++)
        //     System.out.println(list1.get(i).getId());

        
    }
    
    
    

}
