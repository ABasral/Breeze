package com.example.ritika.breeze;

public class blog {


    private String desc;
    private String image;
    private String name;
    private String userid;

    public blog(){

    }

    public blog(String name, String desc, String image, String userid) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.userid = userid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
