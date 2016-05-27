package info.androidhive.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.materialdesign.Bean.ReceipSub_data;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.activity.HalwaRec_2;
import info.androidhive.materialdesign.activity.MainActivity;

/**
 * Created by Dell on 5/17/2016.
 */
public class CustomAdapter2 extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter2(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public CustomAdapter2(HalwaRec_2 halwaRec_2, int latest_list_item_1, ArrayList<ReceipSub_data> arraylist_results) {

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_item_layout, null);
        holder.tv=(TextView)rowView.findViewById(R.id.sweet_text);
        holder.img=(ImageView)rowView.findViewById(R.id.image_sweet);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }
}
