package com.android.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Karun on 8/10/16.
 */
public class FlipViewActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipview);

        List<Map<String, ?>> reviewList = PopulateStoresReviewDB.storeReviewList;
        ArrayList<String> notes = new ArrayList<String>();
        notes.add("........Come.............");
        notes.add(".........On..............");
        notes.add("........Flip.............");
        notes.add(".........Me..............");

       //You can also use FlipViewController.VERTICAL
        FlipViewController flipView = new FlipViewController(this, FlipViewController.VERTICAL);

        //We're creating a NoteViewAdapter instance, by passing in the current context and the
        //values to display after each flip
        flipView.setAdapter(new NoteViewAdapter(this, reviewList));

        setContentView(flipView);
    }
    public class NoteViewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Map<String, ?>> notes;

        public NoteViewAdapter(Context currentContext, List<Map<String, ?>> reviewList) {
            inflater = LayoutInflater.from(currentContext);
            notes = reviewList;
            mContext = currentContext;
        }

        @Override
        public int getCount() {
            return notes.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = convertView;

            if (convertView == null) {
                layout = inflater.inflate(R.layout.activity_flipview, null);
            }

            //Get's value from our ArrayList by the position
            String name = notes.get(position).get("name").toString();
            String email = notes.get(position).get("email").toString();
            String review = notes.get(position).get("review").toString();


            TextView name_textView = (TextView) layout.findViewById(R.id.review_name);
            TextView email_textView = (TextView) layout.findViewById(R.id.review_email);
            TextView review_textView = (TextView) layout.findViewById(R.id.review_review);
            ImageView vIcon = (ImageView)layout.findViewById(R.id.review_image);

            name_textView.setText(name);
            email_textView.setText(email);
            review_textView.setText(review);
            Picasso.with(mContext).load(notes.get(position).get("image").toString()).into(vIcon);


            return layout;
        }
    }
}
