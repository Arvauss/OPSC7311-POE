package opscwork.viewitempagefeatures;

/*
public class ItemViewAdapter extends ArrayAdapter<Item_Information> {

    Context cont;
    int res;

    public ItemViewAdapter(@NonNull Context context, int LayoutID, @NonNull ArrayList<Item_Information> Att, Context cont) {
        super(context, LayoutID, Att);
        this.cont = context;
        this.res = res;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String ItemName = getItem(position).getItem_Name();
        String item_Description = getItem(position).getItem_Description();
        int ItemImage = getItem(position).getItem_image();
        String item_date = getItem(position).getItem_date();
        Double item_Price = getItem(position).getItem_Price();
        String Category = getItem(position).getCategory();
        int QTY = getItem(position).getQty();
        int Desired_Qty = getItem(position).getDesired_Qty();
        int IncreaseQty = getItem(position).IncreaseQty();
        int DecreaseQty = getItem(position).DecreaseQty();
        int IncreaseDesireQty = getItem(position).IncreaseDesiredQty();
        int DecreasedDesireQty = getItem(position).DecreaseDesiredQty();

        LayoutInflater inflate = LayoutInflater.from(cont);
        convertView = inflate.inflate(R.layout.view_item_page, parent, false);


        TextView nameItem = (TextView) convertView.findViewById(R.id.ItemViewName);
        TextView itemDescription = (TextView) convertView.findViewById(R.id.Description);
        ImageView itemImage = (ImageView) convertView.findViewWithTag(R.id.LettuceImage);
        TextView itemDate = (TextView) convertView.findViewById(R.id.DateOfAcquisition);
        TextView ItemPrice = (TextView) convertView.findViewById(R.id.Price);
        TextView category = (TextView) convertView.findViewById(R.id.VegetablesTextView);
        TextView itemCount = (TextView) convertView.findViewById(R.id.NumOfItems);
        ImageButton decrease = (ImageButton) convertView.findViewById(R.id.decrease_item_qty);
        ImageButton increase = (ImageButton) convertView.findViewById(R.id.increase_item_qty);
        ImageButton decreaseD = (ImageButton) convertView.findViewById(R.id.decrease_desired_qty);
        ImageButton increaseD = (ImageButton) convertView.findViewById(R.id.increase_desired_qty);

        nameItem.setText(ItemName);
        itemDescription.setText(item_Description);
        itemImage.setImageResource(ItemImage);
        itemDate.setText(item_date);
        ItemPrice.setText(String.class.cast(item_Price));
        category.setText(Category);
        itemCount.setText(QTY);
        //decrease.setClickable(DecreaseQty);

        return convertView;

    }
}
*/

