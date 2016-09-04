package com.android.project;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.PopupMenu;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by Karun on 8/2/16.
 */
public class StoresList_Fragment_RecyclerView extends Fragment {

    private static final String ARG_MOVIE_DATA = "section_number";
    RecyclerView mRecyclerView;
    StoresListFirebaseRecyclerAdapter mRecyclerViewAdapter;
    StoresList storesList = new StoresList();
    private List<Map<String, ?>> storesDataList;
    LinearLayoutManager mLayoutManager;
    final Firebase ref = new Firebase("https://grocerryapp-acf0e.firebaseio.com/storesinformation");

    OnCardItemClickedListener onCardItemClickedListener;

    onButtonItemClickListener buttonItemClickListener;

    public static StoresList_Fragment_RecyclerView newInstance() {
        StoresList_Fragment_RecyclerView fragment = new StoresList_Fragment_RecyclerView();
        Bundle args = new Bundle();
        //args.putSerializable(ARG_MOVIE_DATA, (Serializable) movieData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onCardItemClickedListener = (OnCardItemClickedListener) getContext();
        buttonItemClickListener = (onButtonItemClickListener)getContext();
        setHasOptionsMenu(true);
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.findItem(R.id.menu_search) == null)
            inflater.inflate(R.menu.menu_recycler, menu);

        Log.d("Message", (String) getActivity().getTitle());

        if (getActivity().getTitle().equals("Task 3")) {
            Log.d("Message", (String) getActivity().getTitle());
            Drawable drawable = menu.findItem(R.id.menu_search).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new StoresListFirebaseRecyclerAdapter(StoresList.class, R.layout.storeslist_cardview,
                StoresListFirebaseRecyclerAdapter.StoresListViewHolder.class, ref, getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerViewAdapter.setOnItemClickListener(new StoresListFirebaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String vId) {
                onCardItemClickedListener.onCardItemClicked(v, position, vId);
            }

            /*@Override
            public void onItemClick(View view, int position, String vId) {
                onCardItemClickedListener.onCardItemClicked( view, position, vId);
            }*/

        });

        mRecyclerViewAdapter.setButtonClickListener(new StoresListFirebaseRecyclerAdapter.onButtonClickListener() {
            @Override
            public void onButtonClicked(String storeId) {
                buttonItemClickListener.onButtonItemClicked(storeId);
            }
        });

        //defaultAnimation();
        adapterAnimation();
        itemAnimation();


        return rootView;

    }

    //Animation
    private void defaultAnimation() {
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        animator.setRemoveDuration(100);

        mRecyclerView.setItemAnimator(animator);
    }

    private void itemAnimation() {
        ScaleInAnimator animator = new ScaleInAnimator();
        //SlideInLeftAnimator animator=new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());

        animator.setAddDuration(300);
        animator.setRemoveDuration(300);

        mRecyclerView.setItemAnimator(animator);
    }

    private void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecyclerViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        //SlideInRightAnimationAdapter radapter=new SlideInRightAnimationAdapter(scaleAdapter);
        mRecyclerView.setAdapter(scaleAdapter);
    }


    public StoresList_Fragment_RecyclerView() {

    }


    public interface OnCardItemClickedListener{
        public void onCardItemClicked(View v, int position, String vId);

        //void onCardItemClicked(HashMap<String, ?> movie, View v);
    }

    public interface onButtonItemClickListener{
        public void onButtonItemClicked(String storeId);
    }

}
