// John Paul Larkin
// C00001754
// OOP - Lab seven - 20/1/25

public class Student {
    // Private global variables
    private String name;
    private int id;
    private String module;

    // Constructor 
    public Student(String mName, int mID, String mModule) {
        this.name = mName;
        this.id = mID;
        this.module = mModule;
    }

    // Getter for 'name'
    public String getName() {
        return name;
    }

    // Setter for 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Getter for 'id'
    public int getId() {
        return id;
    }

    // Setter for 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter for 'module'  
    public String getModule() {
        return module;
    }

    // Setter for 'module'
    public void setModule(String module) {
        this.module = module;
    }
}
