package com.jelcaf.pacomf.patealapalma.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.fragment.InfoCodesFragment;
import com.jelcaf.pacomf.patealapalma.fragment.InfoSecurityFragment;
import com.jelcaf.pacomf.patealapalma.fragment.InfoWarningsFragment;



public class InfoActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    PagerSlidingTabStrip tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabHost = (PagerSlidingTabStrip) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabHost.setViewPager(pager);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public static final int NUM_TABS = 3;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int num) {
            switch (num) {
                case 0:
                    return new InfoCodesFragment();
                case 1:
                    return new InfoSecurityFragment();
                case 2:
                    return new InfoWarningsFragment();
                default:
                    return new InfoCodesFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_tab_info_codes);
                case 1:
                    return getString(R.string.title_tab_info_security);
                case 2:
                    return getString(R.string.title_tab_info_warnings);
                default:
                    return getString(R.string.title_tab_info_codes);
            }
        }

    }


}
