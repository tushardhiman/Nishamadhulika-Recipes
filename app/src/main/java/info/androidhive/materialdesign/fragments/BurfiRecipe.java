package info.androidhive.materialdesign.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.androidhive.materialdesign.Bean.ReceipSub_data;
import info.androidhive.materialdesign.Bean.RecipeSubCatListResults;
import info.androidhive.materialdesign.Holder.Holder;
import info.androidhive.materialdesign.HttpUrlConnection.HttpULRConnect;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.CustomAdapter2;
import info.androidhive.materialdesign.adapter.ReceipeAdapter;

/**
 * Created by Dell on 5/27/2016.
 */
public class BurfiRecipe extends Fragment {
    RecipeSubCatListResults rs;
    ReceipSub_data mID;
    ProgressDialog pDialog;
    ArrayList<ReceipSub_data> arraylist_results;
    Boolean insidecancel = false;
    ProgressDialog ShowProgress;

    ReceipeAdapter mReceipeAdapter;
    ListView list;
    Context context;
    ArrayList prgmName;
    int page_no = 1, total_pages = 0;

    CustomAdapter2 adapter;
    public BurfiRecipe() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.latest_1, container, false);

        new loadingTask().execute();
        return rootView;
    }

    private class loadingTask extends AsyncTask<String, String, String> {

        String ID, Title, Description, ThumbURL;
        private String jsonStr = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading .........");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.e("Url is as it is :", Holder.Sub_cat_Ladoo);
            String content = HttpULRConnect.getData(Holder.Sub_cat_Ladoo);
            Log.e("url is :  ---->", Holder.Sub_cat_Ladoo);
            Log.e("Halwa  data Response :  ---->", String.valueOf(content));

            if (String.valueOf(content) == null) {
                Toast.makeText(getActivity(), "Connection is Breaking ", Toast.LENGTH_SHORT).show();
            }
            System.out.println("Response is :"+content);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            // TODO Auto-generated method stub
            pDialog.dismiss();
            ArrayList<ReceipSub_data> arraylist_results = new ArrayList<ReceipSub_data>();
            try {

                Log.e("Album Post Method Call  here ....", "Method ...");
                JSONArray ar = new JSONArray(s);
                arraylist_results = new ArrayList<ReceipSub_data>();
                System.out.println("Array response is ---" + ar);
                Log.e("Album Array response ids---", String.valueOf(ar));
                for (int i = 0; i < ar.length(); i++) {
                    Log.e("Album Array  is ---", String.valueOf(ar.length()));

                    JSONObject jsonobject = ar.getJSONObject(i);

                    ReceipSub_data receipSub_data = new ReceipSub_data();

                    ID = jsonobject.getString("ID");

                    //ReceipSub_data.SetID(ID);
//                    String.valueOf(ar).indexOf(i);
                    Log.e("ALBUM ID Is =======================>>>>>", ID);
                    //  Log.e("Array id  Index is ---->>", String.valueOf(String.valueOf(ar).indexOf(i)));


                    ThumbURL = jsonobject.getString("ThumbURL");
                    //  ReceipSub_data.SetThumbURL(ThumbURL);
                    Log.e(" AlbumCover As it Is Here=======================>>>>>", ThumbURL);


                    Title = jsonobject.getString("Title");
                    receipSub_data.SetTitle(Title);
                    Log.e("Recipe name As it Is Here=======================>>>>>", Title);
//                    AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);
                    //    Log.e("Adapter set  here --(*(*(*(*(*(*(*(*(--", String.valueOf(adapter));
                    arraylist_results.add(receipSub_data);
                    Log.e("song_Array_list set  here --NJNIJNIJMNIMNIMNIKMN--", String.valueOf(arraylist_results));
                }
            }

            catch (JSONException e)

            {
                e.printStackTrace();
            }
            Log.e("Album [][][][[][][][][][[]Array list is ----- >>>", String.valueOf(arraylist_results));

            //   list.setAdapter(adapter = new TusharAdapter(context, R.layout.latest_list_item_1, mListIDs));
            //       adapter = new CustomAdapter2(context,  R.layout.latest_list_item_1, arraylist_results);


            //  list.setAdapter(adapter = new CustomAdapter2(context, R.layout.latest_list_item_1, arraylist_results));
            list.setAdapter(adapter);
            Log.e("Recipe Adapter Set here ----->>>", "Adapter set ");
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Log.e("List Item Click -----tushar", "Clicked ");

                }
            });
        }
    }
}

