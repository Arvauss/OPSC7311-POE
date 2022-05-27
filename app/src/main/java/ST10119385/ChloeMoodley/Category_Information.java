package ST10119385.ChloeMoodley;

public class Category_Information {

    private String category_Colour;
    private String category_Name;
    private String category_Description;
    private int category_Icon;

    public Category_Information(String category_Colour, String category_Name, String category_Description, int category_Icon) {
        this.category_Colour = category_Colour;
        this.category_Name = category_Name;
        this.category_Description = category_Description;
        this.category_Icon = category_Icon;
    }

    public String getCategory_Colour() {
        return category_Colour;
    }

    public void setCategory_Colour(String category_Colour) {
        this.category_Colour = category_Colour;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public String getCategory_Description() {
        return category_Description;
    }

    public void setCategory_Description(String category_Description) {
        this.category_Description = category_Description;
    }

    public int getCategory_Icon() {
        return category_Icon;
    }

    public void setCategory_Icon(int category_Icon) {
        this.category_Icon = category_Icon;
    }
}
