package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.CustomAdapter;

/**
 * Created by Ravi on 29/07/15.
 */
public class Sweets extends Fragment {


    GridView gv;
    Context context;
    ArrayList prgmName;
    public static String [] prgmNameList={"All Catagories","Halwa Recipe","Ladoo Recipe","Burfi Recipe","Kheer Recipe","Peda Recipe","Chikki Recipe","Bengali Chhena Recipe","Traditional Sweet"};
    public static int [] prgmImages={R.drawable.t,R.drawable.i,R.drawable.m,R.drawable.t,R.drawable.u,R.drawable.s,R.drawable.i,R.drawable.t,R.drawable.u};

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
        View rootView = inflater.inflate(R.layout.fragment_sweets, container, false);
        gv=(GridView)rootView.findViewById(R.id.grid_sweet);
        gv.setAdapter(new CustomAdapter((MainActivity) getActivity(), prgmNameList,prgmImages));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(),HalwaRecipe .class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 5) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 6) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 7) {
                    Intent myIntent = new Intent(view.getContext(), HalwaRecipe.class);
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
