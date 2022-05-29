package opscwork.viewitempagefeatures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Item_Information;

public class ItemList extends ArrayAdapter<Item_Information> {

    // Declaration of variables
    private static final String TAG = "ItemList";
    private Context mContext;
    int mitem_list_template;

    // Creation of Constructor
//    public ItemList(@NonNull Context context, int resource, @NonNull ArrayList<Item_Information> objects, Context mContext) {
//        super(context, resource, objects);
//        this.mContext = context;
//    }

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
        String ItemDescription = getItem(position).getItem_Description();
        String ItemPurchaseDate = getItem(position).getItem_date();
        double ItemPrice = getItem(position).getItem_Price();

        // Creation of item information object ()
        Item_Information obj = new Item_Information(ItemName, ItemDescription,ItemPurchaseDate,ItemPrice);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mitem_list_template,parent,false);

        EditText EdItemName = (EditText) convertView.findViewById(R.id.ItemNameBox);
        EditText EdItemDescription = (EditText) convertView.findViewById(R.id.ItemDescTextBox);
        TextView EdItemPurchaseDate = (TextView) convertView.findViewById(R.id.DatePicker);
        EditText EdItemPrice = (EditText) convertView.findViewById(R.id.priceTextBox);

        EdItemName.setText(ItemName);
        EdItemDescription.setText(ItemDescription);
        EdItemPurchaseDate.setText(ItemPurchaseDate);
        EdItemPrice.setText(String.class.cast(ItemPrice));

        return convertView;
    }
}












