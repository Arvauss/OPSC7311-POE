package ST10119385.ChloeMoodley;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class Item_list_template_class extends AppCompatActivity {

    private ProgressBar itemProgressBar;

    private int progressStatus = 0;

    private Handler mHandler = new Handler();
    //Declaration of object for the item class
    Item_Information item = new Item_Information();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_template);
        //Code for progress bars
        //Connecting the declared progress bar to the progressbar in
        itemProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get quantity of the specific item
        int quantity = item.getQty();
        //Get desired quantity of the specific item
        int desiredQuantity = item.getDesired_Qty();
        int progress = (quantity/desiredQuantity)*100;

        new Thread(new Runnable() {
            @Override
            public void run(){
                while (progressStatus < 100){
                    progressStatus = progress;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            itemProgressBar.setProgress((progressStatus));
                        }
                    });
                }
            }
        }).start();

    }

}
