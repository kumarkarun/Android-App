package com.android.project;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.PopupMenu;

import com.firebase.client.Firebase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class StoreItems_Fragment_RecyclerView extends Fragment implements Serializable{

    private static final String ARG_MOVIE_DATA = "section_number";
    RecyclerView mRecyclerView;
    StoreItemsRecylerAdapter mRecyclerViewAdapter;
    Movie movieData = new Movie();
    private List<Map<String, ?>> movieDataList;
    LinearLayoutManager mLayoutManager;
    //final Firebase ref = new Firebase("https://grocerryapp-acf0e.firebaseio.com/moviedata");
    static Firebase ref;
    static String urlString = "https://grocerryapp-acf0e.firebaseio.com/";
    static String firebaseStoreLink;
    OnCheckoutClickedListener onCheckoutClickedListener;
    OnItemClickedListener onItemClickedListener;
    LocalStoreItems localStoreItems = new LocalStoreItems();
    LocalStoreItems tempLocalStoreItems = new LocalStoreItems();

    //static PopulateDB populateDB;
    Toolbar toolbar;
    ActionBar actionBar;


    public static StoreItems_Fragment_RecyclerView newInstance() {

        StoreItems_Fragment_RecyclerView fragment = new StoreItems_Fragment_RecyclerView();
        Bundle args = new Bundle();
        //firebaseStoreLink = urlString+vId;
        //ref = new Firebase(firebaseStoreLink);
        //args.putSerializable(ARG_MOVIE_DATA, (Serializable) movieData);


        fragment.localStoreItems.storeItemsList = PopulateDB.movieList;
        Log.d("movieDataFrag>>>",fragment.localStoreItems.storeItemsList.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        onCheckoutClickedListener = (OnCheckoutClickedListener)getContext();
        onItemClickedListener = (OnItemClickedListener)getContext();
        //actionBar  = getActivity(). getSupportActionBar();
        //toolbar=(Toolbar) findViewById(R.id.toolBar);
        //setSupportActionBar(toolbar);
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

        /*SearchView search = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        Log.d("Message", "OnMenuOptions");
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    int position = movieData.getPosition(query);
                    Log.d("Position", position + "");
                    if (position >= 0)
                        mRecyclerView.scrollToPosition(position);
                    else
                        Toast.makeText(getContext(), "Such Movie is not listed!", Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);*/
        inflater.inflate(R.menu.task1menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        /*final Fragment_List.OnListItemSelectedListener mListener;
        try {
            mListener = (Fragment_List.OnListItemSelectedListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("The hosting activity of the fragment" +
                    "forgot to implement onFragmentInteractionListener");
        }*/
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new StoreItemsRecylerAdapter(getActivity(), localStoreItems.getStoreItemsList());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);



        mRecyclerViewAdapter.setOnItemClickListener(new StoreItemsRecylerAdapter.OnItemClickListener() {

            @Override
            public void onOverflowMenuClick(View v, final int position) {
                PopupMenu popup=new PopupMenu(getActivity(),v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_card_duplicate:
                                /*Movie cloud= mRecyclerViewAdapter.getItem(position);
                                cloud.setName(cloud.getName() + "_");
                                cloud.setId(cloud.getId() + "_new");
                                ref.child(cloud.getId()).setValue(cloud);
                                */return true;
                            case R.id.menu_card_delete:
                                /*Movie cloudDelete=mRecyclerViewAdapter.getItem(position);
                                ref.child(cloudDelete.getId()).removeValue();
                                */return true;
                            default:
                                return false;
                        }

                    }
                });
                MenuInflater inflater=popup.getMenuInflater();
                inflater.inflate(R.menu.menu_contextual_popup,popup.getMenu());
                popup.show();

            }

            @Override
            public void onItemChecked(int position, boolean isChecked) {
            Log.d("checkPosition>>",position+" "+isChecked);
                if (isChecked) {
                    localStoreItems.getItem(position).put("selection", true);
                    if(tempLocalStoreItems.getItem(position) == null) {
                        tempLocalStoreItems.addItem(position, localStoreItems.getItem(position));
                    }
                }

                else
                    localStoreItems.getItem(position).put("selection", false);
                    /*if(tempLocalStoreItems.getItem(position) != null){
                        tempLocalStoreItems.getItem(position).put("selection",false);
                    }*/

            }

            @Override
            public void onCheckOutClick(List<Map<String, ?>> movie) {
                onCheckoutClickedListener.onCheckOutClicked(movie);
            }

            @Override
            public void onItemClicked(View view, int position) {
                Log.d("postioninrec>>>",position+"");
                onItemClickedListener.onItemClicked(localStoreItems.getItem(position));
            }
        });


        //defaultAnimation();
        adapterAnimation();
        itemAnimation();


        return rootView;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem selectAll;
        int id= item.getItemId();
        //Intent intent;
        //item.setOnMenuItemClickListener();
        switch(id)
        {
            case R.id.checkOut:
                Log.d("inside checkOut", "adding to local List ");
                int size = localStoreItems.getSize();
                for (int i = 0; i < size; i++) {
                    boolean selectFlag = (Boolean) localStoreItems.getItem(i).get("selection");
                    String name = (String) localStoreItems.getItem(i).get("name");
                    Log.d("CheckOutName>>",name);
                    Log.d("CheckOutSelection>>", selectFlag+"");
                    if (selectFlag) {
                        //mRecyclerViewAdapter.localStoreItems.removeItem(i);
                        //mRecyclerViewAdapter.notifyItemRemoved(i);
                        //i--;
                        //size--;
                        Log.d("checkOutCart>>",name);
                    }
                }
                Log.d("tempLocalStoreItems####", tempLocalStoreItems.getStoreItemsList().toString());
                onCheckoutClickedListener.onCheckOutClicked(tempLocalStoreItems.getStoreItemsList());
                return true;
        }
        return super.onOptionsItemSelected(item);
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


    public StoreItems_Fragment_RecyclerView() {

    }

    public interface OnCheckoutClickedListener{
        public void onCheckOutClicked(List<Map<String, ?>> movie);
    }

    public interface OnItemClickedListener{
        public void onItemClicked(HashMap<String,?> movie);
    }


}
