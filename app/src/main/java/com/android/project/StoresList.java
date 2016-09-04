package com.android.project;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Karun on 8/2/16.
 */
public class StoresList implements Serializable {

    String address, id , type;
    float rating;
    String destination;


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    ArrayList<TestClass> testClass = new ArrayList<TestClass>();

    public StoresList() {

    }

    public ArrayList<TestClass> getTestClass() {
        return testClass;
    }

    public void setTestClass(ArrayList<TestClass> testClass) {
        this.testClass = testClass;
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



}
