package com.android.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class StoreItemsRecylerAdapter extends RecyclerView.Adapter<StoreItemsRecylerAdapter.ViewHolder> {

    private Context mContext;
    static OnItemClickListener mItemClickListener;
    //ArrayList<LocalStoreItems> localMovieList = new ArrayList<LocalStoreItems>();
    //public static LocalStoreItems localStoreItems   = new LocalStoreItems();
    private List<Map<String, ?>> mDataSet;

    public StoreItemsRecylerAdapter(Context myContext, List<Map<String, ?>> myDataSet) {
        mContext = myContext;
        mDataSet = myDataSet;
    }


    @Override
    public StoreItemsRecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        switch (viewType) {
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                break;
        }


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StoreItemsRecylerAdapter.ViewHolder holder, int position) {
        Map<String,?> movie = mDataSet.get(position);
        holder.bindMovieData(movie);
    }

    @Override
    public int getItemCount() {
        int s = mDataSet.size();
        Log.d("size", String.valueOf(s));

        return mDataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;
        public TextView vName;
        public TextView vBrand;
        public CheckBox vCheckBox;
        public TextView vOrganic;
        public TextView vPrice;
        public ImageView vMenu;


        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.rv_icon);
            vName = (TextView) v.findViewById(R.id.rv_name);
            vBrand = (TextView) v.findViewById(R.id.rv_brand);
            vOrganic = (TextView)v.findViewById(R.id.rv_organic);
            vPrice = (TextView)v.findViewById(R.id.rv_price);
            vCheckBox = (CheckBox) v.findViewById(R.id.rv_checkbox);
            vMenu = (ImageView) v.findViewById(R.id.rv_overflow);
            //vRating = (TextView) v.findViewById(R.id.rv_rating);


            if(vMenu!=null){
                vMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener!=null){
                            mItemClickListener.onOverflowMenuClick(v,getAdapterPosition());
                        }
                    }
                });
            }

            vCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemChecked(getPosition(), isChecked);
                }
            });


            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mItemClickListener.onCheckOutClick(mDataSet);
                }
            });


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        Log.d("adapterpos>>>",getAdapterPosition()+"");
                        mItemClickListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }

        public void bindMovieData(Map<String, ? > movie) {
           /// if ((Boolean) movie.get("selection")) {
                // vIcon.setImageResource((Integer)movie.get("image"));
            Picasso.with(mContext).load(movie.get("url").toString()).into(vIcon);

            vName.setText((String) movie.get("name"));
                vBrand.setText((String) movie.get("brand"));
                vOrganic.setText((String) movie.get("organic"));
                vPrice.setText((String) movie.get("price"));
                vCheckBox.setChecked((Boolean) movie.get("selection"));
                //vRating.setText( movie.get("rating")+"/10");
           // }
        }



    }



    /// code below is old for firebase


    public interface OnItemClickListener {
        public void onOverflowMenuClick(View v, final int position);
            //public void onItemClick(View view, int position);
            public void onItemChecked(int position, boolean b);
        public void onCheckOutClick(List<Map<String, ?>> movie);
        public void onItemClicked(View view, int position);
        }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}
