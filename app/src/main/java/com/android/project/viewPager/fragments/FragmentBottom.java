package com.android.project.viewPager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.project.R;
import com.android.project.viewPager.model.Travel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentBottom extends Fragment {
    Travel travel;


    @Bind(R.id.titlefb)
    TextView titlefb;

    public static FragmentBottom newInstance() {
        return new FragmentBottom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (travel != null) {
            titlefb.setText(travel.getName());
        }

    }
}
