package info.androidhive.materialdesign.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.fragments.LatestRecipes;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private MaterialSearchView searchView;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });




        drawerFragment = (FragmentDrawer)
        getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);
//        SearchAdapter adapter = new SearchAdapter();
//        searchView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        if(id == R.id.action_search){
////            Intent i= new Intent(MainActivity.this,MaterialSearchView.class);
////            startActivity(i);
//            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:

                fragment = new Home();
                title = getString(R.string.title_friends);
                break;
            case 1:
                fragment = new LatestRecipes();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new HalwaRec_2();
                title = getString(R.string.title_friends);
                break;
            case 3:
                fragment = new Test();
                title = getString(R.string.title_friends);
                break;

            case 4:
                fragment = new CurryDal();
                title = getString(R.string.title_home);
                break;
            case 5:
                fragment = new Snacks();
                title = getString(R.string.title_messages);
                break;

            case 6:
                fragment = new Puri_paan();
                title = getString(R.string.title_puri_naan);
                break;
            case 7:
                fragment = new Baking();
                title = getString(R.string.title_baking);
                break;
            case 8:
                fragment = new Rice();
                title = getString(R.string.title_rice);
                break;

            case 9:
                fragment = new Chutney_Picke();
                title = getString(R.string.title_chutiny);
                break;
            case 10:
                fragment = new Misc();
                title = getString(R.string.title_misc);
                break;
            case 11:
                fragment = new Reginol();
                title = getString(R.string.title_regional);
                break;
//            case 11:
//                fragment = new VisitNishaMadulika();
//                title = getString(R.string.title_Visit_NishaMadulika);
//
//                break;
//            case 12:
//                fragment = new AllCategory();
//                title = getString(R.string.title_Visit_NishaMadulika);
//
//                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    private class SearchAdapter  extends BaseAdapter implements Filterable {

        private ArrayList<String> data;

        private String[] typeAheadData;

        LayoutInflater inflater;

//        public SearchAdapter() {
//            inflater = LayoutInflater.from(MainActivity.this);
//            data = new ArrayList<String>();
//            typeAheadData = getResources().getStringArray(R.array.state_array_full);
//        }


        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (!TextUtils.isEmpty(constraint)) {
                        // Retrieve the autocomplete results.
                        List<String> searchData = new ArrayList<>();

                        for (String str : typeAheadData) {
                            if (str.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                                searchData.add(str);
                            }
                        }

                        // Assign the data to the FilterResults
                        filterResults.values = searchData;
                        filterResults.count = searchData.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results.values != null) {
                        data = (ArrayList<String>) results.values;
                        notifyDataSetChanged();
                    }
                }
            };
            return filter;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                mViewHolder = new MyViewHolder(convertView);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            String currentListData = (String) getItem(position);

            mViewHolder.textView.setText(currentListData);

            return convertView;
        }


        private class MyViewHolder {
            TextView textView;

            public MyViewHolder(View convertView) {
                textView = (TextView) convertView.findViewById(android.R.id.text1);
            }
        }
    }
}
