package com.android.project;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

//


public class ImageUploadFragment extends Fragment {

    public static final String ARG_USER = "user";
    Issue issue = new Issue();
    Button gallerybutton;
    Button camerabutton;
    ImageView cameraimage;
    final static int GALLERY_PICTURE = 0;
    final static int CAMERA_PICTURE = 1;
    String selectedImagePath;
    Bitmap bitmap;
    static String issuevalue ="";
    final Firebase ref = new Firebase("https://grocerryapp-acf0e.firebaseio.com/storesReportIssue/Asian Store");
    private static String[] PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


    public ImageUploadFragment() {
        // Required empty public constructor
    }

    public static ImageUploadFragment newInstance(String userId, String issueText) {
        ImageUploadFragment fragment = new ImageUploadFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, userId);
        fragment.setArguments(args);
        issuevalue = issueText;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //carUser = (CarData) getArguments().getSerializable(ARG_USER);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image_upload, container, false);
        gallerybutton = (Button) rootView.findViewById(R.id.gallerybutton);
        camerabutton = (Button) rootView.findViewById(R.id.camerabutton);
        cameraimage = (ImageView) rootView.findViewById(R.id.cameraimage);


        Button continueButton = (Button) rootView.findViewById(R.id.continueButton2);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
                bitmap.recycle();
                byte[] byteArray = bYtE.toByteArray();
                String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
                if (imageFile.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    issue.setImage(imageFile);
                    issue.setIssue(issuevalue);
                    //carUser.setUrl(imageFile);
                    ref.child("user3").setValue(issue);
                    //ref.child(carUser.getId()).setValue(carUser);
                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                    //myIntent.putExtra("CarData", carUser);
                    startActivity(myIntent);
                }
            }
        });
        gallerybutton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                int hasGalleryPermission = ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), String.valueOf(PERMISSION));
                if (hasGalleryPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION, GALLERY_PICTURE);
                    return;
                }
                Intent pictureActionIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
            }
        });


        camerabutton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                int hasCameraPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA);
                if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PICTURE);
                    return;
                }
                Intent pictureActionIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(pictureActionIntent, CAMERA_PICTURE);
            }
        });
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PICTURE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_PICTURE);
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Camera Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case GALLERY_PICTURE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent pictureActionIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Gallery Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PICTURE && resultCode == getActivity().RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            cameraimage.setImageBitmap(bitmap);
        } else if (resultCode == getActivity().RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                selectedImagePath = c.getString(columnIndex);
                c.close();
                bitmap = BitmapFactory.decodeFile(selectedImagePath);
                //byte[] decodedString = Base64.decode(imageFile, Base64.DEFAULT);
                //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                cameraimage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
