package com.ankesh.instasplit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by I324832 on 1/28/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitle = new ArrayList<>();

    public void addFragments(Fragment fragment , String title)
    {
        this.fragments.add(fragment);
        this.tabTitle.add(title);
    }
    public ViewPagerAdapter(FragmentManager fragment)
    {
        super(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
