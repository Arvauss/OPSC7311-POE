package opscwork.viewitempagefeatures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Item_Information;
import ST10119385.ChloeMoodley.item_list_all;

public class ItemList extends ArrayAdapter<Item_Information> {

    // Declaration of variables (The IIE, 2022)
    private static final String TAG = "ItemList";
    private Context mContext;
    int mitem_list_template;
    public static ArrayList<Item_Information> ItemArrayList = new ArrayList<>();

    // Creation of Constructor
//    public ItemList(@NonNull Context context, int resource, @NonNull ArrayList<Item_Information> objects, Context mContext) {
//        super(context, resource, objects);
//        this.mContext = context;
//    }

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
        String ItemName = getItem(position).getItem_Name();
        String ItemCat = getItem(position).getCategory();
        int ItemImg = getItem(position).getItem_image();


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

        // The code below sets the item name and item category (The IIE, 2022)
        txtItemName.setText(obj.getItem_Name());
        txtItemCat.setText(obj.getCategory());

        if (obj.getItem_bitmap() == null){
            imgItem.setImageResource(R.drawable.bodega_image);}
        else{
            imgItem.setImageBitmap(obj.getItem_bitmap());}

        // The code below returns the convert view (The IIE, 2022)
        return convertView;
    }
}












