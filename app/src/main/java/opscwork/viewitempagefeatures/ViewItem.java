package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

import ST10119385.ChloeMoodley.ShoppingList_Page;

public class ViewItem extends AppCompatActivity {

    private static final String TAG ="ViewItem";
    TextView ItemNameShoppingList;
    TextView CategoryNameShoppingList;
    ImageView ItemImageShoppingList;
    TextView ItemPriceShoppingList;
    TextView ItemQuantityShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);
    }

    public void GoToShoppingList (View v) {
        Intent i = new Intent(this, ShoppingList_Page.class);

//        Log.d(TAG, "onCreate : Started.");
//        ListView mList = (ListView) findViewById(R.id.shoppingListView);
//
//        ItemNameShoppingList = (TextView) findViewById(R.id.ItemViewName);
//        CategoryNameShoppingList = (TextView) findViewById(R.id.VegetablesTextView);
//        ItemImageShoppingList = (ImageView) findViewById(R.id.LettuceImage);
//        ItemPriceShoppingList = (TextView) findViewById(R.id.Price);
//        ItemQuantityShoppingList = (TextView) findViewById(R.id.NumOfItems);
//
//        //object made
//        Item_Information slo = new Item_Information(ItemNameShoppingList.toString(),
//                CategoryNameShoppingList.toString(),ItemImageShoppingList.toString(),
//                double.class.cast(ItemPriceShoppingList), int.class.cast(ItemQuantityShoppingList));
//
//        //add to list
//        ArrayList<Item_Information> ShoppingListArrayList = new ArrayList<>();
//
//        ShoppingListArrayList.add((slo));
//
//        ShoppingList_Adapter adp = new ShoppingList_Adapter(this, R.layout.shopping_list_template, ShoppingListArrayList);
//        mList.setAdapter(adp);

        startActivity(i);
    }
}