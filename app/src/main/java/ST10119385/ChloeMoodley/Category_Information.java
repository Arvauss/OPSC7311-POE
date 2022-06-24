package ST10119385.ChloeMoodley;

import android.graphics.Bitmap;

import com.example.test.R;
import com.google.firebase.database.Exclude;

public class Category_Information {

    // Declaration of Variables (The IIE, 2022)
    private int category_Colour;
    private String category_Name;
    private String category_Description;
    private int category_Icon;
    private Bitmap cat_Image = null;
   // private String UserID;
    private String CatID;

    @Exclude
    public Bitmap getCat_Image() {
        return cat_Image;
    }

    public void setCat_Image(Bitmap cat_Image) {
        this.cat_Image = cat_Image;
    }

    //default constructor
    public Category_Information(){};

    public Category_Information(String catid ,int category_Colour, String category_Name, String category_Description) {
        this.CatID = catid;
        this.category_Colour = category_Colour;
        this.category_Name = category_Name;
        this.category_Description = category_Description;
        this.category_Icon = R.drawable.bodega_image;
    }
    public Category_Information(String catid, int category_Colour, String category_Name, String category_Description, Bitmap img) {
        this.CatID = catid;
        this.category_Colour = category_Colour;
        this.category_Name = category_Name;
        this.category_Description = category_Description;
        this.cat_Image = img;
    }

    /*public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }*/

    public String getCatID() {
        return CatID;
    }

    public void setCatID(String catID) {
        CatID = catID;
    }

    public int getCategory_Colour() {
        return category_Colour;
    }

    public void setCategory_Colour(int category_Colour) {
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
