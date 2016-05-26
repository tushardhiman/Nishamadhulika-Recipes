package info.androidhive.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.activity.MainActivity;

/**
 * Created by Dell on 5/19/2016.
 */
public class Custom_Latestlist_Adapter extends ArrayAdapter<String> {

private final Activity context;
private final String[] web;
private final int[] imageId;
public Custom_Latestlist_Adapter(Activity context,
                                 String[] web, int[] imageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

        }
@Override
public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
        }
        }