package com.example.dengueinfosys;

public class Model {
    private int id;
    private String name;
    private String date;
    private String cond;
    private byte[] image;

    public Model(int id, String name, String date, String cond, byte[] image) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.cond = cond;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


