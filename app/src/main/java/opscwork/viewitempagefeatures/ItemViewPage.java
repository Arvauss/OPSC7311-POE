package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

import ST10119385.ChloeMoodley.Item_Information;

public class ItemViewPage extends AppCompatActivity {

    //Attribute used to show the selected item
    Item_Information SelectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);
        getSelectedItem();
        setValues();
    }

    //Method used to set the values
    private void setValues() {
        TextView nameItem = (TextView) findViewById(R.id.ItemViewName);
        TextView itemDescription = (TextView) findViewById(R.id.Description);
        ImageView itemImage = (ImageView) findViewById(R.id.LettuceImage);
        TextView itemDate = (TextView) findViewById(R.id.DateOfAcquisition);
        TextView ItemPrice = (TextView) findViewById(R.id.Price);
        TextView category = (TextView) findViewById(R.id.CategoryNameTextView);
        TextView itemCount = (TextView) findViewById(R.id.NumOfItems);
        ImageButton decrease = (ImageButton) findViewById(R.id.decrease_item_qty);
        ImageButton increase = (ImageButton) findViewById(R.id.increase_item_qty);
        ImageButton decreaseD = (ImageButton) findViewById(R.id.decrease_desired_qty);
        ImageButton increaseD = (ImageButton) findViewById(R.id.increase_desired_qty);

        //getting the information of the selected item
        nameItem.setText(SelectedItem.getItem_Name());
        itemDescription.setText(SelectedItem.getItem_Description());
        itemImage.setImageResource(SelectedItem.getItem_image());
        itemDate.setText(SelectedItem.getItem_date());
        ItemPrice.setText(Double.toString(SelectedItem.getItem_Price()));
        category.setText(SelectedItem.getCategory());
        itemCount.setText(Integer.toString(SelectedItem.getQty()));

        // decrease.setClickable(SelectedItem.DecreaseQty());

    }
    //Method used to show the selected items
    private void getSelectedItem() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        //SelectedItem = ViewItem.itemArrayList.get(Integer.valueOf(parsedStringID));
    }
}

