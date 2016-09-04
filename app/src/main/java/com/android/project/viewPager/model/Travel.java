package com.android.project.viewPager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by florentchampigny on 21/06/2016.
 */
public class Travel implements Parcelable{
    String name;
    int image;
    String course;
    String aboutme;

    public Travel(String name, int image ,String course, String description) {
        this.name = name;
        this.image = image;
        this.course = course;
        this.aboutme = description;
    }

    protected Travel(Parcel in) {
        name = in.readString();
        image = in.readInt();
        course = in.readString();
        aboutme = in.readString();
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getCourse(){return course;}

    public String getAboutme(){return aboutme;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeString(course);
        dest.writeString(aboutme);
    }
}
