package com.homestay.bipin.guest.guestList;

/**
 * Created by Bipin on 4/26/17.
 */

public class Guest {

    private Integer id;

    private String name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;
    public Guest(Integer id, String name,String date){
        this.id=id;
        this.name=name;
        this.date= date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
