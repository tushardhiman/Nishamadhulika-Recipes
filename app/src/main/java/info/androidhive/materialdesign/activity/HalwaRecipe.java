package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.materialdesign.R;

import info.androidhive.materialdesign.adapter.TusharAdapter;


/**
 * Created by Dell on 5/17/2016.
 */
public class HalwaRecipe extends Activity {



    public static String [] prgmNameList={"Halwa Recipe","Ladoo Recipe","Burfi Recipe","Kheer Recipe in Hindi","Peda Recipe","Chikki Recipe","Bengali Chhena Recipe","Traditional Sweets"};
    public static int [] prgmImages={R.drawable.t,R.drawable.i,R.drawable.m,R.drawable.t,R.drawable.u,R.drawable.s,R.drawable.i,R.drawable.t};
    Context context;
    ListView list;
    String[] web = {
            "Raw Banana Halwa - Kele ka halwa",
            "Watermelon Rind Halwa Recipe",
            "Strawberry Halwa Recipe" ,
            "Millet Flour Halwa - Bajra nu Halwa",
            "Shahi Tukra Recipe - How To Make Shahi Tukda",
            "Khas Khas ka Halwa recipe in Hindi",
            "Besan Halwa Recipe"


    } ;
    Integer[] imageId =
            {
            R.drawable.i,
            R.drawable.m,
            R.drawable.t,
            R.drawable.u,
            R.drawable.i,
            R.drawable.m,
            R.drawable.u
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halwa_rec_layout);

        context=this;

        list=(ListView)findViewById(R.id.latestrecipeslist);
        list.setAdapter(new TusharAdapter(this, prgmNameList,prgmImages));



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(HalwaRecipe.this,Sub_cat_Discription.class);
                startActivity(i);
                Toast.makeText(HalwaRecipe.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}
