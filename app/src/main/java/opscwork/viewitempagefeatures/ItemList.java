package opscwork.viewitempagefeatures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;

import org.w3c.dom.Text;

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

    // Override method ()
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ItemName = getItem(position).getItem_Name();
        String ItemCat = getItem(position).getCategory();
        int ItemImg = getItem(position).getItem_image();

        // Creation of item information object ()
        Item_Information obj = new Item_Information(ItemName, ItemImg);
        //Creation of inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //inflates item_list_template
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_template, parent, false);
        }
       // convertView = inflater.inflate(mitem_list_template,parent,false);


        TextView txtItemName = (TextView) convertView.findViewById(R.id.itemNameitemAll);
        TextView txtItemCat = (TextView) convertView.findViewById(R.id.catNameitemAll);
        ImageView imgItem = (ImageView) convertView.findViewById(R.id.ItemTemplate_Img);

        txtItemName.setText(ItemName);
        txtItemCat.setText(ItemCat);
        imgItem.setImageResource(R.drawable.bodega_image);

        return convertView;
    }
}












