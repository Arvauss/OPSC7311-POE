package ST10119385.ChloeMoodley;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

import com.google.firebase.database.Exclude;

public class Item_Information
{
    private String item_ID;
    private String item_Name;               //name variable declared (The IIE, 2022).
    private String item_Description;    //description variable declared (The IIE, 2022).
    private int item_icon;
    //image variable declared (The IIE, 2022).
    private String item_date;               //date variable declared (The IIE, 2022).
    private double item_Price;              //price variable declared (The IIE, 2022).
    private String Category;                //category variable declared (The IIE, 2022).
    private String cat_ID;
    private String item_img;


    private int Qty;                        //Qty variable declared (The IIE, 2022).
    private int Desired_Qty;                //Desired_Qty variable declared (The IIE, 2022).


    @Exclude
    private ProgressBar ItemBar;            //ItemBar variable declared (The IIE, 2022).

    //empty constructor
    public Item_Information(){}

    //constructor for item_model class (Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns, 2017).

    //Constructor for New Item Add (The IIE, 2022)
    public Item_Information(String id, String item_Name, String item_desc, String item_date, double item_Price, String Category, String catid ,int Qty, String img) {
        this.item_ID = id;
        this.item_Name = item_Name;
        this.item_Price = item_Price;
        this.Category = Category;
        this.cat_ID = catid;
        this.Qty = Qty;
        this.item_Description = item_desc;
        this.item_date = item_date;
        this.Desired_Qty = 2;
        this.item_img = img;
    }


/* below are the getter and setter methods for all the variables in the item model class
     (Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns, 2017). */

    public String getCat_ID() {
        return cat_ID;
    }

    public void setCat_ID(String cat_ID) {
        this.cat_ID = cat_ID;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

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

    public int getItem_icon() {
        return item_icon;
    }

    public void setItem_icon(int item_image) {
        this.item_icon = item_image;
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

    public String getCategory() { return Category; }

    public void setCategory(String category) { Category = category; }

    public int getQty() { return Qty; }

    public void setQty(int qty) { Qty = qty; }

    public int getDesired_Qty() { return Desired_Qty; }

    public void setDesired_Qty(int desired_Qty) { Desired_Qty = desired_Qty; }

    // The method below increases the item quantity (The IIE, 2022)
    public int IncreaseQty(){
        Qty++;
        return Qty;
    }

    // The method below decrease the item quantity (The IIE, 2022)
    public int DecreaseQty(){
        Qty--;
        return Qty;
    }

    // The method below increases the desired quantity (The IIE, 2022)
    public int IncreaseDesiredQty(){
        Desired_Qty++;
        return Desired_Qty;
    }

    // The method below decrease the desired quantity (The IIE, 2022)
    public int DecreaseDesiredQty(){
        Desired_Qty--;
        return Desired_Qty;
    }

}
