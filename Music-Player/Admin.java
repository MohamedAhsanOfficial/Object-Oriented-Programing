
import java.util.ArrayList;
import java.io.*;


public class Admin extends Person
{
    

    public Admin()
    {
        super();
    }
    public Admin(String name, int id, String password)
    {
        super(name, id,password);
    }

    public static boolean makeAUserAdmin(int id)
    {
        ArrayList<User> UserList = User.readUserFromFile();

        for(int i=0; i<UserList.size();i++)
        {
            if(UserList.get(i).getId()==id)
            {
                addAdminToFile(new Admin(UserList.get(i).getName(), UserList.get(i).getId(), UserList.get(i).getPassword()));
                User.deleteUserFromFile(id);
                return true;
            }
        }
        return false;
    }
    public boolean uniqueId()
    {
        ArrayList<Admin> adminList = Admin.readAdminFromFile();
        
        for(int i=0;i<adminList.size();i++)
        {
            if(adminList.get(i).getId()==super.getId())
                return false;
        }
        return true;
    }

    public static boolean addAdminToFile(Admin a) 
    {
        boolean flag  = false;
        
            try 
            {
                
                File f = new File("Admin.ser");
                ObjectOutputStream oos;
                
                if (f.exists()) {
                    if(a.uniqueId())
                    {
                        oos = new MyObjectOutputStream(new FileOutputStream(f, true));
                    }
                    else return false;
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }
        
                oos.writeObject(a);
                oos.close();

            }
            catch (IOException e) 
            {
                System.out.println("Error in writing file: " + e.getMessage());
                e.printStackTrace();
            }
        
        return flag;
    }

    public static ArrayList<Admin> readAdminFromFile() {
        ArrayList<Admin> list = new ArrayList<Admin>();
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Admin.ser"))) {
                while (true) 
                {
                    Admin a1 = (Admin) ois.readObject();
                    list.add(a1);
                }
            }
        } 
        catch (ClassNotFoundException e) {} 
        catch (EOFException e) {} 
        catch (IOException e) {}
        return list;

    }

    public static boolean deleteAdminFromFile(int x)
    {
        boolean flag = false;
        ArrayList<Admin> list = readAdminFromFile();
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
            File f = new File("Admin.ser");
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
        return flag;
    }

    public static Admin searchAdminFromFile(String n)
    {
        ArrayList<Admin> list = Admin.readAdminFromFile();
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i).getName().equalsIgnoreCase(n))
                return list.get(i);
                
        }
        return null;
    }
    public static Admin searchAdminFromFile(int id)
    {
        ArrayList<Admin> list = Admin.readAdminFromFile();
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i).getId()==id)
                return list.get(i);
                
        }
        return null;
    }



    public static void main(String[] args) {
        Admin.addAdminToFile(new Admin("admin", 1, "admin"));

        // ArrayList<Admin> adminList = Admin.readAdminFromFile();
        // System.out.println(adminList.get(0).getId());

    }
}
    

