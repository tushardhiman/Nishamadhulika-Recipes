package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.CustomList;


/**
 * Created by Dell on 5/17/2016.
 */
public class HalwaRecipe extends Activity {

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
        setContentView(R.layout.halwa_recipe);

        CustomList adapter = new
                CustomList(HalwaRecipe.this, web, imageId);
        list=(ListView)findViewById(R.id.list_halwa_recipe);
        list.setAdapter(adapter);
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
