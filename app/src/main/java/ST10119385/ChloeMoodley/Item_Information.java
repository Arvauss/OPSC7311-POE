package ST10119385.ChloeMoodley;

public class Item_Information
{
    private String item_Name;               //name variable declared (The IIE, 2022).
    private String item_Description;        //description variable declared (The IIE, 2022).
    private int item_image;                 //image variable declared (The IIE, 2022).
    private String item_date;               //date variable declared (The IIE, 2022).
    private double item_Price;              //price variable declared (The IIE, 2022).

    //constructor for item_model class (Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns, 2017).
    public Item_Information(String item_Name, String item_Description, int item_image, String item_date, double item_Price) {
        this.item_Name = item_Name;
        this.item_Description = item_Description;
        this.item_image = item_image;
        this.item_date = item_date;
        this.item_Price = item_Price;
    }

    /* below are the getter and setter methods for all the variables in the item model class
     (Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns, 2017). */

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getItem_Description() {
        return item_Description;
    }

    public void setItem_Description(String item_Description) {
        this.item_Description = item_Description;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public double getItem_Price() {
        return item_Price;
    }

    public void setItem_Price(double item_Price) {
        this.item_Price = item_Price;
    }

}
