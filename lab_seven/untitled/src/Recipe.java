// John Paul Larkin
// C00001754
// OOP - Lab seven - 20/1/25

public class Recipe {
    // Private global variables
    private String name;
    private boolean dairy;
    private boolean meat;
    private boolean gluten;

    // Constructor 
    public Recipe(String name, boolean dairy, boolean meat, boolean gluten) {
        this.name = name;
        this.dairy = dairy;
        this.meat = meat;
        this.gluten = gluten;
    }

    // Getter for 'name'
    public String getName() {
        return name;
    }

    // Setter for 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Getter for 'dairy'
    public boolean hasDairy() {
        return dairy;
    }

    // Setter for 'dairy'
    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    // Getter for 'meat'
    public boolean hasMeat() {
        return meat;
    }

    // Getter for 'gluten'
    public boolean hasGluten() {
        return gluten;
    }
}
