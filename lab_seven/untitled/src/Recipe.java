public class Recipe {
    // Private global variables
    private String name;
    private boolean dairy;
    private boolean meat;
    private boolean gluten;

    // Constructor with 4 parameters
    public Recipe(String name, boolean dairy, boolean meat, boolean gluten) {
        this.name = name;
        this.dairy = dairy;
        this.meat = meat;
        this.gluten = gluten;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter for 'dairy'
    public boolean hasDairy() {
        return dairy;
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
