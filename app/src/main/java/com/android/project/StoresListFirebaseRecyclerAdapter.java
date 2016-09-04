package com.android.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Karun on 8/2/16.
 */
public class StoresListFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<StoresList, StoresListFirebaseRecyclerAdapter.StoresListViewHolder> {

    private Context mContext;
    static OnItemClickListener mItemClickListener;
    static onButtonClickListener buttonClickListener;


    public interface onButtonClickListener{
        public void onButtonClicked(String storeId);
    }
    public void setButtonClickListener(final onButtonClickListener buttonClickListener)
    {
        this.buttonClickListener = buttonClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, final int position, String vId);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public StoresListFirebaseRecyclerAdapter(Class<StoresList> modelClass, int modelLayout,
                                    Class<StoresListViewHolder> holder, Query ref, Context context) {
        super(modelClass, modelLayout, holder, ref);
        this.mContext = context;
    }


    @Override
    protected void populateViewHolder(StoresListViewHolder storesListViewHolder, StoresList storesList, int i) {

        //TODO: Populate viewHolder by setting the movie attributes to cardview fields
        storesListViewHolder.vId.setText(storesList.getId());
        storesListViewHolder.vAddress.setText(storesList.getAddress());
        Float rating = storesList.getRating() / 2;
        storesListViewHolder.vRating.setRating(rating);
        storesListViewHolder.vType.setText(storesList.getType());
        //Picasso.with(mContext).load(movie.getUrl()).into(movieViewHolder.vIcon);
    }

    public static class StoresListViewHolder extends RecyclerView.ViewHolder {

        //public ImageView vIcon;
        public TextView vId;
        public TextView vAddress;
        public RatingBar vRating;
        public TextView vType;
        public Button vdire;
        //public ImageView vMenu;

        public StoresListViewHolder(View v) {
            super(v);
            vId = (TextView) v.findViewById(R.id.rv_id);
            vAddress = (TextView) v.findViewById(R.id.rv_address);
            vRating = (RatingBar) v.findViewById(R.id.rv_rating);
            vType = (TextView) v.findViewById(R.id.rv_type);
            vdire = (Button) v.findViewById(R.id.get_direction);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {

                        mItemClickListener.onItemClick(v, getAdapterPosition(), vId.getText().toString());
                    }
                }
            });


            vdire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClickListener.onButtonClicked(vId.getText().toString());
                }
            });

            /*if(vMenu!=null){
                vMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener!=null){
                            mItemClickListener.onOverflowMenuClick(v,getAdapterPosition());
                        }
                    }
                });
            }*/
        }
    }
}
