package ST10119385.ChloeMoodley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.test.R;

import java.util.ArrayList;

public class ShoppingList_Adapter extends ArrayAdapter<Item_Information> {

    private static final String TAG = "ShoppingList_Adapter";
    private Context con;
    int re;

    public ShoppingList_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Item_Information> objects, Context con, int re) {
        super(context, resource, objects);
        this.con = con;
        this.re = re;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //getting shoppingList information
        String ItemName = getItem(position).getItem_Name();
        int ItemImage = getItem(position).getItem_image();
        int ItemQty = getItem(position).getQty();

        //create object
        Item_Information item = new Item_Information(ItemName, ItemImage, ItemQty);

        LayoutInflater inflate = LayoutInflater.from(con);
        convertView = inflate.inflate(re, parent, false);

        TextView nameItem = (TextView) convertView.findViewWithTag(R.id.ItemViewName);
        TextView imageItem = (TextView) convertView.findViewWithTag(R.id.LettuceImage);
        TextView qtyItem = (TextView) convertView.findViewWithTag(R.id.itemcount);

        nameItem.setText(ItemName);
        imageItem.setText(ItemImage);
        qtyItem.setText(ItemQty);

        return convertView;
    }
}