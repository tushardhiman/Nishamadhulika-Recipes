package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import NetworkCheckConnection.NetworkCheck;
import info.androidhive.materialdesign.Bean.RecipeCatListResults;
import info.androidhive.materialdesign.DataBase.DatabaseHelper;
import info.androidhive.materialdesign.ParserJson.JSONParser;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.Utils.StringFunctions;
import info.androidhive.materialdesign.adapter.Custom_Latestlist_Adapter;
import info.androidhive.materialdesign.adapter.RecipeListAdapter;
import info.androidhive.materialdesign.adapter.RecipeSubCatListAdapter;

/**
 * Created by Dell on 5/20/2016.
 */
public class AllCategory extends Fragment implements View.OnFocusChangeListener {
        ListView list;
        ProgressDialog ShowProgress;
        ArrayList<RecipeCatListResults> arraylist_results;
        ArrayList<RecipeCatListResults> partialNames = new ArrayList<RecipeCatListResults>();
        RecipeCatListResults rs;

        SQLiteDatabase db = null;
       static DatabaseHelper dbhelper;
        Cursor cursor = null;

        EditText auto_search;
        RecipeListAdapter adapter;
        RecipeSubCatListAdapter adapter_subcat;
        Boolean insidecancel = false;
        loadingTask ldtask;
static Context mContext;
        int page_no = 0, total_pages = 0;
        int RECIPE_OPT = 0;
        RadioButton rdcategory;
        ImageView btnSearch, btnNextPage, btnPreviousPage, btnRefresh;
private View mRoot;

public AllCategory() {
        }

// Called once the Fragment has been created in order for it to
// create its user interface.
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Create, or inflate the Fragment's UI, and return it.
        // If this Fragment has no UI then return null.
        mRoot = inflater.inflate(R.layout.fragment_all_cat, container, false);
        list = (ListView) mRoot.findViewById(R.id.list);
        auto_search = (EditText) mRoot.findViewById(R.id.auto_search);
        btnSearch = (ImageView) mRoot.findViewById(R.id.btnSearch);
        btnNextPage = (ImageView) mRoot.findViewById(R.id.btnNextPage);
        btnPreviousPage = (ImageView) mRoot.findViewById(R.id.btnPreviousPage);
        btnRefresh = (ImageView) mRoot.findViewById(R.id.btnRefresh);

        dbhelper = new DatabaseHelper(getActivity());
        db = dbhelper.getWritableDatabase();
        db.setLockingEnabled(true);
        getActivity().getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        btnSearch.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        // TODO Auto-generated method stub

        AlterAdapter();
        list.setAdapter(adapter);

        }
        });
        if (NetworkCheck.isNetworkAvailable(getActivity())) {
        cursor = null;
        cursor = db.rawQuery("select * from "
        + DatabaseHelper.TABLE_CATEGORIES, null);

        if (cursor.getCount() > 0) {
        cursor.close();
        loadingTask ldtask = new loadingTask();
        ldtask.execute("Database");
        } else {
        loadingTask ldtask = new loadingTask();
        ldtask.execute("Refresh");
        }

        } else {
        Toast.makeText(getActivity(),
        "Please check your Internet Connection", Toast.LENGTH_SHORT)
        .show();
        loadingTask ldtask = new loadingTask();
        ldtask.execute("Database");

        }

        auto_search.addTextChangedListener(new TextWatcher() {

@Override
public void onTextChanged(CharSequence arg0, int arg1, int arg2,
        int arg3) {
        if (auto_search.getText().toString().trim().isEmpty()) {
        list.setAdapter(new RecipeListAdapter(getActivity(),
        get10ResultsCategories()));

        }
        }

@Override
public void afterTextChanged(Editable arg0) {

        }

@Override
public void beforeTextChanged(CharSequence arg0, int arg1,
        int arg2, int arg3) {
        // TODO Auto-generated method stub

        }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        // TODO Auto-generated method stub
        if (NetworkCheck.isNetworkAvailable(getActivity())) {
        db.delete(DatabaseHelper.TABLE_CATEGORIES, null, null);
        loadingTask ldtask = new loadingTask();
        ldtask.execute("Refresh");
        } else {
        Toast.makeText(getActivity(),
        "Please check your Internet Connection",3000)
        .show();
        }
        }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        // TODO Auto-generated method stub
        System.out.println(total_pages);
        if (page_no < total_pages) {
        page_no++;
        System.out.println(page_no);

        list.setAdapter(new RecipeListAdapter(getActivity(),
        get10ResultsCategories()));

        }
        if (page_no == total_pages - 1) {

        btnNextPage.setVisibility(View.INVISIBLE);
        }
        btnPreviousPage.setVisibility(View.VISIBLE);
        }
        });
        btnPreviousPage.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        // TODO Auto-generated method stub
        if (page_no > 0) {
        page_no--;
        list.setAdapter(new RecipeListAdapter(getActivity(),
        get10ResultsCategories()));

        }
        if (page_no == 0) {
        btnPreviousPage.setVisibility(View.INVISIBLE);

        }
        btnNextPage.setVisibility(View.VISIBLE);
        }

        });
        return mRoot;
        }
@Override
public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        System.out.println("Recipe_Categories.onDetach()");
        }
@Override
public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("Recipe_Categories.onResume()");
        }
@Override
public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("Recipe_Categories.onPause()");
        }
