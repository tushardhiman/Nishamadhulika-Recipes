package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.CustomAdapter;
import info.androidhive.materialdesign.fragments.Ladoo_Sub_Recipe;

/**
 * Created by Ravi on 29/07/15.
 */
public class Sweets extends Fragment {

String RecipeName;
    ListView gv;
    Context context;
    ArrayList prgmName;
    public static String [] prgmNameList={"Halwa Recipe","Ladoo Recipe","Burfi Recipe","Kheer Recipe in Hindi","Peda Recipe","Chikki Recipe","Bengali Chhena Recipe","Traditional Sweets"};
    public static int [] prgmImages={R.drawable.t,R.drawable.i,R.drawable.m,R.drawable.t,R.drawable.u,R.drawable.s,R.drawable.i,R.drawable.t};

    public Sweets() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.curry_dal_list_layout, container, false);
        gv=(ListView) rootView.findViewById(R.id.latestrecipeslist);
        gv.setAdapter(new CustomAdapter((MainActivity) getActivity(), prgmNameList,prgmImages));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Halwa Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(),Ladoo_Sub_Recipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Ladoo Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Burfi Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Kheer Recipe in Hindi");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Peda Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 5) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Chikki Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 6) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Bengali Chhena Recipe");
                    myIntent.putExtras(b);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 7) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("Key", "Traditional Sweets");
                    myIntent.putExtras(b);

                    startActivityForResult(myIntent, 0);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
