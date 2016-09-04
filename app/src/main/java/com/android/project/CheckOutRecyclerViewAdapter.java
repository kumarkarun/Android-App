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
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by comp on 6/10/2016.
 */
public class CheckOutRecyclerViewAdapter extends RecyclerView.Adapter<CheckOutRecyclerViewAdapter.ViewHolder> {
    private List<Map<String, ?>> mDataSet;
    private Context mContext;
    OnItemClickListener mItemClickListener;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

       public void onItemLongClick(View view, int position);

       public void onItemChecked(int position, boolean b);

    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    public CheckOutRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataSet) {
        mContext = myContext;
        mDataSet = myDataSet;
    }

    @Override
    public CheckOutRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        FoldingCell cell = (FoldingCell) v;
        switch (viewType) {
            case 1:
                //v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                cell = (FoldingCell) LayoutInflater.from(parent.getContext()).inflate(R.layout.fold_cardview_layout, parent, false);
                break;
            case 2:
                //v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                cell = (FoldingCell) LayoutInflater.from(parent.getContext()).inflate(R.layout.fold_cardview_layout, parent, false);
                break;
            default:
                //v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                cell = (FoldingCell) LayoutInflater.from(parent.getContext()).inflate(R.layout.fold_cardview_layout, parent, false);
                break;
        }


        ViewHolder vh = new ViewHolder(cell);
        return vh;
    }

    @Override
    public void onBindViewHolder(CheckOutRecyclerViewAdapter.ViewHolder holder, int position) {
        Map<String,?> movie = mDataSet.get(position);
        holder.bindMovieData(movie);

    }



    @Override
    public int getItemCount() {
        int s = mDataSet.size();
        Log.d("size", String.valueOf(s));

        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        Map<String,?> movie = mDataSet.get(position);
        String rating = (String) movie.get("rating");
        return 1;
        /*Double r=8.0;
        if(rating>=r)
            return 1;
        else
            return 2;*/
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;
        public TextView vName;
        public TextView vNameTL;
        public TextView vBrand;
        public TextView vBrandTL;
        public CheckBox vCheckBox;
        public TextView vOrganic;
        public TextView vPrice;
        public TextView vPriceTL;
        public TextView vDescription;
        public TextView vNutrition;
        public ImageView vMenu;



        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.rv_icon);
            vName = (TextView) v.findViewById(R.id.rv_name);
            vNameTL = (TextView)v.findViewById(R.id.rv_nameTL);
            vBrand = (TextView) v.findViewById(R.id.rv_brand);
            vBrandTL = (TextView) v.findViewById(R.id.rv_brandTL);
            vOrganic = (TextView)v.findViewById(R.id.rv_organic);
            vPrice = (TextView)v.findViewById(R.id.rv_price);
            vPriceTL = (TextView)v.findViewById(R.id.rv_priceTL);
            vDescription = (TextView)v.findViewById(R.id.rv_description) ;
            vNutrition = (TextView)v.findViewById(R.id.rv_nutrition);
            vCheckBox = (CheckBox) v.findViewById(R.id.rv_checkbox);
            vMenu = (ImageView) v.findViewById(R.id.rv_overflow);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });


            v.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if(mItemClickListener!=null)
                        mItemClickListener.onItemLongClick(v,getPosition());
                    return true;
                }
            });


            vCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemChecked(getPosition(), isChecked);
                }
            });
        }

            public void bindMovieData(Map<String, ? > movie) {
                if ((Boolean) movie.get("selection")) {
                    // vIcon.setImageResource((Integer)movie.get("image"));
                    Picasso.with(mContext).load(movie.get("url").toString()).into(vIcon);
                    vName.setText((String) movie.get("name"));
                    vNameTL.setText((String)movie.get("name"));
                    vBrand.setText((String) movie.get("brand"));
                    vBrandTL.setText((String) movie.get("brand"));
                    vOrganic.setText((String) movie.get("organic"));
                    vPrice.setText((String) movie.get("price"));
                    vPriceTL.setText((String) movie.get("price"));
                    vDescription.setText((String)movie.get("description"));
                    vNutrition.setText((String)movie.get("nutrition"));
                    vCheckBox.setChecked((Boolean) movie.get("selection"));
                    //vRating.setText( movie.get("rating")+"/10");
                }
            }

        }


    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

}

