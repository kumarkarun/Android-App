package com.android.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class detailview_fragment extends Fragment {

    Button youtubeButton;
    private youtubeButtonClickedListener youtubeListener;
    public interface youtubeButtonClickedListener{
        public void onYoutubeButtonClicked(String youtubelink);
    }



    public static detailview_fragment newInstance(HashMap<String,?> mitems) {

        detailview_fragment fragment = new detailview_fragment();
        Bundle args = new Bundle();
        //firebaseStoreLink = urlString+vId;
        //ref = new Firebase(firebaseStoreLink);
        args.putSerializable("item", mitems);

        fragment.setArguments(args);
        return fragment;
    }

    public detailview_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        youtubeListener = (youtubeButtonClickedListener)getContext();
        View rootview = inflater.inflate(R.layout.detailview_fragment,container,false);
        TextView vdesc = (TextView)rootview.findViewById(R.id.desc);
        TextView vbrand = (TextView)rootview.findViewById(R.id.brand);
        RatingBar vrb = (RatingBar)rootview.findViewById(R.id.rating);
        TextView vprice = (TextView)rootview.findViewById(R.id.price);
        TextView vnutrition = (TextView)rootview.findViewById(R.id.nutrition);
        HashMap<String,?> abc = (HashMap<String,?>)this.getArguments().getSerializable("item");
        double rat = Double.parseDouble(abc.get("review").toString());

        vbrand.setText(abc.get("brand").toString());
        vprice.setText(abc.get("price").toString());
        vdesc.setText(abc.get("description").toString());
        vnutrition.setText(abc.get("nutrition").toString());
        vrb.setRating((float)rat);


        final String youtubelink = abc.get("link").toString();
        Log.d("youtube>>>",youtubelink);
        youtubeButton = (Button)rootview.findViewById(R.id.youtube_button);
        youtubeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                youtubeListener.onYoutubeButtonClicked(youtubelink);

               /* Intent intent = new Intent(getActivity(), Task1Activity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d("Animator","inside if");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), v, "activityAnimation");
                    getActivity().startActivity(intent, options.toBundle());
                }
                else {
                    startActivity(intent);
                    Log.d("Animator", "inside else");
                }*/
            }
        });


        return rootview;
    }

}
