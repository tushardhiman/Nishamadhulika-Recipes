package info.androidhive.materialdesign.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.Bean.LatestdishPojo;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.activity.ServiceHandler;
import info.androidhive.materialdesign.adapter.LatestAdapter;

/**
 * Created by EnemSML2 on 20-05-2016.
 */
public class LatestRecipes extends Fragment {

    private List<LatestdishPojo> latestdishlist;
    private LatestAdapter mLatestAdapter;
    private Context context;
    private ListView listview;
    private View rootView;
    private int counterTag = 1;
    private boolean flag_loading;

    //Main Url
    private String url = "http://nishamadhulika.com/api/getlatestpaged?page=";

    //TAGS
    private static final String _ID = "ID";
    private static final String TITLE = "Title";
    private static final String THUMB_URL = "ThumbURL";

    public LatestRecipes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        latestdishlist = new ArrayList<LatestdishPojo>();
        context = getActivity();
        //call
        (new RequestTask()).execute(url + counterTag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.latest_1, container, false);
        listview = (ListView) rootView.findViewById(R.id.latestrecipeslist);
        return rootView;
    }

    private class RequestTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... url) {
            int count = url.length;
            String response = ServiceHandler.makeGetRequest(url[0]);
            Log.d("response", response);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String jsonString = s;
            Log.d("postString", s);
            try {
                JSONArray jsonarray = new JSONArray(jsonString);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    int _id = jsonobject.getInt(_ID);
                    String title = jsonobject.getString(TITLE);
                    String thumbURL = jsonobject.getString(THUMB_URL);
                    latestdishlist.add(new LatestdishPojo(_id, title, thumbURL));

                }


            } catch (JSONException e) {
                // Exception Handler Nishamadhulika.com
            }

            flag_loading = false;
            mLatestAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        listview.setAdapter(mLatestAdapter = new LatestAdapter(context, R.layout.latest_list_item_1, latestdishlist));

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (flag_loading == false) {
                        flag_loading = true;
                        addNextLatestDishes(++counterTag);
                    }
                }
            }
        });

         listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 int _id = latestdishlist.get(position).getId();

             }
         });
    }


    void addNextLatestDishes(int counterTag) {

        (new RequestTask()).execute(url + counterTag);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
