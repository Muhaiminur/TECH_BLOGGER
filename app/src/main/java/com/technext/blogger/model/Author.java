package com.technext.blogger.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "blogauthor")
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("profession")
    @Expose
    private String profession;

    public Author() {
    }

    public Author(String name, String avatar, String profession) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.profession = profession;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}