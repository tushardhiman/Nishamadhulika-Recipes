package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.com.tushar.nishamadulika.Appam_recipe_fragment_tab;
import info.androidhive.materialdesign.fragments.LatestRecipes;


/**
 * Created by Dell on 5/24/2016.
 */
public class Home extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FragmentPagerAdapter adapterViewPager;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_custom_view_icon_text_tabs, container, false);
        // Locate the ViewPager in viewpager_main.xml

        viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set the ViewPagerAdapter into ViewPager0
      //  mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();


//        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new Sweets(), "Sweets");
        adapter.addFrag(new CurryDal(), "Curry Daal");
        adapter.addFrag(new Snacks(), "Snacks");
        adapter.addFrag(new Puri_paan(), "Puri Naan");

        adapter.addFrag(new Baking(), "Baking ");
        adapter.addFrag(new Rice(), "Rice");
        adapter.addFrag(new Chutney_Picke(), "Chutney Pickel");
        adapter.addFrag(new Misc(), "Misc");
        adapter.addFrag(new Reginol(), "Regional");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void setupTabIcons(){
        TextView tabAppam_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabAppam_recipe.setText("Sweet Recipe");
        tabAppam_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.baking, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabAppam_recipe);

        TextView tab_baati_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_baati_recipe.setText("Curry-Dal Recipe");
        tab_baati_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.chutney, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tab_baati_recipe);

        TextView tab_baking_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_baking_recipe.setText("Snacks Recipe");
        tab_baking_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.curry, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tab_baking_recipe);

        TextView tab_pani_puri = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_pani_puri.setText("Paani Puri Recipe");
        tab_pani_puri.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.rice, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tab_pani_puri);

        TextView tab_bangali_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_bangali_recipe.setText("Baking Recipe");
        tab_bangali_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.puri, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tab_bangali_recipe);


        TextView tab_bengali_sweets = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_bengali_sweets.setText("Rice Recipe");
        tab_bengali_sweets.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.rice, 0, 0);
        tabLayout.getTabAt(5).setCustomView(tab_bengali_sweets);

        TextView tab_bihari_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_bihari_recipe.setText("Chutney-Pickle Recipe");
        tab_bihari_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.snacks, 0, 0);
        tabLayout.getTabAt(6).setCustomView(tab_bihari_recipe);

        TextView tab_bonda = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_bonda.setText("Misc Recipe");
        tab_bonda.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.sweets, 0, 0);
        tabLayout.getTabAt(7).setCustomView(tab_bonda);

//        TextView tab_bread_recipe = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
//        tab_bread_recipe.setText("Regional Recipe");
//        tab_bread_recipe.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.chutney, 0, 0);
//        tabLayout.getTabAt(7).setCustomView(tab_bread_recipe);

        TextView tab_reginol = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_reginol.setText("Regional Recipe");
        tab_reginol.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.chutney, 0, 0);
        tabLayout.getTabAt(8).setCustomView(tab_reginol);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
