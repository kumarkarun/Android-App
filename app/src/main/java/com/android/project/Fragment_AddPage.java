package com.android.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class Fragment_AddPage extends Fragment {

    private static final int RESULT_LOAD_IMAGE=1;
    Button addImage;
    ImageView displayPic,displayPic2;
    public static Fragment_AddPage newInstance() {

        Fragment_AddPage fragment = new Fragment_AddPage();
        //Bundle args = new Bundle();
        //args.putSerializable(ARG_MOVIE_DATA, (Serializable) movieData);
        //fragment.setArguments(args);
        return fragment;
    }

    public Fragment_AddPage() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.new_record, container, false);
        addImage = (Button) rootView.findViewById(R.id.fv_addImage);
        displayPic = (ImageView) rootView.findViewById(R.id.fv_icon);
        displayPic2 = (ImageView) rootView.findViewById(R.id.fv_icon2);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Hello","From button addimages");
                Intent galleyIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleyIntent, RESULT_LOAD_IMAGE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //this method is called when a user selected a picture from the gallery
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE  && data!=null){
            Uri selectedImage = data.getData();
            displayPic.setImageURI(selectedImage);
            Bitmap image = ((BitmapDrawable) displayPic.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // The output stream holds the byte representation of the image
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // Compress the image to JPEG with Quality 100 and put into Bytearraystream
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT); //Encoded string value of the image
            byte[] decodedBytes = Base64.decode(encodedImage, 0);
            Bitmap image2= BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            displayPic2.setImageBitmap(image2);
            Log.d("Image String",encodedImage);
        }
    }
}
