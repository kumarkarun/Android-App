package com.android.project.viewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.project.R;
import com.android.project.viewPager.model.Travel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    private static final String EXTRA_TRAVEL = "EXTRA_TRAVEL";
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.title) TextView title;
    @Bind(R.id.aboutMe)TextView aboutme;
    @Bind(R.id.course)TextView course;

    public static Intent newInstance(Context context, Travel travel) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(EXTRA_TRAVEL, travel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        Travel travel = getIntent().getParcelableExtra(EXTRA_TRAVEL);
        if (travel != null) {
            image.setImageResource(travel.getImage());
            title.setText(travel.getName());
            aboutme.setText(travel.getAboutme());
            course.setText(travel.getCourse());
        }
    }
}
