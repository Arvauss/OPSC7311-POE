package ST10119385.ChloeMoodley;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.test.R;
import com.google.firebase.database.Exclude;

public class Category_Information {

    // Declaration of Variables (The IIE, 2022)
    private int category_Colour;
    private String category_Name;
    private String category_Description;
    private int category_Icon;
    private String cat_Image;
    private String UID;
    private String CatID;


    public String getCat_Image() {
        return cat_Image;
    }

    public void setCat_Image(String cat_Image) {
        this.cat_Image = cat_Image;
    }

    //default constructor
    public Category_Information(){};


    public Category_Information(String catid, int category_Colour, String category_Name, String category_Description, String img, String uid) {
        this.CatID = catid;
        this.category_Colour = category_Colour;
        this.category_Name = category_Name;
        this.category_Description = category_Description;
        this.cat_Image = img;
        this.UID = uid;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

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
