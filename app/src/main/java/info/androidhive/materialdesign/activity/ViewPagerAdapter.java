package info.androidhive.materialdesign.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import info.androidhive.materialdesign.R;

/**
 * Created by Dell on 5/25/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 2;
    private String titles[] = new String[] { "Tab1", "Tab2" };

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
//                TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//                tabOne.setText("ONE");
//                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ornament, 0, 0);
//                tabLayout.getTabAt(0).setCustomView(tabOne);


                AllCategory fragmenttab1 = new AllCategory();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                Baking fragmenttab2 = new Baking();
                return fragmenttab2;
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}