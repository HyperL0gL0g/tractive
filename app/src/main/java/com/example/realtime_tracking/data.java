package com.example.realtime_tracking;

public class data {

    private  String name,address,lat,lng,online;

    public data(){}

    public data(String name, String address,String lat,String lng,String online) {
        this.name = name;
        this.address = address;

        this.lat=lat;
        this.lng=lng;
        this.online=online;
    }

    public data(String lat,String lng){
        this.lat = lat;
        this.lng = lng;

    }
    public data (String online)
    {
        this.online=online;
    }


    public String getOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }




    public String getLat(){
        return lat;
    }
    public String getLng(){
        return lng;
    }
}
