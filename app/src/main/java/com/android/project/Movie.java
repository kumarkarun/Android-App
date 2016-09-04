package com.android.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties({ "selection" })
public class Movie implements Serializable {

    String name, description, director, id , primaryID, image, stars, url, year, length;
    float rating;
    ArrayList<TestClass> testClass = new ArrayList<TestClass>();

    public Movie() {

    }

    public ArrayList<TestClass> getTestClass() {
        return testClass;
    }

    public void setTestClass(ArrayList<TestClass> testClass) {
        this.testClass = testClass;
    }

    /*public static class Record{
        String icon, description;
        public static class Hotels{
            public static class Hotel{
                String experience,image,rating;

                public void setExperience(String experience) {
                    this.experience = experience;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getRating() {
                    return rating;
                }

                public void setRating(String rating) {
                    this.rating = rating;
                }

                public String getExperience() {

                    return experience;
                }
            }
        }
        public static class PlacesVisited{
            public static class Place{
                ArrayList<String> images=new ArrayList<String>();
                HashMap<String, Integer> location = new HashMap<String, Integer>();
                ArrayList<String> videos=new ArrayList<String>();

                public ArrayList<String> getImages() {
                    return images;
                }

                public void setImages(ArrayList<String> images) {
                    this.images = images;
                }

                public HashMap<String, Integer> getLocation() {
                    return location;
                }

                public void setLocation(HashMap<String, Integer> location) {
                    this.location = location;
                }

                public ArrayList<String> getVideos() {
                    return videos;
                }

                public void setVideos(ArrayList<String> videos) {
                    this.videos = videos;
                }
            }
        }
        public static class Inns{
            public class Inn{
                String experience,image,rating;

                public String getExperience() {
                    return experience;
                }

                public void setExperience(String experience) {
                    this.experience = experience;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getRating() {
                    return rating;
                }

                public void setRating(String rating) {
                    this.rating = rating;
                }
            }
        }
    }*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }



}
