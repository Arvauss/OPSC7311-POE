package ST10119385.ChloeMoodley;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //getting shoppingList information
        String ItemName = getItem(position).getItem_Name();
        String CatName = getItem(position).getCategory();
        int ItemImage = getItem(position).getItem_image();
        double ItemPrice = getItem(position).getItem_Price();
        int ItemQty = getItem(position).getQty();

        //create object
        Item_Information item = new Item_Information(ItemName.toString(), int.class.cast(ItemImage), double.class.cast(ItemPrice), CatName.toString(), int.class.cast(ItemQty));

        LayoutInflater inflater = LayoutInflater.from(con);
        convertView = inflater.inflate(re, parent, false);

        TextView nameItem = (TextView) convertView.findViewById(R.id.itemnameshoppinglisttemplate);
        ImageView imageItem = (ImageView) convertView.findViewById(R.id.imageshoppinglisttemplate);
        TextView nameCat = (TextView) convertView.findViewById(R.id.catnameshoppinglisttemelate);
        TextView qtyItem = (TextView) convertView.findViewById(R.id.qtyshoppinglisttemeplate);
        TextView priceItem = (TextView) convertView.findViewById(R.id.priceshoppinglisttemeplate);

        nameItem.setText(ItemName);
        imageItem.setImageResource(ItemImage);
        priceItem.setText(double.class.cast(ItemPrice).toString());
        nameCat.setText(CatName);
        qtyItem.setText(int.class.cast(ItemQty).toString());


        return convertView;
    }
}
