package com.android.project.viewPager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.project.viewPager.fragments.TravelExpandingFragment;
import com.android.project.viewPager.model.Travel;
import com.qslll.library.ExpandingViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qs on 16/5/30.
 */
public class TravelViewPagerAdapter extends ExpandingViewPagerAdapter {

    List<Travel> travels;

    public TravelViewPagerAdapter(FragmentManager fm) {
        super(fm);
        travels = new ArrayList<>();
    }

    public void addAll(List<Travel> travels){
        this.travels.addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Travel travel = travels.get(position);
        return TravelExpandingFragment.newInstance(travel);
    }

    @Override
    public int getCount() {
        return travels.size();
    }

}
