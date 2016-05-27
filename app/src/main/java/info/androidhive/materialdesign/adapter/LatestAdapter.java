package info.androidhive.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.Bean.LatestdishPojo;
import info.androidhive.materialdesign.R;

/**
 * Created by EnemSML2 on 20-05-2016.
 */
public class LatestAdapter extends ArrayAdapter<LatestdishPojo> {
    private ArrayList<LatestdishPojo> latestdishlist;
    private Context context;
    public LatestAdapter(Context context, int resource, List<LatestdishPojo> objects) {
        super(context, resource, objects);
        latestdishlist = (ArrayList<LatestdishPojo>) objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public LatestdishPojo getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(LatestdishPojo item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        super.getView(position, convertView, parent);
        View rowView = convertView;
        ViewHolder viewHolder = new ViewHolder();
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.latest_list_item_1, null);
            // configure view holder

            viewHolder.image = (ImageView) rowView.findViewById(R.id.latestrecipeimage);
            viewHolder.text = (TextView) rowView.findViewById(R.id.latestrecipetitle);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        //fill data
        viewHolder.image.setImageResource(R.drawable.flower);
        //fetch image from url
        viewHolder.text.setText(latestdishlist.get(position).getTitle());

        return rowView;
    }
}
