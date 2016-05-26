package info.androidhive.materialdesign.com.tushar.nishamadulika;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

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
import info.androidhive.materialdesign.ParserJson.JSONParser;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.ReceipeAdapter;

/**
 * Created by tushar sharma on 5/25/2016.
 */
public class Appam_recipe_fragment_tab extends Fragment {
     RecipeSubCatListResults rs;
    ReceipSub_data mID;
    ProgressDialog pDialog;
    ArrayList<RecipeSubCatListResults> arraylist_results;
    Boolean insidecancel = false;
    ProgressDialog ShowProgress;
    public static ArrayList<ReceipSub_data> mListIDs=new ArrayList<ReceipSub_data>();
    ReceipeAdapter mReceipeAdapter;
    ListView list;
    Context context;
    ArrayList prgmName;
    int page_no = 1, total_pages = 0;
    public Appam_recipe_fragment_tab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.halwa_recipe, container, false);
        list=(ListView) rootView.findViewById(R.id.list_halwa_recipe);

        new loadingTask().execute();

//        gv.setAdapter(new Custom_Latestlist_Adapter((MainActivity) getActivity(), prgmNameList,prgmImages));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
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

    private class loadingTask extends AsyncTask<String, String, String> {

        String ID,Title,Description,ThumbURL;

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

            Log.e("CAtagory data is tushar  ---->", String.valueOf(content));

            if (String.valueOf(content) == null) {
                Toast.makeText(getActivity(), "Connection is Breaking ", Toast.LENGTH_SHORT).show();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            // TODO Auto-generated method stub
            pDialog.dismiss();
//            try {
//
//                Log.e("Album Post Method Call  here ....", "Method ...");
//                JSONArray ar = new JSONArray(s);
//                arraylist_results = new ArrayList<RecipeSubCatListResults>();
//                System.out.println("Array response is ---" + ar);
//                Log.e("Album Array response ids---", String.valueOf(ar));
//                for (int i = 0; i < ar.length(); i++) {
//                    Log.e("Album Array  is ---", String.valueOf(ar.length()));
//
//                    JSONObject jsonobject = ar.getJSONObject(i);
//
//                    RecipeSubCatListResults songListBean = new RecipeSubCatListResults();
//
//                    ID = jsonobject.getString("ID");
//
//                   // songListBean.setId(id);
////                    String.valueOf(ar).indexOf(i);
//                    Log.e("ALBUM ID Is =======================>>>>>", id);
//                    Log.e("Array id  Index is ---->>", String.valueOf(String.valueOf(ar).indexOf(i)));
//
//
////                    AlbumCover = jsonobject.getString("AlbumCover");
////                    songListBean.setAlbumCover(AlbumCover);
////                    Log.e(" AlbumCover As it Is Here=======================>>>>>", AlbumCover);
////
////
////                    AlbumName = jsonobject.getString("AlbumName");
////                    songListBean.setAlbumName(AlbumName);
////                    Log.e("Album name As it Is Here=======================>>>>>", AlbumName);
//////                    AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);
////                    //    Log.e("Adapter set  here --(*(*(*(*(*(*(*(*(--", String.valueOf(adapter));
////                    song_Array_list.add(songListBean);
////                    Log.e("song_Array_list set  here --NJNIJNIJMNIMNIMNIKMN--", String.valueOf(song_Array_list));
////                }
////            }
////
////            catch (JSONException e)
////
////            {
////                e.printStackTrace();
////            }
////            Log.e("Album [][][][[][][][][][[]Array list is ----- >>>", String.valueOf(song_Array_list));
////
////            AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);
////            //     AlbumListAdapter adapter = new AlbumListAdapter(AlbumList.this, R.layout.album_list_layout, song_Array_list);
////            album_list_layout_list1 = (ListView) findViewById(R.id.album_list_layout_list1);
////
////            Log.e("Album Adapter Set here ----->>>", "Adapter set ");
////
////            album_list_layout_list1.setAdapter(adapter);
////
////
////            album_list_layout_list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
////                    Log.e("List Item Click -----tushar", "Clicked ");
////
////
//////                    String latest_songs_bean12 = album_list_aaray.get(position).getId();
////                    String latest_songs_bean123 = song_Array_list.get(position).getAlbumCover();
////                    int songIndex = position;
////
////                    Intent i = new Intent(getApplicationContext(), AlbumeSongs.class);
////
////                    String string = String.valueOf(song_Array_list);
////
////                    i.putExtra("songIndex", songIndex);
////                    Log.e("put Index is --->", String.valueOf(songIndex));
////                    String latest_songs_bean12 = song_Array_list.get(position).getId();
////
////                    i.putExtra("ALBUM_ID", latest_songs_bean12);
////                    Log.e("ALBUM_ID put is ----", latest_songs_bean12);
////                    //   String latest_songs_bean123 = album_list_aaray.get(position).getAlbumName();
////                    i.putExtra("Album_cover_image", latest_songs_bean123);
////                    System.out.println(" Album_cover_image is ----" + latest_songs_bean123);
////
////
////                    startActivity(i);
////
//                }
////            });
//
////            if (!insidecancel) {
////                list.requestFocus();
////                ShowProgress.dismiss();
////
////                mReceipeAdapter = new ReceipeAdapter(getActivity(), mListIDs);
////                list.setAdapter(mReceipeAdapter);
////				/*list.setAdapter(new ReceipeAdapter(
////						getBaseContext(), mListIDs));*/
////
////            }
////            mReceipeAdapter = new ReceipeAdapter(getActivity(), mListIDs);
////            list.setAdapter(mReceipeAdapter);
////        }
//
////        @Override
////        protected void onPreExecute() {
////            // TODO Auto-generated method stub
////            ShowProgress = new ProgressDialog(getActivity());
////            ShowProgress.setMessage("Preparing to download");
////            ShowProgress.setCancelable(true);
////            insidecancel = false;
////            ShowProgress
////                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
////
////                        @Override
////                        public void onCancel(DialogInterface dialog) {
////                            try {
////                                System.out.println("Inside cancel");
////                                Toast.makeText(getActivity(),
////                                        "Loading Cancelled.", 3000).show();
////                                insidecancel = true;
////                                ShowProgress.dismiss();
////                                //    loadingTask.cancel(true);
////                            } catch (Exception e) {
////                                // TODO: handle exception
////                            }
////
////                        }
////                    });
////            arraylist_results = new ArrayList<RecipeSubCatListResults>();
////            ShowProgress.show();
////            super.onPreExecute();
////        }

        }
    }
    }
