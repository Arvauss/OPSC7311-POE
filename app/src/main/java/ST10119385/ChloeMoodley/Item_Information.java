package ST10119385.ChloeMoodley;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

public class Item_Information
{
    private String item_Name;               //name variable declared (The IIE, 2022).
    private String item_Description;        //description variable declared (The IIE, 2022).
    private int item_image;                 //image variable declared (The IIE, 2022).
    private Bitmap Item_bitmap = null;
    private String item_date;               //date variable declared (The IIE, 2022).
    private double item_Price;              //price variable declared (The IIE, 2022).
    private String Category;                //category variable declared (The IIE, 2022).
    private int Qty;                        //Qty variable declared (The IIE, 2022).
    private int Desired_Qty;                //Desired_Qty variable declared (The IIE, 2022).



    private ProgressBar ItemBar;            //ItemBar variable declared (The IIE, 2022).

    //constructor for item_model class (Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns, 2017).
    //Constructor for ItemList adapter
    public Item_Information(String item_Name, int item_img) {
        this.item_Name = item_Name;
        this.item_image = item_img;
    }
    //Shopping list constructor
    public Item_Information(String item_Name, int item_img, double item_Price, int qty, String category) {
        this.item_Name = item_Name;
        this.item_image = item_img;
        this.item_date = item_date;
        this.item_Price = item_Price;
        this.Qty = qty;
        this.Category = category;
    }
    //Constructor for ItemArrayList population
    public Item_Information(String item_Name, String item_desc, int img, String item_date, double item_Price, String Category, int Qty) {
        this.item_Name = item_Name;
        this.item_Price = item_Price;
        this.item_image = img;
        this.Category = Category;
        this.Qty = Qty;
        this.item_Description = item_desc;
        this.item_date = item_date;
        this.Desired_Qty = 1;
    }
    //Constructor for New Item Add
    public Item_Information(String item_Name, String item_desc, String item_date, double item_Price, String Category, int Qty, Bitmap bmp) {
        this.item_Name = item_Name;
        this.item_Price = item_Price;
        this.Category = Category;
        this.Qty = Qty;
        this.item_Description = item_desc;
        this.item_date = item_date;
        this.Desired_Qty = 1;
        this.Item_bitmap = bmp;
    }

    //Constructor for ItemListAll Add
    public Item_Information(String item_Name, String item_desc, int item_image, String item_date, double item_Price, int Qty) {
        this.item_Name = item_Name;
        this.item_image = item_image;
        this.item_Price = item_Price;
        this.Qty = Qty;
        this.item_Description = item_desc;
        this.item_date = item_date;
    }


    public Item_Information(int item_image,String item_Name) {
        this.item_image = item_image;
        this.item_Name = item_Name;
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

    public Bitmap getItem_bitmap() {
        return Item_bitmap;
    }

    public void setItem_bitmap(Bitmap item_bitmap) {
        Item_bitmap = item_bitmap;
    }

}
