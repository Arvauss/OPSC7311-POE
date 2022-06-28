package ST10119385.ChloeMoodley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingList_Adapter extends ArrayAdapter<Item_Information> {

    private static final String TAG = "ShoppingList_Adapter";
    private Context con;
    int re;

    public ShoppingList_Adapter(ShoppingList_Page context, int shopping_list_page, ArrayList<Item_Information> shoppingListArrayList) {
        super(context, shopping_list_page, shoppingListArrayList);
        con = context;
        re = shopping_list_page;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create object
        Item_Information obj = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(con);
        convertView = inflater.inflate(re, parent, false);

        TextView nameItem = (TextView) convertView.findViewById(R.id.itemnameshoppinglisttemplate);
        ImageView imageItem = (ImageView) convertView.findViewById(R.id.imageshoppinglisttemplate);
        TextView nameCat = (TextView) convertView.findViewById(R.id.catnameshoppinglisttemelate);
        TextView qtyItem = (TextView) convertView.findViewById(R.id.qtyshoppinglisttemeplate);
        TextView priceItem = (TextView) convertView.findViewById(R.id.priceshoppinglisttemeplate);

        nameItem.setText(obj.getItem_Name());
        priceItem.setText("R " + obj.getItem_Price());
        nameCat.setText(obj.getCategory());
        int qtyDiff = obj.getDesired_Qty() - obj.getQty();
        qtyItem.setText("QTY: " + qtyDiff);

        if (obj.getItem_img() != null){
            Picasso.get().load(obj.getItem_img()).resize(150,150).centerCrop().into(imageItem);}


        return convertView;
    }
}
