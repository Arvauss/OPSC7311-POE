package ST10119385.ChloeMoodley;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
public class ShoppingList_Page extends AppCompatActivity {

    private static final String TAG ="ShoppingList_Page";
    TextView ItemNameShoppingList;
    TextView CategoryNameShoppingList;
    ImageView ItemImageShoppingList;
    TextView ItemPriceShoppingList;
    TextView ItemQuantityShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_page);

    }
}