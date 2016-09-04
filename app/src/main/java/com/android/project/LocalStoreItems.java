package com.android.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({ "selection" })
public class LocalStoreItems implements Serializable {

    List<Map<String,?>> storeItemsList = new ArrayList<Map<String, ?>>();

    public List<Map<String, ?>> getStoreItemsList() {
        return storeItemsList;
    }

    public int getSize(){
        return storeItemsList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < storeItemsList.size()){
            return (HashMap) storeItemsList.get(i);
        } else return null;
    }
    public void removeItem(int i){
        if (i >=0 && i < storeItemsList.size()){
            storeItemsList.remove(i);
        }
    }
    public void addItem(int i, HashMap<String, ?> movie) {
        //if (i >= 0 && i < storeItemsList.size()) {
            storeItemsList.add(movie);
        //}
    }

    public HashMap createMovie(String name,
                                //String image,
                                String description,
                                //String year,
                                //String length,
                                String rating,
                                //String director,
                                //String stars,
                                String url) {
        HashMap item = new HashMap();
        //movie.put("image", image);
        item.put("name", name);
        item.put("description", description);
        //movie.put("year", year);
        //movie.put("length", length);
        item.put("rating", rating);
        //movie.put("director", director);
        //movie.put("stars", stars);
        item.put("url", url);
        item.put("selection", false);
        return item;
    }

    String name;
    String description;
    String director;
    String id;
    String primaryID;
    String image;
    String stars;
    String url;
    String year;
    String length;
    String selection;
    float rating;




    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    ArrayList<TestClass> testClass = new ArrayList<TestClass>();

    public LocalStoreItems() {

    }

    public ArrayList<TestClass> getTestClass() {
        return testClass;
    }

    public void setTestClass(ArrayList<TestClass> testClass) {
        this.testClass = testClass;
    }


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
