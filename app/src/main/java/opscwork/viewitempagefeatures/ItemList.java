package opscwork.viewitempagefeatures;

import static com.example.test.Dashboard_Activity.catList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Item_Information;
import ST10119385.ChloeMoodley.item_list_all;

public class ItemList extends ArrayAdapter<Item_Information> {

    // Declaration of variables (The IIE, 2022)
    private static final String TAG = "ItemList";
    private Context mContext;
    int mitem_list_template;
    public static ArrayList<Item_Information> ItemArrayList = new ArrayList<>();



    //constructor for itemList (The IIE, 2022)
    public ItemList(ItemPage context, int item_list_template, ArrayList<Item_Information> itemArrayList) {
        super(context, item_list_template,itemArrayList);
        this.mContext = context;
        mitem_list_template = item_list_template;
    }

    //constructor for itemAllList (The IIE, 2022)
    public ItemList(item_list_all context, int item_list_template, ArrayList<Item_Information> itemArrayList) {
        super(context, item_list_template,itemArrayList);
        this.mContext = context;
        mitem_list_template = item_list_template;
        itemArrayList = itemArrayList;
    }



    // Override method (The IIE, 2022)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Creation of item information object (The IIE, 2022)
        Item_Information obj = getItem(position);
        //Creation of inflater (The IIE, 2022)
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //inflates item_list_template (The IIE, 2022)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_template, parent, false);
        }
       // convertView = inflater.inflate(mitem_list_template,parent,false);


        TextView txtItemName = (TextView) convertView.findViewById(R.id.itemNameitemAll);
        TextView txtItemCat = (TextView) convertView.findViewById(R.id.catNameitemAll);
        ImageView imgItem = (ImageView) convertView.findViewById(R.id.ItemTemplate_Img);
        ProgressBar itemProgressBar = (ProgressBar) convertView.findViewById(R.id.itemProgressGraph);



        //Get quantity of the specific item
        int quantity = obj.getQty();
        //Get desired quantity of the specific item
        int desiredQuantity = obj.getDesired_Qty();
        int progress = (quantity/desiredQuantity)*100;

        itemProgressBar.setMax(desiredQuantity);
        itemProgressBar.setProgress(quantity);

        // The code below sets the item name and item category (The IIE, 2022)
        txtItemName.setText(obj.getItem_Name());
        txtItemCat.setText(obj.getItem_Description());

        if (obj.getItem_img() != null){
            //Category images are set using Picasso library (Picasso, 2022)
            Picasso.get().load(obj.getItem_img()).resize(150,150).centerCrop().into(imgItem);}



        // The code below returns the convert view (The IIE, 2022)
        return convertView;
    }
}












