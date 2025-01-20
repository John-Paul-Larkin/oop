
public class Student {
    // Private global variables
    private String name;
    private int id;
    private String module;

    // Constructor with 3 parameters
    public Student(String mName, int mID, String mModule) {
        this.name = mName;
        this.id = mID;
        this.module = mModule;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for 'module'
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
