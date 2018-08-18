package com.example.ritika.breeze;

public class newevent {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    private String title;
    private String date;
    private String time;
    private String venue;
    private String desc;

    public newevent(){

    }

    public newevent(String title, String date, String time, String venue,String desc) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
