package com.android.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class detailview_activity extends AppCompatActivity  implements detailview_fragment.youtubeButtonClickedListener{

    private android.support.v7.app.ActionBar ab;
    HashMap<String,?> items;
    String ABC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar mtoolbar;

        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        items = (HashMap<String,?>)bundle.getSerializable("item");

        setContentView(R.layout.detailview_activity);
        ABC=items.get("name").toString();

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(ABC);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#EEFF41"));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#EEFF41"));



        // mtoolbar = (Toolbar)findViewById(R.id.toolbar123);
        // setSupportActionBar(mtoolbar);
        //mtoolbar.setTitle(ABC);
        // ab = getSupportActionBar();


        ImageView imageView=(ImageView)findViewById(R.id.back_image);
        imageView.setImageResource(R.drawable.delete);
        Picasso.with(getApplicationContext()).load(items.get("url").toString()).into(imageView);



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,detailview_fragment.newInstance(items))
                .commit();

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.floatingbutton);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Working fine",Toast.LENGTH_SHORT).show();
                Intent sharingIntent=new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Buying " + items.get("name").toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Shopping");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));

            }
        });





    }


    @Override
    public void onYoutubeButtonClicked(String youtubelink) {
        Intent intent = new Intent(this,YoutubeActivity.class);
        intent.putExtra("youtubelink", (Serializable) youtubelink);
        startActivity(intent);
    }
}
