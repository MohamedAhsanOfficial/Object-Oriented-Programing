import java.io.Serializable;

public abstract class Person implements Serializable{
    private String name;
    private int id;
    private String password;
    
    public Person() {
        this.name = null;
        
        this.password=null;
    }
    
    public Person(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
    public Person(Person p) {
        this.name = p.getName();
        this.id = p.getId();
        this.password = p.getPassword();
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Person p)
    {
        return (this.name.equalsIgnoreCase(p.getName())
        && this.id == p.getId()
        && this.password.equalsIgnoreCase(p.getPassword()));
    }
    
    public abstract boolean uniqueId();



    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + ", password=" + password + "";
    }


    
    

    
}