private void AlterAdapter() {

        if (auto_search.getText().toString().isEmpty()) {
        partialNames.clear();
        partialNames.addAll(get10ResultsCategories());
        adapter.notifyDataSetChanged();
        adapter = new RecipeListAdapter(getActivity(), partialNames);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        } else {
        partialNames.clear();
        for (int i = 0; i < arraylist_results.size(); i++) {
        if (arraylist_results
        .get(i)
        .getRecipeCategoryName()
        .toUpperCase()
        .contains(
        auto_search.getText().toString().toUpperCase())) {
        partialNames.add(arraylist_results.get(i));
        }

        }
        adapter = new RecipeListAdapter(getActivity(), partialNames);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        }
        }

class loadingTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... arg0) {
        // TODO Auto-generated method stub
        if (arg0[0].equals("Refresh")) {
            System.err.println("REFRESH");
            String url = "";
            url = "http://nishamadhulika.com/api/categorylist";
            try {
                JSONParser jParser = new JSONParser();

                // getting JSON string from URL
                JSONArray CatArray = jParser.getJSONFromUrl(url);
					/*
					 * total_pages = CatArray.length() / 10; if
					 * (CatArray.length() % 10 != 0) { total_pages = total_pages
					 * + 1; }
					 */
                rs = new RecipeCatListResults();

                for (int i = 0; i < CatArray.length(); i++) {
                    try {
                        ContentValues insert_values = new ContentValues();
                        JSONObject c = CatArray.getJSONObject(i);

                        // rs.setRecipeCategoryName(c.getString("Name"));
                        rs.setRecipeCategoryName(StringFunctions
                                .StringUpperCase(c.getString("Name")));
                        insert_values.put(DatabaseHelper.COL_NAME,
                                StringFunctions.StringUpperCase(c
                                        .getString("Name")));
                        rs.setRecipeCategoryID(c.getString("ID"));
                        insert_values.put(DatabaseHelper.COL_ID,
                                c.getString("ID"));
                        rs.setRecipeArticleCount(c
                                .getString("ArticleCount"));
                        insert_values.put(DatabaseHelper.COL_ARTICLECOUNT,
                                c.getString("ArticleCount"));
                        arraylist_results.add(rs);

                        Log.e("Response For Catagory List IS  : ", String.valueOf(rs));

                        db.insertOrThrow(DatabaseHelper.TABLE_CATEGORIES,
                                null, insert_values);
                        rs = new RecipeCatListResults();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            System.err.println("DB");
            cursor = null;
            cursor = db.rawQuery("select * from "
                    + DatabaseHelper.TABLE_CATEGORIES, null);
            rs = new RecipeCatListResults();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                rs.setRecipeCategoryName(cursor.getString(cursor
                        .getColumnIndex(DatabaseHelper.COL_NAME)));
                rs.setRecipeCategoryID(cursor.getString(cursor
                        .getColumnIndex(DatabaseHelper.COL_ID)));
                rs.setRecipeArticleCount(cursor.getString(cursor
                        .getColumnIndex(DatabaseHelper.COL_ARTICLECOUNT)));
                arraylist_results.add(rs);
                rs = new RecipeCatListResults();
            }
            cursor.close();
        }
        total_pages = arraylist_results.size() / 10;
        if (arraylist_results.size() % 10 != 0) {
            total_pages = total_pages + 1;
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        ShowProgress = new ProgressDialog(getActivity());
        ShowProgress.setMessage("Preparing to download");
        ShowProgress.setCancelable(true);
        insidecancel = false;
        page_no = 0;
        total_pages = 0;
        ShowProgress
                .setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        try {
                            System.out.println("Inside cancel");
                            Toast.makeText(getActivity(),
                                    "Loading Cancelled.", 3000).show();
                            insidecancel = true;
                            ldtask.cancel(true);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                });
        arraylist_results = new ArrayList<RecipeCatListResults>();

        ShowProgress.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        if (!insidecancel) {

            adapter = new RecipeListAdapter(getActivity(),
                    get10ResultsCategories());
            list.setAdapter(adapter);
//            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View v,
//                                        int position, long id) {
//                    // TODO Auto-generated method stub
//                    Object obj_ret = list.getItemAtPosition(position);
//                    RecipeCatListResults result_obj = (RecipeCatListResults) obj_ret;
//                    Intent intent = new Intent(getActivity(),
//                            Recipe_Subcategory.class);
//                    intent.putExtra("CategoryID",
//                            result_obj.getRecipeCategoryID());
//                    intent.putExtra("ArticleCount",
//                            result_obj.getRecipeArticleCount());
//                    startActivity(intent);
//                    System.out
//                            .println("Recipe_Categories.loadingTask.onPostExecute(...).new OnItemClickListener() {...}.onItemClick()");
//                }
//            });
        }
        if (total_pages > 1)
            btnNextPage.setVisibility(View.VISIBLE);
        else
            btnNextPage.setVisibility(View.INVISIBLE);
        btnPreviousPage.setVisibility(View.INVISIBLE);
        list.requestFocus();
        ShowProgress.dismiss();
    }
}

    public ArrayList<RecipeCatListResults> get10ResultsCategories() {

        ArrayList<RecipeCatListResults> temparraylist_results = null;
        temparraylist_results = new ArrayList<RecipeCatListResults>();

        for (int i = 0; i < 10; i++) {
            try {
                temparraylist_results.add(arraylist_results.get(i + page_no
                        * 10));
            } catch (Exception e) {
                // TODO: handle exception
                break;
            }

        }
        return temparraylist_results;

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub
        System.out.println("Recipe_Categories.onFocusChange()");
    }

}
