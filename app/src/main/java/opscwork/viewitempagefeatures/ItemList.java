package opscwork.viewitempagefeatures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Item_Information;

public class ItemList extends ArrayAdapter<Item_Information> {

    // Declaration of variables
    private static final String TAG = "ItemList";
    private Context mContext;
    int mitem_list_template;

    public ItemList(ItemPage context, int item_list_template, ArrayList<Item_Information> itemArrayList) {
        super(context, item_list_template,itemArrayList);
        this.mContext = context;
        mitem_list_template = item_list_template;
    }

    // Overide method ()
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ItemName = getItem(position).getItem_Name();
        int ItemImage = getItem(position).getItem_image();

        // Creation of item information object ()
        Item_Information obj = new Item_Information(ItemImage, ItemName);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mitem_list_template,parent,false);

        EditText EdItemName = (EditText) convertView.findViewById(R.id.ItemNameBox);
        ImageView EdItemImage = (ImageView) convertView.findViewById(R.id.ImageItemPic);

        EdItemName.setText(ItemName);
        EdItemImage.setImageResource(ItemImage);

        return convertView;
    }
}












