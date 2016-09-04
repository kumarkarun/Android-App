package com.android.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class CheckOutRecyclerViewFragment extends Fragment {
    CheckOutRecyclerViewAdapter mRecyclerViewAdapter;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    LocalStoreItems localStoreItems = new LocalStoreItems();
    OnCardItemClickedListener onCardItemClickedListener;


    public static CheckOutRecyclerViewFragment newInstance(List<Map<String, ?>> movie) {
        CheckOutRecyclerViewFragment fragment = new CheckOutRecyclerViewFragment();
        String test = movie.toString();
        Log.d("CheckOutRECVIEW>>", test);

        fragment.localStoreItems.storeItemsList = movie;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public CheckOutRecyclerViewFragment() {
       // movieData = new MovieData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //onCardItemClickedListener = (OnCardItemClickedListener) getContext();
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.checkout_fragment, container,false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.cardList);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new CheckOutRecyclerViewAdapter(getActivity(), localStoreItems.getStoreItemsList());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        adapterAnimation();
        itemAnimator();

        mRecyclerViewAdapter.setOnItemClickListener(new CheckOutRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // onCardItemClickedListener.onCardItemClicked(localStoreItems.getItem(position));
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                mRecyclerViewAdapter.registerToggle(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                int newPos = position + 1;
                localStoreItems.addItem(newPos, (HashMap<String, ?>) localStoreItems.getItem(position).clone());
                mRecyclerViewAdapter.notifyItemInserted(newPos);
            }

            @Override
            public void onItemChecked(int position, boolean isChecked) {
                if (isChecked)
                    localStoreItems.getItem(position).put("selection", true);
                else
                    localStoreItems.getItem(position).put("selection", false);
            }


        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.task1menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem selectAll;
        int id= item.getItemId();
        //Intent intent;

        switch(id) {
            case R.id.checkOut:
                for (int i = 0; i < localStoreItems.getSize(); i++) {
                    localStoreItems.getItem(i).put("selection", true);
                    mRecyclerViewAdapter.notifyItemChanged(i);
                }
                return true;

           /* case R.id.clearAll:
                for(int i=0;i<localStoreItems.getSize();i++){
                    localStoreItems.getItem(i).put("selection", false);
                    mRecyclerViewAdapter.notifyItemChanged(i);
                }
                return true;
            case R.id.delete:
                Log.d("inside delete", "item going to be removed ");
                int size = localStoreItems.getSize();
                for (int i = 0; i < size; i++) {
                    boolean selectFlag = (Boolean) localStoreItems.getItem(i).get("selection");
                    if (selectFlag) {
                        localStoreItems.removeItem(i);
                        mRecyclerViewAdapter.notifyItemRemoved(i);
                        i--;
                        size--;
                        Log.d("inside delete", "item removed ");
                    }
                }
                return true; */
        }

        return super.onOptionsItemSelected(item);
    }


    private void itemAnimator() {
        SlideInRightAnimator animator = new SlideInRightAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        animator.setAddDuration(1000);
        animator.setRemoveDuration(500);
        mRecyclerView.setItemAnimator(animator);


    }

    private void adapterAnimation() {
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(mRecyclerViewAdapter);
        alphaAdapter.setDuration(500);
        mRecyclerView.setAdapter(alphaAdapter);
    }





    public interface OnCardItemClickedListener{
        public void onCardItemClicked(HashMap<String, ?> movie);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putSerializable("moviData", );

    }
}



