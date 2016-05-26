package info.androidhive.materialdesign.activity;

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
import info.androidhive.materialdesign.adapter.Custom_Latestlist_Adapter;
import info.androidhive.materialdesign.adapter.ReceipeAdapter;

/**
 * Created by Dell on 5/25/2016.
 */
public class Test extends Fragment {
    RecipeSubCatListResults rs;
    ReceipSub_data mID;
    ProgressDialog pDialog;
    ArrayList<ReceipSub_data> arraylist_results;
    Boolean insidecancel = false;
    ProgressDialog ShowProgress;
    public static ArrayList<ReceipSub_data> mListIDs = new ArrayList<ReceipSub_data>();
    ReceipeAdapter mReceipeAdapter;
    ListView list;
    Context context;
    ArrayList prgmName;
    int page_no = 1, total_pages = 0;

    public Test() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.halwa_recipe, container, false);
        list = (ListView) rootView.findViewById(R.id.list_halwa_recipe);

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
            Log.e("Url is as it is :", Holder.Cat_nishamadulika);
            String content = HttpULRConnect.getData(Holder.Cat_nishamadulika);
            Log.e("url is :  ---->", Holder.Cat_nishamadulika);
            Log.e("Catagory data Response :  ---->", String.valueOf(content));

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
            try {

                Log.e("Album Post Method Call  here ....", "Method ...");
                JSONArray ar = new JSONArray(s);
                arraylist_results = new ArrayList<ReceipSub_data>();
                System.out.println("Array response is ---" + ar);
                Log.e("Album Array response ids---", String.valueOf(ar));
                for (int i = 0; i < ar.length(); i++) {
                    Log.e("Album Array  is ---", String.valueOf(ar.length()));

                    JSONObject jsonobject = ar.getJSONObject(i);

                    ReceipSub_data songListBean = new ReceipSub_data();

                    ID = jsonobject.getString("ID");

                    //ReceipSub_data.SetID(ID);
//                    String.valueOf(ar).indexOf(i);
                    Log.e("ALBUM ID Is =======================>>>>>", ID);
                  //  Log.e("Array id  Index is ---->>", String.valueOf(String.valueOf(ar).indexOf(i)));


                    ThumbURL = jsonobject.getString("ThumbURL");
                  //  ReceipSub_data.SetThumbURL(ThumbURL);
                    Log.e(" AlbumCover As it Is Here=======================>>>>>", ThumbURL);


                    Title = jsonobject.getString("Title");
                    songListBean.SetTitle(Title);
                    Log.e("Recipe name As it Is Here=======================>>>>>", Title);
//                    AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);
                    //    Log.e("Adapter set  here --(*(*(*(*(*(*(*(*(--", String.valueOf(adapter));
                    arraylist_results.add(songListBean);
                    Log.e("song_Array_list set  here --NJNIJNIJMNIMNIMNIKMN--", String.valueOf(arraylist_results));
                }
            }

            catch (JSONException e)

            {
                e.printStackTrace();
            }
            Log.e("Album [][][][[][][][][][[]Array list is ----- >>>", String.valueOf(arraylist_results));

//            Custom_Latestlist_Adapter adapter = new Custom_Latestlist_Adapter(getActivity(), R.layout.halwa_recipe,arraylist_results );
            //     AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);

            Log.e("Recipe Adapter Set here ----->>>", "Adapter set ");

//            list.setAdapter(adapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Log.e("List Item Click -----tushar", "Clicked ");


//                    String latest_songs_bean12 = album_list_aaray.get(position).getId();
//

                }
            });
        }
    }
}
